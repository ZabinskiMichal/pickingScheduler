package solution.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class OrderModelTest {

    @Test
    public void checkIfWillNotExceedDeadLineWithBigButter(){
        OrderModel order = new OrderModel("1", new BigDecimal(0), Duration.ofMinutes(30), LocalTime.of(10, 0));
        Assert.assertTrue(order.checkIfWillNotExceedDeadLine(LocalTime.of(9,0)));
    }

    @Test
    public void willNotExceedWithZeroBufferSouldBeTrue(){
        OrderModel order = new OrderModel("1", new BigDecimal(0), Duration.ofMinutes(30), LocalTime.of(10, 0));
        Assert.assertTrue(order.checkIfWillNotExceedDeadLine(LocalTime.of(9,30)));
    }

    @Test
    public void oneMinuteAboveDeadLineShouldReturnFalse(){
        OrderModel order = new OrderModel("1", new BigDecimal(0), Duration.ofMinutes(30), LocalTime.of(10, 0));
        Assert.assertFalse(order.checkIfWillNotExceedDeadLine(LocalTime.of(9,31)));
    }


    @Test
    public void  getStartTimeOfTenMinutesPickingTimeAndCompleteByAsTenSouldBeNineFifty(){
        OrderModel order = new OrderModel("1", new BigDecimal(0), Duration.ofMinutes(10), LocalTime.of(10, 0));
        Assert.assertEquals(order.getStartTime(), LocalTime.of(9, 50));
    }




}