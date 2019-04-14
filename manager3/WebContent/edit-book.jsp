<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="commons/header.jsp"></jsp:include>
<div class="content">
	<h2>编辑书籍</h2>
	<form action="add1" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${requestScope.book.id }">
		<input type="hidden" name="tag" value="edit">
		<table>
			<tbody>
				<tr>
					<td>书名:</td>
					<td>
						<input type="text" name="name" value="${requestScope.book.name }">
					</td>
				</tr>
				<tr>
					<td>价格:</td>
					<td>
						<input type="text" name="price" value="${requestScope.book.price }">
					</td>
				</tr>
				<tr>
					<td>封面:</td>
					<td>
						<input type="file" name="cover" /> <a href="http://127.0.0.1/${requestScope.book.cover}" target="_blank">预览</a>
					</td> 
				</tr>
				<tr>
					<td>种类:</td>
					<td>
						<select name="categoryId">
							<c:forEach items="${requestScope.list }" var="c">
								<option ${c.id == requestScope.book.categoryId ? 'selected':''} value="${c.id}">${c.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button onclick="javascript:history.go(-1);">返回</button>
						<input type="submit">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<jsp:include page="commons/footer.jsp"></jsp:include>