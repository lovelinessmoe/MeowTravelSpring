package vip.ashes.travel.pay.service.impl;

import vip.ashes.travel.pay.entity.Sponsor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

public interface SponsorService extends IService<Sponsor>{


    HashMap<String, String> paySponsor(Sponsor sponsor) throws Exception;
}
