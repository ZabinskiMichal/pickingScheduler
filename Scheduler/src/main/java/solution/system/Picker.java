package solution.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Picker {
    private String ID;
    private LocalTime startsWorking;
    private LocalTime endsWorking;
    private boolean pickerIsStillFree;
    private int numberOfTries;

    public void updateBusyTime(TemporalAmount time){
        this.startsWorking = this.startsWorking.plus(time);
    }

    public boolean canPickOrderThatWillTake(TemporalAmount pickingTime){
        return !startsWorking.plus(pickingTime).isAfter(endsWorking);
    }

    public void incremtntNumberOfTries() {
        this.numberOfTries++;

    }
}
