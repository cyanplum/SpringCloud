package org.uppower.sevenlion.back.user.server.api;

import org.uppower.sevenlion.back.system.common.api.DistrictServiceApi;
import org.uppower.sevenlion.common.exceptions.BackException;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/6/2 10:07 上午
 */
public class DistrictServiceApiStub implements DistrictServiceApi {

    private final DistrictServiceApi districtServiceApi;

    public DistrictServiceApiStub(DistrictServiceApi districtServiceApi) {
        this.districtServiceApi = districtServiceApi;
    }

    @Override
    public String selectAddressById(Long id) {
        if (id != null) {
            return districtServiceApi.selectAddressById(id);
        }
        throw new BackException("调用失败！");
    }
}
