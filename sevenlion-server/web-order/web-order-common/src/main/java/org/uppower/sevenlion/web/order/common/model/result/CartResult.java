package org.uppower.sevenlion.web.order.common.model.result;

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
 * @date 2021/4/1 2:02 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResult {

    private Long id;

    private Integer number;

    private String title;

    private Long price;

    private Long specialPrice;

    private Integer isSpecial;

    private String picture;

    private String content;
}
