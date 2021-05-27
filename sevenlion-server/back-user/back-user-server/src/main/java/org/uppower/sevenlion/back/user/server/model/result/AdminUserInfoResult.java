package org.uppower.sevenlion.back.user.server.model.result;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/26 3:41 下午
 */
@ApiModel("管理用户详情返回集")
@Data
public class AdminUserInfoResult {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户email")
    private String email;

    @ApiModelProperty(value = "上级id")
    private Long superId;

    @ApiModelProperty(value = "上级名称")
    private String superName;

    @ApiModelProperty(value = "用户角色")
    private Integer roleId;

    @ApiModelProperty(value = "省id")
    private Long provinceId;

    @ApiModelProperty(value = "市id")
    private Long cityId;

    @ApiModelProperty(value = "区、县id")
    private Long districtId;

    @ApiModelProperty(value = "地区")
    private String address;

    @ApiModelProperty(value = "用户状态")
    private Integer status;

    @ApiModelProperty(value = "用户状态名称")
    private String statusName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
