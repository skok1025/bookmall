package bookmall.vo;

public class OrdersVo {
	private Long no;
	private String businesscode;
	private String destination;
	private Long memberNo;
	private Long statusNo;
	
	private String memberName;
	private String memberEmail;
	private String status;
	
	private int payment;
	private Long bookNo;
	private int cnt;
	
	
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getBusinesscode() {
		return businesscode;
	}
	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public Long getStatusNo() {
		return statusNo;
	}
	public void setStatusNo(Long statusNo) {
		this.statusNo = statusNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", businesscode=" + businesscode + ", destination=" + destination + ", memberNo="
				+ memberNo + ", statusNo=" + statusNo + ", memberName=" + memberName + ", memberEmail=" + memberEmail
				+ ", status=" + status + ", payment=" + payment + ", bookNo=" + bookNo + ", cnt=" + cnt + "]";
	}
	
	
	
	

}
