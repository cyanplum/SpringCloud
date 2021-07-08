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

/**
 * <p>
 * 产品类目关系表
 * </p>
 *
 * @author qmw
 * @since 2021-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("products_category")
@ApiModel(value="ProductsCategoryEntity对象", description="产品类目关系表")
public class ProductsCategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "类目id")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty(value = "产品id")
    @TableField("product_id")
    private Long productId;


}
