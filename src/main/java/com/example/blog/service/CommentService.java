package com.example.blog.service;

import com.example.blog.dto.CommentDto;

import java.util.List;

public interface CommentService {

   public CommentDto createComment(Long postID, CommentDto commentDto);
   List<CommentDto> getAllCommentsByPostID(long postID);

   CommentDto getCommendById(long postID, long commentID);

   CommentDto updateComment(long postID, long commentID, CommentDto commentDto) ;

   void deleteCommentByID(long postID, long commentID);
}
