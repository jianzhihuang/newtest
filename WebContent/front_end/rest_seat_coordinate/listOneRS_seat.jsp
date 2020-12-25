<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rest_table.model.*"%>
<%@ page import="com.rest_seat_coordinate.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%
	Rest_Seat_Coordinate_TableVO rest_Seat_Coordinate_TableVO = (Rest_Seat_Coordinate_TableVO) request
			.getAttribute("rest_Seat_Coordinate_TableVO");
%>

<%--取出 對應的DeptVO物件--%>
<%-- <% --%>
<!-- // Rest_Seat_Coordinate_TableService rest_Seat_CoordinateSvc = new -->
<!-- Rest_Seat_Coordinate_TableService(); // Rest_Seat_Coordinate_TableVO -->
<!-- rest_Seat_Coordinate_TableVO = -->
<!-- rest_Seat_CoordinateSvcByPrimaryKey(booking_Fixed_TableVO.getRs_seat_sieral()); -->
<%-- %> --%>

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
				<h3>餐廳座位資料 - ListOneEmp.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front_end/rest_seat_coordinate/select_pageRS_seat.jsp"><img
						src="../front_end/rest_seat_coordinate/images/back1.gif"
						width="100" height="33" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>餐廳座位編號</th>
			<th>餐廳座位座標</th>
			<th>座位加入時間</th>
			<th>餐廳編號</th>
		</tr>
		<tr>
			<td><%=rest_Seat_Coordinate_TableVO.getRs_seat_sieral()%></td>
			<td><%=rest_Seat_Coordinate_TableVO.getRs_seat_xy()%></td>
			<td><%=rest_Seat_Coordinate_TableVO.getRs_seat_xy_time()%></td>
			<td><%=rest_Seat_Coordinate_TableVO.getRs_id()%></td>
	
		</tr>
	</table>

</body>
</html>