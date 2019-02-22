

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Refer
 */
public class Refer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Refer() {
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
		Connection conn=null;
	      Statement stmt=null;
	      PrintWriter out = response.getWriter();

	      String referer_email = request.getParameter("referer_email");
    	      // JDBC driver name and database URL
    	       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    	       final String DB_URL="jdbc:mysql://3.16.62.130/cl";

    	      //  Database credentials
    	       final String USER = "cl";
    	       final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
    		
	      try {
	         // Register JDBC driver
	          Class.forName("com.mysql.jdbc.Driver");

	         // Open a connection
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);

	         // Execute SQL query
	         stmt = conn.createStatement();
	         String sql;
	         sql = "SELECT * FROM users where email = '"+ referer_email + "' ;" ;
	         ResultSet rs = stmt.executeQuery(sql);
	         String javaCalcLogic="";
	         // Extract data from result set
	         
	         boolean match=false;
	       
	         if(rs.next()){
	            //Retrieve by column name
	         //   user_id  = rs.getInt("user_id");
	            //int age = rs.getInt("age");
	          //  String email = rs.getString("email");	           
	            match=true;	            
	         } 
	         else{
	        	 out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
	        	 out.println(" <h2 style='text-align: center;'>Referer email not found</h2>");
				 
				 out.println("<div style='text-align: center;'>");
				 out.println("<form method='POST' action='dashboard.jsp' >");
				  out.println("<input type='submit' value='RETURN TO HOME' class='btn btn-hero'>");   	            
			      out.println("</form></div>");
	         }
	         rs.close();
	         if(match==true){
	        	 
	        	 try{
	 				sql = "INSERT INTO referals ( ref_id, email, refered_email, date_refered ) VALUES ( " +
	 						0+", '"+userName+"', '"+ referer_email +"', NULL );"; 
	 				stmt.executeUpdate(sql);
	 				stmt.close();
	 				conn.close();
	 				out.println("<br/><center><h3>Referal Made</h3></center><br/>");
	 				out.println("<form method='POST' action='Hello2' >");
	        		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
	        		 out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
	        		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
	        		 out.println("<input type='submit' value='RETURN TO HOME'>");   	            
	        		 out.println("</form>");
	 				}catch(Exception se){
	 					out.println("insert "+se );
	 					//out.println(se);
	 				} 
	         }
	         
	      } catch(SQLException se) {
 	         //Handle errors for JDBC
 	         se.printStackTrace();
 	      } catch(Exception e) {
 	         //Handle errors for Class.forName
 	         e.printStackTrace();
 	      } finally {
 	         //finally block used to close resources
 	         try {
 	            if(stmt!=null)
 	               stmt.close();
 	         } catch(SQLException se2) {
 	         } // nothing we can do
 	         try {
 	            if(conn!=null)
 	            conn.close();
 	         } catch(SQLException se) {
 	            se.printStackTrace();
 	         } //end finally try
 	      } //end try
	            
	            
	}

}
