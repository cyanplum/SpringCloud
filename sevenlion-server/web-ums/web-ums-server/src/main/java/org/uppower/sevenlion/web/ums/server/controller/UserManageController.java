package org.uppower.sevenlion.web.ums.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.sevenlion.common.model.user.UserInfo;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.web.ums.server.service.UserManageService;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/20 10:35 下午
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserManageController {

    @Autowired
    private UserManageService userManageService;

    @GetMapping
    @ApiOperation("个人首页")
    public CommonResult show(UserInfo userInfo) {
        return CommonResult.success(userManageService.show(userInfo));
    }

}
