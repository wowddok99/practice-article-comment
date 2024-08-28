package com.example.demo.entity;

import com.example.demo.dto.comment.CommentReqDto.CreateReqDto;
import com.example.demo.dto.comment.CommentReqDto.UpdateReqDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id") // 외래키 생성, Article 엔티티의 기본키(id)와 매핑
    private Article article; // 해당 댓글의 부모 게시글

    @Column(nullable = false)
    private String nickname; // 댓글을 단 사람
    @Column(nullable = false)
    private String body; // 댓글 본문

    public static Comment createComment(CreateReqDto dto, Article article) {
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        // 엔티티 생성 및 반환
        return new Comment(
                null,        // 댓글 아이디
                article,            // 부모 게시글
                dto.getNickname(),  // 댓글 닉네임
                dto.getBody()       // 댓글 본문
        );
    }
    public void patch(UpdateReqDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");

        // 객체 갱신
        if (dto.getNickname() != null) // 수정할 닉네임 데이터가 있다면
            this.nickname = dto.getNickname(); // 내용 반영
        if (dto.getBody() != null) // 수정할 본문 데이터가 있다면
            this.body = dto.getBody(); // 내용 반영
    }
}
