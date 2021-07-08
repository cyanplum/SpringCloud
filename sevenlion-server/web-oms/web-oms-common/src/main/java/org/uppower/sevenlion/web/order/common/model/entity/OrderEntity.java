package org.uppower.sevenlion.web.order.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.uppower.sevenlion.web.order.common.model.jsonobject.AddressJsonObject;
import org.uppower.sevenlion.web.order.common.model.jsonobject.ProductSnapObject;

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
@TableName("orders")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("trade_order_no")
    private Long tradeOrderNo;

    @TableField("user_id")
    private Long userId;

    @TableField("product_id")
    private Long productId;

    @TableField(value = "snap_product_info",el = "snapProductInfo,typeHandler=org.uppower.common.typehandler.ProductSnapTypeHandler")
    private ProductSnapObject snapProductInfo;

    @TableField("remark")
    private String remark;

    @TableField("price")
    private Long price;

    @TableField("count")
    private Integer count;

    @TableField(value = "address",el = "address,typeHandler=org.uppower.common.typehandler.AddressTypeHandler")
    private AddressJsonObject address;

    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("pay_status")
    private Integer payStatus;


}
