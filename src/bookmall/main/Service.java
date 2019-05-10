package bookmall.main;

import java.util.List;
import java.util.Scanner;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.util.UI;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrdersVo;

public class Service {

	public static void showMemberList() {

		List<MemberVo> list = MemberDao.getList();

		UI.menuTitle("멤버 리스트");
		UI.listBar();
		UI.header(new String[] { "번호", "이름", "전화번호", "  이메일     ", "비밀번호" });
		for (MemberVo vo : list) {
			System.out.printf("%s\t\t%s\t\t%s\t%s\t%s\n", vo.getNo(), vo.getName(), vo.getTel(), vo.getEmail(),
					vo.getPasswd());
		}
		UI.listBar();
	}

	public static void showCategoryList() {
		List<CategoryVo> list = CategoryDao.getList();

		UI.menuTitle("카테고리 리스트");
		UI.listBar();
		UI.header(new String[] { "카테고리 번호", "카테고리" });
		for (CategoryVo vo : list) {
			// System.out.println(vo);
			System.out.printf("%s\t\t\t%s\n", vo.getNo(), vo.getName());

		}
		UI.listBar();
	}

	public static void showBookList() {
		List<BookVo> list = BookDao.getList();

		UI.menuTitle("상품 리스트");
		UI.listBar();
		UI.header(new String[] { "상품번호", "책제목", "가격", "카테고리" });
		for (BookVo vo : list) {
			// System.out.println(vo);
			System.out.printf("%s\t\t%-12s\t%s원\t\t%s\n", vo.getNo(), vo.getTitle(), vo.getPrice(), vo.getCategory());

		}
		UI.listBar();

	}

	public static void showCartList() {
		Scanner scan = new Scanner(System.in);
		System.out.println("=========카트리스트를 확인할 멤버 번호를 입력하세요==========");
		showMemberList();
		System.out.print("확인할 멤버번호 > ");
		int membernum = scan.nextInt();

		List<CartVo> list = CartDao.getList(membernum);
		// 도서제목, 수량, 가격
		UI.menuTitle(MemberDao.get(membernum) + "의 카트 리스트");

		UI.listBar();
		UI.header(new String[] { "책제목", "수량", "가격" });
		if (!list.isEmpty()) {
			for (CartVo vo : list) {

				System.out.printf("%-12s\t%s권\t\t%s원\n", vo.getBookName(), vo.getCnt(), vo.getTotalPrice());
			}
			System.out.printf("총 금액:%s원\n",CartDao.getTotalPrice(membernum));
		} else {
			System.out.println("출력할 내용이 없습니다......");
		}
		UI.listBar();

	}

	public static void showOrderList() {
		Scanner scan = new Scanner(System.in);
		System.out.println("=========주문리스트를 확인할 멤버 번호를 입력하세요==========");
		showMemberList();
		System.out.print("확인할 멤버번호 > ");
		int membernum = scan.nextInt();

		List<OrdersVo> list = OrderDao.getList(membernum);

		UI.listBar();
		// 도서제목, 수량, 가격
		UI.menuTitle(MemberDao.get(membernum) + "의 주문 리스트");
		UI.header(new String[] { "주문코드","상품명" ,"고객명", "이메일", "결제금액", "배송지", "주문상태" });
		if (!list.isEmpty()) {
			for (OrdersVo vo : list) {

				System.out.printf("%s\t%s\t%s\t%s\t%s원\t%s\t%s\n", vo.getBusinesscode(),vo.getFirstbooktitle()+" 외"+(vo.getOrdercnt()-1)+" 권", vo.getMemberName(),
						vo.getMemberEmail(), vo.getPayment(), vo.getDestination(), vo.getStatus());

			}
		} else {
			System.out.println("출력할 내용이 없습니다......");
		}
		UI.listBar();

	}

	public static void showOrderBookList() {

		List<OrderBookVo> list = OrderDao.getOrderBookList();
		// 도서제목, 수량, 가격
		UI.menuTitle("주문도서 리스트");
		UI.listBar();
		UI.header(new String[] { "책 번호", "책 제목", "주문수량" });
		for (OrderBookVo vo : list) {
			// System.out.println(vo);
			System.out.printf("%s\t\t%-12s\t%s권\n", vo.getBookNo(), vo.getBookTitle(), vo.getCnt());
		}
		UI.listBar();

	}

}
