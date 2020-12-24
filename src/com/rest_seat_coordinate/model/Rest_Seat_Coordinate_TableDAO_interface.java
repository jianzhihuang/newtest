package com.rest_seat_coordinate.model;

import java.util.*;


public interface Rest_Seat_Coordinate_TableDAO_interface {
	public void insert(Rest_Seat_Coordinate_TableVO rest_seat_coordinate_VO);
    public void update(Rest_Seat_Coordinate_TableVO rest_seat_coordinate_VO);
    public Rest_Seat_Coordinate_TableVO findByPrimaryKey(String RS_SEAT_SIERAL);
    public List<Rest_Seat_Coordinate_TableVO> getAll();
	public List<Rest_Seat_Coordinate_TableVO> get_RS_ID_All(String RS_ID);
}
