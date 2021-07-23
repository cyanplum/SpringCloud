package org.uppower.sevenlion.web.cms.server.provider;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.constant.RedisConst;
import org.uppower.sevenlion.common.constant.SqlConst;
import org.uppower.sevenlion.web.cms.common.model.entity.CategoryEntity;
import org.uppower.sevenlion.web.cms.dao.mapper.CustomBaseMapper;
import org.uppower.sevenlion.web.cms.server.model.vo.CategoryVo;

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
 * @date 2021/7/9 3:23 下午
 */
@Service
@Slf4j
public class CategoryProvider {

    @Autowired
    private CustomBaseMapper customBaseMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public List<CategoryEntity> queryList() {
        List<CategoryEntity> values = (List<CategoryEntity>) (List<?>) redisTemplate.opsForHash().values(RedisConst.CATEGORY_LIST);
        //查询redis，redis为空查数据库
        if (CollUtil.isEmpty(values)) {
            List<Object> objects = customBaseMapper.selectRawData(SqlConst.BASE_CATEGORY);
            List<CategoryEntity> categoryEntityList = JSONArray.parseArray(JSON.toJSONString(objects),CategoryEntity.class);
            if (CollUtil.isEmpty(categoryEntityList)) {
                return Lists.newArrayList();
            }
            Map<String, CategoryEntity> map = categoryEntityList.stream().collect(Collectors.toMap(it->it.getId().toString(), Function.identity()));
            //存入redis
            redisTemplate.opsForHash().putAll(RedisConst.CATEGORY_LIST,map);
            return categoryEntityList;
        }
        return values;
    }

    public List<CategoryVo> getCategoryByType(Integer type) {
        List<CategoryEntity> categoryEntityList = queryList();
        if (CollUtil.isEmpty(categoryEntityList)) {
            return Lists.newArrayList();
        }
        List<CategoryVo> result = categoryEntityList
                .stream()
                .filter(it -> it.getType().equals(type) && it.getSuperId() == 0L)
                .map(it->{
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(it,categoryVo);
                    return categoryVo;
                }).sorted((o1, o2) -> o2.getWeight() - o1.getWeight())
                .collect(Collectors.toList());
        return result;
    }

    public List<CategoryVo> getCategoryByPId(Long id) {
        List<CategoryEntity> categoryEntityList = queryList();
        if (CollUtil.isEmpty(categoryEntityList)) {
            return Lists.newArrayList();
        }
        List<CategoryVo> result = categoryEntityList
                .stream()
                .filter(it -> it.getSuperId().equals(id))
                .map(it->{
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(it,categoryVo);
                    return categoryVo;
                }).sorted((o1, o2) -> o2.getWeight() - o1.getWeight())
                .collect(Collectors.toList());
        return result;
    }
}
