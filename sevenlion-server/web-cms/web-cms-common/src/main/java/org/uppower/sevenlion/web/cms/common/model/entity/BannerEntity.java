package org.uppower.sevenlion.web.cms.common.model.entity;

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
 * banner表
 * </p>
 *
 * @author qmw
 * @since 2021-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("banner")
@ApiModel(value="BannerEntity对象", description="banner表")
public class BannerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "图片")
    @TableField("picture")
    private String picture;

    @ApiModelProperty(value = "跳转id")
    @TableField("target_id")
    private Long targetId;

    @ApiModelProperty(value = "权重")
    @TableField("weight")
    private Integer weight;

    @ApiModelProperty(value = "状态")
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

    @ApiModelProperty(value = "省份id")
    @TableField("province_id")
    private Long provinceId;

    @ApiModelProperty(value = "市id")
    @TableField("city_id")
    private Long cityId;

    @ApiModelProperty(value = "区县id")
    @TableField("district_id")
    private Long districtId;


}
