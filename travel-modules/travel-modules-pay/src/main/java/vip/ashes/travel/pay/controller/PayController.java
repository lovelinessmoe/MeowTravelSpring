package vip.ashes.travel.pay.controller;

import com.alipay.easysdk.factory.Factory;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.pay.entity.Sponsor;
import vip.ashes.travel.pay.service.impl.SponsorService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/pay")
@AllArgsConstructor
public class PayController {
    private final SponsorService sponsorService;

    @SneakyThrows
    @PostMapping("paySponsor")
    public Result paySponsor(@RequestBody Sponsor sponsor) {
        return Result.ok().data(sponsorService.paySponsor(sponsor));
    }

    @SneakyThrows
    @PostMapping("asyncNotify")
    public void asyncNotify(HttpServletRequest request) {
        System.out.println("进入异步");

        // 解析异步请求的参数
        Map<String, String> params = new HashMap<>(21);
        Enumeration<?> temp = request.getParameterNames();
        while (temp.hasMoreElements()) {
            String en = (String) temp.nextElement();
            String value = request.getParameter(en);
            params.put(en, value);
        }
        // 验签
        boolean signVerified = Factory.Payment.Common().verifyNotify(params);

        if (signVerified) {
            UpdateWrapper<Sponsor> sponsorUpdateWrapper = new UpdateWrapper<>();
            UpdateWrapper<Sponsor> eq = sponsorUpdateWrapper.set(Sponsor.COL_TYPE, 1)
                    .eq(Sponsor.COL_SPONSOR_ID, params.get("out_trade_no"));
            sponsorService.update(eq);
        }
    }

    @GetMapping("getPayState")
    public Result getPayState(String sponsorId) {
        Sponsor byId = sponsorService.getById(sponsorId);
        return Result.ok().data(byId.getType());
    }
}
