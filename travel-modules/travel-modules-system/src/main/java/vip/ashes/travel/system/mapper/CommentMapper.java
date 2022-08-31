package vip.ashes.travel.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.travel.system.entity.Comment;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}