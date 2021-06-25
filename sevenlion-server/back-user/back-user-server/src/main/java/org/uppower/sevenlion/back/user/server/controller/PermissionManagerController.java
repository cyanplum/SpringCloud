//package org.uppower.sevenlion.back.user.server.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.uppower.sevenlion.back.user.server.model.result.PermissionInfoResult;
//import org.uppower.sevenlion.back.user.server.model.result.RoleInfoResult;
//import org.uppower.sevenlion.back.user.server.model.result.RoleListResult;
//import org.uppower.sevenlion.back.user.server.model.vo.RoleSaveVO;
//import org.uppower.sevenlion.back.user.server.model.vo.RoleUpdateVO;
//import org.uppower.sevenlion.back.user.server.service.PermissionManageService;
//import org.uppower.sevenlion.common.model.admin.AdminInfo;
//import org.uppower.sevenlion.common.utils.CommonResult;
//import springfox.documentation.annotations.ApiIgnore;
//
//import javax.validation.Valid;
//import java.util.List;
//
///**
// * @author create by:
// * *      ____        ___  ___       __          __
// * *    /  _  \     /   |/   |      | |        / /
// * *   | | | |     / /|   /| |     | |  __   / /
// * *  | | | |     / / |__/ | |    | | /  | / /
// * * | |_| |_    / /       | |   | |/   |/ /
// * * \_______|  /_/        |_|  |___/|___/
// * @date 2021/5/25 10:15 下午
// */
//@Api(tags = "权限管理")
//@RestController
//@RequestMapping("/permission")
//public class PermissionManagerController{
//
//
//    @Autowired
//    private PermissionManageService permissionManageService;
//
//    @ApiOperation("查询角色列表")
//    @GetMapping("/role")
//    public CommonResultPage<RoleListResult> indexRole(@ApiIgnore AdminInfo adminInfo,
//                                                      @ApiParam("页码") @RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn,
//                                                      @ApiParam("页数大小") @RequestParam(value = "pageSize", required = false, defaultValue = "15") Integer pageSize,
//                                                      @ApiParam("权限名称") @RequestParam(value = "name", required = false) String name) {
//        return permissionManageService.indexRole(adminInfo, pn, pageSize, name);
//    }
//
//    @ApiOperation("查询角色详情")
//    @GetMapping("/role/{id}")
//    public CommonResult<RoleInfoResult> showRole(@ApiIgnore AdminInfo adminInfo,
//                                                 @ApiParam("权限id") @PathVariable Long id) {
//        return permissionManageService.showRole(adminInfo, id);
//    }
//
//    @ApiOperation("新增角色")
//    @PostMapping("/role")
//    public CommonResult saveRole(@ApiIgnore AdminInfo adminInfo,
//                                 @ApiParam("新增角色对象") @Valid @RequestBody RoleSaveVO vo) {
//        return permissionManageService.saveRole(adminInfo, vo);
//    }
//
//    @ApiOperation("修改角色")
//    @PutMapping("/role/{id}")
//    public CommonResult updateRole(@ApiIgnore AdminInfo adminInfo,
//                                   @ApiParam("角色id") @PathVariable Long id,
//                                   @ApiParam("修改角色对象") @Valid @RequestBody RoleUpdateVO vo) {
//        return permissionManageService.updateRole(adminInfo, id, vo);
//    }
//
//    @ApiOperation("删除角色")
//    @DeleteMapping("/role/{id}")
//    public CommonResult deleteRole(@ApiIgnore AdminInfo adminInfo,
//                                   @ApiParam("角色id") @PathVariable Long id) {
//        return permissionManageService.deleteRole(adminInfo, id);
//    }
//
//    @ApiOperation("查询当前用户权限列表")
//    @GetMapping
//    public CommonResult<List<PermissionInfoResult>> indexPermission(@ApiIgnore AdminInfo adminInfo) {
//        return permissionManageService.indexPermission(adminInfo);
//    }
//
//}
