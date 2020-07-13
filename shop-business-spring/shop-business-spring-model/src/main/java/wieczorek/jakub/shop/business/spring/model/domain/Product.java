package wieczorek.jakub.shop.business.spring.model.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import wieczorek.jakub.shop.business.spring.client.dto.ProductDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Setter
@Getter
class Product
{
    @Id
    @Column(name = "product_id")
    @SequenceGenerator(name = "product_id_sequence", sequenceName = "product_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "product_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Long amountInStock;
    private String description;
    private Long soldAmount;
    private BigDecimal weight;
    private String vendor;
    private Long productionYear;

    @Column(name = "`size`")
    private String size;

    @OneToMany(mappedBy = "product")
    private Set<ProductOrder> productOrders= new HashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Product()
    {

    }

    public Product(ProductDTO productDTO)
    {
        setProductName(productDTO.getProductName());
        setProductPrice(productDTO.getProductPrice());
        setAmountInStock(productDTO.getAmountInStock());
        setDescription(productDTO.getDescription());
        setSoldAmount(productDTO.getSoldAmount());
        setWeight(productDTO.getWeight());
        setVendor(productDTO.getVendor());
        setProductionYear(productDTO.getProductionYear());
        setSize(productDTO.getSize());
        //set
    }

    public ProductDTO toDTO()
    {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductName(getProductName());
        productDTO.setProductPrice(getProductPrice());
        productDTO.setAmountInStock(getAmountInStock());
        productDTO.setDescription(getDescription());
        productDTO.setSoldAmount(getSoldAmount());
        productDTO.setWeight(getWeight());
        productDTO.setVendor(getVendor());
        productDTO.setProductionYear(getProductionYear());
        productDTO.setSize(getSize());
        //set
        return productDTO;
    }
}
