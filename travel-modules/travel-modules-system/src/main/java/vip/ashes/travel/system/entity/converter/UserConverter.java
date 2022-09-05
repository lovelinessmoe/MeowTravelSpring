package vip.ashes.travel.system.entity.converter;


import org.mapstruct.Mapper;
import vip.ashes.travel.system.entity.User;

/**
 * @author loveliness
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    /**
     * 类型转换
     *
     * @param user user对象
     * @return userVO对象
     */
    //UserVO userToVO(User user);

}
