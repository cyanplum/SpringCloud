package org.uppower.sevenlion.back.user.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.back.system.common.api.DistrictServiceApi;
import org.uppower.sevenlion.back.user.dao.entity.AdminUserEntity;
import org.uppower.sevenlion.back.user.dao.entity.UserEntity;
import org.uppower.sevenlion.back.user.dao.entity.UserInfoEntity;
import org.uppower.sevenlion.back.user.dao.mapper.AdminUserMapper;
import org.uppower.sevenlion.back.user.dao.mapper.UserInfoMapper;
import org.uppower.sevenlion.back.user.dao.mapper.UserMapper;
import org.uppower.sevenlion.back.user.server.model.result.AdminUserInfoResult;
import org.uppower.sevenlion.back.user.server.model.result.AdminUserListResult;
import org.uppower.sevenlion.back.user.server.model.result.UserInfoResult;
import org.uppower.sevenlion.back.user.server.model.result.UserListResult;
import org.uppower.sevenlion.back.user.server.model.vo.AdminUserSaveVO;
import org.uppower.sevenlion.back.user.server.model.vo.AdminUserUpdateVO;
import org.uppower.sevenlion.common.enums.BaseStatusEnum;
import org.uppower.sevenlion.common.enums.VipLevelTypeEnum;
import org.uppower.sevenlion.common.exceptions.BackException;
import org.uppower.sevenlion.common.model.Const;
import org.uppower.sevenlion.common.model.admin.AdminInfo;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.common.utils.CommonResultPage;
import org.uppower.sevenlion.oss.common.utils.FileUploadUtils;
import org.uppower.sevenlion.oss.common.utils.OssUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/26 3:40 下午
 */
