package vip.ashes.travel.system.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.system.entity.Comment;
import vip.ashes.travel.system.service.CommentService;

import java.util.ArrayList;


/**
 * @author loveliness
 */
@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 获取某个文章的评论
     * @param tacticId 文章id
     * @return 评论
     */
    @GetMapping("/getComment/{tacticId}")
    public Result getCommentByTacticId(@PathVariable String tacticId) {
        return Result.ok().data(commentService.getCommentByTacticId(tacticId));
    }

    /**
     * 删除评论
     * @param commentId 评论id
     * @return 成功与否
     */
    @GetMapping("/remove/{commentId}")
    public Result remove(@PathVariable String commentId) {
        return Result.ok().data(commentService.removeCommentById(commentId));
    }


    /**
     * 删除多条评论
     */
    @PostMapping("/removeMany")
    public Result removeMany(@RequestBody ArrayList<Comment> commentList) {
        return commentService.removeManyComment(commentList);
    }
}
