<%@page import="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@ page import="java.util.*"%>
<%     session.setAttribute("rs_id", "RS10001"); %>
<%-- <%String rs_id = session.getAttribute("rs_id",rs_id); %> --%>
<!-- 到時候合併需要處理-->
<!DOCTYPE html>
<html lang="zh-Hant">

<head>
<meta charset="utf-8">
<title></title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="./css/bk_table.css">
</head>

<body>
	<div class="card">
		<a href="https://icons8.com/icon/40881/restaurant-table">Restaurant Table icon by Icons8</a>
		<img src="./pic/餐廳座位圖@2x.png" class="img-responsive" alt="">
	</div>
		<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<jsp:useBean id="booking_Fixed_TableSvc" scope="page"
		class="com.booking_fixed_table.model.Booking_Fixed_TableService" />
	<jsp:useBean id="rest_Seat_Coordinate_TableSve" scope="page"
		class="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService" />
	
	<article class="task_container">

<!-- 		<button type="button" class="btn_empty"></button> -->
		<h1 class="title1">選擇桌號輸入可以訂位的人數並點選新增</h1>

		<div class="task_add_block">
			<b>選擇餐廳公版流水號:</b> 
			<select size="1" class="task_sel" name="rs_seat_sieral" id="selValue">
				<c:forEach var="rest_Seat_Coordinate_TableVO" items="${rest_Seat_Coordinate_TableSve.get_RS_ID_All(rs_id)}">
					<option value="${rest_Seat_Coordinate_TableVO.rs_seat_sieral}">${rest_Seat_Coordinate_TableVO.rs_seat_sieral}
				</c:forEach>
			</select> <input type="text" class="task_name" placeholder="請輸入人數"> 
			<input type="hidden" name="rs_id" value="${rs_id}">
			<button type="button" class="task_add" id="submit">新增</button>
		</div>

		<div class="task_list_parent">
			<ul class="task_list">
			</ul>
		</div>



	</article>

	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
		integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<!-- //  Option 2: jQuery, Popper.js, and Bootstrap JS -->
	<!-- Latest compiled and minified JavaScript -->
	<script src="./js/bk_table_index.js"></script>
</body>

</html>