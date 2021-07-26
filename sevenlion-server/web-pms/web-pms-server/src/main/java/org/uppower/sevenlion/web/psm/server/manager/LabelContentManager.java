package org.uppower.sevenlion.web.psm.server.manager;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.web.pms.common.model.entity.LabelContentEntity;
import org.uppower.sevenlion.web.pms.common.model.entity.ProductContentEntity;
import org.uppower.sevenlion.web.pms.common.model.entity.ProductContentLabelEntity;
import org.uppower.sevenlion.web.pms.common.model.entity.ProductLabelEntity;
import org.uppower.sevenlion.web.pms.common.model.vo.LabelVo;
import org.uppower.sevenlion.web.pms.dao.mapper.LabelContentMapper;
import org.uppower.sevenlion.web.pms.dao.mapper.ProductContentLabelMapper;
import org.uppower.sevenlion.web.pms.dao.mapper.ProductContentMapper;
import org.uppower.sevenlion.web.pms.dao.mapper.ProductLabelMapper;

import java.util.List;

@Service
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
}
