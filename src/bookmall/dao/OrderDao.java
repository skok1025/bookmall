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

	public Boolean insertOrderBook(List<OrderBookVo> orderBookList) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstat = null;
		int loop = 0;
		try {
			
			conn = DBUtil.getConnection();
			
			for(OrderBookVo vo : orderBookList) {
				String sql = "insert into order_book values(null,?,?,?)";
				pstat = conn.prepareStatement(sql);
				pstat.setLong(1, vo.getOrderNo());
				pstat.setLong(2, vo.getBookNo());
				pstat.setLong(3, vo.getCnt());
				
				int count = pstat.executeUpdate();
				
				if(loop++ ==0) { // 첫 루프 일때 
					result = (count == 1);
				
				} else {
					result = result && (count == 1);					
				}
				orderpaymentupdate(vo);
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
	
	public Boolean orderbookinsert(OrderBookVo vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "insert into order_book values(null,?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setLong(1, vo.getOrderNo());
			pstat.setLong(2, vo.getBookNo());
			pstat.setLong(3, vo.getCnt());
			
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
		orderpaymentupdate(vo);
		
		return result;
	}
	
	public Boolean insert(OrdersVo vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "insert into orders values(null,?,?,?,?,0)";
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

			String sql = "select distinct o.businesscode as businesscode,m.name as memberName,m.email as memberEmail,format(o.payment,0) as payment,  o.destination as destination, (select sum(cnt) from order_book where order_no = o.no) as ordercnt, (select title from book where no = (select book_no from order_book where order_no = o.no order by no limit 1)) as firstbooktitle from member m, orders o, order_book ob, book b,status s where m.no = o.member_no and o.no = ob.order_no and ob.book_no = b.no and o.status_no = s.no and m.no = ? order by o.businesscode";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, membernum + "");

			rs = pstat.executeQuery();

			while (rs.next()) {
				OrdersVo vo = new OrdersVo();
				vo.setBusinesscode(rs.getString(1));
				vo.setMemberName(rs.getString(2));
				vo.setMemberEmail(rs.getString(3));
				vo.setPayment(rs.getString(4));
				vo.setDestination(rs.getString(5));
				vo.setOrdercnt(rs.getInt(6));
				vo.setFirstbooktitle(rs.getString(7));
				
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

			String sql = "select (select title from book where no = ob.book_no) as booktitle,book_no,sum(ob.cnt) as cnt from order_book ob group by book_no order by book_no";
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

	public void orderpaymentupdate(OrderBookVo vo) {
		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "update orders set payment = payment + (select (select price from book where no = ?)*?) where no = ?";
			pstat = conn.prepareStatement(sql);

			pstat.setLong(1, vo.getBookNo());
			pstat.setInt(2, vo.getCnt());
			pstat.setLong(3, vo.getOrderNo());
			
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
