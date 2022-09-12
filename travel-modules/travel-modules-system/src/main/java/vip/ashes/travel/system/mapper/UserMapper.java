package vip.ashes.travel.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.travel.system.entity.User;
import vip.ashes.travel.system.entity.Vo.UserLocationVo;

import java.util.List;

/**
 * @author loveliness
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<UserLocationVo> getUserLocation();
}
