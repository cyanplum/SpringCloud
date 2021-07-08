package org.uppower.sevenlion.back.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uppower.sevenlion.security.reposiroty.image.ImageValidateCodeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/25 6:06 下午
 */
@RequestMapping
@RestController
@Api(tags = "验证码")
public class ImageCodeController {

    @Autowired
    private ImageValidateCodeService imageValidateCodeService;

    @ApiOperation("发送验证码")
    @GetMapping("/code/image")
    public void image(@RequestParam(value = "deviceId") String deviceId, HttpServletRequest request, HttpServletResponse response) {
        imageValidateCodeService.generate(deviceId,response);
    }
}
