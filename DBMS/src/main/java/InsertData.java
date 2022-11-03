import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Import Database Connection Class file
//import DatabaseConnection;

// Servlet Name
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
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
			PreparedStatement st = con
				.prepareStatement("insert into login_details values(?, ?, ?)");
			//"select * from Internship where companyname = (?)"; 

			// For the first parameter,
			// get the data using request object
			// sets the data to st pointer
			// First parameter
			String regno = request.getParameter("registrationno");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String cnfrpass = request.getParameter("confirmpassword");
//			if(password.equals(cnfrpass)) {
				st.setString(1, regno);
				//
				st.setString(2, password);
				//
				st.setString(3, email);
				// Execute the insert command using executeUpdate()
				// to make changes in database
				st.executeUpdate();

//			}
//			else {
//				PrintWriter out = response.getWriter();
//				out.println("<html><body><b>Passwords do not match!!"
//							+ "</b></body></html>");
//			}
			
			// Close all the connections
			st.close();
			con.close();

			// Get a writer pointer
			// to display the successful result
			PrintWriter out = response.getWriter();
			out.println("<html><body><b>Successfully Inserted"
						+ "</b></body></html>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
