package org.uppower.sevenlion.back.user.server.model.result;

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
 * @date 2021/5/26 1:57 下午
 */
@ApiModel("权限详情返回集合")
@Data
public class PermissionInfoResult {

    @ApiModelProperty(value = "权限id")
    private Long id;

    @ApiModelProperty(value = "权限标签")
    private String label;

    @ApiModelProperty(value = "权限名")
    private String name;

}
