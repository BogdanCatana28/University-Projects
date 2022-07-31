package view;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;

public class View extends JFrame{

	private JFrame frame;
	private JTextField P1text;
	private JTextField P2text;
	private JTextField ResultText;
	private JButton AddButton;
	private JButton SubButton;
	private JButton MultButton;
	private JButton DivButton;
	private JButton DerButton;
	private JButton IntButton;
	
	public View() {
		this.setBounds(100, 100, 804, 616);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel TitluLabel = new JLabel("POLYNOMIAL CALCULATOR");
		TitluLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		TitluLabel.setBounds(219, 41, 336, 48);
		this.getContentPane().add(TitluLabel);

		JLabel InsertLabel = new JLabel("Insert the polynoms:");
		InsertLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		InsertLabel.setBounds(27, 130, 228, 35);
		this.getContentPane().add(InsertLabel);

		P1text = new JTextField();
		P1text.setFont(new Font("Tahoma", Font.PLAIN, 18));
		P1text.setBounds(27, 197, 321, 35);
		this.getContentPane().add(P1text);
		P1text.setColumns(10);

		P2text = new JTextField();
		P2text.setFont(new Font("Tahoma", Font.PLAIN, 18));
		P2text.setColumns(10);
		P2text.setBounds(27, 284, 321, 35);
		this.getContentPane().add(P2text);

		AddButton = new JButton("+");
		AddButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		AddButton.setBounds(501, 182, 54, 54);
		this.getContentPane().add(AddButton);

		SubButton = new JButton("-");
		SubButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		SubButton.setBounds(620, 182, 54, 54);
		this.getContentPane().add(SubButton);

		MultButton = new JButton("x");
		MultButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		MultButton.setBounds(501, 269, 54, 54);
		this.getContentPane().add(MultButton);

		DivButton = new JButton("/");
		DivButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		DivButton.setBounds(620, 269, 54, 54);
		this.getContentPane().add(DivButton);

		JLabel OpLabel = new JLabel("Choose the operation:");
		OpLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		OpLabel.setBounds(493, 130, 228, 35);
		this.getContentPane().add(OpLabel);

		ResultText = new JTextField();
		ResultText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ResultText.setBounds(27, 403, 403, 155);
		this.getContentPane().add(ResultText);
		ResultText.setColumns(10);

		DerButton = new JButton("Derivative");
		DerButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		DerButton.setBounds(501, 351, 173, 54);
		this.getContentPane().add(DerButton);

		IntButton = new JButton("Integration");
		IntButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		IntButton.setBounds(501, 430, 173, 54);
		this.getContentPane().add(IntButton);

		JLabel ResultLabel = new JLabel("Result:");
		ResultLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		ResultLabel.setBounds(27, 351, 228, 35);
		this.getContentPane().add(ResultLabel);

		this.setVisible(true);
	}

	public JTextField getP1text() {
		return P1text;
	}

	/*public void setP1text(JTextField p1text) {
		P1text = p1text;
	}*/

	public void setP1text(String p1text) {
		this.P1text.setText(p1text);
	}

	public JTextField getP2text() {
		return P2text;
	}

	/*public void setP2text(JTextField p2text) {
		P2text = p2text;
	}*/

	public void setP2text(String p2text) {
		this.P2text.setText(p2text);
	}

	public JTextField getResultText() {
		return ResultText;
	}

	/*public void setResultText(JTextField resultText) {
		ResultText = resultText;
	}*/


	public JButton getAddButton() {
		return AddButton;
	}

	public void setAddButton(JButton addButton) {
		AddButton = addButton;
	}

	public JButton getSubButton() {
		return SubButton;
	}

	public void setSubButton(JButton subButton) {
		SubButton = subButton;
	}

	public JButton getMultButton() {
		return MultButton;
	}

	public void setMultButton(JButton multButton) {
		MultButton = multButton;
	}

	public JButton getDivButton() {
		return DivButton;
	}

	public void setDivButton(JButton divButton) {
		DivButton = divButton;
	}

	public JButton getDerButton() {
		return DerButton;
	}

	public void setDerButton(JButton derButton) {
		DerButton = derButton;
	}

	public JButton getIntButton() {
		return IntButton;
	}

	public void setIntButton(JButton intButton) {
		IntButton = intButton;
	}

	public void addPoli(ActionListener action){
		AddButton.addActionListener(action);
	}
	public void subPoli(ActionListener action){
		SubButton.addActionListener(action);
	}
	public void mulPoli(ActionListener action){
		MultButton.addActionListener(action);
	}
	public void derPoli(ActionListener action){ DerButton.addActionListener(action);}
	public void intPoli(ActionListener action){ IntButton.addActionListener(action);}

	public String poli1Text(){
		return P1text.getText();
	}
	public String poli2Text(){
		return P2text.getText();
	}

	public void setText(String textArea){
		this.ResultText.setText(textArea);
	}
}

