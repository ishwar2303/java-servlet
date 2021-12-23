package com.quizwit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = 992673077435050419L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String page = req.getParameter("page");
		System.out.println("page = " + page);
		HttpSession session = req.getSession();
		session.invalidate();
		Boolean control = true;
		if(page == null) 
			control = false;
		else if(!page.equals("admin-login") && !page.equals("student-login"))
			control = false;
		
		if(control)
			res.sendRedirect(page);
		else res.sendRedirect("index");
			
	}
}