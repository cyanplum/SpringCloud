package org.uppower.sevenlion.web.cms.common.client;

import cn.sevenlion.utils.response.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/8 3:11 下午
 * 产品类目相关
 */
@FeignClient(path = "/category")
public interface ProductCategoryClient {

    @GetMapping("/queryProductsById")
    public CommonResult<List<Long>> queryProductsById(List<Long> categoryIds);
}
