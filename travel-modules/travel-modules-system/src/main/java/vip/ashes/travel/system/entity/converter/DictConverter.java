package vip.ashes.travel.system.entity.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vip.ashes.travel.system.entity.Dict;
import vip.ashes.travel.system.entity.Vo.DictVO;

import java.util.List;

/**
 * @author loveliness
 */
@Mapper(componentModel = "spring")
public interface DictConverter {
    /**
     * 类型转换
     *
     * @param dict dict对象
     * @return dictVO对象
     */
    @Mappings({
            @Mapping(target = "value", source = "dictKey"),
            @Mapping(target = "label", source = "dictValue")
    })
    DictVO dictToVo(Dict dict);

    /**
     * list Dict 转换
     *
     * @param dicts dict对象组
     * @return dictVO对象组
     */
    List<DictVO> dictsToVos(List<Dict> dicts);


}
