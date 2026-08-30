package application.domain.models;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cart extends BusinessEntity {
    private Buyer buyer;
    private List<CartItem> items;
    private LocalDateTime lastUpdateDate;
}