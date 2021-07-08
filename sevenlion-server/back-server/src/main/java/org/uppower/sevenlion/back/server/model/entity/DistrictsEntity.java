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
 * 地区表
 * </p>
 *
 * @author qmw
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("districts")
@ApiModel(value="DistrictsEntity对象", description="地区表")
public class DistrictsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "地区code")
    @TableField("city_code")
    private String cityCode;

    @ApiModelProperty(value = "地区名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "上级id")
    @TableField("p_id")
    private Long pId;

    @ApiModelProperty(value = "是否启用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "地区级别")
    @TableField("level")
    private Integer level;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "逻辑删除0未删除 1删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
