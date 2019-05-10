package bookmall.test;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class _2BookDaoTest {
	
	public static void main(String[] args) {
		System.out.println("책 insert 성공:"+bookinsert("햣키엔 수필", "15000", 2L));
		System.out.println("책 insert 성공:"+bookinsert("너의 췌장을 먹고 싶어", "13800", 1L));
		System.out.println("책 insert 성공:"+bookinsert("스페인 예술로 걷다", "17600", 6L));
		System.out.println("책 입력완료");
	}
	
	public static Boolean bookinsert(String title,String price,Long categoryNo) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategoryNo(categoryNo);
		
		return new BookDao().insert(vo);
	}

}
