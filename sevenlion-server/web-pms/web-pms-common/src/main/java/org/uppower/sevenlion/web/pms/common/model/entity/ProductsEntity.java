package org.uppower.sevenlion.web.pms.common.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.uppower.sevenlion.common.typehandler.PicturesTypeHandler;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author qmw
 * @since 2021-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("products")
@ApiModel(value="ProductsEntity对象", description="产品表")
public class ProductsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "小图")
    @TableField("small_picture")
    private String smallPicture;

    @ApiModelProperty(value = "图片集合")
    @TableField(value = "pictures", typeHandler = PicturesTypeHandler.class)
    private List<String> pictures;

    @ApiModelProperty(value = "类目id")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty(value = "商品类型(商家 用户)")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "拥有者id")
    @TableField("belong_id")
    private Long belongId;

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

    @ApiModelProperty(value = "审核状态")
    @TableField("audit")
    private Integer audit;

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
