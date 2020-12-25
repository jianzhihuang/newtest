<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>�q���A��x�޲z  Rest_Seat_Coordinate_Table: Home</title>

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
			<td><h3>�q���A��x�޲z Rest_Seat_Coordinate_Table: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>���� �q���A��x�޲z Rest_Seat_Coordinate_Table: Home</p>

	<h3>��Ƭd��:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
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
				<b>��J�\�U�y��s�� (�pRSCOD001001):</b> <input type="text" name="rs_seat_sieral">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<jsp:useBean id="rest_Seat_Coordinate_TableSvc" scope="page"
			class="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/rest_seat_coordinate_table/rest_seat_coordinate_table.do">
				<b>����\�U�y��s��:</b> <select size="1" name="rs_seat_sieral">
					<c:forEach var="rest_Seat_Coordinate_TableVO"
						items="${rest_Seat_Coordinate_TableSvc.all}">
						<option value="${rest_Seat_Coordinate_TableVO.rs_seat_sieral}">${rest_Seat_Coordinate_TableVO.rs_seat_sieral}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/rest_seat_coordinate_table/rest_seat_coordinate_table.do">
				<b>����\�U�s��:</b> <select size="1" name="rs_seat_sieral">
					<c:forEach var="rest_Seat_Coordinate_TableVO" items="${rest_Seat_Coordinate_TableSvc.all}">
						<option value="${rest_Seat_Coordinate_TableVO.rs_seat_sieral}">${rest_Seat_Coordinate_TableVO.rs_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>
	</ul>


	<h3>�\�U��l�y�к޲z</h3>
	<ul>
		<li><a
			href='<%=request.getContextPath()%>/front_end/rest_seat_coordinate/addRS_seat.jsp'>Add</a>�s�W�@���\�U�y��ήy��y��</li>
	</ul>

</body>
</html>