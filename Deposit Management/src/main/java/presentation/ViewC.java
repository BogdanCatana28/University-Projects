package presentation;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class ViewC extends JFrame {
    private JTextField idClient;
    private JTextField numeClient;
    private JTextField addressClient;
    private JTextField idRemove;
    private JButton AddButton;
    private JButton EditButton;
    private JButton RemoveButton;
    private JButton ShowButton;
    private JButton BackButton;

    public ViewC() {
        this.setBounds(100, 100, 950, 743);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Client Window");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        titleLabel.setBounds(338, 32, 185, 56);
        this.getContentPane().add(titleLabel);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        idLabel.setBounds(47, 201, 62, 39);
        this.getContentPane().add(idLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        nameLabel.setBounds(47, 279, 81, 39);
        this.getContentPane().add(nameLabel);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        addressLabel.setBounds(46, 357, 123, 39);
        this.getContentPane().add(addressLabel);

        idClient = new JTextField();
        idClient.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idClient.setBounds(159, 205, 62, 39);
        this.getContentPane().add(idClient);

        numeClient = new JTextField();
        numeClient.setFont(new Font("Tahoma", Font.PLAIN, 15));
        numeClient.setColumns(10);
        numeClient.setBounds(159, 279, 136, 39);
        this.getContentPane().add(numeClient);

        addressClient = new JTextField();
        addressClient.setFont(new Font("Tahoma", Font.PLAIN, 15));
        addressClient.setColumns(10);
        addressClient.setBounds(159, 357, 223, 39);
        this.getContentPane().add(addressClient);

        AddButton = new JButton("Add Client");
        AddButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        AddButton.setBounds(46, 441, 194, 56);
        this.getContentPane().add(AddButton);

        EditButton = new JButton("Edit Client");
        EditButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        EditButton.setBounds(47, 526, 194, 56);
        this.getContentPane().add(EditButton);

        RemoveButton = new JButton("Remove Client with ID:");
        RemoveButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        RemoveButton.setBounds(47, 619, 279, 56);
        this.getContentPane().add(RemoveButton);

        idRemove = new JTextField();
        idRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idRemove.setColumns(10);
        idRemove.setBounds(349, 629, 62, 39);
        this.getContentPane().add(idRemove);

        ShowButton = new JButton("Show Clients");
        ShowButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        ShowButton.setBounds(589, 144, 194, 56);
        this.getContentPane().add(ShowButton);

        BackButton = new JButton("<-");
        BackButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        BackButton.setBounds(24, 20, 62, 50);
        this.getContentPane().add(BackButton);
    }
    public void GoBack(ActionListener action) {BackButton.addActionListener(action);}

    public int idC() {
        return Integer.parseInt(idClient.getText());
    }
    public String nameC() {
        return numeClient.getText();
    }
    public String addressC() {
        return addressClient.getText();
    }
    public void ShowClients(ActionListener action) {ShowButton.addActionListener(action);}
    public void AddClient(ActionListener action) {AddButton.addActionListener(action);}
    public void EditClient(ActionListener action) {EditButton.addActionListener(action);}
    public void RemoveCLient(ActionListener action) {RemoveButton.addActionListener(action);}
    public int idCR() {
        return Integer.parseInt(idRemove.getText());
    }
}