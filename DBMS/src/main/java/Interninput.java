import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Interninput")
public class Interninput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			PrintWriter out = response.getWriter();
			// Initialize the database
			Connection con = DatabaseConnection.initializeDatabase();

			// Create a SQL query to insert data into demo table
			// demo table consists of two columns, so two '?' is used
			String cmpname = null;
			int intyear = 0;
			String imonth = null;
			int stp = 0;
			cmpname = request.getParameter("compname");
			intyear = Integer.parseInt(request.getParameter("iyear"));
			imonth = request.getParameter("imonth");
			stp = Integer.parseInt(request.getParameter("stipend"));

			System.out.println(cmpname);
			System.out.println(intyear);
			System.out.println(imonth);
			System.out.println(stp);

			PreparedStatement st = con.prepareStatement("call insertintointernship('" +
					cmpname + "'," + stp + "," + intyear + ",'" + imonth + "');");
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