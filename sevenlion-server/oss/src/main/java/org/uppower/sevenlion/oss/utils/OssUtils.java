package org.uppower.sevenlion.oss.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.uppower.sevenlion.common.utils.SpringApplicationContext;
import org.uppower.sevenlion.oss.exception.OssException;
import org.uppower.sevenlion.oss.model.dto.FileNameDTO;
import org.uppower.sevenlion.oss.model.result.FileInfoResult;
import org.uppower.sevenlion.oss.oss.OssFileMetaData;
import org.uppower.sevenlion.oss.oss.OssTemplate;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/1/2 4:28 下午
 */
@Slf4j
public class OssUtils {
    /**
     * 解析数据库中存储的名称
     *
     * @param databaseName
     * @return
     */
    public static FileNameDTO resolveDatabaseName(String databaseName) throws OssException {
        String[] split = StringUtils.split(databaseName, "-", 3);
        if (split.length != 3) {
            throw new OssException("数据库存储格式错误!");
        }
        FileNameDTO dto = new FileNameDTO();
        dto.setBucketName(split[0]);
        dto.setMd5(split[1]);
        dto.setOriginName(split[2]);
        return dto;
    }

    public static String getUrlFromDatabaseName(String name) {
        if (name != null) {
            OssTemplate template = SpringApplicationContext.getBean(OssTemplate.class);
            if (template == null) {
                log.error("未注册bean!");
                return null;
            }
            String url = null;
            try {
                url = template.getObjectUrlFromDatabaseName(name);
            } catch (Exception e) {
                log.error("获取图片url失败：" + e.getMessage());
            }
            return url;
        } else {
            return null;
        }
    }


    public static FileInfoResult getFileInfoResult(FileNameDTO dto, Boolean isPublic) {
        try {
            OssTemplate template = SpringApplicationContext.getBean(OssTemplate.class);
            if (template == null) {
                log.error("未注册bean!");
                return null;
            }
            String url = template.getObjectURL(dto, isPublic);
            OssFileMetaData objectInfo = template.getObjectInfo(dto);
            return new FileInfoResult(dto.getOriginName(), dto.getDatabaseName(), url, objectInfo.getLength());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static FileInfoResult getFileInfoResult(String databaseName, Boolean isPublic) throws OssException {
        try {
            return getFileInfoResult(resolveDatabaseName(databaseName), isPublic);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static FileInfoResult getFileInfoResult(String databaseName) throws OssException {
        return getFileInfoResult(databaseName, true);
    }

}
