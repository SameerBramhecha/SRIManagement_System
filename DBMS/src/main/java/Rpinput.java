import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Rpinput")
public class Rpinput extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException {
		try {
		 	
			PrintWriter out = response.getWriter();
			// Initialize the database
			Connection con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			// demo table consists of two columns, so two '?' is used
			String pubname = request.getParameter("publicationname");
			String pubtitle = request.getParameter("publicationtitle");
			String domain = request.getParameter("domain");
			
			int sid = Integer.parseInt(request.getParameter("s_id"));
			String tmem= request.getParameter("Team Members");
			
			
			
			
			PreparedStatement st = con.prepareStatement("call insertintoresearch('"+pubname+"','"+pubtitle+"','"+domain+"',"+
			sid+",'"+tmem+"');");		
			int result = st.executeUpdate();
			if(result!=0) {
				out.println("<html><body><b>Successfully Inserted"
						+ "</b></body></html>");
			}else {
				out.println("<html><body><b>Not Inserted"
						+ "</b></body></html>");
			}
			// Close all the connections
			//st.close();
			
			con.close();

			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}