package bookmall.main;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.dao.StatusDao;
import bookmall.util.OrderBookDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrdersVo;

public class FirstInsertDaoTest {

	public static void main(String[] args) {
		System.out.println("카테고리 insert 성공: "+categoryinsert("소설"));
		System.out.println("카테고리 insert 성공: "+categoryinsert("수필"));
		System.out.println("카테고리 insert 성공: "+categoryinsert("컴퓨터/IT"));
		System.out.println("카테고리 insert 성공: "+categoryinsert("인문"));
		System.out.println("카테고리 insert 성공: "+categoryinsert("경제"));
		System.out.println("카테고리 insert 성공: "+categoryinsert("예술"));
		System.out.println("카테고리 입력완료");
		
		System.out.println("멤버 insert 성공:"+memberinsert("김석현", "010-6866-9202", "skok1025@naver.com", "1234"));
		System.out.println("멤버 insert 성공:"+memberinsert("유서정", "010-2626-8234", "uscasc@naver.com", "1234"));
		System.out.println("멤버 입력완료");

		System.out.println("책 insert 성공:"+bookinsert("햣키엔 수필", 15000, 2L));
		System.out.println("책 insert 성공:"+bookinsert("너의 췌장을 먹고 싶어", 13800, 1L));
		System.out.println("책 insert 성공:"+bookinsert("스페인 예술로 걷다", 17600, 6L));
		System.out.println("책 입력완료");

		System.out.println("카트 insert 성공: "+cartinsert(1L, 1L, 2));
		System.out.println("카트 insert 성공: "+cartinsert(2L, 1L, 3));
		System.out.println("카트 입력완료");

		System.out.println("상태 insert 성공: "+statusinsert("출고 준비"));
		System.out.println("상태 insert 성공: "+statusinsert("출고 완료"));
		System.out.println("상태 insert 성공: "+statusinsert("배송 중"));
		System.out.println("상태 insert 성공: "+statusinsert("배송 완료"));
		System.out.println("상태 insert 성공: "+statusinsert("주문 취소"));
		System.out.println("주문 상태입력완료");

		String businesscode = OrderDao.getBusinesscode();

		System.out.println("주문1 insert 성공:" + ordersinsert(businesscode, "서울시 성동구 성수동", 1L, 1L));

		Long orderno = OrderDao.getCurrentOrderNo();
		Long bookNo = 1L;
		int cnt = 2;
		
		System.out.println("주문2 insert 성공:" + orderbookinsert(orderno, bookNo, cnt));
		
		orderpaymentupdate(orderno,bookNo, cnt);
	}

	private static void orderpaymentupdate(Long orderno, Long bookNo, int cnt) {
		OrdersVo vo = new OrdersVo();
		vo.setNo(orderno);
		vo.setBookNo(bookNo);
		vo.setCnt(cnt);
		
		new OrderDao().orderpaymentupdate(vo);
	}

	public static Boolean categoryinsert(String name) {
		return new CategoryDao().insert(name);
	}

	public static Boolean memberinsert(String name, String tel, String email, String passwd) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setTel(tel);
		vo.setEmail(email);
		vo.setPasswd(passwd);

		return new MemberDao().insert(vo);
	}

	public static Boolean bookinsert(String title, int price, Long categoryNo) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategoryNo(categoryNo);

		return new BookDao().insert(vo);
	}

	public static Boolean cartinsert(Long bookNo, Long memberNo, int cnt) {
		CartVo vo = new CartVo();
		vo.setBookNo(bookNo);
		vo.setMemberNo(memberNo);
		vo.setCnt(cnt);

		return new CartDao().insert(vo);
	}

	public static Boolean statusinsert(String name) {

		return new StatusDao().insert(name);
	}

	public static Boolean ordersinsert(String businesscode,String destination,Long memberNo,Long statusNo) {
		OrdersVo vo = new OrdersVo();
		vo.setBusinesscode(businesscode);
		vo.setDestination(destination);
		vo.setMemberNo(memberNo);
		vo.setStatusNo(statusNo);
		
		return new OrderDao().insert(vo);
	}
	
	public static Boolean orderbookinsert(Long orderNo,Long bookNo, int cnt) {
		OrderBookVo vo = new OrderBookVo();
		vo.setOrderNo(orderNo);
		vo.setBookNo(bookNo);
		vo.setCnt(cnt);
		
		return new OrderBookDao().insert(vo);
	}

}
