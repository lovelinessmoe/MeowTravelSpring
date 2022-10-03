package vip.ashes.travel.system.entity.converter;

import org.mapstruct.Mapper;
import vip.ashes.travel.system.entity.Tactic;
import vip.ashes.travel.system.entity.TacticDetail;
import vip.ashes.travel.system.entity.Vo.TacticDetailVO;

/**
 * @author loveliness
 */
@Mapper(componentModel = "spring")
public interface TacticConverter {
    /**
     * 前端的添加文章转换成Tactic
     *
     * @param TacticDetailVO
     * @return Tactic
     */
    Tactic tacticDetailVOToTactic(TacticDetailVO TacticDetailVO);

    /**
     * TacticDetail
     *
     * @param TacticDetailVO
     * @return TacticDetail
     */
    TacticDetail tacticDetailVOToTacticDetail(TacticDetailVO TacticDetailVO);
}

