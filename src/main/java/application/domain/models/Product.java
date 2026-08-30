package application.domain.models;

import application.domain.valueobjects.Currency;
import application.domain.valueobjects.ProductStatus;
import application.domain.valueobjects.ProductType;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product extends BusinessEntity {
    private String name;
    private String description;
    private ProductType productType;
    private ProductStatus productStatus;
    private BigDecimal price;
    private Currency currency;
    private List<ProductVariant> variants;
    private Seller seller;
}