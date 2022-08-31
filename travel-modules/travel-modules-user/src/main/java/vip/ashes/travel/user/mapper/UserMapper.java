package vip.ashes.travel.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.travel.user.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
