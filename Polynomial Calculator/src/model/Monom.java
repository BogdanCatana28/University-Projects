package model;

public class Monom {
    public double coeficient;
    public int grad;

    public  Monom(double coeficient, int grad){
        this.coeficient = coeficient;
        this.grad = grad;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }

    public int getGrad() {
        return grad;
    }

    public void setGrad(int grad) {
        this.grad = grad;
    }
}
