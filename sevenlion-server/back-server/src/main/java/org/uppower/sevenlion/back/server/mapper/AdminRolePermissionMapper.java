package org.uppower.sevenlion.back.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.uppower.sevenlion.back.server.model.entity.AdminRolePermissionEntity;

import java.util.List;

/**
 * <p>
 * 用户角色权限表 Mapper 接口
 * </p>
 *
 * @author qmw
 * @since 2021-05-25
 */
public interface AdminRolePermissionMapper extends BaseMapper<AdminRolePermissionEntity> {

    /**
     * 批量插入角色权限
     * @date 2021/5/26 2:42 下午
     * @param roleId
     * @param permissionIdList
     * @return int
     * @auther sevenlion
     */
    int insertList(@Param("roleId") Long roleId, @Param("permissionIdList") List<Long> permissionIdList);

}
