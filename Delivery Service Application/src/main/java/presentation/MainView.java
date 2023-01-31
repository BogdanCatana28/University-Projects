package presentation;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private JTextField UserText;
    private JTextField PassText;
    private JComboBox TypeBox;
    private JButton LoginBtn;
    private JButton RegisterBtn;

    public MainView() {
        this.setBounds(100, 100, 864, 621);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel Title = new JLabel("Food Delivery Management Application");
        Title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        Title.setBounds(223, 46, 408, 63);
        this.getContentPane().add(Title);

        JLabel User = new JLabel("Username:");
        User.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        User.setBounds(268, 170, 106, 29);
        this.getContentPane().add(User);

        JLabel Pass = new JLabel("Password:");
        Pass.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        Pass.setBounds(268, 226, 106, 29);
        this.getContentPane().add(Pass);

        UserText = new JTextField();
        UserText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        UserText.setBounds(409, 173, 135, 29);
        this.getContentPane().add(UserText);
        UserText.setColumns(10);

        PassText = new JPasswordField();
        PassText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        PassText.setBounds(409, 226, 135, 29);
        this.getContentPane().add(PassText);

        LoginBtn = new JButton("Login");
        LoginBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        LoginBtn.setBounds(339, 283, 124, 42);
        this.getContentPane().add(LoginBtn);

        RegisterBtn = new JButton("Register");
        RegisterBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        RegisterBtn.setBounds(339, 491, 124, 42);
        this.getContentPane().add(RegisterBtn);

        TypeBox = new JComboBox();
        TypeBox.setModel(new DefaultComboBoxModel(new String[] {"Client", "Employee"}));
        TypeBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        TypeBox.setBounds(339, 392, 124, 29);
        this.getContentPane().add(TypeBox);

        JLabel RegisterLabel = new JLabel("Or select one and register:");
        RegisterLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        RegisterLabel.setBounds(292, 347, 222, 24);
        this.getContentPane().add(RegisterLabel);

        this.setVisible(true);
    }

    public void loginButton(ActionListener action){ LoginBtn.addActionListener(action);}
    public void registerButton(ActionListener action){ RegisterBtn.addActionListener(action);}
    public int getComboType(){ return TypeBox.getSelectedIndex();}
    public String username(){return UserText.getText();}
    public String password(){return PassText.getText();}
}

