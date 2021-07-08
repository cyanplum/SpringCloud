package org.uppower.sevenlion.web.order.common.model.jsonobject;

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
 * @date 2021/3/10 1:16 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressJsonObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String province;

    private String city;

    private String district;

    private String detail;


}
