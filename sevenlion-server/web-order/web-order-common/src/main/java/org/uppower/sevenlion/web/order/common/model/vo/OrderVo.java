package org.uppower.sevenlion.web.order.common.model.vo;


import lombok.Data;
import org.uppower.sevenlion.web.order.common.model.jsonobject.AddressJsonObject;
import org.uppower.sevenlion.web.product.common.model.vo.ProductOrderVo;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/3/10 2:31 下午
 */
@Data
public class OrderVo {

    private Long id;

    private List<ProductOrderVo> productOrders;

    private Integer flag;

    private AddressJsonObject address;
}
