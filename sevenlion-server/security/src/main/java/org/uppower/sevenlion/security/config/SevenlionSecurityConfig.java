package org.uppower.sevenlion.security.config;


import org.uppower.sevenlion.security.common.SecurityAuthenticationProvider;
import org.uppower.sevenlion.security.filter.AuthenticationProcessingFilter;
import org.uppower.sevenlion.security.filter.SevenlionAuthenticationEntryPoint;
import org.uppower.sevenlion.security.filter.SevenlionAuthenticationFilter;
import org.uppower.sevenlion.security.handler.AuthenticationTokenResponseHandler;
import org.uppower.sevenlion.security.handler.SevenlionAuthenticationFailureHandler;
import org.uppower.sevenlion.security.handler.SevenlionAuthenticationSuccessHandler;
import org.uppower.sevenlion.security.properties.SevenlionSecurityProperties;
import org.uppower.sevenlion.security.resolver.SevenlionSecurityWebArgumentResolver;
import org.uppower.sevenlion.security.service.AuthenticationService;
import org.uppower.sevenlion.security.service.JwtToRedisTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 3:48 ??????
 */
@Configuration
@EnableConfigurationProperties(SevenlionSecurityProperties.class)
public class SevenlionSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {


    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthenticationProcessingFilter authenticationProcessingFilter;

//    @Autowired
//    List<UrlStrategyMatcher> urlStrategyMatchers;

    @Autowired(required = false)
    private List<AuthenticationService> authenticationServices;

    @Autowired
    private SevenlionSecurityProperties properties;
//
//    @Autowired(required = false)
//    private SimpleCorsFilter simpleCorsFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
        .and()
        .authenticationProvider(new SecurityAuthenticationProvider(authenticationServices,passwordEncoder(),new ObjectMapper(),properties));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable()
                .sessionManagement().disable()
                .cors()
                .and()
                .csrf().disable()
                .httpBasic().disable();
        //????????????filter
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //????????????filter
        http.addFilterAfter(authenticationProcessingFilter,UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        return httpServletRequest -> {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.addAllowedHeader("*");
            cfg.addAllowedMethod("*");
            cfg.addAllowedOrigin("*");
            cfg.setAllowCredentials(true);
            cfg.checkOrigin("*");
            return cfg;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SevenlionAuthenticationFilter authenticationFilter() throws Exception {
        return new SevenlionAuthenticationFilter(authenticationManager(),authenticationSuccessHandler,authenticationFailureHandler,new ObjectMapper());
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SevenlionAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SevenlionAuthenticationFailureHandler();
    }

    @Bean
    public SevenlionAuthenticationEntryPoint sevenlionAuthenticationEntryPoint() {
        return new SevenlionAuthenticationEntryPoint(new ObjectMapper());
    }

    @Bean
    public AuthenticationTokenResponseHandler authenticationTokenResponseHandler() {
        return new AuthenticationTokenResponseHandler(new ObjectMapper());
    }

    @Bean
    public AuthenticationProcessingFilter authenticationProcessingFilter() {
        return new AuthenticationProcessingFilter();
    }


    @Bean
    public JwtToRedisTokenService jwtToRedisTokenService(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
//        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // ????????????value?????????????????????FastJsonRedisSerializer???
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // ????????????key?????????????????????StringRedisSerializer???
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return new JwtToRedisTokenService(redisTemplate);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SevenlionSecurityWebArgumentResolver(properties.getInjectClass(),new ObjectMapper()));
    }
}
