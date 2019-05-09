package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.util.DBUtil;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;

public class CartDao {

	public Boolean insert(CartVo vo) {

		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "insert into cart values(null,?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setLong(1, vo.getBookNo());
			pstat.setLong(2, vo.getMemberNo());
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

	public static List<CartVo> getList() {
		List<CartVo> result = new ArrayList<CartVo>();

		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "";
			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			while (rs.next()) {
				CartVo vo = new CartVo();
				vo.setNo(rs.getLong(1));
				
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

	public static List<CartVo> getList(int membernum) {
		List<CartVo> result = new ArrayList<CartVo>();

		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "select b.title as bookname,c.cnt as cnt,c.cnt*price as totalprice from cart c, book b, member m where c.book_no = b.no and	c.member_no = m.no and c.member_no = ? order by b.title";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, membernum + "");

			rs = pstat.executeQuery();

			while (rs.next()) {
				CartVo vo = new CartVo();
				vo.setBookName(rs.getString("bookname"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setTotalPrice(rs.getInt("totalprice"));

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

}
