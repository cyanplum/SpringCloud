package org.uppower.sevenlion.back.system.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.uppower.sevenlion.back.system.common.api.DistrictServiceApi;
import org.uppower.sevenlion.back.system.dao.entity.DistrictsEntity;
import org.uppower.sevenlion.back.system.dao.mapper.DistrictsMapper;
import org.uppower.sevenlion.back.system.server.model.result.DistrictInfoResult;
import org.uppower.sevenlion.back.system.server.model.result.DistrictListResult;
import org.uppower.sevenlion.back.system.server.model.vo.DistrictSaveVO;
import org.uppower.sevenlion.common.enums.BaseStatusEnum;
import org.uppower.sevenlion.common.enums.DistrictLevelTypeEnum;
import org.uppower.sevenlion.common.exceptions.BackException;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.common.utils.CommonResultPage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/27 1:45 下午
 */
@Component
@Service
public class DistrictManageService implements DistrictServiceApi {


    @Autowired
    private DistrictsMapper districtsMapper;

    /**
     * 地区列表查询
     * @date 2021/5/27 1:56 下午
     * @param pn
     * @param pageSize
     * @param level
     * @param name
     * @return CommonResultPage<DistrictListResult>
     * @auther sevenlion
     */
    public CommonResultPage<DistrictListResult> index(Integer pn, Integer pageSize, Integer level, String name) {
        IPage<DistrictsEntity> districtsEntityIPage = districtsMapper.selectPage(new Page<DistrictsEntity>(pn, pageSize),
                new QueryWrapper<DistrictsEntity>().eq("level", level)
                        .like(name != null, "name", name));
        List<DistrictListResult> result = districtsEntityIPage.getRecords().stream().map(it -> {
            DistrictListResult districtListResult = new DistrictListResult();
            BeanUtils.copyProperties(it, districtListResult);
            districtListResult.setLevelName(DistrictLevelTypeEnum.msgByStatus(it.getLevel()));
            return districtListResult;
        }).collect(Collectors.toList());
        return CommonResultPage.success(result,districtsEntityIPage.getCurrent(),districtsEntityIPage.getTotal());
    }


    /**
     * 查询地区详情
     * @date 2021/5/27 2:21 下午
     * @param id
     * @return CommonResult<DistrictInfoResult>
     * @auther sevenlion
     */
    public CommonResult<DistrictInfoResult> show(Long id) {
        DistrictsEntity districtsEntity = districtsMapper.selectById(id);
        if (districtsEntity == null) {
            throw new BackException("地区不存在！");
        }
        DistrictInfoResult result = new DistrictInfoResult();
        BeanUtils.copyProperties(districtsEntity,result);
        String address = districtsMapper.selectAllNameById(id);
        result.setAddress(address);
        result.setLevelName(DistrictLevelTypeEnum.msgByStatus(result.getLevel()));
        return CommonResult.success(result);
    }

    /**
     * 新增地区
     * @date 2021/5/27 2:41 下午
     * @param vo
     * @return CommonResult
     * @auther sevenlion
     */
    public CommonResult save(DistrictSaveVO vo) {
        DistrictsEntity districtsEntity = districtsMapper.selectOne(new QueryWrapper<DistrictsEntity>().eq("city_code", vo.getCityCode()));
        if (districtsEntity != null) {
            throw new BackException("地区已存在！");
        }
        DistrictsEntity saveEntity = new DistrictsEntity();
        BeanUtils.copyProperties(vo, saveEntity);

        if (districtsMapper.insert(saveEntity) != 1) {
            throw new BackException("新增地区失败！");
        }
        return CommonResult.success();
    }

    /**
     * 修改地区
     * @param id
     * @param vo
     * @return
     */
    public CommonResult update(Long id, DistrictSaveVO vo) {
        DistrictsEntity districtsEntity = districtsMapper.selectById(id);
        if (districtsEntity == null) {
            throw new BackException("地区不存在！");
        }
        DistrictsEntity cityCodeEntity = districtsMapper.selectOne(new QueryWrapper<DistrictsEntity>().eq("city_code", vo.getCityCode()));
        if (cityCodeEntity != null) {
            throw new BackException("地区已存在！");
        }
        DistrictsEntity updateEntity = new DistrictsEntity();
        BeanUtils.copyProperties(vo, updateEntity);
        updateEntity.setId(id);
        if (districtsMapper.updateById(updateEntity) != 1) {
            throw new BackException("修改地区失败！");
        }
        return CommonResult.success();
    }

    /**
     * 删除地区
     * @date 2021/5/27 2:51 下午
     * @param id
     * @return CommonResult
     * @auther sevenlion
     */
    @Transactional(rollbackFor = Exception.class)
    public CommonResult delete(Long id) {
        DistrictsEntity districtsEntity = districtsMapper.selectById(id);
        if (districtsEntity == null) {
            throw new BackException("地区不存在！");
        }
        if (districtsMapper.deleteById(id) != 1) {
            return CommonResult.failed("删除失败！");
        }
        List<DistrictsEntity> childEntity = districtsMapper.selectList(new QueryWrapper<DistrictsEntity>().eq("p_id", id));
        if (!childEntity.isEmpty()) {
            districtsMapper.delete(new QueryWrapper<DistrictsEntity>()
                    .in("p_id",childEntity.stream().map(it->it.getId()).collect(Collectors.toList())));
        }
        districtsMapper.delete(new QueryWrapper<DistrictsEntity>().eq("p_id",id));
        return CommonResult.success();
    }

    /**
     * 修改地区状态
     * @date 2021/5/27 3:00 下午
     * @param id
     * @param status
     * @return CommonResult
     * @auther sevenlion
     */
    public CommonResult updateStatus(Long id, Integer status) {
        DistrictsEntity districtsEntity = districtsMapper.selectById(id);
        if (districtsEntity == null) {
            throw new BackException("地区不存在！");
        }
        if (districtsMapper.updateStatus(id,null,status) != 1) {
            return CommonResult.failed("修改地区失败！");
        }
        if (status.equals(BaseStatusEnum.OFFLINE.getStatus())) {
            List<DistrictsEntity> childEntity = districtsMapper.selectList(new QueryWrapper<DistrictsEntity>().eq("p_id", id));
            if (!childEntity.isEmpty()) {
                districtsMapper.updateStatus(null,childEntity.stream().map(it->it.getId()).collect(Collectors.toList()), status);
            }
            districtsMapper.updateStatus(null,Arrays.asList(id),status);
        }
        return CommonResult.success();
    }

    @Override
    public String selectAddressById(Long id) {
        return districtsMapper.selectAllNameById(id);
    }
}
