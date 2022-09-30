package vip.ashes.travel.pay.service.impl;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.travel.pay.entity.Sponsor;
import vip.ashes.travel.pay.entity.User;
import vip.ashes.travel.pay.mapper.SponsorMapper;
import vip.ashes.travel.pay.utils.LoginUserUtil;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class SponsorServiceImpl extends ServiceImpl<SponsorMapper, Sponsor> implements SponsorService {
    private final LoginUserUtil loginUserUtil;
    private final SponsorMapper sponsorMapper;

    @Override
    public HashMap<String, String> paySponsor(Sponsor sponsor) throws Exception {
        User currentUser = loginUserUtil.getCurrentUser();
        // 未支付
        sponsor.setType(false);
        sponsor.setUserId(currentUser.getUserId());

        int save = sponsorMapper.insert(sponsor);

        String subject = "赞助攻略:" + sponsor.getTacticId();

        //0：订单码-简约前置模式，对应 iframe 宽度不能小于600px，高度不能小于300px；
        //1：订单码-前置模式，对应iframe 宽度不能小于 300px，高度不能小于600px；
        //3：订单码-迷你前置模式，对应 iframe 宽度不能小于 75px，高度不能小于75px；
        //4：订单码-可定义宽度的嵌入式二维码，商户可根据需要设定二维码的大小。
        AlipayTradePagePayResponse pay =
                Factory.Payment.Page().optional("qr_pay_mode", "3")
                        .pay(subject, sponsor.getSponsorId(), sponsor.getMoney() + "", null);

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("pay",pay.getBody());
        stringStringHashMap.put("sponsorId",sponsor.getSponsorId());
        return stringStringHashMap;
    }
}
