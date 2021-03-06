package org.uppower.sevenlion.web.pms.common.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/1 2:01 下午
 */
@Data
@ApiModel("添加购物车参数")
public class CartBo {

    @ApiModelProperty("产品id")
    private Long productId;

    @ApiModelProperty("产品内容id")
    private Long productContentId;

    @ApiModelProperty("标签")
    private List<LabelBo> labelList;

    @ApiModelProperty("数量")
    private Integer number;

}
