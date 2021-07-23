package org.uppower.sevenlion.web.cms.server.model.vo;

import cn.sevenlion.utils.annotation.ColumnField;
import cn.sevenlion.utils.enums.ColumnFieldEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/9 4:04 下午
 */
@ApiModel("类目返回集合")
@Data
public class CategoryVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "上级id")
    private Long superId;

    @ApiModelProperty(value = "类目名称")
    private String name;

    @ApiModelProperty(value = "图标")
    @ColumnField(type = ColumnFieldEnum.IMAGE)
    private String icon;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "类目类型")
    private Integer type;

    public CategoryVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSuperId() {
        return superId;
    }

    public void setSuperId(Long superId) {
        this.superId = superId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
