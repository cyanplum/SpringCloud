package org.uppower.sevenlion.web.psm.server.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.utils.response.CommonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.enums.ProductsTypeEnum;
import org.uppower.sevenlion.common.exceptions.BaseException;
import org.uppower.sevenlion.web.cms.common.client.ProductCategoryClient;
import org.uppower.sevenlion.web.pms.common.model.entity.ProductContentEntity;
import org.uppower.sevenlion.web.pms.common.model.entity.ProductsEntity;
import org.uppower.sevenlion.web.pms.common.model.query.ProductsQueryModel;
import org.uppower.sevenlion.web.pms.common.model.vo.LabelVo;
import org.uppower.sevenlion.web.pms.common.model.vo.ProductContentVo;
import org.uppower.sevenlion.web.pms.common.model.vo.ProductInfoVo;
import org.uppower.sevenlion.web.pms.common.model.vo.ProductsVo;
import org.uppower.sevenlion.web.pms.dao.mapper.LabelMapper;
import org.uppower.sevenlion.web.psm.server.manager.LabelContentManager;
import org.uppower.sevenlion.web.psm.server.manager.LabelManager;
import org.uppower.sevenlion.web.psm.server.manager.ProductsManager;


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
@Slf4j
public class ProductService {

    @Autowired
    private ProductsManager productsManager;

    @Autowired
    private LabelContentManager contentManager;

    @Autowired
    private LabelManager labelManager;

    @Autowired
    private ProductCategoryClient productCategoryClient;


    public List<ProductsVo> indexProduct(ProductsQueryModel queryModel) {
        List<Long> productIds = null;
        if (CollUtil.isNotEmpty(queryModel.getCategoryId())) {
            CommonResult<List<Long>> commonResult = productCategoryClient.queryProductsById(queryModel.getCategoryId());
            if (commonResult.isSuccess()) {
                productIds = commonResult.getData();
            }
        }
        Page<ProductsEntity> page = productsManager.selectPage(queryModel.getPn(), queryModel.getPageSize(), queryModel.getType(), queryModel.getTitle(), productIds, queryModel.getBelongId());
        if (CollUtil.isEmpty(page.getRecords())) {
            return Lists.newArrayList();
        }
        List<ProductsVo> result = page.getRecords().stream().map(it -> {
            ProductsVo vo = new ProductsVo();
            BeanUtils.copyProperties(it, vo);
            vo.setTypeName(ProductsTypeEnum.getNameByType(vo.getType()));
            return vo;
        }).collect(Collectors.toList());
        return result;
    }

    public ProductInfoVo showProduct(Long id) {
        ProductsEntity productsEntity = productsManager.selectById(id);
        if (ObjectUtil.isNull(productsEntity)) {
            throw new BaseException("产品不存在！");
        }
        ProductInfoVo result = new ProductInfoVo();
        BeanUtils.copyProperties(productsEntity, result);
        //查询标签
        List<LabelVo> labelVos = labelManager.queryByProductId(productsEntity.getId());
        result.setLabels(labelVos);
        List<ProductContentEntity> productContentEntities = productsManager.selectContentByProductId(productsEntity.getId());
        if (CollUtil.isNotEmpty(productContentEntities)) {
            List<ProductContentVo> list = productContentEntities.stream().map(it -> {
                ProductContentVo vo = new ProductContentVo();
                BeanUtils.copyProperties(it, vo);
                return vo;
            }).collect(Collectors.toList());
            result.setProductContentList(list);
        }

        return result;
    }

//    /**
//     * 得到商品信息集合
//     * @param ids
//     * @param name
//     * @return
//     */
//    public CommonResult<List<ProductDetailVo>> getProductList(List<Long> ids, String name) {
//
//        List<Product> productEntities = productMapper.selectList(new QueryWrapper<ProductEntity>()
//                .in(ids!=null && !ids.isEmpty(), "id", ids)
//                .like(name != null, "title", name));
//
//        List<ProductDetailVo> results = productEntities.stream().map(it -> {
//            ProductDetailVo result = new ProductDetailVo();
//            BeanUtils.copyProperties(it, result);
//            return result;
//        }).collect(Collectors.toList());
//
//        return CommonResult.success(results);
//    }
//
//    /**
//     * 扣减库存
//     * @param productOrderList
//     * @return
//     */
//    public CommonResult cutProductStock(List<ProductOrderBo> productOrderList) {
//        if (productMapper.cutProductStock(productOrderList) != productOrderList.size()) {
//            return CommonResult.failed("");
//        }
//        return CommonResult.success();
//    }
//
//    public CommonResult<List<CartResult>> cartIndex(Long id) {
//        Collection<CartResult> values = redisTemplate.opsForHash().entries(id.toString()).values();
//        List<CartResult> list = new ArrayList<>(values.size());
//        values.forEach(it->list.add(it));
//        return CommonResult.success(list);
//    }
//
//    public CommonResult storeCart(CartBo bo) {
//        ProductEntity productEntity = productMapper.selectById(bo.getProductId());
//        if (productEntity == null) {
//            throw new RuntimeException("商品不存在！");
//        }
//        CartResult productRedis = new CartResult();
//        BeanUtils.copyProperties(productEntity,productRedis);
//        productRedis.setId(bo.getProductId());
//        productRedis.setNumber(bo.getNumber());
//        try {
//            redisTemplate.opsForHash().put(bo.getId().toString(),productEntity.getId().toString(),productRedis);
//        } catch (Exception e) {
//
//        }
//        return CommonResult.success();
//    }
}
