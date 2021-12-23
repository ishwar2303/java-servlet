package com.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.database.DBConDao;


@WebServlet("/student-login")
public class StudentLogin extends HttpServlet {
	
	private static final long serialVersionUID = -3976008207672651335L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// to move to contact-us, about-us page after redirecting from other pages 
		req.setAttribute("makeLink", true);
		
		HttpSession session = req.getSession();
		String msg = (String) session.getAttribute("flashSuccess");
		if(msg != null) {
			System.out.println("In student login : " + msg);
			req.setAttribute("flashSuccess", msg);
			session.removeAttribute("flashSuccess");
		}
		req.getRequestDispatcher("/student/student-login.jsp").forward(req, res);
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String success = "", error = "";
		ArrayList<String> errorLog = new ArrayList<String>();
		errorLog.add("");
		errorLog.add("");
		if(email != null && password != null) {
			Boolean control = true;
			if(email == "") {
				control = false;
				errorLog.set(0, "E-mail required");
			}
			else {
				StudentRegister sr = new StudentRegister();
				try {
					Boolean result = sr.studentExists(email);
					if(!result) {
						control = false;
						errorLog.set(0, "E-mail not registered");
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
			if(password == "") {
				control = false;
				errorLog.set(1, "Password required");
			}
			
			if(control) {
				try {
					DBConDao dao = new DBConDao();
					Connection con = dao.connection();
					
					String sql = "SELECT studentId, firstName FROM student WHERE email = ? AND password = ?";
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, email);
					st.setString(2, password);
					ResultSet rs = st.executeQuery();
					Boolean result = false;
					while(rs.next()) {
						result = true;
						Integer studentId = rs.getInt(1);
						String firstName = rs.getString(2);
						HttpSession session = req.getSession();
						session.setAttribute("studentId", studentId);
						session.setAttribute("studentFirstName", firstName);
						session.setAttribute("studentEmail", email);
					}
					st.close();
					rs.close();
					con.close();
					
					if(result) {
						success = "Login Successfull";
					}
					else {
						errorLog.set(1, "Incorrect Password");
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else error = "Please fill your credentials";
			
		}
		else error = "Invalid name attribute value according to backend process"; 
		
		JSONObject json = new JSONObject();
		json.put("success", success);
		json.put("error", error);
		json.put("errorLog", errorLog);
		
		PrintWriter printWriter = res.getWriter();
		printWriter.println(json.toString());
	}
	
	
}
