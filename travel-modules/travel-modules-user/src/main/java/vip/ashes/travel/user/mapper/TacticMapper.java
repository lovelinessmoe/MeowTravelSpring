package vip.ashes.travel.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.travel.user.entity.Tactic;import vip.ashes.travel.user.entity.Vo.TacticDetailVo;

/**
 * @author loveliness
 */
@Mapper
public interface TacticMapper extends BaseMapper<Tactic> {
    /**
     * 通过id获取文章
     *
     * @param tacticId 文章ID
     * @return 文章对象的文章detail对象的相同
     */
    TacticDetailVo getTacticDetail(String tacticId);

    boolean addCommentNum(String tacticId);
}
