package com.booking_ing_table.model;

import java.util.*;







public interface Booking_Ing_TableDAO_interface {
	public void insert(Booking_Ing_TableVO booking_ing_tablevo);
    public void update(Booking_Ing_TableVO booking_ing_tablevo);
    public void update_status(Booking_Ing_TableVO booking_ing_tablevo);
    public Booking_Ing_TableVO findByPrimaryKey(String order_id);
    public List<Booking_Ing_TableVO> getAll();
    public List<Booking_Ing_TableVO> findByPrimaryKey_Status(Integer rs_status);
    
    
}
