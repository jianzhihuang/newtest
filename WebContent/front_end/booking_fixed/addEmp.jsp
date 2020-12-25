<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.booking_fixed_table.model.*"%>
<%@ page import="com.rest_seat_coordinate.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rest_table.model.*"%>
<%
 Booking_Fixed_TableVO booking_Fixed_TableVO = (Booking_Fixed_TableVO) request.getAttribute("booking_Fixed_TableVO");
 Booking_Fixed_TableService booking_Fixed_TableSvc = new Booking_Fixed_TableService();
 List<Booking_Fixed_TableVO> list = booking_Fixed_TableSvc.getAll();
 pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�\�U������Ʒs�W - addEmp.jsp</title>

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
	width: 650px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�\�U������Ʒs�W - addEmp.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front_end/booking_fixed/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/booking_fixed_table/booking_fixed_table.do" name="form1">
<table>
	<tr>
		<td>�\�U�ู:</td>
		<td><input type="TEXT" name="rs_table_number" size="45" 
			 value="<%= (booking_Fixed_TableVO==null)? "2" : booking_Fixed_TableVO.getRs_table_number()%>" /></td>
	</tr>
	<tr>
		<td>�\�U�H��:</td>
		<td><input type="TEXT" name="rs_table_seat" size="45"
			 value="<%= (booking_Fixed_TableVO==null)? "2" : booking_Fixed_TableVO.getRs_table_seat()%>" /></td>

	
	<jsp:useBean id="rest_Seat_CoordinateSvc" scope="page" class="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableService" />
	<jsp:useBean id="rest_Seat_CoordinateVO" scope="page" class="com.rest_seat_coordinate.model.Rest_Seat_Coordinate_TableVO" />
	<tr>
		<td>�\�U�y��s��:<font color=red><b>*</b></font></td>
		<td><select size="1" name="rs_seat_sieral">
			<c:forEach var="rest_Seat_CoordinateVO" items="${rest_Seat_CoordinateSvc.all}">
				<option value="${rest_Seat_CoordinateVO.rs_seat_sieral}"${(booking_Fixed_TableVO.rs_seat_sieral==rest_Seat_CoordinateVO.rs_seat_sieral)? 'selected':'' } >${rest_Seat_CoordinateVO.rs_seat_sieral}
			</c:forEach>
		</select></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<%-- <%  --%>
<!-- //   java.sql.Date hiredate = null; -->
<!-- //   try { -->
<!-- // 	    hiredate = booking_Fixed_TableVO.getHiredate(); -->
<!-- //    } catch (Exception e) { -->
<!-- // 	    hiredate = new java.sql.Date(System.currentTimeMillis()); -->
<!-- //    } -->
<%-- %> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
// 	       theme: '',              //theme: 'dark',
// 	       timepicker:false,       //timepicker:true,
// 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
// 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 		   value: '<%=hiredate%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
//            //startDate:	            '2017/07/10',  // �_�l��
//            //minDate:               '-1970-01-01', // �h������(���t)���e
//            //maxDate:               '+1970-01-01'  // �h������(���t)����
//         });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>