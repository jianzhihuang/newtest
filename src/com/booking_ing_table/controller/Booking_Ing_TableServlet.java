package com.booking_ing_table.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.booking_ing_table.model.Booking_Ing_TableService;
import com.booking_ing_table.model.Booking_Ing_TableVO;
import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService;
import com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO;


public class Booking_Ing_TableServlet extends HttpServlet {

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
////		 新增
		if ("insert".equals(action)) {
			insert(req, res);
			// 來自addEmp.jsp的請求
		}
		if ("getOne_For_Update".equals(action)) {
			// 來自listAllEmp.jsp的請求
			getOne_For_Update(req, res);
		}
		if ("update".equals(action)) { 
			// 來自update_emp_input.jsp的請求
			update(req, res);
		}
		if("update_status".equals(action)){
			//來自listAll_Status_BK_ing.jsp請求
			
			update_status(req, res);
		}

		
	}

	public void getOne_For_Display(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// 來自select_page.jsp的請求
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("order_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入餐廳訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/booking_ing/select_pageBK_ing.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			String order_id = null;
			try {
				order_id = new String(str);
			} catch (Exception e) {
				errorMsgs.add("餐廳訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/booking_ing/select_pageBK_ing.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			Booking_Ing_TableService booking_Ing_TableSvc = new Booking_Ing_TableService();
			Booking_Ing_TableVO booking_Ing_TableVO = booking_Ing_TableSvc.getOneBooking_Ing_Table(order_id);
			if (booking_Ing_TableVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/booking_ing/select_pageBK_ing.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("booking_Ing_TableVO", booking_Ing_TableVO); // 資料庫取出的empVO物件,存入req
			String url = "/front_end/booking_ing/listOneBK_ing.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_ing/select_pageBK_ing.jsp");
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
//			String rs_status = req.getParameter("rs_status");
			
			Integer rs_status =null;
			
			try {
				
				rs_status = new Integer(req.getParameter("rs_status").trim());
				if(rs_status>=2) {
					errorMsgs.add("餐廳座位狀態請填入數字範圍內(ex:0(未訂位)或1(已訂位))");
					}
			} catch (NumberFormatException e1) {
				rs_status = 0;
				
				errorMsgs.add("餐廳座位狀態請填入數字(ex:0(未訂位)或1(已訂位))");
			}
			
	
			String rs_sieral = req.getParameter("rs_sieral").trim();

			Booking_Ing_TableVO booking_Ing_TableVO = new Booking_Ing_TableVO();
			booking_Ing_TableVO.setRs_status(rs_status);
			booking_Ing_TableVO.setRs_sieral(rs_sieral);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				System.out.println("errorMsgs");
				req.setAttribute("booking_Ing_TableVO", booking_Ing_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_ing/addBK_ing.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/*************************** 2.開始新增資料 ***************************************/
			Booking_Ing_TableService booking_Ing_TableSvc = new Booking_Ing_TableService();
			booking_Ing_TableVO = booking_Ing_TableSvc.addBooking_Ing_Table(rs_status, rs_sieral);
			System.out.println("insert");
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front_end/booking_ing/listAllBK_ing.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_ing/addBK_ing.jsp");
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
			String order_id = req.getParameter("order_id");

			/*************************** 2.開始查詢資料 ****************************************/
			Booking_Ing_TableService booking_Ing_TableSvc = new Booking_Ing_TableService();
			Booking_Ing_TableVO booking_Ing_TableVO = booking_Ing_TableSvc.getOneBooking_Ing_Table(order_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("booking_Ing_TableVO", booking_Ing_TableVO); // 資料庫取出的empVO物件,存入req
			String url = "/front_end/booking_ing/update_BK_ing_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_ing/listAllBK_ing.jsp");
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
			String order_id = req.getParameter("order_id").trim();

			Integer rs_status = null;
			
			try {
				rs_status = new Integer(req.getParameter("rs_status").trim());
			} catch (NumberFormatException e1) {
				rs_status = 0;
				errorMsgs.add("餐廳座位狀態請填入數字(ex:0(未訂位)或1(已訂位))");
			}
			

			String rs_sieral = req.getParameter("rs_sieral").trim();

			Booking_Ing_TableVO booking_Ing_TableVO = new Booking_Ing_TableVO();
			booking_Ing_TableVO.setOrder_id(order_id);
			booking_Ing_TableVO.setRs_status(rs_status);
			booking_Ing_TableVO.setRs_sieral(rs_sieral);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				System.out.println("errorMsgs");
				req.setAttribute("booking_Ing_TableVO", booking_Ing_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/booking_ing/update_BK_ing_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			Booking_Ing_TableService booking_Ing_TableSvc = new Booking_Ing_TableService();
			booking_Ing_TableVO = booking_Ing_TableSvc.
					updateBooking_Ing_Table(rs_sieral, rs_status, order_id);
		
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("booking_Ing_TableVO", booking_Ing_TableVO);
			String url = "/front_end/booking_ing/listOneBK_ing.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_ing/update_BK_ing_input.jsp");
			failureView.forward(req, res);
		}

	}
	
	public void update_status(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			String order_id = req.getParameter("order_id").trim();
			
			Integer rs_status = new Integer(req.getParameter("rs_status"));
			
			/***************************2.開始更改資料***************************************/
			Booking_Ing_TableService Booking_Ing_TableSvc = new Booking_Ing_TableService();
			Booking_Ing_TableSvc.updateBooking_Ing_Status(rs_status, order_id);
			
			/***************************3.更改完成,準備轉交(Send the Success view)***********/								
			String url = "/front_end/booking_ing/listAllBK_ing.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 更改成功後,轉交回送出更改的來源網頁
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("更改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front_end/booking_ing/listAll_Status_BK_ing.jsp");
			failureView.forward(req, res);
		}
		
		
	}
	public void get_RS_ID_All(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		// 來自select_page.jsp的請求
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
					String rs_id = req.getParameter("rs_id");
				
									// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/booking_ing/select_pageBK_ing.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					/*************************** 2.開始查詢資料 *****************************************/
					Rest_Seat_Coordinate_TableService rest_Seat_Coordinate_TableSvc = new Rest_Seat_Coordinate_TableService();
					List<Rest_Seat_Coordinate_TableVO> rest_Seat_Coordinate_TableVO = rest_Seat_Coordinate_TableSvc.get_RS_ID_All(rs_id);
					if (rest_Seat_Coordinate_TableVO == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front_end/booking_ing/select_pageBK_ing.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
					
					/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//					System.out.println("enter");
					req.setAttribute("rest_Seat_Coordinate_TableVO", rest_Seat_Coordinate_TableVO); // 資料庫取出的empVO物件,存入req
					String url = "/front_end/booking_ing/listAllBK_ing.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 *************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_ing/select_pageBK_ing.jsp");
					failureView.forward(req, res);
				}
			}

}
