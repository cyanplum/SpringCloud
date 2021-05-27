package org.uppower.sevenlion.back.user.server.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/26 2:32 下午
 */
@ApiModel("角色新增对象参数集")
@Data
public class RoleSaveVO {

    @ApiModelProperty(value = "角色标签")
    @NotBlank(message = "角色标签不能为空")
    private String label;

    @ApiModelProperty(value = "角色名")
    @NotBlank(message = "角色名不能为空")
    private String name;

    @ApiModelProperty(value = "角色介绍")
    @NotBlank(message = "角色介绍不能为空")
    private String description;

    @ApiModelProperty(value = "角色权限列表id")
    private List<Long> permissionIdList;

}
