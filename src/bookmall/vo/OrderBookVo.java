package bookmall.vo;

public class OrderBookVo {
	
	private Long no;
	private Long orderNo;
	private Long bookNo;
	private int cnt;
	
	private String bookTitle;

	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "OrderBookVo [no=" + no + ", orderNo=" + orderNo + ", bookNo=" + bookNo + ", cnt=" + cnt + ", bookTitle="
				+ bookTitle + "]";
	}
	
	
	

}
