package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JTextField QueueAfis;
    private JTextField WatingClients;
    private JButton StartButton;
    private JTextArea Clients;
    private JTextArea Queues;
    private JTextArea SimBox;
    private JTextArea TimeBox;
    private JTextArea ArrMin;
    private JTextArea ArrMax;
    private JTextArea SerMin;
    private JTextArea SerMax;

    public View() {
        this.setBounds(100, 100, 1119, 765);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("QUEUES MANAGEMENT APPLICATION");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(360, 27, 492, 65);
        this.getContentPane().add(lblNewLabel);

        Clients = new JTextArea();
        Clients.setBounds(203, 117, 169, 51);
        this.getContentPane().add(Clients);

        JLabel NrClients = new JLabel("Number of Clients");
        NrClients.setFont(new Font("Tahoma", Font.BOLD, 18));
        NrClients.setBounds(27, 117, 169, 51);
        this.getContentPane().add(NrClients);

        JLabel NrQueues = new JLabel("Number of Queues");
        NrQueues.setFont(new Font("Tahoma", Font.BOLD, 18));
        NrQueues.setBounds(27, 211, 182, 51);
        this.getContentPane().add(NrQueues);

        Queues = new JTextArea();
        Queues.setBounds(203, 211, 169, 51);
        this.getContentPane().add(Queues);

        JLabel Simulation = new JLabel("Simulation Interval");
        Simulation.setFont(new Font("Tahoma", Font.BOLD, 18));
        Simulation.setBounds(472, 117, 182, 51);
        this.getContentPane().add(Simulation);

        SimBox = new JTextArea();
        SimBox.setBounds(472, 163, 182, 51);
        this.getContentPane().add(SimBox);

        JLabel Timer = new JLabel("Timer");
        Timer.setFont(new Font("Tahoma", Font.BOLD, 18));
        Timer.setBounds(532, 247, 61, 51);
        this.getContentPane().add(Timer);

        TimeBox = new JTextArea();
        TimeBox.setBounds(472, 289, 182, 51);
        this.getContentPane().add(TimeBox);

        JLabel Arrival = new JLabel("ArrivalTime Lapse");
        Arrival.setFont(new Font("Tahoma", Font.BOLD, 18));
        Arrival.setBounds(732, 117, 169, 51);
        this.getContentPane().add(Arrival);

        JLabel Service = new JLabel("ServiceTime Lapse");
        Service.setFont(new Font("Tahoma", Font.BOLD, 18));
        Service.setBounds(732, 211, 182, 51);
        this.getContentPane().add(Service);

        ArrMin = new JTextArea();
        ArrMin.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ArrMin.setBounds(911, 117, 61, 51);
        this.getContentPane().add(ArrMin);

        ArrMax = new JTextArea();
        ArrMax.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ArrMax.setBounds(1001, 117, 61, 51);
        this.getContentPane().add(ArrMax);

        SerMin = new JTextArea();
        SerMin.setFont(new Font("Monospaced", Font.PLAIN, 14));
        SerMin.setBounds(911, 211, 61, 51);
        this.getContentPane().add(SerMin);

        SerMax = new JTextArea();
        SerMax.setFont(new Font("Monospaced", Font.PLAIN, 14));
        SerMax.setBounds(1001, 211, 61, 51);
        this.getContentPane().add(SerMax);

        QueueAfis = new JTextField();
        QueueAfis.setBounds(27, 329, 345, 367);
        this.getContentPane().add(QueueAfis);
        QueueAfis.setColumns(10);

        WatingClients = new JTextField();
        WatingClients.setColumns(10);
        WatingClients.setBounds(732, 329, 345, 367);
        this.getContentPane().add(WatingClients);

        StartButton = new JButton("START");
        StartButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        StartButton.setBounds(472, 501, 182, 51);
        this.getContentPane().add(StartButton);

        this.setVisible(true);
    }

    public int clients() {
        return Integer.parseInt(Clients.getText());
    }

    public int queues() {
        return Integer.parseInt(Queues.getText());
    }

    public int simTime() {
        return Integer.parseInt(SimBox.getText());
    }

    public int arrTimeMin() {
        return Integer.parseInt(ArrMin.getText());
    }

    public int arrTimeMax() {
        return Integer.parseInt(ArrMax.getText());
    }

    public int serTimeMin() {
        return Integer.parseInt(SerMin.getText());
    }

    public int serTimeMax() {
        return Integer.parseInt(SerMax.getText());
    }

    public void setTimer(String textArea) {
        this.TimeBox.setText(textArea);
    }

    public void setQueues(String textArea) {
        this.QueueAfis.setText(textArea);
    }

    public void setWaiting(String textArea) {
        this.WatingClients.setText(textArea);
    }

    public void start(ActionListener action) {
        StartButton.addActionListener(action);
    }
}
