package solution.System;

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

    public boolean canWorkDuringHours(LocalTime start, LocalTime stop, TemporalAmount timeNeededToWork){
        if (isFreeDuringHours(start, stop) && pickerWillNotExceedWorkingHours(timeNeededToWork)){
            return true;
        }
        return false;
    }


     private boolean isFreeDuringHours(LocalTime startTime, LocalTime stopTime){
        return !(startTime.isBefore(this.startsWorking) && stopTime.isAfter(endsWorking));

    }

    public void updateBusyTime(TemporalAmount time){
        this.startsWorking = this.startsWorking.plus(time);

    }

    private boolean pickerWillNotExceedWorkingHours(TemporalAmount timeNeededToPick){
        if(!this.getStartsWorking().plus(timeNeededToPick).isAfter(this.getEndsWorking())){
            return true;
        }
        return false;
    }

}
