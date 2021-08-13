package Calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorFrame extends JFrame implements ActionListener{
	private JTextField text;
	private JButton[] button;
	private JButton plus, minus, divide, multiple, cancel, result;
	private String op="+";
	private int num1;
	
	public CalculatorFrame() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel(new GridLayout(4,4,5,5));
	
		text = new JTextField(14);
		text.setFont(new Font("Serif",Font.BOLD,35));
		text.setHorizontalAlignment(JTextField.RIGHT);
		text.setEnabled(false);
		p1.add(text);
		
		button = new JButton[10];
		button[0] = new JButton("1");
		button[1] = new JButton("2");
		button[2] = new JButton("3");
		plus = new JButton("+");
		
		for(int i=0; i<3; i++) {
			button[i].addActionListener(this);
			p2.add(button[i]);
		}
		p2.add(plus);
		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cur = text.getText();
				num1 = Integer.parseInt(cur);
				text.setText("");
				op = "+";
			}
		});
		
		button[3] = new JButton("4");
		button[4] = new JButton("5");
		button[5] = new JButton("6");
		minus = new JButton("-");
		
		for(int i=3; i<6; i++) {
			button[i].addActionListener(this);
			p2.add(button[i]);
		}
		p2.add(minus);
		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cur = text.getText();
				num1 = Integer.parseInt(cur);
				text.setText("");
				op = "-";
			}
		});
		
		button[6] = new JButton("7");
		button[7] = new JButton("8");
		button[8] = new JButton("9");
		multiple = new JButton("X");
		
		for(int i=6; i<9; i++) {
			button[i].addActionListener(this);
			p2.add(button[i]);
		}
		p2.add(multiple);
		multiple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cur = text.getText();
				num1 = Integer.parseInt(cur);
				text.setText("");
				op = "X";
			}
		});
		
		cancel = new JButton("C");
		button[9] = new JButton("0");
		result = new JButton("=");
		divide = new JButton("/");
		
		divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cur = text.getText();
				num1 = Integer.parseInt(cur);
				text.setText("");
				op = "/";
			}
		});

		result.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(op) {
					case "+": {
						int num2 = Integer.parseInt(text.getText());
						text.setText((num1+num2)+"");
						break;
					}
					case "-": {
						int num2 = Integer.parseInt(text.getText());
						text.setText((num1-num2)+"");
						break;
					}
					case "X":{
						int num2 = Integer.parseInt(text.getText());
						text.setText((num1*num2)+"");
						break;
					}
					case "/": {
						int num2 = Integer.parseInt(text.getText());
						text.setText((num1/num2)+"");
						break;
					}
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText("");
				op = "";
			}
		});
		
		button[9].addActionListener(this);
		
		p2.add(cancel);
		p2.add(button[9]);
		p2.add(result);
		p2.add(divide);
		
		add(p1,BorderLayout.NORTH);
		add(p2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Calculator");
		setSize(430,550);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String str = text.getText();
		if(str.equals("0")) {
			for(int i=0; i<10; i++) {
				if(button[i] == e.getSource())
					text.setText(button[i].getText());
			}
		}
		else {
			for(int i=0; i<10; i++) {
				if(button[i] == e.getSource())
					text.setText(str+button[i].getText());
			}
		}
		
	}
}
