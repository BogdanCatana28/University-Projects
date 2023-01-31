package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {
    private int idOrder;
    private int idClient;
    private LocalDateTime dateOrder;
    private Double priceOrder;

    public Order(int idOrder, int idClient, Double priceOrder) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.priceOrder = priceOrder;
        this.dateOrder = LocalDateTime.now();
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Double getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(Double priceOrder) {
        this.priceOrder = priceOrder;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order ord = (Order) obj;
        return idOrder == ord.getIdOrder();
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idClient=" + idClient +
                ", dateOrder=" + dateOrder +
                ", priceOrder=" + priceOrder +
                '}';
    }
}
