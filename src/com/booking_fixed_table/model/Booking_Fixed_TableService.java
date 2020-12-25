package com.booking_fixed_table.model;

import java.util.List;

public class Booking_Fixed_TableService {
	private Booking_Fixed_TableDAO_interface dao;

	public Booking_Fixed_TableService() {
		dao = new Booking_Fixed_Table_JdbcDAO();
	}

	public Booking_Fixed_TableVO addbooking_fixed_table(String rs_table_number,Integer rs_table_seat,String rs_seat_sieral) {

		Booking_Fixed_TableVO booking_Fixed_TableVO1 = new Booking_Fixed_TableVO();

		
		booking_Fixed_TableVO1.setRs_table_number(rs_table_number);
		booking_Fixed_TableVO1.setRs_table_seat(rs_table_seat);
		booking_Fixed_TableVO1.setRs_seat_sieral(rs_seat_sieral);
		dao.insert(booking_Fixed_TableVO1);

		return booking_Fixed_TableVO1;
	}

	public Booking_Fixed_TableVO updatebooking_fixed_table(String rs_sieral,String rs_table_number,Integer rs_table_seat,String rs_seat_sieral) {

		Booking_Fixed_TableVO booking_Fixed_TableVO2 = new Booking_Fixed_TableVO();

		booking_Fixed_TableVO2.setRs_sieral(rs_sieral);;
		booking_Fixed_TableVO2.setRs_table_number(rs_table_number);
		booking_Fixed_TableVO2.setRs_table_seat(rs_table_seat);
		booking_Fixed_TableVO2.setRs_seat_sieral(rs_seat_sieral);
		dao.update(booking_Fixed_TableVO2);

		return booking_Fixed_TableVO2;
	}
	public void delete(String rs_sieral) {
		dao.delete(rs_sieral);
	}


	public Booking_Fixed_TableVO getOneBooking_Fixed_Table(String rs_sieral) {
		return dao.findByPrimaryKey(rs_sieral);
	}

	public List<Booking_Fixed_TableVO> getAll() {
		return dao.getAll();
	}
	 public List<Booking_Fixed_TableVO> select_get_rs_id_all(String rs_id){
			
		 return  dao.select_get_rs_id_all(rs_id);
	 };

}
