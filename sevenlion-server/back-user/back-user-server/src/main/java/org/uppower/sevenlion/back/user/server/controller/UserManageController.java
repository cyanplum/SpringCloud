package org.uppower.sevenlion.back.user.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.sevenlion.back.user.server.model.result.AdminUserInfoResult;
import org.uppower.sevenlion.back.user.server.model.result.AdminUserListResult;
import org.uppower.sevenlion.back.user.server.model.result.UserInfoResult;
import org.uppower.sevenlion.back.user.server.model.result.UserListResult;
import org.uppower.sevenlion.back.user.server.model.vo.AdminUserSaveVO;
import org.uppower.sevenlion.back.user.server.model.vo.AdminUserUpdateVO;
import org.uppower.sevenlion.back.user.server.service.UserManageService;
import org.uppower.sevenlion.common.model.admin.AdminInfo;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.common.utils.CommonResultPage;
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
    public CommonResultPage<AdminUserListResult> indexAdminUser(@ApiIgnore AdminInfo adminInfo,
                                                                @ApiParam("页码") @RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn,
                                                                @ApiParam("页数大小") @RequestParam(value = "pageSize", required = false, defaultValue = "15") Integer pageSize,
                                                                @ApiParam("用户名") @RequestParam(value = "name",required = false) String name) {
        return userManageService.indexAdminUser(adminInfo, pn, pageSize, name);
    }

    @ApiOperation("查询后台用户详情")
    @GetMapping("/adminUser/{id}")
    public CommonResult<AdminUserInfoResult> showAdminUser(@ApiIgnore AdminInfo adminInfo,
                                                           @ApiParam("用户id") @PathVariable Long id) {
        return userManageService.showAdminUser(adminInfo,id);
    }

    @ApiOperation("新增后台用户")
    @PostMapping("/adminUser")
    public CommonResult saveAdminUser(@ApiIgnore AdminInfo adminInfo,
                                      @ApiParam("新增后台用户对象") @Valid @RequestBody AdminUserSaveVO vo) {
        return userManageService.saveAdminUser(adminInfo, vo);
    }

    @ApiOperation("修改后台用户")
    @PutMapping("/adminUser/{id}")
    public CommonResult updateAdminUser(@ApiIgnore AdminInfo adminInfo,
                                        @ApiParam("管理用户id") @PathVariable Long id,
                                        @ApiParam("修改后台用户对象") @Valid @RequestBody AdminUserUpdateVO vo) {
        return userManageService.updateAdminUser(adminInfo, id, vo);
    }

    @ApiOperation("删除后台用户")
    @DeleteMapping("/adminUser/{id}")
    public CommonResult deleteAdminUser(@ApiIgnore AdminInfo adminInfo,
                                        @ApiParam("用户id") @PathVariable Long id) {
        return userManageService.deleteAdminUser(adminInfo,id);
    }

    @ApiOperation("修改后台用户状态")
    @PatchMapping("/adminUser/{id}/{status}")
    public CommonResult updateAdminUserStatus(@ApiIgnore AdminInfo adminInfo,
                                              @ApiParam("用户id") @PathVariable Long id,
                                              @ApiParam("用户状态") @PathVariable Integer status) {
        return userManageService.updateAdminUserStatus(adminInfo, id, status);
    }

    @ApiOperation("查询前台用户")
    @GetMapping("/user")
    public CommonResultPage<UserListResult> indexUser(@ApiIgnore AdminInfo adminInfo,
                                                      @ApiParam("页码") @RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn,
                                                      @ApiParam("页大小") @RequestParam(value = "pageSize", required = false, defaultValue = "15") Integer pageSize,
                                                      @ApiParam("名称") @RequestParam(value = "name",required = false) String name,
                                                      @ApiParam("电话号码") @RequestParam(value = "phone",required = false) String phone) {
        return userManageService.indexUser(adminInfo, pn, pageSize, name, phone);
    }

    @ApiOperation("查询前台用户详情")
    @GetMapping("/user/{id}")
    public CommonResult<UserInfoResult> showUser(AdminInfo adminInfo,
                                                 @ApiParam("用户id") @PathVariable Long id) {
        return userManageService.showUser(adminInfo, id);
    }
}
