package vip.ashes.travel.user.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.travel.user.entity.Comment;
import vip.ashes.travel.user.entity.Vo.CommentVo;
import vip.ashes.travel.user.mapper.CommentMapper;
import vip.ashes.travel.user.service.CommentService;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public List<Tree<String>> getCommentByTacticId(String tacticId) {
        List<CommentVo> comments = commentMapper.getCommentByTacticId(tacticId);

        //转换成树
        // 构建node列表

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名
        treeNodeConfig.setIdKey("commentId");
        treeNodeConfig.setParentIdKey("pId");
        treeNodeConfig.setNameKey("userName");
        // 最大递归深度
        treeNodeConfig.setDeep(2);

        //转换器
        return TreeUtil.build(comments, "0", treeNodeConfig,
                (comment, tree) -> {
                    tree.setId(comment.getCommentId());
                    tree.setParentId(comment.getPId());
                    tree.setName(comment.getUserName());
                    tree.putExtra("userId", comment.getUserId());
                    tree.putExtra("content", comment.getContent());
                    tree.putExtra("createTime", comment.getCreateTime());
                    tree.putExtra("avatarUrl", comment.getAvatarUrl());
                });
    }
}
