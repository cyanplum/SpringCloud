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
 * @date 2021/6/25 4:06 下午
 */
@Data
@ApiModel("地区查询参数")
public class DistrictQueryModel {

    @ApiModelProperty(name = "pn", value = "页码")
    private Integer pn = 1;

    @ApiModelProperty(name = "pageSize", value = "页大小")
    private Integer pageSize = 15;

    @ApiModelProperty(name = "level", value = "地区级别")
    private Integer level;

    @ApiModelProperty(name = "name",value = "地区名称")
    private String name;
}
