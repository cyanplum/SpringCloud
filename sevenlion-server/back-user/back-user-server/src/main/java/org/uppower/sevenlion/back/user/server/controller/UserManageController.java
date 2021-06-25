package org.uppower.sevenlion.back.user.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.sevenlion.back.user.server.model.bo.AdminUserSaveBo;
import org.uppower.sevenlion.back.user.server.model.bo.AdminUserUpdateBo;
import org.uppower.sevenlion.back.user.server.model.query.AdminUserQueryModel;
import org.uppower.sevenlion.back.user.server.model.query.UserQueryModel;
import org.uppower.sevenlion.back.user.server.model.vo.AdminUserInfoVo;
import org.uppower.sevenlion.back.user.server.model.vo.AdminUserListVo;
import org.uppower.sevenlion.back.user.server.model.vo.UserInfoVo;
import org.uppower.sevenlion.back.user.server.model.vo.UserListVo;
import org.uppower.sevenlion.back.user.server.service.UserManageService;
import org.uppower.sevenlion.common.model.admin.AdminInfo;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.common.utils.PageInfo;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/26 3:39 下午
 */
@Api(tags = "用户管理")
@RestController
public class UserManageController {

    @Autowired
    private UserManageService userManageService;

    @ApiOperation("查询后台用户列表")
    @GetMapping("/adminUser")
    public CommonResult<PageInfo<AdminUserListVo>> indexAdminUser(@ApiIgnore AdminInfo adminInfo,
                                                                  AdminUserQueryModel queryModel) {
        return CommonResult.success(userManageService.indexAdminUser(adminInfo, queryModel));
    }

    @ApiOperation("查询后台用户详情")
    @GetMapping("/adminUser/{id}")
    public CommonResult<AdminUserInfoVo> showAdminUser(@ApiIgnore AdminInfo adminInfo,
                                                       @ApiParam("用户id") @PathVariable Long id) {
        return CommonResult.success(userManageService.showAdminUser(adminInfo,id));
    }

    @ApiOperation("新增后台用户")
    @PostMapping("/adminUser")
    public CommonResult saveAdminUser(@ApiIgnore AdminInfo adminInfo,
                                      @ApiParam("新增后台用户对象") @Valid @RequestBody AdminUserSaveBo bo) {
        userManageService.saveAdminUser(adminInfo, bo);
        return CommonResult.success();
    }

    @ApiOperation("修改后台用户")
    @PutMapping("/adminUser/{id}")
    public CommonResult updateAdminUser(@ApiIgnore AdminInfo adminInfo,
                                        @ApiParam("管理用户id") @PathVariable Long id,
                                        @ApiParam("修改后台用户对象") @Valid @RequestBody AdminUserUpdateBo bo) {
        userManageService.updateAdminUser(adminInfo, id, bo);
        return CommonResult.success();
    }

    @ApiOperation("删除后台用户")
    @DeleteMapping("/adminUser/{id}")
    public CommonResult deleteAdminUser(@ApiIgnore AdminInfo adminInfo,
                                        @ApiParam("用户id") @PathVariable Long id) {
        userManageService.deleteAdminUser(adminInfo,id);
        return CommonResult.success();
    }

    @ApiOperation("修改后台用户状态")
    @PatchMapping("/adminUser/{id:.+}/{status:.+}")
    public CommonResult updateAdminUserStatus(@ApiIgnore AdminInfo adminInfo,
                                              @ApiParam("用户id") @PathVariable Long id,
                                              @ApiParam("用户状态") @PathVariable Integer status) {
        userManageService.updateAdminUserStatus(adminInfo, id, status);
        return CommonResult.success();
    }

    @ApiOperation("查询前台用户")
    @GetMapping("/user")
    public CommonResult<PageInfo<UserListVo>> indexUser(@ApiIgnore AdminInfo adminInfo,
                                                        UserQueryModel userQueryModel) {
        return CommonResult.success(userManageService.indexUser(adminInfo, userQueryModel));
    }

    @ApiOperation("查询前台用户详情")
    @GetMapping("/user/{id:.+}")
    public CommonResult<UserInfoVo> showUser(AdminInfo adminInfo,
                                             @ApiParam("用户id") @PathVariable Long id) {
        return CommonResult.success(userManageService.showUser(adminInfo, id));
    }

    @ApiOperation("修改用户状态")
    @PatchMapping("/user/{id}/{status}")
    public CommonResult updateUserStatus(@ApiParam("用户id") @PathVariable Long id,
                                         @ApiParam("状态") @PathVariable Integer status) {
        userManageService.updateUserStatus(id, status);
        return CommonResult.success();
    }
}
