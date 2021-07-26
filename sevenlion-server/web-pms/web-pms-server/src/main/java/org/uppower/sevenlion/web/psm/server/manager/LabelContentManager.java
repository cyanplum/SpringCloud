package org.uppower.sevenlion.web.psm.server.manager;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.web.pms.common.model.entity.LabelContentEntity;
import org.uppower.sevenlion.web.pms.common.model.entity.LabelEntity;
import org.uppower.sevenlion.web.pms.common.model.entity.ProductContentLabelEntity;
import org.uppower.sevenlion.web.pms.dao.mapper.LabelContentMapper;
import org.uppower.sevenlion.web.pms.dao.mapper.ProductContentLabelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/23 10:09 下午
 * 标签内容管理
 */
@Service
@Slf4j
public class LabelContentManager {

    @Autowired
    private LabelContentMapper labelContentMapper;

    @Autowired
    private ProductContentLabelMapper contentLabelMapper;


    public List<LabelContentEntity> selectByLabelIds(List<Long> labelIds) {
        if (CollUtil.isEmpty(labelIds)) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<LabelContentEntity> queryWrapper = new QueryWrapper<LabelContentEntity>().lambda();
        queryWrapper.in(LabelContentEntity::getLabelId,labelIds);
        return labelContentMapper.selectList(queryWrapper);
    }

    public List<ProductContentLabelEntity> selectListByProductIds(List<Long> productContentIds) {
        if (CollUtil.isEmpty(productContentIds)) {
            return Lists.newArrayList();
        }
        return  contentLabelMapper.selectList(new QueryWrapper<ProductContentLabelEntity>().lambda().in(ProductContentLabelEntity::getProductContentId, productContentIds));
    }
}
