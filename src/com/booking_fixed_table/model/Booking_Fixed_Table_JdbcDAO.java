package com.booking_fixed_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Booking_Fixed_Table_JdbcDAO implements Booking_Fixed_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G1";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO BOOKING_FIXED_TABLE (RS_SIERAL,RS_TABLE_NUMBER,rs_table_seat,RS_SEAT_SIERAL) values ('RSFIX' || lpad(BOOKING_FIXED_TABLE_SEQ.Nextval,5,'0'),?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT RS_SIERAL,RS_TABLE_NUMBER,rs_table_seat,RS_SEAT_SIERAL FROM BOOKING_FIXED_TABLE order by RS_SIERAL";
	private static final String GET_ONE_STMT = 
			"SELECT RS_SIERAL,RS_TABLE_NUMBER,rs_table_seat,RS_SEAT_SIERAL FROM BOOKING_FIXED_TABLE where RS_SIERAL = ?";
	private static final String DELETE = 
			"DELETE FROM BOOKING_FIXED_TABLE where RS_SIERAL = ?";
	private static final String UPDATE = 
			"UPDATE BOOKING_FIXED_TABLE set RS_TABLE_NUMBER=?, RS_TABLE_SEAT=?, RS_SEAT_SIERAL=? where RS_SIERAL=?";
	private static final String SELECT_GET_RS_ID_ALL=
			"Select * From BOOKING_FIXED_TABLE Inner join REST_SEAT_COORDINATE_TABLE on  \r\n" + 
			"REST_SEAT_COORDINATE_TABLE.RS_SEAT_SIERAL  =\r\n" + 
			"BOOKING_FIXED_TABLE.RS_SEAT_SIERAL Where REST_SEAT_COORDINATE_TABLE.RS_ID = ?";
	
	
	@Override
	public void insert(Booking_Fixed_TableVO booking_fixed_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, booking_fixed_tableVO.getRs_table_number());
			pstmt.setInt(2, booking_fixed_tableVO.getRs_table_seat());
			pstmt.setString(3, booking_fixed_tableVO.getRs_seat_sieral());

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

		Booking_Fixed_Table_JdbcDAO dao = new Booking_Fixed_Table_JdbcDAO();
//		setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		// 新增
		Booking_Fixed_TableVO booking_Fixed_VO1 = new Booking_Fixed_TableVO();
		booking_Fixed_VO1.setRs_table_number("1");
		booking_Fixed_VO1.setRs_table_seat(1);
		booking_Fixed_VO1.setRs_seat_sieral("RSCOD001005");
		dao.insert(booking_Fixed_VO1);
////		// 修改
//		Booking_Fixed_TableVO booking_Fixed_VO2 = new Booking_Fixed_TableVO();
//		booking_Fixed_VO2.setRs_table_number("11111");
//		booking_Fixed_VO2.setRs_table_seat(666);
//		booking_Fixed_VO2.setRs_seat_sieral("RSCOD001001");
//		booking_Fixed_VO2.setRs_sieral("RSFIX01001");
//		dao.update(booking_Fixed_VO2);
////		// 查詢單筆
//		Booking_Fixed_TableVO booking_Fixed_VO3 = dao.findByPrimaryKey("RSFIX01003");
//		System.out.print(booking_Fixed_VO3.getRs_sieral() + ",");
//		System.out.print(booking_Fixed_VO3.getRs_table_number() + ",");
//		System.out.print(booking_Fixed_VO3.getRs_table_seat() + ",");
//		System.out.println(booking_Fixed_VO3.getRs_seat_sieral());
//		System.out.println("---------------------");
////		// 查詢全部
//		List<Booking_Fixed_TableVO> list = dao.getAll();
//		for (Booking_Fixed_TableVO aBooking_Fixed : list) {
//			System.out.print(aBooking_Fixed.getRs_sieral() + ",");
//			System.out.print(aBooking_Fixed.getRs_table_number() + ",");
//			System.out.print(aBooking_Fixed.getRs_table_seat() + ",");
//			System.out.print(aBooking_Fixed.getRs_seat_sieral());
//			System.out.println();
//		}
		//查詢餐廳編號底下的Booking_Fixed_TableVO資訊
//		List<Booking_Fixed_TableVO> list2 = dao.select_get_rs_id_all("RS10001");
//		for (Booking_Fixed_TableVO aBooking_Fixed : list2) {
//			System.out.print(aBooking_Fixed.getRs_sieral() + ",");
//			System.out.print(aBooking_Fixed.getRs_table_number() + ",");
//			System.out.print(aBooking_Fixed.getRs_table_seat() + ",");
//			System.out.print(aBooking_Fixed.getRs_seat_sieral());
//			System.out.println();
//		}
		//刪除一筆資料
		dao.delete("RSFIX01203");
		
	}

	@Override
	public void update(Booking_Fixed_TableVO booking_fixed_tableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, booking_fixed_tableVO.getRs_table_number());
			pstmt.setInt(2, booking_fixed_tableVO.getRs_table_seat());
			pstmt.setString(3, booking_fixed_tableVO.getRs_seat_sieral());
//			System.out.println("update1");
			pstmt.setString(4, booking_fixed_tableVO.getRs_sieral());
//			System.out.println("update2");
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String rs_sieral) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rs_sieral);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Booking_Fixed_TableVO findByPrimaryKey(String rs_sieral) {
		Booking_Fixed_TableVO booking_fixed_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rs_sieral);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// booking_fixed_tableVO 銋迂� Domain objects
				booking_fixed_tableVO = new Booking_Fixed_TableVO();
				booking_fixed_tableVO.setRs_sieral(rs.getString("RS_SIERAL"));
				booking_fixed_tableVO.setRs_table_number(rs.getString("RS_TABLE_NUMBER"));
				booking_fixed_tableVO.setRs_table_seat(rs.getInt("RS_TABLE_SEAT"));
				booking_fixed_tableVO.setRs_seat_sieral(rs.getString("RS_SEAT_SIERAL"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<Booking_Fixed_TableVO> getAll() {
		List<Booking_Fixed_TableVO> list = new ArrayList<Booking_Fixed_TableVO>();
		Booking_Fixed_TableVO booking_Fixed_TableVO = null;

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
				booking_Fixed_TableVO = new Booking_Fixed_TableVO();
				booking_Fixed_TableVO.setRs_sieral(rs.getString("RS_SIERAL"));
				booking_Fixed_TableVO.setRs_table_number(rs.getString("RS_TABLE_NUMBER"));
				booking_Fixed_TableVO.setRs_table_seat(rs.getInt("RS_TABLE_SEAT"));
				booking_Fixed_TableVO.setRs_seat_sieral(rs.getString("RS_SEAT_SIERAL"));
				list.add(booking_Fixed_TableVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<Booking_Fixed_TableVO> select_get_rs_id_all(String rs_id) {
		List<Booking_Fixed_TableVO> list = new ArrayList<Booking_Fixed_TableVO>();
		Booking_Fixed_TableVO booking_Fixed_TableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_GET_RS_ID_ALL);
			pstmt.setString(1, rs_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				booking_Fixed_TableVO = new Booking_Fixed_TableVO();
				booking_Fixed_TableVO.setRs_sieral(rs.getString("RS_SIERAL"));
				booking_Fixed_TableVO.setRs_table_number(rs.getString("RS_TABLE_NUMBER"));
				booking_Fixed_TableVO.setRs_table_seat(rs.getInt("RS_TABLE_SEAT"));
				booking_Fixed_TableVO.setRs_seat_sieral(rs.getString("RS_SEAT_SIERAL"));
				list.add(booking_Fixed_TableVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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



}
