$(document).ready(function () {
	// // 全选
	// $(".allselect").click(function () {
	// 	$(".gwc_tb2 input[name=newslist]").each(function () {
	// 		$(this).attr("checked", true);
	// 	});
	// 	GetCount();
	// });

	// //反选
	// $("#invert").click(function () {
	// 	$(".gwc_tb2 input[name=newslist]").each(function () {
	// 		if ($(this).attr("checked")) {
	// 			$(this).attr("checked", false);
	//
	// 		} else {
	// 			$(this).attr("checked", true);
	//
	// 		}
	// 	});
	// 	GetCount();
	// });
	//取消
	$("#cancel").click(function () {
		$(".gwc_tb2 input[name=newslist]").each(function () {
			$(this).attr("checked", false);

		});
		$('.gwc_tb2 input[name=maplist]').each(function () {
			$(this).attr("checked", false);
		});
		GetCount();
	});

	// 所有复选(:checkbox)框点击事件
	$(".gwc_tb2 input[name=newslist]").click(function () {
		if ($(this).attr("checked")) {
			GetCount();
		} else {

		}
	});

	//
	$(".gwc_tb2 input[name=maplist]").click(function () {
		var name = $(this).attr("value");

		$('.gwc_tb2 input[name=maplist]').each(function () {
			var name1 = $(this).attr("value");
			if ($(this).attr("checked") && name1 != name) {
				$(this).attr("checked", false);
				$('.gwc_tb2 input[name=newslist' + name1 + ']').each(function () {
					if ($(this).attr("checked")) {
						$(this).attr("checked", false);
					}
				});
			}
		});

		$('.gwc_tb2 input[name=newslist' + name + ']').each(function () {
			if ($(this).attr("checked")) {
				$(this).attr("checked", false);
			} else {
				$(this).attr("checked", true);
			}
		});

		GetCount(name);
	});



	// 输出
	$(".gwc_tb2 input[name=newslist]").click(function () {

		GetCount();
	});
});
//******************
function GetCount() {
	var conts = 0;
	var aa = 0;
	$('.gwc_tb2 input[yangtao=newslist]').each(function () {
		if ($(this).attr("checked")) {
			for (var i = 0; i < $(this).length; i++) {
				conts += parseInt($(this).val());
				aa += 1;
			}
		}
	});
	$("#shuliang").text(aa);
	$("#zong1").html((conts).toFixed(2));
	$("#jz1").css("display", "none");
	$("#jz2").css("display", "block");
}
//ADD:对删除链接进行处理2014-9-20DeathGhost
//     $(document).ready(function(){
		// $("#delcart1").click(function(){
		// 	$("#table1").remove();
		// 	});
		//
		//
		// $("#delcart2").click(function(){
		// 	$("#table2").remove();
		// 	});
		// });
function delcart(parameters,custid) {
	$('#table' + parameters).remove();

	$.ajax({
		url: "/index/removeGwc",
		data: {foodid:parameters,custid:custid},
		type: "POST",
		dataType: "text",
		async:false,
		success: function(data) {
			location.reload();
		}
	});

}
	// $(function () {
	// 	var t = $("#text_box");
	// 	var p = $("#total1").html();
	// 	console.log(p);
	// 	$("#add2").click(function (event) {
	// 		console.log(event)
	// 		t.val(parseInt(t.val()) + 1)
	//
	// 		setTotal(); GetCount();
	// 	})
	// 	$("#min2").click(function () {
	// 		t.val(parseInt(t.val(1)) - 1)
	// 		t.val(1)//初始值防止为负数ADD deathghost
	// 		setTotal(); GetCount();
	// 	})
	// 	function setTotal() {
	// 		$("#total2").html((parseInt(t.val()) * p).toFixed(2));
	// 		$("#newslist-2").val(parseInt(t.val()) * p);
	// 	}
	// 	setTotal();
	// })
	// $(function () {
	// 	var t = $("#text_box");
	// 	var p = $("#total1").html();
	// 	console.log(p);
	// 	function asdasdasdsdad() {
	// 		console.log(event)
	//
	// 		t.val(parseInt(t.val()) + 1)
	// 		setTotal(); GetCount();
	// 	};
	// 	$("#min1").click(function () {
	// 		t.val(parseInt(t.val()) - 1)
	// 		t.val(1)//初始值防止为负数ADD deathghost
	// 		setTotal(); GetCount();
	// 	})
	// 	function setTotal() {
	//
	// 		$("#total1").html((parseInt(t.val()) * p).toFixed(2));
	// 		$("#newslist-1").val(parseInt(t.val()) * p);
	// 	}
	// 	setTotal();
	// })


