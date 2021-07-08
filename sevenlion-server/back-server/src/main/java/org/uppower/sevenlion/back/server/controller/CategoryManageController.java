package org.uppower.sevenlion.back.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.sevenlion.back.server.model.query.CategoryQueryModel;
import org.uppower.sevenlion.back.server.model.vo.CategoryListVo;
import org.uppower.sevenlion.back.server.service.CategoryManageService;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.common.utils.PageInfo;

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
@Api(tags = "类目管理")
@RestController
@RequestMapping
public class CategoryManageController {

    @Autowired
    private CategoryManageService categoryManageService;

    @ApiOperation("查询类目列表")
    @GetMapping
    public CommonResult<PageInfo<CategoryListVo>> index(CategoryQueryModel queryModel) {
        return CommonResult.success(categoryManageService.index(queryModel));
    }
}
