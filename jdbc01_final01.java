package JDBC01;

import java.sql.*;
import java.util.*;

/*
 * 회원가입,조회,삭제,수정,전체조회의 기능을 갖는 프로그램 작성

CREATE TABLE MEMS
(
ID VARCHAR2(10) PRIMARY KEY, --아이디
PWD VARCHAR2(10), --비밀번호
EMAIL VARCHAR2(15), --이메일
PHONE VARCHAR2(15), --전화번호
REGDATE DATE --가입일
);

#회원가입할때는 아이디중복 검사를 하세요.
예) 사용할 아이디입력 : song
song는 이미 사용중인 아이디입니다.

#검색은 아이디로 조회
 */

class JDBC04 {
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	Scanner scan=new Scanner(System.in);
	public JDBC04() {
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 성공!");
			
			//2. DB구현 및 Connection객체 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "c##scott", "tiger");
			System.out.println("DB접속 성공!");
			
			//3. Statement 얻어오기
			stmt = con.createStatement();
			
		} catch (ClassNotFoundException ce) {
			System.out.println(ce.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} 
	}
	
	/*
	 * 4. DB구현 해제
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
	 * 회원가입
	 */
	public void insert() throws SQLException {
		System.out.println("<< 회원가입 >>");
		System.out.println("가입할 아이디를 입력하세요");
		String id = scan.next();
		System.out.println("가입할 비밀번호를 입력하세요");
		String pwd = scan.next();
		System.out.println("가입할 이메일을 입력하세요");
		String email = scan.next();
		System.out.println("가입할 핸드폰번호를 입력하세요");
		String phone = scan.next();
		System.out.println("가입일을 입력하세요");
		String regdate = scan.next();
		String sql = "INSERT INTO MEMS VALUES('"+id+"', '"+pwd+"', '"+email+"', '"+phone+"', '"+regdate+"')";
		stmt.executeUpdate(sql);
		System.out.println("<< 회원가입 완료! >>");
	}
	
	/*
	 * 조회
	 */
	public void select() throws SQLException{
		System.out.println("조회할 아이디를 입력하세요");
		String id = scan.next();
		String sql = "SELECT * FROM MEMS WHERE ID='"+id+"'";
		rs = stmt.executeQuery(sql);
		if (rs.next()) {
			System.out.println("<< 조회된 회원정보 >>");
			String idd = rs.getString("id");
			String pwd = rs.getString("pwd");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			String regdate = rs.getString("regdate");
			System.out.println("아이디 : "+idd);
			System.out.println("비밀번호 : "+pwd);
			System.out.println("이메일 : "+email);
			System.out.println("번호 : "+phone);
			System.out.println("가입일 : "+regdate);
		} else {
			System.out.println("조회할 아이디가 없습니다");
		}
	}
	
	/*
	 * 삭제
	 */
	public void delete() throws SQLException{
		System.out.println("삭제할 아이디를 입력하세요");
		String id = scan.next();
		String sql = "DELETE FROM MEMBERS WHERE ID = "+id;
		stmt.executeUpdate(sql);
		System.out.println(id+" 회원 삭제 완료!");
	}
	
	/*
	 * 수정
	 */
	public void update() throws SQLException {
		System.out.println("수정할 회원아이디를 입력하세요");
		String id = scan.next();
		System.out.println("수정할 회원의 비밀번호를 입력하세요");
		String pwd = scan.next();
		String sql = "UPDATE MEMS SET PWD='pwd' WHERE ID='id'";
		stmt.executeUpdate(sql);
		System.out.println(id+" 회원 수정 완료!");
	}
	
	/*
	 * 전체조회
	 */
	public void select_all() throws SQLException{
		System.err.println("<< 전체 데이터 조회 >>");
		String sql = "SELECT * FROM MEMS";
		rs = stmt.executeQuery(sql);
		rs.next();
		String id = rs.getString("id");
		String pwd = rs.getString("pwd");
		String email = rs.getString("email");
		String phone = rs.getString("phone");
		String regdate = rs.getString("regdate");
		System.out.println("아이디 : "+id);
		System.out.println("비밀번호 : "+pwd);
		System.out.println("이메일 : "+email);
		System.out.println("번호 : "+phone);
		System.out.println("가입일 : "+regdate);
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
