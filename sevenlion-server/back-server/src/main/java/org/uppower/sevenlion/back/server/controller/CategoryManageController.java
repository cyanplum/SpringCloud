package org.uppower.sevenlion.back.server.controller;

import cn.sevenlion.utils.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.sevenlion.back.server.model.bo.CategorySaveBo;
import org.uppower.sevenlion.back.server.model.query.CategoryQueryModel;
import org.uppower.sevenlion.back.server.model.vo.CategoryListVo;
import org.uppower.sevenlion.back.server.service.CategoryManageService;
import org.uppower.sevenlion.common.model.admin.AdminInfo;
import org.uppower.sevenlion.common.utils.PageInfo;

import javax.validation.Valid;

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

    @ApiOperation("新增类目")
    @PostMapping
    public CommonResult save(@Valid @RequestBody CategorySaveBo bo) {
        categoryManageService.save(bo);
        return CommonResult.success();
    }

    @ApiOperation("修改类目")
    @PatchMapping("/{id:.+}")
    public CommonResult update(AdminInfo adminInfo, @PathVariable Long id, @RequestBody CategorySaveBo bo) {
        categoryManageService.update(adminInfo,id, bo);
        return CommonResult.success();
    }

    @ApiOperation("删除类目")
    @DeleteMapping("/{id:.+")
    public CommonResult delete(@PathVariable Long id) {
        categoryManageService.delete(id);
        return CommonResult.success();
    }

    @ApiOperation("查看类目详情")
    @GetMapping("/{id:.+}")
    public CommonResult show(@PathVariable Long id) {
        return CommonResult.success(categoryManageService.show(id));
    }
}
