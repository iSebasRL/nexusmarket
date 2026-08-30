package application.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductVariant {
    private String identifier;
    private String name;
    private String value;
    private Product product;
}