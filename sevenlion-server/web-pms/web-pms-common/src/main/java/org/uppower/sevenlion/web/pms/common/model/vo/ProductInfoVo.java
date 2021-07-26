package org.uppower.sevenlion.web.pms.common.model.vo;

import cn.sevenlion.utils.annotation.ColumnField;
import cn.sevenlion.utils.enums.ColumnFieldEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel("产品详情返回集")
public class ProductInfoVo {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "小图")
    @ColumnField(type = ColumnFieldEnum.IMAGE)
    private String smallPicture;

    @ApiModelProperty(value = "图片集合")
    @ColumnField(type = ColumnFieldEnum.IMAGE)
    private List<String> pictures;

    @ApiModelProperty(value = "类目id")
    private Long categoryId;

    @ApiModelProperty(value = "商品类型(商家 用户)")
    private Integer type;

    @ApiModelProperty(value = "商品类型(商家 用户)")
    private String typeName;

    @ApiModelProperty(value = "拥有者id")
    private Long belongId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "省id")
    private Long provinceId;

    @ApiModelProperty(value = "市id")
    private Long cityId;

    @ApiModelProperty(value = "(县、区)id")
    private Long districtId;

    @ApiModelProperty(value = "父标签列表")
    private List<LabelVo> labels;

    @ApiModelProperty(value = "产品内容列表")
    private List<ProductContentVo> productContentList;

}
