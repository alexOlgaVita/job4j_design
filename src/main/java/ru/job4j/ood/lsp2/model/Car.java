package ru.job4j.ood.lsp2.model;

public class Car {
    private int id;

    private int size;

    private String vin;

    public Car() {
    }

    public Car(int id, int size, String vin) {
        this.id = id;
        this.size = size;
        this.vin = vin;
    }

    public Car(int size, String vin) {
        this.size = size;
        this.vin = vin;
    }

    public Car(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", size=" + size
                + ", vin=" + vin
                + '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + ((vin == null) ? 0 : vin.hashCode());
        result = prime * result + id;
        result = prime * result + size;
        result = prime * result + size;
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
        Car car = (Car) obj;
        return (id == car.id) && (vin.equals(car.vin)) && (size == car.size);
    }

    public int getSize() {
        return size;
    }

    public int getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
