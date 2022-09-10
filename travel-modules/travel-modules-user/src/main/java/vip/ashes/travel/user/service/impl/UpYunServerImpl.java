package vip.ashes.travel.user.service.impl;

import com.upyun.RestManager;
import com.upyun.UpException;
import com.upyun.UpYunUtils;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vip.ashes.travel.user.service.UpYunServer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author loveliness
 */
@Service
public class UpYunServerImpl implements UpYunServer {
    @Value("${up-yun.user-name}")
    @Resource
    private String username;
    @Value("${up-yun.password}")
    @Resource
    private String password;
    @Value("${up-yun.bucket}")
    @Resource
    private String bucket;

    private RestManager manager;


    @PostConstruct
    private void init() {
        this.manager = new RestManager(bucket, username, password);
    }


    @Override
    public Response uploadFile(String uri, MultipartFile file) {
        try {
            Map<String, String> params = new HashMap<>(8);
            // 设置待上传文件的 Content-MD5 值
            // 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
            params.put(RestManager.PARAMS.CONTENT_MD5.getValue(), UpYunUtils.md5(file.getBytes()));

            return manager.writeFile(uri, file.getInputStream(), params);
        } catch (IOException | UpException e) {
            e.printStackTrace();
        }
        return null;
    }
}
