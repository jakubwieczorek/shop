package wieczorek.jakub.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wieczorek.jakub.shop.dto.Article;
import wieczorek.jakub.shop.services.ArticleDAO;

@RestController
public class ArticleController
{
    private ArticleDAO articleDAO;

    @Autowired
    public ArticleController(ArticleDAO articleDAO)
    {
        this.articleDAO = articleDAO;
    }

    @GetMapping("/getShoes")
    public Article getShoes(@RequestParam(value="size", defaultValue="42") String aSize)
    {
        return articleDAO.retrieveArticle(Long.parseLong(aSize));
    }
}