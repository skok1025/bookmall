package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.util.DBUtil;
import bookmall.vo.CartVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrdersVo;

public class OrderDao {

	public Boolean insert(OrdersVo vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "insert into orders values(null,?,?,?,?,null)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, vo.getBusinesscode());
			pstat.setString(2, vo.getDestination());
			pstat.setLong(3, vo.getMemberNo());
			pstat.setLong(4, vo.getStatusNo());
			
			int count = pstat.executeUpdate();

			result = (count == 1);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (pstat != null) {
					pstat.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static List<OrdersVo> getList(int membernum) {
		List<OrdersVo> result = new ArrayList<OrdersVo>();

		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "select o.businesscode as businesscode,m.name as memberName,m.email as memberEmail,o.payment as payment,  o.destination as destination from member m, orders o, order_book ob, book b,status s where m.no = o.member_no and o.no = ob.order_no and ob.book_no = b.no and o.status_no = s.no and m.no = ? order by o.businesscode";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, membernum + "");

			rs = pstat.executeQuery();

			while (rs.next()) {
				OrdersVo vo = new OrdersVo();
				vo.setBusinesscode(rs.getString(1));
				vo.setMemberName(rs.getString(2));
				vo.setMemberEmail(rs.getString(3));
				vo.setPayment(rs.getInt(4));
				vo.setDestination(rs.getString(5));
				
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {

				if (pstat != null) {
					pstat.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static List<OrderBookVo> getOrderBookList() {
		List<OrderBookVo> result = new ArrayList<OrderBookVo>();

		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "select (select title from book where no = ob.book_no) as booktitle,book_no,count(*) as cnt from order_book ob group by book_no order by book_no";
			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			while (rs.next()) {
				OrderBookVo vo = new OrderBookVo();
				vo.setBookTitle(rs.getString("booktitle"));
				vo.setBookNo(rs.getLong("book_no"));
				vo.setCnt(rs.getInt("cnt"));
				
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {

				if (pstat != null) {
					pstat.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static Long getCurrentOrderNo() {

		Long result = null;
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			//String sql = "select last_insert_id()";
			String sql = "select max(no) from orders";
			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			if(rs.next()) {
				result = rs.getLong(1);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {

				if (pstat != null) {
					pstat.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static String getBusinesscode() {

		String result = null;
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "select ifnull(concat(date_format(curdate(),\"%Y%m%d\"),\"-\",(select lpad((select substring(max(businesscode),10)+1 from orders  where substr(businesscode,1,8) = date_format(curdate(),\"%Y%m%d\")),4,\"0\"))),concat(date_format(curdate(),\"%Y%m%d\"),\"-\",\"0001\"))";
			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			if(rs.next()) {
				result = rs.getString(1);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {

				if (pstat != null) {
					pstat.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public void orderpaymentupdate(OrdersVo vo) {
		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "update orders set payment = (select (select price from book where no = ?)*?) where no = ?";
			pstat = conn.prepareStatement(sql);

			pstat.setLong(1, vo.getBookNo());
			pstat.setInt(2, vo.getCnt());
			pstat.setLong(3, vo.getNo());
			
			pstat.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {

				if (pstat != null) {
					pstat.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
