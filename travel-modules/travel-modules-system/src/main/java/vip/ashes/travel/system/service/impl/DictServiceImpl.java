package vip.ashes.travel.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.travel.system.entity.Dict;
import vip.ashes.travel.system.entity.Vo.DictVO;
import vip.ashes.travel.system.entity.converter.DictConverter;
import vip.ashes.travel.system.mapper.DictMapper;
import vip.ashes.travel.system.service.DictService;

import java.util.List;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    private final DictMapper dictMapper;
    private final DictConverter dictConverter;

    @Override
    public List<DictVO> getDictByCode(String code) {
        QueryWrapper<Dict> codeEq = new QueryWrapper<Dict>().eq(Dict.COL_CODE, code);
        List<Dict> dicts = dictMapper.selectList(codeEq);
        return dictConverter.dictsToVos(dicts);
    }
}
