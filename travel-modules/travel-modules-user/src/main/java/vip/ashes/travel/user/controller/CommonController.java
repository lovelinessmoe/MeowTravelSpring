package vip.ashes.travel.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.entity.Comment;
import vip.ashes.travel.user.service.CommentService;
import vip.ashes.travel.user.utils.LoginUserUtil;

/**
 * 评论
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommonController {
    private final CommentService commentService;
    private final LoginUserUtil loginUserUtil;


    @GetMapping("/getComment/{tacticId}")
    public Result getCommentByArticleId(@PathVariable String tacticId) {
        return Result.ok().data(commentService.getCommentByTacticId(tacticId));
    }

    /**
     *
     * @param comment 用户发表的评论
     * @return
     */
    @PostMapping("/addComment")
    public Result addComment(@RequestBody Comment comment) {
        String userId = loginUserUtil.getCurrentUser().getUserId();
        comment.setUserId(userId);
        boolean save = commentService.save(comment);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}
