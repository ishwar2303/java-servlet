package com.quizwit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/index")
public class Home extends HttpServlet {
	
	private static final long serialVersionUID = -4826327637033619882L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String ref = req.getParameter("ref");
		req.setAttribute("ref", ref);
		req.getRequestDispatcher("index.jsp").forward(req, res);
	}
}
