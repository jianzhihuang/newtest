package com.booking_ing_table.model;

import java.io.Serializable;



//ORDER_ID	    VARCHAR2(100) NOT NULL,
//RS_STATUS     		NUMBER  NOT NULL,
//RS_SIERAL 	    VARCHAR2(100) NOT NULL,
//PRIMARY KEY (ORDER_ID),
public class Booking_Ing_TableVO implements Serializable{
	private String order_id;
	private Integer rs_status;
	private String rs_sieral;
	public String getRs_sieral() {
		return rs_sieral;
	}
	public void setRs_sieral(String rs_sieral) {
		this.rs_sieral = rs_sieral;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Integer getRs_status() {
		return rs_status;
	}
	public void setRs_status(Integer rs_status) {
		this.rs_status = rs_status;
	}

	





}


