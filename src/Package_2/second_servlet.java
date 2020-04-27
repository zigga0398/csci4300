package Package_2;


import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class second_servlet
 */
@WebServlet("/second_servlet")
public class second_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public second_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		out.println("<title>JDBC-Servlet Example</title>");
		out.println("</head><body><h1>JDBC Connection example</h1>");
	   // System.out.println("here");
	    String name_1 = request.getParameter("s_name");
	    String name_2 = request.getParameter("e_name");
	    String name_3 = request.getParameter("c_name");
	    String [] name_4 = request.getParameterValues("continent");
	    String [] str4 = {"*","*","*","*","*"};
	    for (int i = 0; i < name_4.length; i++)
	        str4[i] = name_4[i];
	    String str1 = "%";
	    String str2 = "%";
	    String str3 = "%";
	    if (name_1 != "") str1 = name_1 + str1;
	    if (name_2 != "") str2 = str2 + name_2;
	    if (name_3 != "") str3 = str3 + name_3 + str3;

	    String str5 = request.getParameter("city_pop");
	    int pop = Integer.parseInt(str5);
	    if (pop == 5) pop = 50000;
	    else if (pop == 50) pop = 500000;
	    else if (pop == 500) pop = 5000000;
	    else pop = Integer.MAX_VALUE;

	    String dbURL = "jdbc:mysql://localhost/world";
	  String query = "SELECT country.name, city.name FROM city, country "
	  + " WHERE country.code = city.countrycode and " + 
	  " (city.name like ? and city.name like ? and city.name like ?) "+
	  " and (continent = ? or continent = ? or continent = ? or continent = ? or continent = ? ) "+ 
	  " and city.population < ?";

	  try {
	    Connection connection = DriverManager.getConnection(dbURL, "root", "3rn!3B4ll");
	  //  System.out.println("here");
	    PreparedStatement pstmt = connection.prepareStatement( query );
	    pstmt.setString(1, str1);
	    pstmt.setString(2, str2);
	    pstmt.setString(3, str3);
	    for (int i = 0 ; i < str4.length; i++)
	        pstmt.setString(i+4 , str4[i]);
	    pstmt.setInt(9, pop);
	    out.println(pstmt);
	    ResultSet rs = pstmt.executeQuery( );

	    out.println("<table border='2'> <th>Country Name</th> <th>City Name</th>");
		while(rs.next()){
	        out.println("<tr>");
			out.println("<td>"+ rs.getString(1) +  "</td><td>" + rs.getString(2)+"</td>");
	        out.println("</tr>");

	  }
		connection.close();  
	  out.println("</table>");

	  }
	  catch(SQLException e) {
	      e.printStackTrace();
	  }
	  out.println("</body></html>");
	  out.close();	
		
	}

}
