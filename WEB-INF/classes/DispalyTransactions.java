

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
 * Servlet implementation class DispalyTransactions
 */
public class DispalyTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispalyTransactions() {
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
	      String username=(String)session.getAttribute("username");
	      String password=(String)session.getAttribute("password");
	      String user_idS=(String)session.getAttribute("user_id");	  	    
	      int user_id=Integer.parseInt(user_idS);		
		  
		  
		  PrintWriter out = response.getWriter();
  	      // JDBC driver name and database URL
  	       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  	       final String DB_URL="jdbc:mysql://3.16.62.130/cl";

  	      //  Database credentials
  	       final String USER = "cl";
  	       final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
	    	    request.getRequestDispatcher("includes/header.jsp").include(request, response);
  	      // Set response content type
  	      response.setContentType("text/html");
  	     
  	      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
  	      response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
  	      response.setDateHeader("Expires", 0); // Proxies.
  	      String title = "Lock Coin User";
  	      
  	      String docType =
  	         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
  	      String style="<style>  .fixed_header { display:block; height: 300px; overflow-y: scroll;} .fixed_header tbody{  display:block;  overflow:auto;  height:20px;  width:100%;} .fixed_header thead tr{  display:block;} </style>";
  	      Connection conn=null;
  	      Statement stmt=null;
  	      
  	      
	      
