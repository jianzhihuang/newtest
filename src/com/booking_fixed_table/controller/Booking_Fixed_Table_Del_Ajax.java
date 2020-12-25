package com.booking_fixed_table.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.booking_fixed_table.model.Booking_Fixed_TableService;
import com.booking_fixed_table.model.Booking_Fixed_TableVO;


@WebServlet(urlPatterns = { "/Booking_Fixed_Table_Del_Ajax.do" })
public class Booking_Fixed_Table_Del_Ajax extends HttpServlet {
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
		//產生錯誤訊息list
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
//			String rs_id = (String)session.getAttribute("rs_id");
			//請求進來由字串處理
			String json = getJsonString(req);
//			System.out.println(json);
			JSONObject jsonObject = new JSONObject(json);
//			System.out.println("del="+jsonObject.toString());
			String rs_sieral = jsonObject.getString("rs_sieral").trim();
			System.out.println("rs_sieral="+rs_sieral);
			
			/***************************2.開始刪除資料***************************************/
			Booking_Fixed_TableService booking_Fixed_TableSvc = new Booking_Fixed_TableService();
			booking_Fixed_TableSvc.delete(rs_sieral);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			//新增到json裡
//			jsonObject.put("rs_id", rs_id);
			jsonObject.put("msg","item delete success");
			System.out.println(jsonObject.toString());
			out.write(jsonObject.toString());
			out.flush();
			out.close();		
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
		}

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
