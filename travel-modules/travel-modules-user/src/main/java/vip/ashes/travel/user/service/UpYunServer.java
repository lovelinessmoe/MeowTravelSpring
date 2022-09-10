package vip.ashes.travel.user.service;


import okhttp3.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author loveliness
 */
public interface UpYunServer {
    /**
     * 上传文件到又拍云
     *
     * @param uri
     * @param file
     * @return
     */
    Response uploadFile(String uri, MultipartFile file);
}
