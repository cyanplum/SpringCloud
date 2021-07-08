package org.uppower.sevenlion.web.user.server.controller;

import io.swagger.annotations.Api;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.uppower.sevenlion.security.annotation.LoginMapping;
import org.uppower.sevenlion.security.model.UserDetails;
import org.uppower.sevenlion.security.service.AuthenticationService;
import org.uppower.sevenlion.common.model.user.UserInfo;
import org.uppower.sevenlion.common.model.user.vo.UserLoginVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/24 6:12 下午
 */
@Api(tags = "登录接口")
@LoginMapping("/user/login")
public class UserLoginController implements AuthenticationService<UserLoginVO> {


    @Override
    public UserDetails loadUser(UserLoginVO body, PasswordEncoder passwordEncoder) {
        UserInfo userInfo = new UserInfo();
        List<String> userRoles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        userInfo.setRoles(userRoles);
        userInfo.setPermissions(permissions);
        userInfo.setNickname("qmw");
        userInfo.setUserId(1L);
        UserDetails<UserInfo> userDetails = new UserDetails<UserInfo>();
        userDetails.setUserDetail(userInfo);
        userDetails.setEnable(true);
        userDetails.setNonLocked(true);
        userDetails.setId(userInfo.getUserId());
        userDetails.setPermission(permissions);
        userDetails.setRoles(userRoles);
        return userDetails;
    }
}
