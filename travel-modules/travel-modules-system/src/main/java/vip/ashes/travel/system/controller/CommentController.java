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

    @GetMapping("/getComment/{articleId}")
    public Result getCommentByArticleId(@PathVariable String articleId) {
        return Result.ok().data(commentService.getCommentByArticleId(articleId));
    }

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
