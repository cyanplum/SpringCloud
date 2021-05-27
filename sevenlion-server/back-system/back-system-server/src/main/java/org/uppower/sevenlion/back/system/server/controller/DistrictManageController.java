package org.uppower.sevenlion.back.system.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.sevenlion.back.system.server.model.result.DistrictInfoResult;
import org.uppower.sevenlion.back.system.server.model.result.DistrictListResult;
import org.uppower.sevenlion.back.system.server.model.vo.DistrictSaveVO;
import org.uppower.sevenlion.back.system.server.service.DistrictManageService;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.common.utils.CommonResultPage;

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
    public CommonResultPage<DistrictListResult> index(@ApiParam("页码") @RequestParam(value = "pn",required = false,defaultValue = "1") Integer pn,
                                                      @ApiParam("页大小") @RequestParam(value = "pageSize",required = false,defaultValue = "15") Integer pageSize,
                                                      @ApiParam("地区级别") @RequestParam(value = "level",required = false,defaultValue = "1") Integer level,
                                                      @ApiParam("地区名称") @RequestParam(value = "name",required = false) String name) {
        return districtManageService.index(pn, pageSize,level,name);
    }

    @ApiOperation("查看地区详情")
    @GetMapping("/{id}")
    public CommonResult<DistrictInfoResult> show(@ApiParam("地区id") @PathVariable Long id) {
        return districtManageService.show(id);
    }

    @ApiOperation("新增地区")
    @PostMapping
    public CommonResult save(@ApiParam("新增地区对象") @Valid @RequestBody DistrictSaveVO vo) {
        return districtManageService.save(vo);
    }

    @ApiOperation("修改地区")
    @PutMapping("/{id}")
    public CommonResult update(@ApiParam("地区id") @PathVariable Long id,
                               @ApiParam("修改地区对象") @Valid @RequestBody DistrictSaveVO vo) {
        return districtManageService.update(id, vo);
    }

    @ApiOperation("删除地区")
    @DeleteMapping("/{id}")
    public CommonResult delete(@ApiParam("地区id") @PathVariable Long id) {
        return districtManageService.delete(id);
    }

    @ApiOperation("修改地区状态")
    @PatchMapping("/{id}/{status}")
    public CommonResult updateStatus(@ApiParam("地区id") @PathVariable Long id,
                                     @ApiParam("状态") @PathVariable Integer status) {
        return districtManageService.updateStatus(id, status);
    }
}
