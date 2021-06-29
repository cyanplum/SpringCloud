package org.uppower.sevenlion.back.user.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.sevenlion.back.user.server.model.bo.RoleSaveBo;
import org.uppower.sevenlion.back.user.server.model.bo.RoleUpdateBo;
import org.uppower.sevenlion.back.user.server.model.query.PermissionQueryModel;
import org.uppower.sevenlion.back.user.server.model.vo.PermissionInfoVo;
import org.uppower.sevenlion.back.user.server.model.vo.RoleInfoVo;
import org.uppower.sevenlion.back.user.server.model.vo.RoleListVo;
import org.uppower.sevenlion.back.user.server.service.PermissionManageService;
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
 * @date 2021/5/25 10:15 下午
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/permission")
public class PermissionManagerController{


    @Autowired
    private PermissionManageService permissionManageService;

    @ApiOperation("查询角色列表")
    @GetMapping("/role")
    public CommonResult<PageInfo<RoleListVo>> indexRole(@ApiIgnore AdminInfo adminInfo,
                                                        PermissionQueryModel queryModel) {
        return CommonResult.success(permissionManageService.indexRole(adminInfo, queryModel));
    }

    @ApiOperation("查询角色详情")
    @GetMapping("/role/{id:.+}")
    public CommonResult<RoleInfoVo> showRole(@ApiIgnore AdminInfo adminInfo,
                                             @ApiParam("权限id") @PathVariable Long id) {
        return CommonResult.success(permissionManageService.showRole(adminInfo, id));
    }

    @ApiOperation("新增角色")
    @PostMapping("/role")
    public CommonResult saveRole(@ApiIgnore AdminInfo adminInfo,
                                 @ApiParam("新增角色对象") @Valid @RequestBody RoleSaveBo bo) {
        permissionManageService.saveRole(adminInfo, bo);
        return CommonResult.success();
    }

    @ApiOperation("修改角色")
    @PutMapping("/role/{id:.+}")
    public CommonResult updateRole(@ApiIgnore AdminInfo adminInfo,
                                   @ApiParam("角色id") @PathVariable Long id,
                                   @ApiParam("修改角色对象") @Valid @RequestBody RoleUpdateBo bo) {
        permissionManageService.updateRole(adminInfo, id, bo);
        return CommonResult.success();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/role/{id:.+}")
    public CommonResult deleteRole(@ApiIgnore AdminInfo adminInfo,
                                   @ApiParam("角色id") @PathVariable Long id) {
        permissionManageService.deleteRole(adminInfo, id);
        return CommonResult.success();
    }

    @ApiOperation("查询当前用户权限列表")
    @GetMapping
    public CommonResult<List<PermissionInfoVo>> indexPermission(@ApiIgnore AdminInfo adminInfo) {
        return CommonResult.success(permissionManageService.indexPermission(adminInfo));
    }

}
