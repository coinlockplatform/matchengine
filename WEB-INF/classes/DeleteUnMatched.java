

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
 * Servlet implementation class DeleteUnMatched
 */
public class DeleteUnMatched extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUnMatched() {
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
	      String name=(String)session.getAttribute("username");
	      String password=(String)session.getAttribute("password");
	      String user_idS=(String)session.getAttribute("user_id");	  	    
	      int user_id=Integer.parseInt(user_idS);		
		PrintWriter out = response.getWriter();
		 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		 response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		 response.setDateHeader("Expires", 0); // Proxies.
		 double btcTradeAmount = Double.parseDouble(request.getParameter("btcLockAmount"));
		 //   String name=request.getParameter("name");
			//String password=request.getParameter("password");
			//double fourTimesTradeAmount = btcTradeAmount*4;
		    //JDBC driver name and database URL
		   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   final String DB_URL="jdbc:mysql://3.16.62.130/cl";
		   request.getRequestDispatcher("includes/header.jsp").include(request, response);
		   //  Database credentials
		   final String USER = "cl";
		  final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
		  Connection conn=null;
		  Statement stmt=null;
		  try {
		     // Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		     // Open a connection
		     conn = DriverManager.getConnection(DB_URL, USER, PASS);

		     // Execute SQL query
		     stmt = conn.createStatement();
		     if(btcTradeAmount!=0){
			    
			     
				    String sql="select unmatched from Protect where name='"+name+"';";
				     ResultSet rs = stmt.executeQuery(sql);
				     double bitCoinUnMatched=0;
				     while(rs.next()){
				    	 bitCoinUnMatched += rs.getDouble("unmatched");
				     }
				      rs.close();
				      
					     sql="select unmatched from Trading where name='"+name+"';";
					     rs = stmt.executeQuery(sql);
					   //  bitCoinUnMatched=0;
					     while(rs.next()){
					    	 bitCoinUnMatched += rs.getDouble("unmatched");
					     }
					      rs.close();
						    
			      
			      sql="select btc_holdings from users where email='"+name+"';";
				      rs = stmt.executeQuery(sql);
				     
				     rs.next();
				      double bitCoin = rs.getDouble("btc_holdings");
				      rs.close();
				      
			      
			      double newBtcHoldings = bitCoin+bitCoinUnMatched;
			      if(newBtcHoldings>=0){
			           sql="UPDATE users set btc_holdings="+newBtcHoldings+" where email='"+name+"';";
			           stmt.executeUpdate(sql);
			      }
			      sql="UPDATE Protect set unmatched="+0.0+" where name='"+name+"';";
	              stmt.executeUpdate(sql);
			      sql="UPDATE Trading set unmatched="+0.0+" where name='"+name+"';";
	              stmt.executeUpdate(sql);
	                
/*			      RequestDispatcher dispatcher = getServletContext()
			    	      .getRequestDispatcher("/BtcPriceProtection3.jsp");
			    	    dispatcher.forward(request, response);
			     stmt.close();
			     conn.close();
*/	
//-------------------------------------------------------------------	              
	              try{  
	            	  out.println("<div width='40%'>");
	            	      sql="Select * from Protect where name='"+name+"' ;";
	            	      rs = stmt.executeQuery(sql);
	            	     out.println("<h4>Protect</h4>");
	            	     out.println("<table class='table-bordered' width='40%'>");
	            	     out.println("<tr><td>protect_id</td><td>name</td><td>password</td><td>unmatched</td><td>matched</td>");
	            	     // Extract data from result set
	            	     while(rs.next()){
	            	        //Retrieve by column name
	            	        int protect_id  = rs.getInt("protect_id");
	            	        //int age = rs.getInt("age");
	            	         name = rs.getString("name");
	            	        // password = rs.getString("password");
	            	       
	            	        double unmatched = rs.getDouble("unmatched");
	            	        double matched = rs.getDouble("matched");
	            	        
	            	        out.println("<tr><td>"+protect_id+"</td><td>"+name+"</td><td>"+unmatched+"</td><td>"+matched+"</td>");
	            	     }
	            	     out.println("</table>");
	            	     out.println("</div>");
	            	     rs.close();
	            	     sql="Select * from Trading where name='"+name+"' ;";
	            	      rs = stmt.executeQuery(sql);
	            	      out.println("<center><h4>Trade</h4>");
	            	      out.println("<table class='table-bordered' width='40%'>");
	            	      out.println("<tr><td>trade_id</td><td>name</td><td>password</td><td>unmatched</td><td>matched</td>");
	            	     // Extract data from result set
	            	     while(rs.next()){
	            	        //Retrieve by column name
	            	        int trade_id  = rs.getInt("trade_id");
	            	        //int age = rs.getInt("age");
	            	         name = rs.getString("name");
	            	       // password = rs.getString("password");
	            	       
	            	        double unmatched = rs.getDouble("unmatched");
	            	        double matched = rs.getDouble("matched");
	            	        out.println("<tr><td>"+trade_id+"</td><td>"+name+"</td><td>"+unmatched+"</td><td>"+matched+"</td>");
	            	     }
	            	     out.println("</table></center>");
	            	     rs.close();
	            	     
	            	      }catch(Exception e){
	            	    	  out.println(e);
	            	      }finally{
	            	    	  stmt.close();
	            	    	  conn.close();
	            	      }              
	              
//-------------------------------------------------------------------	              
	              out.println("<form action='BtcPriceProtection3.jsp' method='post' onsubmit='myButton.disabled = true; return true;'>");
	          		out.println("<fieldset>");
	          		out.println("<legend>????????????</legend>");
	          		
	          		
	          								
	          		out.println("<input type='hidden' name='name' value='"+name+"'>");
	          												
	          		//out.println("<input type='hidden' name='password' value='"+password+"'>");
	          	    out.println("Amount of BTC you want to lock:<br>");
	          		out.println("<input type='hidden' name='btcLockAmount' value='0'></br></br>");
	          		out.println("<input type='submit' name='myButton' value='Submit'></br>");
	          		out.println("</fieldset>");
	          		out.println("</form> ");        	
	          		 request.getRequestDispatcher("includes/footer.jsp").include(request, response);
		     }
		  }catch(Exception e){
			  out.println(e);
		  }
	}

}
