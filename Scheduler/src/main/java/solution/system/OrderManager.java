package solution.system;

import solution.model.OrderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class OrderManager {

    private List<Picker> pickers;
    private Queue<OrderModel> orders;
    private int ordersAmount;
    List<OrderProcessed> pickerToOrder = new ArrayList<>();

    public OrderManager(List<Picker> pickers, Queue<OrderModel> orders, int ordersAmount) {
        this.pickers = pickers;
        this.orders = orders;
        this.ordersAmount = ordersAmount;
    }

    public List<OrderProcessed> splitTasks(){
        int numberOfTries = 0;
        boolean toContinue = true;
//        TODO: zaimpelnetnowac mozliwosc zmiany kolejnci przyjmowacnych zamowieni do sprawdzenia


//        tutaj bedzie implementacja petli rodzelajacej zamowienia
        while (freePickersLeft() && !orders.isEmpty() && toContinue){

            if(orders.peek() != null){

                for (Picker picker : pickers){
//                checking if picker is able to take order that will take @Param time
                    if(picker.canPickOrderThatWillTake(orders.peek().getPickingTime())){

//                        checks if order will be processed before deadline
                        if(orders.peek().checkIfWillNotExceedDeadLine(picker.getStartsWorking())){
//                            here we are sure that picker can take this order
                            pickerToOrder.add(new OrderProcessed(orders.peek().getOrderId(), picker.getID(), orders.peek().getStartTime()));

//                            now we need to update Pickwers working time
//                            olso we need to make sure that picker tried to get all of orders
                            picker.updateBusyTime(orders.peek().getPickingTime());
                            orders.poll();
                        }else {
                            numberOfTries++;
                            if(numberOfTries >= pickers.size()){
                                toContinue = false;
                            }
                        }


                    }else {
                        picker.incremtntNumberOfTries();

                        if(picker.getNumberOfTries() > ordersAmount){
//                        TODO: switch orders
                            orders.poll();
                            picker.setNumberOfTries(0);

                        }
//                        we need to switch pickers now with amount not bigger than pickers amount
                        if(picker.getNumberOfTries() >= ordersAmount ){
                            picker.setPickerIsStillFree(false);
                        }
                    }



                }

            }



        }

        return pickerToOrder;

    }


    private boolean freePickersLeft(){
        boolean freePickersLeft = true;
        for (Picker picker : pickers) {
            if (picker.isPickerIsStillFree()) {
                continue;
            }
            freePickersLeft = false;
        }
        return freePickersLeft;
    }






}
