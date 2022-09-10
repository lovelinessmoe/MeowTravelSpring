package vip.ashes.travel.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.system.entity.Tactic;
import vip.ashes.travel.system.entity.TacticDetail;
import vip.ashes.travel.system.entity.Vo.TacticDetailVO;
import vip.ashes.travel.system.entity.converter.TacticConverter;
import vip.ashes.travel.system.mapper.TacticDetailMapper;
import vip.ashes.travel.system.mapper.TacticMapper;
import vip.ashes.travel.system.service.TacticService;

import java.util.List;

@Service
@AllArgsConstructor
public class TacticServiceImpl extends ServiceImpl<TacticMapper, Tactic> implements TacticService {
    private final TacticConverter tacticConverter;
    private final TacticMapper tacticMapper;
    private final TacticDetailMapper tacticDetailMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveOrUpdateTactic(TacticDetailVO tacticVO) {
        try {
            //查询是否存在该文章
            QueryWrapper<Tactic> queryWrapper = new QueryWrapper<Tactic>().eq(Tactic.COL_TACTIC_ID, tacticVO.getTacticId());
            Long count = tacticMapper.selectCount(queryWrapper);
            //VO转换为实体
            Tactic tactic = tacticConverter.tacticDetailVOToTactic(tacticVO);
            TacticDetail tacticDetail = tacticConverter.tacticDetailVOToTacticDetail(tacticVO);

            if (count == 0) {
                tacticMapper.insert(tactic);
                tacticDetailMapper.insert(tacticDetail);
            } else {
                tacticMapper.updateById(tactic);
                tacticDetailMapper.updateById(tacticDetail);
            }
        } catch (Exception e) {
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.RCode(false, ResultCode.ARTICLE_NOT_MODIFY);
        }
        return Result.ok();
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
        return Result.ok();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result removeMany(List<Tactic> tacticList) {
        try {
            tacticMapper.deleteBatchIds(tacticList);
            tacticDetailMapper.deleteBatchIds(tacticList);
        } catch (Exception e) {
            return Result.RCode(false, ResultCode.ARTICLE_NOT_DELET);
        }
        return Result.ok();
    }

    @Override
    public TacticDetailVO getTacticDetail(String tacticId) {
        return tacticDetailMapper.getTacticDetail(tacticId);
    }
}
