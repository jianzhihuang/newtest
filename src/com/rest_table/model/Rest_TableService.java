package com.rest_table.model;

import java.util.List;





public class Rest_TableService {
	private Rest_TableDAO_Interface dao;

	public Rest_TableService() {
		dao = new Rest_TableDAOjdbc();
	}
	public List<Rest_TableVO> getAll() {
		return dao.getAll();
	}
	public Rest_TableVO getOneRest_Table(String rs_id) {
		return dao.findByPrimaryKey(rs_id);
	}
}
