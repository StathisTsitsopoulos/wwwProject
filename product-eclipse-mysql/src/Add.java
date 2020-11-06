

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
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String product_name = request.getParameter("product_name");
		String barcode = request.getParameter("barcode");
		String color = request.getParameter("Color");
		String description = request.getParameter("Description");
		
		
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3307/testdb";
	    String username = "root";
	    String password = "";
		int flag = 0;
	    
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	conn = DriverManager.getConnection(url,username,password);
	    	
	    	if (conn!=null) {
	    		System.out.println("HEY");
	    	}
	    }catch(SQLException e) {
	    	 e.printStackTrace();
	    }catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
		if (conn!=null) {
			Statement stmt = null;
			Statement stmt2 = null;
			try {
				stmt = conn.createStatement();
				stmt2 = conn.createStatement();
				
				
				ResultSet rs = stmt2.executeQuery("SELECT * FROM names");
				while (rs.next()) {
					if (barcode.equals(rs.getString(2))) {
						flag = 1;
						stmt2.close();
						response.sendRedirect("./Error");
					}
				}
				
				if (flag == 0) {
					stmt.executeUpdate("INSERT INTO names(product_name,barcode,color,description) VALUES ('"+product_name+"','"+barcode+"','"+color+"','"+description+"')");
					out.print("<a href='index.html'>Home</a>");
					out.print("\nproduct: "+product_name+" with barcode: "+barcode+" and of color: "+color+" was added succesfully. Click above to add another product.");
					
					stmt.close();
					
				}
				else {
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

				
				
				
			
		}
		
		
		
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
