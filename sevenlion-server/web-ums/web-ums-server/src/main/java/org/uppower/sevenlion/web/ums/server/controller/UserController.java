package org.uppower.sevenlion.web.ums.server.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.sevenlion.utils.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.sevenlion.common.constant.RedisConst;
import org.uppower.sevenlion.common.exceptions.BaseException;
import org.uppower.sevenlion.common.model.user.UserInfo;
import org.uppower.sevenlion.web.ums.server.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation("个人首页")
    public CommonResult show(@ApiIgnore UserInfo userInfo) {
        return CommonResult.success(userService.show(userInfo));
    }

    @ApiOperation("签到")
    @GetMapping("/signIn")
    public CommonResult signIn(@ApiIgnore UserInfo userInfo) {
        if (ObjectUtil.isNull(userInfo)) {
            throw new BaseException("用户未登陆，请登录！");
        }
        userService.singIn(userInfo.getUserId());
        return CommonResult.success();
    }

}
