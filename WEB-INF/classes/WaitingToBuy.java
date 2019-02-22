

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WaitingToBuy
 */
public class WaitingToBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaitingToBuy() {
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
		 HttpSession session = request.getSession();
	      String auth=(String)session.getAttribute("auth");  
	      if(!auth.equals("1")){
	    	  
	    	  session.invalidate();//destroy any session that they may have
	    	 response.sendRedirect(request.getContextPath() + "/index.jsp");
	    	//  out.println("redirect "+request.getContextPath() + "/login2.jsp");
	    	 return;
	      }
		
	         String userName = request.getParameter("username");
    String password = request.getParameter("password");
    double btcToEscrow = Double.parseDouble(request.getParameter("buyEscrowAmt"));
	      // JDBC driver name and database URL
	       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	       final String DB_URL="jdbc:mysql://3.16.62.130/cl";

	      //  Database credentials
	       final String USER = "cl";
	       final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
  	    request.getRequestDispatcher("includes/header.jsp").include(request, response);
	      // Set response content type
	      response.setContentType("text/html");
	     
	      String title = "Lock Coin User";
	      
	      String docType =
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
	         sql = "SELECT * FROM users where email = '"+ userName + "' and password = '" + password +"' ;" ;
	         ResultSet rs = stmt.executeQuery(sql);
	         String javaCalcLogic="";
	         // Extract data from result set
	         if(rs.next()){
	            //Retrieve by column name
	           // int id  = rs.getInt("id");
	            //int age = rs.getInt("age");
	            String first = rs.getString("fname");
	            String last = rs.getString("lname");
	            int user_id = rs.getInt("user_id");
	            double bitCoin = rs.getDouble("btc_holdings");
	    	    if(bitCoin>=btcToEscrow){
	    	    	int escrowId=-1;
	    		   sql = "Insert into escrow ( escrow_id, user_id, btc_escrow) values (NULL, '"+user_id+"', "+btcToEscrow+"  );"; 
	    		   stmt.executeUpdate(sql);
	    		   sql="SELECT LAST_INSERT_ID();";
	    		   ResultSet rs2 = stmt.executeQuery(sql);
	    		   if(rs2.next()){
	    			   escrowId = rs2.getInt(1);
	    			   
	    		   }
	    		   
	    		   double newUserBtcValue = (bitCoin - btcToEscrow);
	    		   sql = "UPDATE users "+
                         "SET btc_holdings = " + newUserBtcValue +
                         " WHERE user_id = "+user_id+";"; 
	    		   stmt.executeUpdate(sql);
	    		   out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
	    	       out.println("<h2 style='text-align: center;'>Waiting for finalization of "+btcToEscrow+" BTC escrow</h2>");
		     		 out.println("<form method='POST' action='StillWaiting' >");
			     		 out.println("<input type='hidden' id='escrowId' name='escrowId' value='"+escrowId+"'>");
			     		 out.println("<input type='hidden' id='userName' name='userName' value='"+userName+"'>");
			     		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
			     		 out.println("<input type='submit' value='Check to see id Btc is locked' class='btn btn-hero'>");   	            
			     		 out.println("</form>");

	    	    	 out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
	    	    }else{
	    	    	 out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
	    	    	out.println("<h2 style='text-align: center;'>Not enough BTC in your user account to do an escrow of "+btcToEscrow+" </h2>");
	    	    }
	            
	            

		      }else{
		        	 out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
		        	 out.println(" <h2 style='text-align: center;'>ESCROW FAILED</h2>");
					 out.println("<div style='text-align: center;'>");
	 	         //out.println("<h4>Buy Lock Coin</h4>");	    	         
	     		 out.println("<form method='POST' action='index.jsp' >");
	     		// out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
	     		// out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
	     		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
	     		 out.println("<input type='submit' value='RETURN TO LOGIN'>");   	            
	     		 out.println("</form>");
	
		         
	          }
	         rs.close();
   	         stmt.close();
   	         conn.close();

	      }catch(Exception e){
	    	  out.println(e);
	      }
	      request.getRequestDispatcher("includes/footer.jsp").include(request, response);     
	}

}
