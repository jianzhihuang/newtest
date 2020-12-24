package com.rest_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Rest_TableDAOjdbc implements Rest_TableDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver"; // 驅動程式
	String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL
	String userid = "TEA102G1";// 帳號
	String passwd = "123456";// 密碼

	private static final String INSERT_STMT = "INSERT INTO REST_TABLE (RS_ID, CP_CONTACT_EMAIL, RS_NAME, RS_ADDRESS,RS_PHONE , RS_INTRO, RS_CHECK_YN, RS_TYPE, RS_BIGPIC, RS_PIC, RS_VIEW1, RS_VIEW2, RS_VIEW3, RS_VIEW4, RS_VIEW5, RS_OPEN_TIME, RS_REG_DATE, AUTHORITY) VALUES('RS' || lpad(REST_TABLE_SEQ.Nextval,5,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,DEFAULT)";
	private static final String GET_ALL_STMT = "SELECT RS_ID, CP_CONTACT_EMAIL, RS_NAME, RS_ADDRESS, RS_PHONE, RS_INTRO, RS_CHECK_YN, RS_TYPE, RS_BIGPIC, RS_PIC, RS_VIEW1, RS_VIEW2, RS_VIEW3, RS_VIEW4, RS_VIEW5, RS_OPEN_TIME, TO_CHAR(RS_REG_DATE,'YYYY-MM-DD')RS_REG_DATE, AUTHORITY FROM REST_TABLE";
	private static final String GET_ONE_STMT = "SELECT RS_ID, CP_CONTACT_EMAIL, RS_NAME, RS_ADDRESS, RS_PHONE, RS_INTRO, RS_CHECK_YN, RS_TYPE, RS_BIGPIC, RS_PIC, RS_VIEW1, RS_VIEW2, RS_VIEW3, RS_VIEW4, RS_VIEW5, RS_OPEN_TIME, TO_CHAR(RS_REG_DATE,'YYYY-MM-DD')RS_REG_DATE, AUTHORITY FROM REST_TABLE WHERE RS_ID=?";
	private static final String DELETE = "DELETE FROM REST_TABLE WHERE RS_ID=?";
	private static final String UPDATE = "UPDATE REST_TABLE SET RS_NAME=?, RS_ADDRESS=?, RS_PHONE=?, RS_INTRO=?, RS_CHECK_YN=?, RS_TYPE=?, RS_BIGPIC=?, RS_PIC=?, RS_VIEW1=?, RS_VIEW2=?, RS_VIEW3=?, RS_VIEW4=?, RS_VIEW5=?, RS_OPEN_TIME=?, RS_REG_DATE=? WHERE RS_ID=?";

	// 新增
	@Override
	public void insert(Rest_TableVO rest_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String[] cols = { "RS_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, rest_TableVO.getCp_contact_email());
			pstmt.setString(2, rest_TableVO.getRs_name());
			pstmt.setString(3, rest_TableVO.getRs_address());
			pstmt.setString(4, rest_TableVO.getRs_phone());
			pstmt.setString(5, rest_TableVO.getRs_intro());
			pstmt.setInt(6, rest_TableVO.getRs_check_yn());
			pstmt.setString(7, rest_TableVO.getRs_type());
			pstmt.setBytes(8, rest_TableVO.getRs_big_pic());
			pstmt.setBytes(9, rest_TableVO.getRs_pic());
			pstmt.setBytes(10, rest_TableVO.getRs_view1());
			pstmt.setBytes(11, rest_TableVO.getRs_view2());
			pstmt.setBytes(12, rest_TableVO.getRs_view3());
			pstmt.setBytes(13, rest_TableVO.getRs_view4());
			pstmt.setBytes(14, rest_TableVO.getRs_view5());
			pstmt.setString(15, rest_TableVO.getRs_open_time());
			pstmt.setDate(16, rest_TableVO.getRs_reg_date());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 更新
	@Override
	public void update(Rest_TableVO rest_TableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rest_TableVO.getRs_name());
			pstmt.setString(2, rest_TableVO.getRs_address());
			pstmt.setString(3, rest_TableVO.getRs_phone());
			pstmt.setString(4, rest_TableVO.getRs_intro());
			pstmt.setInt(5, rest_TableVO.getRs_check_yn());
			pstmt.setString(6, rest_TableVO.getRs_type());
			pstmt.setBytes(7, rest_TableVO.getRs_big_pic());
			pstmt.setBytes(8, rest_TableVO.getRs_pic());
			pstmt.setBytes(9, rest_TableVO.getRs_view1());
			pstmt.setBytes(10, rest_TableVO.getRs_view2());
			pstmt.setBytes(11, rest_TableVO.getRs_view3());
			pstmt.setBytes(12, rest_TableVO.getRs_view4());
			pstmt.setBytes(13, rest_TableVO.getRs_view5());
			pstmt.setString(14, rest_TableVO.getRs_open_time());
			pstmt.setDate(15, rest_TableVO.getRs_reg_date());
			pstmt.setString(16, rest_TableVO.getRs_id());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 刪除全部
	@Override
	public void delete(String rs_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rs_id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 用主鍵找表格內容
	@Override
	public Rest_TableVO findByPrimaryKey(String rs_id) {
		Rest_TableVO rest_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rs_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rest_TableVO = new Rest_TableVO();
				rest_TableVO.setRs_id(rs.getString("rs_id"));
				rest_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				rest_TableVO.setRs_name(rs.getString("rs_name"));
				rest_TableVO.setRs_address(rs.getString("rs_address"));
				rest_TableVO.setRs_phone(rs.getString("rs_phone"));
				rest_TableVO.setRs_intro(rs.getString("rs_intro"));
				rest_TableVO.setRs_check_yn(rs.getInt("rs_check_yn"));
				rest_TableVO.setRs_type(rs.getString("rs_type"));
				rest_TableVO.setRs_big_pic(rs.getBytes("rs_bigpic"));
				rest_TableVO.setRs_pic(rs.getBytes("rs_pic"));
				rest_TableVO.setRs_view1(rs.getBytes("rs_view1"));
				rest_TableVO.setRs_view2(rs.getBytes("rs_view2"));
				rest_TableVO.setRs_view3(rs.getBytes("rs_view3"));
				rest_TableVO.setRs_view4(rs.getBytes("rs_view4"));
				rest_TableVO.setRs_view5(rs.getBytes("rs_view5"));
				rest_TableVO.setRs_open_time(rs.getString("rs_open_time"));
				rest_TableVO.setRs_reg_date(rs.getDate("rs_reg_date"));
				rest_TableVO.setAuthority(rs.getInt("authority"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return rest_TableVO;
	}

	// 查全部的資料
	@Override
	public List<Rest_TableVO> getAll() {
		List<Rest_TableVO> list = new ArrayList<Rest_TableVO>();
		Rest_TableVO rest_TableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rest_TableVO = new Rest_TableVO();
				rest_TableVO.setRs_id(rs.getString("rs_id"));
				rest_TableVO.setCp_contact_email(rs.getString("cp_contact_email"));
				rest_TableVO.setRs_name(rs.getString("rs_name"));
				rest_TableVO.setRs_address(rs.getString("rs_address"));
				rest_TableVO.setRs_phone(rs.getString("rs_phone"));
				rest_TableVO.setRs_intro(rs.getString("rs_intro"));
				rest_TableVO.setRs_check_yn(rs.getInt("rs_check_yn"));
				rest_TableVO.setRs_type(rs.getString("rs_type"));
				rest_TableVO.setRs_big_pic(rs.getBytes("rs_bigpic"));
				rest_TableVO.setRs_pic(rs.getBytes("rs_pic"));
				rest_TableVO.setRs_view1(rs.getBytes("rs_view1"));
				rest_TableVO.setRs_view2(rs.getBytes("rs_view2"));
				rest_TableVO.setRs_view3(rs.getBytes("rs_view3"));
				rest_TableVO.setRs_view4(rs.getBytes("rs_view4"));
				rest_TableVO.setRs_view5(rs.getBytes("rs_view5"));
				rest_TableVO.setRs_open_time(rs.getString("rs_open_time"));
				rest_TableVO.setRs_reg_date(rs.getDate("rs_reg_date"));
				rest_TableVO.setAuthority(rs.getInt("authority"));
				list.add(rest_TableVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// =====================測試========================================================================================================================
	public static void main(String[] args) {
		Rest_TableDAOjdbc rest_TableDAOjdbc=new Rest_TableDAOjdbc();
		// =====================insert 新增==============================
		Rest_TableVO rest_TableVO=new Rest_TableVO();
		rest_TableVO.setCp_contact_email("abc@gmail.com");
		rest_TableVO.setRs_name("真的好餓");
		rest_TableVO.setRs_address("台北市中正區");
		rest_TableVO.setRs_phone("0800123455");
		rest_TableVO.setRs_intro("Its good food");
		rest_TableVO.setRs_check_yn(1);
		rest_TableVO.setRs_type("日式");
		rest_TableVO.setRs_big_pic(null);
		rest_TableVO.setRs_pic(null);
		rest_TableVO.setRs_view1(null);
		rest_TableVO.setRs_view2(null);
		rest_TableVO.setRs_view3(null);
		rest_TableVO.setRs_view4(null);
		rest_TableVO.setRs_view5(null);
		rest_TableVO.setRs_open_time("1,2,3");
		rest_TableVO.setRs_reg_date(java.sql.Date.valueOf("2019-9-5"));
		
		rest_TableDAOjdbc.insert(rest_TableVO);
		System.out.println("REST_TABLE 資料\"新增\"成功");

		// =====================update 更新==============================
		Rest_TableVO rest_TableVO2 = new Rest_TableVO();
		rest_TableVO2.setRs_id("RS10005");
		rest_TableVO2.setRs_name("好不不不餓");
		rest_TableVO2.setRs_address("台北市中正區");
		rest_TableVO2.setRs_phone("0800123455");
		rest_TableVO2.setRs_intro("Its good food");
		rest_TableVO2.setRs_check_yn(1);
		rest_TableVO2.setRs_type("日式");
		rest_TableVO2.setRs_big_pic(null);
		rest_TableVO2.setRs_pic(null);
		rest_TableVO2.setRs_view1(null);
		rest_TableVO2.setRs_view2(null);
		rest_TableVO2.setRs_view3(null);
		rest_TableVO2.setRs_view4(null);
		rest_TableVO2.setRs_view5(null);
		rest_TableVO2.setRs_open_time("1,2,3");
		rest_TableVO2.setRs_reg_date(java.sql.Date.valueOf("1999-10-01"));
		
		rest_TableDAOjdbc.update(rest_TableVO2);
		System.out.println("REST_TABLE 資料\"更新\"成功");

		// =====================delete 刪除==============================
//		Rest_TableDAOjdbc rest_TableDAOjdbc=new Rest_TableDAOjdbc();
//		rest_TableDAOjdbc.delete("10007");
//		System.out.println("REST_TABLE 資料\"刪除\"成功");

		// =====================get one 找一個==============================
		Rest_TableVO rest_TableVO3=new Rest_TableDAOjdbc().findByPrimaryKey("RS10005");
		
		System.out.println(rest_TableVO3.getRs_id());
		System.out.println(rest_TableVO3.getCp_contact_email());
		System.out.println(rest_TableVO3.getRs_name());
		System.out.println(rest_TableVO3.getRs_address());
		System.out.println(rest_TableVO3.getRs_phone());
		System.out.println(rest_TableVO3.getRs_intro());
		System.out.println(rest_TableVO3.getRs_check_yn());
		System.out.println(rest_TableVO3.getRs_type());
		System.out.println(rest_TableVO3.getRs_big_pic());
		System.out.println(rest_TableVO3.getRs_pic());
		System.out.println(rest_TableVO3.getRs_view1());
		System.out.println(rest_TableVO3.getRs_view2());
		System.out.println(rest_TableVO3.getRs_view3());
		System.out.println(rest_TableVO3.getRs_view4());
		System.out.println(rest_TableVO3.getRs_view5());
		System.out.println(rest_TableVO3.getRs_open_time());
		System.out.println(rest_TableVO3.getRs_reg_date());
		System.out.println(rest_TableVO3.getAuthority());
		System.out.println("REST_TABLE 資料\"查詢(單筆)\"成功");

		// =====================get all 找全部==============================
		List<Rest_TableVO> list = new Rest_TableDAOjdbc().getAll();

		for (Rest_TableVO rest_TableVO4 : list) {
			System.out.println(rest_TableVO4.getRs_id());
			System.out.println(rest_TableVO4.getCp_contact_email());
			System.out.println(rest_TableVO4.getRs_name());
			System.out.println(rest_TableVO4.getRs_address());
			System.out.println(rest_TableVO4.getRs_phone());
			System.out.println(rest_TableVO4.getRs_intro());
			System.out.println(rest_TableVO4.getRs_check_yn());
			System.out.println(rest_TableVO4.getRs_type());
			System.out.println(rest_TableVO4.getRs_big_pic());
			System.out.println(rest_TableVO4.getRs_pic());
			System.out.println(rest_TableVO4.getRs_view1());
			System.out.println(rest_TableVO4.getRs_view2());
			System.out.println(rest_TableVO4.getRs_view3());
			System.out.println(rest_TableVO4.getRs_view4());
			System.out.println(rest_TableVO4.getRs_view5());
			System.out.println(rest_TableVO4.getRs_open_time());
			System.out.println(rest_TableVO4.getRs_reg_date());
			System.out.println(rest_TableVO4.getAuthority());
			System.out.println("REST_TABLE 資料\"查詢(全部)\"成功");
			System.out.println("====================================");
		}
	}
}
