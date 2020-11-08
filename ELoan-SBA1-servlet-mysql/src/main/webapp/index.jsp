<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan system</title>
</head>
<body>
	<!-- write the html code to read user credentials and send it to validateservlet
	    to validate and user servlet's registernewuser method if create new user
	    account is selected
	-->

	<jsp:include page="header.jsp" />
	<!-- <nav>
		<a href="index.jsp">Home</a> 
		<span>|</span>
	</nav>   -->
	
	<hr />
	<div align=center>
		<h2>eLoan User Login</h2>
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
			<br />
			<input id="action" name = "action" type="hidden" value ="validate">
			<div>
				<div>
					<button>Sign In</button>
				</div>
			</div>
		</form>
		<a href="newuserui.jsp">New User Registration</a>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>