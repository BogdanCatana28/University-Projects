package presentation;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;

public class AdminView extends JFrame{
    private JButton AddBtn;
    private JButton EditBtn;
    private JButton ShowBtn;
    private JButton DelBtn;
    private JButton ImportBtn;
    private JButton LogOutBtn;
    private JButton CompBtn;
    private JButton ReportsBtn;
    private JTextField titleField;
    private JTextField ratingField;
    private JTextField caloriesField;
    private JTextField proteinsField;
    private JTextField fatsField;
    private JTextField sodiumField;
    private JTextField priceField;



    public AdminView() {
        this.setBounds(100, 100, 864, 621);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel Title = new JLabel("Admin Window");
        Title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        Title.setBounds(312, 27, 162, 25);
        this.getContentPane().add(Title);

        AddBtn = new JButton("Add Product");
        AddBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        AddBtn.setBounds(21, 159, 162, 36);
        this.getContentPane().add(AddBtn);

        EditBtn = new JButton("Edit Product");
        EditBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        EditBtn.setBounds(21, 206, 162, 36);
        this.getContentPane().add(EditBtn);

        ShowBtn = new JButton("Show Product");
        ShowBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        ShowBtn.setBounds(21, 253, 162, 36);
        this.getContentPane().add(ShowBtn);

        DelBtn = new JButton("Delete Product");
        DelBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        DelBtn.setBounds(21, 300, 162, 36);
        this.getContentPane().add(DelBtn);

        ImportBtn = new JButton("Import Product");
        ImportBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        ImportBtn.setBounds(21, 347, 162, 36);
        this.getContentPane().add(ImportBtn);

        LogOutBtn = new JButton("Log Out");
        LogOutBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        LogOutBtn.setBounds(10, 11, 121, 36);
        this.getContentPane().add(LogOutBtn);

        titleField = new JTextField();
        titleField.setBounds(208, 124, 86, 20);
        this.getContentPane().add(titleField);
        titleField.setColumns(10);

        JLabel titleLabel = new JLabel("Title");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        titleLabel.setBounds(208, 100, 86, 25);
        this.getContentPane().add(titleLabel);

        JLabel ratingLabel = new JLabel("Rating");
        ratingLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        ratingLabel.setBounds(208, 155, 86, 25);
        this.getContentPane().add(ratingLabel);

        ratingField = new JTextField();
        ratingField.setColumns(10);
        ratingField.setBounds(208, 175, 86, 20);
        this.getContentPane().add(ratingField);

        JLabel caloriesLabel = new JLabel("Calories");
        caloriesLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        caloriesLabel.setBounds(208, 206, 86, 25);
        this.getContentPane().add(caloriesLabel);

        caloriesField = new JTextField();
        caloriesField.setColumns(10);
        caloriesField.setBounds(208, 228, 86, 20);
        this.getContentPane().add(caloriesField);

        JLabel proteinLabel = new JLabel("Proteins");
        proteinLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        proteinLabel.setBounds(208, 259, 86, 25);
        this.getContentPane().add(proteinLabel);

        proteinsField = new JTextField();
        proteinsField.setColumns(10);
        proteinsField.setBounds(208, 280, 86, 20);
        this.getContentPane().add(proteinsField);

        JLabel fatsLabel = new JLabel("Fats");
        fatsLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        fatsLabel.setBounds(208, 306, 86, 25);
        this.getContentPane().add(fatsLabel);

        fatsField = new JTextField();
        fatsField.setColumns(10);
        fatsField.setBounds(208, 324, 86, 20);
        this.getContentPane().add(fatsField);

        JLabel sodiumLabel = new JLabel("Sodium");
        sodiumLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        sodiumLabel.setBounds(208, 347, 86, 25);
        this.getContentPane().add(sodiumLabel);

        sodiumField = new JTextField();
        sodiumField.setColumns(10);
        sodiumField.setBounds(208, 368, 86, 20);
        this.getContentPane().add(sodiumField);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        priceLabel.setBounds(208, 393, 86, 25);
        this.getContentPane().add(priceLabel);

        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setBounds(208, 415, 86, 20);
        this.getContentPane().add(priceField);

        CompBtn = new JButton("Composite ");
        CompBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        CompBtn.setBounds(21, 394, 162, 36);
        this.getContentPane().add(CompBtn);

        ReportsBtn = new JButton("Reports");
        ReportsBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        ReportsBtn.setBounds(21, 443, 162, 36);
        this.getContentPane().add(ReportsBtn);
    }

    public void AddProduct(ActionListener action){ AddBtn.addActionListener(action);}
    public void EditProduct(ActionListener action){ EditBtn.addActionListener(action);}
    public void RemoveProduct(ActionListener action){ DelBtn.addActionListener(action);}
    public void ShowProduct(ActionListener action){ ShowBtn.addActionListener(action);}
    public void ImportProduct(ActionListener action){ ImportBtn.addActionListener(action);}
    public void LogOut(ActionListener action){ LogOutBtn.addActionListener(action);}
    public void CreateComposite(ActionListener action){ CompBtn.addActionListener(action);}
    public void OpenReports(ActionListener action){ ReportsBtn.addActionListener(action);}
    public String title(){return titleField.getText();}
    public String rating(){return ratingField.getText();}
    public String calories(){return caloriesField.getText();}
    public String proteins(){return proteinsField.getText();}
    public String fats(){return fatsField.getText();}
    public String sodium(){return sodiumField.getText();}
    public String price(){return priceField.getText();}

    public JButton getShowBtn() {
        return ShowBtn;
    }
}

