<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
	<script>
		$(document).ready(function() {
			$('#status').on('change', function() {
				if(this.value == "rejected"){
					$('#action').val("updatestatus");
					$('#amotsanctioned').prop('required', false);
					$('#loanterm').prop('required', false);
					$('#psd').prop('required', false);
					$('.dynamic').hide();
				}else{
					$('#action').val("calemi");
					$('#amotsanctioned').prop('required', true);
					$('#loanterm').prop('required', true);
					$('#psd').prop('required', true);
					$('.dynamic').show();
				}
			})
		})
		
	</script>
	<!--
     Read the values from the admin servlet and cal emi and other details and send to
     to the same admin servlet to update the values in the database 
  -->

	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Edit Loan Application</h2>
		<hr />
		<a href="adminhome1.jsp">Home</a>
		<hr />
		<c:if test="${msg!=null }">
			<p>
				<strong>${msg }</strong>
			</p>
		</c:if>
		<form action="admin" method="post">
			<c:if test="${populate=='Process' }">
			<div>
				<label for="applno">Application Number: </label> <input type="text"
					value=${applno } disabled> <input type="hidden" id="applno"
					name="applno" value=${applno }>
			</div>
			<br />
			
				<div>
					<label for="purpose">Loan Name (Description): </label> <input
						type="text" id="purpose" name="purpose" value=${purpose } disabled>
				</div>
				<br />
				<div>
					<label for="amtrequest">Loan Amount Requested: </label> <input
						type="number" id="amtrequest" name="amtrequest"
						value=${amtrequest } disabled>
				</div>
				<br />
				<div>
					<label for="doa">Date of Application: </label> <input type="text"
						id="doa" name="doa" value=${doa } disabled>
				</div>
				<br />
				<div>
					<label for="bstructure">Business Structure:</label> <select
						name="bstructure" id="bstructure" value=${bstructure } disabled>
						<option value="Individual">Individual</option>
						<option value="Organisation">Organisation</option>
					</select>

				</div>
				<br />
				<div>
					<label for="bindicator">Billing Indicator:</label> <select
						name="bindicator" id="bindicator" value=${bindicator } disabled>
						<option value="aalaried">Salaried</option>
						<option value="nonsalaried">Non Salaried</option>
					</select>
				</div>
				<br />
				<div>
					<label for="tindicator">Tax Indicator:</label> <select
						name="tindicator" id="tindicator" value=${tindicator } disabled>
						<option value="taxPayer">Tax Payer</option>
						<option value="nonTaxPayer">Non Tax Payer</option>
					</select>
				</div>
				<br />
				<div>
					<label for="address">Contact Address:</label> <input type="text"
						id="address" name="address" value=${address } disabled>
				</div>
				<br />
				<div>
					<label for="email">Contact Email:</label> <input type="text"
						id="email" name="email" value=${email } disabled>
				</div>
				<br />
				<div>
					<label for="mobile">Contact Mobile No: </label> <input type="text"
						id="mobile" name="mobile" value=${mobile } disabled>
				</div>
				<br />

				<div class="dynamic">
					<label for="amotsanctioned">Loan Amount Sanctioned: </label><input
						type="number" id="amotsanctioned" name="amotsanctioned" required>
				</div>
				<br />
				<div class="dynamic">
					<label for="loanterm">Term of loan ( Duration in Months): </label><input
						type="text" id="loanterm" name="loanterm" required>
				</div>
				<br />
				<div class="dynamic">
					<label for="psd">Payment start Date: </label><input type="date"
						id="psd" name="psd" required>
				</div>
				<br />
				<div>
					<label for="status">Loan Status:</label> <select name="status"
						id="status" value=${status } >
						<option value="approved">Approved</option>
						<option value="rejected">Rejected</option>
					</select>
				</div>
				<br />
				<input id="action" name="action" type="hidden" value="calemi">
				<div>
					<button>Save</button>
				</div>

			</c:if>
			<c:if test="${populate=='Approved' }">
				<div>
					<label for="amotsanctioned">Loan Amount Sanctioned: </label><input
						type="number" id="amotsanctioned" name="amotsanctioned"
						value=${amotsanctioned } disabled>
				</div>
				<br />
				<div>
					<label for="loanterm">Term of loan ( Duration in Month): </label><input
						type="text" id="loanterm" name="loanterm" value=${loanterm }
						disabled>
				</div>
				<br />
				<div>
					<label for="psd">Payment start Date: </label><input type="date"
						id="psd" name="psd" value=${psd } disabled>
				</div>
				<br />
				<div>
					<label for="lcd">Loan closure Date: </label><input type="text"
						value=${lcd } disabled> <input type="hidden" id="lcd"
						name="lcd" value=${lcd } disabled>
				</div>
				<br />
				<div>
					<label for="emi">Monthly payment: </label><input type="text"
						value=${emi } disabled> <input type="hidden" id="emi"
						name="emi" value=${emi } disabled>
				</div>
				<br />
			</c:if>


		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />

</body>
</html>