import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Projectinput")
public class Projectinput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			PrintWriter out = response.getWriter();
			// Initialize the database
			Connection con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			// demo table consists of two columns, so two '?' is used
			int pid = Integer.parseInt(request.getParameter("project_id"));
			String title = request.getParameter("title");
			String mentor = request.getParameter("mentor");
			String description = request.getParameter("description");
			int sid = Integer.parseInt(request.getParameter("s_id"));
			String domain  =request.getParameter("domain");
			// System.out.println(pid);
			// System.out.println(title);
			// System.out.println(mentor);
			// System.out.println(description);
			// System.out.println(sid);
			PreparedStatement st = con
					.prepareStatement("call insertintoprojects(" + pid + ",'" + title + "','" + mentor + "','" +
							description + "'," + sid + ");");
			int result = st.executeUpdate();
			
			PreparedStatement st1 = con.prepareStatement("insert into project_domain values ("+pid+",'"+domain+"');");
			if (result != 0) {
				out.println("<html><body><b>Successfully Inserted"
						+ "</b></body></html>");
			} else {
				out.println("<html><body><b>Not Inserted"
						+ "</b></body></html>");
			}
			// Close all the connections
			// st.close();

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}