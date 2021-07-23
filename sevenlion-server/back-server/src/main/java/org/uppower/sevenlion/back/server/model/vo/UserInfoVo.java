package org.uppower.sevenlion.back.server.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.uppower.sevenlion.oss.model.result.FileInfoResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/6/1 3:48 下午
 */
@ApiModel("用户列表返回集")
@Data
public class UserInfoVo {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private FileInfoResult avatar;

    @ApiModelProperty(value = "用户状态")
    private Integer status;

    @ApiModelProperty(value = "状态名称")
    private String statusName;

    @ApiModelProperty(value = "积分")
    private BigDecimal score;

    @ApiModelProperty(value = "会员等级")
    private Integer vipLevel;

    @ApiModelProperty(value = "阿里支付账号")
    private String alipayCount;

    @ApiModelProperty(value = "会员等级名称")
    private String vipLevelName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
