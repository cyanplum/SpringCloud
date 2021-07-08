package org.uppower.sevenlion.back.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.uppower.sevenlion.back.server.model.entity.DistrictsEntity;

import java.util.List;

/**
 * <p>
 * 地区表 Mapper 接口
 * </p>
 *
 * @author qmw
 * @since 2021-05-27
 */
public interface DistrictsMapper extends BaseMapper<DistrictsEntity> {

    int updateStatus(@Param("id") Long id, @Param("pIds") List<Long> pIds, @Param("status") Integer status);

    /**
     * 查询全地区名称
     * @param id
     * @return
     */
    String selectAllNameById(@Param("id") Long id);
}
