package com.rest_seat_coordinate.model;

import java.io.Serializable;
import java.sql.Date;
//
//RS_SEAT_SIERAL    VARCHAR2(100)NOT NULL,
//RS_SEAT_XY     VARCHAR2(100)NOT NULL,
//RS_SEAT_XY_TIME  DATE,
//RS_ID      VARCHAR2(100)NOT NULL,

public class Rest_Seat_Coordinate_TableVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rs_seat_sieral;
	private String rs_seat_xy;
	private Date rs_seat_xy_time;
	private String rs_id;
	
	public String getRs_seat_sieral() {
		return rs_seat_sieral;
	}
	public void setRs_seat_sieral(String rs_seat_sieral) {
		this.rs_seat_sieral = rs_seat_sieral;
	}
	public String getRs_seat_xy() {
		return rs_seat_xy;
	}
	public void setRs_seat_xy(String rs_seat_xy) {
		this.rs_seat_xy = rs_seat_xy;
	}
	public Date getRs_seat_xy_time() {
		return rs_seat_xy_time;
	}
	public void setRs_seat_xy_time(Date rs_seat_xy_time) {
		this.rs_seat_xy_time = rs_seat_xy_time;
	}
	public String getRs_id() {
		return rs_id;
	}
	public void setRs_id(String rs_id) {
		this.rs_id = rs_id;
	}
	
	





}


