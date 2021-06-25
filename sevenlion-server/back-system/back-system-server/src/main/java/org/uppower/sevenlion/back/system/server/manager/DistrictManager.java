package org.uppower.sevenlion.back.system.server.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.back.system.dao.entity.DistrictsEntity;
import org.uppower.sevenlion.back.system.dao.mapper.DistrictsMapper;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/6/25 4:15 下午
 */
@Component
public class DistrictManager {

    @Autowired
    private DistrictsMapper districtsMapper;

    public IPage<DistrictsEntity> selectPage(Integer pn, Integer pageSize, Integer level, String name) {
        IPage<DistrictsEntity> districtsEntityIPage = districtsMapper.selectPage(new Page<DistrictsEntity>(pn, pageSize),
                new QueryWrapper<DistrictsEntity>().lambda().eq(DistrictsEntity::getLevel, level)
                        .like(name != null, DistrictsEntity::getName, name));
        return districtsEntityIPage;
    }
}
