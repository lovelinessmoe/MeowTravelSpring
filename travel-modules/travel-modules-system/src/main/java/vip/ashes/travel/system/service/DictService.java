package vip.ashes.travel.system.service;

import vip.ashes.travel.system.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.system.entity.Vo.DictVO;

import java.util.List;

/**
 * @author loveliness
 */
public interface DictService extends IService<Dict>{
    /**
     * 通过code来获取字典值
     * @param code 查询位置
     * @return 字典集合VO
     */
    List<DictVO> getDictByCode(String code);

}
