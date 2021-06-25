package org.uppower.sevenlion.web.order.common.client;//package org.uppower.alibaba_demo.order.common.client;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.uppower.alibaba_demo.order.common.model.result.ProductResult;
//import org.uppower.alibaba_demo.order.common.model.vo.ProductOrderBo;
//import org.uppower.alibaba_demo.order.common.utils.CommonResult;
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
//@FeignClient(name = "alibaba-demo-product")
//public interface ProductFeignClient {
//
//    /**
//     * 得到商品信息
//     * @param ids
//     * @param name
//     * @return
//     */
//    @GetMapping("/product/getProductList")
//    public CommonResult<List<ProductResult>> getProductList(@RequestParam(value = "ids",required = false)List<Long> ids, @RequestParam(value = "name",required = false) String name);
//
//    /**
//     * 扣减库存
//     * @param productOrders
//     * @return
//     */
//    @PostMapping("/product/cutProductStock")
//    public CommonResult cutProductStock(@RequestBody List<ProductOrderBo> productOrders);
//}
