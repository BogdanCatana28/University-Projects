package model;

import static org.junit.jupiter.api.Assertions.*;

class PolinomTest {

    Polinom polinom1;
    Polinom polinom2;

    @org.junit.jupiter.api.Test
    void adunare() {
        polinom1 = new Polinom();
        polinom1.getElements("3x^3+2x^2+7x^0");
        polinom2 = new Polinom();
        polinom2.getElements("4x^2+9x^1");
        assertEquals("+3.00x^3+6.00x^2+9.00x^1+7.00x^0", polinom1.adunare(polinom2).beautifyList());
    }

    @org.junit.jupiter.api.Test
    void scadere() {
        polinom1 = new Polinom();
        polinom1.getElements("8x^3+2x^2-10x^1");
        polinom2 = new Polinom();
        polinom2.getElements("9x^3+6x^1+4x^0");
        assertEquals("-1.00x^3+2.00x^2-16.00x^1-4.00x^0", polinom1.scadere(polinom2).beautifyList());
    }

    @org.junit.jupiter.api.Test
    void inmultire() {
        polinom1 = new Polinom();
        polinom1.getElements("4x^2+2x^1");
        polinom2 = new Polinom();
        polinom2.getElements("3x^3+4x^0");
        assertEquals("+12.00x^5+6.00x^4+16.00x^2+8.00x^1", polinom1.inmultire(polinom2).beautifyList());
    }

    @org.junit.jupiter.api.Test
    void derivare() {
        polinom1 = new Polinom();
        polinom1.getElements("9x^4+3x^2+6x^1");
        assertEquals("+36.00x^3+6.00x^1+6.00x^0", polinom1.derivare().beautifyList());
    }

    @org.junit.jupiter.api.Test
    void integrare() {
        polinom1 = new Polinom();
        polinom1.getElements("8x^4+2x^2+4x^1");
        assertEquals("+1.60x^5+0.67x^3+2.00x^2", polinom1.integrare().beautifyList());
    }
}