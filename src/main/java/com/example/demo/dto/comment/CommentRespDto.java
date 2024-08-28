package com.example.demo.dto.comment;

import lombok.*;

public class CommentRespDto {
    @AllArgsConstructor
    @Setter
    @Getter
    public static class AllCommentsRespDto {
        private Long id; // 댓글의 id
        private Long articleId; // 댓글의 부모 id
        private String nickname; // 댓글 작성자
        private String body; // 댓글 본문
    }
    @AllArgsConstructor
    @Setter
    @Getter
    public static class CommentsRespDto {
        private Long id;
        private Long articleId;
        private String nickname;
        private String body;
    }
    @AllArgsConstructor
    @Setter
    @Getter
    public static class CreateRespDto {
        private Long id;
        private Long articleId;
        private String nickname;
        private String body;
    }
    @AllArgsConstructor
    @Setter
    @Getter
    public static class UpdateRespDto {
        private Long id;
        private Long articleId;
        private String nickname;
        private String body;
    }
    @AllArgsConstructor
    @Setter
    @Getter
    public static class DeleteRespDto {
        private Long id;
        private Long articleId;
        private String nickname;
        private String body;
    }
}
