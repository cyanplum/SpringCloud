package org.uppower.sevenlion.web.pms.common.model.entity;

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

/**
 * <p>
 * 产品标签-内容关系表
 * </p>
 *
 * @author qmw
 * @since 2021-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_content_label")
@ApiModel(value="ProductContentLabelEntity对象", description="产品内容-标签关系表")
public class ProductContentLabelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品内容id")
    @TableField("product_content_id")
    private Long productContentId;

    @ApiModelProperty(value = "标签内容id")
    @TableField("label_content_id")
    private Long labelContentId;


}
