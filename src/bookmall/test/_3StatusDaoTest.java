package bookmall.test;

import bookmall.dao.StatusDao;

public class _3StatusDaoTest {
	public static void main(String[] args) {
		System.out.println("상태 insert 성공: "+statusinsert("출고 준비"));
		System.out.println("상태 insert 성공: "+statusinsert("출고 완료"));
		System.out.println("상태 insert 성공: "+statusinsert("배송 중"));
		System.out.println("상태 insert 성공: "+statusinsert("배송 완료"));
		System.out.println("상태 insert 성공: "+statusinsert("주문 취소"));
		System.out.println("주문 상태입력완료");
	}
	public static Boolean statusinsert(String name) {
		
		return new StatusDao().insert(name);
	}

}
