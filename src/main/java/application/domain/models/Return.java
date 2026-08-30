package application.domain.models;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Return extends BusinessEntity {
    private Order order;
    private String reason;
    private LocalDateTime requestDate;
    private LocalDateTime approvalDate;
    private Buyer requestedBy;
    private User approvedBy;
    private Refund refund;
}