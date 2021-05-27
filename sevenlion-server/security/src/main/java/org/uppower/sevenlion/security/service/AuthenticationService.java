package org.uppower.sevenlion.security.service;


import org.uppower.sevenlion.security.model.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/19 3:52 下午
 */
public interface AuthenticationService<T> {

    /**
     * 登录逻辑处理
     * @param body
     * @param passwordEncoder
     * @return
     */
    public UserDetails loadUser(T body, PasswordEncoder passwordEncoder);
}
