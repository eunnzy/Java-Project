package CafePos;

import java.sql.*;
import java.util.*;


public class ConnectDB {
	private String url = "jdbc:mysql://localhost:3306/cafe_db?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
	private String user = "root";
	private String password = "dmswl12";
	Connection con;
	Statement st;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public Connection makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 적재 성공");
			con=DriverManager.getConnection(url,user,password);
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch(SQLException e) {
			System.out.println("연결에 실패하였습니다.");
			e.printStackTrace();
		}
		return con;
	}
	 	
	public void dbClose() {
		try {
            if (rs != null)
            	rs.close();
            if (st != null) 
            	st.close();
            if (pstmt != null) 
            	pstmt.close();
        } catch (Exception e) {
            System.out.println(e + "=> dbClose fail");
        }
	}
	
	public int InsertSales(Coffee coffee) {
		int result=0;
		try {
			con=makeConnection();
			String sql = "INSERT INTO sales VALUE (?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, coffee.getName());
			pstmt.setInt(2, coffee.getCount());
			pstmt.setInt(3, coffee.getPrice());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("커피 상품별 판매 데이터 추가 오류");
		}finally {
			dbClose();
		}
		return result;
	}
	
	// DB에서 stock 데이터 받기
	public Stock getStockdata() {
		Stock stock=null;
		
		try {
			con=makeConnection();
			String sql = "SELECT * FROM stocks";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				stock = new Stock();
				stock.setWondu(rs.getInt("wondu"));
				stock.setWhitegrapes(rs.getInt("whitegrapes"));
				stock.setStrawberry(rs.getInt("strawberry"));
				stock.setMilk(rs.getInt("milk"));
				stock.setLemon(rs.getInt("lemon"));
				stock.setCocoa(rs.getInt("cocoa"));
				stock.setVanila(rs.getInt("vanila"));
				stock.setSparkling(rs.getInt("sparkling"));
			}
			
		}catch(Exception e) {
			System.out.println("getStockdata() 오류");
			e.printStackTrace();
		}finally {
			try {
				dbClose();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return stock;
	}

	
	// DB에 수정된 재고 업데이트
	public int updateStock(Stock s) {
		int result = 0;
		String sql = "UPDATE stocks SET wondu=?, whitegrapes=?, strawberry=?, milk=?, lemon=?, cocoa=?, vanila=?, sparkling=?";
		
		try {
			
			con=makeConnection();
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1,s.getWondu());
			pstmt.setInt(2,s.getWhitegrapes());
			pstmt.setInt(3,s.getStrawberry());
			pstmt.setInt(4,s.getMilk());
			pstmt.setInt(5,s.getLemon());
			pstmt.setInt(6,s.getCocoa());
			pstmt.setInt(7,s.getVanila());
			pstmt.setInt(8,s.getSparkling());
			
			result=pstmt.executeUpdate();	
		//	con.commit();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("업데이트 오류");
		} finally {
			dbClose();
		}
		return result;
	}
	
	public Vector getMenuSales() {
		Vector data = new Vector();
		
		try {
			con=makeConnection();
			String sql =  "SELECT name,sum(count),sum(price) FROM sales GROUP BY name ORDER BY sum(price) DESC";// 수량 순서로 내림차순 정렬
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Coffee coffee= new Coffee();
				String name = rs.getString("name");
				int count = rs.getInt("sum(count)");
				int price = rs.getInt("sum(price)");

				Vector row = new Vector();
				row.add(name);
				row.add(count);
				row.add(price);
				
				data.add(row);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("카페 주문목록 실패");
		}finally {
			dbClose();
		}
		return data;
	}

	public Vector getStockList() {
		Vector data = new Vector();
		
		try {
			con=makeConnection();
			String sql = "SELECT * FROM stocks";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int wondu=rs.getInt("wondu");
				int whitegrapes=rs.getInt("whitegrapes");
				int strawberry=rs.getInt("strawberry");
				int milk=rs.getInt("milk");
				int lemon=rs.getInt("lemon");
				int cocoa=rs.getInt("cocoa");
				int vanila=rs.getInt("vanila");
				int sparkling=rs.getInt("sparkling");
				
				Vector row = new Vector();
				row.add(wondu);
				row.add(whitegrapes);
				row.add(strawberry);
				row.add(milk);
				row.add(lemon);
				row.add(cocoa);
				row.add(vanila);
				row.add(sparkling);
				
				data.add(row);
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
		return data;
	}
}
