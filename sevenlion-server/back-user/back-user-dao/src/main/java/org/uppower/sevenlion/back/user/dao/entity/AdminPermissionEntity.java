package org.uppower.sevenlion.back.user.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户权限表
 * </p>
 *
 * @author qmw
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("admin_permission")
@ApiModel(value="AdminPermissionEntity对象", description="用户权限表")
public class AdminPermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父权限标签")
    @TableField("p_id")
    private Integer pId;

    @ApiModelProperty(value = "权限标签")
    @TableField("label")
    private String label;

    @ApiModelProperty(value = "前端菜单索引")
    @TableField("reference")
    private String reference;

    @ApiModelProperty(value = "权限名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "权限介绍")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;


}
