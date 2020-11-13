import news.Dao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;


/**
 * Servlet implementation class Add
 */
@WebServlet("/add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		String product_name = request.getParameter("product_name");
		String barcode = request.getParameter("barcode");
		String color = request.getParameter("Color");
		String description = request.getParameter("Description");
		
		Dao dao= new Dao();
		boolean success = false;
		success = dao.saveDetails(product_name,barcode,color,description); 
		
				
		if (success)	 {	
			out.print("<a href='index.html'>Home</a>");
			out.print("\nproduct: "+product_name+" with barcode: "+barcode+" and of color: "+color+" was added succesfully. Click above to add another product.");
		}
		else {
			out.print("ERRORRR");			
		}
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
