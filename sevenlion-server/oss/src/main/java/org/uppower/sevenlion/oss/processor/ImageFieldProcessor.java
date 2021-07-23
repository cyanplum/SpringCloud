package org.uppower.sevenlion.oss.processor;

import cn.sevenlion.utils.enums.ColumnFieldEnum;
import cn.sevenlion.utils.processor.TableFieldProcessor;
import cn.sevenlion.utils.provider.TableFieldProviderFactory;
import org.springframework.beans.factory.InitializingBean;
import org.uppower.sevenlion.oss.utils.OssUtils;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/21 4:39 下午
 */
public class ImageFieldProcessor implements TableFieldProcessor, InitializingBean {
    @Override
    public ColumnFieldEnum getFieldType() {
        return ColumnFieldEnum.IMAGE;
    }

    @Override
    public Object serialize(Object target) {
        if (target instanceof String) {
            return OssUtils.getFileInfoResult(target.toString());
        }
        return null;
    }

    @Override
    public Object deSerialize(Object target) {
        if (target instanceof String) {
            return OssUtils.getFileInfoResult(target.toString());
        }
        return null;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        TableFieldProviderFactory.registerProcessor(this.getFieldType(),this);
    }
}
