<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.booking_fixed_table.model.*"%>
<%@ page import="com.rest_seat_coordinate.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%
	Booking_Fixed_TableVO booking_Fixed_TableVO = (Booking_Fixed_TableVO) request
			.getAttribute("booking_Fixed_TableVO");
%>

<%--取出 對應的DeptVO物件--%>
<%
	Rest_Seat_Coordinate_TableService rest_Seat_CoordinateSvc = new Rest_Seat_Coordinate_TableService();
	Rest_Seat_Coordinate_TableVO rest_Seat_Coordinate_TableVO = rest_Seat_CoordinateSvc.getOneRest_Seat_Coordinate_Table(booking_Fixed_TableVO.getRs_seat_sieral());
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>餐廳公版資料 - ListOneEmp.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/front_end/booking_fixed/select_page.jsp"><img
						src="../front_end/booking_fixed/images/back1.gif" width="100" height="33" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>餐廳公版流水號</th>
			<th>餐廳桌號</th>
			<th>桌位人數</th>
			<th>餐廳座位流水號</th>
			<th>餐廳編號</th>
			<th>餐廳座位座標</th>
		</tr>
		<tr>
			<td><%=booking_Fixed_TableVO.getRs_sieral()%></td>
			<td><%=booking_Fixed_TableVO.getRs_table_number()%></td>
			<td><%=booking_Fixed_TableVO.getRs_table_seat()%></td>
			<td><%=booking_Fixed_TableVO.getRs_seat_sieral()%></td>
			<td><%=rest_Seat_Coordinate_TableVO.getRs_id()%></td>
			<td><%=rest_Seat_Coordinate_TableVO.getRs_seat_xy()%><br>【加入時間<%=rest_Seat_Coordinate_TableVO.getRs_seat_xy_time()%>】</td>
		</tr>
	</table>

</body>
</html>