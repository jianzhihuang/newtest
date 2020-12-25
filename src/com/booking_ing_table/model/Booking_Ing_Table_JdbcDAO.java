package com.booking_ing_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booking_fixed_table.model.Booking_Fixed_TableVO;

public class Booking_Ing_Table_JdbcDAO implements Booking_Ing_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G1";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO  BOOKING_ING_TABLE (ORDER_ID,RS_STATUS,RS_SIERAL) values ('OD' || lpad(ORDER_TABLE_SEQ.Nextval,5,'0'),?,?)";
	private static final String GET_ALL_STMT = "SELECT ORDER_ID,RS_STATUS,RS_SIERAL FROM BOOKING_ING_TABLE order by ORDER_ID";
	private static final String GET_ONE_STMT = "SELECT ORDER_ID,RS_STATUS,RS_SIERAL FROM BOOKING_ING_TABLE where ORDER_ID = ?";
	private static final String UPDATE = "UPDATE BOOKING_ING_TABLE set RS_STATUS=?, RS_SIERAL=? where ORDER_ID=?";
	private static final String GET_STATUS = "SELECT ORDER_ID,RS_STATUS,RS_SIERAL FROM BOOKING_ING_TABLE where RS_STATUS = ?";
	private static final String UPDATE_STATUS ="UPDATE BOOKING_ING_TABLE set RS_STATUS=? where ORDER_ID=?";
	@Override
	public void insert(Booking_Ing_TableVO booking_ing_tablevo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, booking_ing_tablevo.getRs_status());
			pstmt.setString(2, booking_ing_tablevo.getRs_sieral());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public static void main(String[] args) {

		Booking_Ing_Table_JdbcDAO dao = new Booking_Ing_Table_JdbcDAO();
//		setHiredate(java.sql.Date.valueOf("2005-01-01"));
		// 新增
		Booking_Ing_TableVO booking_ing_tablevo1 = new Booking_Ing_TableVO();
		booking_ing_tablevo1.setRs_status(0);
		;
		booking_ing_tablevo1.setRs_sieral("RSFIX01001");
		;
		dao.insert(booking_ing_tablevo1);
//		 修改
		Booking_Ing_TableVO booking_ing_tablevo2 = new Booking_Ing_TableVO();
		booking_ing_tablevo2.setOrder_id("OD00105");
		booking_ing_tablevo2.setRs_status(0);
		booking_ing_tablevo2.setRs_sieral("RSFIX01001");
		dao.update(booking_ing_tablevo2);
//		
		////修改訂位狀態
		Booking_Ing_TableVO booking_ing_tablevo3 = new Booking_Ing_TableVO();
		booking_ing_tablevo3.setRs_status(0);
		booking_ing_tablevo3.setOrder_id("OD00101");
		dao.update_status(booking_ing_tablevo3);
		
		
////		//單筆查詢
		Booking_Ing_TableVO booking_Ing_TableVO3 = dao.findByPrimaryKey("OD00103");
		System.out.print(booking_Ing_TableVO3.getOrder_id() + ",");
		System.out.print(booking_Ing_TableVO3.getRs_status() + ",");
		System.out.println(booking_Ing_TableVO3.getRs_sieral());
		System.out.println("---------------------");
////		//查詢全部
		List<Booking_Ing_TableVO> list = dao.getAll();
		for (Booking_Ing_TableVO aBooking_Ing : list) {
			System.out.print(aBooking_Ing.getOrder_id() + ",");
			System.out.print(aBooking_Ing.getRs_status() + ",");
			System.out.print(aBooking_Ing.getRs_sieral());
			System.out.println();
		}
		System.out.println("---------------------");
////	//查詢訂位狀態
		List<Booking_Ing_TableVO> list2 = dao.findByPrimaryKey_Status(1);
		for (Booking_Ing_TableVO aBooking_Ing : list2) {
			System.out.print(aBooking_Ing.getOrder_id() + ",");
			System.out.print(aBooking_Ing.getRs_status() + ",");
			System.out.print(aBooking_Ing.getRs_sieral());
			System.out.println();
		}
	}

	@Override
	public void update(Booking_Ing_TableVO booking_ing_tablevo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, booking_ing_tablevo.getRs_status());
			pstmt.setString(2, booking_ing_tablevo.getRs_sieral());
			pstmt.setString(3, booking_ing_tablevo.getOrder_id());
//			System.out.println("update1");
//			System.out.println("update2");
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Booking_Ing_TableVO findByPrimaryKey(String order_id) {
		Booking_Ing_TableVO booking_fixed_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// booking_fixed_tableVO 銋迂� Domain objects
				booking_fixed_tableVO = new Booking_Ing_TableVO();
				booking_fixed_tableVO.setRs_sieral(rs.getString("rs_sieral"));
				booking_fixed_tableVO.setOrder_id(rs.getString("order_id"));
				booking_fixed_tableVO.setRs_status(rs.getInt("rs_status"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return booking_fixed_tableVO;
	}

	@Override
	public List<Booking_Ing_TableVO> getAll() {

		List<Booking_Ing_TableVO> list = new ArrayList<Booking_Ing_TableVO>();
		Booking_Ing_TableVO booking_Ing_TableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				booking_Ing_TableVO = new Booking_Ing_TableVO();
				booking_Ing_TableVO.setOrder_id(rs.getString("order_id"));
				booking_Ing_TableVO.setRs_status(rs.getInt("rs_status"));
				booking_Ing_TableVO.setRs_sieral(rs.getString("rs_sieral"));
				list.add(booking_Ing_TableVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public List<Booking_Ing_TableVO> findByPrimaryKey_Status(Integer rs_status) {
		List<Booking_Ing_TableVO> list = new ArrayList<Booking_Ing_TableVO>();
		Booking_Ing_TableVO booking_Ing_TableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STATUS);
			pstmt.setInt(1, rs_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				booking_Ing_TableVO = new Booking_Ing_TableVO();
				booking_Ing_TableVO.setOrder_id(rs.getString("order_id"));
				booking_Ing_TableVO.setRs_status(rs.getInt("rs_status"));
				booking_Ing_TableVO.setRs_sieral(rs.getString("rs_sieral"));
				list.add(booking_Ing_TableVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void update_status(Booking_Ing_TableVO booking_ing_tablevo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, booking_ing_tablevo.getRs_status());
			pstmt.setString(2, booking_ing_tablevo.getOrder_id());
//			System.out.println("update1");
//			System.out.println("update2");
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

}
