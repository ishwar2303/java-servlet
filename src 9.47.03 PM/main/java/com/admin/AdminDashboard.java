package com.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin-dashboard")
public class AdminDashboard extends HttpServlet {
	
	private static final long serialVersionUID = 992673077435050419L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String flashSuccess = (String) session.getAttribute("flashSuccess");
		if(flashSuccess != null) {
			req.setAttribute("flashSuccess", flashSuccess);
			session.removeAttribute("flashSuccess");
		}
		req.setAttribute("adminFirstName", session.getAttribute("adminFirstName"));
		req.setAttribute("adminEmail", session.getAttribute("adminEmail"));

		req.setAttribute("activeNavLink", "admin-dashboard");
		if(session.getAttribute("adminId") == null)
			res.sendRedirect("admin-login");
		else req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, res);
	}
}
