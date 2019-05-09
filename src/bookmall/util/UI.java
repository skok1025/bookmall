package bookmall.util;

import java.util.Scanner;

/**
 * 기본 UI를 위한 클래스
 * @author 김석현
 *
 */
public class UI {

	
	
	/**
	 * 메뉴 타이틀 이름
	 * @param title 붙일 타이틀
	 */
	public static void menuTitle(String title) {
		System.out.println();
		for (int i = 0; i < 40; i++) {
			System.out.print("●○");
		}		
		System.out.println();
		
		for(int i=0;i<75-title.length()/2;i++) {
			System.out.print(" ");
		}
		System.out.println(title);
		for (int i = 0; i < 40; i++) {
			System.out.print("○●");
		}	
		System.out.println();
	}
	

	/**
	 *  리스트 제목에 붙일 바
	 */
	public static void listBar(){
		for (int i = 0; i < 40; i++) {
			System.out.print("==");
		}
		System.out.println();
	}

	/**
	 * 잠시 멈춤
	 */
	public static void pause() {
		System.out.println("뒤로 돌아가려면 아무 키나 눌러주세요.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
	}

	

	/**
	 * 테이블 출력 > 헤더 출력
	 * @param labels
	 */
	public static void header(String[] labels) {
		for (String label : labels) {
			System.out.printf("[%s]\t\t", label);
		}
		System.out.println();
	}

	/**
	 * 테이블 출력 > 데이터 출력
	 * @param datas 
	 */
	public static void data(Object[] datas) {
		for (Object data : datas) {
			System.out.printf("%s\t", data);
		}
		System.out.println();
	}

	
	/**
	 * 결과에 따른 메세지 출력 메소드 
	 * @param result 실행 결과
	 * @param msg 실행 성공시 출력 메세지
	 */
	public static void result(int result, String msg) {
		if (result > 0) {
			System.out.printf("결과]" + msg);
		} else {
			System.out.println("실패했습니다. 관리자에게 문의하세요.");
		}
		System.out.println();
	}

	
	


}
