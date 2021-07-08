package org.uppower.sevenlion.web.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.uppower.sevenlion.web.order.common.model.entity.AlipayLogEntity;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/4 6:30 下午
 */
public interface AliPayLogMapper extends BaseMapper<AlipayLogEntity> {
    /**
     * 修改支付状态
     *
     * @param businessOrderNo
     * @param status
     * @return
     */
    int updateStatus(@Param("businessOrderNo") String businessOrderNo, @Param("status") Integer status);
}
