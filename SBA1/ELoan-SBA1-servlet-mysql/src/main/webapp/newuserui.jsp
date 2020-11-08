<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- read user name and password from user to create account
	     and send to usercontrollers registeruser method
	-->

	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan New User Registration</h2>
		<hr />
		<a href="index.jsp">Home</a>
		<hr />
		<form action="user" method="post">
			<div>

				<div>
					<label for="loginid">Enter login Id:</label>
				</div>
				<div>
					<input type="text" id="loginid" name="loginid" required>
				</div>
			</div>
			<br />
			<div>
				<div>
					<label for="password">Enter password:</label>
				</div>
				<div>
					<input type="text" id="password" name="password" required>
				</div>
			</div>
			<br /> <input id="action" name="action" type="hidden"
				value="registernewuser">
			<div>
				<div>
					<button>Register</button>
				</div>
			</div>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />

</body>
</html>