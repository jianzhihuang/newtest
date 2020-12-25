package com.booking_fixed_table.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.JSONObject;

import com.booking_fixed_table.model.Booking_Fixed_TableService;
import com.booking_fixed_table.model.Booking_Fixed_TableVO;
import com.booking_ing_table.model.Booking_Ing_TableVO;

@WebServlet(urlPatterns = { "/Booking_Fixed_Table_Update_Ajax.do" })
public class Booking_Fixed_Table_Update_Ajax extends HttpServlet {
	int i = 0;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		String rs_id = (String)session.getAttribute("rs_id");
		System.out.println(rs_id);
		// 請求進來由字串處理
		String json = getJsonString(req);
		System.out.println(json);
		JSONObject jsonObject = new JSONObject(json);
		//取得字串
		String rs_seat_sieral = jsonObject.getString("rs_seat_sieral");
		String rs_sieral = jsonObject.getString("rs_sieral");
		String rs_table_number = "1";
		Integer rs_table_seat = new Integer(jsonObject.getString("rs_table_seat"));
		/***************************2.開始更新資料***************************************/
		Booking_Fixed_TableService booking_FixedSvc = new Booking_Fixed_TableService();
		Booking_Fixed_TableVO booking_Fixed_TableVO = booking_FixedSvc.updatebooking_fixed_table(rs_sieral
				, rs_table_number, rs_table_seat, rs_seat_sieral);
		//新增修改成功訊息
		jsonObject.put("msg","item update success");
		jsonObject.put("rs_id",rs_id);
		//回傳資訊(json)
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

	public String getJsonString(HttpServletRequest req) {

		StringBuffer json = new StringBuffer();
		try {
			String line = null;
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
}
