package org.uppower.sevenlion.web.pms.common.model.vo;

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
 * @date 2021/4/1 2:02 下午
 */
@Data
@ApiModel("购物车返回集合")
public class CartVo {

    @ApiModelProperty("数量")
    private Integer number;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("价格")
    private Long price;

    @ApiModelProperty("图片")
    private String picture;

    @ApiModelProperty("标签")
    private List<LabelContentVo> labelContentList;

    public void incrNumber(Integer number) {
        this.number = this.number + number;
    }
}
