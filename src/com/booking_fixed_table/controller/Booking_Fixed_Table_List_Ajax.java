package com.booking_fixed_table.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.booking_fixed_table.model.Booking_Fixed_TableService;
import com.booking_fixed_table.model.Booking_Fixed_TableVO;
import com.booking_ing_table.model.Booking_Ing_TableVO;
import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService;
import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO;

@WebServlet(urlPatterns = { "/Booking_Fixed_Table_List_Ajax.do" })
public class Booking_Fixed_Table_List_Ajax extends HttpServlet {
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
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String rs_id = (String) session.getAttribute("rs_id");
			// 請求進來由字串處理
			String json = getJsonString(req);
			// 開始讀取資料
			System.out.println(json);
			JSONObject jsonObject = new JSONObject(json);
			/*************************** 2.開始查詢資料 *****************************************/
			Booking_Fixed_TableService booking_Fixed_TableSvc = new Booking_Fixed_TableService();
			List<Booking_Fixed_TableVO> booking_Fixed_TableVO = booking_Fixed_TableSvc.select_get_rs_id_all(rs_id);

			if (booking_Fixed_TableVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			// 新增到json裡
//			jsonObject.put("rs_id",rs_id);
			// List to JSON
			String jsonStr = new JSONArray(booking_Fixed_TableVO).toString();
			System.out.println(jsonStr);
			out.write(jsonStr);
			out.flush();
			out.close();
//			req.setAttribute("booking_Fixed_TableVO", booking_Fixed_TableVO); // 資料庫取出的empVO物件,存入req
//			String url = "/front_end/booking_ing/listAllBK_ing.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());

		}
	}

//		String rs_seat_sieral = jsonObject.getString("rs_seat_sieral");
//		String rs_table_number = "1";
//		Integer rs_table_seat = new Integer(jsonObject.getString("rs_table_seat"));
//		Booking_Fixed_TableService booking_FixedSvc = new Booking_Fixed_TableService();
//		Booking_Fixed_TableVO booking_Fixed_TableVO = booking_FixedSvc.addbooking_fixed_table(rs_table_number,
//				rs_table_seat, rs_seat_sieral);
//		//取出所有資料
//		List<Booking_Fixed_TableVO> list = booking_FixedSvc.getAll();
//		//取出最後一筆
//		Booking_Fixed_TableVO list2 = list.get(list.size() -1);
//		jsonObject.put("rs_sieral",list2.getRs_sieral());
//		jsonObject.put("sort",new Integer(i++));

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
