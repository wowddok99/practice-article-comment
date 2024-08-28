package com.example.demo.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ArticleRespDto {
    @AllArgsConstructor
    @Setter
    @Getter
    public static class IndexRespDto {
        private Long id;
        private String title;
        private String content;
    }
    @AllArgsConstructor
    @Setter
    @Getter
    public static class ShowRespDto {
        private Long id;
        private String title;
        private String content;
    }
    @AllArgsConstructor
    @Setter
    @Getter
    public static class CreateRespDto {
        private Long id;
        private String title;
        private String content;
    }
    @AllArgsConstructor
    @Setter
    @Getter
    public static class UpdateRespDto {
        private Long id;
        private String title;
        private String content;
    }
    @AllArgsConstructor
    @Setter
    @Getter
    public static class DeleteRespDto {
        private Long id;
        private String title;
        private String content;
    }
}
