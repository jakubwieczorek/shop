package wieczorek.jakub.shop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wieczorek.jakub.shop.dto.Article;

@RestController
public class ArticleController
{
    @GetMapping("/getShoes")
    public Article getShoes(@RequestParam(value="size", defaultValue="42") String aSize)
    {
        Article article = new Article();
        article.setAvailability(5L);
        article.setDescription("Sport shoes from Nike company, best for running, but suitable also for other daily use.");
        article.setName("Nike, Total 50 shoe, size " + aSize);
        article.setPrice(199.99);
        return article;
    }
}