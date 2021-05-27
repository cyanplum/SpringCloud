package org.uppower.sevenlion.back.system.common.api;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/27 3:23 下午
 */
public interface DistrictServiceApi {


    /**
     * 通过地区id查全地区名称
     * @date 2021/5/27 4:09 下午
     * @return String
     * @param id
     * @auther sevenlion
     */
    public String selectAddressById(Long id);


//    public List<> selectAddressByIds(List<Long> idList);
}
