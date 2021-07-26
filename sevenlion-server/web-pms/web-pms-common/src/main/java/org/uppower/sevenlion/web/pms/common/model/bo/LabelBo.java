package org.uppower.sevenlion.web.pms.common.model.bo;

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
 * @date 2021/7/26 10:08 下午
 */
@Data
@ApiModel("标签参数")
public class LabelBo {

    @ApiModelProperty("标签id")
    private Long labelId;

    @ApiModelProperty("标签内容id")
    private Long labelContentId;
}
