package org.uppower.sevenlion.web.user.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表详情表
 * </p>
 *
 * @author qmw
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_info")
@ApiModel(value="UserInfoEntity对象", description="用户表详情表")
public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "积分")
    @TableField("score")
    private Long score;

    @ApiModelProperty(value = "会员等级")
    @TableField("vip_level")
    private Integer vipLevel;

    @ApiModelProperty(value = "最近登录时间")
    @TableField("recent_login")
    private LocalDateTime recentLogin;


}
