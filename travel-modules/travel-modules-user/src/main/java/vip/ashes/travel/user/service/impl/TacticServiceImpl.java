package vip.ashes.travel.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.user.entity.Tactic;
import vip.ashes.travel.user.entity.Vo.TacticDetailVO;
import vip.ashes.travel.user.mapper.TacticDetailMapper;
import vip.ashes.travel.user.mapper.TacticMapper;
import vip.ashes.travel.user.service.TacticService;

@Service
@AllArgsConstructor
public class TacticServiceImpl extends ServiceImpl<TacticMapper, Tactic> implements TacticService {

    private final TacticMapper tacticMapper;
    private final TacticDetailMapper tacticDetailMapper;

    @Override
    public TacticDetailVO getTacticDetail(String tacticId) {
        return tacticMapper.getTacticDetail(tacticId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result removeTactic(String tacticId) {
        try {
            tacticMapper.deleteById(tacticId);
            tacticDetailMapper.deleteById(tacticId);
        } catch (Exception e) {
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.RCode(false, ResultCode.ARTICLE_NOT_DELET);
        }
        return Result.ok().message("删除成功");
    }

    @Override
    public boolean addCommentNum(String tacticId) {
        return tacticMapper.addCommentNum(tacticId);
    }
}
