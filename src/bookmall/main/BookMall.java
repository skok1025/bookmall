package bookmall.main;

import java.util.Scanner;

import bookmall.util.UI;

public class BookMall {

	public static void main(String[] args) {
		
		while(true) {
			mainMenu();
		}
	}
	
	public static void mainMenu() {
		Scanner scan = new Scanner(System.in);

		System.out.println("===================");
		System.out.println("1.회원 리스트");
		System.out.println("2.카테고리 리스트");
		System.out.println("3.상품 리스트");
		System.out.println("4.카트 리스트");
		System.out.println("5.주문 리스트");

		System.out.println("6.주문도서 리스트");
		System.out.println("===================");
		System.out.print("출력할 리스트 번호 >");

		int listNum = scan.nextInt();
		
			switch (listNum) {
			case 1:
				Service.showMemberList();
				UI.pause();
				mainMenu();
				break;
			case 2:
				Service.showCategoryList();
				UI.pause();
				mainMenu();
				break;
			case 3:
				Service.showBookList();
				UI.pause();
				mainMenu();
				break;
			case 4:
				Service.showCartList();
				UI.pause();
				mainMenu();
				break;
			case 5:
				Service.showOrderList();
				UI.pause();
				mainMenu();
				break;
			case 6:
				Service.showOrderBookList();
				UI.pause();
				mainMenu();
				break;
			default:
				break;
			}
	}

}