package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		//System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "listall" : 
				viewName = listall(request, response);
				break;
			case "process":
				viewName=process(request,response);
				break;
			case "calemi":
				viewName=calemi(request,response);
				break;
			case "updatestatus":
				viewName=updatestatus(request,response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code for updatestatus of loan and return to admin home page */
		LoanInfo loanInfo = new LoanInfo();
		
		loanInfo.setApplno(request.getParameter("applno"));
		loanInfo.setStatus(request.getParameter("status"));
		
		try {
			connDao.updateLoanStatus(loanInfo);
			request.setAttribute("msg", "Loan Application Rejected! Application No: " + request.getParameter("applno"));
		} catch (SQLException e) {
			throw e;
		}
	
		return "calemi.jsp";
		
	}
	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* write the code to calculate emi for given applno and display the details */
		
		
		//Term payment amount = (Sanctioned loan amount ) * (1 + interest rate/100) ^ (term of loan)
		//Monthly payment = (Term payment amount ) / (Term of loan)
		
		ApprovedLoan aprovedLoan = new ApprovedLoan();
		LoanInfo loanInfo = new LoanInfo();
		
		loanInfo.setApplno(request.getParameter("applno"));
		loanInfo.setStatus(request.getParameter("status"));
		
		aprovedLoan.setApplno(request.getParameter("applno"));
		aprovedLoan.setAmotsanctioned(Integer.parseInt(request.getParameter("amotsanctioned")));
		aprovedLoan.setLoanterm(Integer.parseInt(request.getParameter("loanterm")));
		aprovedLoan.setPsd(request.getParameter("psd"));
		
		aprovedLoan.setLcd((LocalDate.parse(aprovedLoan.getPsd())).plusMonths(aprovedLoan.getLoanterm()).toString());
		System.out.println(aprovedLoan.getLcd());		
		int tpa = ((int)(aprovedLoan.getAmotsanctioned()) * (1 + 9/100) ^ (int)(aprovedLoan.getLoanterm()))/(int)(aprovedLoan.getLoanterm());
		aprovedLoan.setEmi(tpa);
		System.out.println(aprovedLoan.getEmi());
				
		try {
			connDao.insertApprovedLoan(aprovedLoan);
			connDao.updateLoanStatus(loanInfo);
			request.setAttribute("msg", "Loan Approved sucessfully! Application No: " + request.getParameter("applno"));
			request.setAttribute("applno", aprovedLoan.getApplno());
			request.setAttribute("amotsanctioned", aprovedLoan.getAmotsanctioned());
			request.setAttribute("loanterm", aprovedLoan.getLoanterm());
			request.setAttribute("psd", aprovedLoan.getPsd());
			request.setAttribute("lcd", aprovedLoan.getLcd());
			request.setAttribute("emi", aprovedLoan.getEmi());
			request.setAttribute("populate", "Approved");
		} catch (SQLException e) {
			throw e;
		}
	
		return "calemi.jsp";
		
	}
	private String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	/* return to process page */
		
		LoanInfo loanInfo = new LoanInfo();
		
		loanInfo.setApplno(request.getParameter("applno"));
		
		try {
			LoanInfo dbLoanInfo = connDao.getLoanInfoByID(loanInfo);
			//not working
			if (dbLoanInfo != null) {
				
				request.setAttribute("applno", dbLoanInfo.getApplno());
				request.setAttribute("purpose", dbLoanInfo.getPurpose());
				request.setAttribute("amtrequest", dbLoanInfo.getAmtrequest());
				request.setAttribute("doa", dbLoanInfo.getDoa());
				request.setAttribute("bstructure", dbLoanInfo.getBstructure());
				request.setAttribute("bindicator", dbLoanInfo.getBindicator());
				request.setAttribute("tindicator", dbLoanInfo.getTindicator());
				request.setAttribute("address", dbLoanInfo.getAddress());
				request.setAttribute("email", dbLoanInfo.getEmail());
				request.setAttribute("mobile", dbLoanInfo.getMobile());
				
				request.setAttribute("populate", "Process");
				
				return "calemi.jsp";	
			}
		
		} catch (SQLException e) {
			throw e;
		}
	
		throw new Exception("Invalid Loan Application Id.");
		
	}
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write code to return index page */
		return null;
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
	/* write the code to display all the loans */
		List<LoanInfo> loanInfos = connDao.getAllLoan();
		request.setAttribute("loanInfos", loanInfos);
		return "listall.jsp";
		
	}

	
}