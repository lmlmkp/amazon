<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="css/cart.css" />
		<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript">
			
			function formatFloat(value, digital) {
				var m = Math.pow(10, digital);
				return parseInt(m * value, 10) / m;
			}
		
			$(function(){
				var goodsList = $("#goodsList");
				
				var books = localStorage.books; //先获取目前在本地存储的购物车信息
				var cartNum = 0; //这个用来存储我们最终在购物车上显示的商品的总数量
				
				var bookIds = []; //用于存储所有的书本的id
				
				if(books) {
					books = JSON.parse(books); 
					$.each(books, function(key,value) {
						cartNum += value;
						bookIds.push(key);
					});
				}
				$("#cartNum").html(cartNum);
				
				/**
				  var arr = [3, 4, 5, 8];
				  arr.join(",") -> 3,4,5,8
				*/
				var total = 0;
				//表示购物车中有数据
				if(bookIds.length > 0) {
					$.ajax({
						url: "cart",
						dataType: "json",
						type:"get", 
						data: {ids: bookIds.join(","), tag:"2"},
						success: function(_data){
							for(var i = 0; i < _data.length; i++) {
								total += _data[i].price * books[_data[i].id];
								total = formatFloat(total, 2);
								var goodsHtml  = "<li>" + 	
													"<div class='left'>" + 
													     "<img src='" + _data[i].cover + "' height='200' width='200' />" + 
													"</div>" + 
													"<div class='right'>" +
														"<h4>" + _data[i].name + "</h4>" +
														"<h5>￥" + _data[i].price + "</h5>" + 
														"<a href='javascript:;'>" + 
															"<i class='fa fa-times'></i>删除" + 
														"</a>" +
														"<div class='process'>" + 
															"<button class='proc-btn'>-</button>" + 
															"<input id='numInp" + _data[i].id  + "' class='num-inp' type='text' value='" + books[_data[i].id] + "'/>" + 
															"<button onclick='plusNum(" + _data[i].id + ", " + _data[i].price + ")' class='proc-btn'>+</button>" + 
														"</div>" + 
													 "</div>" + 
												 "</li>";
								goodsList.append(goodsHtml);					
							}
							$("#account").html(total);
						}
					});
				}
			});
			function plusNum(goodsId, goodsPrice) {
				var books = localStorage.books; 
				books = JSON.parse(books);   //将json字符串转换为js的对象
				var num = books[goodsId]; //取以前的数量
				$("#numInp" + goodsId).val(num + 1);  //将对应数量文本框的+1
				
				var totalNum = 0; //总的数量
				
				$.each(books, function(key, value) {
					if(goodsId == key) {
						books[key] = ++value;
					}
					totalNum += books[key];  //总数量
 				});
				
				var account = $("#account").html(); //或取到没加之前的总价格
				account = formatFloat(parseFloat(account) + goodsPrice, 2);
				$("#account").html(account); 
				
				$("#cartNum").html(totalNum);
				
				localStorage.books = JSON.stringify(books);
			}
		</script>
	</head>
<body>
	<div class="navbar-main">
		<div class="my-info">
			<a href="#">我的账户</a>
			<i class="trangle"></i>
			<a class="my-cart" href="#">
				<i class="my-cart-icon">
				</i>
				<span id="cartNum" class="goods-num">0</span>
				购物车
			</a>
		</div>
	</div>
	<div class="content">
		<div class="cart-list">
			<ul id="goodsList">
			</ul>
		</div>
		<div class="balance">
			<p>小计：<span id="account">0</span>￥</p>
			<button>去结算</button>
			<a href="book">去购物</a>
		</div>
	</div>
</body>
</html>