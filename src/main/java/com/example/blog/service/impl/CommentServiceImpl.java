package com.example.blog.service.impl;

import com.example.blog.dto.CommentDto;
import com.example.blog.exception.BlogAPIException;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private static Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private CommentRepository commentRepository;

    private PostRepository postRepository;



    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(Long postID, CommentDto commentDto) {
        //CommentDTO to Comment Model Class
        Comment comment = mapToEntity(commentDto);

        //retrieve post by id
        logger.info( "Check the value"+ postRepository.findById(comment.getId()));
        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postID));

        //set post to comment entity
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostID(long postID) {
        //retrieve comment by post id
        List<Comment> comments = commentRepository.findByPostId(postID);

        //convert the list of comment entities to list of comment dto's
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommendById(long postID, long commentID) {
        // retrieve post by id
        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postID));

        Comment comment = commentRepository.findById(commentID).orElseThrow(() -> new ResourceNotFoundException("comment","id",commentID));


    if(!comment.getPost().getId().equals(post.getId())){
        throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to post");
    }

        return mapToDTO(comment);

    }

    @Override
    public CommentDto updateComment(long postID, long commentID, CommentDto commentDto) {
        // retrieve post by id
        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postID));
        //retrieve the comment by comment id
        Comment comment = commentRepository.findById(commentID).orElseThrow(() -> new ResourceNotFoundException("comment","id",commentID));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to post");
        }

        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        Comment commentUpdated = commentRepository.save(comment);

        return mapToDTO(commentUpdated);

    }

    @Override
    public void deleteCommentByID(long postID, long commentID) {

        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postID));
        //retrieve the comment by comment id
        Comment comment = commentRepository.findById(commentID).orElseThrow(() -> new ResourceNotFoundException("comment","id",commentID));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to post");
        }

        commentRepository.delete(comment);

    }


    private CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        commentDto.setId(comment.getId());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {

        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setId(commentDto.getId());

        return comment;
    }
}
