package vip.ashes.travel.user.entity.converter;

import org.mapstruct.Mapper;
import vip.ashes.travel.user.entity.Tactic;
import vip.ashes.travel.user.entity.TacticDetail;
import vip.ashes.travel.user.entity.Vo.TacticDetailVO;

/**
 * @author loveliness
 */
@Mapper(componentModel = "spring")
public interface TacticConverter {
    /**
     * 前端的添加文章转换成Tactic
     * @param TacticDetailVO
     * @return Tactic
     */
    Tactic tacticDetailVOToTactic(TacticDetailVO TacticDetailVO);

    /**
     * TacticDetail
     * @param TacticDetailVO
     * @return TacticDetail
     */
    TacticDetail tacticDetailVOToTacticDetail(TacticDetailVO TacticDetailVO);
}

