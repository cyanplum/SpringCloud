package org.uppower.sevenlion.back.system.server.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/27 2:18 下午
 */
@ApiModel("地区详情参数集")
@Data
public class DistrictInfoResult {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "地区名称")
    private String name;

    @ApiModelProperty(value = "上级id")
    private Long pId;

    @ApiModelProperty(value = "是否启用")
    private Integer status;

    @ApiModelProperty(value = "地区级别")
    private Integer level;

    @ApiModelProperty(value = "省id")
    private Long provinceId;

    @ApiModelProperty(value = "市id")
    private Long cityId;

    @ApiModelProperty(value = "区、县id")
    private Long districtId;

    @ApiModelProperty(value = "地区")
    private String address;

    @ApiModelProperty(value = "地区级别名称")
    private String levelName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
