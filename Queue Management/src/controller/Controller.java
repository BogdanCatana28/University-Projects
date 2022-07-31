package controller;

import model.Simulator;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;
    private Simulator simulator;

    public Controller(View view) {

        this.view = view;
        this.view.start(new ExtractData());
    }

    class ExtractData implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            simulator = new Simulator(view);
            simulator.setArrivalTimerMax(view.arrTimeMax());
            simulator.setArrivalTimerMin(view.arrTimeMin());
            simulator.setServiceTimerMax(view.serTimeMax());
            simulator.setServiceTimerMin(view.serTimeMin());
            simulator.setTimerSimulation(view.simTime());
            simulator.setNrClienti(view.clients());
            simulator.setNrCozi(view.queues());
            simulator.adaugaClient();
            Thread threadSim = new Thread(simulator);
            threadSim.start();
        }
    }


}
