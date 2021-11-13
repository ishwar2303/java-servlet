package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

@WebServlet("/create-quiz")
public class CreateQuiz extends HttpServlet {
	
	private static final long serialVersionUID = 8404285090287638368L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setAttribute("adminFirstName", session.getAttribute("adminFirstName"));
		req.setAttribute("adminEmail", session.getAttribute("adminEmail"));
		
		req.setAttribute("activeNavLink", "create-quiz");
		if(session.getAttribute("adminId") == null)
			res.sendRedirect("admin-login");
		else req.getRequestDispatcher("/admin/create-quiz.jsp").forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		Integer adminId = (Integer) session.getAttribute("adminId");
		if(adminId == null) {
			res.sendRedirect("admin-login");
			return;
		}
		String quizName = req.getParameter("quizName");
		String quizDesc = req.getParameter("quizDescription");
		String quizKey  = req.getParameter("quizKey");
		String difficultyLevel = req.getParameter("difficultyLevel");
		String state = req.getParameter("state");
		String duration = req.getParameter("duration");
		String startTime = req.getParameter("startTime");
		String endTime   = req.getParameter("endTime");

		String success = "";
		String error = "";

		ArrayList<String> errorLog = new ArrayList<String>();
		for(int i=0; i<8; i++)
			errorLog.add("");
		
		if(quizName != null && quizDesc != null && quizKey != null && difficultyLevel != null && state != null && duration != null && startTime != null && endTime != null) {
			Boolean control = true;
			if(quizName == "") {
				control = false;
				errorLog.set(0, "Quiz name required");
			}
			
			if(quizDesc == "") {
				control = false;
				errorLog.set(1, "Description required");
			}
			
			if(quizKey == "") {
				control = false;
				errorLog.set(2, "Key required");
				
			}
			else if(quizKey.length() < 8) {
				control = false;
				errorLog.set(2, "Key must contain atleast 8 characters");
			}
			
			if(difficultyLevel == "") {
				control = false;
				errorLog.set(3, "Difficulty level required");
			}
			else if(!difficultyLevel.equals("1") && !difficultyLevel.equals("2") && !difficultyLevel.equals("3")) {
				control = false;
				errorLog.set(3, "Invalid difficulty level");
			}
			
			if(state == "") {
				control = false;
				errorLog.set(4, "State required");
			}
			else if(!state.equals("0") && !state.equals("1")) {
				control = false;
				errorLog.set(4, "Invalid state");
			}
			
			if(duration == "") {
				control = false;
				errorLog.set(5, "Quiz duration required");
			}
			else if(!duration.equals("900") && !duration.equals("1800") && !duration.equals("2700") && !duration.equals("3600") && !duration.equals("7200") && !duration.equals("10800")) {
				control = false;
				errorLog.set(5, "Invalid duration");
			}
			
			System.out.println(startTime);
			System.out.println(endTime);
			
			if(control) {
				Boolean result = true;
				try {
					result = addQuiz(quizName, quizDesc, quizKey, difficultyLevel, state, duration, startTime, endTime, adminId);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(result) {
					success = "Quiz added successfully";
					session.setAttribute("flashSuccess", success);
				}
				else error = "Something went wrong"; 
			}
			else {
				error = "Please fill all details appropriately";
			}
			
		}
		else {
			error = "Please fill all details appropriately";
		}
		
		JSONObject json = new JSONObject();
		json.put("succes", success);
		json.put("error", error);
		json.put("errorLog", errorLog);
		
		PrintWriter printWriter = res.getWriter();
		printWriter.println(json.toString());
	}
	
	public Boolean addQuiz(String quizName, String quizDescription, String quizKey, String difficultyLevel, String state, String duration, String startTime, String endTime, Integer adminId) throws ClassNotFoundException, SQLException {
		
		DBConDao dao = new DBConDao();
		Connection con = dao.connection();
		String sql = "INSERT INTO quizzes VALUES (NULL, ?, ?, ?, ?, ?, 0, ?, ?, ?, 0, CURRENT_TIMESTAMP(), ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, quizName);
		st.setString(2, quizDescription);
		st.setString(3, quizKey);
		st.setInt(4, Integer.parseInt(difficultyLevel));
		st.setInt(5, Integer.parseInt(state));
		st.setInt(6, Integer.parseInt(duration));
		st.setString(7, startTime);
		st.setString(8, endTime);
		st.setInt(9, adminId);
		Integer result = st.executeUpdate();
		return result > 0 ? true : false;
	}
}
