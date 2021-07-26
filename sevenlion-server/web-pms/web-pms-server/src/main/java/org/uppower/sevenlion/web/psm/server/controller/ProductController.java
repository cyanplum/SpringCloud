package org.uppower.sevenlion.web.psm.server.controller;

import cn.sevenlion.utils.response.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.sevenlion.web.pms.common.model.query.ProductsQueryModel;
import org.uppower.sevenlion.web.pms.common.model.vo.ProductInfoVo;
import org.uppower.sevenlion.web.pms.common.model.vo.ProductsVo;
import org.uppower.sevenlion.web.psm.server.service.ProductService;

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

//    /**
//     * 得到商品信息集合
//     * @param ids
//     * @param name
//     * @return
//     */
//
//    @GetMapping("/getProductList")
//    public CommonResult<List<ProductDetailVo>> getProductList(@RequestParam(value = "ids",required = false)List<Long> ids, @RequestParam(value = "name",required = false) String name) {
//        return productService.getProductList(ids,name);
//    }
//
//    @PostMapping("/cutProductStock")
//    public CommonResult cutProductStock(@RequestBody List<ProductOrderBo> productOrders) {
//        return productService.cutProductStock(productOrders);
//    }
//
//    @GetMapping("/cart/{id}")
//    public CommonResult<List<CartResult>> cartIndex(@PathVariable Long id) {
//        return productService.cartIndex(id);
//    }
//
//
//    @PostMapping("/cart")
//    public CommonResult storeCart(@RequestBody CartBo bo) {
//        return productService.storeCart(bo);
//    }
//
    @GetMapping
    public CommonResult<List<ProductsVo>> indexProduct(ProductsQueryModel queryModel) {
        return CommonResult.success(productService.indexProduct(queryModel));
    }

    @GetMapping("/{id}")
    public CommonResult<ProductInfoVo> showProduct(@PathVariable Long id) {
        return CommonResult.success(productService.showProduct(id));
    }
}
