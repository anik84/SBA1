<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>admin home</title>
</head>
<body>
<!-- 
<jsp:include page="header.jsp"/>
<div align="right"><a href="index.jsp">Logout</a></div>
<h4>Admin Dash Board</h4>
<a href="admin?action=listall">List All</a><br>
<a href="admin?action=process">Process Loan</a><br>
<jsp:include page="footer.jsp"/>
 -->
 
 <div align=center>
		<jsp:include page="header.jsp" />
		<h2>eLoan Admin Dash Board</h2>

		<hr />
		<nav>
			<a href="adminhome1.jsp">Home</a> <span>|</span> 
			<a href="admin?action=listall">List All</a> <span>|</span> <a
			href="process.jsp">Process Loan</a> <span>|</span> 
			<a href="index.jsp">Logout</a>
		</nav>
		<hr />
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>