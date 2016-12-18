package com.jensen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jensen.util.BankMoney;
import com.jensen.util.BankUsers;

// hämtar ut från sql från vart pers och password matchar

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    // in login
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pernr = request.getParameter("pernr");
		String password = request.getParameter("password");
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			ps = con.prepareStatement("select id,fname,lname,pernr from bankusers where pernr=? and password=? limit 1");
			ps.setString(1, pernr);
			ps.setString(2, password);
			rs = ps.executeQuery();
			System.out.println("innan try");
			if(rs != null && rs.next()){
			
				BankUsers user = new BankUsers (rs.getInt("id"),rs.getString("lname"),rs.getString("fname") ,rs.getString("pernr"));
				System.out.println(rs.getInt("id"));
				HttpSession session = request.getSession();
				session.setAttribute("fname", user.getFname());
				session.setAttribute("lname", user.getLname());
					
				ps2= con.prepareStatement("select amount,id from bankmoney where id =?");
				ps2.setInt(1, user.getId());
				rs2 = ps2.executeQuery();
				rs2.next();
				System.out.println("efter amount");
				BankMoney money = new BankMoney(rs2.getInt("id"), rs2.getInt("amount"));
				session.setAttribute("amount", money.getAmount());
				session.setAttribute("id", money.getId());
				session.setAttribute("userid",user.getId());
				request.getRequestDispatcher("ProfileServlet").forward(request,response);
				
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				PrintWriter out= response.getWriter();
				out.println("<font color=red>No user found, please register first.</font>");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("DB Connection problem.");
		}finally{
			
				try {
					rs.close();
					ps.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
		}
	}

}
