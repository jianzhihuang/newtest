<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.booking_fixed_table.model.*"%>
<%@ page import="com.booking_ing_table.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%
	Booking_Ing_TableVO booking_Ing_TableVO = (Booking_Ing_TableVO) request
			.getAttribute("booking_Ing_TableVO");
%>

<%--���X ������DeptVO����--%>
<%
Booking_Fixed_TableService booking_Fixed_TableSvc = new Booking_Fixed_TableService();
Booking_Fixed_TableVO booking_Fixed_TableVO = booking_Fixed_TableSvc.getOneBooking_Fixed_Table(booking_Ing_TableVO.getRs_sieral());

%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css" />
<title>�\�U�q���� - listOneBK_ing.jsp</title>
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

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	
	<table id="table-1">
		<tr>
			<td>
				<h3>�\�U�q����  - listOneBK_ing.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/front_end/booking_ing/select_pageBK_ing.jsp"><img
						src="../front_end/booking_fixed/images/back1.gif" width="100" height="33" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�\�U�q��y����</th>
			<th>�\�U�y�쪬�A</th>
			<th>�\�U�y��y����</th>
			<th>�\�U�����y����</th>
			<th>�\�U�ู</th>
			<th>�ู�H��</th>
			<th>�ק�</th>
			<th>�ק�q��</th>
		</tr>
		<tr>
			<td><%=booking_Ing_TableVO.getOrder_id()%></td>
			<td>${(booking_Ing_TableVO.rs_status=='0')? '���q��':'�w�q��'}(<%=booking_Ing_TableVO.getRs_status()%>)</td>
			<td><%=booking_Fixed_TableVO.getRs_seat_sieral()%></td>
			<td><%=booking_Ing_TableVO.getRs_sieral()%></td>
			<td><%=booking_Fixed_TableVO.getRs_table_number()%></td>
			<td><%=booking_Fixed_TableVO.getRs_table_seat()%></td>
							<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/booking_ing_table/booking_Ing_TableServlet.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="order_id" value="${booking_Ing_TableVO.order_id}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/booking_ing_table/booking_Ing_TableServlet.do"
						style="margin-bottom: 0px;" class="form2">
						<input type="hidden"
						name="order_id" value="${booking_Ing_TableVO.order_id}">
						<input type="hidden"
						name="rs_status" value="1">
						<input type="hidden" name="action" value="update_status">
						<input type="button" class="btn_bk_ing_yes" value="�q��" >
					</FORM>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/booking_ing_table/booking_Ing_TableServlet.do"
						style="margin-bottom: 0px;" class="form3">
						<input type="button" value="�����q��" class="btn_bk_ing_no"> 
						<input type="hidden"
						name="order_id" value="${booking_Ing_TableVO.order_id}">
						<input type="hidden"
						name="rs_status" value="0">
						<input type="hidden" name="action" value="update_status">
					</FORM>
				</td>
		</tr>
	</table>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
<script src="<%=request.getContextPath()%>/js/index.js"></script>

</body>
</html>