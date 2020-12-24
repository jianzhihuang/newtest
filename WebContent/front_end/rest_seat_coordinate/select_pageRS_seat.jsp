<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>訂食你後台管理  Rest_Seat_Coordinate_Table: Home</title>

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
			<td><h3>訂食你後台管理 Rest_Seat_Coordinate_Table: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>首頁 訂食你後台管理 Rest_Seat_Coordinate_Table: Home</p>

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
			href='<%=request.getContextPath()%>/front_end/rest_seat_coordinate/listAllRS_seat.jsp'>List</a>
			all Rest_Seat_Coordinate_Tables. <br>
		<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/rest_seat_coordinate_table/rest_seat_coordinate_table.do">
				<b>輸入餐廳座位編號 (如RSCOD001001):</b> <input type="text" name="rs_seat_sieral">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="rest_Seat_Coordinate_TableSvc" scope="page"
			class="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/rest_seat_coordinate_table/rest_seat_coordinate_table.do">
				<b>選擇餐廳座位編號:</b> <select size="1" name="rs_seat_sieral">
					<c:forEach var="rest_Seat_Coordinate_TableVO"
						items="${rest_Seat_Coordinate_TableSvc.all}">
						<option value="${rest_Seat_Coordinate_TableVO.rs_seat_sieral}">${rest_Seat_Coordinate_TableVO.rs_seat_sieral}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/rest_seat_coordinate_table/rest_seat_coordinate_table.do">
				<b>選擇餐廳編號:</b> <select size="1" name="rs_seat_sieral">
					<c:forEach var="rest_Seat_Coordinate_TableVO" items="${rest_Seat_Coordinate_TableSvc.all}">
						<option value="${rest_Seat_Coordinate_TableVO.rs_seat_sieral}">${rest_Seat_Coordinate_TableVO.rs_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>餐廳位子座標管理</h3>
	<ul>
		<li><a
			href='<%=request.getContextPath()%>/front_end/rest_seat_coordinate/addRS_seat.jsp'>Add</a>新增一個餐廳座位及座位座標</li>
	</ul>

</body>
</html>