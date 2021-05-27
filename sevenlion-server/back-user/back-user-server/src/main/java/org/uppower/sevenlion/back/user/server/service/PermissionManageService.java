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
import org.uppower.sevenlion.back.user.server.model.result.PermissionInfoResult;
import org.uppower.sevenlion.back.user.server.model.result.RoleInfoResult;
import org.uppower.sevenlion.back.user.server.model.result.RoleListResult;
import org.uppower.sevenlion.back.user.server.model.vo.RoleSaveVO;
import org.uppower.sevenlion.back.user.server.model.vo.RoleUpdateVO;
import org.uppower.sevenlion.common.exceptions.BackException;
import org.uppower.sevenlion.common.model.admin.AdminInfo;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.common.utils.CommonResultPage;

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

    /**
     * 查询角色列表
     * @date 2021/5/26 1:45 下午
     * @param adminInfo
     * @param pn
     * @param name
     * @param pageSize
     * @return CommonResultPage<List<RoleListResult>>
     * @auther sevenlion
     */
    public CommonResultPage<RoleListResult> indexRole(AdminInfo adminInfo, Integer pn, Integer pageSize, String name) {
        IPage<AdminRoleEntity> adminRoleEntityIPage = adminRoleMapper.selectPage(new Page<AdminRoleEntity>(pn, pageSize),
                new QueryWrapper<AdminRoleEntity>()
                        .like(StringUtils.isNotBlank(name), "name", name)
                        .eq("belong_id", adminInfo.getUserId()));
        List<RoleListResult> resultList = adminRoleEntityIPage.getRecords().stream().map(it -> {
            RoleListResult result = new RoleListResult();
            BeanUtils.copyProperties(result, it);
            return result;
        }).collect(Collectors.toList());
        return CommonResultPage.success(resultList, adminRoleEntityIPage.getCurrent(), adminRoleEntityIPage.getTotal());
    }

    /**
     * 查看角色详情
     * @date 2021/5/26 2:03 下午
     * @param adminInfo
     * @param id
     * @return CommonResult<RoleInfoResult>
     * @auther sevenlion
     */
    public CommonResult<RoleInfoResult> showRole(AdminInfo adminInfo, Long id) {
        AdminRoleEntity adminRoleEntity = adminRoleMapper.selectById(id);
        if (adminRoleEntity == null) {
            throw new BackException("角色不存在！");
        }
        if (!adminRoleEntity.getBelongId().equals(adminInfo.getUserId())) {
            throw new BackException("该角色不属于当前用户，无权限查看！");
        }
        List<AdminRolePermissionEntity> rolePermissionEntityList = adminRolePermissionMapper.selectList(new QueryWrapper<AdminRolePermissionEntity>()
                .eq("role_id", adminRoleEntity.getId()));
        List<Long> permissionIdList = rolePermissionEntityList.stream().map(AdminRolePermissionEntity::getPermissionId).collect(Collectors.toList());
        RoleInfoResult result = new RoleInfoResult();
        BeanUtils.copyProperties(result,adminRoleEntity);
        if (!permissionIdList.isEmpty()) {
            List<AdminPermissionEntity> permissionEntityList = adminPermissionMapper.selectList(new QueryWrapper<AdminPermissionEntity>().in("id", permissionIdList));
            List<PermissionInfoResult> permissionInfoResultList = new ArrayList<PermissionInfoResult>();
            permissionEntityList.forEach(it->{
                PermissionInfoResult permissionInfoResult = new PermissionInfoResult();
                BeanUtils.copyProperties(it,permissionInfoResult);
                permissionInfoResultList.add(permissionInfoResult);
            });
            result.setPermissionInfoResultList(permissionInfoResultList);
        }
        return CommonResult.success(result);
    }

    /**
     * 新增角色
     * @date 2021/5/26 2:35 下午
     * @param adminInfo
     * @param vo
     * @return CommonResult
     * @auther sevenlion
     */
    @Transactional(rollbackFor = Exception.class)
    public CommonResult saveRole(AdminInfo adminInfo, RoleSaveVO vo) {
        AdminRoleEntity adminRoleEntity = new AdminRoleEntity();
        BeanUtils.copyProperties(vo, adminRoleEntity);
        adminRoleEntity.setBelongId(adminInfo.getUserId());
        if (adminRoleMapper.insert(adminRoleEntity) != 1) {
            return CommonResult.failed("新增角色失败！");
        }
        if (!vo.getPermissionIdList().isEmpty()
                && adminRolePermissionMapper.insertList(adminRoleEntity.getId(),vo.getPermissionIdList()) != vo.getPermissionIdList().size()) {
             throw new BackException("新增角色权限失败！");
        }
        return CommonResult.success();
    }

    /**
     * 修改角色
     * @date 2021/5/26 2:49 下午
     * @param adminInfo
     * @param id
     * @param vo
     * @return CommonResult
     * @auther sevenlion
     */
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateRole(AdminInfo adminInfo, Long id, RoleUpdateVO vo) {
        AdminRoleEntity adminRoleEntity = adminRoleMapper.selectById(id);
        if (adminRoleEntity == null) {
            throw new BackException("角色不存在！");
        }
        if (!adminRoleEntity.getBelongId().equals(adminInfo.getUserId())) {
            throw new BackException("该角色不属于该用户，不能修改！");
        }
        List<AdminRolePermissionEntity> rolePermissionEntityList = adminRolePermissionMapper.selectList(new QueryWrapper<AdminRolePermissionEntity>()
                .eq("role_id", id));
        List<Long> permissionEntityIdList = rolePermissionEntityList.stream().map(it -> it.getPermissionId()).collect(Collectors.toList());
        List<Long> permissionIdList = vo.getPermissionIdList();

        List<Long> deletePermissionIdList = new ArrayList<>();
        List<Long> insertPermissionIdList = new ArrayList<>();
        if (permissionIdList.isEmpty() || permissionIdList.size() == 0) {
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
                && adminRolePermissionMapper.delete(new QueryWrapper<AdminRolePermissionEntity>()
                        .eq("role_id",id)
                        .in("permission_id",deletePermissionIdList))
                        != deletePermissionIdList.size()) {
            throw new BackException("更新角色权限失败！");
        }
        if (!insertPermissionIdList.isEmpty()
                && adminRolePermissionMapper.insertList(id,insertPermissionIdList) != insertPermissionIdList.size()) {
            throw new BackException("更新角色权限失败！");
        }
        return CommonResult.success();
    }

    /**
     * 删除角色
     * @date 2021/5/26 3:20 下午
     * @param adminInfo
     * @param id
     * @return CommonResult
     * @auther sevenlion
     */
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteRole(AdminInfo adminInfo, Long id) {
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
        adminRolePermissionMapper.delete(new QueryWrapper<AdminRolePermissionEntity>().eq("role_id",id));
        return CommonResult.success();
    }

    /**
     * 查询当前用户拥有角色
     * @date 2021/5/26 3:31 下午
     * @param adminInfo
     * @return CommonResult<List<PermissionInfoResult>>
     * @auther sevenlion
     */
    public CommonResult<List<PermissionInfoResult>> indexPermission(AdminInfo adminInfo) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(adminInfo.getUserId());
        List<AdminRolePermissionEntity> rolePermissionEntityList = adminRolePermissionMapper.selectList(new QueryWrapper<AdminRolePermissionEntity>().eq("role_id", adminUserEntity.getRoleId()));
        List<AdminPermissionEntity> permissionEntityList = adminPermissionMapper.selectList(new QueryWrapper<AdminPermissionEntity>()
                .in("id", rolePermissionEntityList
                        .stream()
                        .map(AdminRolePermissionEntity::getPermissionId)
                        .collect(Collectors.toList())));

        List<PermissionInfoResult> results = permissionEntityList.stream().map(it -> {
            PermissionInfoResult permissionInfoResult = new PermissionInfoResult();
            BeanUtils.copyProperties(it, permissionInfoResult);
            return permissionInfoResult;
        }).collect(Collectors.toList());
        return CommonResult.success(results);
    }
}
