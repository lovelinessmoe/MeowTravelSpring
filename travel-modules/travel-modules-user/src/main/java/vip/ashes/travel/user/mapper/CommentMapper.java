package vip.ashes.travel.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.travel.user.entity.Comment;
import vip.ashes.travel.user.entity.Vo.CommentVo;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 获取文章评论
     *
     * @param tacticId 文章id
     * @return 文章VOList
     */
    List<CommentVo> getCommentByTacticId(String tacticId);
}
