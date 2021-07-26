package org.uppower.sevenlion.web.pms.common.model.query;

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
 * @date 2021/4/1 2:02 下午
 */
@Data
@ApiModel("产品查询参数集")
public class ProductsQueryModel {

    @ApiModelProperty("页码")
    private Integer pn = 1;

    @ApiModelProperty("页大小")
    private Integer pageSize = 10;

    @ApiModelProperty("产品名称")
    private String title;

    @ApiModelProperty("产品类型(1-商家 2-用户)")
    private Integer type;

    @ApiModelProperty("类目id集合")
    private List<Long> categoryId;

    @ApiModelProperty("商户id")
    private Long belongId;


}
