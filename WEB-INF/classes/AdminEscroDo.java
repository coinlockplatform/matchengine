

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminEscro
 */
public class AdminEscroDo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEscroDo() {
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
		 PrintWriter out = response.getWriter();
		 out.println("hello");
		/*	      String userName = request.getParameter("username");
	      String password = request.getParameter("password");
  	      // JDBC driver name and database URL
  	       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  	       final String DB_URL="jdbc:mysql://54.214.194.247/cl";

  	      //  Database credentials
  	       final String USER = "cl";
  	       final String PASS = "95ODVqCd8T7mfivU";
	    //	    request.getRequestDispatcher("includes/header.jsp").include(request, response);
  	      // Set response content type
  	 //     response.setContentType("text/html");
  	      PrintWriter out = response.getWriter();
  	      String title = "Lock Coin User";
//  	      request.getRequestDispatcher("includes/header.jsp").include(request, response);

*//*  	      String docType =
  	         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
  	      String style="<style>  .fixed_header { display:block; height: 300px; overflow-y: scroll;} .fixed_header tbody{  display:block;  overflow:auto;  height:20px;  width:100%;} .fixed_header thead tr{  display:block;} </style>";
  	      Connection conn=null;
  	      Statement stmt=null;
	      try {
 	         // Register JDBC driver
 	          Class.forName("com.mysql.jdbc.Driver");

 	         // Open a connection
 	         conn = DriverManager.getConnection(DB_URL, USER, PASS);

 	         // Execute SQL query
 	         stmt = conn.createStatement();
 	         String sql;
 	         sql = "SELECT * FROM adminList where user_name = '"+ userName + "' and user_password = '" + password +"' ;" ;
 	         ResultSet rs = stmt.executeQuery(sql);
 	         String javaCalcLogic="";
 	         // Extract data from result set
 	         if(rs.next()){
 	        	 rs.close();
 	 	         sql = "SELECT * FROM escrow ;" ;
 	 	         rs = stmt.executeQuery(sql);
 	 
 	            //int age = rs.getInt("age");
 	         
 	           // String last = rs.getString("lname");
 	           // double bitCoin = rs.getDouble("btc_holdings");
 	    	   // double lotCoin = rs.getDouble("loc_holdings");
                 out.println("<table class='fixed_header' border='1' style='font-size:8px'>");
	    	    	out.println("<tr>");
  	    		  out.println("<th>");
  	    			out.println("Escrow Id");
  	    		  out.println("</th>");
  	    		  out.println("<th>");
  	    			out.println("User Id");
  	    		  out.println("</th>");
  	    		  out.println("<th>");
  	    			out.println("btc escrow");
  	    		  out.println("</th>");
  	    		  out.println("<th>");
  	    			out.println("Lock Escrow");
  	    		  out.println("</th>");
  	    		  out.println("<th>");
  	    			out.println("Time Stamp");
  	    		  out.println("</th>");
  	    		  out.println("<th>");
  	    			out.println("Select");
  	    		  out.println("</th>");
  	    	    out.println("</tr>");

   	         // Extract data from result set
   	         while(rs.next()){
   	            int escrow_id  = rs.getInt("escrow_id");
  	            int user_id  = rs.getInt("user_id");
  	            float btc_escrow = rs.getFloat("btc_escrow");
  	            float lock_escrow = rs.getFloat("lock_escrow");
  	            String time_stamp = rs.getString("time_stamp");
		 
    	    	   
   	    	   out.println("<tr>");
	    		      out.println("<td>");
	    			     out.println(escrow_id);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(user_id);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(btc_escrow);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(lock_escrow);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(time_stamp);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    		         String form ="<form action='UserAllert' method='POST'>"+
	     		    	  "<input type='hidden' id='escrow_id' name='escrow_id' value='"+escrow_id+"'>"+
	     		   		  "<input type='hidden' id='user_id' name='user_id' value='"+user_id+"'>"+
	     				  "<input type='hidden' id='btc_escrow' name='btc_escrow' value='"+btc_escrow+"'>"+
	     				  "<input type='hidden' id='lock_escrow' name='lock_escrow' value='"+lock_escrow+"'>"+
	     				  "<input type='hidden' id='time_stamp' name='time_stamp' value='"+time_stamp+"'>"+
	     				  "<input type='submit' value='Place Bitcoin Escrow'>"+
	     				  "</form>";
	  	     		     out.println(form);
	    		      out.println("</td>");
	    	       out.println("</tr>");
   	    	    
   	         }
   	         out.println("</table>");
   	         rs.close();

 	    	    
 	        }else{
	        	 out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
	        	 out.println(" <h2 style='text-align: center;'>LOG IN FAILED</h2>");
				 out.println("<div style='text-align: center;'>");
   	         //out.println("<h4>Buy Lock Coin</h4>");	    	         
       		 out.println("<form method='POST' action='login2.jsp' >");
       		// out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
       		// out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
       		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
       		 out.println("<input type='submit' value='RETURN TO LOGIN'>");   	            
       		 out.println("</form>");

	         
            }
	      }catch(Exception e){
	    	  out.println(e);
	      }
*///	      request.getRequestDispatcher("includes/footer.jsp").include(request, response);     

	}

}
