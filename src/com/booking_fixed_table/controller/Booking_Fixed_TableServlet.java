package com.booking_fixed_table.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.booking_fixed_table.model.Booking_Fixed_TableService;
import com.booking_fixed_table.model.Booking_Fixed_TableVO;

public class Booking_Fixed_TableServlet extends HttpServlet {

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
		// 新增
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
			String str = req.getParameter("rs_sieral");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入餐廳流水編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_fixed/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			String rs_sieral = null;
			try {
				rs_sieral = new String(str);
			} catch (Exception e) {
				errorMsgs.add("餐廳流水編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_fixed/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			Booking_Fixed_TableService booking_Fixed_TableSvc = new Booking_Fixed_TableService();
			Booking_Fixed_TableVO booking_Fixed_TableVO = booking_Fixed_TableSvc.getOneBooking_Fixed_Table(rs_sieral);
			if (booking_Fixed_TableVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_fixed/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("booking_Fixed_TableVO", booking_Fixed_TableVO); // 資料庫取出的empVO物件,存入req
			String url = "/front_end/booking_fixed/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_fixed/select_page.jsp");
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
			String rs_table_number = req.getParameter("rs_table_number");
			String rs_table_numberReg = "^[(0-9)]{1,100}$";
			if (rs_table_number == null || rs_table_number.trim().length() == 0) {
				errorMsgs.add("餐廳桌號請勿空白");
			} else if (!rs_table_number.trim().matches(rs_table_numberReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("餐廳桌號: 只能是數字, 且長度必需在1到100之間");
			}

			Integer rs_table_seat = null;

			try {
				rs_table_seat = new Integer(req.getParameter("rs_table_seat").trim());
			} catch (NumberFormatException e1) {
				rs_table_seat = 0;
				errorMsgs.add("餐廳人數請填入數字(ex:1或2)");
			}

			String rs_seat_sieral = req.getParameter("rs_seat_sieral").trim();

			Booking_Fixed_TableVO booking_Fixed_TableVO = new Booking_Fixed_TableVO();
			booking_Fixed_TableVO.setRs_table_number(rs_table_number);
			booking_Fixed_TableVO.setRs_table_seat(rs_table_seat);
			booking_Fixed_TableVO.setRs_seat_sieral(rs_seat_sieral);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				System.out.println("errorMsgs");
				req.setAttribute("booking_Fixed_TableVO", booking_Fixed_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_fixed/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Booking_Fixed_TableService booking_FixedSvc = new Booking_Fixed_TableService();
			booking_Fixed_TableVO = booking_FixedSvc.addbooking_fixed_table(rs_table_number, rs_table_seat,
					rs_seat_sieral);
			System.out.println("insert");
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front_end/booking_fixed/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_fixed/addEmp.jsp");
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
			String rs_sieral = req.getParameter("rs_sieral");

			/*************************** 2.開始查詢資料 ****************************************/
			Booking_Fixed_TableService booking_Fixed_TableSvc = new Booking_Fixed_TableService();
			Booking_Fixed_TableVO booking_Fixed_TableVO = booking_Fixed_TableSvc.getOneBooking_Fixed_Table(rs_sieral);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("booking_Fixed_TableVO", booking_Fixed_TableVO); // 資料庫取出的empVO物件,存入req
			String url = "/front_end/booking_fixed/update_emp_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_fixed/listAllEmp.jsp");
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
			String rs_sieral = req.getParameter("rs_sieral").trim();

			String rs_table_number = req.getParameter("rs_table_number");
			String rs_table_numberReg = "^[(0-9)]{1,100}$";
			if (rs_table_number == null || rs_table_number.trim().length() == 0) {
				errorMsgs.add("餐廳桌號請勿空白");
			} else if (!rs_table_number.trim().matches(rs_table_numberReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("餐廳桌號: 只能是數字, 且長度必需在1到100之間");
			}

			Integer rs_table_seat = null;

			try {
				rs_table_seat = new Integer(req.getParameter("rs_table_seat").trim());
			} catch (NumberFormatException e1) {
				rs_table_seat = 0;
				errorMsgs.add("餐廳人數請填入數字(ex:1或2)");
			}

			String rs_seat_sieral = req.getParameter("rs_seat_sieral").trim();

			Booking_Fixed_TableVO booking_Fixed_TableVO = new Booking_Fixed_TableVO();
			booking_Fixed_TableVO.setRs_sieral(rs_sieral);
			booking_Fixed_TableVO.setRs_table_number(rs_table_number);
			booking_Fixed_TableVO.setRs_table_seat(rs_table_seat);
			booking_Fixed_TableVO.setRs_seat_sieral(rs_seat_sieral);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				System.out.println("errorMsgs");
				req.setAttribute("booking_Fixed_TableVO", booking_Fixed_TableVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/booking_fixed/update_emp_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			Booking_Fixed_TableService booking_FixedSvc = new Booking_Fixed_TableService();
			booking_Fixed_TableVO = booking_FixedSvc.updatebooking_fixed_table(rs_sieral, rs_table_number,
					rs_table_seat, rs_seat_sieral);
			System.out.println("update");
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("booking_Fixed_TableVO", booking_Fixed_TableVO);
			String url = "/front_end/booking_fixed/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/booking_fixed/update_emp_input.jsp");
			failureView.forward(req, res);
		}

	}
	
}
