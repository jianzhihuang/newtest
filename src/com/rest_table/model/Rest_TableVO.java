package com.rest_table.model;

import java.sql.Date;

public class Rest_TableVO {
	private String rs_id;//餐廳編號 not null
	private String cp_contact_email;//聯絡人EMAIL  not null
	private String rs_name;//餐廳名字  not null
	private String rs_address;//餐廳地址  not null
	private String rs_phone;//餐廳電話  not null
	private String rs_intro;//餐廳介紹  not null
	private Integer rs_check_yn;//審核狀態
	private String rs_type;//餐廳類別  not null
	private byte[] rs_big_pic;//餐廳大頭照 
	private byte[] rs_pic;//餐廳座位圖 
	private byte[] rs_view1;//餐廳環視圖1
	private byte[] rs_view2;//餐廳環視圖2
	private byte[] rs_view3;//餐廳環視圖3
	private byte[] rs_view4;//餐廳環視圖4
	private byte[] rs_view5;//餐廳環視圖5
	private String rs_open_time;//營業時間  not null
	private Date rs_reg_date;//註冊日期  not null
	private Integer authority;//權限
	public String getRs_id() {
		return rs_id;
	}
	public void setRs_id(String rs_id) {
		this.rs_id = rs_id;
	}
	public String getCp_contact_email() {
		return cp_contact_email;
	}
	public void setCp_contact_email(String cp_contact_email) {
		this.cp_contact_email = cp_contact_email;
	}
	public String getRs_name() {
		return rs_name;
	}
	public void setRs_name(String rs_name) {
		this.rs_name = rs_name;
	}
	public String getRs_address() {
		return rs_address;
	}
	public void setRs_address(String rs_address) {
		this.rs_address = rs_address;
	}
	public String getRs_phone() {
		return rs_phone;
	}
	public void setRs_phone(String rs_phone) {
		this.rs_phone = rs_phone;
	}
	public String getRs_intro() {
		return rs_intro;
	}
	public void setRs_intro(String rs_intro) {
		this.rs_intro = rs_intro;
	}
	public Integer getRs_check_yn() {
		return rs_check_yn;
	}
	public void setRs_check_yn(Integer rs_check_yn) {
		this.rs_check_yn = rs_check_yn;
	}
	public String getRs_type() {
		return rs_type;
	}
	public void setRs_type(String rs_type) {
		this.rs_type = rs_type;
	}
	public byte[] getRs_big_pic() {
		return rs_big_pic;
	}
	public void setRs_big_pic(byte[] rs_big_pic) {
		this.rs_big_pic = rs_big_pic;
	}
	public byte[] getRs_pic() {
		return rs_pic;
	}
	public void setRs_pic(byte[] rs_pic) {
		this.rs_pic = rs_pic;
	}
	public byte[] getRs_view1() {
		return rs_view1;
	}
	public void setRs_view1(byte[] rs_view1) {
		this.rs_view1 = rs_view1;
	}
	public byte[] getRs_view2() {
		return rs_view2;
	}
	public void setRs_view2(byte[] rs_view2) {
		this.rs_view2 = rs_view2;
	}
	public byte[] getRs_view3() {
		return rs_view3;
	}
	public void setRs_view3(byte[] rs_view3) {
		this.rs_view3 = rs_view3;
	}
	public byte[] getRs_view4() {
		return rs_view4;
	}
	public void setRs_view4(byte[] rs_view4) {
		this.rs_view4 = rs_view4;
	}
	public byte[] getRs_view5() {
		return rs_view5;
	}
	public void setRs_view5(byte[] rs_view5) {
		this.rs_view5 = rs_view5;
	}
	public String getRs_open_time() {
		return rs_open_time;
	}
	public void setRs_open_time(String rs_open_time) {
		this.rs_open_time = rs_open_time;
	}
	
	public Date getRs_reg_date() {
		return rs_reg_date;
	}
	public void setRs_reg_date(Date rs_reg_date) {
		this.rs_reg_date = rs_reg_date;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	
	
}
