package ru.job4j.ood.dip;

public class Order {
    int id;
    String name;
    String customerName;
    String customerEmail;

    public boolean isValid() {
        return true;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerName;
    }
}