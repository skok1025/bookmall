package bookmall.vo;

import java.util.List;

public class CartVo {
	private Long no;
	private Long bookNo;
	private Long memberNo;
	private int cnt;
	
	private String bookName;
	private String totalPrice;
	
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", bookNo=" + bookNo + ", memberNo=" + memberNo + ", cnt=" + cnt + ", bookName="
				+ bookName + ", totalPrice=" + totalPrice + "]";
	}
	
	

}
