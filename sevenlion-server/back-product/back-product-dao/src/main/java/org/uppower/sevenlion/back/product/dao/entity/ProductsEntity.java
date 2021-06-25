package org.uppower.sevenlion.back.product.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author qmw
 * @since 2021-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("products")
@ApiModel(value="ProductsEntity对象", description="商品表")
public class ProductsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "图片集合")
    @TableField("pictures")
    private String pictures;

    @ApiModelProperty(value = "类目id")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty(value = "商品类型(商家 用户)")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "拥有者id")
    @TableField("belong_id")
    private Long belongId;

    @ApiModelProperty(value = "标签对象集合")
    @TableField("lables")
    private String lables;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "省id")
    @TableField("province_id")
    private Long provinceId;

    @ApiModelProperty(value = "市id")
    @TableField("city_id")
    private Long cityId;

    @ApiModelProperty(value = "(县、区)id")
    @TableField("district_id")
    private Long districtId;

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


}
