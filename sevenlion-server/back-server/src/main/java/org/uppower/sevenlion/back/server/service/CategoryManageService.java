package org.uppower.sevenlion.back.server.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.back.server.manager.CategoryManager;
import org.uppower.sevenlion.back.server.mapper.CategoryMapper;
import org.uppower.sevenlion.back.server.model.bo.CategorySaveBo;
import org.uppower.sevenlion.back.server.model.entity.CategoryEntity;
import org.uppower.sevenlion.back.server.model.query.CategoryQueryModel;
import org.uppower.sevenlion.back.server.model.vo.CategoryInfoVo;
import org.uppower.sevenlion.back.server.model.vo.CategoryListVo;
import org.uppower.sevenlion.common.enums.BaseAuditStatusEnum;
import org.uppower.sevenlion.common.enums.BaseStatusEnum;
import org.uppower.sevenlion.common.exceptions.BackException;
import org.uppower.sevenlion.common.model.admin.AdminInfo;
import org.uppower.sevenlion.common.utils.PageInfo;
import org.uppower.sevenlion.common.utils.RoleUtils;

import java.util.Arrays;
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
 * @date 2021/7/8 5:49 下午
 */
@Service
@Slf4j
public class CategoryManageService {

    @Autowired
    private CategoryManager categoryManager;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分页查询类目列表
     * @date 2021/7/9 11:58 上午
     * @param query
     * @return PageInfo<CategoryListVo>
     * @auther sevenlion
     */
    public PageInfo<CategoryListVo> index(CategoryQueryModel query) {
        IPage<CategoryEntity> categoryEntityIPage = categoryMapper.selectPage(new Page<CategoryEntity>(query.getPn(), query.getPageSize()),
                new QueryWrapper<CategoryEntity>().lambda()
                        .eq(ObjectUtil.isNotNull(query.getType()), CategoryEntity::getType, query.getType())
                        .eq(ObjectUtil.isNotNull(query.getStatus()), CategoryEntity::getStatus, query.getStatus())
                        .eq(ObjectUtil.isNotNull(query.getPId()),CategoryEntity::getSuperId,query.getPId()));
        if (categoryEntityIPage.getRecords().isEmpty()) {
            return PageInfo.build(categoryEntityIPage);
        }
        log.info("-------> 查询类目信息完成! {}",categoryEntityIPage);
        //得到上级节点id集合
        List<Long> superIds = categoryEntityIPage.getRecords().stream().filter(it -> it.getSuperId() == 0).map(CategoryEntity::getSuperId).distinct().collect(Collectors.toList());
        Map<Long, String> map = categoryManager.selectNameByPIds(superIds);
        List<CategoryListVo> result = categoryEntityIPage.getRecords().stream().map(it -> {
            CategoryListVo vo = new CategoryListVo();
            BeanUtils.copyProperties(it, vo);
            vo.setStatusName(BaseStatusEnum.msgByStatus(it.getStatus()));
            // TODO: 2021/7/8 图片
            vo.setPName(map.get(it.getSuperId()));
            return vo;
        }).collect(Collectors.toList());
        log.info("-------> 组装类目信息完成! {}",result);
        return PageInfo.build(categoryEntityIPage, result);
    }

    /**
     * 新增类目
     * @date 2021/7/9 11:59 上午
     * @param bo
     * @return int
     * @auther sevenlion
     */
    public int save(CategorySaveBo bo) {
        CategoryEntity pEntity = categoryMapper.selectById(bo.getSuperId());
        if (ObjectUtil.isNull(pEntity)) {
            log.error("父类目不存在！");
            throw new BackException("父类目不存在！");
        }
        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(bo, entity);
        int result = categoryMapper.insert(entity);
        if (result != 1) {
            log.error("新增类目失败！");
            throw new BackException("新增类目失败！");
        }
        return result;
    }

    /**
     * 修改类目
     * @date 2021/7/9 2:10 下午
     * @param adminInfo
     * @param id
     * @param bo
     * @return int
     * @auther sevenlion
     */
    public int update(AdminInfo adminInfo, Long id, CategorySaveBo bo) {
        CategoryEntity entity = categoryMapper.selectById(id);
        if (ObjectUtil.isNull(entity)) {
            log.error("类目不存在！");
            throw new BackException("类目不存在！");
        }
        entity = categoryMapper.selectById(bo.getSuperId());
        if (ObjectUtil.isNull(entity)) {
            log.error("父类目不存在！");
            throw new BackException("父类目不存在！");
        }
        entity = new CategoryEntity();
        BeanUtils.copyProperties(bo, entity);
        entity.setAudit(BaseAuditStatusEnum.WAIT.getType());
        entity.setId(id);
        boolean flag = RoleUtils.hasPermission(adminInfo.getPermissions(), "");
        if (flag) {
            entity.setAudit(BaseAuditStatusEnum.PASS.getType());
        }
        int result = categoryMapper.updateById(entity);
        if (result != 1) {
            log.error("修改类目失败！");
            throw new BackException("修改类目失败！");
        }
        return result;
    }

    /**
     * 删除类目
     * @date 2021/7/9 2:12 下午
     * @param id
     * @return int
     * @auther sevenlion
     */
    public int delete(Long id) {
        CategoryEntity entity = categoryMapper.selectById(id);
        if (ObjectUtil.isNull(entity)) {
            log.error("类目不存在！");
            throw new BackException("类目不存在！");
        }
        int result = categoryMapper.deleteById(id);
        if (result != 1) {
            log.error("删除类目失败！");
            throw new BackException("删除失败！");
        }
        categoryMapper.delete(new QueryWrapper<CategoryEntity>().lambda().eq(CategoryEntity::getSuperId,entity.getId()));
        return result;
    }

    public Object show(Long id) {
        CategoryEntity entity = categoryMapper.selectById(id);
        if (ObjectUtil.isNull(entity)) {
            log.error("类目不存在！");
            throw new BackException("类目不存在！");
        }
        CategoryInfoVo result = new CategoryInfoVo();
        BeanUtils.copyProperties(entity,result);
        result.setStatusName(BaseStatusEnum.msgByStatus(entity.getStatus()));
        // TODO: 2021/7/8 图片
        Map<Long, String> map = categoryManager.selectNameByPIds(Arrays.asList(entity.getSuperId()));
        result.setPName(map.get(entity.getSuperId()));
        CategoryQueryModel query = new CategoryQueryModel();
        query.setPId(id);
        PageInfo<CategoryListVo> list = index(query);
        result.setList(list.getData());
        return result;
    }
}
