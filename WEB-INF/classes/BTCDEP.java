


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StillWaiting
 */
public class BTCDEP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BTCDEP() {
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
	      String email=(String)session.getAttribute("username");
	      String password=(String)session.getAttribute("password");
	      String user_idS=(String)session.getAttribute("user_id");	  	    
	      int user_id=Integer.parseInt(user_idS);	
	      double BTC = Double.parseDouble(request.getParameter("BTC"));
		  //String email = request.getParameter("email");
		//  String password = request.getParameter("password");
	      Connection conn=null;
	      Statement stmt=null;
	      PrintWriter out = response.getWriter();
	      ResultSet rs = null;
	      final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL="jdbc:mysql://3.16.62.130/cl";

	      //  Database credentials
	       final String USER = "cl";
	       final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
	       request.getRequestDispatcher("includes/header.jsp").include(request, response);
	       out.println("<br/><br/><br/><br/><br/><br/><br/><br/><br/><center>");
	      try {
	         // Register JDBC driver
	          Class.forName("com.mysql.jdbc.Driver");

	         // Open a connection
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         stmt = conn.createStatement();
	        String sql = "SELECT * FROM users where email = '"+email+"' ;" ;
	        rs = stmt.executeQuery(sql);
	         if(rs.next()){
	        	 String btc_address = rs.getString("btc_address");
	        	 out.println("<h3>Send "+BTC+" BTC to this address: <br/>"+btc_address+"</h3>");
	         }
            rs.close();
            
            sql = "UPDATE users " +
					"SET PendingBTCDep = "+BTC+"  where email = '"+email+"' ;" ;
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
	      }catch(Exception e){
	    	  out.println(e);
	      }
	      out.println("</center><br/><br/><br/><br/><br/>");
	      request.getRequestDispatcher("includes/footer.jsp").include(request, response);
	}

}








