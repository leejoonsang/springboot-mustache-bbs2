package com.mustache.bbs2.domain.entity;

import com.mustache.bbs2.domain.dto.ArticleDto;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name="article2")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    public static ArticleDto of(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }
}
