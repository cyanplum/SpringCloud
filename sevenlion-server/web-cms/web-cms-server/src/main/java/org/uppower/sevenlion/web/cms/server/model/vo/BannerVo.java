package org.uppower.sevenlion.web.cms.server.model.vo;

import cn.sevenlion.utils.annotation.ColumnField;
import cn.sevenlion.utils.enums.ColumnFieldEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/23 9:46 下午
 */
@Data
@ApiModel("banner列表返回集合")
public class BannerVo {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图片")
    @ColumnField(type = ColumnFieldEnum.IMAGE)
    private String picture;

    @ApiModelProperty(value = "跳转id")
    private Long targetId;

    @ApiModelProperty(value = "权重")
    private Integer weight;
}
