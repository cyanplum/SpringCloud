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
@ApiModel("类目查询条件")
@Data
public class CategoryQueryModel {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "类型 1-首页类目 2-产品类目")
    private Integer type;

}