  	    out.println(
  	    		"<style>"
	  	    		+".lefttbl{}"
	  	    		+".righttbl{}"
	  	    		+".rightdiv{float: right; width: 40%; margin: auto;}"
	  	    		+".leftdiv{float: left; width: 40%; margin: auto;}"
	  	    		+".clearfix::after {"
	  	    		  +"content: '';"
	  	    		  +"clear: both;"
	  	    		  +"display: table;"
	  	    		  +"overflow:auto;"
	  	    		+"}"	
	  	    		  
+".lefttbl, righttbl"
+"{"
	  	    		+"margin-top:80px;"
	  	    		+"}"
	  	    		+"table{"
	  	    		+"}"
	  	    		+"th{"
	  	    		+"  background-color:#888;"
	  	    		+"color:#fff;"
	  	    		 +" padding:6px;"
	  	    		+"}"
	  	    		+"tr{"
	  	    		 +" border:1px solid #ddd;"
	  	    		+"}"
	  	    		+"td{"
	  	    		 +" padding:5px;"
	  	    		+"}"
	  	    		+".odd{"
	  	    		 +" background-color:#ccc;"
	  	    		+"} "
  	    		+"</style>"
  	    );  	      
  	   	      
  	      
  	      try {
  	         // Register JDBC driver
  	          Class.forName("com.mysql.jdbc.Driver");

  	         // Open a connection
  	         conn = DriverManager.getConnection(DB_URL, USER, PASS);

  	         // Execute SQL query
  	         stmt = conn.createStatement();
  	         String sql;
		out.println ("</br></br></br></br></br>");
		
		
		 out.println("<div style='text-align: center;'>");
		 out.println("<form method='POST' action='dashboard.jsp' >");
		  out.println("<input type='submit' value='RETURN TO HOME' class='btn btn-hero'>");   	            
	      out.println("</form></div>");
				   //------------------------match contracts------------------------------------------
		     out.println ("</br></br>");
			  out.println("<h4>Protect Contracts</h4>");
			     sql = "select * from matched where protect_email='"+username+"' and finished = 0 ORDER BY match_id DESC;";
			     ResultSet  rs = stmt.executeQuery(sql);
			     out.println("<table class='fixed_header'>");
			     out.println("<h1>"+username+"<h1>");
		         
		  	    	out.println("<tr>");

			    	out.println("<th>Match ID</th><th>Protect ID</th><th>Trade ID</th><th>protect_email</th><th>trade_email</th><th>trade_amount_matched</th><th>protect_amount_matched</th><th>bitcion_price_at_match</th><th>time_stamp</th><th> Protected $ Value</th><th>view</th><th>Validate Transfer</th>");
		    	    out.println("</tr>");
			     while(rs.next()){
			    	 int match_id = rs.getInt("match_id");
			    	 int protect_id = rs.getInt("protect_id");
			    	 int trade_id = rs.getInt("trade_id");
			    	 String protect_email = rs.getString("protect_email");
			    	 String trade_email = rs.getString("trade_email");

			    	 double trade_amount_matched = rs.getDouble("trade_amount_matched");
			    	 double protect_amount_matched =rs.getDouble("protect_amount_matched");
			    	 double bitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
			    	 String time_stamp = rs.getString("time_stamp");
			    	 
			    	 String money_transfer = rs.getString("money_transfer");
			    	 double dollarValue=protect_amount_matched*bitcion_price_at_match;
    		         String form ="<form action='DisplayProtectContract.jsp' method='POST'>"+
     		    	  "<input type='hidden' id='bitcion_price_at_match' name='bitcion_price_at_match' value='"+bitcion_price_at_match+"'>"+
     		   		  "<input type='hidden' id='time_stamp' name='time_stamp' value='"+time_stamp+"'>"+
     		   		  "<input type='hidden' id='match_id' name='match_id' value='"+match_id+"'>"+
     		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
     		   		  "<input type='hidden' id='trade_id' name='trade_id' value='"+trade_id+"'>"+
     		   		  "<input type='hidden' id='trade_email' name='trade_email' value='"+trade_email+"'>"+
     		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
     		   		  "<input type='hidden' id='user_id' name='user_id' value='"+user_id+"'>"+
     		   		  //"<input type='hidden' id='username' name='username' value='"+username+"'>"+
     				  "<input type='hidden' id='protect_amount_matched' name='protect_amount_matched' value='"+protect_amount_matched+"'>"+    				  
     				  "<input type='submit' value='View' class='btn btn-hero'>"+
     				  "</form>";
    		         String form2 ="<form action='MatchBTC' method='POST'>"+
     		    	  "<input type='hidden' id='bitcion_price_at_match' name='bitcion_price_at_match' value='"+bitcion_price_at_match+"'>"+
     		   		  "<input type='hidden' id='time_stamp' name='time_stamp' value='"+time_stamp+"'>"+
     		   		  "<input type='hidden' id='match_id' name='match_id' value='"+match_id+"'>"+
     		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
     		   		  "<input type='hidden' id='trade_id' name='trade_id' value='"+trade_id+"'>"+
     		   		  "<input type='hidden' id='trade_email' name='trade_email' value='"+trade_email+"'>"+
     		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
     		   		  "<input type='hidden' id='user_id' name='user_id' value='"+user_id+"'>"+
     		   		 // "<input type='hidden' id='username' name='username' value='"+username+"'>"+
     		   		  //"<input type='hidden' id='password' name='password' value='"+password+"'>"+
     				  "<input type='hidden' id='protect_amount_matched' name='protect_amount_matched' value='"+protect_amount_matched+"'>"+    				  
     				  "<input type='submit' value='Offer Protected Position' class='btn btn-hero'>"+
     				  "</form>";
   		         String formMoneyValidate="<form action='ValidateMoneyRecieved' method='POST'>"+
    	     		    	  "<input type='hidden' id='bitcion_price_at_match' name='bitcion_price_at_match' value='"+bitcion_price_at_match+"'>"+
    	     		   		  "<input type='hidden' id='time_stamp' name='time_stamp' value='"+time_stamp+"'>"+
    	     		   		  "<input type='hidden' id='match_id' name='match_id' value='"+match_id+"'>"+
    	     		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
    	     		   		  "<input type='hidden' id='trade_id' name='trade_id' value='"+trade_id+"'>"+
    	     		   		  "<input type='hidden' id='trade_email' name='trade_email' value='"+trade_email+"'>"+
    	     		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
    	     		   		  "<input type='hidden' id='user_id' name='user_id' value='"+user_id+"'>"+
    	     		   		 // "<input type='hidden' id='username' name='username' value='"+username+"'>"+
    	     		   		 // "<input type='hidden' id='password' name='password' value='"+password+"'>"+
    	     				  "<input type='hidden' id='protect_amount_matched' name='protect_amount_matched' value='"+protect_amount_matched+"'>"+    				  
    	     				  "<input type='submit' value='Money Validate' class='btn btn-hero'>"+
    	     				  "</form>";

			  	    	out.println("<tr>");
			  	    	  out.println("<td>" + match_id + "</td>");
			  		      out.println("<td>" + protect_id + "</td>");
			  		      out.println("<td>" + trade_id + "</td>");
			  		      out.println("<td>" + protect_email + "</td>");
			  		      out.println("<td>" + trade_email + "</td>");
			  		      out.println("<td>" + trade_amount_matched + "</td>");
			  		      out.println("<td>" + protect_amount_matched + "</td>");
			  		      out.println("<td>" + bitcion_price_at_match + "</td>");
			  		     // out.printf("<td>%.4f</td>",coinsAvailableInTier);
			  		     // out.printf("<td>%.6f</td>",bitcoin_gained);
				  		    out.println("<td>" + time_stamp + "</td>");
				  		    out.println("<td>" + dollarValue + "</td>");
				  		    out.println("<td>" + form + "</td>");
				  		    out.println("<td>" + form2 + "</td>");
				  		    if(money_transfer.equals("no")==false)
				  		        out.println("<td>" + formMoneyValidate + "</td>");
			    	    out.println("</tr>");
			     }
			     out.println("</table>");
			     
			     rs.close();		     
//-----------------------end match contracts---------------------------------------		     
			   //------------------------match contracts t------------------------------------------
			     out.println ("</br></br>");
				  out.println("<h4>Trade Contracts</h4>");
				     sql = "select * from matched where trade_email='"+username+"' and finished = 0 ORDER BY match_id DESC ;";
				     rs = stmt.executeQuery(sql);
				     out.println("<table class='fixed_header'>");

			         
			  	    	out.println("<tr>");

				    	out.println("<th>Match ID</th><th>Protect ID</th><th>Trade ID</th><th>protect_email</th><th>trade_email</th><th>trade_amount_matched</th><th>protect_amount_matched</th><th>bitcion_price_at_match</th><th>time_stamp</th><th>view</th>");
			    	    out.println("</tr>");
				     while(rs.next()){
				    	 int match_id = rs.getInt("match_id");
				    	 int protect_id = rs.getInt("protect_id");
				    	 int trade_id = rs.getInt("trade_id");
				    	 String protect_email = rs.getString("protect_email");
				    	 String trade_email = rs.getString("trade_email");

				    	 double trade_amount_matched = rs.getDouble("trade_amount_matched");
				    	 double protect_amount_matched =rs.getDouble("protect_amount_matched");
				    	 double bitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
				    	 String time_stamp = rs.getString("time_stamp");
	    		         String form ="<form action='DisplayContract.jsp' method='POST'>"+
	    		        		 "<input type='hidden' id='bitcion_price_at_match' name='bitcion_price_at_match' value='"+bitcion_price_at_match+"'>"+
	    	     		   		  "<input type='hidden' id='time_stamp' name='time_stamp' value='"+time_stamp+"'>"+
	    	     		   		  "<input type='hidden' id='match_id' name='match_id' value='"+match_id+"'>"+
	    	     		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
	    	     		   		  "<input type='hidden' id='trade_id' name='trade_id' value='"+trade_id+"'>"+
	    	     		   		  "<input type='hidden' id='trade_email' name='trade_email' value='"+trade_email+"'>"+
	    	     		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
	    	     				  "<input type='hidden' id='protect_amount_matched' name='protect_amount_matched' value='"+protect_amount_matched+"'>"+   
	     				  
	     				  "<input type='submit' value='View' class='btn btn-hero'>"+
	     				  "</form>";

				  	    	out.println("<tr>");
				  	    	  out.println("<td>" + match_id + "</td>");
				  		      out.println("<td>" + protect_id + "</td>");
				  		      out.println("<td>" + trade_id + "</td>");
				  		      out.println("<td>" + protect_email + "</td>");
				  		      out.println("<td>" + trade_email + "</td>");
				  		      out.println("<td>" + trade_amount_matched + "</td>");
				  		      out.println("<td>" + protect_amount_matched + "</td>");
				  		      out.println("<td>" + bitcion_price_at_match + "</td>");
				  		     // out.printf("<td>%.4f</td>",coinsAvailableInTier);
				  		     // out.printf("<td>%.6f</td>",bitcoin_gained);
					  		    out.println("<td>" + time_stamp + "</td>");
					  		    out.println("<td>" + form + "</td>");
				    	    out.println("</tr>");
				     }
				     out.println("</table>");
				     
				     rs.close();		     
	//-----------------------end match contracts---------------------------------------
				     
				     out.println("<h4>Winners</h4>");
				     sql = "select * from winners;";
				      rs = stmt.executeQuery(sql);
				     out.println("<table class='fixed_header'>");

			         
			  	    	out.println("<tr>");

				    	out.println("<th>Winner ID</th><th>User ID</th><th>Lockcoin Value</th><th>Coins Available In Tier</th><th>Bitcoin Gained</th><th>Time Stamp</th>");
			    	    out.println("</tr>");
				     while(rs.next()){
				    	 int winner_id = rs.getInt("winner_id");
				    	 int user_id2 = rs.getInt("user_id");
				    	 String email = rs.getString("email");
				    	 double lockcoin_value = rs.getDouble("lockcoin_value");
				    	 double coinsAvailableInTier =rs.getDouble("coinsAvailableInTier");
				    	 double bitcoin_gained =rs.getDouble("bitcoin_gained");
				    	 String time_stamp = rs.getString("time_stamp");
				  	    	out.println("<tr>");
				  	    	  out.println("<td>" + winner_id + "</td>");
				  		      out.println("<td>" + user_id2 + "</td>");
				  		     // out.println("<td>" + email + "</td>");
				  		      out.println("<td>" + lockcoin_value + "</td>");
				  		      out.printf("<td>%.4f</td>",coinsAvailableInTier);
				  		      out.printf("<td>%.6f</td>",bitcoin_gained);
				  		    out.println("<td>" + time_stamp + "</td>");
				    	    out.println("</tr>");
				     }
				     out.println("</table>");
				     
				     rs.close();
				     
				     
  	        //==================table of transactions============================
  	         out.println("<div class='clearfix'></div>");
    	       out.println("<div class='leftdiv'>"); 
    	       
    	       
    	       //----------saved sql-------------------------------
    	       
    	   //    SELECT c2.coin_name AS from_name, c1.coin_name AS to_name, t.* FROM `transactions` t
    	    //   INNER JOIN `coin_stats` c1 ON t.to_coin_id = c1.coin_id
    	     //  INNER JOIN `coin_stats` c2 ON t.from_coin_id = c2.coin_id;
    	       
    	       
    	     //--------------------------------------------------


    	       
    	       sql = " SELECT c2.coin_name AS from_name, c1.coin_name AS to_name, t.* FROM transactions t"
    	       +" INNER JOIN coin_stats c1 ON t.to_coin_id = c1.coin_id"
    	       +" INNER JOIN coin_stats c2 ON t.from_coin_id = c2.coin_id;";

    	       
    	      //------------------------------------------------------ 
    	         
      	    //     sql = "SELECT * FROM transactions ;" ;
           rs = stmt.executeQuery(sql);
           out.println("<table class='fixed_header lefttbl'>");
           out.println("<caption>All Transactions</caption>");
  	    	out.println("<tr>");
//	    	out.println("<th>User ID</th><th>from_coin_id</th><th>to_coin_id</th><th>amount_from</th><th>amount_to</th><th>current_btc_usd</th><th>timestamp</th>");
	    	out.println("<th>Form Coin</th><th>To Coin</th><th>amount_from</th><th>amount_to</th><th>current_btc_usd</th><th>timestamp</th>");
    	    out.println("</tr>");

          // Extract data from result set
          while(rs.next()){
         	 int uid = rs.getInt("uid");
         	 String from_name=rs.getString("from_name");
         	 String to_name=rs.getString("to_name");
         	 
         	 
         	// int from_coin_id = rs.getInt("from_coin_id");
         	// int to_coin_id = rs.getInt("to_coin_id");
         	 double amount_from = rs.getDouble("amount_from");
         	 double amount_to = rs.getDouble("amount_to");
         	 double current_btc_usd = rs.getDouble("current_btc_usd");
         	 String timeStamp = rs.getString("date");
         			 
      	   
     	   out.println("<tr>");
  		     // out.println("<td>" + uid + "</td>");
  		      out.println("<td>" + from_name + "</td>");
  		      out.println("<td>" + to_name + "</td>");
  		      out.println("<td>" + amount_from + "</td>");
  		      out.println("<td>" + amount_to + "</td>");
  		      out.println("<td>" + current_btc_usd + "</td>");
  		      out.println("<td>" + timeStamp + "</td>");
  	       out.println("</tr>");
     	    
          }
          out.println("</table>");
          
          out.println("</div>");
          rs.close();
    	         //===================end table of transactions========================
          //==================table of transactions2 for logged in only============================
	         
 	       out.println("<div class='rightdiv'>");   
 	         
   	       //  sql = "SELECT * FROM transactions where uid = "+user_id+" ;" ;
   	         
  	       sql = " SELECT c2.coin_name AS from_name, c1.coin_name AS to_name, t.* FROM transactions t"
  	       +" INNER JOIN coin_stats c1 ON t.to_coin_id = c1.coin_id"
  	       +" INNER JOIN coin_stats c2 ON t.from_coin_id = c2.coin_id"
  	       +"  where uid = "+user_id+" ;";
   	         
         rs = stmt.executeQuery(sql);
//         out.println("<table class='fixed_header' border='1' style='font-size:8px'>");
         
         
         out.println("<table class='lefttbl'>");
         
         out.println("<caption>My Transactions for "+username+"</caption>");
         out.println("<tr>");
//	    	out.println("<th>User ID</th><th>from_coin_id</th><th>to_coin_id</th><th>amount_from</th><th>amount_to</th><th>current_btc_usd</th><th>timestamp</th>");
	    	out.println("<th>From Coin</th><th>To Coin</th><th>amount from</th><th>amount to</th><th>current_btc_usd</th><th>timestamp</th>");
 	    out.println("</tr>");

       // Extract data from result set
       while(rs.next()){
    	 int uid = rs.getInt("uid"); 
       	 String from_name=rs.getString("from_name");
       	 String to_name=rs.getString("to_name");

      	 
      	 //int from_coin_id = rs.getInt("from_coin_id");
      	// int to_coin_id = rs.getInt("to_coin_id");
      	 double amount_from = rs.getDouble("amount_from");
      	 double amount_to = rs.getDouble("amount_to");
      	 double current_btc_usd = rs.getDouble("current_btc_usd");
      	 String timeStamp = rs.getString("date");
      			 
   	   
      	 out.println("<tr>");
		     // out.println("<td>" + uid + "</td>");
		      out.println("<td>" + from_name + "</td>");
		      out.println("<td>" + to_name + "</td>");
		      out.println("<td>" + amount_from + "</td>");
		      out.println("<td>" + amount_to + "</td>");
		      out.println("<td>" + current_btc_usd + "</td>");
		      out.println("<td>" + timeStamp + "</td>");
	     out.println("</tr>");
  	    
       }
       out.println("</table>");
       out.println("</div>");
        out.println("<div class='clearfix'></div>");
      
       rs.close();
 	         //===================end table of transactions2========================
        stmt.close();
        conn.close();
  	      }catch(Exception e){
  	    	  
  	      }
  	    request.getRequestDispatcher("includes/footer.jsp").include(request, response);
	}

}
