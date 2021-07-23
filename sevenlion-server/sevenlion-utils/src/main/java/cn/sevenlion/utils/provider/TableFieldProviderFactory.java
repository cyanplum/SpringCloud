package cn.sevenlion.utils.provider;

import cn.hutool.core.collection.CollUtil;
import cn.sevenlion.utils.enums.TableFieldEnum;
import cn.sevenlion.utils.processor.TableFieldProcessor;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/21 3:32 下午
 */
public class TableFieldProviderFactory {

    public static ArrayListMultimap<TableFieldEnum, TableFieldProcessor> tableFieldProcessorMap = ArrayListMultimap.create();

    public static List<TableFieldProcessor> getProcessorByType(TableFieldEnum tableFieldEnum) {
        if (tableFieldProcessorMap.size() == 0) {
            return Lists.newArrayList();
        }
        return tableFieldProcessorMap.get(tableFieldEnum);
    }

    public static void registerProcessor(TableFieldEnum tableFieldEnum, TableFieldProcessor processor) {
        tableFieldProcessorMap.put(tableFieldEnum, processor);
    }

    public TableFieldProviderFactory() {
    }
}
