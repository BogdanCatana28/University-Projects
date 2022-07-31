package presentation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MainView extends JFrame{
    private JButton cWindowButton;
    private JButton pWindowButton;
    private JButton oWindowButton;

    public MainView() {
        this.setBounds(100, 100, 950, 743);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblOrderManagement = new JLabel("Order Management");
        lblOrderManagement.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        lblOrderManagement.setBounds(320, 40, 234, 56);
        this.getContentPane().add(lblOrderManagement);

        cWindowButton = new JButton("Open Client Window");
        cWindowButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        cWindowButton.setBounds(316, 176, 268, 68);
        this.getContentPane().add(cWindowButton);

        pWindowButton = new JButton("Open Product Window");
        pWindowButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        pWindowButton.setBounds(316, 330, 268, 68);
        this.getContentPane().add(pWindowButton);

        oWindowButton = new JButton("Open Order Window");
        oWindowButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        oWindowButton.setBounds(316, 482, 268, 68);
        this.getContentPane().add(oWindowButton);

        this.setVisible(true);
    }
    public void OpenClient(ActionListener action) {cWindowButton.addActionListener(action);}
    public void OpenProduct(ActionListener action) {pWindowButton.addActionListener(action);}
    public void OpenOrder(ActionListener action) {oWindowButton.addActionListener(action);}
}

