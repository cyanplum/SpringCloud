package org.uppower.sevenlion.back.server.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @date 2021/7/8 5:54 下午
 */
@Data
@ApiModel("类目列表返回集合")
public class CategoryListVo {

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

}
