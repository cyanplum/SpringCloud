package org.uppower.sevenlion.web.psm.server.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.utils.response.CommonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.constant.RedisConst;
import org.uppower.sevenlion.common.enums.ProductsTypeEnum;
import org.uppower.sevenlion.common.exceptions.BaseException;
import org.uppower.sevenlion.common.model.user.UserInfo;
import org.uppower.sevenlion.web.cms.common.client.ProductCategoryClient;
import org.uppower.sevenlion.web.pms.common.model.bo.CartBo;
import org.uppower.sevenlion.web.pms.common.model.bo.LabelBo;
import org.uppower.sevenlion.web.pms.common.model.entity.*;
import org.uppower.sevenlion.web.pms.common.model.query.ProductsQueryModel;
import org.uppower.sevenlion.web.pms.common.model.vo.*;
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

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;


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
            List<ProductContentLabelEntity> productContentLabelEntities = contentManager.selectListByProductIds(productContentEntities.stream().map(ProductContentEntity::getId).collect(Collectors.toList()));
            ArrayListMultimap<Long, Long> proConLabList = ArrayListMultimap.create();
            productContentLabelEntities.stream().forEach(it -> {
                proConLabList.put(it.getProductContentId(), it.getLabelContentId());
            });
            List<ProductContentVo> list = productContentEntities.stream().map(it -> {
                ProductContentVo vo = new ProductContentVo();
                BeanUtils.copyProperties(it, vo);
                vo.setLabelContentList(proConLabList.get(it.getId()));
                return vo;
            }).collect(Collectors.toList());
            result.setProductContentList(list);
        }

        return result;
    }

    public void addCart(UserInfo userInfo, CartBo cartBo) {
        if (cartBo.getNumber() <= 0) {
            throw new BaseException("操作购物车错误！");
        }
        ProductsEntity productsEntity = productsManager.selectById(cartBo.getProductId());
        if (ObjectUtil.isNull(productsEntity)) {
            throw new BaseException("产品不存在！");
        }
        ProductContentEntity productContentEntity = productsManager.selectByContentId(cartBo.getProductContentId());
        if (ObjectUtil.isNull(productContentEntity)) {
            throw new BaseException("产品不存在！");
        }
        List<LabelContentEntity> labelContentEntities = contentManager.selectByLabelIds(cartBo.getLabelList().stream().map(LabelBo::getLabelContentId).collect(Collectors.toList()));
        CartVo cartVo = new CartVo();
        if (CollUtil.isNotEmpty(labelContentEntities)) {
            List<LabelContentVo> labelContentList = labelContentEntities.stream().map(it -> {
                LabelContentVo vo = new LabelContentVo();
                BeanUtils.copyProperties(it, vo);
                return vo;
            }).collect(Collectors.toList());
            cartVo.setLabelContentList(labelContentList);
        }
        cartVo.setNumber(cartBo.getNumber());
        cartVo.setPicture(productContentEntity.getPicture());
        cartVo.setPrice(productContentEntity.getPrice());
        cartVo.setTitle(productContentEntity.getName());
        try {
            String mapKey = buildKey(RedisConst.ADD_CART_MAP, userInfo.getUserId());
            String key = buildKey(RedisConst.ADD_CART_KEY, productContentEntity.getId() ,labelContentEntities.stream().map(LabelContentEntity::getId).sorted().collect(Collectors.toList()));
            redisTemplate.opsForHash().put(mapKey, key, cartVo);
        }catch (Exception e) {
            log.error("加入购物车错误，存放redis失败！:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeCart(UserInfo userInfo, List<CartBo> cartBoList) {
        if (CollUtil.isEmpty(cartBoList)) {
            throw new BaseException("购物车操作错误！");
        }
        cartBoList.stream().forEach(it -> {
            try {
                String mapKey = buildKey(RedisConst.ADD_CART_MAP, userInfo.getUserId());
                String key = buildKey(RedisConst.ADD_CART_KEY, it.getProductContentId() ,it.getLabelList().stream().map(LabelBo::getLabelContentId).sorted().collect(Collectors.toList()));
                redisTemplate.opsForHash().delete(mapKey, key);
            }catch (Exception e) {
                log.error("移除购物车错误，移除redis失败！:{}", e.getMessage());
                e.printStackTrace();
            }
        });

    }

    @SneakyThrows
    public String buildKey(String prefix, Object... key) {
        for (int i = 0; i < key.length; i++) {
            prefix = String.format(prefix, objectMapper.writeValueAsString(key));
        }
        return prefix;
    }


}
