package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.util.DBUtil;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;

public class CategoryDao {
	
	public Boolean insert(String name) {
		
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "insert into category values(null,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);

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

	public static List<CategoryVo> getList() {
		List<CategoryVo> result = new ArrayList<CategoryVo>();
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "select no,name from category";
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			while(rs.next()) {
				CategoryVo vo = new CategoryVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				
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
