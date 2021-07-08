package org.uppower.sevenlion.back.server.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author qmw
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("admin_user")
@ApiModel(value="AdminUserEntity对象", description="用户表")
public class AdminUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户email")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "上级id")
    @TableField("super_id")
    private Long superId;

    @ApiModelProperty(value = "用户角色")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "省id")
    @TableField("province_id")
    private Long provinceId;

    @ApiModelProperty(value = "市id")
    @TableField("city_id")
    private Long cityId;

    @ApiModelProperty(value = "区、县id")
    @TableField("district_id")
    private Long districtId;

    @ApiModelProperty(value = "用户状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除0未删除 1删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

}
