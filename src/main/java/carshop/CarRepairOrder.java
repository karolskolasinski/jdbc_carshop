package carshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRepairOrder {
    private LocalDateTime addDate;
    private boolean realized;
    private LocalDateTime relizedDate;
    private String orderText;
    private Long id;
}