package org.uppower.sevenlion.web.cms.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.web.cms.server.model.query.BannerQueryModel;
import org.uppower.sevenlion.web.cms.server.service.BannerService;

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
@RestController
@RequestMapping("/banner")
@Api(tags = "banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation("查询banner")
    @GetMapping
    public CommonResult index(BannerQueryModel queryModel) {
        bannerService.index(queryModel);
        return CommonResult.success();
    }
}
