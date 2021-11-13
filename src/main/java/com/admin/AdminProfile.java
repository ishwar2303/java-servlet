package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.database.DBConDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quizwit.Utility;

@WebServlet("/admin-profile")

public class AdminProfile extends HttpServlet {
	
	private static final long serialVersionUID = -2329232613495123276L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Integer adminId = (Integer) session.getAttribute("adminId");
		req.setAttribute("adminFirstName", session.getAttribute("adminFirstName"));
		req.setAttribute("adminEmail", session.getAttribute("adminEmail"));

		String flashSuccess = (String) session.getAttribute("flashSuccess");
		if(flashSuccess != null) {
			req.setAttribute("flashSuccess", flashSuccess);
			session.removeAttribute("flashSuccess");
		}
		if(adminId == null) {
			res.sendRedirect("index");
			return;
		}
		
		JSONObject json = new JSONObject();
		String success = "", error = "";
		try {
			JSONObject data = getAdminProfileInfo(adminId);
			
			json.put("admin", data);
			if(data != null)
				success = "Data fetched successfully";
			else error = "Something went wrong";
			
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		json.put("success", success);
		json.put("error", error);
		
		req.setAttribute("data", json);
		req.setAttribute("activeNavLink", "admin-profile");
		req.getRequestDispatcher("/admin/admin-profile.jsp").forward(req, res);
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession session = req.getSession();
		Integer adminId = (Integer) session.getAttribute("adminId");
		if(adminId == null) {
			res.sendRedirect("admin-login");
			return;
		}
		String firstName = req.getParameter("firstName");
		String lastName  = req.getParameter("lastName");
		String contact   = req.getParameter("contact");
		String genderId  = req.getParameter("gender");
		String institution = req.getParameter("institution");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String success = "";
		String error = "";

		ArrayList<String> errorLog = new ArrayList<String>();
		for(int i=0; i<9; i++)
			errorLog.add("");
		
		if(firstName != null && genderId != null && dateOfBirth != null) {
			Boolean control = true;
			if(firstName == "") {
				control = false;
	 			errorLog.set(0, "First Name required");
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
		        } catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

			if(control) {
				Boolean result = false;
				try {
					result = updateProfile(firstName, lastName, contact, genderId, institution, dateOfBirth, adminId);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(result) {
					success = "Profile Updated Successfully";
					session.setAttribute("flashSuccess", success);
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
	
	public JSONObject getAdminProfileInfo(Integer adminId) throws ClassNotFoundException, SQLException, ParseException {
		DBConDao dao = new DBConDao();
		Connection con = dao.connection();
		String sql = "SELECT * FROM admin a INNER JOIN gender g on a.gender = g.genderId WHERE adminId = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, adminId);
		ResultSet rs = st.executeQuery();
		rs.next();
		AdminPojo ap = new AdminPojo();
		ap.adminId = rs.getInt(1);
		ap.firstName = rs.getString(2);
		ap.lastName = rs.getString(3);
		ap.email = rs.getString(4);
		ap.contact = rs.getString(5);
		ap.institution = rs.getString(7);
		ap.dob = rs.getString(8);
		ap.gender = rs.getString(10);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonStr = gson.toJson(ap);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(jsonStr);
		
		return jsonObj;
		
	}
	
	public Boolean updateProfile(String firstName, String lastName, String contact, String gender, String institution, String dateOfBirth, Integer adminId) throws SQLException, ClassNotFoundException {
		DBConDao dao = new DBConDao();
		Connection con = dao.connection();

		String sql = "UPDATE admin SET firstName = ?, lastName = ?, contact = ?, gender = ?, institution = ?, dateOfBirth = ? WHERE adminId = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, firstName);
		st.setString(2, lastName);
		st.setString(3, contact);
		st.setInt(4, Integer.parseInt(gender));
		st.setString(5, institution);
		st.setString(6, dateOfBirth);
		st.setInt(7, adminId);
		Integer result = st.executeUpdate();
		st.close();
		con.close();
		return result > 0 ? true : false;
	}
}
