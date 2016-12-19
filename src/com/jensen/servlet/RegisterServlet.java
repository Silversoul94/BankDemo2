package com.jensen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jensen.util.DBConnectionManager;

//hämtar ut parameter och skickar det till sql

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pernr = request.getParameter("pernr");
		String password = request.getParameter("password");
		String fname = request.getParameter("fname");
		String lname =request.getParameter("lname");
		int amount = Integer.parseInt(request.getParameter("amount"));


		String errorMsg = null;
		//hÃ¤r kan tÃ¤nkas att det ska gÃ¶ras en mer noggrann kontroll om
		//e-mail verkligen Ã¤r en korrekt e-mail-adress
		if(pernr == null|| pernr.equals("")){
			errorMsg = "får ej vara tom.";
		}
		if(password == null || password.equals("")){
			errorMsg = "får ej vara tom.";
		}
		if(fname == null || fname.equals("")){
			errorMsg = "får ej vara tom.";
		}
		if(lname == null || lname.equals("")){
			errorMsg = "får ej vara tom.";
		}
		//inte klar
		if(amount == 0){
			errorMsg = " får ej vara 0.";
		}
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		
		try {
			ps = con.prepareStatement("insert into bankusers(pernr, password, fname, lname) values (?,?,?,?)");
			
			ps.setString(1, pernr);
			ps.setString(2, password);
			ps.setString(3, fname);
			ps.setString(4, lname);		
			
			
			ps2 = con.prepareStatement("insert into bankmoney (amount) values (?)");
			ps2.setInt(1,amount);
			ps2.execute();
			ps.execute();
			
			//forward to login page to login
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=green>Registration successful, please login below.</font>");
			rd.include(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
		
	}

}
