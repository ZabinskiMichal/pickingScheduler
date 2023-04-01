package System;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

@Setter
@Getter
@ToString
public class Picker {
    private String ID;
    private LocalTime startsWorking;
    private LocalTime endsWorking;


    public Picker(String ID, LocalTime startsWorking, LocalTime endsWorking) {
        this.ID = ID;
        this.startsWorking = startsWorking;
        this.endsWorking = endsWorking;
    }


     private boolean isFreeDuringHours(LocalTime startTime, LocalTime stopTime){
        return !(startTime.isBefore(this.startsWorking) && stopTime.isAfter(endsWorking));

    }

    private void updateBusyTime(TemporalAmount time){
        this.startsWorking = this.startsWorking.plus(time);

    }

    private boolean pickerWillNotExceedWorkingHours(TemporalAmount timeNeededToPick){
        if(!this.getStartsWorking().plus(timeNeededToPick).isAfter(this.getEndsWorking())){
            return true;
        }
        return false;
    }

    public boolean canWorkDuringHours(LocalTime start, LocalTime stop, TemporalAmount timeNeededToWork){
        if (isFreeDuringHours(start, stop) && pickerWillNotExceedWorkingHours(timeNeededToWork)){
            updateBusyTime(timeNeededToWork);
            return true;
        }
        return false;
    }

}
