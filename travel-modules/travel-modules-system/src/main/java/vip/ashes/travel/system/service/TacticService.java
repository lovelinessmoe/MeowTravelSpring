package vip.ashes.travel.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.system.entity.Tactic;
import vip.ashes.travel.system.entity.Vo.TacticDetailVO;

import java.util.List;

/**
 * @author loveliness
 */
public interface TacticService extends IService<Tactic> {
    /**
     * 如果存在则更新
     * 不存在则插入
     *
     * @param TacticVO 博客文章的所有信息
     * @return 是否成功
     */
    Result saveOrUpdateTactic(TacticDetailVO TacticVO);

    /**
     * 删除博客文章
     *
     * @param TacticId 文章id
     * @return 是否成功
     */
    Result removeTactic(String TacticId);


    /**
     * 删除多个文章
     *
     * @param TacticList 文章列表
     * @return Result
     */
    Result removeMany(List<Tactic> TacticList);

    /**
     * 获取文章细节
     *
     * @param tacticId 文章id
     * @return 文章对象的文章detail对象的相同
     */
    TacticDetailVO getTacticDetail(String tacticId);
}
