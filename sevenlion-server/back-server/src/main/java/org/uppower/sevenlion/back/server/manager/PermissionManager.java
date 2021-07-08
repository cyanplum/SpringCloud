package org.uppower.sevenlion.back.server.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.back.server.model.entity.AdminRoleEntity;
import org.uppower.sevenlion.back.server.mapper.AdminRoleMapper;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/6/25 4:48 下午
 */
@Component
public class PermissionManager {

    @Autowired
    private AdminRoleMapper adminRoleMapper;


    public IPage<AdminRoleEntity> selectPage(Long userId, Integer pn, Integer pageSize, String name) {
        IPage<AdminRoleEntity> adminRoleEntityIPage = adminRoleMapper.selectPage(new Page<AdminRoleEntity>(pn, pageSize),
                new QueryWrapper<AdminRoleEntity>().lambda()
                        .like(StringUtils.isNotBlank(name), AdminRoleEntity::getName, name)
                        .eq(AdminRoleEntity::getBelongId, userId));
        return adminRoleEntityIPage;
    }
}
