package com.booking_ing_table.model;

import java.util.List;

public class Booking_Ing_TableService {
	private Booking_Ing_TableDAO_interface dao;

	public Booking_Ing_TableService() {
		dao = new Booking_Ing_Table_JdbcDAO();
	}

	public Booking_Ing_TableVO addBooking_Ing_Table(Integer rs_status,String rs_sieral) {
		  Booking_Ing_TableVO  booking_Ing_TableVO1 = new Booking_Ing_TableVO();
		  booking_Ing_TableVO1.setRs_status(rs_status);
		  booking_Ing_TableVO1.setRs_sieral(rs_sieral);
		  dao.insert(booking_Ing_TableVO1);
		  
		  return booking_Ing_TableVO1;
	}

	public Booking_Ing_TableVO updateBooking_Ing_Table(String rs_sieral, Integer rs_status, String order_id) {
		 Booking_Ing_TableVO booking_Ing_TableVO2= new Booking_Ing_TableVO();
		 booking_Ing_TableVO2.setRs_sieral(rs_sieral);
		 booking_Ing_TableVO2.setRs_status(rs_status);
		 booking_Ing_TableVO2.setOrder_id(order_id);
		 dao.update(booking_Ing_TableVO2);
		 
		 return booking_Ing_TableVO2;
	}

	public Booking_Ing_TableVO getOneBooking_Ing_Table(String order_id) {
		return dao.findByPrimaryKey(order_id);
		
	}

	public List<Booking_Ing_TableVO> getAll() {
		return dao.getAll();
	}
	 public List<Booking_Ing_TableVO> getAll_Status(Integer rs_status){
		 return dao.findByPrimaryKey_Status(rs_status);
	 }
	 public Booking_Ing_TableVO updateBooking_Ing_Status(Integer rs_status,String order_id) {
		 Booking_Ing_TableVO booking_Ing_TableVO3 = new Booking_Ing_TableVO();
		 booking_Ing_TableVO3.setRs_status(rs_status);
		 booking_Ing_TableVO3.setOrder_id(order_id);
		 dao.update_status(booking_Ing_TableVO3);
		 
		 return booking_Ing_TableVO3;
	}

}
