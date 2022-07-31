package model;

import view.View;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Simulator implements Runnable{
    private View view;
    private int timerSimulation;
    private int timer;
    private LinkedList<Client> listClienti;
    private int arrivalTimerMin;
    private int arrivalTimerMax;
    private int serviceTimerMin;
    private int serviceTimerMax;
    private int nrClienti;
    private int nrCozi;
    private LinkedList<Coada> listCozi;

    public Simulator(View view) {
        this.view = view;
        this.timerSimulation = timerSimulation;
        this.timer = 0;
        this.listClienti = new LinkedList<>();
        this.listCozi = new LinkedList<>();
        this.arrivalTimerMin = arrivalTimerMin;
        this.arrivalTimerMax = arrivalTimerMax;
        this.serviceTimerMin = serviceTimerMin;
        this.serviceTimerMax = serviceTimerMax;
        this.nrClienti = nrClienti;
        this.nrCozi = nrCozi;
    }

    public int getTimerSimulation() {
        return timerSimulation;
    }

    public void setTimerSimulation(int timerSimulation) {
        this.timerSimulation = timerSimulation;
    }

    public LinkedList<Client> getListClienti() {
        return listClienti;
    }

    public void setListClienti(LinkedList<Client> listClienti) {
        this.listClienti = listClienti;
    }

    public int getArrivalTimerMin() {
        return arrivalTimerMin;
    }

    public void setArrivalTimerMin(int arrivalTimerMin) {
        this.arrivalTimerMin = arrivalTimerMin;
    }

    public int getArrivalTimerMax() {
        return arrivalTimerMax;
    }

    public void setArrivalTimerMax(int arrivalTimerMax) {
        this.arrivalTimerMax = arrivalTimerMax;
    }

    public int getServiceTimerMin() {
        return serviceTimerMin;
    }

    public void setServiceTimerMin(int serviceTimerMin) {
        this.serviceTimerMin = serviceTimerMin;
    }

    public int getServiceTimerMax() {
        return serviceTimerMax;
    }

    public void setServiceTimerMax(int serviceTimerMax) {
        this.serviceTimerMax = serviceTimerMax;
    }

    public int getNrClienti() {
        return nrClienti;
    }

    public void setNrClienti(int nrClienti) {
        this.nrClienti = nrClienti;
    }

    public int getNrCozi() {
        return nrCozi;
    }

    public void setNrCozi(int nrCozi) {
        this.nrCozi = nrCozi;
    }

    public void sortareLista(LinkedList<Client> clients){
        Collections.sort(listClienti, new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return o1.getArrival_time() - o2.getArrival_time();
            }
        });
    }

    public void adaugaClient(){
        int id = 1;
        for(int i = 0; i < nrClienti; i++) {
            Random rand = new Random();
            int randArrTime = rand.nextInt(arrivalTimerMax - arrivalTimerMin) + arrivalTimerMin;
            int randSerTime = rand.nextInt(serviceTimerMax - serviceTimerMin) + serviceTimerMin;
            Client client = new Client(id, randArrTime, randSerTime);
            listClienti.add(client);
            id++;
        }

        sortareLista(listClienti);
    }

    public String afisareWaiting(){
        String wait = "";
        for(int i = 0; i < listClienti.size(); i++){
            wait = wait + ("(" + String.valueOf(listClienti.get(i).getId()) + "," + String.valueOf(listClienti.get(i).getArrival_time()) + "," + String.valueOf(listClienti.get(i).getService_time()) + ") ");
        }
        return wait;
    }

    public void adaugareCoada(){
        for(int i = 0; i < nrCozi; i++){
            if(listClienti.size() != 0){
                if(listCozi.get(i).getClienti().size() == 0 && listClienti.get(0).getArrival_time() <= timer && listClienti.get(0).getArrival_time() <= timerSimulation){
                    listCozi.get(i).getClienti().add(listClienti.get(0));
                    listClienti.remove(0);
                }
            }
        }
    }

    public String afisareQueues(){
        String queue = "";
        for(int i = 0; i < nrCozi; i++){
            if(listCozi.get(i).getClienti().size() != 0) {
                queue = queue + ("(" + String.valueOf(listCozi.get(i).getClienti().get(0).getId()) + "," + String.valueOf(listCozi.get(i).getClienti().get(0).getArrival_time()) + "," + String.valueOf(listCozi.get(i).getClienti().get(0).getService_time()) + ") ");
            }
        }
        return queue;
    }

    public int verifCozi(){
        int ok = 0;
        for(int i = 0; i < nrCozi; i++){
            if(listCozi.get(i).getClienti().size() != 0){
                ok = 1;
            }
        }

        return ok;
    }

    @Override
    public void run() {
        BufferedWriter buffer = null;
        FileWriter file = null;

        view.setWaiting(afisareWaiting());
        for(int i = 0; i < nrCozi; i++){
            listCozi.add(new Coada());
            listCozi.get(i).start();
        }
        try {
            file = new FileWriter("afisare.txt");
            buffer = new BufferedWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(timer <= timerSimulation || verifCozi() == 1 || listClienti.size() != 0){
            adaugareCoada();

            try {
                assert buffer != null;
                buffer.write("La timpul ");
                buffer.write(String.valueOf(timer));
                buffer.write(" avem: ");
                buffer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            view.setWaiting(afisareWaiting());
            try {
                buffer.write("Lista de clienti in asteptare este: ");
                for (Client client : listClienti) {
                    buffer.write("(" + String.valueOf(client.getId()) + "," + String.valueOf(client.getArrival_time()) + "," + String.valueOf(client.getService_time()) + ") ");
                }
                buffer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            view.setQueues(afisareQueues());
            try {
                    for(int i = 0; i < nrCozi; i++) {
                        if(listCozi.get(i).getClienti().size() != 0) {
                            buffer.write("Coada ");
                            buffer.write(String.valueOf(i + 1));
                            buffer.write(" este: ");
                            buffer.write("(" + String.valueOf(listCozi.get(i).getClienti().get(0).getId()) + "," + String.valueOf(listCozi.get(i).getClienti().get(0).getArrival_time()) + "," + String.valueOf(listCozi.get(i).getClienti().get(0).getService_time()) + ") ");
                            buffer.newLine();
                        }
                        else{
                            buffer.write("Coada ");
                            buffer.write(String.valueOf(i + 1));
                            buffer.write(" este libera ");
                            buffer.newLine();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            view.setTimer(String.valueOf(timer));
            timer++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            assert buffer != null;
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.setQueues(afisareQueues());
    }
}
