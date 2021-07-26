//package org.uppower.sevenlion.web.pms.common.client;
//
//import cn.sevenlion.utils.response.CommonResult;
//import org.springframework.stereotype.Component;
//import org.uppower.sevenlion.web.pms.common.model.bo.ProductOrderBo;
//import org.uppower.sevenlion.web.pms.common.model.vo.ProductDetailVo;
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
// *
// * 服务降级后执行这个兜底的方法
// */
//@Component
//public class ProductFeignFallbackService implements ProductFeignClient {
//
//    @Override
//    public CommonResult<List<ProductDetailVo>> getProductList(List<Long> ids, String name) {
//        return CommonResult.failed("业务繁忙... 请稍后再试");
//    }
//
//    @Override
//    public CommonResult cutProductStock(List<ProductOrderBo> productOrders) {
//        return null;
//    }
//}
