package org.uppower.sevenlion.back.system.server.model.result;

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
 * @date 2021/5/27 1:51 下午
 */
@ApiModel("地区列表返回参数集")
@Data
public class DistrictListResult {

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

    @ApiModelProperty(value = "地区级别名称")
    private String levelName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
