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

@WebServlet("/Internship")
public class Internship extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			PrintWriter out = response.getWriter();
			// Initialize the database
			Connection con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			String cmpname = null;
			int intyear = 0;
			String imonth = null;
			int stp = 0;
			try {
				cmpname = request.getParameter("compname");

			} catch (Exception e) {
				intyear = Integer.parseInt(request.getParameter("iyear"));
			}
			try {
				intyear = Integer.parseInt(request.getParameter("iyear"));
			} catch (Exception e) {

			}
			try {
				imonth = request.getParameter("imonth");
			} catch (Exception e) {

			}
			try {
				stp = Integer.parseInt(request.getParameter("stp1"));
			} catch (Exception e) {

			}

			System.out.println(cmpname);
			System.out.println(intyear);
			System.out.println(imonth);
			System.out.println(stp);

			String s = "";
			if (stp == 1) {
				s = "=0";
			} else if (stp == 2) {
				s = "<10000";
			} else if (stp == 3) {
				s = ">=10000 and stipend<25000";
			} else if (stp == 4) {
				s = ">=25000 and stipend<50000";
			} else if (stp == 5) {
				s = ">=50000 and stipend<75000";
			} else if (stp == 6) {
				s = ">=75000 and stipend<100000";
			} else if (stp == 7) {
				s = ">=100000";
			}

			PreparedStatement st = null;
			ResultSet rs = null;
			if (cmpname.equals("") && intyear == 0 && imonth.equals("") && stp == 0) {
				// 1
				st = con.prepareStatement("select * from internship;");
				rs = st.executeQuery();
			} else if (cmpname != null && ((intyear == 0) && imonth.equals("") && stp == 0)) {
				// 2
				st = con.prepareStatement("select * from internship where companyname = '" + cmpname + "';");
				rs = st.executeQuery();
			} else if (cmpname.equals("") && intyear != 0 && imonth.equals("") && stp == 0) {
				// 3
				st = con.prepareStatement("select * from internship where i_year = " + intyear + ";");
				rs = st.executeQuery();
			} else if (cmpname.equals("") && ((intyear == 0) && imonth != null && stp == 0)) {
				// 4
				st = con.prepareStatement("select * from internship where i_month = '" + imonth + "';");
				rs = st.executeQuery();
			} else if (cmpname.equals("") && ((intyear == 0) && imonth.equals("") && stp != 0)) {
				// 5
				st = con.prepareStatement("select * from internship where stipend" + s + ";");
				rs = st.executeQuery();
			} else if (cmpname != null && ((intyear != 0) && imonth.equals("") && stp == 0)) {
				// 6
				st = con.prepareStatement(
						"select * from internship where companyname= '" + cmpname + "' and i_year = " + intyear + ";");
				rs = st.executeQuery();
			} else if (cmpname.equals("") && ((intyear != 0) && imonth != null && stp == 0)) {
				// 7
				st = con.prepareStatement(
						"select * from internship where i_year = " + intyear + " and i_month ='" + imonth + "';");
				rs = st.executeQuery();
			} else if (cmpname.equals("") && ((intyear == 0) && imonth != null && stp != 0)) {
				// 8
				st = con.prepareStatement(
						"select * from internship where i_month ='" + imonth + "' and stipend" + s + ";");
				rs = st.executeQuery();
			} else if (cmpname != null && intyear == 0 && imonth != null && stp == 0) {
				// 9
				st = con.prepareStatement(
						"select * from internship where companyname= '" + cmpname + "' and i_month ='" + imonth + "';");
				rs = st.executeQuery();
			} else if (cmpname != null && intyear == 0 && imonth.equals("") && stp != 0) {
				// 10
				st = con.prepareStatement(
						"select * from internship where companyname= '" + cmpname + "' and stipend" + s + ";");
				rs = st.executeQuery();
			} else if (cmpname.equals("") && intyear != 0 && imonth.equals("") && stp != 0) {
				// 11
				st = con.prepareStatement(
						"select * from internship where i_year = " + intyear + " and stipend" + s + ";");
				rs = st.executeQuery();
			} else if (cmpname != null && intyear != 0 && imonth != null && stp == 0) {
				// 12
				st = con.prepareStatement("select * from internship where companyname= '" + cmpname + "' and i_year = '"
						+ intyear + "' and i_month ='" + imonth + "';");
				rs = st.executeQuery();
			} else if (cmpname.equals("") && intyear != 0 && imonth != null && stp != 0) {
				// 13
				st = con.prepareStatement("select * from internship where i_year = '" + intyear + "' and i_month ='"
						+ imonth + "' and stipend" + s + ";");
				rs = st.executeQuery();
			} else if (cmpname != null && intyear != 0 && imonth.equals("") && stp != 0) {
				// 14
				st = con.prepareStatement("select * from internship where companyname= '" + cmpname + "' and i_year = '"
						+ intyear + "' and stipend" + s + ";");
				rs = st.executeQuery();
			} else if (cmpname != null && intyear == 0 && imonth != null && stp != 0) {
				// 15
				st = con.prepareStatement("select * from internship where companyname= '" + cmpname + "' and i_month ='"
						+ imonth + "' and stipend" + s + ";");
				rs = st.executeQuery();
			} else if (cmpname != null && intyear != 0 && imonth != null && stp != 0) {
				// 16
				st = con.prepareStatement("select * from internship where companyname= '" + cmpname + "' and i_year = "
						+ intyear + " and i_month ='" + imonth + "' and stipend" + s + ";");
				rs = st.executeQuery();
			}
			
			
			
			printh(out);
			printb(out);
			out.println("<table>");
			out.println(
					"<tr><th>Company Name</th><th>Stipend</th><th>Internship Year</th><th>Internship Month</th></tr>");

			while (rs.next()) {
				String compname = rs.getString("companyname");
				long stipend = rs.getLong("stipend");
				int i_year = rs.getInt("i_year");
				String i_month = rs.getString("i_month");
				out.println("<tr><td>" + compname + "</td><td>" + stipend + "</td><td>" + i_year + "</td><td>" + i_month
						+ "</td></tr>");
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
		out.println("<title>INTERNSHIP</title>");
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
				+ "      <a class=\"nav-link active\"  href=\"#\">INTERNSHIP</a>\r\n"
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
		out.println("table{s"
				+ "	font-size : 30px;"
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

/*
 * Search All -> 1
 * Compname -> 1
 * Iyear -> 1
 * imonth ->0
 * stipend->0
 * 1,2->1
 * 2,3->1
 * //3,4->
 * 1,3->1
 * 1,4->1
 * 2,4->1
 * 1,2,3->1
 * 2,3,4->1
 * 1,3,4->1
 * 1,2,4->1
 * 1,2,3,4->1
 */