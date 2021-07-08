package org.uppower.sevenlion.back.server.model.query;

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
 * @date 2021/7/8 5:51 下午
 */
@Data
@ApiModel("类目查询参数集合")
public class CategoryQueryModel {

    @ApiModelProperty(name = "pn", value = "页码")
    private Integer pn = 1;

    @ApiModelProperty(name = "pageSize", value = "页大小")
    private Integer pageSize = 15;

    @ApiModelProperty("类目类型")
    private Integer type;

    @ApiModelProperty("状态")
    private Integer status;
}
