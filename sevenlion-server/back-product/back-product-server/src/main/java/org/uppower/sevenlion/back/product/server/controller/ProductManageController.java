package org.uppower.sevenlion.back.product.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.sevenlion.back.product.server.service.ProductManageService;
import org.uppower.sevenlion.common.utils.CommonResult;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/6/16 10:11 上午
 */
@RestController
@RequestMapping("/product")
public class ProductManageController {

    @Autowired
    private ProductManageService productManageService;

//    @GetMapping("/category")
//    public CommonResult categoryIndex() {
//        return productManageService.categoryIndex();
//    }

}
