package vip.ashes.travel.user.controller;

import lombok.AllArgsConstructor;
import okhttp3.Response;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.service.UpYunServer;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/upYun")
@AllArgsConstructor
@RefreshScope
public class UpYunController {

    private final UpYunServer upYunServer;

    /**
     * 又拍云上传图片
     *
     * @param uri  文件路径
     * @param file 文件
     * @return 成功与否
     */
    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestPart String uri,
                             @RequestPart MultipartFile file) {
        Response response = upYunServer.uploadFile(uri, file);
        int successCode = 200;
        String baseImgUrl = "https://oss.ashes.vip";
        if (response.code() == successCode) {
            return Result.ok().message("上传成功").data(baseImgUrl + uri);
        } else {
            return Result.error().message("上传失败");
        }
    }
}
