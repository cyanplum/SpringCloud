package org.uppower.sevenlion.web.product.common.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/1 2:01 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartBo {

    private Long id;

    private Long productId;

    private Integer number;

}
