<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.booking_fixed_table.model.*"%>
<%@ page import="com.rest_seat_coordinate.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Booking_Fixed_TableService booking_Fixed_TableSvc = new Booking_Fixed_TableService();
    List<Booking_Fixed_TableVO> list = booking_Fixed_TableSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="rest_Seat_CoordinateSvc" scope="page" class="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService" />
<jsp:useBean id="rest_Seat_CoordinateVO" scope="page" class="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO" />
<html>
<head>
<title>�\�U�q�줽��(BOOKING_FIXED_TABLE)- listAllEmp.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��\�U�q�줽����T - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front_end/booking_fixed/select_page.jsp"><img src="./images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�\�U�����y����</th>
		<th>�\�U�ู</th>
		<th>���H��</th>
		<th>�\�U�y��y����</th>
		<th>�\�U�s��</th>
		<th>�\�U�y��y��</th>
		<th>�ק�</th>
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
	                    ${rest_Seat_Coordinate_TableVO.rs_seat_xy}<br>�i�[�J�ɶ�:${rest_Seat_Coordinate_TableVO.rs_seat_xy_time}�j
                    </c:if>
                </c:forEach>
<%-- 			�i�[�J�ɶ�<%=rest_Seat_Coordinate_TableVO.getRs_seat_xy_time()%>�j --%>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/booking_fixed_table/booking_fixed_table.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="rs_sieral"  value="${booking_Fixed_TableVO.rs_sieral}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>