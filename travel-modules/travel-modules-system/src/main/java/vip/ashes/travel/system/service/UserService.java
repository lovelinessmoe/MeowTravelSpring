package vip.ashes.travel.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.system.entity.User;
import vip.ashes.travel.system.entity.Vo.UserLocationVo;

import java.util.List;

/**
 * @author loveliness
 */
public interface UserService extends IService<User> {

    /**
     * 获取用户的位置省和人数
     *
     * @return
     */
    List<UserLocationVo> getUserLocation();
}
