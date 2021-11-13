package com.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
}
