  var rs_id = $("input[name='rs_id']").val();
  function init() {
    let form_date = {
      "rs_id" : rs_id,
    }
    let jsonString = JSON.stringify(form_date);

    $.ajax({
      url: "/TEA102G1/Booking_Fixed_Table_List_Ajax.do",           // 資料請求的網址
      type: "POST",                  // GET | POST | PUT | DELETE | PATCH
      data: jsonString,                  // 傳送資料到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      beforeSend: function () {       // 在 request 發送之前執行
        $("ul.task_list").html('<li style="text-align: center;"><i class="fas fa-spinner fa-spin fa-3x"></i></li>');
      },
      success: function (data) {      // request 成功取得回應後執行
  
        // console.log(data);
  
        let list_html = '';
        $.each(data, function (index, item) {
  
          list_html += '<li data-id="' + item.rs_sieral  + '" data-sort="' + item.sort + '">';
          list_html += '<div class="item_flex">';
          list_html += '<div class="middle_block">';
          list_html += '<p class="para">' + '<span>人數:</span>' + item.rs_table_seat + '</p>';
          list_html += '<input type="hidden" name="rs_id" value="'+ item.rs_id +'">';
          list_html += '<input type="hidden" class="rs_seat_sieral_class" value="'+ item.rs_seat_sieral +'">';
          list_html += '<p class="pare">' + '<span>桌號:</span>' + item.rs_seat_sieral + '</p>';
          list_html += '<input type="text" class="task_name_update -none" placeholder="更新待辦事項…" value="' + item.rs_table_seat + '">';
          list_html += '</div>';
          list_html += '<div class="right_block">';
          list_html += '<div class="btn_flex">';
          list_html += '<button type="button" class="btn_update">更新</button>';
          list_html += '<button type="button" class="btn_delete">移除</button>';
          list_html += '</div>';
          list_html += '</div>';
          list_html += '</div>';
          list_html += '</li>';
        });
        $("ul.task_list").html(list_html);
      },
      error: function (xhr) {         // request 發生錯誤的話執行
        console.log("error");
        console.log(xhr);
      }
    });
  }

$(function () {
  init();
  // ==== 待辦事項文字框的 focus 事件及 blur 事件觸發 ===== //
  $("input.task_name").on("focus", function () {
    $("div.task_add_block").addClass("-on");
  });
  $("input.task_name").on("blur", function () {
    $("div.task_add_block").removeClass("-on");
  });

  // ==== text 欄位新增待辦事項 ===== //
  $("input.task_name").on("keyup", function (e) {
    if (e.which == 13) { // 按下 Enter 鍵
      $("button.task_add").click();
    }
  });


  // 按下新增按鈕
  $("button.task_add").on("click", function (e) {

    let rs_seat_sieral = ($("select[name='rs_seat_sieral']").val()).trim();
    let rs_table_seat = ($("input.task_name").val()).trim();
  
    console.log(rs_id);
    
   
    if (rs_table_seat != "") {
      let obj = new reqobj(rs_seat_sieral, rs_table_seat);
      let jsonString = JSON.stringify(obj);
      // console.log(jsonString);
      $.ajax({
        url: "/TEA102G1/Booking_Fixed_Table_Add_Ajax.do",//資料請求網址
        type: "POST",
        data: jsonString,
        dataType: "json",
        beforeSend: function () {       // 在 request 發送之前執行
        },
        success: function (data) {
      
          console.log(data.rs_sieral);
          let list_html = "";


          list_html += '<li data-id="' + data.rs_sieral  + '" data-sort="' + data.sort + '">';
          list_html += '<div class="item_flex">';
          list_html += '<div class="middle_block">';
          list_html += '<p class="para">' + '<span>人數:</span>' + data.rs_table_seat + '</p>';
          list_html += '<input type="hidden" name="rs_id" value="'+ data.rs_id +'">';
          list_html += '<input type="hidden" class="rs_seat_sieral_class" value="'+ data.rs_seat_sieral +'">';
          list_html += '<p class="pare">' + '<span>桌號:</span>' + data.rs_seat_sieral + '</p>';
          list_html += '<input type="text" class="task_name_update -none" placeholder="更新待辦事項…" value="' + data.rs_table_seat + '">';
          list_html += '</div>';
          list_html += '<div class="right_block">';
          list_html += '<div class="btn_flex">';
          list_html += '<button type="button" class="btn_update">更新</button>';
          list_html += '<button type="button" class="btn_delete">移除</button>';
          list_html += '</div>';
          list_html += '</div>';
          list_html += '</div>';
          list_html += '</li>';

          $("ul.task_list").prepend(list_html);
          $("input.task_name").val("");

        },
        error: function (xhr) {         // request 發生錯誤的話執行
          console.log("error");
        }


      })



    }
  });
});

