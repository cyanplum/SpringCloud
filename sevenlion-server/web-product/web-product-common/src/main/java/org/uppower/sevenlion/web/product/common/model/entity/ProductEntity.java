package org.uppower.sevenlion.web.product.common.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author qmw
 * @since 2021-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("price")
    private Long price;

    @TableField("special_price")
    private Long specialPrice;

    @TableField("stock")
    private Integer stock;

    @TableField("picture")
    private String picture;

    @TableField("content")
    private String content;

    @TableField("is_special")
    private Integer isSpecial;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("status")
    private Integer status;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
