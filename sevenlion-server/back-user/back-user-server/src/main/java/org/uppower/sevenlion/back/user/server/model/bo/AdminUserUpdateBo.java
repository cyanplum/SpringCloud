package org.uppower.sevenlion.back.user.server.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/26 4:41 下午
 */
@ApiModel("修改管理用户对象参数集")
@Data
public class AdminUserUpdateBo {

    @ApiModelProperty(value = "用户电话")
    @NotBlank(message = "用户电话不能为空")
    private String phone;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "用户email")
    @NotNull(message = "用户email不能为空")
    private String email;

    @ApiModelProperty(value = "用户角色")
    @NotNull(message = "用户角色不能为空")
    private Integer roleId;

    @ApiModelProperty(value = "省id")
    @NotNull(message = "省不能为空")
    private Long provinceId;

    @ApiModelProperty(value = "市id")
    @NotNull(message = "市不能为空")
    private Long cityId;

    @ApiModelProperty(value = "区、县id")
    @NotNull(message = "区、县不能为空")
    private Long districtId;

    @ApiModelProperty(value = "用户状态")
    @NotBlank(message = "用户状态不能为空")
    private Integer status;
}
