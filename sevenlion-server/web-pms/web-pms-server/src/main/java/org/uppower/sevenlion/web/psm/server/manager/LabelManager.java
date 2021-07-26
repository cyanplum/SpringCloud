package org.uppower.sevenlion.web.psm.server.manager;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.web.pms.common.model.entity.LabelContentEntity;
import org.uppower.sevenlion.web.pms.common.model.entity.LabelEntity;
import org.uppower.sevenlion.web.pms.common.model.entity.ProductLabelEntity;
import org.uppower.sevenlion.web.pms.common.model.vo.LabelContentVo;
import org.uppower.sevenlion.web.pms.common.model.vo.LabelVo;
import org.uppower.sevenlion.web.pms.dao.mapper.LabelMapper;
import org.uppower.sevenlion.web.pms.dao.mapper.ProductLabelMapper;

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
 * 标签管理
 */
@Service
public class LabelManager {

    @Autowired
    private ProductLabelMapper productLabelMapper;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private LabelContentManager contentManager;

    public List<LabelVo> queryByProductId(Long productId) {
        List<ProductLabelEntity> productLabelEntities = productLabelMapper.selectList(new QueryWrapper<ProductLabelEntity>().lambda().eq(ProductLabelEntity::getProductId, productId));
        if (CollUtil.isEmpty(productLabelEntities)) {
            return Lists.newArrayList();
        }
        //得到标签id
        List<Long> labelIds = productLabelEntities.stream().map(ProductLabelEntity::getLabelId).collect(Collectors.toList());
        //查询标签内容
        List<LabelEntity> labelEntities = labelMapper.selectList(new QueryWrapper<LabelEntity>()
                .lambda()
                .in(LabelEntity::getId, labelIds));
        if (CollUtil.isEmpty(labelEntities)) {
            return Lists.newArrayList();
        }
        //设置标签返回信息
        List<LabelVo> result = labelEntities.stream().map(it -> {
            LabelVo vo = new LabelVo();
            BeanUtils.copyProperties(it, vo);
            return vo;
        }).collect(Collectors.toList());
        //查询标签内容
        List<LabelContentEntity> labelContentEntities = contentManager.selectByLabelIds(labelIds);
        //如果有，就这是标签内容
        if (CollUtil.isNotEmpty(labelContentEntities)) {
            ArrayListMultimap<Long, LabelContentVo> list = ArrayListMultimap.create();
            for (LabelContentEntity it : labelContentEntities) {
                LabelContentVo vo = new LabelContentVo();
                BeanUtils.copyProperties(it,vo);
                list.put(it.getLabelId(),vo);
            }
            result.stream().forEach(it -> {
                it.setChildList(list.get(it.getId()));
            });
        }

        return result;
    }

    public List<LabelEntity> selectByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        return labelMapper.selectList(new QueryWrapper<LabelEntity>().lambda().in(LabelEntity::getId,ids));
    }

}
