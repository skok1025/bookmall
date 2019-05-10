package bookmall.test;

import bookmall.dao.CategoryDao;

public class _1CategoryDaoTest {
	
	public static void main(String[] args) {
		System.out.println("카테고리 insert 성공: "+categoryinsert("소설"));
		System.out.println("카테고리 insert 성공: "+categoryinsert("수필"));
		System.out.println("카테고리 insert 성공: "+categoryinsert("컴퓨터/IT"));
		System.out.println("카테고리 insert 성공: "+categoryinsert("인문"));
		System.out.println("카테고리 insert 성공: "+categoryinsert("경제"));
		System.out.println("카테고리 insert 성공: "+categoryinsert("예술"));
		System.out.println("카테고리 입력완료");
	}
	
	public static Boolean categoryinsert(String name) {
		return new CategoryDao().insert(name);
	}

}
