package com.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/student-dashboard")
public class StudentDashboard extends HttpServlet {
	
	private static final long serialVersionUID = 992673077435050419L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setAttribute("studentFirstName", session.getAttribute("studentFirstName"));
		req.setAttribute("studentEmail", session.getAttribute("studentEmail"));
		
		if(session.getAttribute("studentId") == null)
			res.sendRedirect("student-login");
		else req.getRequestDispatcher("/student/student-dashboard.jsp").forward(req, res);
	}
}
