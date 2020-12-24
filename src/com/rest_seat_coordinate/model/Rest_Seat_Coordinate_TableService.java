package com.rest_seat_coordinate.model;

import java.sql.Date;
import java.util.List;

import com.booking_ing_table.model.Booking_Ing_TableVO;

public class Rest_Seat_Coordinate_TableService {
	private Rest_Seat_Coordinate_TableDAO_interface dao;

	public Rest_Seat_Coordinate_TableService() {
		dao = new Rest_Seat_Coordinate_TableDAOjdbc();
	}

	public Rest_Seat_Coordinate_TableVO addRest_Seat_Coordinate(String rs_seat_xy,
			Date rs_seat_xy_time, String rs_id) {
		Rest_Seat_Coordinate_TableVO rest_Seat_Coordinate_VO1 = new Rest_Seat_Coordinate_TableVO();
		rest_Seat_Coordinate_VO1.setRs_seat_xy(rs_seat_xy);
		rest_Seat_Coordinate_VO1.setRs_seat_xy_time(rs_seat_xy_time);
		rest_Seat_Coordinate_VO1.setRs_id(rs_id);
		dao.insert(rest_Seat_Coordinate_VO1);
		return rest_Seat_Coordinate_VO1;
	}

	public Rest_Seat_Coordinate_TableVO updateRest_Seat_Coordinate(String rs_seat_sieral, String rs_seat_xy,
			Date rs_seat_xy_time, String rs_id) {
		Rest_Seat_Coordinate_TableVO rest_Seat_Coordinate_VO2 = new Rest_Seat_Coordinate_TableVO();
		rest_Seat_Coordinate_VO2.setRs_seat_sieral(rs_seat_sieral);
		rest_Seat_Coordinate_VO2.setRs_seat_xy(rs_seat_xy);
		rest_Seat_Coordinate_VO2.setRs_seat_xy_time(rs_seat_xy_time);
		rest_Seat_Coordinate_VO2.setRs_id(rs_id);
		dao.update(rest_Seat_Coordinate_VO2);
		return rest_Seat_Coordinate_VO2;
	}

	public Rest_Seat_Coordinate_TableVO getOneRest_Seat_Coordinate_Table(String rs_seat_sieral) {
		return dao.findByPrimaryKey(rs_seat_sieral);
	}

	public List<Rest_Seat_Coordinate_TableVO> getAll() {
		return dao.getAll();
	}

	public List<Rest_Seat_Coordinate_TableVO> get_RS_ID_All(String rs_id) {
		return dao.get_RS_ID_All(rs_id);
		
	}
}
