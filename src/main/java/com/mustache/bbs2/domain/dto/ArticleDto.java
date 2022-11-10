package com.mustache.bbs2.domain.dto;

import com.mustache.bbs2.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    public ArticleDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(this.id, this.title, this.content);
    }
}
