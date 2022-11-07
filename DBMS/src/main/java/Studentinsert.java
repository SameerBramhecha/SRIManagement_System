import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Studentinsert")
public class Studentinsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			PrintWriter out = response.getWriter();
			// Initialize the database
			Connection con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			// demo table consists of two columns, so two '?' is used
			
			int sid = Integer.parseInt(request.getParameter("sid"));
			String sname = request.getParameter("sname");
			double cgpa = Double.parseDouble(request.getParameter("cgpa"));
			String branch = request.getParameter("branch");
			String division = request.getParameter("division");
			String intership = request.getParameter("internship");
			int projectid = Integer.parseInt(request.getParameter("projectid"));
			String domain = request.getParameter("domain");
			String clubname = request.getParameter("clubname");
			String registration_id = request.getParameter("registration id");
			String researchpaper = request.getParameter("researchpaper");
			
			PreparedStatement st = con
					.prepareStatement("call insertintostudent("+sid+",'"
																+sname+"',"
																+cgpa+",'"
																+branch+"','"+
																division+"','"+
																intership+"',"+
																projectid+",'"+
																domain+"','"+
																clubname+"','"+
																registration_id+"','"+
																researchpaper+"');");
			int result = st.executeUpdate();
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