package org.uppower.sevenlion.security.filter;

import org.uppower.sevenlion.security.annotation.LoginMapping;
import org.uppower.sevenlion.security.exceptions.SevenlionException;
import org.uppower.sevenlion.security.handler.AuthenticationTokenResponseHandler;
import org.uppower.sevenlion.security.model.image.ImageValidateCodeBean;
import org.uppower.sevenlion.security.model.token.CheckAuthenticationToken;
import org.uppower.sevenlion.security.reposiroty.AbstractValidateCodeService;
import org.uppower.sevenlion.security.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 3:51 ??????
 * ??????????????????????????????????????????????????????
 * ?????????????????????????????????
 * ??????????????????????????????
 */
public class SevenlionAuthenticationFilter extends GenericFilterBean implements InitializingBean {


    private AuthenticationManager authenticationManager;

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    private AuthenticationFailureHandler authenticationFailureHandler;

    private ObjectMapper objectMapper;

    public SevenlionAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.objectMapper = objectMapper;
    }

    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    @Autowired(required = false)
    private List<AuthenticationService> authenticationServices;

    @Autowired
    private AuthenticationEventPublisher authenticationEventPublisher;

    @Autowired
    private AuthenticationTokenResponseHandler authenticationTokenResponseHandler;

    @Autowired
    private SevenlionAuthenticationEntryPoint sevenlionAuthenticationEntryPoint;

    @Autowired(required = false)
    private Map<String, AbstractValidateCodeService> abstractValidateCodeServices;

    private List<RequestMatcher> requiresAuthenticationRequestMatchers = null;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            try{
                if (httpRequest.getMethod().equals("OPTIONS") || !checkLoginUrl(httpRequest)) {
                    filterChain.doFilter(request, response);
                    return;
                }
                if (!httpRequest.getMethod().equals("POST")) {
                    throw new SevenlionException("authentication http method not supported");
                }
                AuthenticationService service = findService(httpRequest);
                //???????????????????????????
                Type genericInterface = service.getClass().getGenericInterfaces()[0];
                //??????????????????????????????
                Class formClass = (Class) ((ParameterizedTypeImpl) genericInterface).getActualTypeArguments()[0];
                //??????????????????
                Object body = parseForm(httpRequest, formClass);

                //???????????????
                if (body instanceof ImageValidateCodeBean) {
                    if (abstractValidateCodeServices == null || abstractValidateCodeServices.isEmpty()) {
                        throw new SevenlionException("???????????????????????????????????????");
                    }
                    for (String key : abstractValidateCodeServices.keySet()) {
                        abstractValidateCodeServices.get(key).validate(((ImageValidateCodeBean) body).getImageCode(),((ImageValidateCodeBean) body).getDeviceId());
                    }
                }
                //??????token??????manager????????????
                CheckAuthenticationToken authenticationToken = new CheckAuthenticationToken(null,body,false,formClass);
                authenticationToken.setDetails(authenticationDetailsSource.buildDetails(httpRequest));
                //manager???????????????authenticationProvider?????????
                Authentication authenticate = authenticationManager.authenticate(authenticationToken);
                //???????????????
                success(authenticate,httpRequest,httpResponse,false);
            }catch (Exception e) {
                authenticationEventPublisher.publishAuthenticationFailure(new BadCredentialsException(e.getMessage()),
                        new PreAuthenticatedAuthenticationToken("access-token", "N/A"));
                if (authenticationFailureHandler != null) {
                    authenticationFailureHandler.onAuthenticationFailure(httpRequest, httpResponse, new BadCredentialsException(e.getMessage(), e));
                }
                sevenlionAuthenticationEntryPoint.commence(httpRequest,httpResponse,new BadCredentialsException(e.getMessage(), e));
            }
        }
    }

    private boolean checkLoginUrl(HttpServletRequest httpRequest) {
        if (requiresAuthenticationRequestMatchers == null) {
            return false;
        }
        for (RequestMatcher it : requiresAuthenticationRequestMatchers) {
            if (it.matches(httpRequest)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ???????????????????????????service??????
     * @param request
     * @return
     */
    public AuthenticationService findService(HttpServletRequest request) {
        AuthenticationService authenticationService = null;
        for (AuthenticationService service : authenticationServices) {
            LoginMapping annotation = AnnotationUtils.findAnnotation(service.getClass(), LoginMapping.class);
            if (annotation != null) {
                String value = annotation.value();
                //????????????????????????url???????????????url??????????????????????????????????????????
                if (value.trim().equals(request.getRequestURI())) {
                    authenticationService = service;
                    break;
                }
            }
        }
        if (authenticationService == null) {
            throw new SevenlionException("service ?????????");
        }
        return authenticationService;
    }


    private <T> T parseForm(HttpServletRequest httpRequest, Class<T> formClass) throws IOException {
        return objectMapper.readValue(httpRequest.getInputStream(),formClass);
    }


    /**
     * ????????????????????????spring security,??????????????????
     * @param authentication
     * @param request
     * @param response
     * @param isRefresh
     * @throws IOException
     */
    private void success(Authentication authentication, HttpServletRequest request, HttpServletResponse response, boolean isRefresh) throws IOException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        authenticationEventPublisher.publishAuthenticationSuccess(authentication);
        authenticationTokenResponseHandler.responseWithToken(request,response, authentication);
    }

    @Override
    public void afterPropertiesSet() {
       setAuthFilterProcess();
    }

    /**
     * ???????????????????????????????????????url??????
     */
    private void setAuthFilterProcess() {
        if (authenticationServices == null ||authenticationServices.isEmpty()) {
            return;
            //throw new SevenlionException("???????????????");
        }
        List<String> urls = new ArrayList<>();
        List<RequestMatcher> requestMatchers = new ArrayList<>();
        for (AuthenticationService it : authenticationServices) {
            LoginMapping annotation = AnnotationUtils.findAnnotation(it.getClass(), LoginMapping.class);
            if (annotation != null) {
                urls.add(annotation.value());
                requestMatchers.add(new AntPathRequestMatcher(annotation.value()));
            }
        }
        if (urls.isEmpty()) {
            throw new SevenlionException("??????url????????????");
        }
        this.requiresAuthenticationRequestMatchers = requestMatchers;
    }
}
