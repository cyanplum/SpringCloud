package org.uppower.sevenlion.back.user.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.uppower.sevenlion.back.user.dao.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author qmw
 * @since 2021-06-01
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
