package presentation;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewP extends JFrame {
    private JTextField priceProduct;
    private JTextField nameProduct;
    private JTextField idProduct;
    private JTextField idRemoveP;
    private JTextField stockProduct;
    private JButton AddButtonP;
    private JButton RemoveButtonP;
    private JButton EditButtonP;
    private JButton ShowButtonP;
    private JButton BackButtonP;

    public ViewP() {
        this.setBounds(100, 100, 950, 743);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Product Window");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        titleLabel.setBounds(338, 32, 214, 56);
        this.getContentPane().add(titleLabel);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        idLabel.setBounds(47, 131, 62, 39);
        this.getContentPane().add(idLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        nameLabel.setBounds(47, 203, 81, 39);
        this.getContentPane().add(nameLabel);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        priceLabel.setBounds(47, 271, 123, 39);
        this.getContentPane().add(priceLabel);

        AddButtonP = new JButton("Add Product");
        AddButtonP.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        AddButtonP.setBounds(46, 441, 194, 56);
        this.getContentPane().add(AddButtonP);

        EditButtonP = new JButton("Edit Product");
        EditButtonP.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        EditButtonP.setBounds(47, 526, 194, 56);
        this.getContentPane().add(EditButtonP);

        RemoveButtonP = new JButton("Remove Product with ID:");
        RemoveButtonP.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        RemoveButtonP.setBounds(47, 619, 285, 56);
        this.getContentPane().add(RemoveButtonP);

        ShowButtonP = new JButton("Show Products");
        ShowButtonP.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        ShowButtonP.setBounds(589, 144, 194, 56);
        this.getContentPane().add(ShowButtonP);

        priceProduct = new JTextField();
        priceProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
        priceProduct.setColumns(10);
        priceProduct.setBounds(138, 273, 89, 39);
        this.getContentPane().add(priceProduct);

        nameProduct = new JTextField();
        nameProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nameProduct.setColumns(10);
        nameProduct.setBounds(138, 205, 196, 39);
        this.getContentPane().add(nameProduct);

        idProduct = new JTextField();
        idProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idProduct.setColumns(10);
        idProduct.setBounds(136, 133, 62, 39);
        this.getContentPane().add(idProduct);

        idRemoveP = new JTextField();
        idRemoveP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idRemoveP.setColumns(10);
        idRemoveP.setBounds(352, 630, 62, 39);
        this.getContentPane().add(idRemoveP);

        BackButtonP = new JButton("<-");
        BackButtonP.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        BackButtonP.setBounds(24, 20, 62, 50);
        this.getContentPane().add(BackButtonP);

        JLabel stockLabel = new JLabel("Stock:");
        stockLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        stockLabel.setBounds(47, 353, 123, 39);
        this.getContentPane().add(stockLabel);

        stockProduct = new JTextField();
        stockProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
        stockProduct.setColumns(10);
        stockProduct.setBounds(138, 353, 89, 39);
        this.getContentPane().add(stockProduct);
    }

    public void GoBackP(ActionListener action) {BackButtonP.addActionListener(action);}
    public int idP() {
        return Integer.parseInt(idProduct.getText());
    }
    public String nameP() {
        return nameProduct.getText();
    }
    public int priceP() {
        return Integer.parseInt(priceProduct.getText());
    }
    public int stockP() {
        return Integer.parseInt(stockProduct.getText());
    }
    public void ShowProducts(ActionListener action) {ShowButtonP.addActionListener(action);}
    public void AddProduct(ActionListener action) {AddButtonP.addActionListener(action);}
    public void EditProduct(ActionListener action) {EditButtonP.addActionListener(action);}
    public void RemoveProduct(ActionListener action) {RemoveButtonP.addActionListener(action);}
    public int idPR() {return Integer.parseInt(idRemoveP.getText());}
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this,message);
    }
}

