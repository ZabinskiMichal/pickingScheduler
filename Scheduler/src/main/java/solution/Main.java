package solution;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import solution.model.OrderModel;
import solution.model.StoreModel;
import solution.system.OrderManager;
import solution.system.OrderProcessed;
import solution.system.Picker;

import java.io.File;
import java.util.*;

public class Main {

//    args
//    1) src to store.json
//    2) src to orders.json

    public static void main(String[] args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

//        String storeSourceFilePath = "/Users/michalzabinski/Desktop/zadanie-java/self-test-data/optimize-order-count/store.json";
//        String ordersSourceFilePath = "/Users/michalzabinski/Desktop/zadanie-java/self-test-data/optimize-order-count/orders.json";


        String storeSourceFilePath = args[0];
        String ordersSourceFilePath = args[1];

        StoreModel store = objectMapper.readValue(new File(storeSourceFilePath), StoreModel.class);
        List<OrderModel> ordersList = objectMapper.readValue(new File(ordersSourceFilePath), new TypeReference<List<OrderModel>>() {});

        int ordersAmount = ordersList.size();


//        ordersList.sort(Comparator.comparing(OrderModel::getPickingTime));
        ordersList.sort(Comparator.comparing(OrderModel::getStartTime));
        Queue<OrderModel> ordersQueue =  new LinkedList<>(ordersList);



        List<Picker> pickers = new ArrayList<>();


        for (String picker : store.getPickers()) {
            Picker pickerer = new Picker(picker, store.getPickingStartTime(), store.getPickingEndTime(), true, 0);
            pickers.add(pickerer);
        }

        OrderManager orderManager = new OrderManager(pickers, ordersQueue, ordersAmount);

        List<OrderProcessed> orderProcesseds = orderManager.splitTasks();

        orderProcesseds.forEach(orderProcessed -> System.out.println(
                orderProcessed.getPickerId() + " " +
                        orderProcessed.getOrderId() + " " +
                        orderProcessed.getPickingStartTime()
        ));


    }


}
