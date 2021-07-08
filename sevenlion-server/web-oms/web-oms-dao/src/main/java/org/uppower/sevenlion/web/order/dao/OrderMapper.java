package org.uppower.sevenlion.web.order.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.uppower.sevenlion.web.order.common.model.entity.OrderEntity;


import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author qmw
 * @since 2021-01-23
 */
public interface OrderMapper extends BaseMapper<OrderEntity> {



    /**
     * 批量插入订单
     *
     * @param insertList
     * @return
     */
    int insertList(@Param("insertList") List<OrderEntity> insertList);
}
