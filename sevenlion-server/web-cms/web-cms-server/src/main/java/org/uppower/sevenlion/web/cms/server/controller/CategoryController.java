package org.uppower.sevenlion.web.cms.server.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.utils.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.uppower.sevenlion.oss.model.result.FileInfoResult;
import org.uppower.sevenlion.oss.utils.FileUploadUtils;
import org.uppower.sevenlion.web.cms.server.model.query.CategoryQueryModel;
import org.uppower.sevenlion.web.cms.server.model.vo.CategoryVo;
import org.uppower.sevenlion.web.cms.server.provider.CategoryProvider;
import org.uppower.sevenlion.web.cms.server.service.CategoryService;

import java.util.List;

import static org.uppower.sevenlion.common.model.Const.BUCKET_NAME;

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
    private CategoryProvider categoryProvider;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("根据类型查询类目列表")
    @GetMapping("/type/{type:.+}")
    public CommonResult<List<CategoryVo>> queryCategoryByType(@PathVariable Integer type) {
        return CommonResult.success(categoryProvider.getCategoryByType(type));
    }

    @ApiOperation("根据父节点查询类目列表")
    @GetMapping("/{id:.+}")
    public CommonResult<List<CategoryVo>> queryCategoryByPId(@PathVariable Long id) {
        return CommonResult.success(categoryProvider.getCategoryByPId(id));
    }

    @GetMapping("/queryProductsById")
    public CommonResult<List<Long>> queryProductsById(List<Long> categoryIds) {
        return CommonResult.success(categoryService.queryProductsById(categoryIds));
    }

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<FileInfoResult> upload(@ApiParam(required = true, value = "二进制文件流") @RequestParam("file") MultipartFile file) {
        return CommonResult.success(FileUploadUtils.upload(file, BUCKET_NAME));
    }


}
