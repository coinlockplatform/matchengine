


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Hello2
 */
public class dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public dashboard() {
        // TODO Auto-generated constructor stub
    }

    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
		  out.println("Hello "+ request.getParameter("username"));
/*		  if(request.getParameter("username")!=null){
			  out.println("We are logging you in "); 
			  
			 // return;
		  }else{
			  
			  out.println("xnoooo homie!!! ");
		 }
*/		  
		  String userName = request.getParameter("username");
	      String password = request.getParameter("password");
	      String submit = request.getParameter("submit");
	      
 	       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
 	       final String DB_URL="jdbc:mysql://3.16.62.130/cl";
	            //int age = rs.getInt("age");
	            String email=null ;
	            String last=null ;
	            double bitCoin =0;
	    	    double lotCoin =0 ;
	    	    double PendingBTCDep =0 ;
	    	    double PendingBTCWithdrawal =0 ;
	    	    double PendingLOCKWith =0 ;
	    	    boolean status = false;
	    	    int user_id  = 0;
 	      //  Database credentials
 	       final String USER = "cl";
 	       final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
	  	    Connection conn=null;
	  	    Statement stmt=null;
	      if( userName != null){
	    	  HttpSession session = request.getSession();
	    	  session.invalidate();//destroy any session that they may have
	    	  
	    	  

	  	      try {
	  	         // Register JDBC driver
	  	          Class.forName("com.mysql.jdbc.Driver");

	  	         // Open a connection
	  	         conn = DriverManager.getConnection(DB_URL, USER, PASS);

	  	         // Execute SQL query
	  	         stmt = conn.createStatement();
	  	         String sql;

	  	         sql = "SELECT * FROM users where email = ? and password = ? ;" ;
	  	         PreparedStatement ps = conn.prepareStatement(sql);
	  	         ps.setString (1, userName);
	  	         ps.setString (2, password);
	  	         
	  	         ResultSet rs = ps.executeQuery();	  	         
	  	         if(rs.next()){
	   	    	     session = request.getSession();
	  	    	     session.setAttribute("username", userName);
	  	    	     session.setAttribute("password", password);
	  	    	     session.setAttribute("auth", "1");
	   	    	     status = rs.getBoolean("approved");
	    	            //int age = rs.getInt("age");
	    	         email = rs.getString("email");
	    	         last = rs.getString("lname");
	    	         bitCoin = rs.getDouble("btc_holdings");
	    	    	 lotCoin = rs.getDouble("loc_holdings");
	    	    	 PendingBTCDep = rs.getDouble("PendingBTCDep");
	    	    	 PendingBTCWithdrawal = rs.getDouble("PendingBTCWithdrawal");
	    	    	 PendingLOCKWith = rs.getDouble("PendingLOCKWith");
	    	    	   

		    	     if(status==false){
	  	    	    	rs.close();
	  	    	    	stmt.close();
	  	    	    	conn.close();
	  	    	    	response.sendRedirect(request.getContextPath() + "/WaitForRegistration");
	  	    	    }
	    	       
	  	         }else {
	  	        	out.println("lllllllllllllllyoooo brooooo ");
	  	        	response.sendRedirect(request.getContextPath() + "/index.jsp");
	  	        	return;
	  	         }
	  	         
	  	      }catch(Exception e){
	  	    	  out.println(e);
	  	      }
	    	  	    	  
	      }else{
	    	  out.println("yoooo brooooo ");
	    	 
	    	  
	    	  HttpSession session = request.getSession();
		      String auth=(String)session.getAttribute("auth");  
		      if(!auth.equals("1")){
		    	  
		    	  session.invalidate();//destroy any session that they may have
		    	 response.sendRedirect(request.getContextPath() + "/index.jsp");
		    	//  out.println("redirect "+request.getContextPath() + "/login2.jsp");
		    	 return;
		      }
	    	   
	      }
	      /*
	       * if submit is not empty
	       *    destroy any session that they may have
	       * 	if the user/pass sent by post is not correct
	       *    	destroy the session and redirect to index.jsp
	       *    else if the username and pass are correct, create a session
	       *    		set auth = 1 inside the session
	       * else if the submit is empty
	       * 	check the session to see if auth=1
	       * 	if auth is not =1
	       * 		destroy their session, redirect them to index.jsp
	       * else if the session is auth =1
	       * 	continue on, they are logged in.
	       */
	      
	      
  	      // JDBC driver name and database URL

	    	    request.getRequestDispatcher("includes/header.jsp").include(request, response);
  	      // Set response content type
  	      response.setContentType("text/html");
  	     
  	      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
  	      response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
  	      response.setDateHeader("Expires", 0); // Proxies.
  	      String title = "Match Engine Wallet";
  	      
  	      String docType =
  	         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
  	      String style="<style>  .fixed_header { display:block; height: 300px; overflow-y: scroll;} .fixed_header tbody{  display:block;  overflow:auto;  height:20px;  width:100%;} .fixed_header thead tr{  display:block;} </style>";
  	    //  Connection conn=null;
  	    //  Statement stmt=null;
 /* 	      try {
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
  	         
  	         
  	         int user_id = 0;
  	         if(rs.next()){
  	            //Retrieve by column name
  	            user_id  = rs.getInt("user_id");
  	            //int age = rs.getInt("age");
  	            String email = rs.getString("email");
  	            String last = rs.getString("lname");
  	            double bitCoin = rs.getDouble("btc_holdings");
  	    	    double lotCoin = rs.getDouble("loc_holdings");
  	    	    double PendingBTCDep = rs.getDouble("PendingBTCDep");
  	    	    double PendingBTCWithdrawal = rs.getDouble("PendingBTCWithdrawal");
  	    	    double PendingLOCKWith = rs.getDouble("PendingLOCKWith");
  	    	    boolean status = rs.getBoolean("approved");
	    	    if(status==false){
  	    	    	rs.close();
  	    	    	stmt.close();
  	    	    	conn.close();
  	    	    	response.sendRedirect(request.getContextPath() + "/WaitForRegistration");
  	    	    }
  	    	    
  	    	    javaCalcLogic ="<script language='JavaScript'>"
  	    	    		+ "<!-- "
                          +"function showpay() { "
                          +" if ((document.calc.btcToSell.value == null || document.calc.btcToSell.value.length == 0) || "
                               +"(document.calc.btcPrice.value == null || document.calc.btcPrice.value.length == 0) "
                              
                            +"{ document.calc.netSale.value = 'Incomplete data';"
                            +" }"
                                  +" else"
                                    +" {"
                                        +" var btcToSell = document.calc.btcToSell.value; "
                                        +" var btcPrice  = document.calc.btcPrice.value; "
                                       
                                        +"document.calc.netSale.value = btcToSell *btcPrice ; "
                                        +" } "
                                       +"} "
                             +" // --> "
                            +"</script> ";

    	    	      out.println(docType +
  	    	    	         "<html>\n" +
  	    	    	         "<head>"+style+"<title>" + title + "</title></head>\n" +
  	    	    	         "<body bgcolor = \"#ffffff\">\n" +
  	    	    	         "<h1 align = \"center\">" + title + "</h1>\n"+
  	    	 	             "<h1 align = \"center\">" + first+ " "+ last + "</h1>\n");
*/
//  	    	    request.getRequestDispatcher("includes/header.jsp").include(request, response);
  	    	    out.println("<table>");
  	    	    out.println("<tr>");
  	    	    out.println("<td style='margin: 25px;'>");
  	    	    out.println("<div style='float: right;'>");
  	    	    out.println( "<br/><br/><br/><br/><h5 align = \"center\">" + title + "</h5>\n"+
	    	 	             "<h5 align = \"center\">" + email + "</h5>\n");

  	            //Display values
    	    	    //out.println("<h5>");
  	    	    out.println("<center>");
      
  	    	    out.println("<table class='fixed_header' border='1' style='font-size:12px; margin-top=70px;'>");
  	    	    
  	    	    out.println("<caption></caption>");
  	    	    
