<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.booking_fixed_table.model.*"%>
<%@ page import="com.rest_seat_coordinate.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Booking_Fixed_TableService booking_Fixed_TableSvc = new Booking_Fixed_TableService();
    List<Booking_Fixed_TableVO> list = booking_Fixed_TableSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="rest_Seat_CoordinateSvc" scope="page" class="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService" />
<jsp:useBean id="rest_Seat_CoordinateVO" scope="page" class="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO" />
<html>
<head>
<title>餐廳訂位公版(BOOKING_FIXED_TABLE)- listAllEmp.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有餐廳訂位公版資訊 - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front_end/booking_fixed/select_page.jsp"><img src="./images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>餐廳公版流水號</th>
		<th>餐廳桌號</th>
		<th>桌位人數</th>
		<th>餐廳座位流水號</th>
		<th>餐廳編號</th>
		<th>餐廳座位座標</th>
		<th>修改</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="booking_Fixed_TableVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${booking_Fixed_TableVO.rs_sieral}</td>
			<td>${booking_Fixed_TableVO.rs_table_number}</td>
			<td>${booking_Fixed_TableVO.rs_table_seat}</td>
			<td>${booking_Fixed_TableVO.rs_seat_sieral}</td>
			<td>
			${rest_Seat_CoordinateSvc.getOneRest_Seat_Coordinate_Table(booking_Fixed_TableVO.rs_seat_sieral).rs_id}
			</td>
			<td>
				<c:forEach var="rest_Seat_Coordinate_TableVO" items="${rest_Seat_CoordinateSvc.all}">
                    <c:if test="${booking_Fixed_TableVO.rs_seat_sieral==rest_Seat_Coordinate_TableVO.rs_seat_sieral}">
	                    ${rest_Seat_Coordinate_TableVO.rs_seat_xy}<br>【加入時間:${rest_Seat_Coordinate_TableVO.rs_seat_xy_time}】
                    </c:if>
                </c:forEach>
<%-- 			【加入時間<%=rest_Seat_Coordinate_TableVO.getRs_seat_xy_time()%>】 --%>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/booking_fixed_table/booking_fixed_table.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="rs_sieral"  value="${booking_Fixed_TableVO.rs_sieral}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>