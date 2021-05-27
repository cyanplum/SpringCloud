package org.uppower.sevenlion.oss.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.uppower.sevenlion.common.utils.SpringApplicationContext;
import org.uppower.sevenlion.oss.common.model.dto.FileNameDTO;
import org.uppower.sevenlion.oss.common.model.result.FileInfoResult;
import org.uppower.sevenlion.oss.common.oss.OssFileMetaData;
import org.uppower.sevenlion.oss.common.oss.OssTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/1/2 6:53 下午
 */
@Slf4j
public class FileUploadUtils {

    private static OssTemplate ossTemplate() {
        return SpringApplicationContext.getBean(OssTemplate.class);
    }

    public static FileInfoResult upload(MultipartFile file, String bucketName) {
        try {
            //实际上传
            FileNameDTO filenameDTO = uploadAndGetFilenameDTO(file, bucketName);
            return getFileInfoResult(filenameDTO, true);
        } catch (Exception e) {
            log.error("上传失败，data:{}", e.getMessage());
        }
        return new FileInfoResult();
    }

    public static FileNameDTO uploadAndGetFilenameDTO(MultipartFile file, String bucketName) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String md5Code = getInputStreamMD5(file.getInputStream());
        ossTemplate().upload(bucketName, md5Code + "-" + originalFilename, file.getInputStream());
        return new FileNameDTO(originalFilename, md5Code, bucketName);
    }

    private static FileInfoResult getFileInfoResult(FileNameDTO dto, Boolean isPublic) {
        String url = ossTemplate().getObjectURL(dto, isPublic);
        OssFileMetaData objectInfo = ossTemplate().getObjectInfo(dto);
        return new FileInfoResult(dto.getOriginName(), dto.getDatabaseName(), url, objectInfo.getLength());
    }

    public static String genFileMd5(File file) throws IOException, NoSuchAlgorithmException {
        String data = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            data = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    /**
     * 获取该输入流的MD5值
     *
     * @param is
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getInputStreamMD5(InputStream is) throws NoSuchAlgorithmException, IOException {
        StringBuffer md5 = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] dataBytes = new byte[1024];

        int nread = 0;
        while ((nread = is.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }
        ;
        byte[] mdbytes = md.digest();

        // convert the byte to hex format
        for (int i = 0; i < mdbytes.length; i++) {
            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return md5.toString();
    }
}
