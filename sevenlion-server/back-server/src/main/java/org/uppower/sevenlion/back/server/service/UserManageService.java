package org.uppower.sevenlion.back.server.service;

import cn.sevenlion.utils.response.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.back.server.model.entity.AdminUserEntity;
import org.uppower.sevenlion.back.server.model.entity.UserEntity;
import org.uppower.sevenlion.back.server.model.entity.UserInfoEntity;
import org.uppower.sevenlion.back.server.mapper.AdminUserMapper;
import org.uppower.sevenlion.back.server.mapper.UserInfoMapper;
import org.uppower.sevenlion.back.server.mapper.UserMapper;
import org.uppower.sevenlion.back.server.manager.UserManager;
import org.uppower.sevenlion.back.server.model.bo.AdminUserSaveBo;
import org.uppower.sevenlion.back.server.model.bo.AdminUserUpdateBo;
import org.uppower.sevenlion.back.server.model.query.AdminUserQueryModel;
import org.uppower.sevenlion.back.server.model.query.UserQueryModel;
import org.uppower.sevenlion.back.server.model.vo.AdminUserInfoVo;
import org.uppower.sevenlion.back.server.model.vo.AdminUserListVo;
import org.uppower.sevenlion.back.server.model.vo.UserInfoVo;
import org.uppower.sevenlion.back.server.model.vo.UserListVo;
import org.uppower.sevenlion.common.enums.BaseStatusEnum;
import org.uppower.sevenlion.common.enums.VipLevelTypeEnum;
import org.uppower.sevenlion.common.exceptions.BackException;
import org.uppower.sevenlion.common.model.Const;
import org.uppower.sevenlion.common.model.admin.AdminInfo;
import org.uppower.sevenlion.common.utils.PageInfo;
import org.uppower.sevenlion.oss.utils.OssUtils;


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
 * @date 2021/5/26 3:40 ??????
 */
