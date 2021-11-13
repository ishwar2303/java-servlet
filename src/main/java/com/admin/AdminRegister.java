package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.database.DBConDao;
import com.quizwit.Utility;

@WebServlet("/admin-register")
public class AdminRegister extends HttpServlet {

	private static final long serialVersionUID = 14224301720778699L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// to move to contact-us, about-us page after redirecting from other pages 
		req.setAttribute("makeLink", true);
		
		req.getRequestDispatcher("/admin/admin-register.jsp").forward(req, res);
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String firstName = req.getParameter("firstName");
		String lastName  = req.getParameter("lastName");
		String email     = req.getParameter("email");
		String contact   = req.getParameter("contact");
		String genderId  = req.getParameter("gender");
		String institution = req.getParameter("institution");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		String success = "";
		String error = "";

		ArrayList<String> errorLog = new ArrayList<String>();
		for(int i=0; i<9; i++)
			errorLog.add("");
		
		HttpSession session = req.getSession();
		session.setAttribute("userType", "admin");
		Boolean verifiedEmail = (Boolean) session.getAttribute("verifiedEmail");
		String verifiedEmailDesc = (String) session.getAttribute("verifiedEmailDesc");
		if(firstName != null && email != null && genderId != null && dateOfBirth != null && password != null && confirmPassword != null) {
			Boolean control = true;
			if(firstName == "") {
				control = false;
	 			errorLog.set(0, "First Name required");
			}
			
			
			if(email == "") {
				control = false;
				errorLog.set(2, "E-mail required");
			}
			else if(!Utility.validateEmail(email)) {
				control = false;
				errorLog.set(2, "Invalid E-mail");
			}
			else if(verifiedEmail != null) {
				System.out.println(verifiedEmailDesc);
				if(verifiedEmail && !verifiedEmailDesc.equals(email)) {
					control = false;
					errorLog.set(2, "E-mail changed verify again");
				}
				else email = verifiedEmailDesc;
			}
			else {
				control = false;
				errorLog.set(2, "E-mail not verified");
			}

			try {
				Boolean result = adminExists(email);
				if(result) {
					control = false;
					errorLog.set(2, "Already registered with this E-mail");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(genderId == "") {
				control = false;
				errorLog.set(4, "Gender required");
			}
			else if(!Utility.isNumeric(genderId) || !genderId.matches("[123]{1}")) {
				control = false;
				errorLog.set(4, "Invalid gender");
			}
			if(dateOfBirth == "") {
				control = false;
				errorLog.set(6, "Date of Brith required");
			}
			else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		        dateFormat.setLenient(false);
		        try {
		            dateFormat.parse(dateOfBirth);
		        } catch (ParseException pe) {
					control = false;
					errorLog.set(6, "Invalid Date of Birth");
		        }
			}
			
			if(password == "" ) {
				control = false;
				errorLog.set(7, "Password required");
			}
			else if(password.length() < 8) {
				control = false;
				errorLog.set(7, "Password must contain atleast 8 characters");
			}
			
			if(confirmPassword == "" ) {
				control = false;
				errorLog.set(8, "Confirm Password required");
			}
			else if(!password.equals(confirmPassword)) {
				control = false;
				errorLog.set(8, "Password not matched");
			}

			if(control) {
				Boolean result = false;
				try {
					result = addAdmin(firstName, lastName, email, contact, genderId, institution, dateOfBirth, password);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(result) {

					session.removeAttribute("verifiedEmail");
					session.removeAttribute("verifiedEmailDesc");
					session.setAttribute("flashSuccess", "Registration Successfull");
					success = "Registration Successfull";
				}
				else error = "Something went wrong";
			}
			else {
				error = "Please fill required fields appropriately";
			}
			
		}
		else {
			error = "Please fill required fields appropriately";
		}
		JSONObject json = new JSONObject();
		json.put("success", success);
		json.put("error", error);
		json.put("errorLog", errorLog);
		PrintWriter printWriter = res.getWriter();
		printWriter.println(json.toString());
		
	}
	public boolean adminExists(String email) throws ClassNotFoundException, SQLException {
		DBConDao dao = new DBConDao();
		Connection con = dao.connection();
		String sql = "SELECT COUNT(adminId) FROM `admin` WHERE email = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		rs.next();
		Integer records = rs.getInt(1);
		rs.close();
		st.close();
		con.close();
		return records > 0 ? true : false;
	}
	
	public boolean addAdmin(String firstName, String lastName, String email, String contact, String gender, String institution, String dateOfBirth, String password) throws ClassNotFoundException, SQLException {
		DBConDao dao = new DBConDao();
		Connection con = dao.connection();

		String sql = "INSERT INTO `admin` (`firstName`, `lastName`, `email`, `contact`, `gender`, `institution`, `dateOfBirth`, `password`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, firstName);
		st.setString(2, lastName);
		st.setString(3, email);
		st.setString(4, contact);
		st.setInt(5, Integer.parseInt(gender));
		st.setString(6, institution);
		st.setString(7, dateOfBirth);
		st.setString(8, password);
		Integer count = st.executeUpdate();
		st.close();
		con.close();
		return count > 0 ? true : false;
	}
}
