package org.uppower.sevenlion.web.cms.server.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/8 3:15 下午
 */
@Data
@ApiModel("banner查詢参数集")
public class BannerQueryModel {

    @ApiModelProperty("省id")
    private Long cityId;

    @ApiModelProperty("市id")
    private Long provinceId;

    @ApiModelProperty("区县id")
    private Long districtId;
}
