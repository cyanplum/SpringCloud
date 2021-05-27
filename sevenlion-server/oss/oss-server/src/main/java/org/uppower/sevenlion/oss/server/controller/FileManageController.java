package org.uppower.sevenlion.oss.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.uppower.sevenlion.common.model.Const;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.oss.common.model.result.FileInfoResult;
import org.uppower.sevenlion.oss.common.utils.FileUploadUtils;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/24 11:55 下午
 */
@Api(tags = "文件管理")
@RestController
@RequestMapping("/file")
public class FileManageController {

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<FileInfoResult> upload(@ApiParam(required = true, value = "二进制文件流") @RequestParam("file") MultipartFile file) {
        return CommonResult.success(FileUploadUtils.upload(file, Const.BUCKET_NAME));

    }
}
