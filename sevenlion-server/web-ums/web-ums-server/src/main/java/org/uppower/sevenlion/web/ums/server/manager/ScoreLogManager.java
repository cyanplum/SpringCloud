package org.uppower.sevenlion.web.ums.server.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.common.enums.BaseStatusEnum;
import org.uppower.sevenlion.web.ums.common.model.entity.ScoreLogEntity;
import org.uppower.sevenlion.web.ums.dao.mapper.ScoreLogMapper;

@Component
public class ScoreLogManager {

    @Autowired
    private ScoreLogMapper scoreLogMapper;


    public void insertScoreLog(Long value, Long userId, String remark, Integer type) {
        ScoreLogEntity scoreLogEntity = new ScoreLogEntity();
        scoreLogEntity.setValue(value);
        scoreLogEntity.setRemark(remark);
        scoreLogEntity.setType(type);
        scoreLogEntity.setUserId(userId);
        scoreLogEntity.setStatus(BaseStatusEnum.ONLINE.getStatus());
        scoreLogMapper.insert(scoreLogEntity);
    }
}
