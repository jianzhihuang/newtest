<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.rest_table.model.Rest_TableVO"%>
<%@ page import="com.rest_seat_coordinate.model.*"%>
<%
	Rest_Seat_Coordinate_TableVO rest_Seat_Coordinate_TableVO = (Rest_Seat_Coordinate_TableVO) request.getAttribute("rest_Seat_Coordinate_TableVO");
%>
<%     session.setAttribute("rs_id", "RS10001"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="./css/bking.css">
</head>
<body>
	<div class="drag_drop">
		<div class="tab_panel drag_panel">
			<ul class="draggable_item">

				<li id="table" class="table">
					<canvas id="table_pic" width="100px" height="50px"
						style="border: 1px solid #000000"></canvas>
				</li>
			</ul>
		</div>
		<div class="tab_panel drop_panel">
			<span>�Ч��l�즲�ܦ�</span>
			<ul class="drop_ul"></ul>
		</div>
	</div>


	<button id="send">�]�w��m</button>
	
</FORM>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script>
		$("#send").on("click", function() {
			var dataArray = [];
			let $next = $("ul.drop_ul li:first-child");
// 			/*�N������J�}�C��*/
			for (let i = 0; i < $("ul.drop_ul").children("li").length; i++) {
				let obj = {};
				obj.xy = $next.offset()
// 				console.log(obj.xy)
				obj.obj = $next.data();
				dataArray.push(obj);
				$next = $next.next();
			}
			
			console.log(dataArray);
			var jsonString = JSON.stringify(dataArray);
			console.log(jsonString);
			
			$.ajax({
				type : "POST",
				url : "/TEA102G1/GetJson.do",
				data : jsonString,//�����Ǫ��̪����
				
// 				data:{
// 				    userName:userName,
// 				    password:password
// 				},
				success : function() {
						
					
						
				},
				error : function() {
					alert("�t�ο��~-loginPage.jsp-ajax");
				}
			});
			
		});
	</script>
<!-- 	����즲���� -->
	<script>
// 		/*�ݰ��ƶ�
// // 		1.�u��drag and drop �ƹ��۹�󤸯�����m�A�H��drop �᪺xy�y��
// // 		2. �q���using ajax������
// // 		3. �s���Ʈw

// 		/*-------------------------�ܼƫŧi-------------------------*/
// 		/*�i�H�Q�즲��������*/
		$li = $("li");
// 		/*�i�H��m������drop�ϰ�*/
		$drop_panel = $("div.drop_panel");

// 		/*���\�i�H�즲������drop�ϰ�*/
		$drop_panel.on("dragover", function(event) {
			event.preventDefault();
		})

// 		/*�N����drop��Ӱϰ��Q��dataTransfer���X��id�A�ýƻs�@�������A�H�K��drop�ϰ�*/
		$drop_panel.on("drop", function(event) {
			let el = event.originalEvent.dataTransfer.getData("item");//���o����id
			$copyEl = $(el).clone();
			$("ul.drop_ul").append($copyEl);
// 			/*��J��]�w��m�~���ĪG*/
			let xy = $("ul.drop_ul").children(":last-child").offset({
				left : event.pageX,
				top : event.pageY
			})
			$("ul.drop_ul").children(":last-child").data("object", el);

		});

// 		/*���\�����Q�즲*/
		$li.attr("draggable", "true");

// 		/*�����}�l�즲�ɡA�O��Q�즲������ID�AdataTransfer������map�����c�A���u��s������ID*/
		$li.on("dragstart", function(event) {
			event.originalEvent.dataTransfer.setData("item",`#\${event.target.id}`);
		});
	</script>
</body>

</html>