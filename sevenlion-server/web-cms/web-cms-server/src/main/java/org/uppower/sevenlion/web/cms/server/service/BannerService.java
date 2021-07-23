package org.uppower.sevenlion.web.cms.server.service;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.web.cms.common.model.entity.BannerEntity;
import org.uppower.sevenlion.web.cms.server.manager.BannerManager;
import org.uppower.sevenlion.web.cms.server.model.query.BannerQueryModel;
import org.uppower.sevenlion.web.cms.server.model.vo.BannerVo;

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
 * @date 2021/7/8 3:11 下午
 */
@Service
public class BannerService {

    @Autowired
    private BannerManager bannerManager;


    @Cacheable(value = "bannerList",key = "#queryModel.cityId+'-'+#queryModel.cityId+'-'+#queryModel.cityId")
    public List<BannerVo> index(BannerQueryModel queryModel) {
        List<BannerEntity> bannerEntities = bannerManager.selectByQueryModel(queryModel);
        if (CollUtil.isEmpty(bannerEntities)) {
            return Lists.newArrayList();
        }
        List<BannerVo> result = bannerEntities.stream().map(it -> {
            BannerVo bannerVo = new BannerVo();
            BeanUtils.copyProperties(it, bannerVo);
            return bannerVo;
        }).collect(Collectors.toList());
        return result;
    }
}
