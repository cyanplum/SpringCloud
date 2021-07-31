package org.uppower.sevenlion.web.ums.server.manager;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.common.enums.BaseStatusEnum;
import org.uppower.sevenlion.web.ums.common.model.entity.UserEntity;
import org.uppower.sevenlion.web.ums.common.model.entity.UserInfoEntity;
import org.uppower.sevenlion.web.ums.dao.mapper.UserInfoMapper;
import org.uppower.sevenlion.web.ums.dao.mapper.UserMapper;

@Component
public class UserManager {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfoEntity selectInfoById(Long userId) {
        UserEntity userEntity = userMapper.selectById(userId);
        if (ObjectUtil.isNull(userEntity) || userEntity.getStatus().equals(BaseStatusEnum.OFFLINE.getStatus())) {
            return null;
        }
        return userInfoMapper.selectById(userId);
    }
}
