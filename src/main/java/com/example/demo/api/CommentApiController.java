package com.example.demo.api;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.comment.CommentReqDto.CreateReqDto;
import com.example.demo.dto.comment.CommentReqDto.UpdateReqDto;
import com.example.demo.dto.comment.CommentRespDto.*;
import com.example.demo.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;
    // 댓글 전체 조회
    @GetMapping("/api/articles/comments")
    public ResponseEntity<?> allComments(){
        List<AllCommentsRespDto> allCommentsDto = commentService.allComments();
        return new ResponseEntity<>(new ResponseDto<>(1,"댓글 전체 조회 성공", allCommentsDto), HttpStatus.OK);
    }
    // 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<?> comments(@PathVariable Long articleId){
        // 서비스에 위임
        List<CommentsRespDto> commentsDto = commentService.comments(articleId);
        // 결과 응답
        return new ResponseEntity<>(new ResponseDto<>(1,"댓글 조회 성공", commentsDto), HttpStatus.OK);

    }
    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<?> create(@PathVariable Long articleId,
                                                @RequestBody @Valid CreateReqDto dto) {
        // 서비스에 위임
        CreateRespDto createdDto = commentService.create(articleId, dto);
        // 결과 응답
        return new ResponseEntity<>(new ResponseDto<>(1,"댓글 생성 성공", createdDto), HttpStatus.CREATED);
    }
    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                                @RequestBody @Valid UpdateReqDto dto) {
        // 서비스에 위임
        UpdateRespDto updatedDto = commentService.update(id, dto);
        // 결과 응답
        return new ResponseEntity<>(new ResponseDto<>(1,"댓글 수정 성공", updatedDto), HttpStatus.OK);
    }
    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        // 서비스에 위임
        DeleteRespDto deletedDto = commentService.delete(id);
        // 결과 응답
        return new ResponseEntity<>(new ResponseDto<>(1,"댓글 삭제 성공", deletedDto), HttpStatus.OK);
    }
}
