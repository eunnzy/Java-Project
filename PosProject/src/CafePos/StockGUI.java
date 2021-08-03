package CafePos;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class StockGUI extends JFrame{
	private Vector stocklist;
	private Vector header;
	private ConnectDB db;
	private DefaultTableModel model;
	private Stock stock;
	private JTextField stockname, amount;
	private JButton addbtn,newbtn;
	private JTable table;
		
	public StockGUI() {
		stock=new Stock();
		
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(25,8,200,48);
		JLabel lb1 = new JLabel("��� ��Ȳ");
		lb1.setFont(new Font("�������",Font.BOLD,15));
		lb1.setBounds(20,20,100,29);
		newbtn=new JButton();
		newbtn.setBounds(90,20,30,30);
		newbtn.setIcon(new ImageIcon(getClass().getResource("new.png")));
		newbtn.setOpaque(false);
		newbtn.setContentAreaFilled(false);
		newbtn.setBorderPainted(false);
		newbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stocklist=db.getStockList();
				header=getHeader();
				table=new JTable(stocklist,header);
				table.setBounds(36,50,929,97);
				table.setRowHeight(25);
				table.setFont(new Font("�������",Font.PLAIN,13));
				table.getTableHeader().setFont(new Font("�������", Font.BOLD, 15));
				JScrollPane scroll = new JScrollPane(table);
				scroll.setBounds(36,70,929,70);
				add(scroll);
			}
		});
		
		db=new ConnectDB();
		stocklist=db.getStockList();
		header=getHeader();
		table=new JTable(stocklist,header);
		table.setBounds(36,50,929,97);
		table.setRowHeight(25);
		table.setFont(new Font("�������",Font.PLAIN,13));
		table.getTableHeader().setFont(new Font("�������", Font.BOLD, 15));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(36,70,929,70);
		
		JPanel p2 = new JPanel();
		p2.setLayout(null);
		p2.setBounds(46,100,900,300);
		 
		JLabel txt = new JLabel("�԰��� ��� �Է�");
		txt.setBounds(168,120,126,37);
		txt.setFont(new Font("�������",Font.BOLD,15));
		
		JLabel lb2 = new JLabel("�̸�:");
		lb2.setBounds(321,147,54,29);
		lb2.setFont(new Font("�������",Font.BOLD,14));
		
		JLabel lb3 = new JLabel("����:");
		lb3.setBounds(321,190,54,29);
		lb3.setFont(new Font("�������",Font.BOLD,14));
		
		stockname=new JTextField(10);
		stockname.setBounds(383,147,172,29);	
		stockname.setFont(new Font("�������",Font.PLAIN,14));
	
		amount = new JTextField(10);
		amount.setBounds(383,190,172,29);
		amount.setFont(new Font("�������",Font.PLAIN,14));
		amount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addCheck()==true) {
					db.updateStock(stock);
					JOptionPane.showMessageDialog(null,stockname.getText()+" �԰� �Ϸ�");
					stockname.setText("");
					amount.setText("");
				}
				else{
					stockname.setText("");
					amount.setText("");
					JOptionPane.showMessageDialog(null,"��� �̸��� �ٽ� Ȯ���� �ּ���.");
				}
			}
		});
		
		addbtn=new JButton("�԰�");
		addbtn.setBounds(584,210,71,29);
		addbtn.addActionListener(new ActionListener() {      
			public void actionPerformed(ActionEvent e) {	
				if(addCheck()==true) {
						db.updateStock(stock);
						JOptionPane.showMessageDialog(null,stockname.getText()+" �԰� �Ϸ�");
						stockname.setText("");
						amount.setText("");
				}
				else{
					stockname.setText("");
					amount.setText("");
					JOptionPane.showMessageDialog(null,"��� �̸��� �ٽ� Ȯ���� �ּ���.");
				}
			}
		});
		
		p1.add(lb1);
		p1.add(newbtn);
		p2.add(txt);
		p2.add(lb2);
		p2.add(stockname);
		p2.add(lb3);
		p2.add(amount);
		p2.add(addbtn);
		
		add(p1);
		add(scroll);
		add(p2);
		
		setResizable(false);
		setLayout(null);
		setTitle("��� ��Ȳ");
		setSize(1000,400);
		setVisible(true);
	}
	public Vector getHeader() {
		Vector header= new Vector();
		header.add("����");
		header.add("û����");
		header.add("����");
		header.add("����");
		header.add("����");
		header.add("���ھư���");
		header.add("�ٴҶ�÷�");
		header.add("ź���");
		return header;
	}

	public boolean addCheck() {
		ConnectDB db= new ConnectDB();
		stock=db.getStockdata();
		if(stockname.getText().equals("����")) {
			stock.setWondu(stock.getWondu()+(Integer.parseInt(amount.getText())));
			return true;
		}
		if(stockname.getText().equals("û����")) {
			stock.setWhitegrapes(stock.getWhitegrapes()+(Integer.parseInt(amount.getText())));
			return true;
		}
		if(stockname.getText().equals("����")) {
			stock.setStrawberry(stock.getStrawberry()+(Integer.parseInt(amount.getText())));
			return true;
		}
		if(stockname.getText().equals("����")) {
			stock.setMilk(stock.getMilk()+(Integer.parseInt(amount.getText())));
			return true;
		}
		if(stockname.getText().equals("����")) {
			stock.setLemon(stock.getLemon()+(Integer.parseInt(amount.getText())));
			return true;
		}
		if(stockname.getText().equals("���ھư���") || stockname.getText().equals("���ھ�")) {
			stock.setCocoa(stock.getCocoa()+(Integer.parseInt(amount.getText())));
			return true;
		}
		if(stockname.getText().equals("�ٴҶ�÷�") || stockname.getText().equals("�ٴҶ�")) {
			stock.setVanila(stock.getVanila()+(Integer.parseInt(amount.getText())));
			return true;
		}
		if(stockname.getText().equals("ź���")) {
			stock.setSparkling(stock.getSparkling()+(Integer.parseInt(amount.getText())));
			return true;
		}
		return false;
	}

}
