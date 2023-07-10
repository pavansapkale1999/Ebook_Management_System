<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin:Add_Books</title>
<%@include file="allCss.jsp"%>
</head>
<body style="background-color: #f0f2f2;">
	<%@include file="navbar.jsp"%>
	<div class="container"></div>
	<div class="row">
		<div class="col-md-4 offset-md-4">
			<div class="card">
				<div class="card-body">
					<h4 class="text-center">Add Books</h4>

					<c:if test="${not empty succMsg }">
						<p class="text-center text-success">${succMsg }</p>
						<c:remove var="succMsg" scope="session" />
					</c:if>

					<c:if test="${not empty failedMsg }">
						<p class="text-center text-danger">${failedMsg }</p>
						<c:remove var="failedMsg" scope="session" />
					</c:if>

					<form action="../add_books" method="post"
						enctype="multipart/form-data">
						<div class="form-group">
							<label for="exampleInputEmail1">Book Name*</label> <input
								type="text" class="form-control" id="exampleInputEmail1"
								aria-describedby="emailHelp" name="bname">
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Author Name* </label> <input
								type="text" class="form-control" id="exampleInputEmail1"
								aria-describedby="emailHelp" name="author">
						</div>


						<div class="form-group">
							<label for="exampleInputPassword1">Price*</label> <input
								type="number" class="form-control" id="exampleInputPassword1"
								name="price">
						</div>

						<div class="form-group">
							<label for="inputState">Book Categories</label> <select
								class="form-control" id="inputState" name="categories">
								<option selected="selected">--select--</option>
								<option value="New">New Book</option>
							</select>
						</div>

						<div class="form-group">
							<label for="inputState">Book Status</label> <select
								class="form-control" id="inputState" name="status">
								<option selected="selected">--select--</option>
								<option value="Active">Active</option>
								<option value="Inactive">Inactive</option>
							</select>
						</div>

						<div class="form-group">
							<label for="exampleFormControlFile1">Upload Photo</label> <input
								type="file" class="form-control-file" name="bimg"
								id="exampleFormControlFile1">
						</div>
						<button type="submit" class="btn btn-primary">Add</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>