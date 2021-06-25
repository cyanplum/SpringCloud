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
 * 评论表
 * </p>
 *
 * @author qmw
 * @since 2021-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_comment")
@ApiModel(value="ProductCommentEntity对象", description="评论表")
public class ProductCommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty(value = "商家、用户")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "发起者id")
    @TableField("from_id")
    private Long fromId;

    @ApiModelProperty(value = "送达者id")
    @TableField("to_id")
    private Long toId;

    @ApiModelProperty(value = "图片集")
    @TableField("pictures")
    private String pictures;

    @ApiModelProperty(value = "回复内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "是否顶层")
    @TableField("is_floor")
    private Integer isFloor;

    @ApiModelProperty(value = "组")
    @TableField("group")
    private String group;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "逻辑删除0未删除 1删除")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
