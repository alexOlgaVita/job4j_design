package ru.job4j.ood.lsp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Food {
    private int id;
    private String name;
    private LocalDate createDate;
    private LocalDate expiryDate;
    private float price;
    private float discount;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy");

    public Food() {
    }

    public Food(int id, String name, LocalDate createDate, LocalDate expiryDate, float price, float discount) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public Food(String name, LocalDate createDate, LocalDate expiryDate, float price, float discount) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public Food(String name, LocalDate createDate, LocalDate expiryDate, float price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = 0;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", createDate=" + createDate.format(FORMATTER)
                + ", expiryDate=" + expiryDate.format(FORMATTER)
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + id;
        result = prime * result + createDate.hashCode();
        result = prime * result + expiryDate.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Food food = (Food) obj;
        return (id == food.id) && (name.equals(food.name))
                && (createDate.equals(food.createDate))
                && (expiryDate.equals(food.expiryDate))
                && (Float.compare(food.price, price) == 0)
                && (Float.compare(food.discount, discount) == 0);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
