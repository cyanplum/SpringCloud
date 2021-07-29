package org.uppower.sevenlion.web.ums.server.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.web.ums.common.model.entity.SignStrategyEntity;
import org.uppower.sevenlion.web.ums.dao.mapper.SignStrategyMapper;
import org.uppower.sevenlion.web.ums.dao.mapper.UserSignLogMapper;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/29 9:05 下午
 */
@Component
public class SignManager {

    @Autowired
    private SignStrategyMapper signStrategyMapper;

    @Autowired
    private UserSignLogMapper userSignLogMapper;


    public List<SignStrategyEntity> selectStrategyList() {
        List<SignStrategyEntity> signStrategyEntities = signStrategyMapper.selectList(null);
        return signStrategyEntities;
    }
}
