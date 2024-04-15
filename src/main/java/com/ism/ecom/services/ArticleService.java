package com.ism.ecom.services;

import com.ism.ecom.data.entities.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> getArticlesFormComande();
    public Article getArticleById(Long id);
    public Article getArticleByLibelle(String libelle);
}
