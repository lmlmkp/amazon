<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>购物车</title>
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<link rel="stylesheet" href="css/index.css" />
		<link rel="stylesheet" href="css/jquery-ui.min.css" />
		<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/jquery-ui.min.js"></script>
		<script type="text/javascript">
			var availableTags = ["江西理工大学", "武汉理工大学", "华中师范大学", "武汉大学", "北京大学", "Java编程思想"];
			
			$(function() {
				var books = localStorage.books; //先获取目前在本地存储的购物车信息
				var cartNum = 0; //这个用来存储我们最终在购物车上显示的商品的总数量
				if(books) {
					books = JSON.parse(books); 
					$.each(books, function(key,value) {
						cartNum += value;
					});
				}
				$("#cartNum").html(cartNum);
				
				$("#searchInp").autocomplete({
				      minLength:1,
				      source: function(request, response) {
				    	 $.ajax({
				    		 url:"cart",
				    		 data: {text:$("#searchInp").val(), tag:"1"},
				    		 dataType:"json",
				    		 type:"get",
				    		 success:function(_data){
				    			 //[{label:1, value:"java"}, {label:2, value:"html"}]
				    			 //["java", "html", "javasixiang"]
				    			 
				    			 response($.map(_data, function(item){
				    				 return {label:item};
				    			 }));
				    		 }
				    	 });
				      }
				 });
			});
			
			function pagination(page){
				/**
				 location.search的意思是，例如：
				 	http://localhost:8080/cart/book?page=1&categeor=17  --> ?page=1&category=17
				 	http://localhost:8080/cart/book?categeor=17  --> ?category=17
				 */
				var search = location.search.substring(1);
				
				/**
				 * 将search中除了 page=X排除在外，然后将其他的搜索参数提取出来，
				 * 最后一步就将  page=page
				 */
				var searchParams = search.split("&");
				var newSearch = "";
				for(var i = 0; i < searchParams.length; i++) {
					/**
					 * indexOf() 返回指定字符串的首次发生的索引位置，如果没有返回-1
					 * 如果不包含 "page"就表示为其他的参数
					 * 例如时说： searchParams 的值为 ["page=1", "category=17"]
					 */
					if(searchParams[i].indexOf("page") < 0) {
						newSearch += searchParams[i] + "&";
					}
				}
				//
				location.href="book?" + newSearch + "page=" + page;
			}
		
			function joinShoppingCart(bookId) {
				/**
				 * 在本地保存购物车信息的时候，采用：{20:1, 32:3}
				 */
				var books = localStorage.books; //先获取目前在本地存储的购物车信息
				
				var cartNum = 0; //这个用来存储我们最终在购物车上显示的商品的总数量
				
				/**
				 * 在js中 "", null, undefined,将其放在if中的时候，会转换为true还是false
				 */
				if(books) {  //表示之前在购物车中加过商品，
					//方法的作用是将一个类 json的字符串转换为 对象
					//"{1:2}" JSON.parse(books) {1:2}
					books = JSON.parse(books); 
					// {3:2, 2:1}
					var flag = false;  //假设该数在购物车中之前是不存在的。
					$.each(books, function(key,value) {
						/**
						 * 之前在购物车中数据是{2:1, 3:1}
						 */
						if(key == bookId) {
							flag = true;   //该代码执行，表示该书本在购物车之前是存在的
							books[key] = ++value;
						}
						cartNum += books[key];
					});
					
					if(!flag) {  //再添加这本书
						books[bookId] = 1;
						cartNum += 1;
					}
				
				}else{ //表示在这个商品加入购物车之前，没有商品被加入到购物车
					books = {}; //先要初始一个空对象
					books[bookId]  = 1;
					cartNum += 1;
				}
				
				/*
				 JSON.stringify(), 使用的目的是localStorage中不能存储对象，只能存储字符串
				  例如： books = {1:2}; ->  JSON.stringify(books) -> "{1:2}" 
				 */
				localStorage.books = JSON.stringify(books);
				$("#cartNum").html(cartNum);
			}
			
		</script>
	</head>
	<body>
		<!-- 双飞翼布局 | 圣杯布局 -->
		<div id="navbar">
			<div class="navbar-belt">
				<div class="nav-fill">
					<div class="nav-search">
						<form>
							<div class="search-box">
								<input type="text" id="searchInp" value="${param.search }" class="search-inp" name="search" />
							</div>		
							<div class="submit-box">
								<input class="sub-inp" type="submit" />
								<span class="search-icon">
									<i class="fa fa-search"></i>
								</span>
							</div>
						</form>
					</div>
				</div>
				<div class="nav-left">
					<h1></h1>
				</div>
				<div class="nav-right">
					<img src="img/activity.jpg" />
				</div>
				<div class="clear"></div>
			</div>
			<div class="navbar-main">
				<div class="all-goods">
					全部商品 <i class="trangle"></i>
					<div class="book-menu">
						<ul>
							<c:forEach items="${requestScope.categoryList }" var="c">
								<li><a href="book?category=${c.id}">${c.name}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="my-info">
					<a href="#">我的账户</a>
					<i class="trangle"></i>
					<a class="my-cart" href="shopping-cart.jsp">
						<i class="my-cart-icon">
						</i>
						<span id="cartNum" class="goods-num">0</span>
						购物车
					</a>
				</div>
			</div>
		</div>
		<div class="content">
			<div>
				<ul class="book-items">
					<c:forEach items="${requestScope.pageBean.datas }" var="book">
						<li>
						<a href="javascript:;">
							<img height="200" width="200" src="${book.cover }"/>
							<h4>${book.name }</h4>
							<h5>￥${book.price }</h5>
							<button onclick="joinShoppingCart(${book.id})">加入购物车</button>
						</a>
					</li>
					</c:forEach>
				</ul>
				<div class="clear">
					
				</div>
			</div>
			<c:if test="${requestScope.pageBean.totalPage > 1 }">
				<div class="pagnation">
					<ul>
						<c:forEach items="${requestScope.pageBean.pageBar }" var="bar">
							<c:choose>
								<c:when test="${bar.active }">
									<li class="active"><a onclick="pagination(${bar.content})" href="javascript:;">${bar.content}</a></li>
								</c:when>
								<c:otherwise>
									<li><a>${bar.content}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
					<div class="clear"></div>
				</div>
			</c:if>
		</div>
	</body>
</html>