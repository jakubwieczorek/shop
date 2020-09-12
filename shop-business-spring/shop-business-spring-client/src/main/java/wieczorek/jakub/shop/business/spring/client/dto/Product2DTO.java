package wieczorek.jakub.shop.business.spring.client.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="productId")
public class Product2DTO implements Serializable
{
    private Long productId;
    private String productName;

    @JsonBackReference
    private Category2DTO category;
}
