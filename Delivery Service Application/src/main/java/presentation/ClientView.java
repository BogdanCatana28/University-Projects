package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private JTextField titleFieldC;
    private JTextField ratingFieldC;
    private JTextField caloriesFieldC;
    private JTextField proteinsFieldC;
    private JTextField fatsFieldC;
    private JTextField sodiumFieldC;
    private JTextField priceFieldC;
    private JTable table;
    private JButton LogOutC;
    private JButton SearchBtn;
    private JButton OrderBtn;

    public ClientView() {
        this.setBounds(100, 100, 864, 621);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel TitleLabel = new JLabel("Client Window");
        TitleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        TitleLabel.setBounds(322, 37, 155, 32);
        this.getContentPane().add(TitleLabel);

        LogOutC = new JButton("Log Out");
        LogOutC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        LogOutC.setBounds(10, 11, 121, 36);
        this.getContentPane().add(LogOutC);

        titleFieldC = new JTextField();
        titleFieldC.setBounds(168, 124, 86, 20);
        this.getContentPane().add(titleFieldC);
        titleFieldC.setColumns(10);

        JLabel titleLabel = new JLabel("Title");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        titleLabel.setBounds(168, 100, 86, 25);
        this.getContentPane().add(titleLabel);

        JLabel ratingLabel = new JLabel("Rating");
        ratingLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        ratingLabel.setBounds(168, 155, 86, 25);
        this.getContentPane().add(ratingLabel);

        ratingFieldC = new JTextField();
        ratingFieldC.setColumns(10);
        ratingFieldC.setBounds(168, 175, 86, 20);
        this.getContentPane().add(ratingFieldC);

        JLabel caloriesLabel = new JLabel("Calories");
        caloriesLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        caloriesLabel.setBounds(168, 206, 86, 25);
        this.getContentPane().add(caloriesLabel);

        caloriesFieldC = new JTextField();
        caloriesFieldC.setColumns(10);
        caloriesFieldC.setBounds(168, 228, 86, 20);
        this.getContentPane().add(caloriesFieldC);

        JLabel proteinLabel = new JLabel("Proteins");
        proteinLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        proteinLabel.setBounds(168, 259, 86, 25);
        this.getContentPane().add(proteinLabel);

        proteinsFieldC = new JTextField();
        proteinsFieldC.setColumns(10);
        proteinsFieldC.setBounds(168, 275, 86, 20);
        this.getContentPane().add(proteinsFieldC);

        JLabel fatsLabel = new JLabel("Fats");
        fatsLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        fatsLabel.setBounds(168, 295, 86, 25);
        this.getContentPane().add(fatsLabel);

        fatsFieldC = new JTextField();
        fatsFieldC.setColumns(10);
        fatsFieldC.setBounds(168, 316, 86, 20);
        this.getContentPane().add(fatsFieldC);

        JLabel sodiumLabel = new JLabel("Sodium");
        sodiumLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        sodiumLabel.setBounds(168, 342, 86, 25);
        this.getContentPane().add(sodiumLabel);

        sodiumFieldC = new JTextField();
        sodiumFieldC.setColumns(10);
        sodiumFieldC.setBounds(168, 362, 86, 20);
        this.getContentPane().add(sodiumFieldC);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        priceLabel.setBounds(168, 393, 86, 25);
        this.getContentPane().add(priceLabel);

        priceFieldC = new JTextField();
        priceFieldC.setColumns(10);
        priceFieldC.setBounds(168, 415, 86, 20);
        this.getContentPane().add(priceFieldC);

        SearchBtn = new JButton("Search");
        SearchBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        SearchBtn.setBounds(22, 108, 110, 46);
        this.getContentPane().add(SearchBtn);

        OrderBtn = new JButton("Order");
        OrderBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        OrderBtn.setBounds(22, 174, 110, 46);
        this.getContentPane().add(OrderBtn);
    }

    public String titleC(){return titleFieldC.getText();}
    public String ratingC(){return ratingFieldC.getText();}
    public String caloriesC(){return caloriesFieldC.getText();}
    public String proteinsC(){return proteinsFieldC.getText();}
    public String fatsC(){return fatsFieldC.getText();}
    public String sodiumC(){return sodiumFieldC.getText();}
    public String priceC(){return priceFieldC.getText();}
    public void SearchProduct(ActionListener action){ SearchBtn.addActionListener(action);}
    public void PlaceOrder(ActionListener action){ OrderBtn.addActionListener(action);}
    public void LogOutC(ActionListener action){ LogOutC.addActionListener(action);}
}

