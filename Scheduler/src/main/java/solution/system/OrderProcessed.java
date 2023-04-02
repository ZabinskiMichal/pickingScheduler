package solution.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class OrderProcessed {
    private String orderId;
    private String pickerId;
    private LocalTime pickingStartTime;

}
