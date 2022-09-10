package vip.ashes.travel.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.system.entity.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loveliness
 */
public interface CommentService extends IService<Comment> {


    /**
     * 通过文章id返回评论
     *
     * @param articleId 文章id
     * @return 评论列表
     */
    List<Tree<String>> getCommentByTacticId(String articleId);

    /**
     * 更新评论为已删除
     *
     * @param commentId 评论id
     * @return Result
     */
    Result removeCommentById(String commentId);

    /**
     * 批量更新为已删除
     *
     * @param commentList 评论list
     * @return Result
     */
    Result removeManyComment(ArrayList<Comment> commentList);
}
