<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- write the code to read application number, and send it to admincontrollers
	     callemi method to calculate the emi and other details also provide links
	     to logout and admin home page
	-->
	
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Get Loan Details To Process</h2>
		<hr />
		<a href="adminhome1.jsp">Home</a>
		<hr />
		<form action="admin" method="post">
			<div>
				<label for="applno">Application Number:</label> <input
					type="text" id="applno" name="applno" required>
			</div>
			<br />
			<input id="action" name="action" type="hidden"
				value="process">
			<div>
				<button>Get Loan Application</button>
			</div>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />

</body>
</html>