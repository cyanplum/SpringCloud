package org.uppower.sevenlion.web.cms.server.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.sevenlion.utils.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.uppower.sevenlion.oss.model.result.FileInfoResult;
import org.uppower.sevenlion.oss.utils.FileUploadUtils;
import org.uppower.sevenlion.web.cms.server.model.query.CategoryQueryModel;
import org.uppower.sevenlion.web.cms.server.model.vo.CategoryVo;
import org.uppower.sevenlion.web.cms.server.provider.CategoryProvider;

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

    @ApiOperation("查询类目列表")
    @GetMapping
    public CommonResult<List<CategoryVo>> selectCategory(CategoryQueryModel queryModel) {
        List<CategoryVo> result = null;
        if (ObjectUtil.isNotNull(queryModel.getType())) {
            result = CategoryProvider.getCategoryByType(queryModel.getType());
        }
        return CommonResult.success(result);
    }

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<FileInfoResult> upload(@ApiParam(required = true, value = "二进制文件流") @RequestParam("file") MultipartFile file) {
        return CommonResult.success(FileUploadUtils.upload(file, BUCKET_NAME));
    }
}
