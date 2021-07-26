package org.uppower.sevenlion.web.cms.server.manager;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.web.cms.common.client.ProductCategoryClient;
import org.uppower.sevenlion.web.cms.common.model.entity.ProductCategoryEntity;
import org.uppower.sevenlion.web.cms.dao.mapper.CategoryMapper;
import org.uppower.sevenlion.web.cms.dao.mapper.ProductCategoryMapper;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/8 3:31 下午
 */
@Component
public class CategoryManager {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public List<ProductCategoryEntity> selectProductCategoryList(List<Long> categoryIds) {
        LambdaQueryWrapper<ProductCategoryEntity> queryWrapper = new QueryWrapper<ProductCategoryEntity>().lambda();
        if (CollUtil.isNotEmpty(categoryIds)) {
            queryWrapper.in(ProductCategoryEntity::getCategoryId, categoryIds);
        }
        List<ProductCategoryEntity> result =  productCategoryMapper.selectList(queryWrapper);
        return result;
    }
}
