package solution;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import solution.model.OrderModel;
import solution.System.OrderProcessed;
import solution.System.Picker;
import solution.model.StoreModel;

import java.io.File;
import java.util.*;

public class Main {

    //        args:
//    1) src to store.json
//    2) src to orders.json
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        String storeSourceFilePath = "/Users/michalzabinski/Desktop/zadanie-java/self-test-data/optimize-order-count/store.json";
        String ordersSourceFilePath = "/Users/michalzabinski/Desktop/zadanie-java/self-test-data/optimize-order-count/orders.json";


        StoreModel store = objectMapper.readValue(new File(storeSourceFilePath), StoreModel.class);
        System.out.println("Store specification: ");
        System.out.println(store);
        System.out.println();

        System.out.println("Orders:");
        Queue<OrderModel> orders = objectMapper.readValue(new File(ordersSourceFilePath), new TypeReference<Queue<OrderModel>>() {
        });
        orders.forEach(order -> System.out.println(order.toString()));

        int iloscZamowien = orders.size();

//        creating pickers
        List<Picker> pickers = new ArrayList<>();
        for (String picker : store.getPickers()) {
            Picker pickerer = new Picker(picker, store.getPickingStartTime(), store.getPickingEndTime());
            pickers.add(pickerer);
            System.out.println(pickerer);
        }


        pickers.forEach(System.out::println);

        List<OrderProcessed> processedOrders = new ArrayList<>();

        System.out.println();

//        TODO: dopilmowsc zaby pracownik nie mogl przekroczyc working hours sklepu
//        TODO: zaimplementowac jak najwiecej sprawdzen w przod
//        TODO: wydzielic czesc do kalsy pickingManager

        boolean freePicker = true;

        while (!orders.isEmpty() && freePicker) {
            for (Picker currentPicker : pickers) {

                if (orders.peek() != null) {

                    if (currentPicker.canWorkDuringHours(orders.peek().getStartTime(), orders.peek().getCompleteBy(), orders.peek().getPickingTime())) {
                        System.out.println("Poprzednie wolne godziny pracy pickera o id: " + currentPicker.getID() + " wynosiły : " + currentPicker.getStartsWorking() + " - " + currentPicker.getEndsWorking());
                        currentPicker.updateBusyTime(orders.peek().getPickingTime());
                        System.out.println("godziny pakowania: " + orders.peek().getCompleteBy().minus(orders.peek().getPickingTime()) + " do " + orders.peek().getCompleteBy());
                        processedOrders.add(new OrderProcessed(orders.peek().getOrderId(), currentPicker.getID(), orders.poll().getStartTime()));
                        System.out.println("Nowe wolne godziny pracy pickera o id: " + currentPicker.getID() + " wynosiły : " + currentPicker.getStartsWorking() + " - " + currentPicker.getEndsWorking());
                        System.out.println();
                    } else {
                        freePicker = false;
                    }

                }

            }

        }

            System.out.println("Wyniki: ");
            System.out.println();
            System.out.println("Wszystkich zamowien: " + iloscZamowien);

//        usunac napisy
            processedOrders.forEach((
                    currentOrder -> System.out.println(
                            "picker-id " + currentOrder.getPickerId() +
                                    " order-id " + currentOrder.getOrderId() +
                                    " picking-start-time " + currentOrder.getPickingStartTime()
                    )));





    }
}
