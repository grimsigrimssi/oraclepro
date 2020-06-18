package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		
		
		PhoneDao phoneDao = new PhoneDao();
		
		
		PersonVo vo01 = new PersonVo("이효리", "010-1111-1111", "02-1111-1111");
		phoneDao.personInsert(vo01);
		
		PersonVo vo02 = new PersonVo("정우성", "010-2222-2222", "02-2222-2222");
		phoneDao.personInsert(vo02);
		
		PersonVo vo03 = new PersonVo("유재석", "010-3333-3333", "02-3333-3333");
		phoneDao.personInsert(vo03);
		
		PersonVo vo04 = new PersonVo("이정재", "010-4444-4444", "02-4444-4444");
		phoneDao.personInsert(vo04);
		
		PersonVo vo05 = new PersonVo("서장훈", "010-5555-5555", "02-5555-5555");
		phoneDao.personInsert(vo05);
		
				
		//전화번호 관리프로그램 시작
		
		System.out.println("*********************************");
		System.out.println("*         전화번호 관리 프로그램                 *");
		System.out.println("*********************************");
		
		//while 시작
		while (run) {
			
			System.out.println("");
			System.out.println("1.리스트   2.등록   3.수정   4.삭제   5.검색   6.종료");
			System.out.println("----------------------------------");
			System.out.println(">메뉴번호: ");
			
			int menuNum = sc.nextInt();
			sc.nextLine();
			
			//switch 시작
			switch (menuNum) {
			
			//1 리스트
			case 1:
				System.out.println("<1.리스트>"); 
				
				//리스트 가져오기
				List<PersonVo> personList = phoneDao.getPersonList();
						
				//출력		
				for(PersonVo vo : personList) {		
					System.out.println(vo.getPersonId() + ", " + vo.getName() + ", " + vo.getHp() + ", " + vo.getCompany());
				}
				break;
			
			//등록
			case 2:
				System.out.println("<2.등록>");
				
				//이름
				System.out.print("이름>");
				String n1 = sc.nextLine();
				
				//hp
				System.out.print("휴대전화>");
				String h1 = sc.nextLine();
				
				//company
				System.out.print("회사번호>");
				String c1 = sc.nextLine();
				
				//등록
				PersonVo vo06 = new PersonVo(n1, h1, c1);
				phoneDao.personInsert(vo06);
				
				System.out.println("[1건 등록되었습니다.]");
				break;
			
			//수정
			case 3:
				System.out.println("<3.수정>");
				
				//이름
				System.out.print("이름>");
				String n2 = sc.nextLine();
				
				//hp
				System.out.print("휴대전화>");
				String h2 = sc.nextLine();
				
				//company
				System.out.print("회사번호>");
				String c2 = sc.nextLine();
				
				//등록
				PersonVo vo07 = new PersonVo(n2, h2, c2);
				phoneDao.personUpdate(vo07);
				
				//결과 출력
				System.out.println("[1건 수정되었습니다.]");
				break;
								
			//삭제
			case 4:
				System.out.println("<4.삭제>");
				
				//이름
				System.out.print("번호>");
				int inputNum = sc.nextInt();
							
				//삭제
				phoneDao.personDelete(inputNum);
				
				//결과 출력
				System.out.println("1건 삭제되었습니다.");
				break;
				
			//검색
			case 5:
				System.out.println("<4.검색>");
				
				//이름
				System.out.print("검색어>");
				String keyword = sc.nextLine();
//				
//				List<PersonVo> searchList = phoneDao.searchPersonList();
//				
//				//출력
//				for(PersonVo vo : searchList) {
//					String searchKeyword = phoneDao.searchPersonList();
//					if (searchKeyword.contains(keyword)) {
//					System.out.println(vo.getPersonId() + ", " + vo.getName() + ", " + vo.getHp() + ", " + vo.getCompany());
//					}
//				}				
//				break;
				
			//종료
			case 6:
				run = false;
				break;
			
			}//switch 종료
			
		}//while 종료
		
		sc.close();
		
		//종료화면
		System.out.println("");
		System.out.println("*********************************");
		System.out.println("*             감사합니다                          *");
		System.out.println("*********************************");
	}

}
