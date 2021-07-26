//package org.uppower.sevenlion.web.pms.common.client;
//
//import cn.sevenlion.utils.response.CommonResult;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.uppower.sevenlion.web.pms.common.model.vo.ProductDetailVo;
//import org.uppower.sevenlion.web.pms.common.model.bo.ProductOrderBo;
//
//
//import java.util.List;
//
///**
// * @author create by:
// * *      ____        ___  ___       __          __
// * *    /  _  \     /   |/   |      | |        / /
// * *   | | | |     / /|   /| |     | |  __   / /
// * *  | | | |     / / |__/ | |    | | /  | / /
// * * | |_| |_    / /       | |   | |/   |/ /
// * * \_______|  /_/        |_|  |___/|___/
// * @date 2021/3/9 8:08 下午
// */
//@FeignClient(name = "alibaba-demo-product",fallback = ProductFeignFallbackService.class)
//public interface ProductFeignClient {
//
//    /**
//     * 得到商品信息
//     * @param ids
//     * @param name
//     * @return
//     */
//    @GetMapping("/product/getProductList")
//    public CommonResult<List<ProductDetailVo>> getProductList(@RequestParam(value = "ids")List<Long> ids, @RequestParam(value = "name") String name);
//
//    /**
//     * 扣减库存
//     * @param productOrders
//     * @return
//     */
//    @PostMapping("/product/cutProductStock")
//    public CommonResult cutProductStock(@RequestBody List<ProductOrderBo> productOrders);
//}
