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
 * @date 2021/5/26 12:37 下午
 */
@ApiModel("权限列表返回集合")
@Data
public class RoleListResult {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "角色标签")
    private String label;

    @ApiModelProperty(value = "角色名")
    private String name;
}
