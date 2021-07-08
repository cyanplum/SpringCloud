package org.uppower.sevenlion.web.order.common.client;//package org.uppower.alibaba_demo.order.common.client;
//
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
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
// * @date 2021/4/22 12:34 下午
// */
////@Component
//public class ProductFeignFallbackService implements ProductFeignClient {
//
//    @Override
//    public CommonResult<List<ProductResult>> getProductList(List<Long> ids, String name) {
//        return CommonResult.failed("服务降级");
//    }
//
//    @Override
//    public CommonResult cutProductStock(List<ProductOrderBo> productOrders) {
//        return null;
//    }
//}
