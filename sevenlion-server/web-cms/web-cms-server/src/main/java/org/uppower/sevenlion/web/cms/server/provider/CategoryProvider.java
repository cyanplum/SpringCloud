package org.uppower.sevenlion.web.cms.server.provider;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.constant.SqlConst;
import org.uppower.sevenlion.common.utils.SpringApplicationContext;
import org.uppower.sevenlion.web.cms.server.metabase.MetabaseService;
import org.uppower.sevenlion.web.cms.server.model.vo.CategoryVo;
import org.uppower.sevenlion.web.cms.server.schedule.BaseSchedule;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
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
public class CategoryProvider implements InitializingBean,Runnable {

    private static Map<Integer, List<CategoryVo>> categoryVoMap = Maps.newHashMap();

    @Resource(name = "mysqlMetabaseService")
    private MetabaseService metabaseService;

    @Resource(name = "redisMetabaseService")
    private MetabaseService redisMetabaseService;


    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void run() {
        categoryVoMap.clear();
        String jsonStr = metabaseService.getStringCurrent(SqlConst.BASE_CATEGORY);
        List<CategoryVo>  categoryVos = null;
        try {
            categoryVos = objectMapper.readValue(jsonStr,new TypeReference<List<CategoryVo>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (CollUtil.isNotEmpty(categoryVos)) {
            categoryVos.forEach(it->{
                if (categoryVoMap.containsKey(it.getType())) {
                    categoryVoMap.get(it.getType()).add(it);
                }else {
                    ArrayList<CategoryVo> tmp = new ArrayList<>();
                    tmp.add(it);
                    categoryVoMap.put(it.getType(),tmp);
                }
            });
        }
        //把mysql数据库中数据放入redis
        for (Integer key : categoryVoMap.keySet()) {
            redisMetabaseService.push(key.toString(),categoryVoMap.get(key));
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BaseSchedule.register(this, 5, 30, TimeUnit.SECONDS);
    }

    public static List<CategoryVo> getCategoryByType(Integer type) {
        MetabaseService redisMetabaseService = SpringApplicationContext.getBean("redisMetabaseService");
        String strData = redisMetabaseService.getStringCurrent(type.toString());
        if (ObjectUtil.isNull(strData)) {
            return null;
        }
        ObjectMapper objectMapper = SpringApplicationContext.getBean(ObjectMapper.class);
        List<CategoryVo>  categoryVos = null;
        try {
            categoryVos = objectMapper.readValue(strData,new TypeReference<List<CategoryVo>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (CollUtil.isEmpty(categoryVos)) {
            return null;
        }
        List<CategoryVo> result = categoryVos.stream().filter(it -> it.getSuperId() == 0L).collect(Collectors.toList());
        return result;
    }

}
