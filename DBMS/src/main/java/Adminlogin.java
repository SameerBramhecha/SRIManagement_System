import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Import Database Connection Class file
//import DatabaseConnection;

// Servlet Name
@WebServlet("/Adminlogin")
public class Adminlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
HttpServletResponse response)
		throws ServletException, IOException
	{
		try {

			// Initialize the database
			Connection con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			// demo table consists of two columns, so two '?' is used
			PreparedStatement st;
			//"select * from Internship where companyname = (?)"; 

			String regno = request.getParameter("registrationno");
			String password = request.getParameter("password");
			st = con.prepareStatement("select password from login_details where registration_no = '" + regno+"'");
			ResultSet rs = st.executeQuery();
			if(rs!=null) {
				rs.next();
				String opass = rs.getString(1);
				
				if(opass.equals(password)) {
					PrintWriter out = response.getWriter();
					out.println("<html><body><b><form action = \"admin.html\">\r\n"
							+ "    		<button class=\"button\">Sign In  </button><br><br></form>"
								+ "</b></body></html>");
				}
				else {
					PrintWriter out = response.getWriter();
					out.println("<html><body><b>Incorrect Registration Number or Password"
								+ "</b></body></html>");
				}
			}
			else {
				PrintWriter out = response.getWriter();
				out.println("<html><body><b>Please Register Before Sign In"
							+ "</b></body></html>");
			}
			
			
			// For the first parameter,
			// get the data using request object
			// sets the data to st pointer
			// First parameter
			// Execute the insert command using executeUpdate()
			// to make changes in database

			// Close all the connections
			st.close();
			con.close();

			// Get a writer pointer
			// to display the successful result
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//create table admin login