// ==== 移除待辦事項 ===== //
$("ul.task_list").on("click", "button.btn_delete", function () {
  let r = confirm("確認移除？")
  if (r) {
    let rs_sieral = $(this).closest("li").attr("data-id");
    let that = this;
    let form_date = {
      "rs_id" : rs_id,
      "rs_sieral":rs_sieral,
    }
    let jsonString = JSON.stringify(form_date);
    $.ajax({
      url: "/TEA102G1/Booking_Fixed_Table_Del_Ajax.do",           // 資料請求的網址
      type: "POST",                  // GET | POST | PUT | DELETE | PATCH
      data: jsonString,                  // 傳送資料到指定的 url
      //processData: false,
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      beforeSend: function () {       // 在 request 發送之前執行
      },
      success: function (data) {      // request 成功取得回應後執行
        // console.log(data);
        $(that).closest("li").animate({
          "opacity": 0
        }, 1000, "swing", function () {
          $(this).remove();
        });
      },
      error: function (xhr) {         // request 發生錯誤的話執行
        console.log("error");
        console.log(xhr);
      }
    });

  }
});
$("button.btn_empty").on("click", function () {
  let r = confirm("確認清空？")
  if (r) {
    $("ul.task_list").children("li").animate({
      "opacity": 0
    }, 1000, "swing", function () {
      $(this).remove();
    });
  }
});

// ==== 更新待辦事項 ===== //
$("ul.task_list").on("click", "button.btn_update", function () {
 
 
  let update_rs_table_seat = ($(this).closest("li").find("input.task_name_update").val()).trim();
  let update_rs_seat_sieral = $(this).closest("li").find("input.rs_seat_sieral_class").val();
  if (update_rs_table_seat == "") {
    alert("請輸入待辦事項");
  } else {
    $(this).closest("li").find("p.para").html(update_rs_table_seat).toggleClass("-none");
    $(this).closest("li").find("input.task_name_update").toggleClass("-none");

    if ($(this).closest("li").find("input.task_name_update").hasClass("-none")) {
      //alert("呼叫更新待辦事項 api");
      let form_date = {
        "rs_id" : rs_id,
        "rs_seat_sieral" :update_rs_seat_sieral,
        "rs_sieral": $(this).closest("li").attr("data-id"),
        "rs_table_seat" : update_rs_table_seat,
        "sort" : $(this).closest("li").attr("data-sort")
      }
      let jsonString = JSON.stringify(form_date);
      
      $.ajax({
        url: "/TEA102G1/Booking_Fixed_Table_Update_Ajax.do",           // 資料請求的網址
        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
        data:jsonString,                  // 傳送資料到指定的 url
        //processData: false,
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        beforeSend: function () {       // 在 request 發送之前執行
        },
        success: function (data) {      // request 成功取得回應後執行
          // console.log(data);

          if (data.msg == "item update success") {
            alert("更新成功");
          }

        },
        error: function (xhr) {         // request 發生錯誤的話執行
          console.log("error");
          console.log(xhr);
        },
        complete: function (xhr) {
        }
      });
    }
  }
});

// ==== 排序 ===== //
// $("ul.task_list").on("click", "button.btn_up, button.btn_down", function () {



function reqobj(rs_seat_sieral, rs_table_seat) {
  this.rs_seat_sieral = rs_seat_sieral;
  this.rs_table_seat = rs_table_seat;

}  
