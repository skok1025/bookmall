package bookmall.test;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {
	
	public static void main(String[] args) {
		System.out.println("카트 insert 성공: "+cartinsert(1L, 1L, 2));
		System.out.println("카트 insert 성공: "+cartinsert(2L, 1L, 3));
		System.out.println("카트 입력완료");
	}
	public static Boolean cartinsert(Long bookNo,Long memberNo,int cnt) {
		CartVo vo = new CartVo();
		vo.setBookNo(bookNo);
		vo.setMemberNo(memberNo);
		vo.setCnt(cnt);
		
		return new CartDao().insert(vo);
	}

}
