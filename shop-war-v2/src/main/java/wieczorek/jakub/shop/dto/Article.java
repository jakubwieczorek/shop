package wieczorek.jakub.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article
{
    private String name;
    private Double price;
    private String description;
    private Long availability;
}