@Service
public class UserManageService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Reference(check = false)
    private DistrictServiceApi districtServiceApi;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;


    /**
     * 查询管理员列表
     * @date 2021/5/26 3:45 下午
     * @param adminInfo
     * @param pn
     * @param pageSize
     * @param name
     * @return CommonResultPage<List<AdminUserListResult>>
     * @auther sevenlion
     */
    public CommonResultPage<AdminUserListResult> indexAdminUser(AdminInfo adminInfo, Integer pn, Integer pageSize, String name) {

        IPage<AdminUserEntity> adminUserEntityIPage = adminUserMapper.selectPage(new Page<AdminUserEntity>(pn, pageSize),
                new QueryWrapper<AdminUserEntity>()
                        .like(StringUtils.isNotBlank(name), "username", name)
                        .eq("super_id", adminInfo.getUserId()));
        List<AdminUserListResult> results = adminUserEntityIPage.getRecords().stream().map(it -> {
            AdminUserListResult adminUserListResult = new AdminUserListResult();
            BeanUtils.copyProperties(adminUserListResult, it);
            adminUserListResult.setStatusName(BaseStatusEnum.msgByStatus(it.getStatus()));
            adminUserListResult.setSuperName(adminInfo.getUsername());
            // TODO: 2021/5/26 地区
            return adminUserListResult;
        }).collect(Collectors.toList());
        return CommonResultPage.success(results,adminUserEntityIPage.getCurrent(), adminUserEntityIPage.getTotal());
    }

    /**
     * 查看管理用户详情
     * @date 2021/5/26 4:03 下午
     * @param adminInfo
     * @param id
     * @return CommonResult<AdminUserInfoResult>
     * @auther sevenlion
     */
    public CommonResult<AdminUserInfoResult> showAdminUser(AdminInfo adminInfo, Long id) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(id);
        if (adminUserEntity == null) {
            throw new BackException("用户不存在！");
        }
        AdminUserInfoResult result = new AdminUserInfoResult();
        BeanUtils.copyProperties(adminUserEntity,result);
        result.setStatusName(BaseStatusEnum.msgByStatus(adminUserEntity.getStatus()));
        result.setSuperName(adminInfo.getUsername());
        String address = districtServiceApi.selectAddressById(result.getDistrictId());
        result.setAddress(address);
        return CommonResult.success(result);
    }

    /**
     * 新增后台管理员
     * @date 2021/5/26 4:07 下午
     * @param adminInfo
     * @param vo
     * @return CommonResult
     * @auther sevenlion
     */
    public CommonResult saveAdminUser(AdminInfo adminInfo, AdminUserSaveVO vo) {
        AdminUserEntity adminUserEntity = new AdminUserEntity();
        BeanUtils.copyProperties(vo,adminUserEntity);
        adminUserEntity.setPassword(passwordEncoder.encode(Const.ADMIN_DEFAULT_PASSWORD));
        adminUserEntity.setSuperId(adminInfo.getUserId());
        CommonResult checkEntity = checkAdminUserDistrict(adminUserEntity, adminInfo);
        if (!checkEntity.isSuccess()) {
            throw new BackException("地区选择错误！");
        }
        if (adminUserMapper.insert(adminUserEntity) != 1) {
            throw new BackException("新增管理员失败！");
        }
        return CommonResult.success();
    }


    /**
     * 修改后台管理员
     * @date 2021/5/26 4:44 下午
     * @param adminInfo
     * @param id
     * @param vo
     * @return CommonResult
     * @auther sevenlion
     */
    public CommonResult updateAdminUser(AdminInfo adminInfo, Long id, AdminUserUpdateVO vo) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(id);
        if (adminUserEntity == null) {
            throw new BackException("用户不存在！");
        }
        AdminUserEntity entity = new AdminUserEntity();
        BeanUtils.copyProperties(vo,entity);
        entity.setSuperId(adminInfo.getUserId());
        entity.setId(adminInfo.getUserId());
        CommonResult checkEntity = checkAdminUserDistrict(adminUserEntity, adminInfo);
        if (!checkEntity.isSuccess()) {
            throw new BackException("地区选择错误！");
        }
        if (adminUserMapper.insert(entity) != 1) {
            throw new BackException("新增用户失败！");
        }
        return CommonResult.success();
    }

    /**
     * 删除管理用户
     * @date 2021/5/26 5:29 下午
     * @param adminInfo
     * @param id
     * @return CommonResult
     * @auther sevenlion
     */
    public CommonResult deleteAdminUser(AdminInfo adminInfo, Long id) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(id);
        if (adminUserEntity == null) {
            throw new BackException("管理员不存在！");
        }
        if (adminUserMapper.deleteById(id) != 1) {
            throw new BackException("删除管理员失败！");
        }
        adminUserMapper.delete(new QueryWrapper<AdminUserEntity>().eq("super_id",adminInfo.getUserId()));
        return CommonResult.success();
    }

    /**
     * 修改后台用户状态
     * @date 2021/5/26 5:32 下午
     * @param adminInfo
     * @param id
     * @param status
     * @return CommonResult
     * @auther sevenlion
     */
    public CommonResult updateAdminUserStatus(AdminInfo adminInfo, Long id, Integer status) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(id);
        if (adminUserEntity == null) {
            throw new BackException("管理用户不存在！");
        }
        if (adminUserMapper.updateStatus(id,status) != 1) {
            throw new BackException("删除管理用户失败！");
        }
        adminUserMapper.delete(new QueryWrapper<AdminUserEntity>().eq("super_id",adminInfo.getUserId()));

        return CommonResult.success();
    }

    /**
     * 查询用户列表
     * @date 2021/6/1 4:32 下午
     * @param adminInfo
     * @param pn
     * @param pageSize
     * @param name
     * @param phone
     * @return CommonResultPage<UserListResult>
     * @auther sevenlion
     */
    public CommonResultPage<UserListResult> indexUser(AdminInfo adminInfo, Integer pn, Integer pageSize, String name, String phone) {
        IPage<UserEntity> userEntityIPage = userMapper.selectPage(new Page<UserEntity>(pn, pageSize),
                new QueryWrapper<UserEntity>()
                        .lambda()
                        .eq(name != null, UserEntity::getNickname, name)
                        .like(phone != null, UserEntity::getPhone, phone));
        if (userEntityIPage.getRecords().isEmpty()) {
            return CommonResultPage.success();
        }
        List<UserInfoEntity> userInfoEntityList = userInfoMapper.selectList(new QueryWrapper<UserInfoEntity>()
                .lambda()
                .in(UserInfoEntity::getUserId, userEntityIPage.getRecords()
                                .stream()
                                .map(UserEntity::getId)
                                .collect(Collectors.toList())));
        Map<Long, UserInfoEntity> userInfoMap = userInfoEntityList.stream().collect(Collectors.toMap(UserInfoEntity::getUserId, Function.identity()));
        List<UserListResult> results = userEntityIPage.getRecords().stream().map(it -> {
            UserListResult userListResult = new UserListResult();
            BeanUtils.copyProperties(it, userListResult);
            userListResult.setAvatar(OssUtils.getFileInfoResult(it.getAvatar()));
            userListResult.setStatusName(BaseStatusEnum.msgByStatus(it.getStatus()));
            userListResult.setScore(userInfoMap.get(it.getId()).getScore());
            userListResult.setVipLevel(userInfoMap.get(it.getId()).getVipLevel());
            userListResult.setVipLevelName(VipLevelTypeEnum.msgByStatus(userListResult.getVipLevel()));
            return userListResult;
        }).collect(Collectors.toList());
        return CommonResultPage.success(results, pn, pageSize);
    }

    /**
     * 查询用户详情
     * @date 2021/6/1 4:35 下午
     * @param adminInfo
     * @param id
     * @return CommonResult<UserInfoResult>
     * @auther sevenlion
     */
    public CommonResult<UserInfoResult> showUser(AdminInfo adminInfo, Long id) {
        UserEntity userEntity = userMapper.selectById(id);
        if (userEntity == null) {
            return CommonResult.failed("用户不存在！");
        }
        UserInfoEntity userInfoEntity = userInfoMapper.selectOne(new QueryWrapper<UserInfoEntity>().lambda().eq(UserInfoEntity::getUserId, userEntity.getId()));
        UserInfoResult result = new UserInfoResult();
        BeanUtils.copyProperties(userEntity,result);
        result.setAlipayCount(userInfoEntity.getAlipayCount());
        result.setAvatar(OssUtils.getFileInfoResult(userEntity.getAvatar()));
        result.setVipLevelName(VipLevelTypeEnum.msgByStatus(result.getVipLevel()));
        result.setStatusName(BaseStatusEnum.msgByStatus(result.getStatus()));
        return CommonResult.success(result);
    }

    // TODO: 2021/5/26
    public CommonResult checkAdminUserDistrict(AdminUserEntity entity, AdminInfo adminInfo) {

        return CommonResult.success();
    }
}
