package org.uppower.sevenlion.back.product.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.sevenlion.back.product.server.service.CategoryManageService;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/6/16 10:44 上午
 */
@Api(tags = "类目管理")
@RestController
@RequestMapping("/category")
public class CategoryManageController {

    @Autowired
    private CategoryManageService categoryManageService;

//    @ApiOperation("查询类目列表")
//    @GetMapping
//    public CommonResultPage index(@ApiIgnore AdminInfo adminInfo,
//                                  @ApiParam("页码") @RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn,
//                                  @ApiParam("页大小") @RequestParam(value = "pageSize", required = false, defaultValue = "15") Integer pageSize,
//                                  @ApiParam("类目名称") @RequestParam(value = "name", required = false) String name) {
//        return categoryManageService.index(adminInfo, pn, pageSize, name);
//    }
}
