package org.uppower.sevenlion.web.order.common.model.jsonobject;


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
 * @date 2021/3/10 1:28 下午
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSnapObject {

    private String title;

    private Long price;

    private Long specialPrice;

    private String picture;

    private String content;
}
