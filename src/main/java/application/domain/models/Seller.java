package application.domain.models;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Seller extends Participant {
    private List<Warehouse> warehouses;
    private List<Product> products;
}