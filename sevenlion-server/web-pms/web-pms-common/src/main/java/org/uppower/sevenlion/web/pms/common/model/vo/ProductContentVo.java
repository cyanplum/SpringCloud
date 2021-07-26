package org.uppower.sevenlion.web.pms.common.model.vo;

import cn.sevenlion.utils.annotation.ColumnField;
import cn.sevenlion.utils.enums.ColumnFieldEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/3/7 6:37 下午
 */
@Data
@ApiModel("产品内容返回列表")
public class ProductContentVo {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "价格")
    private Long price;

    @ApiModelProperty(value = "原价")
    private Long originalPrice;

    @ApiModelProperty(value = "是否特价")
    private Integer special;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "图片")
    @ColumnField(type = ColumnFieldEnum.IMAGE)
    private String picture;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "标签内容id")
    private List<Long> labelContentList;
}
