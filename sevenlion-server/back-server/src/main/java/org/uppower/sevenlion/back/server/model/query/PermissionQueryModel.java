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
 * @date 2021/6/25 4:44 下午
 */
@Data
@ApiModel("权限查询参数集合")
public class PermissionQueryModel {

    @ApiModelProperty(name = "pn", value = "页码")
    private Integer pn = 1;

    @ApiModelProperty(name = "pageSize", value = "页大小")
    private Integer pageSize = 15;


    @ApiModelProperty(name = "name",value = "地区名称")
    private String name;
}
