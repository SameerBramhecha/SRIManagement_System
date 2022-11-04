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
			if (option == 1) {
				st = con.prepareStatement("select * from student;");
				rs = st.executeQuery();
				out.println("<table class='tb' border=1 width=50% height=50%>");
				out.println("<tr>\r\n"
						+ "<td>Student Id</td>\r\n"
						+ "<td>Student Name</td>\r\n"
						+ "<td>CGPA</td>\r\n"
						+ "<td>Branch</td>\r\n"
						+ "<td>Division</td>\r\n"
						+ "<td>Internship</td>\r\n"
						+ "<td>Project Id</td>\r\n"
						+ "<td>Domain</td>\r\n"
						+ "<td>Clubname</td>\r\n"
						+ "<td>Registration Number</td>\r\n"
						+ "<td>Research Paper</td>\r\n"
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
				out.println("<table class='tb' border=1 width=50% height=50%>");
				out.println(
						"<tr><th>Publication Name</th><th>Publication Title</th><th>Domain</th><th>Student Id</th><th>Team Members</th></tr>");
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
				out.println("<table class='tb' border=1 width=50% height=50%>");
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
				out.println("<table class='tb' border=1 width=50% height=50%>");
				out.println(
						"<tr><th>Project_Id</th><th>Title</th><th>Mentor</th><th>Project Description</th><th>Student_id</th></tr>");
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

}
