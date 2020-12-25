<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.booking_fixed_table.model.*"%>
<%@ page import="com.booking_ing_table.model.*"%>
<%@ page import="com.rest_seat_coordinate.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rest_table.model.*"%>
<html>
<head>
<title>訂食你後台管理 booking_ing: Home</title>

<style>
table#table-1 {
	width: 650px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>訂食你後台管理 Booking_ing: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>首頁 訂食你後台管理 Booking_ing: Home</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a
			href='<%=request.getContextPath()%>/front_end/booking_ing/listAllBK_ing.jsp'>List</a>
			all Booking_ing_tables. <br> <br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/booking_ing_table/booking_Ing_TableServlet.do">
				<b>輸入訂單編號 (如OD00101):</b> <input type="text" name="order_id">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="booking_Ing_TableSvc" scope="page"
			class="com.booking_ing_table.model.Booking_Ing_TableService" />
		<jsp:useBean id="rest_TableSvc" scope="page"
			class="com.rest_table.model.Rest_TableService" />
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/booking_ing_table/booking_Ing_TableServlet.do">
				<b>選擇餐廳訂單編號:</b> <select size="1" name="order_id">
					<c:forEach var="booking_Ing_TableVO"
						items="${booking_Ing_TableSvc.all}">
						<option value="${booking_Ing_TableVO.order_id}">${booking_Ing_TableVO.order_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/booking_ing_table/booking_Ing_TableServlet.do">
				<b>選擇餐廳公版流水號:</b> <select size="1" name="order_id">
					<c:forEach var="booking_Ing_TableVO"
						items="${booking_Ing_TableSvc.all}">
						<option value="${booking_Ing_TableVO.order_id}">${booking_Ing_TableVO.rs_sieral}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>



		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/front_end/booking_ing/listAll_Status_BK_ing.jsp">
				<b>選擇是否訂位的訂單:</b> <select size="1" name="rs_status">
					<option value="0">未訂位</option>
					<option value="1">已訂位</option>
				</select> <input type="hidden" name="action" value="getStatus"> <input
					type="submit" value="送出">
			</FORM>
		</li>

	</ul>


	<h3>餐廳訂位資訊管理</h3>

	<ul>
		<li><a
			href='<%=request.getContextPath()%>/front_end/booking_ing/addBK_ing.jsp'>Add</a>新增一個餐廳訂位</li>
	</ul>

</body>
</html>