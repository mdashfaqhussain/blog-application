package com.example.blog.controller;

import com.example.blog.dto.CommentDto;
import com.example.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postID}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postID") Long postID,
                                                    @RequestBody  CommentDto commentDto){

        CommentDto newComment = commentService.createComment(postID, commentDto);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postID}/comments")
    public List<CommentDto> getCommentsByPostID(@PathVariable(name = "postID") long postID){
        return commentService.getAllCommentsByPostID(postID);
    }

    @GetMapping("/posts/{postID}/comments/{commentID}")
    public ResponseEntity<CommentDto> getCommentByID(@PathVariable(name = "postID") Long postID,
                                                     @PathVariable(name = "commentID") Long commentID){
        CommentDto commentDTO = commentService.getCommendById(postID, commentID);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PutMapping("/posts/{postID}/comments/{commentID}")
    public ResponseEntity<CommentDto> updatedComment(@PathVariable(name = "postID") long postID,
                                                     @PathVariable(name="commentID") long commentID,
                                                     @RequestBody CommentDto commentDto){
        CommentDto comment = commentService.updateComment(postID, commentID, commentDto);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{postID}/comments/{commentID}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(name = "postID") long postID,
            @PathVariable(name = "commentID") long commentID
    )
    {
        commentService.deleteCommentByID(postID,commentID);
        return new ResponseEntity<>("Comment Deleted Successfully", HttpStatus.OK);
    }
}
