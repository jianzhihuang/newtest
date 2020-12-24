<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rest_table.model.*"%>
<%@ page import="com.rest_seat_coordinate.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Rest_Seat_Coordinate_TableService rest_Seat_Coordinate_TableSvc = new Rest_Seat_Coordinate_TableService();
    List<Rest_Seat_Coordinate_TableVO> list = rest_Seat_Coordinate_TableSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="rest_TableSvc" scope="page" class="com.rest_table.model.Rest_TableService" />
<jsp:useBean id="rest_TableVO" scope="page" class="com.rest_table.model.Rest_TableVO" />
<html>
<head>
<title>�\�U��l�y��(Rest_Seat_Coordinate_Table)- listAllRS_seat.jsp</title>

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
		 <h3>�Ҧ��\�U��l�y�и�T - listAllRS_seat.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front_end/rest_seat_coordinate/select_pageRS_seat.jsp"><img src="./images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
		<th>�\�U�y��s��</th>
		<th>�\�U�y��y��</th>
		<th>�y��[�J�ɶ�</th>
		<th>�\�U�s��</th>
		<th>�\�U�W��</th>
		<th>�ק�</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="rest_Seat_Coordinate_TableVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${rest_Seat_Coordinate_TableVO.rs_seat_sieral}</td>
			<td>${rest_Seat_Coordinate_TableVO.rs_seat_xy}</td>
			<td>${rest_Seat_Coordinate_TableVO.rs_seat_xy_time}</td>
			<td>${rest_Seat_Coordinate_TableVO.rs_id}</td>
			<td>
				<c:forEach var="rest_TableVO" items="${rest_TableSvc.all}">
                    <c:if test="${rest_Seat_Coordinate_TableVO.rs_id==rest_TableVO.rs_id}">
	                    ${rest_TableVO.rs_name}<br>�i���U���:${rest_TableVO.rs_reg_date}�j
                    </c:if>
                </c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rest_seat_coordinate_table/rest_seat_coordinate_table.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="rs_seat_sieral"  value="${rest_Seat_Coordinate_TableVO.rs_seat_sieral}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>