//  	    	    out.println("<table class='fixed_header' border='2' style='font-size:30px'>");
	    	    	out.println("<tr>");
  	    		  out.println("<td>");
  	    			out.println("Current BTC Balance");
  	    		  out.println("</td><td style='padding-left: 20px;'>"
  	    		  		+ "<b>" + bitCoin + "</b>");
  	    		  out.println("</td>");
  	    	    out.println("</tr>");
  	    	    out.println("<tr>");
	    		  out.println("<td>");
	    			out.println("Pending BTC Deposit");
	    		  out.println("</td><td style='padding-left: 20px;'>"
	    		  		+ "<b>" + PendingBTCDep + "</b>");
	    		  out.println("</td>");
	    	    out.println("</tr>");
	    	  out.println("<tr>");
  		  out.println("<td>");
  			out.println("Pending BTC Withdrawal");
  		  out.println("</td><td style='padding-left: 20px;'>"
  		  		+ "<b>" + PendingBTCWithdrawal + "</b>");
  		  out.println("</td>");
  	    out.println("</tr>");
  	    	   out.println("<tr>");
	    		      out.println("<td>");
	    			     out.println("Current LOCK Balance");
	    		      out.println("</td>");
	    		      out.println("<td style='padding-left: 20px;'>");
	    			     out.println(lotCoin);
	    		      out.println("</td>");
	    	       out.println("</tr>");
	    	       out.println("<tr>");
	    		      out.println("<td>");
	    			     out.println("Pending LOCK Withdrawal");
	    		      out.println("</td>");
	    		      out.println("<td style='padding-left: 20px;'>");
	    			     out.println(PendingLOCKWith);
	    		      out.println("</td>");
	    	       out.println("</tr>");
	    	       ////////////////////////
	    	       
	    	       
 	    	    out.println("</table>");
	    	    out.println("</center>");
	    	    

	    	    //out.println("</h5>");
	    	    out.println("<br/>");
  	          //  out.println("bitCoin: " + bitCoin + "<br>");
  	          //  out.println("lotCoin: " + lotCoin + "<br>");
  	          //  out.println("First: " + first + "<br>");
  	          //  out.println("Last: " + last + "<br>");
  	            
  	         
  				//--------------get BitCoin price---------------------------------------------------------------------
  				double bitCoinValue=0;
  				try{
  			          InputStream is = new URL("https://api.coindesk.com/v1/bpi/currentprice.json").openStream();
  					  JsonReader jsonReader =  Json.createReader(new InputStreamReader(is, "UTF-8"));
  					  JsonObject object = jsonReader.readObject();
  					
  					
  					  JsonObject bpiObject = object.getJsonObject("bpi");
  		               //  out.println(usdObject.getString("rate"));
  					  JsonObject usdObject = bpiObject.getJsonObject("USD");
  					  //JsonObject rateObject = usdObject.getJsonObject("rate");
  					
  					  //System.out.println(usdObject.getString("rate"));
  					  bitCoinValue = Double.parseDouble(usdObject.getString("rate").replaceAll(",", ""));
  					  System.out.println("bitvalue="+bitCoinValue);
  					  jsonReader.close();

  			       }
  			       catch(IOException e){
  				       out.println("<h1>UNABLE TO GET BITCION PRICE</h1>");
  				       out.println("</body></html>");
  				     
  				     //  stmt.close();
  				     //  conn.close();
  					   e.printStackTrace();
  					   return;
  			       }
  				//--------------get lockCoin price---------------------------------------------------------------------
  				double coinsAvailableInTier=-1 ;
  				DoubleReturner drCoinsAvailableInTier = new DoubleReturner();
  				IntReturner ir=new IntReturner() ;
  				double lockCoinValue=0;
				try {
					lockCoinValue = getLockCoinPrice(ir,drCoinsAvailableInTier,  stmt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  				coinsAvailableInTier = drCoinsAvailableInTier.value;
  				int tier = ir.value;
  				out.println("<div style='text-align: center; margin-bottom: 50;'>");	
  	         out.println("<h5>BITCOIN $ PRICE: "+bitCoinValue+"</h5>");
  	         out.println("<h5>LOCKCOIN $ PRICE: "+lockCoinValue+"</h5>");
  	         out.printf("<h5>LOCKCOIN LEFT AT THIS PRICE:%.2f </h5>",coinsAvailableInTier);
  	         out.println(" </div>");
  	         out.println(" </div>");
//-------------------------------------------------
  	         out.println("</td>");
  	         out.println("<td style='padding: 25px;'>");
   	        out.println("<div style='float: left; margin: auto;  width:20%;'>");
    	        
   	       out.println("<center>");	
      		 //==============trading===============================================================================
       		 
       		 out.println("<div style='text-align: center; margin-top: 100px;'>");
   	         //out.println("<h4>Buy Lock Coin</h4>");	    	         
       		 out.println("<form method='POST' action='Trading2.jsp' onsubmit='myButton.disabled = true; return true;'>");
       		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
       		 out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
       		 out.println("<input type='hidden' id='user_id' name='user_id' value='"+user_id+"'>");
       		 
       		 
       		 out.println("<h6>Amount you want to Autotrade </h6><input type='text' id='autoTrade' name='autoTrade' value=''>");
       		 out.println("<input type='submit' name='myButton' value='   Trade   ' class='btn btn-hero'>");   	            
       		 out.println("</form>");
       		 out.println("</div>");
   	      //=======================Lock Bitcoin====================================================== 
   	        out.println("<div style='text-align: center; margin-top: 10px;'>");
            		// out.println("<div>");
    	         //out.println("<h4>Buy Lock Coin</h4>");	    	         
        		 out.println("<form method='POST' action='DispalyTransactions'onsubmit='myButton.disabled = true; return true;' >");
        		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
        		// out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
        		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
        		 out.println("<input type='hidden' id='user_id' name='user_id' value='"+user_id+"'>");
        		 out.println("<input type='submit' name='myButton' value='   Escrow Protected Bitcoin  ' class='btn btn-hero'>");   	            
        		 out.println("</form>");
        		 out.println("</div>");

    	         //=======================Transaction Information====================================================== 
         		 out.println("<div style='text-align: center; margin-top: 10px;'>");
     	         //out.println("<h4>Buy Lock Coin</h4>");	    	         
         		 out.println("<form method='POST' action='DispalyTransactions' onsubmit='myButton.disabled = true; return true;' >");
         		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
         		// out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
         		 out.println("<input type='hidden' id='user_id' name='user_id' value='"+user_id+"'>");
         		 
         		 
         		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
         		 out.println("<input type='submit' name='myButton' value='Transaction Information' class='btn btn-hero'>");   	            
         		 out.println("</form>");
         		 out.println("</div>");

         		 
         		
         		 
         		 
         		out.println("</center>");
        	         //=======================BTC Price protection====================================================== 
 /*            		 out.println("<div style='text-align: center; margin-top: 10px;'>");
         	         //out.println("<h4>Buy Lock Coin</h4>");	    	         
             		 out.println("<form method='POST' action='BtcPriceProtection2.jsp' >");
             		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
             		 out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
             		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
             		 out.println("<input type='submit' value='GO TO BITCOIN PRICE PROTECTION'>");   	            
             		 out.println("</form>");
             		 out.println("</div>");
  */       		 
         	
             		 
        		 //=============================================================================
        		 out.println("</div>");
        		//out.println("<hr>");
        		out.println("</td>");
        		out.println("<td style='padding: 25px;'>");
//---------------------------price protect----------------------------------         		
        		out.println("<form action='BtcPriceProtection3.jsp' method='post' onsubmit='myButton.disabled = true; return true;'>");
        		out.println("<fieldset>");
        		out.println("<legend>Get Price Protection:</legend>");
        								
        		out.println("<input type='hidden' name='name' value='"+userName+"'>");
        		out.println("<input type='hidden' name='user_id' value='"+user_id+"'>");
        												
        		out.println("<input type='hidden' name='password' value='"+password+"'>");
        	    out.println("Amount of BTC you want to lock:<br>");
        		out.println("<input type='text' name='btcLockAmount' value=''></br></br>");
        		out.println("<input type='submit' name='myButton' value='Submit' class='btn btn-hero'></br>");
        		out.println("</fieldset>");
        		out.println("</form> ");        		
//---------------------------end price protect----------------------------         		
        		out.println("</td>");
        		out.println("<td style='margin: 25px;'>");
//---------------------------trade----------------------------------         		
        		out.println("<form action='TradeInfo2.jsp' method='post' onsubmit='myButton.disabled = true; return true;'>");
        		out.println("<fieldset>");
        		out.println("<legend>Trade:</legend>");          								
        		out.println("<input type='hidden' name='name' value='"+userName+"'>");          												
        		out.println("<input type='hidden' name='password' value='"+password+"'>");
        		out.println("Amount of BTC you want to trade:<br>");
        		out.println("<input type='text' name='btcTradeAmount' value=''></br></br>");
        		out.println("<input type='submit' name='myButton' value='Submit' class='btn btn-hero'></br>");
        		out.println("</fieldset>");
        		out.println("</form>");         		
//---------------------------end trade----------------------------            		
        		out.println("</td>");
        		out.println("<td style='margin: 25px;'>");
//------------deposit btc--------------------------------
        		 String form ="<form action='BTCDEP' method='POST' onsubmit='myButton.disabled = true; return true;'>"+
        				 
		  "<input type='text' id='BTC' name='BTC' value=''>"+
	  "<input type='hidden' id='email' name='email' value='"+email+"'>"+
	  
	  
	  "<input type='submit' name='myButton' value='Deposit/Withdraw BTC Amount' class='btn btn-hero'>"+
	  "</form>";
	     out.println(form);
	     out.println("</td>");
//-------------end deposit-------------------------------  
	     //------------ btc send--------------------------------
	     out.println("<td style='margin: 25px;'>");
		  form ="<form action='BTCSendUsd' method='POST' onsubmit='myButton.disabled = true; return true;'>"+
				 
"<input type='text' id='BTC' name='BTC' value=''>"+
"<input type='hidden' id='email' name='email' value='"+email+"'>"+


"<input type='submit' name='myButton' value='Send US Dollars Payments in BTC' class='btn btn-hero'>"+
"</form>";
out.println(form);
out.println("</td>");
//-------------end btc send------------------------------- 	     
	     
        		out.println("</tr>");
        		out.println("</table>");
  	        // ============buy lock coin section================================================================
  	         out.println("<div style='float: left; width:50%;  clear: right;  border-top: 1px solid gray; margin-top: 20px; padding-top: 20px;'>");
  	         out.println("<center>");
  	         out.println("<h3>Buy Lock Coin</h3>");
  	         
      		 out.println("<form method='POST' action='BuyLock' onsubmit='myButton.disabled = true; return true;'>");
      		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
      		 out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
      		 out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
      		 out.println("<input type='submit' value='Buy Lock Coin' class='btn btn-hero'>");   	            
      		 out.println("</form>");
      		 out.println("</center>");
/*//---------------------modal---------------------------------------------
      		 
      		 out.println("<!-- Trigger the modal with a button -->");
      				 out.println("<button type='button' class='btn btn-info btn-lg' data-toggle='modal' data-target='#myModal'>Open Modal</button>");

      				out.println("<!-- Modal -->");
      				//out.println("<div id='myModal' class='modal fade' role='dialog'>");
      				out.println("<div id='myModal' class='modal fade' role='dialog'>");
      				out.println("<div class='modal-dialog'>");

      				out.println("<!-- Modal content-->");
      			    out.println("<div class='modal-content'>");
      				out.println("<div class='modal-header'>");
      				out.println("<button type='button' class='close' data-dismiss='modal'>&times;</button>");
      				out.println("<h4 class='modal-title'>Modal Header</h4>");
      				out.println("</div>");
      				out.println("<div class='modal-body'>");
      				out.println("<p>Some text in the modal.</p>");
      				out.println("</div>");
      				out.println("<div class='modal-footer'>");
      				out.println("<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>");
      				out.println("</div>");
      				out.println("</div>");

      				out.println("</div>");
      				out.println("</div>");      		 
*////////////////////////////out.println("<td>");
// out.println("<tr>");
// out.println("<td colspan=2>");

//out.println("</td>");
//out.println("</td>");
//out.println("</tr>");
//------------------------------m2------------------------        		 
      		 
/*       		 
      		 out.println("<!-- Trigger/Open The Modal -->");
      		out.println("<button id='myBtn'>Open Modal</button>");
      		 out.println("<!-- The Modal -->"
      		 +"<div id='myModal' class='modal'>"

      		   +"<!-- Modal content -->"
      		   +"<div class='modal-content'>"
      		     +"<div class='modal-header'>"
      		       +"<span class='close'>&times;</span>"
      		       +"<h2>Buy Lock Confirmation</h2>"
      		     +"</div>"
      		     +"<div class='modal-body'>"
      		      +"<p>Some text in the Modal Body</p>"
      		       +"<p>Some other text...</p>"
      		     +"</div>"
      		     +"<div class='modal-footer'>"
      		       +"<h3>Modal Footer</h3>"
      		     +"</div>"
      		  +" </div>"

      		 +"</div> ");  
*/       		   		 
//-----------------end modal----------------------------------------------        		 
  	        //=============================================================================
        		 
    	  /*       out.println("<h3>Buy Escrow</h3>");
    	         
        		 out.println("<form method='POST' action='BuyLock' >");
        		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
        		 out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
        		 out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
        		 out.println("<input type='submit' value='Buy Escrow'>");   	            
        		 out.println("</form>");
*//*
    	         out.println("<h3>Sell Escrow</h3>");
    	         
        		 out.println("<form method='POST' action='BuyLock' >");
        		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
        		 out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
        		 out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
        		 out.println("<input type='submit' value='Buy Sell Escrow'>");   	            
        		 out.println("</form>");
*/         		 
    	        //=============================================================================

  	        out.println("</div>");
  	        

  	              		
     		 
  	        out.println("<div style='float: right; width:50%; border-top: 1px solid gray; margin-top: 20px;  padding-top: 20px;'>");
  	        // ==============sell lock coin section==============================================================
  	        out.println("<center>");
  	                         out.println("<h3>Sell Lock Coin</h3>");
  	    	        		 out.println("<form method='POST' action='SellLock' onsubmit='myButton.disabled = true; return true;'>");
  	    	        		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
  	    	        		 out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
  	    	        		 out.println("<h4>Amount of LockCoin to Sell </h4><input type='text' id='sellLockAmount' name='sellLockAmount' value=''>");
  	    	        		 out.println("<input type='submit' name='myButton' value='Sell Lock Coin' class='btn btn-hero'>");   	            
  	    	        		 out.println("</form>");
  	    	        		 out.println("</center>");     		 
  	    	        		 out.println(" <br/>");
  	    	        		 
  	    	    	       
  	    	        		// rs.close();
  	    	 //=============================================================================
  	    	     	        // ==============referal section==============================================================
  	    	     	        out.println("<center>");
  	    	     	                         out.println("<h3></h3>");
  	    	     	    	        		 out.println("<form method='POST' action='Refer' onsubmit='myButton.disabled = true; return true;' >");
  	    	     	    	        		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
  	    	     	    	        		 out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
  	    	     	    	        		 out.println(" Referral:<INPUT class='form-textbox' NAME='referer_email' SIZE=50 VALUE=' Enter email of person who introduced you. '> ");
  	    	     	    	        		 out.println("<input type='submit' name='myButton' value='Send Referral' class='btn btn-hero'>");   	            
  	    	     	    	        		 out.println("</form>");
  	    	     	    	        		 out.println("</center>");     		 
  	    	     	    	        		 out.println(" <br/>");
  	    	     	    	        		 
  	    	     	    	    	       
  	    	     	    	        		// rs.close();
  	    	     	    	 //=============================================================================
     		 
  	    	   out.println(" </div>");       		 
 /* 	         }else{
  	        	 
  	        	response.sendRedirect(request.getContextPath() + "/index.jsp");
 	        	 out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
  	        	 out.println(" <h2 style='text-align: center;'>LOG IN FAILED</h2>");
					 out.println("<div style='text-align: center;'>");
	    	         //out.println("<h4>Buy Lock Coin</h4>");	    	         
	        		 out.println("<form method='POST' action='login2.jsp' onsubmit='myButton.disabled = true; return true;' >");
	        		// out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
	        		// out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
	        		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
	        		 out.println("<input type='submit' name='myButton' value='RETURN TO LOGIN' class='btn btn-hero'>");   	            
	        		 out.println("</form>");
*/
  	         
       //        }
 	         // Clean-up environment
//  	         rs.close();
  	        // out.println("</body>");
  	        // out.println("</html>");
  	        
  	         
  	         //==================table of transactions============================
/*    	         sql = "SELECT * FROM transactions ;" ;
  	         rs = stmt.executeQuery(sql);
                out.println("<table class='fixed_header' border='1' style='font-size:8px'>");
	    	    	out.println("<tr>");
 	    		  out.println("<th>");
 	    			out.println("User Id");
 	    		  out.println("</th>");
 	    		  out.println("<th>");
 	    			out.println("from_coin_id");
 	    		  out.println("</th>");
 	    		  out.println("<th>");
 	    			out.println("to_coin_id");
 	    		  out.println("</th>");
 	    		  out.println("<th>");
 	    			out.println("amount_from");
 	    		  out.println("</th>");
 	    		  out.println("<th>");
 	    			out.println("amount_to");
 	    		  out.println("</th>");
 	    		  out.println("<th>");
 	    			out.println("current_btc_usd");
 	    		  out.println("</th>");
 	    		  out.println("<th>");
 	    			out.println("timeStamp");
 	    		  out.println("</th>");
 	    	    out.println("</tr>");

  	         // Extract data from result set
  	         while(rs.next()){
  	        	 int uid = rs.getInt("uid");
  	        	 int from_coin_id = rs.getInt("from_coin_id");
  	        	 int to_coin_id = rs.getInt("to_coin_id");
  	        	 double amount_from = rs.getDouble("amount_from");
  	        	 double amount_to = rs.getDouble("amount_to");
  	        	 double current_btc_usd = rs.getDouble("current_btc_usd");
  	        	 String timeStamp = rs.getString("date");
  	        			 
   	    	   
  	    	   out.println("<tr>");
	    		      out.println("<td>");
	    			     out.println(uid);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(from_coin_id);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(to_coin_id);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(amount_from);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(amount_to);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(current_btc_usd);
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(timeStamp);
	    		      out.println("</td>");
	    	       out.println("</tr>");
  	    	    
  	         }
  	         out.println("</table>");
  	         rs.close();
*/  	         //===================end table of transactions========================
//  	         stmt.close();
//  	         conn.close();
  	         out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
  	         out.println("<div style='text-align: center;'>");
  	         //out.println("<h4>Buy Lock Coin</h4>");	    	         
      		 out.println("<form method='POST' action='/second/index.jsp' onsubmit='myButton.disabled = true; return true;' >");
      		// out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
      		// out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
      		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
      		 out.println("<input type='submit' value='LOG OUT' class='btn btn-hero'>");   	            
      		 out.println("</form>");
      		 out.println("</div>");
/*  	      } catch(SQLException se) {
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
*/  	      request.getRequestDispatcher("includes/footer.jsp").include(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException {

    	   }
    private double getLockCoinPrice(IntReturner ir, DoubleReturner drCoinsAvailableInTier, Statement stmt) throws SQLException{
    	double coinsAvailableInTier=0;
    	double lockCoinValue=-1;
    	int tierCount=2;
    	ResultSet rs=null;
    	// String sql = "SELECT * FROM coin_stats where coin_name = '%lockcoin%';" ;
    	
    	// get rs
    	// Loop over result set
    		//
    		// If there are no available coins for at least $100 in coins
    			// Go to next record in rs
    		// If there are $100 in coins
    			// Sell
    			// Check if there are still cins left to sell
    				// if there are break
    				// if there
    	
    	
    	
		String sql = "SELECT * FROM coin_stats where coin_id =  "+tierCount+" ;" ;
		rs = stmt.executeQuery(sql);
        
		// Extract data from result set
		if(rs.next()){
			coinsAvailableInTier=rs.getDouble("available_coins");
			lockCoinValue = rs.getDouble("coin_usd_value");
		}
		
		while(coinsAvailableInTier*lockCoinValue<100.0&&tierCount<12){
			tierCount++;
			sql = "SELECT * FROM coin_stats where coin_id =  "+tierCount+" ;" ;
			rs = stmt.executeQuery(sql);
            
			// Extract data from result set
			if(rs.next()){
				coinsAvailableInTier=rs.getDouble("available_coins");
				lockCoinValue = rs.getDouble("coin_usd_value");
			}
		}
		drCoinsAvailableInTier.value=coinsAvailableInTier;
		ir.value=tierCount;
    	return lockCoinValue;
    }
}
