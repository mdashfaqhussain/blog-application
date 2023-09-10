package com.example.blog.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;

    private String content;
}