function dealAdd(parameters,custid) {
	$('#text' + parameters).val(parseInt($('#text' + parameters).val()) + 1)
	setTotal(parameters); GetCount();



	var count = $('#text' + parameters).val();
	$.ajax({
		url: "/index/dealGwcAdd",
		data: {custid: custid,foodid:parameters,count:count},
		type: "POST",
		dataType: "text",
		async:false,
		success: function(data) {

		}
	});
};
function dealSub(parameters,custid) {
	$('#text' + parameters).val(parseInt($('#text' + parameters).val()) - 1)
	if (parseInt($('#text' + parameters).val()) <= 1){
		$('#text' + parameters).val(1);
	}
	setTotal(parameters); GetCount();

	var count = $('#text' + parameters).val();
	$.ajax({
		url: "/index/dealGwcAdd",
		data: {custid: custid,foodid:parameters,count:count},
		type: "POST",
		dataType: "text",
		async:false,
		success: function(data) {

		}
	});
};

function test(){
	var $eventdx=$("input.ytonload");
	for (var i = 0; i < $eventdx.length; i++){
		setTotal($eventdx[i].getAttribute('yttmp'));
	}
	GetCount();
}

window.onload=test;

$(function () {
	$(".quanxun").click(function () {
		setTotals();

	});
	function setTotals() {
		var len = $(".tot");
		var num = 0;
		for (var i = 0; i < len.length; i++) {
			num = parseInt(num) + parseInt($(len[i]).text());

		}
		$("#zong1").text(parseInt(num).toFixed(2));
		$("#shuliang").text(len.length);
	}
	setTotals();
})


function setTotal(parameters) {

	var p = $('#price' + parameters).attr("price");
	$('#price' + parameters).html((parseInt($('#text' + parameters).val()) * p).toFixed(2));
	$('#newslist' + parameters).val(parseInt($('#text' + parameters).val()) * p);
}
//add to cart shoppage
var data = {"total":0,"rows":[]};
var totalCost = 0;

$(function(){
	$('#cartcontent').datagrid({
		singleSelect:true
	});
	$('.item').draggable({
		revert:true,
		proxy:'clone',
		onStartDrag:function(){
			$(this).draggable('options').cursor = 'not-allowed';
			$(this).draggable('proxy').css('z-index',2);
		},
		onStopDrag:function(){
			$(this).draggable('options').cursor='move';
		}
	});
	$('.cart').droppable({
		onDragEnter:function(e,source){
			$(source).draggable('options').cursor='auto';
		},
		onDragLeave:function(e,source){
			$(source).draggable('options').cursor='not-allowed';
		},
		onDrop:function(e,source){
			var name = $(source).find('p:eq(0)').html();
			var price = $(source).find('p:eq(1)').html();
			var food_id = $(source).find('p:eq(2)').html();
			alert(food_id);
			addProduct(name, parseFloat(price.split('￥')[1]));
		}
	});
});


//添加至购物车
function addProduct(name,price){
	function add(){
		for(var i=0; i<data.total; i++){
			var row = data.rows[i];
			if (row.name == name){
				row.quantity += 1;
				return;
			}
		}
		data.total += 1;
		data.rows.push({
			name:name,
			quantity:1,
			price:price
		});
	}
	add();
	totalCost += price;
	$('#cartcontent').datagrid('loadData', data);
	$('div.cart .total').html('共计金额: ￥'+totalCost);
}

