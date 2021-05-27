package org.uppower.sevenlion.oss.common.oss;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/1/2 4:26 下午
 */
@Data
@AllArgsConstructor
public class OssFileMetaData {

    private String bucketName;

    private String name;

    private Date createdTime;

    private long length;

    private String etag;

    private String contentType;
}
