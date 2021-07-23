package org.uppower.sevenlion.web.product.server.controller;

import cn.sevenlion.utils.response.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.sevenlion.web.product.common.model.result.CartResult;
import org.uppower.sevenlion.web.product.common.model.result.ProductResult;
import org.uppower.sevenlion.web.product.common.model.bo.CartBo;
import org.uppower.sevenlion.web.product.common.model.bo.ProductOrderBo;
import org.uppower.sevenlion.web.product.server.service.ProductService;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/22 11:26 上午
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 得到商品信息集合
     * @param ids
     * @param name
     * @return
     */

    @GetMapping("/getProductList")
    public CommonResult<List<ProductResult>> getProductList(@RequestParam(value = "ids",required = false)List<Long> ids, @RequestParam(value = "name",required = false) String name) {
        return productService.getProductList(ids,name);
    }

    @PostMapping("/cutProductStock")
    public CommonResult cutProductStock(@RequestBody List<ProductOrderBo> productOrders) {
        return productService.cutProductStock(productOrders);
    }

    @GetMapping("/cart/{id}")
    public CommonResult<List<CartResult>> cartIndex(@PathVariable Long id) {
        return productService.cartIndex(id);
    }


    @PostMapping("/cart")
    public CommonResult storeCart(@RequestBody CartBo bo) {
        return productService.storeCart(bo);
    }
}
