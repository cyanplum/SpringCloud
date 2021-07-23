package org.uppower.sevenlion.oss.oss;



import org.uppower.sevenlion.oss.model.dto.FileNameDTO;

import java.io.File;
import java.io.InputStream;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/1/2 4:23 下午
 */
public interface OssTemplate {

    /**
     * 上传文件
     * @param bucketName  bucket名称
     * @param file        文件
     * @param stream      文件流
     * @param contentType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void upload(String bucketName, File file, InputStream stream, String contentType);


    /**
     * 上传文件
     * @param bucketName  bucket名称
     * @param file        文件
     * @param bytes       文件字节数组
     * @param contentType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    void upload(String bucketName, File file, byte[] bytes, String contentType);

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    void upload(String bucketName, String objectName, InputStream stream);


    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    InputStream getObject(String bucketName, String objectName);

    /**
     * 获取文件信息
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    OssFileMetaData getObjectInfo(String bucketName, String objectName);

    /**
     * 获取文件信息
     * @param filenameDTO
     * @return
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    OssFileMetaData getObjectInfo(FileNameDTO filenameDTO);

    /**
     * 获取文件信息
     * @param fileName 文件名称
     * @return
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    OssFileMetaData getObjectInfo(String fileName);


    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    void removeObject(String bucketName, String objectName);

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=
     * @return url
     */
    String getObjectURL(String bucketName, String objectName, Integer expires);

    /**
     * 获取文件外链
     *
     * @param dto      文件名对象
     * @param isPublic 是否公开bucket
     * @param expires  过期时间 <=7
     * @return url
     */
    String getObjectURL(FileNameDTO dto, Boolean isPublic, Integer expires);


    /**
     * 获取文件外链
     * @param dto
     * @param isPublic
     * @return url
     */
    String getObjectURL(FileNameDTO dto, Boolean isPublic);


    /**
     * 从数据库中存储的名称中获得文件链接
     * @param databaseName
     * @return
     */
    String getObjectUrlFromDatabaseName(String databaseName);

    /**
     * 从数据库中存储的名称中获得文件链接
     * @param databaseName
     * @param expire
     * @return
     */
    String getObjectUrlFromDatabaseName(String databaseName, Integer expire);
}
