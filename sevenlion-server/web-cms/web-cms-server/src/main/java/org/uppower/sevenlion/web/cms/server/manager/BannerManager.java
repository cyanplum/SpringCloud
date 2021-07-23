package org.uppower.sevenlion.web.cms.server.manager;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.web.cms.common.model.entity.BannerEntity;
import org.uppower.sevenlion.web.cms.dao.mapper.BannerMapper;
import org.uppower.sevenlion.web.cms.server.model.query.BannerQueryModel;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/8 3:11 下午
 */
@Component
public class BannerManager {

    @Autowired
    private BannerMapper bannerMapper;

    public List<BannerEntity> selectByQueryModel(BannerQueryModel queryModel) {
        LambdaQueryWrapper<BannerEntity> queryWrapper = new QueryWrapper<BannerEntity>().lambda();
        if (ObjectUtil.isNull(queryModel.getCityId())) {
            queryWrapper.eq(BannerEntity::getCityId,queryModel.getCityId());
        }
        if (ObjectUtil.isNull(queryModel.getProvinceId())) {
            queryWrapper.eq(BannerEntity::getProvinceId,queryModel.getProvinceId());
        }
        if (ObjectUtil.isNull(queryModel.getDistrictId())) {
            queryWrapper.eq(BannerEntity::getDistrictId,queryModel.getDistrictId());
        }
        List<BannerEntity> bannerEntities = bannerMapper.selectList(queryWrapper);
        return bannerEntities;
    }
}
