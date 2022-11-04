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

@WebServlet("/Projects")
public class Projects extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
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
			} catch (Exception e) {

			}
			System.out.println(domain);
			ResultSet rs = null;
			if (domain.equals("")) {
				st = con.prepareStatement(
						"select * from projects p inner join project_domain pd on p.project_id = pd.project_id ;");
				rs = st.executeQuery();
			} else {
				st = con.prepareStatement("select * from projects p inner join project_domain pd on"
						+ " p.project_id=pd.project_id where pd.domain = '" + domain + "';");
				rs = st.executeQuery();
			}

			out.println("<table class='tb' border=1 width=50% height=50%>");
			out.println(
					"<tr><th>Project_Id</th><th>Title</th><th>Mentor</th><th>Project Description</th><th>Student_id</th><th>Domain</th></tr>");
			while (rs.next()) {
				int pid = rs.getInt("project_id");
				String title = rs.getString("title");
				String mentor = rs.getString("mentor");
				String prodesc = rs.getString("description_of_project");
				int sid = rs.getInt("s_id");
				String prodomain = rs.getString("domain");
				out.println("<tr><td>" + pid + "</td><td>" + title + "</td><td>" + mentor + "</td><td>" + prodesc
						+ "</td><td>" + sid + "</td><td>" + prodomain + "</td></tr>");
			}
			out.println("</table>");
			// Close all the connections
			// st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
