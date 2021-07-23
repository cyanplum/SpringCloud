package org.uppower.sevenlion.back.server.controller;

import cn.sevenlion.utils.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.sevenlion.back.server.model.bo.DistrictSaveBo;
import org.uppower.sevenlion.back.server.model.query.DistrictQueryModel;
import org.uppower.sevenlion.back.server.model.vo.DistrictInfoVo;
import org.uppower.sevenlion.back.server.model.vo.DistrictListVo;
import org.uppower.sevenlion.back.server.service.DistrictManageService;
import org.uppower.sevenlion.common.utils.PageInfo;

import javax.validation.Valid;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/27 1:40 下午
 */
@Api(tags = "地区管理")
@RestController
@RequestMapping("/district")
public class DistrictManageController {


    @Autowired
    private DistrictManageService districtManageService;


    @ApiOperation("查询地区列表")
    @GetMapping
    public CommonResult<PageInfo<DistrictListVo>> index(DistrictQueryModel queryModel) {
        return CommonResult.success(districtManageService.index(queryModel));

    }

    @ApiOperation("查看地区详情")
    @GetMapping("/{id:.+}")
    public CommonResult<DistrictInfoVo> show(@ApiParam("地区id") @PathVariable Long id) {
        return CommonResult.success(districtManageService.show(id));
    }

    @ApiOperation("新增地区")
    @PostMapping
    public CommonResult save(@ApiParam("新增地区对象") @Valid @RequestBody DistrictSaveBo bo) {
        districtManageService.save(bo);
        return CommonResult.success();
    }

    @ApiOperation("修改地区")
    @PutMapping("/{id}")
    public CommonResult update(@ApiParam("地区id") @PathVariable Long id,
                               @ApiParam("修改地区对象") @Valid @RequestBody DistrictSaveBo bo) {
        districtManageService.update(id, bo);
        return CommonResult.success();
    }

    @ApiOperation("删除地区")
    @DeleteMapping("/{id}")
    public CommonResult delete(@ApiParam("地区id") @PathVariable Long id) {
        districtManageService.delete(id);
        return CommonResult.success();
    }

    @ApiOperation("修改地区状态")
    @PatchMapping("/{id}/{status}")
    public CommonResult updateStatus(@ApiParam("地区id") @PathVariable Long id,
                                     @ApiParam("状态") @PathVariable Integer status) {
        districtManageService.updateStatus(id, status);
        return CommonResult.success();
    }

    @GetMapping("/selectAddressById/{id}")
    public CommonResult<String> selectAddressById(@PathVariable Long id) {
        return  CommonResult.success(districtManageService.selectAddressById(id));
    }
}
