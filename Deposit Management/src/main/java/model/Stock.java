package model;

public class Stock {
    public int idProd;
    public int quantity;

    public Stock(int idProd, int quantity) {
        this.idProd = idProd;
        this.quantity = quantity;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
