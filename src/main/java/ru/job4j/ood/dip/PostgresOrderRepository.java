package ru.job4j.ood.dip;

import java.util.ArrayList;

public class PostgresOrderRepository {
    ArrayList<Order> memStore = new ArrayList<>();

    public boolean save(Order order) {
        memStore.add(order);
        return true;
    }
}
