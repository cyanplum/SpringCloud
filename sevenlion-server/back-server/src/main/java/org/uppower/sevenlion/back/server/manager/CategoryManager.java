package org.uppower.sevenlion.back.server.manager;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.back.server.mapper.CategoryMapper;
import org.uppower.sevenlion.back.server.model.entity.CategoryEntity;

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
 * @date 2021/7/8 5:49 下午
 */
@Component
public class CategoryManager {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点id查询父节点名称
     * @date 2021/7/8 7:54 下午
     * @param superIds
     * @return Map<Long,String>
     * @auther sevenlion
     */
    public Map<Long, String> selectNameByPIds(List<Long> superIds) {
        if (CollectionUtil.isEmpty(superIds)) {
            return new HashMap<>(0);
        }
        List<CategoryEntity> categoryEntities = categoryMapper.selectList(new QueryWrapper<CategoryEntity>().lambda().in(CategoryEntity::getId, superIds));
        Map<Long, String> result = categoryEntities.stream().collect(Collectors.toMap(CategoryEntity::getId, CategoryEntity::getName));
        result.put(0L,"根节点");
        return result;
    }
}
