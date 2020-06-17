package com.javaex.phone;

import java.util.List;

public class PhoneApp {

	public static void main(String[] args) {
		
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
		
		//수정
		PersonVo vo06 = new PersonVo(4, "이정재", "010-9999-9999", "02-9999-9999");
		phoneDao.personUpdate(vo06);
		
		//삭제
		phoneDao.personDelete(5);
		
		//리스트 가져오기
		
		List<PersonVo> personList = phoneDao.getPersonList();
		//System.out.println(personList.get(1).getPersonId()); //
				
		//출력
		System.out.println("===========================================");
		for(PersonVo vo : personList) {		
			System.out.println(vo.getPersonId() + ", " + vo.getName() + ", " + vo.getHp() + ", " + vo.getCompany());
		}
		System.out.println("===========================================");
	}

}
