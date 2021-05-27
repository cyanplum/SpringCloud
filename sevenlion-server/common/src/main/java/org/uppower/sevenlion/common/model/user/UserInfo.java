package org.uppower.sevenlion.common.model.user;

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
 * @date 2021/5/25 12:07 上午
 */
@Data
public class UserInfo {

    private Long userId;

    private String nickname;

    private List<String> roles;

    private List<String> permissions;


}
