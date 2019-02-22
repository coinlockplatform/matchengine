


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CancelSale
 */
public class CancelSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelSale() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	      String auth=(String)session.getAttribute("auth");  
	      if(auth==null||!auth.equals("1")){
	    	  
	    	  session.invalidate();//destroy any session that they may have
	    	 response.sendRedirect(request.getContextPath() + "/index.jsp");
	    	//  out.println("redirect "+request.getContextPath() + "/login2.jsp");
	    	 return;
	      }
	      String userName=(String)session.getAttribute("username");
	      String password=(String)session.getAttribute("password");
	      String user_idS=(String)session.getAttribute("user_id");	  	    
	      int user_id=Integer.parseInt(user_idS);	
	//	double sellLockAmount = Double.parseDouble(request.getParameter("sellLockAmount"));
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL="jdbc:mysql://3.16.62.130/cl";

		//  Database credentials
		final String USER = "cl";
		final String PASS = "Ra.cQH&&ZrFG(44e)Uf";

		// Set response content type
		response.setContentType("text/html");
		
		request.getRequestDispatcher("includes/header.jsp").include(request, response);
		String title = "Sell Order Being Canceled";
		 out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><h1 align = \"center\">" + title + "</h1>\n"+
	 	             "<h3 align = \"center\">"+ userName + "</h3>\n");
		String sql = "Delete from sellersList where email='"+userName+"' ;";
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
		    stmt.executeUpdate(sql);
		    
		    out.println("<h1 style='text-align:center' >Lock Coin Sale Canceled</h1>");
		}catch(Exception e) {
			out.println(e);
		}
		
         //out.println("<h4>Buy Lock Coin</h4>");	    	         
		 out.println("<div style='text-align: center;'>");
		 out.println("<form method='POST' action='dashboard.jsp' >");
		  out.println("<input type='submit' value='RETURN TO HOME' class='btn btn-hero'>");   	            
	      out.println("</form></div>");

		request.getRequestDispatcher("includes/footer.jsp").include(request, response);
	}

}
