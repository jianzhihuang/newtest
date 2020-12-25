package com.rest_table.model;

import java.util.List;


public interface Rest_TableDAO_Interface {
	public void insert(Rest_TableVO rest_TableVO);
	public void update(Rest_TableVO rest_TableVO);
	public void delete(String rs_id);
	public Rest_TableVO findByPrimaryKey(String rs_id);
	public List<Rest_TableVO> getAll();
}
