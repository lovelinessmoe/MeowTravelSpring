package vip.ashes.travel.user.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.entity.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {

    /**
     * 通过文章id返回评论
     *
     * @param tacticId 文章id
     * @return 评论列表
     */
    List<Tree<String>> getCommentByTacticId(String tacticId);
}