@Service
public class UserManageService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserManager userManager;


    /**
     * ?????????????????????
     * @date 2021/5/26 3:45 ??????
     * @param adminInfo
     * @param queryModel
     * @return CommonResultPage<List<AdminUserListVo>>
     * @auther sevenlion
     */
    public PageInfo<AdminUserListVo> indexAdminUser(AdminInfo adminInfo, AdminUserQueryModel queryModel) {
        IPage<AdminUserEntity> adminUserEntityIPage = userManager.selectAdminUserPage(adminInfo.getUserId(), queryModel.getPn(), queryModel.getPageSize(), queryModel.getName());
        List<AdminUserListVo> results = adminUserEntityIPage.getRecords().stream().map(it -> {
            AdminUserListVo adminUserListResult = new AdminUserListVo();
            BeanUtils.copyProperties(adminUserListResult, it);
            adminUserListResult.setStatusName(BaseStatusEnum.msgByStatus(it.getStatus()));
            adminUserListResult.setSuperName(adminInfo.getUsername());
            // TODO: 2021/5/26 ??????
            return adminUserListResult;
        }).collect(Collectors.toList());
        return new PageInfo<>(results,adminUserEntityIPage.getCurrent(), adminUserEntityIPage.getTotal());
    }

    /**
     * ????????????????????????
     * @date 2021/5/26 4:03 ??????
     * @param adminInfo
     * @param id
     * @return CommonResult<AdminUserInfoVo>
     * @auther sevenlion
     */
    public AdminUserInfoVo showAdminUser(AdminInfo adminInfo, Long id) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(id);
        if (adminUserEntity == null) {
            throw new BackException("??????????????????");
        }
        AdminUserInfoVo result = new AdminUserInfoVo();
        BeanUtils.copyProperties(adminUserEntity,result);
        result.setStatusName(BaseStatusEnum.msgByStatus(adminUserEntity.getStatus()));
        result.setSuperName(adminInfo.getUsername());
        // TODO: 2021/6/29 ??????
        //String address = districtServiceApi.selectAddressById(result.getDistrictId());
        //result.setAddress(address);
        return result;
    }

    /**
     * ?????????????????????
     * @date 2021/5/26 4:07 ??????
     * @param adminInfo
     * @param bo
     * @return CommonResult
     * @auther sevenlion
     */
    public int saveAdminUser(AdminInfo adminInfo, AdminUserSaveBo bo) {
        AdminUserEntity adminUserEntity = new AdminUserEntity();
        BeanUtils.copyProperties(bo,adminUserEntity);
        adminUserEntity.setPassword(passwordEncoder.encode(Const.ADMIN_DEFAULT_PASSWORD));
        adminUserEntity.setSuperId(adminInfo.getUserId());
        CommonResult checkEntity = checkAdminUserDistrict(adminUserEntity, adminInfo);
        if (!checkEntity.isSuccess()) {
            throw new BackException("?????????????????????");
        }
        int size = adminUserMapper.insert(adminUserEntity);
        if (size != 1) {
            throw new BackException("????????????????????????");
        }
        return size;
    }


    /**
     * ?????????????????????
     * @date 2021/5/26 4:44 ??????
     * @param adminInfo
     * @param id
     * @param bo
     * @return CommonResult
     * @auther sevenlion
     */
    public CommonResult updateAdminUser(AdminInfo adminInfo, Long id, AdminUserUpdateBo bo) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(id);
        if (adminUserEntity == null) {
            throw new BackException("??????????????????");
        }
        AdminUserEntity entity = new AdminUserEntity();
        BeanUtils.copyProperties(bo,entity);
        entity.setSuperId(adminInfo.getUserId());
        entity.setId(adminInfo.getUserId());
        CommonResult checkEntity = checkAdminUserDistrict(adminUserEntity, adminInfo);
        if (!checkEntity.isSuccess()) {
            throw new BackException("?????????????????????");
        }
        if (adminUserMapper.insert(entity) != 1) {
            throw new BackException("?????????????????????");
        }
        return CommonResult.success();
    }

    /**
     * ??????????????????
     * @date 2021/5/26 5:29 ??????
     * @param adminInfo
     * @param id
     * @return CommonResult
     * @auther sevenlion
     */
    public CommonResult deleteAdminUser(AdminInfo adminInfo, Long id) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(id);
        if (adminUserEntity == null) {
            throw new BackException("?????????????????????");
        }
        if (adminUserMapper.deleteById(id) != 1) {
            throw new BackException("????????????????????????");
        }
        adminUserMapper.delete(new QueryWrapper<AdminUserEntity>().eq("super_id",adminInfo.getUserId()));
        return CommonResult.success();
    }

    /**
     * ????????????????????????
     * @date 2021/5/26 5:32 ??????
     * @param adminInfo
     * @param id
     * @param status
     * @return CommonResult
     * @auther sevenlion
     */
    public int updateAdminUserStatus(AdminInfo adminInfo, Long id, Integer status) {
        AdminUserEntity adminUserEntity = adminUserMapper.selectById(id);
        if (adminUserEntity == null) {
            throw new BackException("????????????????????????");
        }
        if (adminUserMapper.updateStatus(id,status) != 1) {
            throw new BackException("???????????????????????????");
        }
        int size = adminUserMapper.delete(new QueryWrapper<AdminUserEntity>().lambda().eq(AdminUserEntity::getSuperId, adminInfo.getUserId()));

        return size;
    }

    /**
     * ??????????????????
     * @date 2021/6/1 4:32 ??????
     * @param adminInfo
     * @param queryModel
     * @return CommonResultPage<UserListVo>
     * @auther sevenlion
     */
    public PageInfo<UserListVo> indexUser(AdminInfo adminInfo, UserQueryModel queryModel) {
        IPage<UserEntity> userEntityIPage = userManager.selectUserPage(queryModel.getPn(), queryModel.getPageSize(), queryModel.getName(), queryModel.getPhone());
        if (userEntityIPage.getRecords().isEmpty()) {
            return new PageInfo<>(null, userEntityIPage.getCurrent(), userEntityIPage.getTotal());
        }
        List<UserInfoEntity> userInfoEntityList = userInfoMapper.selectList(new QueryWrapper<UserInfoEntity>()
                .lambda()
                .in(UserInfoEntity::getUserId, userEntityIPage.getRecords()
                                .stream()
                                .map(UserEntity::getId)
                                .collect(Collectors.toList())));
        Map<Long, UserInfoEntity> userInfoMap = userInfoEntityList.stream().collect(Collectors.toMap(UserInfoEntity::getUserId, Function.identity()));
        List<UserListVo> results = userEntityIPage.getRecords().stream().map(it -> {
            UserListVo userListResult = new UserListVo();
            BeanUtils.copyProperties(it, userListResult);
            userListResult.setAvatar(OssUtils.getFileInfoResult(it.getAvatar()));
            userListResult.setStatusName(BaseStatusEnum.msgByStatus(it.getStatus()));
            userListResult.setScore(userInfoMap.get(it.getId()).getScore());
            userListResult.setVipLevel(userInfoMap.get(it.getId()).getVipLevel());
            userListResult.setVipLevelName(VipLevelTypeEnum.msgByStatus(userListResult.getVipLevel()));
            return userListResult;
        }).collect(Collectors.toList());

        return new PageInfo<>(results, userEntityIPage.getCurrent(), userEntityIPage.getTotal());
    }

    /**
     * ??????????????????
     * @date 2021/6/1 4:35 ??????
     * @param adminInfo
     * @param id
     * @return UserInfoVo
     * @auther sevenlion
     */
    public UserInfoVo showUser(AdminInfo adminInfo, Long id) {
        UserEntity userEntity = userMapper.selectById(id);
        if (userEntity == null) {
            throw new BackException("??????????????????");
        }
        UserInfoEntity userInfoEntity = userInfoMapper.selectOne(new QueryWrapper<UserInfoEntity>().lambda().eq(UserInfoEntity::getUserId, userEntity.getId()));
        UserInfoVo result = new UserInfoVo();
        BeanUtils.copyProperties(userEntity,result);
        result.setAlipayCount(userInfoEntity.getAlipayCount());
        result.setAvatar(OssUtils.getFileInfoResult(userEntity.getAvatar()));
        result.setVipLevelName(VipLevelTypeEnum.msgByStatus(result.getVipLevel()));
        result.setStatusName(BaseStatusEnum.msgByStatus(result.getStatus()));
        return result;
    }


    /**
     * ??????????????????
     * @date 2021/6/2 11:49 ??????
     * @param id
     * @param status
     * @return CommonResult
     * @auther sevenlion
     */
    public int updateUserStatus(Long id, Integer status) {
        UserEntity userEntity = userMapper.selectById(id);
        if (userEntity == null) {
            throw new BackException("??????????????????");
        }
        int size = userMapper.updateStatus(id, status);
        if (size != 1) {
            throw new BackException("???????????????");
        }
        return size;
    }

    // TODO: 2021/5/26
    public CommonResult checkAdminUserDistrict(AdminUserEntity entity, AdminInfo adminInfo) {

        return CommonResult.success();
    }
}
