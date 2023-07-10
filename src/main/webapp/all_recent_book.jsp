<%@page import="java.util.List"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.entity.BookDetails"%>
<%@page import="com.dao.BookDaoImpl"%>
<%@page import="com.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Recent Book</title>
<%@include file="allComponent/allCss.jsp"%>
<style type="text/css">
.crd-ho:hover {
	background-color: #fcf7f7;
}
</style>
</head>
<body>
	<%
	User user = (User) session.getAttribute("userobj");
	%>
	<%@include file="allComponent/navbar.jsp"%>

	<div class="container-fluid">
		<div class="row p-3">
			<%
			BookDaoImpl dao = new BookDaoImpl(DBConnect.getConn());
			List<BookDetails> list = dao.getAllRecentBook();
			for (BookDetails b : list) {
			%>
			<div class="col-md-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhotoName()%>"
							style="width: 100px; height: 150px" class="img-thumblin">
						<p><%=b.getBookName()%></p>
						<p><%=b.getAuthor()%></p>
						<p>
							Categories:<%=b.getBookCategory()%></p>
						<div class="row">
							<%
							if (b.getBookCategory().equals("Old")) {
							%>
							<a href="cart?bid=<%=b.getBookId()%>&&uid=<%= (user != null) ? user.getId() : "" %>"
								class="btn btn-danger btn-sm ml-4"><i
								class="fas fa-cart-plus"></i> Add Cart</a> <a
								href="view_books.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml-1"><i class="fas fa-eye"></i>
								View</a> <a href="" class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%>
								<i class="fas fa-rupee-sign"></i></a>
							<%
							} else {
							%>
							<div class="row">
								<%
								if (user != null) {
								%>
								<a href="cart?bid=<%=b.getBookId()%>&&uid=<%=user.getId()%>"
									class="btn btn-danger btn-sm ml-4"><i
									class="fas fa-cart-plus"></i> Add Cart</a>
								<%
								} else {
								%>
								<a href="login.jsp" class="btn btn-danger btn-sm ml-4"><i
									class="fas fa-cart-plus"></i> Add Cart</a>
								<%
								}
								%>
								<a href="view_books.jsp?bid=<%=b.getBookId()%>"
									class="btn btn-success btn-sm ml-1"><i class="fas fa-eye"></i>
									View</a> <a href="" class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%>
									<i class="fas fa-rupee-sign"></i></a>
							</div>
							<%
							}
							%>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<%@include file="allComponent/footer.jsp"%>
</body>
</html>
