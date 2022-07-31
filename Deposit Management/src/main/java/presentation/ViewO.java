package presentation;

import model.Client;
import model.Product;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewO extends  JFrame{
    private JTextField quantityO;
    private JButton showOrders;
    private JButton BackButtonO;
    private JButton orderButton;
    private JComboBox productCombo;
    private JComboBox clientCombo;

    public ViewO() {
        this.setBounds(100, 100, 950, 743);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Order Window");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        titleLabel.setBounds(338, 32, 214, 56);
        this.getContentPane().add(titleLabel);

        JLabel clientSelect = new JLabel("Select Client:");
        clientSelect.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        clientSelect.setBounds(47, 201, 144, 39);
        this.getContentPane().add(clientSelect);

        JLabel selectProduct = new JLabel("Select Product:");
        selectProduct.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        selectProduct.setBounds(243, 201, 167, 39);
        this.getContentPane().add(selectProduct);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        quantityLabel.setBounds(158, 453, 123, 39);
        this.getContentPane().add(quantityLabel);

        showOrders = new JButton("Show Orders");
        showOrders.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        showOrders.setBounds(579, 125, 194, 56);
        this.getContentPane().add(showOrders);

        orderButton = new JButton("Place Order");
        orderButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        orderButton.setBounds(121, 614, 194, 56);
        this.getContentPane().add(orderButton);

        clientCombo = new JComboBox();
        clientCombo.setBounds(47, 260, 144, 39);
        this.getContentPane().add(clientCombo);

        productCombo = new JComboBox();
        productCombo.setBounds(243, 260, 144, 39);
        this.getContentPane().add(productCombo);

        quantityO = new JTextField();
        quantityO.setBounds(144, 519, 144, 39);
        this.getContentPane().add(quantityO);

        BackButtonO = new JButton("<-");
        BackButtonO.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        BackButtonO.setBounds(24, 20, 62, 50);
        this.getContentPane().add(BackButtonO);
    }
    public void GoBackO(ActionListener action) {BackButtonO.addActionListener(action);}
    public int quantity() {
        return Integer.parseInt(quantityO.getText());
    }
    public void ShowOrders(ActionListener action) {showOrders.addActionListener(action);}
    public void AddOrder(ActionListener action) {orderButton.addActionListener(action);}
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this,message);
    }

    public String getProductCombo() {
        return productCombo.getSelectedItem().toString();
    }

    public void setProductCombo(List<String> productCombo) {
        this.productCombo.removeAllItems();
        for(String product : productCombo){
            this.productCombo.addItem(product);
        }
    }

    public String getClientCombo() {
        return clientCombo.getSelectedItem().toString();
    }

    public void setClientCombo(List<String> clientCombo) {
        this.clientCombo.removeAllItems();
        for(String client : clientCombo){
            this.clientCombo.addItem(client);
        }
    }
}

