package org.uppower.sevenlion.back.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uppower.sevenlion.back.server.model.entity.DistrictsEntity;
import org.uppower.sevenlion.back.server.mapper.DistrictsMapper;
import org.uppower.sevenlion.back.server.manager.DistrictManager;
import org.uppower.sevenlion.back.server.model.bo.DistrictSaveBo;
import org.uppower.sevenlion.back.server.model.query.DistrictQueryModel;
import org.uppower.sevenlion.back.server.model.vo.DistrictInfoVo;
import org.uppower.sevenlion.back.server.model.vo.DistrictListVo;
import org.uppower.sevenlion.common.enums.BaseStatusEnum;
import org.uppower.sevenlion.common.enums.DistrictLevelTypeEnum;
import org.uppower.sevenlion.common.exceptions.BackException;
import org.uppower.sevenlion.common.utils.PageInfo;

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
@Service
public class DistrictManageService {


    @Autowired
    private DistrictsMapper districtsMapper;

    @Autowired
    private DistrictManager districtManager;

    /**
     * 地区列表查询
     * @date 2021/6/25 4:23 下午
     * @param queryModel
     * @return PageInfo<DistrictListVo>
     * @auther sevenlion
     */
    public PageInfo<DistrictListVo> index(DistrictQueryModel queryModel) {
        IPage<DistrictsEntity> districtsEntityIPage = districtManager.selectPage(queryModel.getPn(),queryModel.getPageSize(), queryModel.getLevel(),queryModel.getName());
        List<DistrictListVo> result = districtsEntityIPage.getRecords().stream().map(it -> {
            DistrictListVo districtListVo = new DistrictListVo();
            BeanUtils.copyProperties(it, districtListVo);
            districtListVo.setLevelName(DistrictLevelTypeEnum.msgByStatus(it.getLevel()));
            return districtListVo;
        }).collect(Collectors.toList());
        return new PageInfo<DistrictListVo>(result,districtsEntityIPage.getCurrent(),districtsEntityIPage.getTotal());
    }


    /**
     * 查询地区详情
     * @date 2021/5/27 2:21 下午
     * @param id
     * @return CommonResult<DistrictInfoVo>
     * @auther sevenlion
     */
    public DistrictInfoVo show(Long id) {
        DistrictsEntity districtsEntity = districtsMapper.selectById(id);
        if (districtsEntity == null) {
            throw new BackException("地区不存在！");
        }
        DistrictInfoVo result = new DistrictInfoVo();
        BeanUtils.copyProperties(districtsEntity,result);
        String address = districtsMapper.selectAllNameById(id);
        result.setAddress(address);
        result.setLevelName(DistrictLevelTypeEnum.msgByStatus(result.getLevel()));
        return result;
    }

    /**
     * 新增地区
     * @date @date 2021/6/25 4:28 下午
     * @param bo
     * @return CommonResult
     * @auther sevenlion
     */
    public int save(DistrictSaveBo bo) {
        DistrictsEntity districtsEntity = districtsMapper.selectOne(new QueryWrapper<DistrictsEntity>().eq("city_code", bo.getCityCode()));
        if (districtsEntity != null) {
            throw new BackException("地区已存在！");
        }
        DistrictsEntity saveEntity = new DistrictsEntity();
        BeanUtils.copyProperties(bo, saveEntity);
        int size = districtsMapper.insert(saveEntity);
        if (size != 1) {
            throw new BackException("新增地区失败！");
        }
        return size;
    }

    /**
     * 修改地区
     * @date 2021/6/25 4:28 下午
     * @param id
     * @param bo
     * @return int
     * @auther sevenlion
     */
    public int update(Long id, DistrictSaveBo bo) {
        DistrictsEntity districtsEntity = districtsMapper.selectById(id);
        if (districtsEntity == null) {
            throw new BackException("地区不存在！");
        }
        DistrictsEntity cityCodeEntity = districtsMapper.selectOne(new QueryWrapper<DistrictsEntity>().eq("city_code", bo.getCityCode()));
        if (cityCodeEntity != null) {
            throw new BackException("地区已存在！");
        }
        DistrictsEntity updateEntity = new DistrictsEntity();
        BeanUtils.copyProperties(bo, updateEntity);
        updateEntity.setId(id);
        int size = districtsMapper.updateById(updateEntity);
        if (size != 1) {
            throw new BackException("修改地区失败！");
        }
        return size;
    }

    /**
     * 删除地区
     * @date 2021/5/27 2:51 下午
     * @param id
     * @return CommonResult
     * @auther sevenlion
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        DistrictsEntity districtsEntity = districtsMapper.selectById(id);
        if (districtsEntity == null) {
            throw new BackException("地区不存在！");
        }
        if (districtsMapper.deleteById(id) != 1) {
            throw new  BackException("删除失败！");
        }
        List<DistrictsEntity> childEntity = districtsMapper.selectList(new QueryWrapper<DistrictsEntity>().eq("p_id", id));
        if (!childEntity.isEmpty()) {
            districtsMapper.delete(new QueryWrapper<DistrictsEntity>()
                    .in("p_id",childEntity.stream().map(it->it.getId()).collect(Collectors.toList())));
        }
        districtsMapper.delete(new QueryWrapper<DistrictsEntity>().eq("p_id",id));
        return 0;
    }

    /**
     * 修改地区状态
     * @date 2021/5/27 3:00 下午
     * @param id
     * @param status
     * @return CommonResult
     * @auther sevenlion
     */
    public int updateStatus(Long id, Integer status) {
        DistrictsEntity districtsEntity = districtsMapper.selectById(id);
        if (districtsEntity == null) {
            throw new BackException("地区不存在！");
        }
        if (districtsMapper.updateStatus(id,null,status) != 1) {
            throw new BackException("修改地区失败！");
        }
        if (status.equals(BaseStatusEnum.OFFLINE.getStatus())) {
            List<DistrictsEntity> childEntity = districtsMapper.selectList(new QueryWrapper<DistrictsEntity>().eq("p_id", id));
            if (!childEntity.isEmpty()) {
                districtsMapper.updateStatus(null,childEntity.stream().map(it->it.getId()).collect(Collectors.toList()), status);
            }
            districtsMapper.updateStatus(null,Arrays.asList(id),status);
        }
        return 0;
    }

    /**
     * 根据地区id查询地区全名称
     * @date 2021/7/1 1:33 下午
     * @param id
     * @return String
     * @auther sevenlion
     */
    public String selectAddressById(Long id) {
        return districtsMapper.selectAllNameById(id);
    }
}
