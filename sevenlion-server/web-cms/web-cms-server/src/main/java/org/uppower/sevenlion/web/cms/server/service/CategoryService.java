package org.uppower.sevenlion.web.cms.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.utils.PageInfo;
import org.uppower.sevenlion.web.cms.server.manager.CategoryManager;
import org.uppower.sevenlion.web.cms.server.model.query.CategoryQueryModel;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/8 3:12 下午
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryManager categoryManager;

    /**
     * 查询类目
     * @date 2021/7/8 3:30 下午
     * @param queryModel
     * @return Object
     * @auther sevenlion
     */
    public PageInfo selectCategory(CategoryQueryModel queryModel) {

        return null;
    }
}
