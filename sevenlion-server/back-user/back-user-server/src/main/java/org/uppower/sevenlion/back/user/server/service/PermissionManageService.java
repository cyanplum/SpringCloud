package org.uppower.sevenlion.back.user.server.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uppower.sevenlion.back.user.dao.entity.AdminPermissionEntity;
import org.uppower.sevenlion.back.user.dao.entity.AdminRoleEntity;
import org.uppower.sevenlion.back.user.dao.entity.AdminRolePermissionEntity;
import org.uppower.sevenlion.back.user.dao.entity.AdminUserEntity;
import org.uppower.sevenlion.back.user.dao.mapper.AdminPermissionMapper;
import org.uppower.sevenlion.back.user.dao.mapper.AdminRoleMapper;
import org.uppower.sevenlion.back.user.dao.mapper.AdminRolePermissionMapper;
import org.uppower.sevenlion.back.user.dao.mapper.AdminUserMapper;

import org.uppower.sevenlion.back.user.server.manager.PermissionManager;
import org.uppower.sevenlion.back.user.server.model.bo.RoleSaveBo;
import org.uppower.sevenlion.back.user.server.model.bo.RoleUpdateBo;
import org.uppower.sevenlion.back.user.server.model.query.PermissionQueryModel;
import org.uppower.sevenlion.back.user.server.model.vo.PermissionInfoVo;
import org.uppower.sevenlion.back.user.server.model.vo.RoleInfoVo;
import org.uppower.sevenlion.back.user.server.model.vo.RoleListVo;
import org.uppower.sevenlion.common.exceptions.BackException;
import org.uppower.sevenlion.common.model.admin.AdminInfo;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.common.utils.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/26 11:22 上午
 */
