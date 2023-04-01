package solution.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class OrderModel {

    private String orderId;
    private BigDecimal orderValue;
    private Duration pickingTime;
    private LocalTime completeBy;



    public LocalTime getStartTime(){
        return this.completeBy.minus(this.pickingTime);
    }


}
