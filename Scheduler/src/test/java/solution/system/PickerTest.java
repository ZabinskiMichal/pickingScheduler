package solution.system;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sun.source.tree.AssertTree;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

public class PickerTest {


//    testing updateBusyTime

    @Test
    public void tenOclockUpdatedByTenMinutesBusyTimeShouldEualsTenPastTen(){
        Picker picker = new Picker("1", LocalTime.of(10,00), LocalTime.of(11,0), true, 0);
        picker.updateBusyTime(Duration.ofMinutes(10));
        Assert.assertEquals(picker.getStartsWorking(), LocalTime.of(10, 10));
    }


    @Test
    public void tenOclockUpdatedByNineMinutesBusyTimeShouldNotEualsTenPastTen(){
        Picker picker = new Picker("1", LocalTime.of(10,00), LocalTime.of(11,0), true, 0);
        picker.updateBusyTime(Duration.ofMinutes(9));
        Assert.assertNotEquals(picker.getStartsWorking(), LocalTime.of(10, 10));
    }

//    testing canPickOrderThatWillTake

    @Test
    public void canPickOrderWithLotsOfBuforTime(){
        Picker picker = new Picker("1", LocalTime.of(10,00), LocalTime.of(11,0), true, 0);
        Assert.assertTrue(picker.canPickOrderThatWillTake(Duration.ofMinutes(10)));
    }

    @Test
    public void takingOrderWithZeroBuforSouldReturnTrue(){
        Picker picker = new Picker("1", LocalTime.of(10,55), LocalTime.of(11,0), true, 0);
        Assert.assertTrue(picker.canPickOrderThatWillTake(Duration.ofMinutes(5)));
    }

    @Test
    public void oneMinuteAfterScheduleShoulReturnFalse(){
        Picker picker = new Picker("1", LocalTime.of(10,55), LocalTime.of(11,0), true, 0);
        Assert.assertFalse(picker.canPickOrderThatWillTake(Duration.ofMinutes(6)));

    }

//    testing incremtntNumberOfTries()

    @Test
    public void noIncrementingNumberOfTriesShoulEqualZero(){
        Picker picker = new Picker("1", LocalTime.of(10,55), LocalTime.of(11,0), true, 0);
        Assert.assertEquals(picker.getNumberOfTries(), 0);
    }


    @Test
    public void doubleIncrementOfNumberOfTriesShouldEqualTwo(){
        Picker picker = new Picker("1", LocalTime.of(10,55), LocalTime.of(11,0), true, 0);
        picker.incremtntNumberOfTries();
        picker.incremtntNumberOfTries();
        Assert.assertEquals(picker.getNumberOfTries(), 2);

    }





}