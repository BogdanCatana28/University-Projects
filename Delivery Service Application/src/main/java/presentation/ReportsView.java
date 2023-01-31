package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReportsView extends JFrame {
    private JTextField Start1Field;
    private JTextField End1Field;
    private JTextArea Report1Field;
    private JTextField Amount2Field;
    private JTextField Times3Field;
    private JTextField Total3Field;
    private JTextField Day4Field;
    private JButton LogOutRBtn;
    private JButton Report1Btn;
    private JButton Report2Btn;
    private JButton Report3Btn;
    private JButton Report4Btn;

    public ReportsView() {
        this.setBounds(100, 100, 864, 621);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel Title = new JLabel("Reports Window");
        Title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        Title.setBounds(312, 27, 178, 25);
        this.getContentPane().add(Title);

        Report1Btn = new JButton("Generate Raport1");
        Report1Btn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        Report1Btn.setBounds(20, 112, 189, 36);
        this.getContentPane().add(Report1Btn);

        Report2Btn = new JButton("Generate Raport2");
        Report2Btn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        Report2Btn.setBounds(219, 112, 189, 36);
        this.getContentPane().add(Report2Btn);

        Report3Btn = new JButton("Generate Raport3");
        Report3Btn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        Report3Btn.setBounds(418, 112, 189, 36);
        this.getContentPane().add(Report3Btn);

        Report4Btn = new JButton("Generate Raport4");
        Report4Btn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        Report4Btn.setBounds(617, 112, 189, 36);
        this.getContentPane().add(Report4Btn);

        JLabel Rep1L = new JLabel("StartHour:");
        Rep1L.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        Rep1L.setBounds(66, 159, 78, 25);
        this.getContentPane().add(Rep1L);

        Start1Field = new JTextField();
        Start1Field.setBounds(61, 182, 95, 30);
        this.getContentPane().add(Start1Field);
        Start1Field.setColumns(10);

        JLabel Rep2L = new JLabel("EndHour:");
        Rep2L.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        Rep2L.setBounds(71, 217, 78, 25);
        this.getContentPane().add(Rep2L);

        End1Field = new JTextField();
        End1Field.setColumns(10);
        End1Field.setBounds(61, 241, 95, 30);
        this.getContentPane().add(End1Field);

        Report1Field = new JTextArea();
        Report1Field.setBounds(61, 282, 729, 289);
        this.getContentPane().add(Report1Field);
        Report1Field.setColumns(10);

        JLabel Rep2Label = new JLabel("Amount:");
        Rep2Label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        Rep2Label.setBounds(277, 159, 78, 25);
        this.getContentPane().add(Rep2Label);

        Amount2Field = new JTextField();
        Amount2Field.setColumns(10);
        Amount2Field.setBounds(264, 182, 95, 30);
        this.getContentPane().add(Amount2Field);

        JLabel Rep3Label = new JLabel("Times:");
        Rep3Label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        Rep3Label.setBounds(487, 159, 78, 25);
        this.getContentPane().add(Rep3Label);

        Times3Field = new JTextField();
        Times3Field.setColumns(10);
        Times3Field.setBounds(467, 182, 95, 30);
        this.getContentPane().add(Times3Field);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        lblTotal.setBounds(477, 217, 78, 25);
        this.getContentPane().add(lblTotal);

        Total3Field = new JTextField();
        Total3Field.setColumns(10);
        Total3Field.setBounds(470, 241, 95, 30);
        this.getContentPane().add(Total3Field);

        JLabel lblDay = new JLabel("Day:");
        lblDay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        lblDay.setBounds(693, 159, 31, 25);
        this.getContentPane().add(lblDay);

        Day4Field = new JTextField();
        Day4Field.setColumns(10);
        Day4Field.setBounds(636, 182, 154, 30);
        this.getContentPane().add(Day4Field);

        LogOutRBtn = new JButton("Log Out");
        LogOutRBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        LogOutRBtn.setBounds(10, 11, 121, 36);
        this.getContentPane().add(LogOutRBtn);
    }

    public String startHour(){return Start1Field.getText();}
    public String endHour(){return End1Field.getText();}
    public String amount(){return Amount2Field.getText();}
    public String times(){return Times3Field.getText();}
    public String total(){return Total3Field.getText();}
    public String day(){return Day4Field.getText();}
    public void Report1(ActionListener action){ Report1Btn.addActionListener(action);}
    public void Report2(ActionListener action){ Report2Btn.addActionListener(action);}
    public void Report3(ActionListener action){ Report3Btn.addActionListener(action);}
    public void Report4(ActionListener action){ Report4Btn.addActionListener(action);}
    public void LogOutR(ActionListener action){ LogOutRBtn.addActionListener(action);}

    public void setReport1Field(String report1Field) {
        Report1Field.setText(report1Field);
    }
}
