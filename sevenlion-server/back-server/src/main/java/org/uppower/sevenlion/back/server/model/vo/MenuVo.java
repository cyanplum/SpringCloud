package org.uppower.sevenlion.back.server.model.vo;

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
 * @date 2021/6/30 1:40 下午
 */
@Data
@ApiModel("菜单返回集合")
public class MenuVo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("权限标签")
    private String label;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("前端菜单索引")
    private String reference;

    @ApiModelProperty("子菜单")
    private List<MenuVo> menuVoList;
}
