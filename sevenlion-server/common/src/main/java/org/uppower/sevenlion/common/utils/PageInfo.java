package org.uppower.sevenlion.common.utils;

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
 * @date 2021/6/25 4:20 下午
 */
@Data
public class PageInfo<T> {

    private List<T> data;

    private long page;

    private long total;

    public PageInfo( List<T> data, long page, long total) {
        this.data = data;
        this.page = page;
        this.total = total;
    }
}
