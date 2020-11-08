<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>user home</title>
</head>
<body>
	<!-- write the html code to display hyperlinks for user functionalities -->
	<div align=center>
		<jsp:include page="header.jsp" />
		<h2>eLoan User Dash Board</h2>

		<hr />
		<nav>
			<a href="userhome1.jsp">Home</a> <span>|</span> 
			<a href="user?action=application1">Apply Loan</a> <span>|</span> <a
			href="trackloan.jsp">Track Loan</a> <span>|</span> <a
			href="editloan.jsp">Edit Loan</a> <span>|</span>
			<a href="index.jsp">Logout</a>
		</nav>
		<hr />
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>