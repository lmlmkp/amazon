<%@ page language="java" contentType="text/html; charset=UTF-8"  
	import="com.st.cart.domain.*, java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		User u1 = new User();
		u1.setId(1);
		u1.setName("zhangsan");
		
		User u2 = new User();
		u2.setId(2);
		u2.setName("lisi");
		
		User u3 = new User();
		u3.setId(3);
		u3.setName("wangwu");
		
		List<User> list = Arrays.asList(u1, u2, u3);
		for(User u : list) {
	%>
		<%=u.getId() %> --- <%=u.getName() %> <br>
	
	<%} %>
	
</body>
</html>