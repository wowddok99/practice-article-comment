package com.example.demo.service;

import com.example.demo.dto.comment.CommentReqDto.CreateReqDto;
import com.example.demo.dto.comment.CommentReqDto.UpdateReqDto;
import com.example.demo.dto.comment.CommentRespDto.*;
import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    public List<AllCommentsRespDto> allComments(){
        return commentRepository.findAll()
                .stream()
                .map(commentPS -> new AllCommentsRespDto( // 엔티티를 DTO로 매핑
                        commentPS.getId(),
                        commentPS.getArticle().getId(),
                        commentPS.getNickname(),
                        commentPS.getBody()))
                .collect(Collectors.toList());
    }
    public List<CommentsRespDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId) // 댓글 엔티티 목록 조회
                .stream() // 댓글 엔티티 목록을 스트림으로 변환
                .map(commentPS -> new CommentsRespDto( // 엔티티를 DTO로 매핑
                        commentPS.getId(),
                        commentPS.getArticle().getId(),
                        commentPS.getNickname(),
                        commentPS.getBody()))
                .collect(Collectors.toList()); // 스트림을 리스트로 변환
    }
    @Transactional
    public CreateRespDto create(Long articleId, CreateReqDto dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " +
                        "대상 게시글이 없습니다."));
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        // 4. DTO로 변환해 반환
        return new CreateRespDto(
                created.getId(),
                created.getArticle().getId(),
                created.getNickname(),
                created.getBody()
        );
    }
    @Transactional
    public UpdateRespDto update(Long id, UpdateReqDto dto) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! " +
                        "대상 게시글이 업습니다."));
        // 2. 댓글 수정
        target.patch(dto);
        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);
        // 4. 댓글 엔티티를 DTO로 변환 및 반환
        return new UpdateRespDto(
                updated.getId(),
                updated.getArticle().getId(),
                updated.getNickname(),
                updated.getBody()
        );
    }
    @Transactional
    public DeleteRespDto delete(Long id) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow();
        // 2. 댓글 삭제
        commentRepository.delete(target);
        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return new DeleteRespDto(
                target.getId(),
                target.getArticle().getId(),
                target.getNickname(),
                target.getBody()
        );
    }
}
