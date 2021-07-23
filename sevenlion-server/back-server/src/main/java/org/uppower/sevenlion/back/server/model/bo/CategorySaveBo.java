package org.uppower.sevenlion.back.server.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/8 8:02 下午
 */
@ApiModel("类目新增对象")
@Data
public class CategorySaveBo {

    @ApiModelProperty(value = "上级id")
    @NotNull(message = "上级id不能为空")
    private Long superId;

    @ApiModelProperty(value = "类目名称")
    @NotBlank(message = "类目名称不能为空")
    private String name;

    @ApiModelProperty(value = "图标")
    @NotBlank(message = "图标不能为空")
    private String icon;

    @ApiModelProperty(value = "权重")
    @NotNull(message = "权重不能为空")
    private Integer weight;

    @ApiModelProperty(value = "类目类型")
    @NotNull(message = "类目类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "状态")
    @NotNull(message = "状态不能为空")
    private Integer status;
}