@Service
public class PermissionManageService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private PermissionManager permissionManager;

    /**
     * 查询角色列表
     * @date 2021/6/25 4:47 下午
     * @param adminInfo
     * @param queryModel
     * @return PageInfo<RoleListVo>
     * @auther sevenlion
     */
    public PageInfo<RoleListVo> indexRole(AdminInfo adminInfo, PermissionQueryModel queryModel) {
        IPage<AdminRoleEntity> adminRoleEntityIPage = permissionManager.selectPage(adminInfo.getUserId(), queryModel.getPn(), queryModel.getPageSize(),queryModel.getName());
        List<RoleListVo> resultList = adminRoleEntityIPage.getRecords().stream().map(it -> {
            RoleListVo result = new RoleListVo();
            BeanUtils.copyProperties(result, it);
            return result;
        }).collect(Collectors.toList());
        return new PageInfo<>(resultList, adminRoleEntityIPage.getCurrent(), adminRoleEntityIPage.getTotal());
    }

    /**
     * 查看角色详情
     * @date 2021/5/26 2:03 下午
     * @param adminInfo
     * @param id
     * @return CommonResult<RoleInfoVo>
     * @auther sevenlion
     */
    public RoleInfoVo showRole(AdminInfo adminInfo, Long id) {
        AdminRoleEntity adminRoleEntity = adminRoleMapper.selectById(id);
        if (adminRoleEntity == null) {
            throw new BackException("角色不存在！");
        }
        if (!adminRoleEntity.getBelongId().equals(adminInfo.getUserId())) {
            throw new BackException("该角色不属于当前用户，无权限查看！");
        }
        List<AdminRolePermissionEntity> rolePermissionEntityList = adminRolePermissionMapper.selectList(new QueryWrapper<AdminRolePermissionEntity>().lambda()
                .eq(AdminRolePermissionEntity::getRoleId, adminRoleEntity.getId()));
        List<Long> permissionIdList = rolePermissionEntityList.stream().map(AdminRolePermissionEntity::getPermissionId).collect(Collectors.toList());
        RoleInfoVo result = new RoleInfoVo();
        BeanUtils.copyProperties(result,adminRoleEntity);
        if (!permissionIdList.isEmpty()) {
            List<AdminPermissionEntity> permissionEntityList = adminPermissionMapper.selectList(new QueryWrapper<AdminPermissionEntity>().lambda()
                    .in(AdminPermissionEntity::getId, permissionIdList));
            List<PermissionInfoVo> permissionInfoResultList = new ArrayList<PermissionInfoVo>();
            permissionEntityList.forEach(it->{
                PermissionInfoVo permissionInfoResult = new PermissionInfoVo();
                BeanUtils.copyProperties(it,permissionInfoResult);
                permissionInfoResultList.add(permissionInfoResult);
            });
            result.setPermissionInfoResultList(permissionInfoResultList);
        }
        return result;
    }

    /**
     * 新增角色
     * @date 2021/5/26 2:35 下午
     * @param adminInfo
     * @param bo
     * @return CommonResult
     * @auther sevenlion
     */
    @Transactional(rollbackFor = Exception.class)
    public int saveRole(AdminInfo adminInfo, RoleSaveBo bo) {
        AdminRoleEntity adminRoleEntity = new AdminRoleEntity();
        BeanUtils.copyProperties(bo, adminRoleEntity);
        adminRoleEntity.setBelongId(adminInfo.getUserId());
        if (adminRoleMapper.insert(adminRoleEntity) != 1) {
            throw new BackException("新增角色失败！");
        }
        if (!bo.getPermissionIdList().isEmpty()
                && adminRolePermissionMapper.insertList(adminRoleEntity.getId(),bo.getPermissionIdList()) != bo.getPermissionIdList().size()) {
             throw new BackException("新增角色权限失败！");
        }
        return 1;
    }

    /**
     * 修改角色
     * @date 2021/5/26 2:49 下午
     * @param adminInfo
     * @param id
     * @param bo
     * @return CommonResult
     * @auther sevenlion
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(AdminInfo adminInfo, Long id, RoleUpdateBo bo) {
        AdminRoleEntity adminRoleEntity = adminRoleMapper.selectById(id);
        if (adminRoleEntity == null) {
            throw new BackException("角色不存在！");
        }
        if (!adminRoleEntity.getBelongId().equals(adminInfo.getUserId())) {
            throw new BackException("该角色不属于该用户，不能修改！");
        }
        List<AdminRolePermissionEntity> rolePermissionEntityList = adminRolePermissionMapper.selectList(new QueryWrapper<AdminRolePermissionEntity>().lambda()
                .eq(AdminRolePermissionEntity::getRoleId, id));
        List<Long> permissionEntityIdList = rolePermissionEntityList.stream().map(AdminRolePermissionEntity::getPermissionId).collect(Collectors.toList());
        List<Long> permissionIdList = bo.getPermissionIdList();

        List<Long> deletePermissionIdList = new ArrayList<>();
        List<Long> insertPermissionIdList = new ArrayList<>();
        if (permissionIdList.isEmpty()) {
            deletePermissionIdList = permissionEntityIdList;
        }else {
            for (Long key : permissionEntityIdList) {
                if (permissionIdList.contains(key)) {
                    deletePermissionIdList.add(key);
                }
            }
            permissionEntityIdList.removeAll(permissionIdList);
            insertPermissionIdList = permissionEntityIdList;
        }

        if (!deletePermissionIdList.isEmpty()
                && adminRolePermissionMapper.delete(new QueryWrapper<AdminRolePermissionEntity>().lambda()
                        .eq(AdminRolePermissionEntity::getRoleId,id)
                        .in(AdminRolePermissionEntity::getPermissionId,deletePermissionIdList))
                        != deletePermissionIdList.size()) {
            throw new BackException("更新角色权限失败！");
        }
        if (!insertPermissionIdList.isEmpty()
                && adminRolePermissionMapper.insertList(id,insertPermissionIdList) != insertPermissionIdList.size()) {
            throw new BackException("更新角色权限失败！");
        }
        return 1;
    }

    /**
     * 删除角色
     * @date 2021/5/26 3:20 下午
     * @param adminInfo
     * @param id
     * @return int
     * @auther sevenlion
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteRole(AdminInfo adminInfo, Long id) {
        AdminRoleEntity adminRoleEntity = adminRoleMapper.selectById(id);
        if (adminRoleEntity == null) {
            throw new BackException("角色不存在！");
        }
        if (!adminRoleEntity.getBelongId().equals(adminInfo.getUserId())) {
            throw new BackException("该角色不属于此用户，不能删除！");
        }
        if (adminRoleMapper.deleteById(id) != 1) {
            throw new BackException("删除角色失败！");
        }
        adminRolePermissionMapper.delete(new QueryWrapper<AdminRolePermissionEntity>().lambda().eq(AdminRolePermissionEntity::getRoleId,id));
        return 1;
    }

    /**
     * 查询当前用户拥有角色
     * @date 2021/5/26 3:31 下午
     * @param adminInfo
     * @return List<PermissionInfoVo>
     * @auther sevenlion
     */
    public List<PermissionInfoVo> indexPermission(AdminInfo adminInfo) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(adminInfo.getUserId());
        List<AdminRolePermissionEntity> rolePermissionEntityList = adminRolePermissionMapper.selectList(new QueryWrapper<AdminRolePermissionEntity>().lambda()
                .eq(AdminRolePermissionEntity::getRoleId, adminUserEntity.getRoleId()));
        List<AdminPermissionEntity> permissionEntityList = adminPermissionMapper.selectList(new QueryWrapper<AdminPermissionEntity>().lambda()
                .in(AdminPermissionEntity::getId, rolePermissionEntityList
                        .stream()
                        .map(AdminRolePermissionEntity::getPermissionId)
                        .collect(Collectors.toList())));

        List<PermissionInfoVo> results = permissionEntityList.stream().map(it -> {
            PermissionInfoVo permissionInfoResult = new PermissionInfoVo();
            BeanUtils.copyProperties(it, permissionInfoResult);
            return permissionInfoResult;
        }).collect(Collectors.toList());
        return results;
    }
}
