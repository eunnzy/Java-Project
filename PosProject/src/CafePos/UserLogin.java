package CafePos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

//���α׷� ����� �α��� ȭ��
public class UserLogin extends JFrame {
	private MainProcess main;
	private MainPos mainPos;	
	private String id = "user";
	private String password="1234";
	private JTextField userId;
	private JPasswordField userPassword;
	private JButton login;
	private boolean check=false;
	private Image background;
	private final int wide=650;
	private final int height=450;

	
	UserLogin() {
		background = new ImageIcon(getClass().getResource("LoginPhoto.png")).getImage();
		MyPanel panel = new MyPanel();
		panel.setLayout(null);

		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("cafepos.png")));
		lb.setBounds(150,20,300,80);
		
		JLabel user = new JLabel("���̵�: ");
		user.setBounds(168,120,71,25);
		user.setFont(new Font("�������",Font.BOLD,15));
		userId=new JTextField(15);
		userId.setFont(new Font("�������",Font.PLAIN,15));
		userId.setBounds(261,120,160,25);
		
		JLabel passlabel = new JLabel("��й�ȣ: ");
		passlabel.setBounds(168,170,71,25);
		passlabel.setFont(new Font("�������",Font.BOLD,15));
		userPassword=new JPasswordField(15);
		userPassword.setFont(new Font("�������",Font.PLAIN,15));
		userPassword.setBounds(261,170,160,25);
		userPassword.addActionListener(new ActionListener() {          
	            public void actionPerformed(ActionEvent e) {
	                LogCheck();        
	            }
	        });
		
		login=new JButton("�α���");
		login.setBounds(423,220,80,25);
		login.setFont(new Font("�������",Font.BOLD,15));
		login.setBackground(new Color(198,217,241));
		login.addActionListener(new ActionListener() {          
	            public void actionPerformed(ActionEvent e) {
	                LogCheck();        
	            }
	        });
		
		panel.add(lb);
		panel.add(user);
		panel.add(userId);
		panel.add(passlabel);
		panel.add(userPassword);
		panel.add(login);
		add(panel);
		setResizable(false);
		setLayout(new GridLayout(0,1));
		setLocation(400,150);
		setSize(650,450);
		setTitle("������ �α���");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public void LogCheck() {
		if(userId.getText().contentEquals(id) && userPassword.getText().equals(password)) {
			check=true;
			if(check==true) {
				main.showFrameTest();
			}
		}
		else {
			JOptionPane.showConfirmDialog(null,"�ٽ� �Է����ּ���");
		}
	}
	class MyPanel extends JPanel {
	public void paint(Graphics g) {
		g.drawImage(background,0,0,this);
		paintComponents(g);
		}
	}

	public void setMain(MainProcess main) {
		this.main=main;
	}
	
}
