package org.uppower.sevenlion.web.product.common.model.result;

import lombok.Data;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/3/7 6:37 下午
 */
@Data
public class ProductResult {

    private Long id;

    private String title;

    private Long price;

    private Long specialPrice;

    private Integer stock;

    private String picture;

    private String content;

    private Integer isSpecial;


    private Integer status;


    private String statusName;

}
