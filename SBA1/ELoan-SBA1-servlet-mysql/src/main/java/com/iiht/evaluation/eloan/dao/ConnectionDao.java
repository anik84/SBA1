package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;

public class ConnectionDao {
	private static final long serialVersionUID = 1L;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ConnectionDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	public  Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	// put the relevant DAO methods here..

	//User
	public static final String INS_USER_QRY = "INSERT INTO users(username,password) VALUES(?,?)";
	public static final String GET_BY_USER_NAME_QRY = "SELECT * FROM users WHERE username=? AND password=?";	
	//Loan Info in table loan
	public static final String INS_LOAN_QRY = 
			"INSERT INTO loan(applno,purpose,amtrequest,doa,bstructure,bindicator,tindicator,address,email,mobile,status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_LOAN_STATUS_BY_APPL_ID_QRY = "SELECT applno,status FROM loan WHERE applno=?";
	public static final String GET_USER_APPL_BY_APPL_ID_QRY = "SELECT * FROM loan WHERE applno=? AND status='new'";
	public static final String GET_ALL_LOAN_QRY = "SELECT * FROM loan";  //admin
	public static final String UPDATE_LOAN_QRY = 
			"UPDATE loan SET purpose=?,amtrequest=?,bstructure=?,bindicator=?,tindicator=?,address=?,email=?,mobile=? WHERE applno=?";
	//Approved loan details
	public static final String INS_APPROVED_LOAN_DETAILS_QRY = 
			"INSERT INTO approvedloan(applno,amotsanctioned,loanterm,psd,lcd,emi) VALUES(?,?,?,?,?,?)";
	public static final String UPDATE_LOAN_STATUS_QRY = 
			"UPDATE loan SET status=? WHERE applno=?";
	
	
	public void addUser(User user) throws SQLException{
			
			try {
				connect();
				PreparedStatement pst = jdbcConnection.prepareStatement(INS_USER_QRY);
				
				pst.setString(1, user.getUsername());
				pst.setString(2, user.getPassword());
				
				pst.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			
			//return user;			
			
	}
	
	public User getUserByName(User user) throws SQLException{
		
		try {
			connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_BY_USER_NAME_QRY);
			
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return null;			
		
	}
	
	public void insertLoanInfo(LoanInfo loanInfo) throws SQLException{
		
		try {
			connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(INS_LOAN_QRY);
			
			pst.setString(1, loanInfo.getApplno());
			pst.setString(2, loanInfo.getPurpose());
			pst.setInt(3, loanInfo.getAmtrequest());
			pst.setString(4, loanInfo.getDoa());
			pst.setString(5, loanInfo.getBstructure());
			pst.setString(6, loanInfo.getBindicator());
			pst.setString(7, loanInfo.getTindicator());
			pst.setString(8, loanInfo.getAddress());
			pst.setString(9, loanInfo.getEmail());
			pst.setString(10, loanInfo.getMobile());
			pst.setString(11, loanInfo.getStatus());
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		//return loanInfo;			
		
	}
	
	public LoanInfo getStatusById(LoanInfo loanInfo) throws SQLException{
		
		try {
			connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_LOAN_STATUS_BY_APPL_ID_QRY);
			
			pst.setString(1, loanInfo.getApplno());
						
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				loanInfo = new LoanInfo();
				loanInfo.setApplno(rs.getString(1));
				loanInfo.setStatus(rs.getString(2));
				
				return loanInfo;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return null;			
		
	}
	
	
	public LoanInfo getLoanInfoByID(LoanInfo loanInfo) throws SQLException{
		
		try {
			connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_USER_APPL_BY_APPL_ID_QRY);
			
			pst.setString(1, loanInfo.getApplno());
						
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				loanInfo = new LoanInfo();
				loanInfo.setApplno(rs.getString(1));
				loanInfo.setPurpose(rs.getString(2));
				loanInfo.setAmtrequest(Integer.parseInt(rs.getString(3)));
				loanInfo.setDoa(rs.getString(4));
				loanInfo.setBstructure(rs.getString(5));
				loanInfo.setBindicator(rs.getString(6));
				loanInfo.setTindicator(rs.getString(7));
				loanInfo.setAddress(rs.getString(8));
				loanInfo.setEmail(rs.getString(9));
				loanInfo.setMobile(rs.getString(10));
				loanInfo.setStatus(rs.getString(11));
				
				return loanInfo;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return null;			
		
	}
	
	public List<LoanInfo> getAllLoan() throws SQLException{
		List<LoanInfo> loanInfos =new ArrayList<LoanInfo>();
		try {
			connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_ALL_LOAN_QRY);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				LoanInfo loanInfo = new LoanInfo();
				
				loanInfo.setApplno(rs.getString(1));
				loanInfo.setPurpose(rs.getString(2));
				loanInfo.setAmtrequest(Integer.parseInt(rs.getString(3)));
				loanInfo.setDoa(rs.getString(4));
				loanInfo.setBstructure(rs.getString(5));
				loanInfo.setBindicator(rs.getString(6));
				loanInfo.setTindicator(rs.getString(7));
				loanInfo.setAddress(rs.getString(8));
				loanInfo.setEmail(rs.getString(9));
				loanInfo.setMobile(rs.getString(10));
				loanInfo.setStatus(rs.getString(11));
				
				loanInfos.add(loanInfo);
				
			}
			return loanInfos;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}	
		
	}
	
	public void updateLoanInfo(LoanInfo loanInfo) throws SQLException{
		
		try {
			connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(UPDATE_LOAN_QRY);
			
			pst.setString(1, loanInfo.getPurpose());
			pst.setInt(2, loanInfo.getAmtrequest());
			pst.setString(3, loanInfo.getBstructure());
			pst.setString(4, loanInfo.getBindicator());
			pst.setString(5, loanInfo.getTindicator());
			pst.setString(6, loanInfo.getAddress());
			pst.setString(7, loanInfo.getEmail());
			pst.setString(8, loanInfo.getMobile());
			pst.setString(9, loanInfo.getApplno());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		//return loanInfo;			
		
	}
	
	public void insertApprovedLoan(ApprovedLoan approvedLoan) throws SQLException{
		
		try {
			connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(INS_APPROVED_LOAN_DETAILS_QRY);
		
			pst.setString(1, approvedLoan.getApplno());
			pst.setInt(2, approvedLoan.getAmotsanctioned());
			pst.setInt(3, approvedLoan.getLoanterm());
			pst.setString(4, approvedLoan.getPsd());
			pst.setString(5, approvedLoan.getLcd());
			pst.setInt(6, approvedLoan.getEmi());
			
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
				
		//return approvedLoan;			
		
	}
	
	public void updateLoanStatus(LoanInfo loanInfo) throws SQLException{
		
		try {
			connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(UPDATE_LOAN_STATUS_QRY);
		
			pst.setString(1, loanInfo.getStatus());
			pst.setString(2, loanInfo.getApplno());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		//return loanInfo;			
		
	}
		
	
}


