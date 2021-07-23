package org.uppower.sevenlion.web.cms.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.web.cms.common.model.entity.BannerEntity;
import org.uppower.sevenlion.web.cms.server.manager.BannerManager;
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
@Service
public class BannerService {

    @Autowired
    private BannerManager bannerManager;


    @Cacheable(value = "bannerList",key = "#queryModel.cityId+'-'+#queryModel.cityId+'-'+#queryModel.cityId")
    public Object index(BannerQueryModel queryModel) {
        List<BannerEntity> bannerEntities = bannerManager.selectByQueryModel(queryModel);
        return bannerEntities;
    }
}
