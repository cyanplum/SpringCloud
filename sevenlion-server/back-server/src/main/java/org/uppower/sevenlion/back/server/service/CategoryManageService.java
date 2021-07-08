package org.uppower.sevenlion.back.server.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.back.server.manager.CategoryManager;
import org.uppower.sevenlion.back.server.mapper.CategoryMapper;
import org.uppower.sevenlion.back.server.model.entity.CategoryEntity;
import org.uppower.sevenlion.back.server.model.query.CategoryQueryModel;
import org.uppower.sevenlion.back.server.model.vo.CategoryListVo;
import org.uppower.sevenlion.common.utils.PageInfo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/8 5:49 下午
 */
@Service
public class CategoryManageService {

    @Autowired
    private CategoryManager categoryManager;

    @Autowired
    private CategoryMapper categoryMapper;

    public PageInfo<CategoryListVo> index(CategoryQueryModel query) {
        IPage<CategoryEntity> categoryEntityIPage = categoryMapper.selectPage(new Page<CategoryEntity>(query.getPn(), query.getPageSize()),
                new QueryWrapper<CategoryEntity>().lambda()
                        .eq(ObjectUtil.isNotNull(query.getType()), CategoryEntity::getType, query.getType())
                        .eq(ObjectUtil.isNotNull(query.getStatus()), CategoryEntity::getStatus, query.getStatus()));
        if (categoryEntityIPage.getRecords().isEmpty()) {
            return PageInfo.build(categoryEntityIPage);
        }
        List<CategoryListVo> result = categoryEntityIPage.getRecords().stream().map(it -> {
            CategoryListVo vo = new CategoryListVo();
            BeanUtils.copyProperties(it, vo);
            return vo;
        }).collect(Collectors.toList());

        return PageInfo.build(categoryEntityIPage, result);
    }
}
