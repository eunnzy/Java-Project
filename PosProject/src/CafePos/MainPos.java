package CafePos;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//포스기 화면
public class MainPos extends JFrame implements ActionListener{
	private ConnectDB db;
	private JTextArea showTotal;
	private Stock stocks;
	private int total=0;
	private JButton[] menu;
	private JTable table;
	private JButton up,down,reset,cancel,pay,stockpage,salespage,receipt;
	private int [] price = { 3500, 4000, 3800, 4300, 4000, 4500, 4200 , 4200, 4800, 4800, 5000};
	private int [] count = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	
	public MainPos() {
		
		String [] header = { "음료", "수량", "가격"};
		DefaultTableModel model = new DefaultTableModel(null,header);
		table=new JTable(model);
		table.setFont(new Font("나눔고딕",Font.PLAIN,13));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("나눔고딕", Font.BOLD, 15));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(728,47,440,405);
		
		JPanel p1 = new JPanel();
		p1.setLayout(null); 
		p1.setBounds(14,18,605,452);
		JLabel lb = new JLabel(new ImageIcon(getClass().getResource("menu.png")));
		lb.setBounds(245,20,130,40);
		lb.setFont(new Font("나눔고딕",Font.BOLD,20));
		p1.add(lb);
		
		menu=new JButton[11];
		menu[0]=new JButton("HOT 아메리카노");
		menu[0].setBounds(56,82,110,105);
		menu[1]=new JButton("ICE 아메리카노");
		menu[1].setBounds(191,82,110,105);
		menu[2]=new JButton("HOT 카페라떼");
		menu[2].setBounds(326,82,110,105);
		menu[3]=new JButton("ICE 카페라떼");
		menu[3].setBounds(459,82,110,105);
		menu[4]=new JButton("HOT 바닐라라떼");
		menu[4].setBounds(56,208,110,105);
		menu[5]=new JButton("ICE 바닐라라떼");
		menu[5].setBounds(191,208,110,105);
		menu[6]=new JButton("HOT 초코");
		menu[6].setBounds(326,208,110,105);
		menu[7]=new JButton("ICE 초코");
		menu[7].setBounds(459,208,110,105);
		menu[8]=new JButton("레몬에이드");
		menu[8].setBounds(56,333,110,105);
		menu[9]=new JButton("청포도에이드");
		menu[9].setBounds(191,333,110,105);
		menu[10]=new JButton("딸기스무디");
		menu[10].setBounds(326,333,110,105);
				
		for(int i=0; i<menu.length; i++) {
			menu[i].setFont(new Font("나눔고딕",Font.BOLD,12));
			p1.add(menu[i]);
			menu[i].addActionListener(this);
		}
		
		JPanel p2=new JPanel();
		p2.setLayout(null);
		p2.setBounds(25,455,1190,325);
		
		JLabel lb2 = new JLabel("수량");
		lb2.setBounds(931, 459, 34, 33);
		lb2.setFont(new Font("나눔고딕",Font.BOLD,17));
		up=new JButton("▲");
		up.setBounds(980, 462, 50, 33);
		down=new JButton("▼");
		down.setBounds(870,462,50,33);
		
		showTotal=new JTextArea();
		showTotal.setBounds(75,535,510,110);
		showTotal.setBackground(getContentPane().getBackground());
		showTotal.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		showTotal.setEditable(false);
		
		pay=new JButton("결제");
		pay.setBounds(729,535,105,105);
		pay.setFont(new Font("나눔고딕",Font.BOLD,15));
	
		reset=new JButton("초기화");
		reset.setFont(new Font("나눔고딕",Font.BOLD,15));
		reset.setBounds(893,535,105,105);
		
		cancel=new JButton("선택취소");
		cancel.setFont(new Font("나눔고딕",Font.BOLD,15));
		cancel.setBounds(1055,535,105,105);
	
		
		salespage=new JButton("매출관리");
		salespage.setBounds(81,715,185,70);
		salespage.setFont(new Font("나눔고딕",Font.BOLD,15));
		salespage.addActionListener(new ActionListener() {      
			public void actionPerformed(ActionEvent e) {
				new SalesGUI();
			}
		});
		
		stockpage=new JButton("재고관리");
		stockpage.setBounds(533,715,185,70);
		stockpage.setFont(new Font("나눔고딕",Font.BOLD,15));
		stockpage.addActionListener(new ActionListener() {      
			public void actionPerformed(ActionEvent e) {
				new StockGUI();
			}
		});
		
