package com.jensen.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//om session �r d�d loggar ut

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        request.getRequestDispatcher("link.html").include(request, response);  
         
        
        //false eftersom vi inte vill skapa en ny session om det inte finns en
        HttpSession session=request.getSession(false);
        if(session==null)
        	//Det fanns ingen session för denna användaren (dvs ej inloggad)
        	out.println("Du är inte inloggad");
        else
        {
        	//tar bort denna sessionen
        	session.invalidate();
           out.print("Du är nu utloggad!");  
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
