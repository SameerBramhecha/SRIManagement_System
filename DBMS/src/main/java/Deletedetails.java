import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Deletedetails")
public class Deletedetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
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
			RequestDispatcher rd = null;
			System.out.println(option);
			
			if(option==1) {
				rd = request.getRequestDispatcher("studdelete.html");
				rd.forward(request, response);
				
			}
			else if(option == 2) {
				rd = request.getRequestDispatcher("projectdelete.html");
				rd.forward(request, response);
			}
//			printh(out);
//			printb(out);
//			out.println("</html>");
			// Close all the connections
			// st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void project(HttpServletRequest request,
			HttpServletResponse response, PrintWriter out, Connection con)
					throws Exception{
		int pid = Integer.parseInt(request.getParameter("project_id"));
		PreparedStatement st = con
				.prepareStatement("delete from project where project_id="+pid+";");
		int result = st.executeUpdate();
		if (result != 0) {
			out.println("<html><body><b>Successfully Deleted"
					+ "</b></body></html>");
		} else {
			out.println("<html><body><b>Not Deleted"
					+ "</b></body></html>");
		}
	}
	
	
	public void student(HttpServletRequest request,
			HttpServletResponse response, PrintWriter out, Connection con) 
					throws Exception{
		
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
