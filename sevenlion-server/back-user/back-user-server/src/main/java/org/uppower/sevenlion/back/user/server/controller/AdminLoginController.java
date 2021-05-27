package org.uppower.sevenlion.back.user.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.uppower.sevenlion.back.user.dao.entity.AdminPermissionEntity;
import org.uppower.sevenlion.back.user.dao.entity.AdminRoleEntity;
import org.uppower.sevenlion.back.user.dao.entity.AdminRolePermissionEntity;
import org.uppower.sevenlion.back.user.dao.entity.AdminUserEntity;
import org.uppower.sevenlion.back.user.dao.mapper.AdminPermissionMapper;
import org.uppower.sevenlion.back.user.dao.mapper.AdminRoleMapper;
import org.uppower.sevenlion.back.user.dao.mapper.AdminRolePermissionMapper;
import org.uppower.sevenlion.back.user.dao.mapper.AdminUserMapper;
import org.uppower.sevenlion.back.user.server.model.vo.AdminLoginVO;
import org.uppower.sevenlion.common.enums.BaseStatusEnum;
import org.uppower.sevenlion.common.exceptions.BackException;
import org.uppower.sevenlion.security.annotation.LoginMapping;
import org.uppower.sevenlion.security.model.UserDetails;
import org.uppower.sevenlion.security.service.AuthenticationService;
import org.uppower.sevenlion.common.model.admin.AdminInfo;

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
@LoginMapping("/auth/login")
public class AdminLoginController implements AuthenticationService<AdminLoginVO> {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    @Override
    public UserDetails loadUser(AdminLoginVO body, PasswordEncoder passwordEncoder) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectOne(new QueryWrapper<AdminUserEntity>().eq("phone", body.getPhone()));
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
                .eq("role_id", adminRoleEntity.getId()));
        if (adminRolePermissionEntityList.isEmpty()) {
            throw new BackException("角色无权限！");
        }
        List<AdminPermissionEntity> adminPermissionEntityList = adminPermissionMapper.selectList(new QueryWrapper<AdminPermissionEntity>()
                .in("id", adminRolePermissionEntityList.stream().map(it -> it.getPermissionId()).collect(Collectors.toList())));

        List<String> userRoles = new ArrayList<>();
        List<String> permissions = adminPermissionEntityList.stream().map(it -> it.getLabel()).collect(Collectors.toList());

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
