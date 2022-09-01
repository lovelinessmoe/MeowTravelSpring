package vip.ashes.travel.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.ashes.travel.system.entity.Comment;
import vip.ashes.travel.system.mapper.CommentMapper;
import vip.ashes.travel.system.service.CommentService;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
