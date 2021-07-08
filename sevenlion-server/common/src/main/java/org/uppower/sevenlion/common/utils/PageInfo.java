package org.uppower.sevenlion.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    private long current;

    private long pageSize;

    private long page;

    private long total;





    public PageInfo(List<T> data, long page, long total) {
        this.data = data;
        this.page = page;
        this.total = total;
    }

    public PageInfo(List<T> data, long current,long pageSize,long total, long page) {
        this.data = data;
        this.current = current;
        this.pageSize = pageSize;
        this.page = page;
        this.total = total;
    }

    public static PageInfo build(IPage page) {
        return new PageInfo(page.getRecords(),page.getCurrent(), page.getSize(), page.getTotal(),page.getPages());
    }

    public static PageInfo build(IPage page, List data) {
        return new PageInfo(data,page.getCurrent(), page.getSize(), page.getTotal(),page.getPages());
    }
}
