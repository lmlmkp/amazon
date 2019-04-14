<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.st.cart.domain.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%--
			prefix: 前缀
			suffix: 后缀
		 --%>
		 <%
		 	/**
		 	User u1 = new User(1, "zhangsan");
			User u2 = new User(2, "lisi");
			User u3 = new User(3, "wangwu");
			User u4 = new User(4, "tianqi");
			
			List<User> list = Arrays.asList(u1, u2, u3, u4);
		 	*/
			request.setAttribute("age", 22);
		 	//request.setAttribute("list", list);
		 %>
		<c:if test="${requestScope.age > 20 }">
			青年。
		</c:if>
		
		<hr>
		
		<c:choose>
			<c:when test="${requestScope.age > 40 }">
				中年。
			</c:when>
			<c:when test="${requestScope.age > 30 }">
				青年。
			</c:when>
			<c:otherwise>
				少年
			</c:otherwise>
		</c:choose>
		
		<hr>
		<%--
			step: 步进。
			for(int i = 0;i <= 10;i+=2) {}
		 --%>
		<c:forEach begin="1" end="10" var="i" step="2">
			${i} <br>
		</c:forEach>
		
		<hr>
		
		<%--
			for(User usr : list) {
				System.out.println(usr.getId() + "---" + u.getName());
			}
		
		 --%>
		<%--
			varStatus: index 索引
					   count  表示第几条数据
					   last 是否为最后一条数据
					   first 是否为第一条数据
		 --%>
		<c:forEach items="${requestScope.list}" var="usr" varStatus="s">
			${usr.id } -- ${usr.name } -- ${s.index }--${s.count }--${s.last}--${s.first } <br>
		</c:forEach>
	</body>
</html>