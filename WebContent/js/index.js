var buttons = $("input.btn_bk_ing_no");
for(let i = 0 ; i<buttons.length;i++){
	buttons[i].addEventListener
	("click", function() {
		swal("取消訂位成功", "歡迎你下次再使用本平台訂位謝謝", "success");
		setTimeout(function(){
			$("form.form3")[i].submit();
		}, 1500); 
	});
}

var buttons = $("input.btn_bk_ing_yes");


for(let i = 0 ; i<buttons.length;i++){
	buttons[i].addEventListener
	("click", function() {
		swal("更改訂位成功", "記得在指定時間前往餐廳用餐祝你用餐愉快", "success");
		
		setTimeout(function(){
			$("form.form2")[i].submit();
		}, 1500); 
	
	});
}
$("#send").on("click",function(){
	  swal("設定成功", "歡迎你下次再使用本平台訂位謝謝", "success");
	});