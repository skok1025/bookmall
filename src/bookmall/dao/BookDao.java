package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.util.DBUtil;
import bookmall.vo.BookVo;
import bookmall.vo.CategoryVo;

public class BookDao {

	public Boolean insert(BookVo vo) {

		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "insert into book values(null,?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, vo.getTitle());
			pstat.setLong(2, vo.getPrice());
			pstat.setLong(3, vo.getCategoryNo());
			
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

	public static List<BookVo> getList() {
		List<BookVo> result = new ArrayList<BookVo>();
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "select no,title,price,(select name from category where no = b.category_no) as category from book b order by no";
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			while(rs.next()) {
				BookVo vo = new BookVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setPrice(rs.getInt(3));
				vo.setCategory(rs.getString(4));
				
				
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
