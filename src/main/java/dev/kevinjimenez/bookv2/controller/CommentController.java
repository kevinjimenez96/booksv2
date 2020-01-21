package dev.kevinjimenez.bookv2.controller;

import dev.kevinjimenez.bookv2.model.Comment;
import dev.kevinjimenez.bookv2.model.CommentId;
import dev.kevinjimenez.bookv2.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getComments(){
        return this.commentService.find();
    }

    @GetMapping("/{book}/{user}")
    public Comment getComments(@PathVariable int book, @PathVariable String user){
        CommentId commentId = new CommentId();
        commentId.setBookISNB(book);
        commentId.setUserEmail(user);
        return this.commentService.findById(commentId);
    }

    @PostMapping
    public Comment postComment(@RequestBody Comment comment){
        this.commentService.insert(comment);
        return comment;
    }

    @PutMapping
    public Comment putComment(@RequestBody Comment comment){
        this.commentService.update(comment);
        return comment;
    }

    @DeleteMapping("/{book}/{user}")
    public void deleteComment(@PathVariable int book, @PathVariable String user){
        CommentId commentId = new CommentId();
        commentId.setBookISNB(book);
        commentId.setUserEmail(user);
        this.commentService.delete(commentId);
    }
}
