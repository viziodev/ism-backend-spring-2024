package com.ism.ecom.api.controllers.impl;

import com.ism.ecom.api.controllers.ArticleRestController;
import com.ism.ecom.api.controllers.dto.response.ArticleAllResponseDto;
import com.ism.ecom.api.controllers.dto.RestResponse;
import com.ism.ecom.data.entities.Article;
import com.ism.ecom.services.ArticleService;
import com.ism.ecom.api.controllers.dto.request.ArticlePanierDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:4200")
public class ArticleRestControllerImpl implements ArticleRestController {
    private final ArticleService articleService;
    @Override
    public ResponseEntity<Map<Object, Object>> listerArticleParLibelle(String libelle) {
        Article article = articleService.getArticleByLibelle(libelle);
        Map<Object, Object> response;
        if (article == null){
            response= RestResponse.response(null, HttpStatus.NO_CONTENT);
        }else {
            response= RestResponse.response(ArticlePanierDto.toDto(article),HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Object, Object>> listerArticle() {
        List<Article> articles = articleService.getArticlesFormComande();
        List<ArticleAllResponseDto> list = articles.stream().map(ArticleAllResponseDto::toDto).toList();
        return new ResponseEntity<>(RestResponse.response(list,HttpStatus.OK), HttpStatus.OK);
    }
}
