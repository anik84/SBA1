<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan Application</title>
</head>
<body>
	<!-- read the application number to edit from user and send to 
	     user controller to edit info
	-->
	
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Get Loan Details To Edit</h2>
		<hr />
		<a href="userhome1.jsp">Home</a>
		<hr />
		<form action="user" method="post">
			<div>
				<label for="applno">Application Number:</label> <input
					type="text" id="applno" name="applno" required>
			</div>
			<br />
			<input id="action" name="action" type="hidden"
				value="editloan">
			<div>
				<button>Get Loan Details</button>
			</div>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />

</body>
</html>