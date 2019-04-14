<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="commons/header.jsp"></jsp:include>
<div class="content">
	<a href="toadd">添加</a>
	<table border="1" cellpadding="0" cellspacing="0" class="dataTable">
		<thead>
			<tr>
				<th>书名</th>
				<th>价格</th>
				<th>创建日期</th>
				<th>修改日期</th>
				<th>所属种类</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.pageBean.datas }" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price} ￥</td>
					<td>${book.createTime }</td>
					<td>${book.updateTime }</td>
					<td>${book.category }</td>
					<td>
						<a href="toedit?tag=toEdit&bookId=${book.id}">编辑</a>
						<a href="todelete?bookId=${book.id}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr class="page">
				<td colspan="6">
					<c:if test="${requestScope.pageBean.totalPage > 1 }">
						<div class="pagnation">
							<ul>
								<c:forEach items="${requestScope.pageBean.pageBar }" var="bar">
									<c:choose>
										<c:when test="${bar.active }">
											<li class="active"><a href="test?page=${bar.content}">${bar.content}</a></li>
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
				</td>
			</tr>
		</tfoot>
	</table>
</div>
<jsp:include page="commons/footer.jsp"></jsp:include>
	