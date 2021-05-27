package org.uppower.sevenlion.back.user.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.sevenlion.back.user.server.model.result.AdminUserInfoResult;
import org.uppower.sevenlion.back.user.server.model.result.AdminUserListResult;
import org.uppower.sevenlion.back.user.server.model.vo.AdminUserSaveVO;
import org.uppower.sevenlion.back.user.server.model.vo.AdminUserUpdateVO;
import org.uppower.sevenlion.back.user.server.service.UserManageService;
import org.uppower.sevenlion.common.model.admin.AdminInfo;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.common.utils.CommonResultPage;

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
    public CommonResultPage<AdminUserListResult> indexAdminUser(AdminInfo adminInfo,
                                                                      @ApiParam("页码") @RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn,
                                                                      @ApiParam("页数大小") @RequestParam(value = "pageSize", required = false, defaultValue = "15") Integer pageSize,
                                                                      @ApiParam("用户名") @RequestParam(value = "name",required = false) String name) {
        return userManageService.indexAdminUser(adminInfo, pn, pageSize, name);
    }

    @ApiOperation("查询后台用户详情")
    @GetMapping("/adminUser/{id}")
    public CommonResult<AdminUserInfoResult> showAdminUser(AdminInfo adminInfo,
                                                           @ApiParam("用户id") @PathVariable Long id) {
        return userManageService.showAdminUser(adminInfo,id);
    }

    @ApiOperation("新增后台用户")
    @PostMapping("/adminUser")
    public CommonResult saveAdminUser(AdminInfo adminInfo,
                                      @ApiParam("新增后台用户对象") @Valid @RequestBody AdminUserSaveVO vo) {
        return userManageService.saveAdminUser(adminInfo, vo);
    }

    @ApiOperation("修改后台用户")
    @PutMapping("/adminUser/{id}")
    public CommonResult updateAdminUser(AdminInfo adminInfo,
                                        @ApiParam("管理用户id") @PathVariable Long id,
                                        @ApiParam("修改后台用户对象") @Valid @RequestBody AdminUserUpdateVO vo) {
        return userManageService.updateAdminUser(adminInfo, id, vo);
    }

    @ApiOperation("删除后台用户")
    @DeleteMapping("/adminUser/{id}")
    public CommonResult deleteAdminUser(AdminInfo adminInfo,
                                        @ApiParam("用户id") @PathVariable Long id) {
        return userManageService.deleteAdminUser(adminInfo,id);
    }

    @ApiOperation("修改后台用户状态")
    @PatchMapping("/adminUser/{id}/{status}")
    public CommonResult updateAdminUserStatus(AdminInfo adminInfo,
                                              @ApiParam("用户id") @PathVariable Long id,
                                              @ApiParam("用户状态") @PathVariable Integer status) {
        return userManageService.updateAdminUserStatus(adminInfo, id, status);
    }
}
