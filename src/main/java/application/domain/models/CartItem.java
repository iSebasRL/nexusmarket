package application.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItem {
    private String identifier;
    private Product product;
    private ProductVariant variant;
    private Integer quantity;
    private Cart cart;
}