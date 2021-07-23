package org.uppower.sevenlion.oss.model.dto;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/1/2 4:24 下午
 */
public class FileNameDTO {

    /**
     * 文件原始名称
     */
    private String originName;

    /**
     * 文件md5
     */
    private String md5;

    /**
     * 文件bucket名称
     */
    private String bucketName;

    public String getS3Name() {
        return md5 + "-" + originName;
    }

    /**
     * 存到数据库中的名称,因为需要查找bucket,所以要带上bucket前缀
     *
     * @return
     */
    public String getDatabaseName() {
        return bucketName + "-" + getS3Name();
    }

    public FileNameDTO() {
    }

    public FileNameDTO(String originName, String md5, String bucketName) {
        this.originName = originName;
        this.md5 = md5;
        this.bucketName = bucketName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
