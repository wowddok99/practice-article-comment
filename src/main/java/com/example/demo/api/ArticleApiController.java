package com.example.demo.api;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.article.ArticleReqDto.CreateReqDto;
import com.example.demo.dto.article.ArticleReqDto.UpdateReqDto;
import com.example.demo.dto.article.ArticleRespDto.*;
import com.example.demo.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ArticleApiController {
    private final ArticleService articleService;
    // 게시글 전체 조회
    @GetMapping("/api/articles")
    public ResponseEntity<?> index() {
        List<IndexRespDto> indexedDto = articleService.index();
        return new ResponseEntity<>(new ResponseDto<>(1,"게시글 전체 조회 성공", indexedDto), HttpStatus.OK);
    }
    // 게시글 조회
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        ShowRespDto showedDto = articleService.show(id);
        return new ResponseEntity<>(new ResponseDto<>(1,"게시글 조회 성공", showedDto), HttpStatus.OK);
    }
    // 게시글 등록
    @PostMapping("/api/articles")
    public ResponseEntity<?> create(@Valid @RequestBody CreateReqDto dto) {
        CreateRespDto createdDto = articleService.create(dto);
        return new ResponseEntity<>(new ResponseDto<>(1,"게시글 등록 성공", createdDto), HttpStatus.CREATED);
    }
    // 게시글 수정
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                                @RequestBody @Valid UpdateReqDto dto) {
        UpdateRespDto updatedDto = articleService.update(id, dto);
        return new ResponseEntity<>(new ResponseDto<>(1,"게시글 수정 성공", updatedDto), HttpStatus.OK);
    }
    // 게시글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        DeleteRespDto deletedDto = articleService.delete(id);
        return new ResponseEntity<>(new ResponseDto<>(1,"게시글 삭제 성공", deletedDto), HttpStatus.OK);
    }
}
