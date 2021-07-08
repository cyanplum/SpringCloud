package org.uppower.sevenlion.back.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.uppower.sevenlion.back.server.model.entity.AdminPermissionEntity;
import org.uppower.sevenlion.back.server.model.entity.AdminRoleEntity;
import org.uppower.sevenlion.back.server.model.entity.AdminRolePermissionEntity;
import org.uppower.sevenlion.back.server.model.entity.AdminUserEntity;
import org.uppower.sevenlion.back.server.mapper.AdminPermissionMapper;
import org.uppower.sevenlion.back.server.mapper.AdminRoleMapper;
import org.uppower.sevenlion.back.server.mapper.AdminRolePermissionMapper;
import org.uppower.sevenlion.back.server.mapper.AdminUserMapper;
import org.uppower.sevenlion.back.server.model.bo.AdminLoginBo;
import org.uppower.sevenlion.common.enums.BaseStatusEnum;
import org.uppower.sevenlion.common.exceptions.BackException;
import org.uppower.sevenlion.common.model.admin.AdminInfo;
import org.uppower.sevenlion.security.annotation.LoginMapping;
import org.uppower.sevenlion.security.model.UserDetails;
import org.uppower.sevenlion.security.service.AuthenticationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/25 6:03 下午
 */
@Api(tags = "管理用户登录")
@LoginMapping("/auth/login")
public class AdminLoginController implements AuthenticationService<AdminLoginBo> {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    @ApiOperation("管理用户登录")
    @Override
    public UserDetails loadUser(@ApiParam("登录参数") AdminLoginBo body, PasswordEncoder passwordEncoder) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectOne(new QueryWrapper<AdminUserEntity>().lambda().eq(AdminUserEntity::getPhone, body.getPhone()));
        if (adminUserEntity == null) {
            throw new BackException("用户不存在！");
        }
        if (!passwordEncoder.matches(body.getPassword(),adminUserEntity.getPassword())) {
            throw new BackException("用户密码不正确！");
        }
        if (adminUserEntity.getStatus().equals(BaseStatusEnum.OFFLINE.getStatus())) {
            throw new BackException("用户被禁用！");
        }
        AdminRoleEntity adminRoleEntity = adminRoleMapper.selectById(adminUserEntity.getRoleId());
        if (adminRoleEntity == null) {
            throw new BackException("角色不存在！");
        }
        //查询角色拥有的权限id
        List<AdminRolePermissionEntity> adminRolePermissionEntityList = adminRolePermissionMapper.selectList(new QueryWrapper<AdminRolePermissionEntity>()
                .lambda().eq(AdminRolePermissionEntity::getRoleId, adminRoleEntity.getId()));
        if (adminRolePermissionEntityList.isEmpty()) {
            throw new BackException("角色无权限！");
        }
        List<AdminPermissionEntity> adminPermissionEntityList = adminPermissionMapper.selectList(new QueryWrapper<AdminPermissionEntity>()
                .lambda().in(AdminPermissionEntity::getId, adminRolePermissionEntityList.stream().map(AdminRolePermissionEntity::getPermissionId).collect(Collectors.toList())));

        List<String> userRoles = new ArrayList<>();
        List<String> permissions = adminPermissionEntityList.stream().map(AdminPermissionEntity::getLabel).collect(Collectors.toList());

        userRoles.add(adminRoleEntity.getName());

        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setRoles(userRoles);
        adminInfo.setPermissions(permissions);
        adminInfo.setUsername(adminUserEntity.getUsername());
        adminInfo.setUserId(adminUserEntity.getId());
        adminInfo.setProvinceId(adminUserEntity.getProvinceId());
        adminInfo.setCityId(adminUserEntity.getCityId());
        adminInfo.setDistrictId(adminUserEntity.getDistrictId());

        UserDetails<AdminInfo> userDetails = new UserDetails<AdminInfo>();
        userDetails.setUserDetail(adminInfo);
        userDetails.setEnable(true);
        userDetails.setNonLocked(true);
        userDetails.setId(adminInfo.getUserId());
        userDetails.setPermission(permissions);
        userDetails.setRoles(userRoles);
        return userDetails;
    }
}
