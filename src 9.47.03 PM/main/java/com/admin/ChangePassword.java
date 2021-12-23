package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.database.DBConDao;

@WebServlet("/admin-change-password")

public class ChangePassword extends HttpServlet {
	
	private static final long serialVersionUID = -4383313839354157961L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String success = "";
		String error = "";
		ArrayList<String> errorLog = new ArrayList<String>();
		for(int i=0; i<3; i++)
			errorLog.add("");
		
		HttpSession session = req.getSession();
		Integer adminId = (Integer) session.getAttribute("adminId");
		if(adminId == null) {
			error = "Not logged in";
		}
		else {

			String oldPassword = req.getParameter("oldPassword");
			String newPassword = req.getParameter("newPassword");
			String confirmPassword = req.getParameter("confirmPassword");

			if(oldPassword != null && newPassword != null && confirmPassword != null) {
				Boolean control = true;
				if(oldPassword == "") {
					control = false;
					errorLog.set(0, "Old password required");
				}
				else {
					Boolean result = true;
					try {
						result = matchOldPassword(oldPassword, adminId);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(!result) {
						control = false;
						errorLog.set(0, "Incorrect old password");
					}
				}
				
				if(newPassword == "") {
					control = false;
					errorLog.set(1, "New password required");
				}
				else if(newPassword.length() < 8) {
					control = false;
					errorLog.set(1, "Password must contain atleast 8 characters");
				}
				else if(newPassword.equals(oldPassword)) {
					control = false;
					errorLog.set(1, "New password cannot be same as old password");
				}
				
				if(confirmPassword == "") {
					control = false;
					errorLog.set(2, "Confirm password required");
				}
				
				if(!newPassword.equals(confirmPassword)) {
					control = false;
					errorLog.set(2, "Password not matched");
				}
				
				if(control) {
					Boolean result = true;
					try {
						result = updatePassword(newPassword, adminId);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(!result) {
						error = "Something went wrong";
					}
					else {
						success = "Password changed successfully";
						session.setAttribute("flashSuccess", success);
					}
				}
				else error = "Please fill details appropriately";
			}
		}
		JSONObject json = new JSONObject();
		json.put("success", success);
		json.put("error", error);
		json.put("errorLog", errorLog);
		
		PrintWriter printWriter = res.getWriter();
		printWriter.println(json.toString());
	}
	
	public Boolean matchOldPassword(String oldPassword, Integer adminId) throws ClassNotFoundException, SQLException {
		DBConDao dao = new DBConDao();
		Connection con = dao.connection();
		String sql = "SELECT COUNT(adminId) from admin WHERE adminId = ? and password = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, adminId);
		st.setString(2, oldPassword);
		ResultSet rs = st.executeQuery();
		rs.next();
		Integer result = rs.getInt(1);
		rs.close();
		st.close();
		con.close();
		return result > 0 ? true : false;
	}
	
	public Boolean updatePassword(String newPassword, Integer adminId) throws ClassNotFoundException, SQLException {
		DBConDao dao = new DBConDao();
		Connection con = dao.connection();
		String sql = "UPDATE admin SET password = ? WHERE adminId = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, newPassword);
		st.setInt(2, adminId);
		Integer result = st.executeUpdate();
		st.close();
		con.close();
		return result > 0 ? true : false;
		
	}
}
