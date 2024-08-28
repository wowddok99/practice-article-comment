package com.example.demo.dto.article;

import com.example.demo.entity.Article;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ArticleReqDto {
    @AllArgsConstructor
    @Setter
    @Getter
    public static class CreateReqDto {
        @NotNull
        @Size(max = 50)
        private String title;
        @NotNull
        @Size(max = 200)
        private String content;
        public Article toEntity() {
            return new Article(null, title, content);
        }
    }
    @AllArgsConstructor
    @Setter
    @Getter
    public static class UpdateReqDto {
        @NotNull
        private Long id;
        @NotNull
        @Size(max = 50)
        private String title;
        @NotNull
        @Size(max = 200)
        private String content;
        public Article toEntity() {
            return new Article(id, title, content);
        }
    }
}
