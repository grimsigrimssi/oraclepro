package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PhoneDao {
		// 0. import java.sql.*;
		private	Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		
		//필드
		private String driver = "oracle.jdbc.driver.OracleDriver";
		private String url = "jdbc:oracle:thin:@localhost:1521:xe";
		private String id = "phonedb";
		private String pw = "phonedb";
			
		//생성자
		public PhoneDao() {}
		
		//g/s
		
		//일반메소드
		
		//connection 가져오기
		private void getConnect() { //메소드를 private으로 바꾸어 외부에서 사용할 수 없게 함
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);

				// 2. Connection 얻어오기	 --암호 세팅	
				conn = DriverManager.getConnection(url, id, pw); // 쿼리로 만들기
				System.out.println("접속 성공"); 	
					
				}catch  (ClassNotFoundException e) {
					System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
					System.out.println("error:" + e);				
				}
				
			}
			
		private void close() {
			try {
				if (rs != null) {
					rs.close();
				}if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
		}
		
		//person 추가
		public void personInsert(PersonVo personVo) {
			getConnect();
			
			try {
				
				// 3. SQL문 준비 / 바인딩 / 실행
				
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " INSERT INTO person ";
				query += " VALUES (seq_person_id.nextval, ?, ?, ?) ";
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기
				
				pstmt.setString(1, personVo.getName());  // ?(물음표) 중 1번째, 순서중요
				pstmt.setString(2, personVo.getHp()); // ?(물음표) 중 2번째, 순서중요
				pstmt.setString(3, personVo.getCompany()); // ?(물음표) 중 3번째, 순서중요

				int count = pstmt.executeUpdate(); // 쿼리문 실행

				// 4.결과처리
				System.out.println(count + "건 처리되었습니다.");
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			//자원 정리
			close();
		}
		
		//person 수정
		public void personUpdate(PersonVo personVo) {
			getConnect();

			try {

				// 3. SQL문 준비 / 바인딩 / 실행
				
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " update person ";
				query += " set 	name = ?, ";
				query += " 		hp = ?, ";
				query += " 		company = ? ";
				query += " where person_id = ? ";

				
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기
				
				pstmt.setString(1, personVo.getName());  // ?(물음표) 중 1번째, 순서중요
				pstmt.setString(2, personVo.getHp()); // ?(물음표) 중 2번째, 순서중요
				pstmt.setString(3, personVo.getCompany()); // ?(물음표) 중 3번째, 순서중요
				pstmt.setInt(4, personVo.getPersonId()); // ?(물음표) 중 3번째, 순서중요
				
				int count = pstmt.executeUpdate(); // 쿼리문 실행

				// 4.결과처리
				System.out.println(count + "건 처리되었습니다.");

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			//자원 정리
			close();
			
		}
				
		//작가 삭제
		public void personDelete(int personId) {
			//Connection
			getConnect();		

			try {
							
				// 3. SQL문 준비 / 바인딩 / 실행  --> 완성된 sql문을 가져와서 작성할것		
				String query = "";
				query += " delete from person";
				query += " where person_id = ?";
				pstmt =conn.prepareStatement(query); //쿼리로 만들기
				
				pstmt.setInt(1, personId);// ?(물음표) 중 1번째, 순서중요
					
				int count = pstmt.executeUpdate(); //쿼리문 실행

				// 4.결과처리
				
					System.out.println(count + "건 처리되었습니다.");
		
			} catch (SQLException e) {
			System.out.println("error:" + e);
			}
			//자원 정리
			close();
		}
		
	public List<PersonVo> getPersonList() {
		//리스트 준비
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		//connection
		getConnect();
		
		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행  --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " select  	person_id, ";
			query += "			name, ";
			query += "			hp, ";
			query += "			company ";
			query += " from person";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); //쿼리로 만들기
					
			rs = pstmt.executeQuery(); //실행
			
			// 4.결과처리
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				//리스트에 추가
				PersonVo personVo = new PersonVo(personId, name, hp, company);
				personList.add(personVo);
				
				//System.out.println(authorId + "\t" + authorName + "\t" + authorDesc);
			}

		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		//자원정리
		close();
	
		return personList;
	}
}
