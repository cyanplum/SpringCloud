package org.uppower.sevenlion.back.server.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
 * @date 2021/7/9 2:18 下午
 */
@Data
public class CategoryInfoVo {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "上级id")
    private Long superId;

    @ApiModelProperty(value = "上级节点名称")
    private String pName;

    @ApiModelProperty(value = "类目名称")
    private String name;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "类目类型")
    private Integer type;

    @ApiModelProperty(value = "审核状态")
    private Integer audit;

    @ApiModelProperty(value = "审核状态名称")
    private String auditName;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "状态名称")
    private String statusName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "子类目")
    List<CategoryListVo> list;
}
