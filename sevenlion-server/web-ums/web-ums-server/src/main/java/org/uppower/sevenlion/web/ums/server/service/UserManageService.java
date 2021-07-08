package org.uppower.sevenlion.web.user.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.model.user.UserInfo;
import org.uppower.sevenlion.web.user.dao.mapper.UserMapper;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/20 10:35 下午
 */
@Service
public class UserManageService {

    @Autowired
    private UserMapper userMapper;


    public Object show(UserInfo userInfo) {

        return null;
    }
}
