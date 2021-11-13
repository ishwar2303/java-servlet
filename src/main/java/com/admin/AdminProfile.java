package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

@WebServlet("/admin-profile")

public class AdminProfile extends HttpServlet {
	
	private static final long serialVersionUID = -2329232613495123276L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Integer adminId = (Integer) session.getAttribute("adminId");
		req.setAttribute("adminFirstName", session.getAttribute("adminFirstName"));
		req.setAttribute("adminEmail", session.getAttribute("adminEmail"));
		
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
}
