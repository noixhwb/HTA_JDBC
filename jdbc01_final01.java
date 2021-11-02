package JDBC01;

import java.sql.*;
import java.util.*;

/*
 * ȸ������,��ȸ,����,����,��ü��ȸ�� ����� ���� ���α׷� �ۼ�

CREATE TABLE MEMS
(
ID VARCHAR2(10) PRIMARY KEY, --���̵�
PWD VARCHAR2(10), --��й�ȣ
EMAIL VARCHAR2(15), --�̸���
PHONE VARCHAR2(15), --��ȭ��ȣ
REGDATE DATE --������
);

#ȸ�������Ҷ��� ���̵��ߺ� �˻縦 �ϼ���.
��) ����� ���̵��Է� : song
song�� �̹� ������� ���̵��Դϴ�.

#�˻��� ���̵�� ��ȸ
 */

class JDBC04 {
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	Scanner scan=new Scanner(System.in);
	public JDBC04() {
		try {
			//1. ����̹� �ε�
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("����̹� �ε� ����!");
			
			//2. DB���� �� Connection��ü ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "c##scott", "tiger");
			System.out.println("DB���� ����!");
			
			//3. Statement ������
			stmt = con.createStatement();
			
		} catch (ClassNotFoundException ce) {
			System.out.println(ce.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} 
	}
	
	/*
	 * 4. DB���� ����
	 */
	public void disconnect() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (con != null) con.close();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	
	/*
	 * ȸ������
	 */
	public void insert() throws SQLException {
		System.out.println("<< ȸ������ >>");
		System.out.println("������ ���̵� �Է��ϼ���");
		String id = scan.next();
		System.out.println("������ ��й�ȣ�� �Է��ϼ���");
		String pwd = scan.next();
		System.out.println("������ �̸����� �Է��ϼ���");
		String email = scan.next();
		System.out.println("������ �ڵ�����ȣ�� �Է��ϼ���");
		String phone = scan.next();
		System.out.println("�������� �Է��ϼ���");
		String regdate = scan.next();
		String sql = "INSERT INTO MEMS VALUES('"+id+"', '"+pwd+"', '"+email+"', '"+phone+"', '"+regdate+"')";
		stmt.executeUpdate(sql);
		System.out.println("<< ȸ������ �Ϸ�! >>");
	}
	
	/*
	 * ��ȸ
	 */
	public void select() throws SQLException{
		System.out.println("��ȸ�� ���̵� �Է��ϼ���");
		String id = scan.next();
		String sql = "SELECT * FROM MEMS WHERE ID='"+id+"'";
		rs = stmt.executeQuery(sql);
		if (rs.next()) {
			System.out.println("<< ��ȸ�� ȸ������ >>");
			String idd = rs.getString("id");
			String pwd = rs.getString("pwd");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			String regdate = rs.getString("regdate");
			System.out.println("���̵� : "+idd);
			System.out.println("��й�ȣ : "+pwd);
			System.out.println("�̸��� : "+email);
			System.out.println("��ȣ : "+phone);
			System.out.println("������ : "+regdate);
		} else {
			System.out.println("��ȸ�� ���̵� �����ϴ�");
		}
	}
	
	/*
	 * ����
	 */
	public void delete() throws SQLException{
		System.out.println("������ ���̵� �Է��ϼ���");
		String id = scan.next();
		String sql = "DELETE FROM MEMBERS WHERE ID = "+id;
		stmt.executeUpdate(sql);
		System.out.println(id+" ȸ�� ���� �Ϸ�!");
	}
	
	/*
	 * ����
	 */
	public void update() throws SQLException {
		System.out.println("������ ȸ�����̵� �Է��ϼ���");
		String id = scan.next();
		System.out.println("������ ȸ���� ��й�ȣ�� �Է��ϼ���");
		String pwd = scan.next();
		String sql = "UPDATE MEMS SET PWD='pwd' WHERE ID='id'";
		stmt.executeUpdate(sql);
		System.out.println(id+" ȸ�� ���� �Ϸ�!");
	}
	
	/*
	 * ��ü��ȸ
	 */
	public void select_all() throws SQLException{
		System.err.println("<< ��ü ������ ��ȸ >>");
		String sql = "SELECT * FROM MEMS";
		rs = stmt.executeQuery(sql);
		rs.next();
		String id = rs.getString("id");
		String pwd = rs.getString("pwd");
		String email = rs.getString("email");
		String phone = rs.getString("phone");
		String regdate = rs.getString("regdate");
		System.out.println("���̵� : "+id);
		System.out.println("��й�ȣ : "+pwd);
		System.out.println("�̸��� : "+email);
		System.out.println("��ȣ : "+phone);
		System.out.println("������ : "+regdate);
	}
}


public class jdbc01_final01 {
	public static void main(String[] args) {
		JDBC04 jdbc=new JDBC04();
		try {
			//jdbc.insert();
			//jdbc.select();
			jdbc.update();
			//jdbc.delete();
			//jdbc.select_all();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			jdbc.disconnect();
		}
	}
}
