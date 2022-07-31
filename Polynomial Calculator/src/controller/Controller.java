package controller;

import model.Monom;
import model.Polinom;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private View view;
    private Polinom polinom1;
    private Polinom polinom2;

    public Controller(View view) {
        this.view = view;
        this.view.addPoli(new addPolinoame());
        this.view.subPoli(new subPolinoame());
        this.view.mulPoli(new mulPolinoame());
        this.view.derPoli(new derPolinoame());
        this.view.intPoli(new intPolinoame());
    }
    public void polinom1Citire(){
        String poli1text = view.poli1Text();
        polinom1 = new Polinom();
        polinom1.getElements(poli1text);
    }
    public void polinom2Citire(){
        String poli2text = view.poli2Text();
        polinom2 = new Polinom();
        polinom2.getElements(poli2text);

    }
    class addPolinoame implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            polinom1 = new Polinom();
            polinom2 = new Polinom();
            polinom1Citire();
            polinom2Citire();
            view.setText(polinom1.adunare(polinom2).beautifyList());
        }
    }

    class subPolinoame implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            polinom1 = new Polinom();
            polinom2 = new Polinom();
            polinom1Citire();
            polinom2Citire();
            view.setText(polinom1.scadere(polinom2).beautifyList());
        }
    }

    class mulPolinoame implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            polinom1 = new Polinom();
            polinom2 = new Polinom();
            polinom1Citire();
            polinom2Citire();
            view.setText(polinom1.inmultire(polinom2).beautifyList());
        }
    }

    class derPolinoame implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            polinom1 = new Polinom();
            polinom1Citire();
            view.setText(polinom1.derivare().beautifyList());
        }
    }

    class intPolinoame implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            polinom1 = new Polinom();
            polinom1Citire();
            view.setText(polinom1.integrare().beautifyList());
        }
    }
}