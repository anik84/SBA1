package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;
import com.mysql.cj.xdevapi.Statement;




@WebServlet("/user")
public class UserController extends HttpServlet {
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
		String action = request.getParameter("action");
		//System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "validate":
				viewName=validate(request,response);
				break;
			case "placeloan":
				viewName=placeloan(request,response);
				break;
			case "application1":
				viewName=application1(request,response);
				break;
			case "editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "registeruser":
				viewName=registerUser(request,response);
				break;
			case "register":
				viewName = register(request, response);
				break;
			case "application":
				viewName = application(request, response);
				break;
			case "trackloan":
				viewName = trackloan(request, response);
				break;
			case "editloan":
				viewName = editloan(request, response);
				break;	
			case  "displaystatus" :
				viewName=displaystatus(request,response);
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
	
	
	
	private String validate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/* write the code to validate the user */
		
		User user = new User();
		
		user.setUsername(request.getParameter("loginid"));
		user.setPassword(request.getParameter("password"));
		try {
			User dbUser = connDao.getUserByName(user);
			if (dbUser != null && !dbUser.getUsername().equals("admin")) { 
				return "userhome1.jsp";	
			} else if(dbUser != null && dbUser.getUsername().equals("admin")) { 
				return "adminhome1.jsp";
			}
		
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw e;
		}
	
		throw new Exception("Invalid User");
	}
	
	private String placeloan(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* write the code to place the loan information */
		
		LoanInfo loanInfo = new LoanInfo();
		
		loanInfo.setApplno(request.getParameter("applno"));
		loanInfo.setPurpose(request.getParameter("purpose"));
		loanInfo.setAmtrequest(Integer.parseInt(request.getParameter("amtrequest")));
		loanInfo.setDoa(request.getParameter("doa"));
		loanInfo.setBstructure(request.getParameter("bstructure"));
		loanInfo.setBindicator(request.getParameter("bindicator"));
		loanInfo.setTindicator(request.getParameter("tindicator"));
		loanInfo.setAddress(request.getParameter("address"));
		loanInfo.setEmail(request.getParameter("email"));
		loanInfo.setMobile(request.getParameter("mobile"));
		loanInfo.setStatus(request.getParameter("status"));
		
		try {
			connDao.insertLoanInfo(loanInfo);
			request.setAttribute("msg", "Loan applied Sucessfully! Application No: " + loanInfo.getApplno());
			
		} catch (SQLException e) {
			throw e;
		}
	
		return "application.jsp";
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to display the loan application page */
		String applno = "APPL-" + (System.nanoTime() & 0xfffffff);  
		request.setAttribute("applno", applno);
		request.setAttribute("doa", LocalDate.now());
		request.setAttribute("status", "New");
		request.setAttribute("populate", "PlaceLoan");
		return "application.jsp";
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
		
		LoanInfo loanInfo = new LoanInfo();
		
		loanInfo.setApplno(request.getParameter("applno"));
		loanInfo.setPurpose(request.getParameter("purpose"));
		loanInfo.setAmtrequest(Integer.parseInt(request.getParameter("amtrequest")));
		loanInfo.setDoa(request.getParameter("doa"));
		loanInfo.setBstructure(request.getParameter("bstructure"));
		loanInfo.setBindicator(request.getParameter("bindicator"));
		loanInfo.setTindicator(request.getParameter("tindicator"));
		loanInfo.setAddress(request.getParameter("address"));
		loanInfo.setEmail(request.getParameter("email"));
		loanInfo.setMobile(request.getParameter("mobile"));
		loanInfo.setStatus(request.getParameter("status"));
		
		try {
			connDao.updateLoanInfo(loanInfo);
			request.setAttribute("msg", "Loan Edited sucessfully! Application No: " + loanInfo.getApplno());
		} catch (SQLException e) {
			throw e;
		}
	
		return "editloanui.jsp";
		
		
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		
		return "newuserui.jsp";
	}
	
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to create the new user account read from user 
		   and return to index page */
		User user = new User();
		
		user.setUsername(request.getParameter("loginid"));
		user.setPassword(request.getParameter("password"));
		try {
			connDao.addUser(user);
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw e;
		}
	
		return "index.jsp";
		
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */
		
		return null;
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		
		LoanInfo loanInfo = new LoanInfo();
		
		loanInfo.setApplno(request.getParameter("applno"));
		try {
			LoanInfo dbLoanInfo = connDao.getStatusById(loanInfo);
			//not working
			if (dbLoanInfo != null) {
				request.setAttribute("applno", dbLoanInfo.getApplno());
				request.setAttribute("status", dbLoanInfo.getStatus());
				return "loanDetails.jsp";	
			}
		
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw e;
		}
	
		throw new Exception("Invalid Loan Application Id.");

	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	/* write a code to return to editloan page */
		
		LoanInfo loanInfo = new LoanInfo();
		
		loanInfo.setApplno(request.getParameter("applno"));
		try {
			LoanInfo dbLoanInfo = connDao.getLoanInfoByID(loanInfo);
			
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
				request.setAttribute("status", dbLoanInfo.getStatus());
				request.setAttribute("populate", "EditLoan");
				return "editloanui.jsp";	
			}
		
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw e;
		}
	
		throw new Exception("Invalid Loan Application Id or The Status of Loan has been Changed from New.");
		
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		
		return null;
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		return null;
	}
}