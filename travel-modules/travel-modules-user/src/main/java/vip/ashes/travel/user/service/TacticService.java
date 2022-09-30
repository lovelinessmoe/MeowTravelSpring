package vip.ashes.travel.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.entity.Tactic;
import vip.ashes.travel.user.entity.Vo.TacticDetailVo;

public interface TacticService extends IService<Tactic> {


    /**
     * 获取文章细节
     *
     * @param tacticId 文章id
     * @return 文章对象的文章detail对象的相同
     */
    TacticDetailVo getTacticDetail(String tacticId);

    /**
     * 删除博客文章
     *
     * @param tacticId 文章id
     * @return 是否成功
     */
    Result removeTactic(String tacticId);

    boolean addCommentNum(String tacticId);
}
