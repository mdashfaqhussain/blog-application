package com.example.blog.service;

import com.example.blog.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts(int pageNo, int pageSize);



    PostDto getPostByID(long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePost(Long id);
}
