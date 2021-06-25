package org.uppower.sevenlion.back.product.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.back.product.dao.entity.CategoryEntity;
import org.uppower.sevenlion.back.product.dao.mapper.CategoryMapper;
import org.uppower.sevenlion.common.model.admin.AdminInfo;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/6/16 10:45 上午
 */
@Service
public class CategoryManageService {

    @Autowired
    private CategoryMapper categoryMapper;

//    public CommonResultPage index(AdminInfo adminInfo, Integer pn, Integer pageSize, String name) {
//        IPage<CategoryEntity> categoryEntityIPage = categoryMapper.selectPage(new Page<CategoryEntity>(pn, pageSize),
//                new QueryWrapper<CategoryEntity>()
//                        .lambda()
//                        .like(StringUtils.isNotBlank(name), CategoryEntity::getName, name));
//    }
}
