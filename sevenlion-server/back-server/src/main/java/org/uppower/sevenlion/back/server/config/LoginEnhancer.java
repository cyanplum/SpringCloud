package org.uppower.sevenlion.back.server.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.back.server.model.entity.AdminPermissionEntity;
import org.uppower.sevenlion.back.server.model.entity.AdminRolePermissionEntity;
import org.uppower.sevenlion.back.server.model.entity.AdminUserEntity;
import org.uppower.sevenlion.back.server.mapper.AdminPermissionMapper;
import org.uppower.sevenlion.back.server.mapper.AdminRolePermissionMapper;
import org.uppower.sevenlion.back.server.mapper.AdminUserMapper;
import org.uppower.sevenlion.back.server.model.vo.MenuVo;
import org.uppower.sevenlion.security.filter.TokenEnhancer;
import org.uppower.sevenlion.security.model.token.SevenlionAuthenticationUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/6/30 11:47 上午
 */
@Component
public class LoginEnhancer implements TokenEnhancer {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    @Override
    public Map<String, Object> enhance(SevenlionAuthenticationUser authenticationToken) {
        Map<String,Object> map = new HashMap<>();
        Long id = authenticationToken.getId();
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(id);
        List<AdminRolePermissionEntity> adminRolePermissionEntities = adminRolePermissionMapper.selectList(new QueryWrapper<AdminRolePermissionEntity>().lambda().eq(AdminRolePermissionEntity::getRoleId, adminUserEntity.getRoleId()));
        List<Long> permissionIdList = adminRolePermissionEntities.stream().map(AdminRolePermissionEntity::getPermissionId).collect(Collectors.toList());
        List<AdminPermissionEntity> adminPermissionEntities = adminPermissionMapper.selectList(new QueryWrapper<AdminPermissionEntity>().lambda().in(AdminPermissionEntity::getId, permissionIdList));
        List<Long> superPermissionIdList = adminPermissionEntities.stream().map(AdminPermissionEntity::getPId).distinct().collect(Collectors.toList());
        List<AdminPermissionEntity> superPermissionList = adminPermissionMapper.selectList(new QueryWrapper<AdminPermissionEntity>().lambda().in(AdminPermissionEntity::getId, superPermissionIdList));

        List<MenuVo> superPermission = superPermissionList.stream().map(it -> {
            MenuVo vo = new MenuVo();
            BeanUtils.copyProperties(it, vo);
            return vo;
        }).collect(Collectors.toList());

        superPermission.forEach(it -> {
            it.setMenuVoList(new ArrayList<>());
            adminPermissionEntities.forEach(p -> {
                if (p.getPId().equals(it.getId())) {
                    MenuVo vo = new MenuVo();
                    BeanUtils.copyProperties(p, vo);
                    it.getMenuVoList().add(vo);
                }
            });
        });
        map.put("menus", superPermission);
        map.put("username", adminUserEntity.getUsername());
        map.put("provinceId", adminUserEntity.getProvinceId());
        map.put("cityId", adminUserEntity.getCityId());
        map.put("districtId", adminUserEntity.getDistrictId());
        return map;
    }
}
