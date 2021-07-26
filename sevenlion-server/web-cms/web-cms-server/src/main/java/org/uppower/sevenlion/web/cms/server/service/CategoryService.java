package org.uppower.sevenlion.web.cms.server.service;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.utils.PageInfo;
import org.uppower.sevenlion.web.cms.common.model.entity.ProductCategoryEntity;
import org.uppower.sevenlion.web.cms.server.manager.CategoryManager;
import org.uppower.sevenlion.web.cms.server.model.query.CategoryQueryModel;

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
 * @date 2021/7/8 3:12 下午
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryManager categoryManager;

    public List<Long> queryProductsById(List<Long> categoryIds) {
        List<ProductCategoryEntity> productCategoryEntities = categoryManager.selectProductCategoryList(categoryIds);
        if (CollUtil.isEmpty(productCategoryEntities)) {
            return Lists.newArrayList();
        }
        List<Long> result = productCategoryEntities.stream().map(ProductCategoryEntity::getProductId).collect(Collectors.toList());
        return result;
    }
}
