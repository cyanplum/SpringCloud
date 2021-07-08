package org.uppower.sevenlion.web.cms.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.web.cms.server.model.query.CategoryQueryModel;
import org.uppower.sevenlion.web.cms.server.service.CategoryService;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/8 3:11 下午
 */
@Api(tags = "类目相关")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("查询类目列表")
    @GetMapping
    public CommonResult selectCategory(CategoryQueryModel queryModel) {
        return CommonResult.success(categoryService.selectCategory(queryModel));
    }
}
