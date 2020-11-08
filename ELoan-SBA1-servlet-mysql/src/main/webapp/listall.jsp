<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body>
	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	-->
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan List All Loan Applications</h2>
		<hr />
		<a href="adminhome1.jsp">Home</a>
		<hr />

		<table>
			<thead>
				<tr>
					<th>Application No </th>
					<th>Loan Name (Description) </th>
					<th>Amount Requested </th>
					<th>Loan Application Date </th>
					<th>Business Structure </th>
					<th>Billing Indicator </th>
					<th>Tax Indicator </th>
					<th>Contact Address </th>
					<th>Contact Mobile </th>
					<th>Contact Email </th>
					<th>Application Status</th>
			</thead>
			<tbody>
				<c:forEach var="c" items="${loanInfos }">
					<tr>
						<td>${c.applno }</td>
						<td>${c.purpose }</td>
						<td>${c.amtrequest }</td>
						<td>${c.doa }</td>
						<td>${c.bstructure }</td>
						<td>${c.bindicator }</td>
						<td>${c.tindicator }</td>
						<td>${c.address }</td>
						<td>${c.email }</td>
						<td>${c.mobile }</td>
						<td>${c.status }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<hr />
	<jsp:include page="footer.jsp" />

</body>
</html>