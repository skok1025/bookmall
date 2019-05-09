package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.util.DBUtil;
import bookmall.vo.MemberVo;

public class MemberDao {

	public Boolean insert(MemberVo vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "insert into member values(null,?,?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, vo.getName());
			pstat.setString(2, vo.getTel());
			pstat.setString(3, vo.getEmail());
			pstat.setString(4, vo.getPasswd());
			
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

	public static List<MemberVo> getList() {
		List<MemberVo> result = new ArrayList<MemberVo>();
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "select no,name,tel,email,passwd from member";
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			while(rs.next()) {
				MemberVo vo = new MemberVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setTel(rs.getString(3));
				vo.setEmail(rs.getString(4));
				vo.setPasswd(rs.getString(5));
				
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

	public static String get(int membernum) {

		String result = null;
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getConnection();

			String sql = "select name from member where no = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, membernum+"");
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				result = rs.getString("name");
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
