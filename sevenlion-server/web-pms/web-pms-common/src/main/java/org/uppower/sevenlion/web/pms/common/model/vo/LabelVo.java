package org.uppower.sevenlion.web.pms.common.model.vo;

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
@ApiModel("标签返回列表")
public class LabelVo {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String title;


    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "子标签内容")
    private List<LabelContentVo> childList;

}
