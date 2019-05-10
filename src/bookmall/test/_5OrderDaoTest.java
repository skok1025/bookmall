package bookmall.test;

import java.util.ArrayList;
import java.util.List;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrdersVo;

public class _5OrderDaoTest {
	
	public static void main(String[] args) {
		
		String businesscode = OrderDao.getBusinesscode();

		System.out.println("주문1 insert 성공:" + ordersinsert(businesscode, "서울시 성동구 성수동", 1L, 1L));

		Long orderno = OrderDao.getCurrentOrderNo();
		Long bookNo = 1L;
		Long bookNo2 = 2L;
		int cnt = 2;
		
		System.out.println("주문2 insert 성공(1):" + orderbookinsert(orderno, bookNo, cnt));
		System.out.println("주문2 insert 성공(2):" + orderbookinsert(orderno, bookNo2, cnt));
		
		// 사용자가 입력한 값
		List<OrderBookVo> orderBookList = new ArrayList<OrderBookVo>();
		OrderBookVo vo = new OrderBookVo();
		vo.setOrderNo(orderno);
		vo.setBookNo(bookNo);
		vo.setCnt(cnt);
		
		orderBookList.add(vo);
		
		OrderBookVo vo2 = new OrderBookVo();
		vo2.setOrderNo(orderno);
		vo2.setBookNo(bookNo2);
		vo2.setCnt(cnt);
		
		orderBookList.add(vo2);
		
		System.out.println("주문2 리스트방식 insert 성공:"+ orderbookinsert(orderBookList));
		
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
		
		return new OrderDao().orderbookinsert(vo);
	}
	
	private static Boolean orderbookinsert(List<OrderBookVo> orderBookList) {
		return new OrderDao().insertOrderBook(orderBookList);
	}

	
	
	


}
