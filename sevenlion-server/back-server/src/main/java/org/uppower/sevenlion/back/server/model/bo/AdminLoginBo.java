package org.uppower.sevenlion.back.server.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.uppower.sevenlion.security.model.image.ImageValidateCodeBean;

import javax.validation.constraints.NotBlank;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/25 6:00 下午
 */
@Data
@ApiModel("用户登录参数")
public class AdminLoginBo implements ImageValidateCodeBean {

    @ApiModelProperty("电话号码")
    @NotBlank(message = "电话号码不能为空")
    private String phone;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("主机号")
    @NotBlank(message = "主机号不能为空")
    private String deviceId;

    @ApiModelProperty("验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;

    @Override
    public String getImageCode() {
        return code;
    }
}
