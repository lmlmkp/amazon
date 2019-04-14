<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="commons/header.jsp"></jsp:include>
<div class="content">
	<a href="toaddc">添加</a>
	<table border="1" cellpadding="0" cellspacing="0" class="dataTable">
		<thead>
			<tr>
				<th>种类</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.category.datas }" var="category">
				<tr>
					<td>${book.name}</td>
					<td>
						<a href="toeditc?tag=toEdit&bookId=${book.id}">编辑</a>
						<a href="todeletec?bookId=${book.id}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<jsp:include page="commons/footer.jsp"></jsp:include>
	