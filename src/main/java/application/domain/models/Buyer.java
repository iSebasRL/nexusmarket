package application.domain.models;

import application.domain.valueobjects.BuyerStatus;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Buyer extends Participant {
    private Address mainAddress;
    private List<Address> additionalAddresses;
    private BuyerStatus commercialStatus;
}