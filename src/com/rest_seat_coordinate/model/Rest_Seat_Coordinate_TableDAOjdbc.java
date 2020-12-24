package com.rest_seat_coordinate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Rest_Seat_Coordinate_TableDAOjdbc implements Rest_Seat_Coordinate_TableDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G1";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO  Rest_Seat_Coordinate_Table (RS_SEAT_SIERAL,RS_SEAT_XY ,RS_SEAT_XY_TIME,RS_ID) values ('RSCOD' || lpad(REST_SEAT_COORDINATE_SEQ.Nextval,6,'0'),?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT RS_SEAT_SIERAL,RS_SEAT_XY,to_char(RS_SEAT_XY_TIME,'yyyy-mm-dd')RS_SEAT_XY_TIME,RS_ID FROM Rest_Seat_Coordinate_Table order by RS_SEAT_SIERAL";
	private static final String GET_ONE_STMT = 
			"SELECT RS_SEAT_SIERAL,RS_SEAT_XY,to_char(RS_SEAT_XY_TIME,'yyyy-mm-dd')RS_SEAT_XY_TIME,RS_ID FROM Rest_Seat_Coordinate_Table where RS_SEAT_SIERAL = ?";
	private static final String UPDATE = 
			"UPDATE Rest_Seat_Coordinate_Table set RS_SEAT_XY=?,RS_SEAT_XY_TIME=?,RS_ID=? where RS_SEAT_SIERAL=?";
	private static final String GET_ONE_RS_ID_STMT = 
			"SELECT RS_SEAT_SIERAL,RS_SEAT_XY,to_char(RS_SEAT_XY_TIME,'yyyy-mm-dd')RS_SEAT_XY_TIME,RS_ID FROM Rest_Seat_Coordinate_Table where RS_ID = ?";


	public static void main(String[] args) {

		Rest_Seat_Coordinate_TableDAOjdbc  dao = new Rest_Seat_Coordinate_TableDAOjdbc();
////		setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		//新增
//		Rest_Seat_Coordinate_TableVO rest_seat_coordinateVO1 = new Rest_Seat_Coordinate_TableVO();
//		rest_seat_coordinateVO1.setRs_seat_xy("111.21,11.23");
//		rest_seat_coordinateVO1.setRs_seat_xy_time(java.sql.Date.valueOf("1111-11-11"));
//		rest_seat_coordinateVO1.setRs_id("RS1000");
//		dao.insert(rest_seat_coordinateVO1);
//		System.out.println("=========");
//		 修改
//		Rest_Seat_Coordinate_TableVO rest_seat_coordinateVO2 = new Rest_Seat_Coordinate_TableVO();
//		rest_seat_coordinateVO2.setRs_seat_xy("301.22,23.01");
//		rest_seat_coordinateVO2.setRs_seat_xy_time(java.sql.Date.valueOf("1999-10-09"));
//		rest_seat_coordinateVO2.setRs_id("RS10002");
//		rest_seat_coordinateVO2.setRs_seat_sieral("10002");
//		dao.update(rest_seat_coordinateVO2);
//		System.out.println("=========");
////		//單筆查詢
//		Rest_Seat_Coordinate_TableVO Rest_Seat_Coordinate_VO3 = dao.findByPrimaryKey("RSCOD001001");
//		System.out.print(Rest_Seat_Coordinate_VO3.getRs_seat_sieral()+ ",");
//		System.out.print(Rest_Seat_Coordinate_VO3.getRs_seat_xy() + ",");
//		System.out.print(Rest_Seat_Coordinate_VO3.getRs_seat_xy_time()+ ",");
//		System.out.println(Rest_Seat_Coordinate_VO3.getRs_id());
//		System.out.println("---------------------");
////		//查詢全部
//		List<Rest_Seat_Coordinate_TableVO> list = dao.getAll();
//		for (Rest_Seat_Coordinate_TableVO aRest_seat : list) {
//			System.out.print(aRest_seat.getRs_seat_sieral()+ ",");
//			System.out.print(aRest_seat.getRs_seat_xy() + ",");
//			System.out.print(aRest_seat.getRs_seat_xy_time() + ",");
//			System.out.print(aRest_seat.getRs_id());
//			System.out.println();
//		}
		
		//查詢餐廳編號的訂單
		List<Rest_Seat_Coordinate_TableVO> list = dao.get_RS_ID_All("RS10001");
		for (Rest_Seat_Coordinate_TableVO aRest_seat : list) {
			System.out.print(aRest_seat.getRs_id()+",");
			System.out.print(aRest_seat.getRs_seat_xy() + ",");
			System.out.print(aRest_seat.getRs_seat_xy_time() + ",");
			System.out.print(aRest_seat.getRs_seat_sieral()+ ",");
			System.out.println();
		}
		
		
		
	}



	@Override
	public void insert(Rest_Seat_Coordinate_TableVO rest_seat_coordinate_VO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rest_seat_coordinate_VO.getRs_seat_xy());
			pstmt.setDate(2, rest_seat_coordinate_VO.getRs_seat_xy_time());
			pstmt.setString(3, rest_seat_coordinate_VO.getRs_id());

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
	public void update(Rest_Seat_Coordinate_TableVO rest_seat_coordinate_VO) {
		Connection con = null;
		PreparedStatement  pstmt=null;
		
		try {
			Class.forName(driver);
			con= DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, rest_seat_coordinate_VO.getRs_seat_xy());
			pstmt.setDate(2, rest_seat_coordinate_VO.getRs_seat_xy_time());
			pstmt.setString(3, rest_seat_coordinate_VO.getRs_id());
			pstmt.setString(4, rest_seat_coordinate_VO.getRs_seat_sieral());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

	@Override
	public Rest_Seat_Coordinate_TableVO findByPrimaryKey(String RS_SEAT_SIERAL) {
		Rest_Seat_Coordinate_TableVO rest_seat_coordinate_vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,RS_SEAT_SIERAL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rest_seat_coordinate_vo = new Rest_Seat_Coordinate_TableVO();
				rest_seat_coordinate_vo.setRs_seat_sieral(rs.getString("rs_seat_sieral"));
				rest_seat_coordinate_vo.setRs_seat_xy(rs.getString("rs_seat_xy"));
				rest_seat_coordinate_vo.setRs_seat_xy_time(rs.getDate("rs_seat_xy_time"));
				rest_seat_coordinate_vo.setRs_id(rs.getString("rs_id"));
				
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
		return rest_seat_coordinate_vo;
	}


	@Override
	public List<Rest_Seat_Coordinate_TableVO> getAll() {
		List<Rest_Seat_Coordinate_TableVO> list = new ArrayList<Rest_Seat_Coordinate_TableVO>();
		Rest_Seat_Coordinate_TableVO rest_seat_coordinate_vo = null;

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
				rest_seat_coordinate_vo = new Rest_Seat_Coordinate_TableVO();
				rest_seat_coordinate_vo.setRs_seat_sieral(rs.getString("rs_seat_sieral"));
				rest_seat_coordinate_vo.setRs_seat_xy(rs.getString("rs_seat_xy"));
				rest_seat_coordinate_vo.setRs_seat_xy_time(rs.getDate("rs_seat_xy_time"));
				rest_seat_coordinate_vo.setRs_id(rs.getString("rs_id"));
				list.add(rest_seat_coordinate_vo); // Store the row in the list
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
	public List<Rest_Seat_Coordinate_TableVO> get_RS_ID_All(String RS_ID) {
		List<Rest_Seat_Coordinate_TableVO> list = new ArrayList<Rest_Seat_Coordinate_TableVO>();
		Rest_Seat_Coordinate_TableVO rest_seat_coordinate_vo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_RS_ID_STMT);
			pstmt.setString(1,RS_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				rest_seat_coordinate_vo = new Rest_Seat_Coordinate_TableVO();
				rest_seat_coordinate_vo.setRs_id(rs.getString("rs_id"));
				rest_seat_coordinate_vo.setRs_seat_xy(rs.getString("rs_seat_xy"));
				rest_seat_coordinate_vo.setRs_seat_xy_time(rs.getDate("rs_seat_xy_time"));
				rest_seat_coordinate_vo.setRs_seat_sieral(rs.getString("rs_seat_sieral"));
				list.add(rest_seat_coordinate_vo); // Store the row in the list
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
