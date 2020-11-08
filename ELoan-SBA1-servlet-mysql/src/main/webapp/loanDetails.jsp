<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- write the code to display the loan status information 
	     received from usercontrollers' displaystatus method
	-->

	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Display Loan Status</h2>
		<hr />
		<a href="userhome1.jsp">Home</a>
		<hr />
		<form>
			<div>
				<label for="applno">Application Number:</label> <input type="text"
					id="applno" name="applno" value=${applno } readonly>
			</div>
			<div>
				<label for="status">Application Status:</label> <input type="text"
					id="status" name="status" value=${status } readonly>
			</div>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />

</body>
</html>