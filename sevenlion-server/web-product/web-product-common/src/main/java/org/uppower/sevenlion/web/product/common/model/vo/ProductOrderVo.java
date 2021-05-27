package org.uppower.sevenlion.web.product.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/1 4:29 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderVo implements Serializable {

    private Long productId;

    private String remark;

    private Integer number;

}
