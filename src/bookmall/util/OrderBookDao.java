package bookmall.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bookmall.vo.OrderBookVo;

public class OrderBookDao {

	public Boolean insert(OrderBookVo vo) {
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

		return result;
	}

}