		receipt=new JButton("영수증");
		receipt.setBounds(980,715,185,70);
		receipt.setFont(new Font("나눔고딕",Font.BOLD,15));
		receipt.addActionListener(new ActionListener() {      
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().edit(new File("C:\\Users\\USER\\eclipse-workspace\\PosProject\\src\\CafePos\\receipt.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		p2.add(up);
		p2.add(lb2);
		p2.add(down);
		p2.add(showTotal);
		p2.add(pay);
		p2.add(reset);
		p2.add(cancel);
		p2.add(salespage);
		p2.add(stockpage);
		p2.add(receipt);
		
		pay.addActionListener(this);
		cancel.addActionListener(this);
		reset.addActionListener(this);
		up.addActionListener(this);
		down.addActionListener(this);
		
		add(p1);
		add(scroll);
		add(p2);
		setResizable(false);
		setTitle("포스기 화면");
		setSize(1250,830);
		setVisible(true);
	}

	public void addSalesData() {
		Coffee coffee = new Coffee();
		db=new ConnectDB();
		String name;
		int count;
		int price;
		
		for(int i=0; i<table.getRowCount(); i++) {
			name=(String)table.getValueAt(i,0);
			coffee.setName(name);
			count=(int)table.getValueAt(i,1);
			coffee.setCount(count);
			price=(int)table.getValueAt(i,2);
			coffee.setPrice(price);
			db.InsertSales(coffee);
		}
	}

	public void sendStock() {
		db=new ConnectDB();
		stocks=new Stock();
		stocks=db.getStockdata();
		String[] tablelist = new String[10] ;		
		
		for(int i=0; i<table.getRowCount(); i++) {
			String s="";
			s=(String)table.getValueAt(i,0);
			tablelist[i]=s;
			// 테이블에 있는 재고 개수			
			if(tablelist[i].equals(menu[0].getText())) { 
				stocks.setWondu( stocks.getWondu() - (count[0] * 1) );
			}
			if( tablelist[i].equals(menu[1].getText())) {
				stocks.setWondu( stocks.getWondu() - (count[0] * 1) );
			}
			if(tablelist[i].equals(menu[2].getText())) {
				stocks.setWondu( stocks.getWondu() - (count[2] * 1) );
				stocks.setMilk( stocks.getMilk() - (count[2] * 1) );
			}
			if(tablelist[i].equals(menu[3].getText())) {
				stocks.setWondu( stocks.getWondu() - (count[3] * 1) );
				stocks.setMilk( stocks.getMilk() - (count[3] * 1) );
			}
			if(tablelist[i].equals(menu[4].getText())) {
				stocks.setWondu( stocks.getWondu() - (count[4] * 1) );
				stocks.setMilk( stocks.getMilk() - (count[4] * 1) );
				stocks.setVanila( stocks.getVanila() - (count[4] * 2) );
			}
			if(tablelist[i].equals(menu[5].getText())) {
				stocks.setWondu( stocks.getWondu() - (count[5] * 1) );
				stocks.setMilk( stocks.getMilk() - (count[5] * 1) );
				stocks.setVanila( stocks.getVanila() - (count[5] * 2) );
			}
			if(tablelist[i].equals(menu[6].getText())) {
				stocks.setCocoa( stocks.getCocoa() - (count[6] * 3) );
				stocks.setMilk( stocks.getMilk() - (count[6] * 2));
			}
			if(tablelist[i].equals(menu[7].getText())) {
				stocks.setCocoa( stocks.getCocoa() - (count[7] * 3) );
				stocks.setMilk( stocks.getMilk() - (count[7] * 2) );
			}
			if(tablelist[i].equals(menu[8].getText())) {
				stocks.setLemon( stocks.getLemon() - (count[8] * 2) );
				stocks.setSparkling( stocks.getSparkling() - (count[8] * 1));
			}
			if(tablelist[i].equals(menu[9].getText())) {
				stocks.setWhitegrapes( stocks.getWhitegrapes() - (count[9] * 2) );
				stocks.setSparkling( stocks.getSparkling() - (count[9] * 1) );
			}
			if(tablelist[i].equals(menu[10].getText())) {
				stocks.setStrawberry( stocks.getStrawberry() - (count[10] * 2) );
				stocks.setMilk( stocks.getMilk() - (count[10] * 2) );
			}
		}
	}
	

	public void printReceipt() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd / HH:mm:ss ");
		String datestr = sdf.format(cal.getTime());
		String[] tablelist = new String[10] ;		
		int [] price = new int[10];
		int [] count = new int[10];
		int total=0;
		
		//BufferedWriter bw = null;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\USER\\eclipse-workspace\\PosProject\\src\\CafePos\\receipt.txt",true));){
			
			bw.write("=================== 주문 내역 ===================\n");
			bw.write("\n"+ "주문일시:" + datestr + "\n\n");
			
			bw.write("상품명 "  +"\t\t\t" +"수량"  + "\t\t" +"가격"  +"\n" );
			for(int i=0; i<table.getRowCount(); i++) {
					tablelist[i]=(String)table.getValueAt(i,0);
					count[i]=(int)table.getValueAt(i,1);
					price[i]=(int)table.getValueAt(i,2);
					String sf= String.format("%-12s \t\t %d  \t\t%d \n",tablelist[i],count[i],price[i]);
					bw.write(sf);		
					total += price[i];
			}
			bw.write("\n총 결제 금액: " + total + "\n");
			bw.write("===============================================\n\n\n");
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("메모 오류");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		JButton MBtn = (JButton)e.getSource();
		Object[] rows=new Object[3];
	
		for(int i=0; i<menu.length; i++) {
			if(e.getSource()==menu[i]) {
				rows[0]=menu[i].getText();
				rows[1]=count[i];
				rows[2]=price[i];
				model.addRow(rows);
				menu[i].setEnabled(false);
			}
		}
 
		if(e.getSource()==up) {	
			if(table.getSelectedRow()>=0) {
				int t=0;
				int num = (int)table.getValueAt(table.getSelectedRow(),1);
				int total=(int)table.getValueAt(table.getSelectedRow(),2);
				String s =(String)table.getValueAt(table.getSelectedRow(), 0);
			
				for(int i=0; i<menu.length; i++) {
					if(s==menu[i].getText()) {
						t=price[i];
						total+=t;
						count[i]++;
						model.setValueAt(count[i], table.getSelectedRow(), 1);
						model.setValueAt(total, table.getSelectedRow(), 2);
					}
				}
			}
		}
		
		if(e.getSource()==down) {
			int t=0;
			int total=(int)table.getValueAt(table.getSelectedRow(),2);
			String s =(String)table.getValueAt(table.getSelectedRow(), 0);
						
			
			for(int i=0; i<menu.length; i++) {
				if(s==menu[i].getText()) {
					if(count[i]==1) {
						model.removeRow(table.getSelectedRow());
						menu[i].setEnabled(true);
					}
					else {
						t=price[i];
						count[i]--;		
						total-=t;
						model.setValueAt(count[i], table.getSelectedRow(), 1);
						model.setValueAt(total, table.getSelectedRow(), 2);
					}
				}
			}
		}
			
		if(e.getSource()==cancel) {
			if(table.getSelectedRow()>=0) {
				String s = (String)table.getValueAt(table.getSelectedRow(),0);
				model.removeRow(table.getSelectedRow());
				for(int i=0; i<menu.length; i++) {
					if(s==menu[i].getText()) {
						menu[i].setEnabled(true);
						count[i]=1;
					}
				}
			}
		}
		
		if(e.getSource()==reset) {
			model.setRowCount(0);
			for(int i=0; i<menu.length; i++) {
				menu[i].setEnabled(true);
				count[i]=1;
				showTotal.setText("");
			}
		}
			
		int total=0;
		for(int i=0; i<table.getRowCount(); i++) {
			total += (int)table.getValueAt(i, 2);
		}
		showTotal.setText(String.valueOf(" 총 금액 : "+total));
		showTotal.setFont(new Font("나눔고딕", Font.BOLD, 40));
	
		
		if(e.getSource()==pay) {			
			sendStock();
			db.updateStock(stocks);
			addSalesData();
			printReceipt();
			JOptionPane.showMessageDialog(null,"결제 완료");
			showTotal.setText("");
			model.setRowCount(0);
			for(int i=0; i<menu.length; i++) {
				count[i]=1;	
				menu[i].setEnabled(true);
			}
		}
		
	}
}