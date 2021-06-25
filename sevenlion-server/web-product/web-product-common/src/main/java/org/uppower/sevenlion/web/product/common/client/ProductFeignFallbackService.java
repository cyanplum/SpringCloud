package org.uppower.sevenlion.web.product.common.client;

import org.springframework.stereotype.Component;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.web.product.common.model.bo.ProductOrderBo;
import org.uppower.sevenlion.web.product.common.model.result.ProductResult;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/22 12:34 下午
 *
 * 服务降级后执行这个兜底的方法
 */
@Component
public class ProductFeignFallbackService implements ProductFeignClient {

    @Override
    public CommonResult<List<ProductResult>> getProductList(List<Long> ids, String name) {
        return CommonResult.failed("业务繁忙... 请稍后再试");
    }

    @Override
    public CommonResult cutProductStock(List<ProductOrderBo> productOrders) {
        return null;
    }
}
