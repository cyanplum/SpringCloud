package org.uppower.sevenlion.web.psm.server.manager;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.enums.BaseAuditStatusEnum;
import org.uppower.sevenlion.common.enums.BaseStatusEnum;
import org.uppower.sevenlion.web.pms.common.model.entity.ProductContentEntity;
import org.uppower.sevenlion.web.pms.common.model.entity.ProductsEntity;
import org.uppower.sevenlion.web.pms.dao.mapper.ProductContentMapper;
import org.uppower.sevenlion.web.pms.dao.mapper.ProductsMapper;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/23 10:09 下午
 * 产品管理
 */
@Service
public class ProductsManager {

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductContentMapper contentMapper;

    public Page<ProductsEntity> selectPage(Integer pn, Integer pageSize, Integer type, String title, List<Long> productIds, Long belongId) {
        LambdaQueryWrapper<ProductsEntity> queryWrapper = new QueryWrapper<ProductsEntity>().lambda();
        Page<ProductsEntity> page = new Page<ProductsEntity>(pn, pageSize);
        if (StrUtil.isNotBlank(title)) {
            queryWrapper.like(ProductsEntity::getTitle,title);
        }
        if (ObjectUtil.isNotNull(type)) {
            queryWrapper.eq(ProductsEntity::getType, type);
        }
        if (CollUtil.isNotEmpty(productIds)) {
            queryWrapper.in(ProductsEntity::getId, productIds);
        }
        if (ObjectUtil.isNotNull(belongId)) {
            queryWrapper.eq(ProductsEntity::getBelongId, belongId);
        }
        queryWrapper.eq(ProductsEntity::getAudit, BaseAuditStatusEnum.PASS.getType());
        queryWrapper.eq(ProductsEntity::getStatus, BaseStatusEnum.ONLINE.getStatus());
        Page<ProductsEntity> result = productsMapper.selectPage(page, queryWrapper);
        return result;
    }

    public ProductsEntity selectById(Long id) {
        LambdaQueryWrapper<ProductsEntity> queryWrapper = new QueryWrapper<ProductsEntity>().lambda();
        queryWrapper.eq(ProductsEntity::getAudit, BaseAuditStatusEnum.PASS.getType());
        queryWrapper.eq(ProductsEntity::getStatus, BaseStatusEnum.ONLINE.getStatus());
        queryWrapper.eq(ProductsEntity::getId, id);
        return productsMapper.selectOne(queryWrapper);
    }

    public ProductContentEntity selectByContentId(Long productContentId) {
        LambdaQueryWrapper<ProductContentEntity> queryWrapper = new QueryWrapper<ProductContentEntity>().lambda();
        queryWrapper.eq(ProductContentEntity::getStatus, BaseStatusEnum.ONLINE.getStatus());
        queryWrapper.eq(ProductContentEntity::getId, productContentId);
        return contentMapper.selectOne(queryWrapper);
    }

    public List<ProductContentEntity> selectContentByProductId(Long productId) {
        LambdaQueryWrapper<ProductContentEntity> queryWrapper = new QueryWrapper<ProductContentEntity>().lambda();
        if (ObjectUtil.isNotNull(productId)) {
            queryWrapper.eq(ProductContentEntity::getProductId,productId);
        }
        return contentMapper.selectList(queryWrapper);
    }
}
