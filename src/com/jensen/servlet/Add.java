package com.jensen.servlet;

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

/**
 * Servlet implementation class AmountChange
 */
@WebServlet("/AmountChange")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
		
	}
//plusar upp användarens pengar.
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int newValue= Integer.parseInt(request.getParameter("newValue"));

        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int oldAmount = 0;
		HttpSession session=request.getSession(false);  
		int id = (int) session.getAttribute("userid");
		try {
			ps2 = con.prepareStatement("select amount from bankmoney where id = ?");
			ps2.setInt(1, id);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
			 oldAmount = rs.getInt("amount");
				
			}
			int newAmount = newValue + oldAmount;
			
			ps = con.prepareStatement("update bankmoney set amount=? where id=? ");
			
			ps.setInt(1, newAmount);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println(newValue);
			session.setAttribute("amount", newAmount);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 request.getRequestDispatcher("ProfileServlet").include(request, response); 
	}

}
