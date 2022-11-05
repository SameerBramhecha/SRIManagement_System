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

			
			printh(out);
			printb(out);
			
			out.println("<table>");
			out.println(
					"<tr><th>Project ID</th><th>Title</th><th>Mentor</th><th>Project Description</th><th>Student ID</th><th>Domain</th></tr>");
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
			out.println("</html>");
			// Close all the connections
			// st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void printh(PrintWriter out) {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		style(out);
		out.println("<title>PROJECTS</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<link rel=\"stylesheet\" href=\"internship.css\">");
		out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js\"></script>");
		out.println("</head>");
	}
	public void printb(PrintWriter out) {
		out.println("<body class = \"body\">\r\n"
				+ "<div class=\"home\">\r\n"
				+ "<div class=\"container mt-3\" >\r\n"
				+ "  <h5>Society for Computer Technology and Research's</h5>\r\n"
				+ "  <h2>PUNE INSTITUTE OF COMPUTER TECHNOLOGY</h2>\r\n"
				+ "  <h6>AFFILIATED TO SPPU | AICTE APPROVED | ISO 9001:2015</h6> \r\n"
				+ "  <ul class=\"nav nav-tabs\">\r\n"
				+ "  \r\n"
				+ "    <li class=\"nav-item dropdown\">\r\n"
				+ "      <a class=\"nav-link dropdown-toggle\" href=\"home.html\">HOME</a>\r\n"
				+ "	      <ul class=\"dropdown-menu\">\r\n"
				+ "	        <li><a class=\"dropdown-item\" href=\"trial.html\">About</a></li>\r\n"
				+ "	        <li><a class=\"dropdown-item\" href=\"trial.html\">Details</a></li>\r\n"
				+ "	        <li><a class=\"dropdown-item\" href=\"trial.html\">Creators</a></li>\r\n"
				+ "	      	<li><a class=\"dropdown-item\" href=\"trial.html\">Feedback</a></li>\r\n"
				+ "	      </ul>\r\n"
				+ "    </li>\r\n"
				+ "    \r\n"
				+ "    <li class=\"nav-item\">\r\n"
				+ "      <a class=\"nav-link\" href=\"Research.html\">RESEARCH</a>\r\n"
				+ "    </li>\r\n"
				+ "    \r\n"
				+ "    <li class=\"nav-item\">\r\n"
				+ "      <a class=\"nav-link active\" href=\"Projects.html\">PROJECT</a>\r\n"
				+ "    </li>\r\n"
				+ "    \r\n"
				+ "    <li class=\"nav-item\">\r\n"
				+ "      <a class=\"nav-link\"  href=\"internship.html\">INTERNSHIP</a>\r\n"
				+ "    </li>\r\n"
				+ "    \r\n"
				+ "    <li class=\"nav-item dropdown\">\r\n"
				+ "      <a class=\"nav-link dropdown-toggle\" data-bs-toggle=\"dropdown\" href=\"#\">LOGIN</a>\r\n"
				+ "      <ul class=\"dropdown-menu\">\r\n"
				+ "        <li><a class=\"dropdown-item\" href=\"signup.html\">Register</a></li>\r\n"
				+ "	<li><a class=\"dropdown-item\" href=\"signIn.html\">Student Login</a></li>\r\n"
				+ "        <li><a class=\"dropdown-item\" href=\"Adminlogin.html\">Admin Login</a></li>\r\n"
				+ "      </ul>\r\n"
				+ "    </li>\r\n"
				+ "  </ul>\r\n"
				+ "</div>\r\n"
				+ "</div>"
				+"<br><br>");
	}
	public void style(PrintWriter out) {
		out.println("<style>");
		out.println("table{"
				+ "	font-size : 20px;"
				+ "text-align: center;"
				+ "margin-left:auto;"
				+ "margin-right:auto;"
				+ "width:80%;"
				+ "rules:all;"
				+ "border-spacing: 0 10px;"
				+ "}");
		out.println("</style>");
	}

}
