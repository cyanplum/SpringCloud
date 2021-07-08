package org.uppower.sevenlion.back.server.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.back.server.model.entity.AdminUserEntity;
import org.uppower.sevenlion.back.server.model.entity.UserEntity;
import org.uppower.sevenlion.back.server.mapper.AdminUserMapper;
import org.uppower.sevenlion.back.server.mapper.UserMapper;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/6/25 5:04 下午
 */
@Component
public class UserManager {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private UserMapper userMapper;

    public IPage<AdminUserEntity> selectAdminUserPage(Long userId, Integer pn, Integer pageSize, String name) {
        IPage<AdminUserEntity> adminUserEntityIPage = adminUserMapper.selectPage(new Page<AdminUserEntity>(pn, pageSize),
                new QueryWrapper<AdminUserEntity>().lambda()
                        .like(StringUtils.isNotBlank(name), AdminUserEntity::getUsername, name)
                        .eq(AdminUserEntity::getSuperId, userId));
        return adminUserEntityIPage;
    }

    public IPage<UserEntity> selectUserPage(Integer pn, Integer pageSize, String name, String phone) {
        IPage<UserEntity> userEntityIPage = userMapper.selectPage(new Page<UserEntity>(pn, pageSize),
                new QueryWrapper<UserEntity>()
                        .lambda()
                        .eq(name != null, UserEntity::getNickname, name)
                        .like(phone != null, UserEntity::getPhone, phone));
        return userEntityIPage;
    }
}
