package com.example.demo.dto.comment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class CommentReqDto {
    @AllArgsConstructor
    @Setter
    @Getter
    public static class CreateReqDto {
        @NotNull
        private Long articleId; // 댓글의 부모 id
        @NotNull
        @Size(max = 15)
        private String nickname; // 댓글 작성자
        @NotNull
        @Size(max = 500)
        private String body; // 댓글 본문
    }
    @AllArgsConstructor
    @Setter
    @Getter
    public static class UpdateReqDto {
        @NotNull
        private Long id; // 댓글의 id
        @NotNull
        @Size(max = 15)
        private String nickname; // 댓글 작성자
        @NotNull
        @Size(max = 500)
        private String body; // 댓글 본문
    }
}
