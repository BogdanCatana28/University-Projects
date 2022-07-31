package model;

import java.util.LinkedList;

public class Coada extends Thread {
    private LinkedList<Client> clienti;

    public Coada() {
        this.clienti = new LinkedList<>();
    }

    public LinkedList<Client> getClienti() {
        return clienti;
    }

    public void setClienti(LinkedList<Client> clienti) {
        this.clienti = clienti;
    }


    @Override
    public void run() {
        while(true){
            for(int i = 0; i < clienti.size(); i++){
                if(clienti.get(i).getService_time() > 0){
                    clienti.get(i).setService_time(clienti.get(i).getService_time() - 1);
                }
                else{
                    clienti.remove(i);
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
