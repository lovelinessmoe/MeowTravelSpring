package vip.ashes.travel.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.travel.system.entity.TacticDetail;
import vip.ashes.travel.system.entity.Vo.TacticDetailVO;

@Mapper
public interface TacticDetailMapper extends BaseMapper<TacticDetail> {
    /**
     * 通过id获取文章
     *
     * @param tacticId 文章ID
     * @return 文章对象的文章detail对象的相同
     */
    TacticDetailVO getTacticDetail(String tacticId);
}
