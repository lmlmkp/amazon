<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.st.cart.domain.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
		http://localhost:8080/cart/success.jsp?username=zhangsan&intrests=movie&intrests=read
	--%>
	<%-- request.getParameter("username"); --%>
	${param.username} <br>
	${paramValues.intrests[2] } <br>
	
	<%
		/**
		Cat cat = new Cat("Tom");
		Dog dog = new Dog("wangcai", cat);
		User user = new User(11, "zhangsan", dog);
	
		request.setAttribute("aa", "XXX");
		application.setAttribute("aa", "ZZZZ");
		request.setAttribute("user", user);
		*/
	%>
	
	<hr>
	
	3 + 4: ${3 + 4} <br>
	3 * 4: ${3 * 4 }<br>
	3 / 4: ${3 / 4 }<br>
	3 % 4: ${3 % 4 }<br>
	
	<hr>
	<%--
		gt: greater than
		lt: less than
		ge: greater or equals
		le: less or equals
		ne: not equals
	 --%>
	3 > 4: ${3 > 4}<br>
	3 > 4: ${3 gt 4 }<br>
	3 < 4: ${3 < 4 }<br>
	3 < 4: ${3 lt 4 } <br>
	5 >= 6: ${5 ge 5 }<br>
	6 <= 5: ${5 le 6 }<br>
	<%-- 
		6 != 5: ${5 ne 6 }<br>
	--%>
	<hr>
	
	value: ${3 gt 2 ? "hello" : "world" }<br>
	
	<hr>
	<%-- request.getAttribute("aa"); --%>
	request: ${requestScope.aa } <br>
	<%-- session.getAttribute("aa"); --%>
	session: ${sessionScope.aa } <br>
	<%-- application.getAttribute("aa"); --%>
	application: ${applicationScope.aa } <br>
	
	<hr>
	<%-- empty 只能判断null或者"" --%>
	${empty requestScope.aa ? "abc": "xyz" }<br>
	
	<hr>
	<%-- 会按照从 requst --> session --> application范围逐渐取找 --%>
	${aa} <br>
	<hr>
	取值1：${user.name } <br>
	取值2：${user.id } <br>
	取值3：${user.friend.name } <br>
	取值3：${user.friend.enemy.name } <br>
	
</body> 
</html>