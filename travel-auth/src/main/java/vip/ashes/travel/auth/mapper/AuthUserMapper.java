package vip.ashes.travel.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.travel.auth.entity.User;

@Mapper
public interface AuthUserMapper extends BaseMapper<User> {
}
