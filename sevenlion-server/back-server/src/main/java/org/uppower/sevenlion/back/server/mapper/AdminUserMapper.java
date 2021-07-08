package org.uppower.sevenlion.back.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.uppower.sevenlion.back.server.model.entity.AdminUserEntity;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author qmw
 * @since 2021-05-25
 */
public interface AdminUserMapper extends BaseMapper<AdminUserEntity> {

    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
