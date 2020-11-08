<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Application Form</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body onload="myFunction()">

	<!--
	write the html code to accept laon info from user and send to placeloan servlet
-->

	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan New Loan Application</h2>
		<hr />
		<a href="userhome1.jsp">Home</a>
		<hr />
		<c:if test="${msg!=null }">
			<p>
				<strong>${msg }</strong>
			</p>
		</c:if>
		<form action="user" method="post">
			<c:if test="${populate=='PlaceLoan' }">
				<div>
					<label for="applno">Application Number: </label> <input type="text"
						value=${applno } disabled> <input id="applno"
						name="applno" value=${applno } type="hidden">
				</div>
				<br />
				<div>
					<label for="purpose">Loan Name (Description):</label> <input
						type="text" id="purpose" name="purpose" required>
				</div>
				<br />
				<div>
					<label for="amtrequest">Loan Amount Requested:</label> <input
						type="number" id="amtrequest" name="amtrequest" required>
				</div>
				<br />
				<div>
					<label for="doa">Date of Application: </label> <input type="text"
						value=${doa } disabled> <input type="hidden" id="doa"
						name="doa" value=${doa }>
				</div>
				<br />
				<div>
					<label for="bstructure">Business Structure:</label> <select
						name="bstructure" id="bstructure">
						<option value="Individual">Individual</option>
						<option value="Organisation">Organisation</option>
					</select>

				</div>
				<br />
				<div>
					<label for="bindicator">Billing Indicator:</label> <select
						name="bindicator" id="bindicator">
						<option value="salaried">Salaried</option>
						<option value="nonsalaried">Non Salaried</option>
					</select>
				</div>
				<br />
				<div>
					<label for="tindicator">Tax Indicator:</label> <select
						name="tindicator" id="tindicator">
						<option value="taxPayer">Tax Payer</option>
						<option value="nonTaxPayer">Non Tax Payer</option>
					</select>
				</div>
				<br />
				<div>
					<label for="address">Contact Address:</label> <input type="text"
						id="address" name="address" required>
				</div>
				<br />
				<div>
					<label for="email">Contact Email:</label> <input type="email"
						id="email" name="email" required>
				</div>
				<br />
				<div>
					<label for="mobile">Contact Mobile No:</label> <input type="text"
						id="mobile" name="mobile" pattern="[1-9][0-9]{9}" required>
				</div>
				<br />
				<div>
					<label for="status">Loan Status: </label> <input type="text"
						value=${status } disabled> <input type="hidden"
						id="status" name="status" value=${status }>
				</div>
				<br />
				<input id="action" name="action" type="hidden" value="placeloan">
				<div>
					<button>Apply Loan</button>
				</div>
			</c:if>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />


</body>
</html>