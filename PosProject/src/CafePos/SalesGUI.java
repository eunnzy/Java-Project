package CafePos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SalesGUI extends JFrame implements ActionListener{
	private JTable table;
	private JPanel p1;
	private ConnectDB db;
	private JButton newbtn;
	private JTextField showTotal;
	private Vector header;
	
	public SalesGUI() {
		db = new ConnectDB();
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(23,13,550,620);
		
		JLabel lb = new JLabel("»óÇ°º° ÁÖ¹®·® ¹× ¸ÅÃâ");
		lb.setBounds(43,28,245,32);
		lb.setFont(new Font("³ª´®°íµñ",Font.BOLD,15));
		newbtn = new JButton(new ImageIcon(getClass().getResource("new.png")));
		newbtn.setBounds(185,28,30,30);
		newbtn.setContentAreaFilled(false);
		newbtn.setBorderPainted(false);
		newbtn.addActionListener(this);
		header= getHeader();
		table=new JTable(db.getMenuSales(),header);
		table.setBounds(44, 74, 420, 420);
		table.setRowHeight(25);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(44,74,430,410);
		table.getTableHeader().setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		
		JLabel lb2 = new JLabel("ÃÑ¸ÅÃâ");
		lb2.setBounds(195,512,65,33);
		lb2.setFont(new Font("³ª´®°íµñ",Font.BOLD,15));
		showTotal = new JTextField();
		showTotal.setBounds(276,512,185,33);
		showTotal.setEditable(false);
		
		p1.add(lb);
		p1.add(newbtn);
		p1.add(scroll);
		p1.add(lb2);
		p1.add(showTotal);
		add(p1);
		setTitle("¸ÅÃâ ÇöÈ²");
		setSize(550,640);
		setResizable(false);
		setVisible(true);
	}
	
	     
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==newbtn) {
				db = new ConnectDB();
				header= getHeader();
				table=new JTable(db.getMenuSales(),header);
				table.setBounds(44, 74, 420, 420);
				table.setRowHeight(25);
				JScrollPane scroll = new JScrollPane(table);
				scroll.setBounds(44,74,430,410);
				table.getTableHeader().setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
				p1.add(scroll);
				
				int total=0;
				for(int i=0; i<table.getRowCount(); i++) {
					total += (int)table.getValueAt(i, 2);
				}
				showTotal.setText(String.valueOf("\t"+total));
				showTotal.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
			}
		}
	
	
	public Vector getHeader() {
		Vector header= new Vector();
		header.add("»óÇ°¸í");
		header.add("ÆÇ¸Å ¼ö·®");
		header.add("ÆÇ¸Å¾×");
		return header;
	}
	
}
