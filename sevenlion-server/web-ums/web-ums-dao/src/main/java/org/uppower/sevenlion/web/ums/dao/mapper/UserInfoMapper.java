package org.uppower.sevenlion.web.ums.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.uppower.sevenlion.web.ums.common.model.entity.UserInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表详情表 Mapper 接口
 * </p>
 *
 * @author qmw
 * @since 2021-05-24
 */
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {

    /**
     * 增加分数
     * @param userId
     * @param incrValue
     * @return
     */
    int incrScore(@Param("userId") Long userId, @Param("incrValue") Integer incrValue);
}
