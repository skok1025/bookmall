package bookmall.test;

import bookmall.dao.OrderDao;
import bookmall.util.OrderBookDao;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrdersVo;

public class OrderDaoTest {
	
	public static void main(String[] args) {
		
		String businesscode = OrderDao.getBusinesscode();

		System.out.println("주문1 insert 성공:" + ordersinsert(businesscode, "서울시 성동구 성수동", 1L, 1L));

		Long orderno = OrderDao.getCurrentOrderNo();
		System.out.println("주문2 insert 성공:" + orderbookinsert(orderno, 1L, 2));
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
