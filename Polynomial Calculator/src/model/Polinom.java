package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom {
    private List<Monom> polinom;

    public Polinom() {
        this.polinom = new ArrayList<>();
    }

    public Polinom(List<Monom> polinom) {
        this.polinom = polinom;
    }

    public List<Monom> getPolinom() {
        return polinom;
    }

    public void setPolinom(List<Monom> polinom) {
        this.polinom = polinom;
    }

    public void getElements(String polinom) {
        Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
        Matcher matcher = pattern.matcher(polinom);
        while (matcher.find()) {
            int coef = 0;
            int grad = 0;
            int i = 0;
            while(i < matcher.group(1).length()) {
                Character semn1 = matcher.group(1).charAt(i);
                Boolean var = Character.isDigit(semn1);
                if(var && i == 0){
                    while(Character.isDigit(matcher.group(1).charAt(i))){
                        coef = coef*10 + Integer.parseInt(String.valueOf(matcher.group(1).charAt(i)));
                        i++;
                    }
                    i = i + 2;
                    while(i < matcher.group(1).length()){
                        if(Character.isDigit(matcher.group(1).charAt(i))){
                            grad = grad*10 + Integer.parseInt(String.valueOf(matcher.group(1).charAt(i)));
                        }
                        i++;
                    }
                }
                else{
                    i++;
                    while(Character.isDigit(matcher.group(1).charAt(i))) {
                        coef = coef * 10 + Integer.parseInt(String.valueOf(matcher.group(1).charAt(i)));
                        i++;
                    }
                    if(semn1 == '-'){
                        coef = -coef;
                    }
                    i = i + 2;
                    while(i < matcher.group(1).length()){
                        if(Character.isDigit(matcher.group(1).charAt(i))){
                            grad = grad*10 + Integer.parseInt(String.valueOf(matcher.group(1).charAt(i)));
                        }
                        i++;
                    }
                }
            }
            Monom monom1 = new Monom(coef,grad);
            this.polinom.add(monom1);
        }
    }

    public String beautifyList(){
        String space = "";
        for(Monom aux:this.polinom){
            space = space + (aux.getCoeficient() >= 0 ? "+":"") + String.format("%.2f", aux.getCoeficient()) + "x" + "^" + aux.getGrad();
        }
        return space;
    }

    public void sortareLista(List<Monom> polinom){
        Collections.sort(polinom, new Comparator<Monom>() {
            @Override
            public int compare(Monom o1, Monom o2) {
                return o2.getGrad() - o1.getGrad();
            }
        });
    }

    public Polinom adunare(Polinom polinom1){
        List<Monom> polinomaux = new ArrayList<>();
        for(int i = 0; i < polinom.size();i++){
            int verifica = 0;
            for(int j = 0; j < polinom1.getPolinom().size();j++){
                if(polinom.get(i).getGrad() == polinom1.getPolinom().get(j).getGrad()){
                    Monom monom1 = new Monom(polinom.get(i).getCoeficient() + polinom1.getPolinom().get(j).getCoeficient(),polinom.get(i).getGrad());
                    polinomaux.add(monom1);
                    verifica++;
                    polinom1.getPolinom().remove(j);
                }
            }
            if(verifica == 0){
                Monom monom1 = new Monom(polinom.get(i).getCoeficient(), polinom.get(i).getGrad());
                polinomaux.add(monom1);
            }
        }
        for(int i = 0; i < polinom1.getPolinom().size();i++){
            Monom monom1 = new Monom(polinom1.getPolinom().get(i).getCoeficient(), polinom1.getPolinom().get(i).getGrad());
            polinomaux.add(monom1);
        }
        sortareLista(polinomaux);
        return new Polinom(polinomaux);
    }

    public Polinom scadere(Polinom polinom2){
        List<Monom> polinomaux = new ArrayList<>();
        for(int i = 0; i < polinom.size();i++){
            int verifica = 0;
            for(int j = 0; j < polinom2.getPolinom().size();j++){
                if(polinom.get(i).getGrad() == polinom2.getPolinom().get(j).getGrad()){
                    Monom monom1 = new Monom(polinom.get(i).getCoeficient() - polinom2.getPolinom().get(j).getCoeficient(),polinom.get(i).getGrad());
                    polinomaux.add(monom1);
                    verifica++;
                    polinom2.getPolinom().remove(j);
                }
            }
            if(verifica == 0){
                Monom monom1 = new Monom(polinom.get(i).getCoeficient(), polinom.get(i).getGrad());
                polinomaux.add(monom1);
            }
        }
        for(int i = 0; i < polinom2.getPolinom().size();i++){
            Monom monom1 = new Monom(-polinom2.getPolinom().get(i).getCoeficient(), polinom2.getPolinom().get(i).getGrad());
            polinomaux.add(monom1);
        }
        sortareLista(polinomaux);
        return new Polinom(polinomaux);
    }

    public Polinom inmultire(Polinom polinom3){
        List<Monom> polinomaux = new ArrayList<>();
        List<Monom> polinomrez = new ArrayList<>();
        for(int i = 0; i < polinom.size();i++){
            for(int j = 0;j < polinom3.getPolinom().size();j++){
                Monom monom1 = new Monom(polinom.get(i).getCoeficient() * polinom3.getPolinom().get(j).getCoeficient(),polinom.get(i).getGrad() + polinom3.getPolinom().get(j).getGrad());
                polinomaux.add(monom1);
            }
        }
        for(int i = 0; i < polinomaux.size();i++){
            int verificare = 0;
            for(int j = i + 1; j < polinomaux.size();j++){
                if(polinomaux.get(i).getGrad() == polinomaux.get(j).getGrad()){
                    Monom monom1 = new Monom(polinomaux.get(i).getCoeficient() + polinomaux.get(j).getCoeficient(),polinomaux.get(i).getGrad());
                    polinomrez.add(monom1);
                    verificare++;
                    polinomaux.remove(j);
                }
            }
            if(verificare == 0){
                Monom monom1 = new Monom(polinomaux.get(i).getCoeficient(),polinomaux.get(i).getGrad());
                polinomrez.add(monom1);
            }
        }
        sortareLista(polinomrez);
        return new Polinom(polinomrez);
    }

    public Polinom derivare(){
        List<Monom> polinomaux = new ArrayList<>();

        for(int i = 0; i < polinom.size();i++){
            Monom monom1 = new Monom(polinom.get(i).getCoeficient() * polinom.get(i).getGrad(), polinom.get(i).grad - 1);
            polinomaux.add(monom1);
        }
        sortareLista(polinomaux);
        return new Polinom(polinomaux);
    }

    public Polinom integrare(){
        List<Monom> polinomaux = new ArrayList<>();

        for(int i = 0; i < polinom.size();i++){
            Monom monom1 = new Monom(polinom.get(i).getCoeficient() / (polinom.get(i).getGrad() + 1), polinom.get(i).grad + 1);
            polinomaux.add(monom1);
        }
        sortareLista(polinomaux);
        return new Polinom(polinomaux);
    }

}
