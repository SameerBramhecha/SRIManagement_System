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
public class Internship extends HttpServlet{
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
			int intyear =0 ;
			String imonth = null;
			int stp = 0;
			try {
				cmpname = request.getParameter("compname");
				intyear = Integer.parseInt(request.getParameter("iyear"));
				imonth = request.getParameter("imonth");
				stp = Integer.parseInt(request.getParameter("stp"));
			}
			catch(Exception e) {
				
			}
			
			
			System.out.println(cmpname);
			System.out.println(intyear);
			System.out.println(imonth);
			System.out.println(stp);
			String s = null;
			if(stp == 1) {
				s = "<10000;";
			}
			else if(stp==2) {
				s = ">=10000 and stipend<25000;";
			}
			else if(stp==3) {
				s = ">=25000 and stipend<50000;";
			}
			else if(stp==4) {
				s = ">=50000 and stipend<75000;";
			}
			else if(stp==5) {
				s = ">=75000 and stipend<100000;";
			}
			else if(stp==6) {
				s = ">=100000;";
			}
			
			PreparedStatement st = null;	
			ResultSet rs = null;
			if(cmpname.equals("") && intyear==0 && imonth==null && stp == 0)
			{
				//1
				st = con.prepareStatement("select * from internship;");
				rs = st.executeQuery();
			}
			else if(cmpname!=null && ((intyear==0)&& imonth==null&& stp == 0)) {
				//2
				st = con.prepareStatement("select * from internship where companyname = '" + cmpname+"';");
				rs = st.executeQuery();
			}
			else if(cmpname.equals("") && intyear!=0 && imonth.equals("") && stp == 0) {
				//3
				st = con.prepareStatement("select * from internship where i_year = "+intyear+";");
				rs = st.executeQuery();
			}
			else if(cmpname.equals("") && ((intyear==0)&& imonth!=null && stp == 0)) {
				//4
				st = con.prepareStatement("select * from internship where i_month = '" + imonth +"';");
				rs = st.executeQuery();
			}
			else if(cmpname.equals("") && ((intyear==0)&& imonth==null && stp == 0)) {
				//5
				st = con.prepareStatement("select * from internship where stipend"+ s+";");
				rs = st.executeQuery();
			}
			else if(cmpname!=null && ((intyear!=0)&& imonth.equals("")&& stp == 0)) {
				//6
				st = con.prepareStatement("select * from internship where companyname= '"+ cmpname +"' and i_year = "+intyear+";");
				rs = st.executeQuery();
			}
			else if(cmpname.equals("") && ((intyear!=0)&& imonth!=null && stp == 0)) {
				//7
				st = con.prepareStatement("select * from internship where i_year = "+intyear+" and i_month ='"+ imonth+"';");
				rs = st.executeQuery();
			}
//			else if(cmpname.equals("") && ((intyear==0)&& imonth!=null && stp != 0)) {
//				//8
//				st = con.prepareStatement("select * from internship where i_month ='"+ imonth+"' and stipend"+ s+";");
//				rs = st.executeQuery();
//			}
			else if(cmpname != null && intyear ==0 && imonth !=null && stp ==0)
			{
				//9
				st = con.prepareStatement("select * from internship where companyname= '"+ cmpname+"' and i_month ='"+ imonth+"';");
				rs = st.executeQuery();
			}
			else if(cmpname != null && intyear ==0 && imonth==null && stp !=0)
			{
				//10
				st = con.prepareStatement("select * from internship where companyname= '"+ cmpname+"' and stipend"+ s+";");
				rs = st.executeQuery();
			}
			else if(cmpname.equals("") && intyear !=0 && imonth.equals("") && stp !=0)
			{
				//11
				st = con.prepareStatement("select * from internship where i_year = "+intyear+" and stipend"+ s+";");
				rs = st.executeQuery();
			}
			else if(cmpname != null && intyear !=0 && imonth !=null && stp ==0)
			{
				//12
				st = con.prepareStatement("select * from internship where companyname= '"+ cmpname +"' and i_year = '"+intyear+"' and i_month ='"+ imonth+"';");
				rs = st.executeQuery();
			}
			else if(cmpname.equals("") && intyear !=0 && imonth !=null && stp !=0)
			{
				//13
				st = con.prepareStatement("select * from internship where i_year = '"+intyear+"' and i_month ='"+ imonth+"' and stipend"+ s+";");
				rs = st.executeQuery();
			}
			else if(cmpname != null && intyear !=0 && imonth.equals("")&& stp !=0)
			{
				//14
				st = con.prepareStatement("select * from internship where companyname= '"+ cmpname +"' and i_year = '"+intyear+"' and stipend"+ s+";");
				rs = st.executeQuery();
			}
			else if(cmpname != null && intyear ==0 && imonth !=null && stp !=0)
			{
				//15
				st = con.prepareStatement("select * from internship where companyname= '"+ cmpname +"' and i_month ='"+ imonth+"' and stipend"+ s+";");
				rs = st.executeQuery();
			}
			else if(cmpname != null && intyear !=0 && imonth !=null && stp !=0)
			{
				//16
				st = con.prepareStatement("select * from internship where companyname= '"+ cmpname +"' and i_year = "+intyear+" and i_month ='"+ imonth+"' and stipend"+ s+";");
				rs = st.executeQuery();
			}
			out.println("<table class='tb' border=1 width=50% height=50%>");
			out.println("<tr><th>Company Name</th><th>Stipend</th><th>Internship Year</th><th>Internship Month</th></tr>");
			
			while(rs.next()) {
				String compname = rs.getString("companyname");
				long stipend = rs.getLong("stipend");
				int i_year = rs.getInt("i_year");
				String i_month = rs.getString("i_month");
				 out.println("<tr><td>" + compname + "</td><td>" + stipend + "</td><td>" + i_year + "</td><td>"+ i_month+"</td></tr>");
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


/*
 * Search All -> 1
 * Compname -> 1
 * Iyear -> 1
 * imonth ->0
 * stipend->0
 * 1,2->1
 * 2,3->1
 *  //3,4->
 * 1,3->1
 * 1,4->1
 * 2,4->1
 * 1,2,3->1
 * 2,3,4->1
 * 1,3,4->1
 * 1,2,4->1
 * 1,2,3,4->1
 * */