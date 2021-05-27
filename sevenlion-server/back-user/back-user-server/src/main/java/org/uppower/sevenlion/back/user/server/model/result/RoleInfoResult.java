package org.uppower.sevenlion.back.user.server.model.result;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/26 1:55 下午
 */
@ApiModel("角色详情返回集合")
@Data
public class RoleInfoResult {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "角色标签")
    private String label;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "角色介绍")
    private String description;

    @ApiModelProperty(value = "权限列表")
    private List<PermissionInfoResult> permissionInfoResultList;
}
