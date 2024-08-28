package com.example.demo.service;

import com.example.demo.dto.article.ArticleReqDto.CreateReqDto;
import com.example.demo.dto.article.ArticleReqDto.UpdateReqDto;
import com.example.demo.dto.article.ArticleRespDto.*;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    public List<IndexRespDto> index() {
        return articleRepository.findAll()
                .stream()
                .map(articlePS -> new IndexRespDto(
                        articlePS.getId(),
                        articlePS.getTitle(),
                        articlePS.getContent()))
                .collect(Collectors.toList());
    }

    public ShowRespDto show(Long id) {
        Article articlePS = articleRepository.findById(id).orElse(null);
        return new ShowRespDto(
                articlePS.getId(),
                articlePS.getTitle(),
                articlePS.getContent()
        );
    }
    @Transactional
    public CreateRespDto create(CreateReqDto dto) {
        // 1. DTO -> 엔티티 변환
        Article article = dto.toEntity();
        // 2. 저장하기
        Article articlePS = articleRepository.save(article);
        return new CreateRespDto(
                articlePS.getId(),
                articlePS.getTitle(),
                articlePS.getContent()
        );
    }
    @Transactional
    public UpdateRespDto update(Long id, UpdateReqDto dto) {
        // 1. DTO -> 엔티티 변환
        Article article = dto.toEntity();
        // 2. 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 업데이트하기
        Article articlePS = articleRepository.save(article);
        return new UpdateRespDto(
                articlePS.getId(),
                articlePS.getTitle(),
                articlePS.getContent()
        );
    }
    @Transactional
    public DeleteRespDto delete(Long id) {
        // 1. 대상 찾기
        Article articlePS = articleRepository.findById(id).orElse(null);
        // 2. 대상 삭제하기
        articleRepository.delete(articlePS);
        return new DeleteRespDto(
                articlePS.getId(),
                articlePS.getTitle(),
                articlePS.getContent());
    }
}
