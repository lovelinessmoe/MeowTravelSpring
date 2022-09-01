package vip.ashes.travel.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.ashes.travel.user.entity.Comment;
import vip.ashes.travel.user.mapper.CommentMapper;
import vip.ashes.travel.user.service.CommentService;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
