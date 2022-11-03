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

@WebServlet("/Research")
public class Research extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
HttpServletResponse response)
		throws ServletException, IOException{
		try {
			
			PrintWriter out = response.getWriter();
			// Initialize the database
			Connection con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			// demo table consists of two columns, so two '?' is used
			PreparedStatement st;
			String domain = null;
			try {
				domain = request.getParameter("domainname");
			} 
			catch(Exception e) {
				
			}
			System.out.println(domain);
			ResultSet rs = null;
			if(domain.equals("")) {
				st = con.prepareStatement("select * from researchpapereach;");
				rs = st.executeQuery();
			}
			else{
				st = con.prepareStatement("select * from researchpapereach where domain = '" + domain+ "';");
				rs = st.executeQuery();
			}
			
			
			
			
			
			out.println("<table class='tb' border=1 width=50% height=50%>");
			out.println("<tr><th>Publication Name</th><th>Publication Title</th><th>Domain</th><th>Student Id</th><th>Team Members</th></tr>");
			while(rs.next()) {
				String pubname = rs.getString("publication_name");
				String pubtitle= rs.getString("publication_title");
				String pdomain = rs.getString("domain");
				int sid = rs.getInt("s_id");
				String teammembers= rs.getString("team_members");
				 out.println("<tr><td>" + pubname + "</td><td>" + pubtitle + "</td><td>" + pdomain+ "</td><td>"+ sid+"</td><td>"+ teammembers+"</td></tr>");
			}
			out.println("</table>");	
			// Close all the connections
			//st.close();
			con.close();

			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
