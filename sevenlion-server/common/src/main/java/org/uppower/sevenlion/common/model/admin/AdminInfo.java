package org.uppower.sevenlion.common.model.admin;

import lombok.Data;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/25 5:57 下午
 */
@Data
public class AdminInfo {

    private Long userId;

    private String username;

    private Long provinceId;

    private Long cityId;

    private Long districtId;

    private List<String> roles;

    private List<String> permissions;
}
