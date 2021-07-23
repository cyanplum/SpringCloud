package org.uppower.sevenlion.web.product.server.service;

import cn.sevenlion.utils.response.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.web.product.common.model.bo.ProductOrderBo;
import org.uppower.sevenlion.web.product.common.model.entity.ProductEntity;
import org.uppower.sevenlion.web.product.common.model.result.CartResult;
import org.uppower.sevenlion.web.product.common.model.result.ProductResult;
import org.uppower.sevenlion.web.product.common.model.bo.CartBo;
import org.uppower.sevenlion.web.product.dao.ProductMapper;


import java.util.ArrayList;
import java.util.Collection;
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
 * @date 2021/4/22 11:26 上午
 */
@Service
public class ProductManageService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 得到商品信息集合
     * @param ids
     * @param name
     * @return
     */
    public CommonResult<List<ProductResult>> getProductList(List<Long> ids, String name) {

        List<ProductEntity> productEntities = productMapper.selectList(new QueryWrapper<ProductEntity>()
                .in(ids!=null && !ids.isEmpty(), "id", ids)
                .like(name != null, "title", name));

        List<ProductResult> results = productEntities.stream().map(it -> {
            ProductResult result = new ProductResult();
            BeanUtils.copyProperties(it, result);
            return result;
        }).collect(Collectors.toList());

        return CommonResult.success(results);
    }

    /**
     * 扣减库存
     * @param productOrderList
     * @return
     */
    public CommonResult cutProductStock(List<ProductOrderBo> productOrderList) {
        if (productMapper.cutProductStock(productOrderList) != productOrderList.size()) {
            return CommonResult.failed("");
        }
        return CommonResult.success();
    }

    public CommonResult<List<CartResult>> cartIndex(Long id) {
        Collection<CartResult> values = redisTemplate.opsForHash().entries(id.toString()).values();
        List<CartResult> list = new ArrayList<>(values.size());
        values.forEach(it->list.add(it));
        return CommonResult.success(list);
    }

    public CommonResult storeCart(CartBo bo) {
        ProductEntity productEntity = productMapper.selectById(bo.getProductId());
        if (productEntity == null) {
            throw new RuntimeException("商品不存在！");
        }
        CartResult productRedis = new CartResult();
        BeanUtils.copyProperties(productEntity,productRedis);
        productRedis.setId(bo.getProductId());
        productRedis.setNumber(bo.getNumber());
        try {
            redisTemplate.opsForHash().put(bo.getId().toString(),productEntity.getId().toString(),productRedis);
        } catch (Exception e) {

        }
        return CommonResult.success();
    }
}
