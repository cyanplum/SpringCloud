package org.uppower.sevenlion.oss.exception;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/1/22 5:08 下午
 */
public class OssException extends RuntimeException {

    private static final long serialVersionUID = 4164685837530017917L;

    public OssException(String message) {
        super(message);
    }
}
