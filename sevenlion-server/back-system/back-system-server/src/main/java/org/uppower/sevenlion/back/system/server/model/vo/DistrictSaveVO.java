package org.uppower.sevenlion.back.system.server.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/27 2:35 下午
 */
@ApiModel("地区新增对象参数集")
@Data
public class DistrictSaveVO {

    @ApiModelProperty(value = "地区code")
    @NotBlank(message = "不能为空")
    private String cityCode;

    @ApiModelProperty(value = "地区名称")
    @NotBlank(message = "不能为空")
    private String name;

    @ApiModelProperty(value = "上级id")
    @NotBlank(message = "不能为空")
    private Long pId;

    @ApiModelProperty(value = "是否启用")
    @NotBlank(message = "不能为空")
    private Integer status;

    @ApiModelProperty(value = "地区级别")
    @NotBlank(message = "不能为空")
    private Integer level;

}
