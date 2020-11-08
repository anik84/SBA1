<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	-->
	
	
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Get Loan Status by Application No</h2>
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
				value="displaystatus">
			<div>
				<button>Get Status</button>
			</div>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />

</body>
</html>