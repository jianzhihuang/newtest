<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>訂食你後台管理  booking_fixed: Home</title>

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
			<td><h3>訂食你後台管理 booking_fixed: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>首頁 訂食你後台管理 booking_fixed: Home</p>

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
			href='<%=request.getContextPath()%>/front_end/booking_fixed/listAllEmp.jsp'>List</a>
			all Booking_fixed_tables. <br>
		<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/booking_fixed_table/booking_fixed_table.do">
				<b>輸入餐廳公版流水號 (如RSFIX01001):</b> <input type="text" name="rs_sieral">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="booking_Fixed_TableSvc" scope="page"
			class="com.booking_fixed_table.model.Booking_Fixed_TableService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/booking_fixed_table/booking_fixed_table.do">
				<b>選擇餐廳公版流水號:</b> <select size="1" name="rs_sieral">
					<c:forEach var="booking_Fixed_TableVO"
						items="${booking_Fixed_TableSvc.all}">
						<option value="${booking_Fixed_TableVO.rs_sieral}">${booking_Fixed_TableVO.rs_sieral}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/booking_fixed_table/booking_fixed_table.do">
				<b>選擇餐廳桌號:</b> <select size="1" name="rs_sieral">
					<c:forEach var="booking_Fixed_TableVO" items="${booking_Fixed_TableSvc.all}">
						<option value="${booking_Fixed_TableVO.rs_sieral}">${booking_Fixed_TableVO.rs_table_number}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>餐廳公版資訊管理</h3>

	<ul>
		<li><a
			href='<%=request.getContextPath()%>/front_end/booking_fixed/addEmp.jsp' >Add</a>新增一個餐廳公版</li>
	</ul>

</body>
</html>