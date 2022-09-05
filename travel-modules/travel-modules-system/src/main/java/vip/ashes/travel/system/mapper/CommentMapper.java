package vip.ashes.travel.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.travel.system.entity.Comment;
import vip.ashes.travel.system.entity.Vo.CommentVO;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 获取文章评论
     *
     * @param tactic_id 攻略id
     * @return 文章VOList
     */
    List<CommentVO> getCommentByTacticId(String tactic_id);

}
