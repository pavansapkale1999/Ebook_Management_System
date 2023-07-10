<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>
<%@include file="allComponent/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
	<%@include file="allComponent/navbar.jsp"%>

	<div class="container">
		<div class="row p-3">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h5 class="text-center text-primary p-1">Edit Profile</h5>
						
						<c:if test="${not empty succMsg }">
							<h5 class="text-center text-success">${succMsg}</h5>
							<c:remove var="succMsg" scope="session" />
						</c:if>

						<c:if test="${not empty failedMsg }">
							<h5 class="text-center text-danger">${failedMsg}</h5>
							<c:remove var="failedMsg" scope="session" />
						</c:if>

						<form action="update_profile" method="post">

							<input type="hidden" value="${userobj.id }" name="id">
							<div class="form-group">
								<label for="exampleInputEmail1">Enter Full Name</label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" placeholder="Enter name"
									required="required" name="name" <%-- value="${userobj.name}" --%>>
							</div>

							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <input
									type="email" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" placeholder="Enter email"
									required="required" name="email" <%-- value="${userobj.email}" --%>>
							</div>


							<div class="form-group">
								<label for="exampleInputPassword1">Phone No</label> <input
									type="number" class="form-control" id="exampleInputPassword1"
									placeholder="Enter Phone No" required="required" name="phoneno" <%-- value="${userobj.phoneno}" --%>>
							</div>

							<div class="form-group">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="exampleInputPassword1"
									placeholder="Password" required="required" name="password" <%-- value="${userobj.password}" --%>>
							</div>

							<div class="text-center p-2">
								<button type="submit" class="btn btn-primary btn-block btn-sm">Update</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>