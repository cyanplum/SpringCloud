package org.uppower.sevenlion.oss.minio;

import cn.hutool.core.util.StrUtil;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.uppower.sevenlion.oss.model.dto.FileNameDTO;
import org.uppower.sevenlion.oss.oss.OssFileMetaData;
import org.uppower.sevenlion.oss.oss.OssTemplate;
import org.uppower.sevenlion.oss.utils.OssUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/1/2 4:39 下午
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class MinioTemplate implements OssTemplate {

    private MinioClient minioClient;




    /**
     * @param bucketName  bucket名称
     * @param file        文件
     * @param stream      文件流
     * @param contentType 类型
     * @ https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    @Override
    @SneakyThrows
    public void upload(String bucketName, File file, InputStream stream, String contentType) {
        minioClient.putObject(bucketName, file.getName(), stream, file.length(), contentType);
    }

    /**
     * @param bucketName  bucket名称
     * @param file        文件
     * @param bytes       文件字节数组
     * @param contentType 类型
     * @ https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    @Override
    @SneakyThrows
    public void upload(String bucketName, File file, byte[] bytes, String contentType) {
        minioClient.putObject(bucketName, file.getName(), new ByteArrayInputStream(bytes), contentType);
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @ https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    @Override
    @SneakyThrows
    public void upload(String bucketName, String objectName, InputStream stream) {
        minioClient.putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
    }


    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    @Override
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        return minioClient.getObject(bucketName, objectName);
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @ https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    @Override
    @SneakyThrows
    public OssFileMetaData getObjectInfo(String bucketName, String objectName) {
        ObjectStat objectStat = minioClient.statObject(bucketName, objectName);
        return new OssFileMetaData(
                objectStat.bucketName(),
                objectStat.name(),
                objectStat.createdTime(),
                objectStat.length(),
                objectStat.etag(),
                objectStat.contentType()
        );
    }

    /**
     * 获取文件信息
     *
     * @ https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    @Override
    @SneakyThrows
    public OssFileMetaData getObjectInfo(FileNameDTO FileNameDTO) {
        ObjectStat objectStat = minioClient.statObject(FileNameDTO.getBucketName(), FileNameDTO.getS3Name());
        return new OssFileMetaData(
                objectStat.bucketName(),
                objectStat.name(),
                objectStat.createdTime(),
                objectStat.length(),
                objectStat.etag(),
                objectStat.contentType()
        );
    }


    /**
     * 获取文件信息
     *
     * @ https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    @Override
    public OssFileMetaData getObjectInfo(String fileName) {
        String[] split = fileName.split(StrUtil.DASHED, 2);
        try {
            ObjectStat objectStat = minioClient.statObject(split[0], split[1]);
            return new OssFileMetaData(
                    objectStat.bucketName(),
                    objectStat.name(),
                    objectStat.createdTime(),
                    objectStat.length(),
                    objectStat.etag(),
                    objectStat.contentType()
            );
        } catch (Exception ignored) {
            log.error("文件信息获取失败! {}", ignored.getMessage());
        }
        return null;
    }


    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @ https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    @Override
    @SneakyThrows
    public void removeObject(String bucketName, String objectName) {
        minioClient.removeObject(bucketName, objectName);
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=
     * @return url
     */
    @Override
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
        return minioClient.presignedGetObject(bucketName, objectName, expires);
    }

    /**
     * 获取文件外链
     *
     * @param dto      文件名对象
     * @param isPublic 是否公开bucket
     * @param expires  过期时间 <=7
     * @return url
     */
    @Override
    @SneakyThrows
    public String getObjectURL(FileNameDTO dto, Boolean isPublic, Integer expires) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("response-content-disposition", "attachment;filename=" + dto.getOriginName());
        if (isPublic) {
            return minioClient.getObjectUrl(dto.getBucketName(), dto.getS3Name());
        } else {
            return minioClient.presignedGetObject(dto.getBucketName(), dto.getS3Name(), expires, params);
        }
    }

    /**
     * 获取文件外链
     *
     * @return url
     */
    @Override
    @SneakyThrows
    public String getObjectURL(FileNameDTO dto, Boolean isPublic) {
        return getObjectURL(dto, isPublic, 24 * 60 * 60);
    }


    /**
     * 从数据库中存储的名称中获得文件链接
     *
     * @return
     */
    @Override
    @SneakyThrows
    public String getObjectUrlFromDatabaseName(String databaseName) {
        return getObjectUrlFromDatabaseName(databaseName, null);
    }

    /**
     * 从数据库中存储的名称中获得文件链接
     *
     * @return
     */
    @Override
    @SneakyThrows
    public String getObjectUrlFromDatabaseName(String databaseName, Integer expire) {
        FileNameDTO dto = OssUtils.resolveDatabaseName(databaseName);
        //todo 暂时还不区分bucket
        if (expire != null) {
            return getObjectURL(dto, true, expire);
        } else {
            return getObjectURL(dto, true);
        }
    }
}
