package org.uppower.sevenlion.web.product.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.uppower.sevenlion.web.product.common.model.entity.ProductEntity;
import org.uppower.sevenlion.web.product.common.model.vo.ProductOrderVo;


import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author qmw
 * @since 2021-01-23
 */
public interface ProductMapper extends BaseMapper<ProductEntity> {

    int cutProductStock(@Param("productOrderList") List<ProductOrderVo> productOrderList);

}
