package com.ism.ecom.services.impl;

import com.ism.ecom.data.entities.Article;
import com.ism.ecom.data.repositories.ArticleRepository;
import com.ism.ecom.exceptions.EntityNotFoundException;
import com.ism.ecom.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    @Override
    public List<Article> getArticlesFormComande() {
        return articleRepository.findAllByActiveTrue();
    }
    @Override
    public Article getArticleById(Long id) {
          return articleRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Not Found"));
    }

    @Override
    public Article getArticleByLibelle(String libelle) {
        return articleRepository.findArticleByLibelleAndActiveTrue(libelle);
    }
}
