package org.uppower.sevenlion.back.system.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.uppower.sevenlion.back.system.dao.entity.DistrictsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

    String selectAllNameById(@Param("id") Long id);
}
