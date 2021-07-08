package org.uppower.sevenlion.back.server.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 用户表详情表
 * </p>
 *
 * @author qmw
 * @since 2021-06-01
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
    private BigDecimal score;

    @ApiModelProperty(value = "会员等级")
    @TableField("vip_level")
    private Integer vipLevel;

    @ApiModelProperty(value = "省id")
    @TableField("province_id")
    private Long provinceId;

    @ApiModelProperty(value = "市id")
    @TableField("city_id")
    private Long cityId;

    @ApiModelProperty(value = "区id")
    @TableField("district_id")
    private Long districtId;

    @ApiModelProperty(value = "阿里支付账号")
    @TableField("alipay_count")
    private String alipayCount;

    @ApiModelProperty(value = "默认地址id")
    @TableField("address_id")
    private Long addressId;


}
