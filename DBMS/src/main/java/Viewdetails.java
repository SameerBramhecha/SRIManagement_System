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

@WebServlet("/Viewdetails")
public class Viewdetails extends HttpServlet {
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
			int option = 0;
			try {
				option = Integer.parseInt(request.getParameter("opt"));
			} catch (Exception e) {

			}
			System.out.println(option);
			ResultSet rs = null;
			printh(out);
			printb(out);
			if (option == 1) {
				st = con.prepareStatement("select * from student;");
				rs = st.executeQuery();
				out.println("<table>");
				out.println("<tr>"
						+ "<td>Student ID</td>"
						+ "<td>Student Name</td>"
						+ "<td>CGPA</td>"
						+ "<td>Branch</td>"
						+ "<td>Division</td>"
						+ "<td>Internship</td>"
						+ "<td>Project ID</td>"
						+ "<td>Domain</td>"
						+ "<td>Clubname</td>"
						+ "<td>Registration Number</td>"
						+ "<td>Research Paper</td>"
						+ "</tr>");
				while (rs.next()) {
					int sid = rs.getInt("s_id");
					String sname = rs.getString("sname");
					double cgpa = rs.getDouble("cgpa");
					String branch = rs.getString("branch");
					String div = rs.getString("division");
					String internship = rs.getString("internship");
					int projectid = rs.getInt("project_id");
					String domain = rs.getString("domain");
					String clbname = rs.getString("clubname");
					String regno = rs.getString("registration_no");
					String rp = rs.getString("researchpaper");
					out.println("<tr><td>" + sid +
							"</td><td>" + sname +
							"</td><td>" + cgpa +
							"</td><td>" + branch +
							"</td><td>" + div +
							"</td><td>" + internship +
							"</td><td>" + projectid +
							"</td><td>" + domain +
							"</td><td>" + clbname +
							"</td><td>" + regno +
							"</td><td>" + rp +
							"</td></tr>");
				}
				out.println("</table>");
			} else if (option == 2) {
				st = con.prepareStatement("select * from researchpapereach;");
				rs = st.executeQuery();
				out.println("<table>");
				out.println(
						"<tr><th>Publication Name</th><th>Publication Title</th><th>Domain</th><th>Student ID</th><th>Team Members</th></tr>");
				while (rs.next()) {
					String pubname = rs.getString("publication_name");
					String pubtitle = rs.getString("publication_title");
					String pdomain = rs.getString("domain");
					int sid = rs.getInt("s_id");
					String teammembers = rs.getString("team_members");
					out.println("<tr><td>" + pubname + "</td><td>" + pubtitle + "</td><td>" + pdomain + "</td><td>"
							+ sid + "</td><td>" + teammembers + "</td></tr>");
				}
				out.println("</table>");
			} else if (option == 3) {
				st = con.prepareStatement("select * from internship;");
				rs = st.executeQuery();
				out.println("<table>");
				out.println(
						"<tr><th>Company Name</th><th>Stipend</th><th>Internship Year</th><th>Internship Month</th></tr>");

				while (rs.next()) {
					String compname = rs.getString("companyname");
					long stipend = rs.getLong("stipend");
					int i_year = rs.getInt("i_year");
					String i_month = rs.getString("i_month");
					out.println("<tr><td>" + compname + "</td><td>" + stipend + "</td><td>" + i_year + "</td><td>"
							+ i_month + "</td></tr>");
				}

				out.println("</table>");
			} else if (option == 4) {
				st = con.prepareStatement("select * from projects;");
				rs = st.executeQuery();
				out.println("<table>");
				out.println(
						"<tr><th>Project ID</th><th>Title</th><th>Mentor</th><th>Project Description</th><th>Student ID</th></tr>");
				while (rs.next()) {
					int pid = rs.getInt("project_id");
					String title = rs.getString("title");
					String mentor = rs.getString("mentor");
					String prodesc = rs.getString("description_of_project");
					int sid = rs.getInt("s_id");
					out.println("<tr><td>" + pid + "</td><td>" + title + "</td><td>" + mentor + "</td><td>" + prodesc
							+ "</td><td>" + sid + "</td></tr>");
				}
				out.println("</table>");
			}
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
		out.println("<title>DETAILS</title>");
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
				+ "      <a class=\"nav-link\" href=\"Projects.html\">PROJECT</a>\r\n"
				+ "    </li>\r\n"
				+ "    \r\n"
				+ "    <li class=\"nav-item\">\r\n"
				+ "      <a class=\"nav-link\"  href=\"internship.html\">INTERNSHIP</a>\r\n"
				+ "    </li>\r\n"
				+ "    \r\n"
				+ "    <li class=\"nav-item dropdown\">\r\n"
				+ "      <a class=\"nav-link dropdown-toggle active\" data-bs-toggle=\"dropdown\" href=\"#\">LOGIN</a>\r\n"
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
				+ "font-size: 20px;"
				+ "text-align: center;"
				+ "margin-left: auto;"
				+ "margin-right: auto;"
				+ "width: 80%;"
				+ "rules: all;"
				+ "border-spacing: 0 10px;"
				+ "}");
		out.println("</style>");
	}
}
