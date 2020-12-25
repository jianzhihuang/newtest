package com.rest_seat_coordinate.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService;
import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO;

public class Rest_Seat_Coordinate_TableServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 查詢
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) {
			getOne_For_Display(req, res);
		}
//		 新增
		if ("insert".equals(action)) {
			insert(req, res);// 來自addEmp.jsp的請求
		}
		if ("getOne_For_Update".equals(action)) {
			// 來自listAllEmp.jsp的請求
			getOne_For_Update(req, res);
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			update(req, res);
		}

	}

	public void getOne_For_Display(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {// 來自select_page.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("rs_seat_sieral");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入餐廳座位編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rest_seat_coordinate/select_pageRS_seat.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			String rs_seat_sieral = null;
			try {
				rs_seat_sieral = new String(str);
			} catch (Exception e) {
				errorMsgs.add("餐廳座位編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rest_seat_coordinate/select_pageRS_seat.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			Rest_Seat_Coordinate_TableService rest_Seat_Coordinate_TableSvc = new Rest_Seat_Coordinate_TableService();
			Rest_Seat_Coordinate_TableVO rest_Seat_Coordinate_TableVO = rest_Seat_Coordinate_TableSvc
					.getOneRest_Seat_Coordinate_Table(rs_seat_sieral);
			if (rest_Seat_Coordinate_TableVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rest_seat_coordinate/select_pageRS_seat.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("rest_Seat_Coordinate_TableVO", rest_Seat_Coordinate_TableVO); // 資料庫取出的empVO物件,存入req
			String url = "/front_end/rest_seat_coordinate/listOneRS_seat.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front_end/rest_seat_coordinate/select_pageRS_seat.jsp");
			failureView.forward(req, res);
		}
	}

	public void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 來自addEmp.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String rs_seat_xy = req.getParameter("rs_seat_xy");
			String rs_seat_xyReg = "^[(0-9,.)]{1,100}$";
			if (rs_seat_xy == null || rs_seat_xy.trim().length() == 0) {
				errorMsgs.add("餐廳座標請勿空白");
			} 
			else if (!rs_seat_xy.trim().matches(rs_seat_xyReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("餐廳座標:(ex:115.541,10.546)只能是數字,經緯度必須以逗號\",\"區隔且長度必需在1到100之間");
			}

			java.sql.Date rs_seat_xy_time = null;

			try {
				rs_seat_xy_time = java.sql.Date.valueOf(req.getParameter("rs_seat_xy_time").trim());
			} catch (IllegalArgumentException e1) {
				rs_seat_xy_time = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			String rs_id = req.getParameter("rs_id").trim();

			Rest_Seat_Coordinate_TableVO rest_Seat_Coordinate_TableVO = new Rest_Seat_Coordinate_TableVO();
			rest_Seat_Coordinate_TableVO.setRs_seat_xy(rs_seat_xy);
			rest_Seat_Coordinate_TableVO.setRs_seat_xy_time(rs_seat_xy_time);
			rest_Seat_Coordinate_TableVO.setRs_id(rs_id);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				System.out.println("errorMsgs");
				req.setAttribute("rest_Seat_Coordinate_TableVO", rest_Seat_Coordinate_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rest_seat_coordinate/addRS_seat.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Rest_Seat_Coordinate_TableService rest_Seat_Coordinate_TableSvc = new Rest_Seat_Coordinate_TableService();
			rest_Seat_Coordinate_TableVO = rest_Seat_Coordinate_TableSvc.addRest_Seat_Coordinate(rs_seat_xy,
					rs_seat_xy_time, rs_id);
//			System.out.println("insert");
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front_end/rest_seat_coordinate/listAllRS_seat.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/rest_seat_coordinate/addRS_seat.jsp");
			failureView.forward(req, res);
		}
	}

	public void getOne_For_Update(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 ****************************************/
			String rs_seat_sieral = req.getParameter("rs_seat_sieral");

			/*************************** 2.開始查詢資料 ****************************************/
			Rest_Seat_Coordinate_TableService rest_Seat_Coordinate_TableSvc = new Rest_Seat_Coordinate_TableService();
			Rest_Seat_Coordinate_TableVO rest_Seat_Coordinate_TableVO = rest_Seat_Coordinate_TableSvc.getOneRest_Seat_Coordinate_Table(rs_seat_sieral);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("rest_Seat_Coordinate_TableVO", rest_Seat_Coordinate_TableVO); // 資料庫取出的empVO物件,存入req
			String url = "/front_end/rest_seat_coordinate/update_RS_seat_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/rest_seat_coordinate/listAllRS_seat.jsp");
			failureView.forward(req, res);
		}
	}

	public void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String rs_seat_sieral = req.getParameter("rs_seat_sieral").trim();

			String rs_seat_xy = req.getParameter("rs_seat_xy");
			String rs_seat_xyReg = "^[(0-9,.)]{1,100}$";
			if (rs_seat_xy == null || rs_seat_xy.trim().length() == 0) {
				errorMsgs.add("餐廳座標請勿空白");
			} 
			else if (!rs_seat_xy.trim().matches(rs_seat_xyReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("餐廳座標:(ex:115.541,10.546)只能是數字,經緯度必須以逗號\",\"區隔且長度必需在1到100之間");
			}

			java.sql.Date rs_seat_xy_time = null;

			try {
				rs_seat_xy_time = java.sql.Date.valueOf(req.getParameter("rs_seat_xy_time").trim());
			} catch (IllegalArgumentException e1) {
				rs_seat_xy_time = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			String rs_id = req.getParameter("rs_id").trim();

			Rest_Seat_Coordinate_TableVO rest_Seat_Coordinate_TableVO = new Rest_Seat_Coordinate_TableVO();
			rest_Seat_Coordinate_TableVO.setRs_seat_sieral(rs_seat_sieral);
			rest_Seat_Coordinate_TableVO.setRs_seat_xy(rs_seat_xy);
			rest_Seat_Coordinate_TableVO.setRs_seat_xy_time(rs_seat_xy_time);
			rest_Seat_Coordinate_TableVO.setRs_id(rs_id);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				System.out.println("errorMsgs");
				req.setAttribute("rest_Seat_Coordinate_TableVO", rest_Seat_Coordinate_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/rest_seat_coordinate/update_RS_seat_input.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始修改資料 *****************************************/
			Rest_Seat_Coordinate_TableService rest_Seat_Coordinate_TableSvc = new Rest_Seat_Coordinate_TableService();
			rest_Seat_Coordinate_TableVO = rest_Seat_Coordinate_TableSvc.updateRest_Seat_Coordinate(rs_seat_sieral,
					rs_seat_xy, rs_seat_xy_time, rs_id);
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("rest_Seat_Coordinate_TableVO", rest_Seat_Coordinate_TableVO);
			String url = "/front_end/rest_seat_coordinate/listOneRS_seat.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/rest_seat_coordinate/update_RS_seat_input.jsp");
			failureView.forward(req, res);
		}

	}

	
		
		
}


