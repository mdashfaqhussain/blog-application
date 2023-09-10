package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.dto.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize);



    PostDto getPostByID(long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePost(Long id);
}
