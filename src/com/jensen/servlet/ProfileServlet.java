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
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
	    PrintWriter out=response.getWriter(); 
	    request.getRequestDispatcher("link.html").include(request, response);  
		
	    HttpSession session=request.getSession(false);  
        if(session!=null){
        String fname=(String)session.getAttribute("fname"); 
        String lname=(String)session.getAttribute("lname"); 
        int amount =(int)session.getAttribute("amount");
        out.println("Hej  " + fname +" " + lname +". V‰lkommen till din profil!"); 
        
        out.println("dina pengar: " + amount);
       
              
		
        }  
        else{  
            out.print("Logga in f√∂rst");  
            request.getRequestDispatcher("login.html").include(request, response);  
        }  
        out.close();  
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		doGet(request, response);
	}

}
