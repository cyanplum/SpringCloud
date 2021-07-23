package org.uppower.sevenlion.oss.model.result;


import lombok.Data;

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
@Data
public class FileInfoResult {

    private String fileName;

    private String storeName;

    private String url;

    private Long size;

    public FileInfoResult() {
    }

    public FileInfoResult(String fileName, String storeName, String url, Long size) {
        this.fileName = fileName;
        this.storeName = storeName;
        this.url = url;
        this.size = size;
    }
}
