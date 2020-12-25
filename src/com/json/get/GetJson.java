package com.json.get;
//package servlet_examples;

import java.io.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService;
import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO;

@WebServlet(urlPatterns = { "/GetJson.do" })
public class GetJson extends HttpServlet {
	Rest_Seat_Coordinate_TableVO rest_Seat_Coordinate_TableVO = new Rest_Seat_Coordinate_TableVO();
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		getJSON(req, res);
	}

	//接收資料json
	public String readJSONString(HttpServletRequest req) {
		StringBuffer json = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
				System.out.println(json);

			}
		} catch (Exception e) {
//			System.out.println(e.toString());
		}

		return json.toString();
	}

	public void getJSON(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String read = readJSONString(req);
		String rs_seat_xy = null ;
		// JSON to List
		
		try {
			JSONArray jsonStr = new JSONArray(read);
			for (int i = 0; i < jsonStr.length(); i++) {
				JSONObject json_book = jsonStr.getJSONObject(i);
				JSONObject xyObj =  (JSONObject) json_book.get("xy");
				rs_seat_xy = xyObj.toString();
				java.sql.Date rs_seat_xy_time = new Date(System.currentTimeMillis());
				HttpSession session = req.getSession();
				String rs_id = null;
				rs_id = (String)session.getAttribute("rs_id");
//				有需要可以切割注意前端回傳的資料類型可以使用getclass().getname()取得類型
				int top = xyObj.getInt("top");//前端int 
				int left = xyObj.getInt("left");//前端int 
//				String topSting = String.valueOf(top);
//				String leftSting =String.valueOf(left);
				       
				rs_seat_xy=  String.valueOf(top+","+left);;
				/*************************** 2.開始新增資料 ***************************************/
				Rest_Seat_Coordinate_TableService rest_Seat_Coordinate_TableSvc = new Rest_Seat_Coordinate_TableService();
				rest_Seat_Coordinate_TableVO = rest_Seat_Coordinate_TableSvc.addRest_Seat_Coordinate(rs_seat_xy,
						rs_seat_xy_time, rs_id);
				
			}
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
//		System.out.println("insert");

	}
}