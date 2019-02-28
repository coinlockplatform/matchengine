<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>
<!--  DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"-->


<%@page import="java.io.*, java.sql.*,java.net.URL,javax.json.*,javax.servlet.*,javax.servlet.http.* "%>


<jsp:include page="includes/header.jsp" />
   <br/><br/><br/><br/><br/><br/><br/> 
<% 	


		//  PrintWriter out = response.getWriter();
		 // out.println("Hello "+ request.getParameter("username"));
		  
//		  String userName = request.getParameter("username");
//	      String password = request.getParameter("password");
//	      String submit = request.getParameter("submit");

              session = request.getSession();
		      String auth=(String)session.getAttribute("auth");
		     // out.println("<h1>String auth="+auth+"</h1>"); 
		      if(auth==null||!auth.equals("1")){
		    	  out.println("<h1>String auth="+auth+"</h1>"); 
		    	  session.invalidate();//destroy any session that they may have
		    	 response.sendRedirect(request.getContextPath() + "/index.jsp");
		    	//  out.println("redirect "+request.getContextPath() + "/login2.jsp");
		    	 return;
		      }
			  String userName = (String)session.getAttribute("username");
			  int user_id = Integer.parseInt((String)session.getAttribute("user_id"));
		      String password = (String)session.getAttribute("password");
		      String submit = (String)session.getAttribute("auth");
		      
	      
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
 	      //  Database credentials
 	       final String USER = "cl";
 	       final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
	  	    Connection conn=null;
	  	    Statement stmt=null;
%>	  	    
<% 	 
//out.println("<h1>sss="+userName+"==sss</h1>");
           if( userName != null){
	    	 // HttpSession session = request.getSession();
	    	//  session.invalidate();//destroy any session that they may have
	    	  
	    	//  out.println("<h1>(userName != null)="+userName+"</h1>");

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
	  	      // out.println("<h1>"+userName+"</h1>");
	  	         if(rs.first()){
	   	    	     session = request.getSession();
	  	    	     session.setAttribute("username", userName);
	  	    	     session.setAttribute("password", password);
	  	    	     session.setAttribute("auth", "1");
	   	    	     status = rs.getBoolean("approved");
	    	            
	    	         email = rs.getString("email");
	    	         //out.println("<h1>"+email+"</h1>");
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
	  	        	
	  	        	response.sendRedirect(request.getContextPath() + "/index.jsp");
	  	        	return;
	  	         }
	  	         
	  	      }catch(Exception e){
	  	    	  out.println(e);
	  	      }
	    	  	    	  
	      }else{

	    	 
	    	  
	    	  session = request.getSession();
	    	  session.invalidate();
	    	  response.sendRedirect(request.getContextPath() + "/index.jsp");
		    //  String auth=(String)session.getAttribute("auth");
		     // out.println("<h1>String auth="+auth+"</h1>"); 
		      
	    	   
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

	    	  //  request.getRequestDispatcher("includes/header.jsp").include(request, response);
	      
  	      // Set response content type
  	      response.setContentType("text/html");
  	     
  	      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
  	      response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
  	      response.setDateHeader("Expires", 0); // Proxies.
  	      String title = "Match Engine Wallet";
%>  	      
  	   <br/><br/><br/><br/>   
  <!--    <style>  .fixed_header { display:block; height: 300px; overflow-y: scroll;} .fixed_header tbody{  display:block;  overflow:auto;  height:20px;  width:100%;} .fixed_header thead tr{  display:block;} </style>-->	
<%   	    //  Connection conn=null;
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

    	    	    
*/

%>  
	    	    <table>
  	    	   <tr>
  	    	   <td style='margin: 25px;'>
  	    	   <div style='float: right;'>
  	    	   <br/><br/><br/><br/><h5 align = "center"><%=title%> </h5>
	    	 	             <h5 align ="center" ><%=email %> </h5>

  	            <!--Display values-->
    	    	    
  	    	    <center>
      
  	    	    <table class='fixed_header' border='1' style='font-size:12px; margin-top=70px;'>
  	    	    
  	    	    
  	    	    

	    	    	<tr>
  	    		  <td>
  	    			Current BTC Balance
  	    		  </td><td style='padding-left: 20px;'>
  	    		  		<b><%=bitCoin %></b>
  	    		  </td>
  	    	    </tr>
  	    	    <tr>
	    		  <td>
	    			Pending BTC Deposit
	    		  </td><td style='padding-left: 20px;'>
	    		  		<b><%=PendingBTCDep%></b>
	    		  </td>
	    	    </tr>
	    	  <tr>
  		  <td>
  			Pending BTC Withdrawal
  		  </td><td style='padding-left: 20px;'>
  		  		<b><%=PendingBTCWithdrawal%></b>
  		  </td>
  	    </tr>
  	    	   <tr>
	    		      <td>
	    			     Current LOCK Balance
	    		      </td>
	    		      <td style='padding-left: 20px;'>
	    			     <%=lotCoin%>
	    		      </td>
	    	       </tr>
	    	       <tr>
	    		      <td>
	    			     Pending LOCK Withdrawal
	    		      </td>
	    		      <td style='padding-left: 20px;'>
	    			     <%=PendingLOCKWith%>
	    		      </td>
	    	
	    	       
	    	       
 	    	    </table>
	    	    </center>
	    	    

	    	   
	    	    <br/>

  	            
  	         
 <%  				//--------------get BitCoin price---------------------------------------------------------------------
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
 %> 				
  			<div style='text-align: center; margin-bottom: 50;'>	
  	        <h5>BITCOIN $ PRICE: <%= bitCoinValue%></h5>
  	        <h5>LOCKCOIN $ PRICE: <%= lockCoinValue%></h5>
  	        <h5>LOCKCOIN LEFT AT THIS PRICE:<%=coinsAvailableInTier %></h5>
  	        </div>
  	         </div>
<!-- ------------------------------------------------->
  	         </td>
  	        <td style='padding: 25px;'>
   	       <div style='float: left; margin: auto;  width:20%;'>
    	        
   	       <center>	
      		<!-- ==============trading===============================================================================-->
       		 
       		 <div style='text-align: center; margin-top: 100px;'>
   	             	         
       		 <form method='POST' action='Trading2.jsp' onsubmit='myButton.disabled = true; return true;'>
       		 <input type='hidden' id='username' name='username' value='<%=userName%>'>
       		 <input type='hidden' id='password' name='password' value='<%=password%>'>
       		 <input type='hidden' id='user_id' name='user_id' value='<%=user_id%>'>
       		 
       		 
       		 <h6>Amount you want to Autotrade </h6><input type='text' id='autoTrade' name='autoTrade' value=''>
       		 <input type='submit' name='myButton' value='   Trade   ' class='btn btn-hero'>   	            
       		 </form>
       		 </div>
   	      <!-- =======================Lock Bitcoin======================================================--> 
   	        <div style='text-align: center; margin-top: 10px;'>
            		
    	            	         
        		 <form method='POST' action='DispalyTransactions'onsubmit='myButton.disabled = true; return true;' >
        		 <input type='hidden' id='username' name='username' value='<%=userName%>'>
        	
        		 
        		 <input type='hidden' id='user_id' name='user_id' value='<%=user_id%>'>
        		 <input type='submit' name='myButton' value='   Escrow Protected Bitcoin  ' class='btn btn-hero'>   	            
        		 </form>
        		 </div>

    	       <!-- =======================Transaction Information======================================================--> 
         		 <div style='text-align: center; margin-top: 10px;'>
     	          <!--//<h4>Buy Lock Coin</h4>	-->    	         
         		 <form method='POST' action='DispalyTransactions' onsubmit='myButton.disabled = true; return true;' >
         		 <input type='hidden' id='username' name='username' value='<%=userName%>'>
         		
         		 <input type='hidden' id='user_id' name='user_id' value='<%=user_id%>'>
         		 
         		 
         		 <!-- //<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>-->
         		 <input type='submit' name='myButton' value='Transaction Information' class='btn btn-hero'>   	            
         		 </form>
         		 </div>

         		 
         		
         		 
         		 
         		</center>
        	        <!--=======================BTC Price protection====================================================== 
 /*            		 <div style='text-align: center; margin-top: 10px;'>
         	         //<h4>Buy Lock Coin</h4>	    	         
             		 <form method='POST' action='BtcPriceProtection2.jsp' >
             		 <input type='hidden' id='username' name='username' value='"+userName+"'>
             		 <input type='hidden' id='password' name='password' value='"+password+"'>
             		 //<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>
             		 <input type='submit' value='GO TO BITCOIN PRICE PROTECTION'>   	            
             		 </form>
             		 </div>
  */       		 
         	
             		 
        		 =============================================================================-->
        		 </div>
        		
        		</td>
        		<td style='padding: 25px;'>
<!-- ---------------------------price protect---------------------------------- -->         		
        		<form action='BtcPriceProtection3.jsp' method='post' onsubmit='myButton.disabled = true; return true;'>
        		<fieldset>
        		<legend>Price Protection:</legend>
        								
        		<input type='hidden' name='name' value='<%=userName%>'>
        		<input type='hidden' name='user_id' value='<%=user_id%>'>
        												
        		<input type='hidden' name='password' value='<%=password%>'>
        	    Amount of BTC you want to lock:<br>
        		<input type='text' name='btcLockAmount' value=''></br></br>
        		<input type='submit' name='myButton' value='Submit' class='btn btn-hero'></br>
        		</fieldset>
        		</form>         		
<!-- --------------------------end price protect------------------------------>         		
        		</td>
        		<td style='margin: 25px;'>
<!-- ---------------------------trade----------------------------------    -->     		
        		<form action='TradeInfo2.jsp' method='post' onsubmit='myButton.disabled = true; return true;'>
        		<fieldset>
        		<legend>Trade:</legend>          								
        		<input type='hidden' name='name' value='<%=userName%>'>          												
        		<input type='hidden' name='password' value='<%=password%>'>
        		Amount of BTC you want to trade:<br>
        		<input type='text' name='btcTradeAmount' value=''></br></br>
        		<input type='submit' name='myButton' value='Submit' class='btn btn-hero'></br>
        		</fieldset>
        		</form>         		
<!-- ---------------------------end trade---------------------------- -->           		
        		</td>
        		<td style='margin: 25px;'>
<!-- ------------deposit btc---------------------------------->
        		<form action='BTCDEP' method='POST' onsubmit='myButton.disabled = true; return true;'>
        				 
		 <input type='text' id='BTC' name='BTC' value=''>
	 <input type='hidden' id='email' name='email' value='<%=email%>'>
	  
	  
	 <input type='submit' name='myButton' value='Deposit/Withdraw BTC Amount' class='btn btn-hero'>
	 </form>
	    
	     </td>
<!-- -------------end deposit-------------------------------  -->
	    <!-- ------------ btc send---------------------------------->
	     <td style='margin: 25px;'>
	     
		  
<form action='BTCSendUsd' method='POST' onsubmit='myButton.disabled = true; return true;'>
				 
<input type='text' id='BTC' name='BTC' value=''>
<input type='hidden' id='email' name='email' value='<%=email%>'>


<input type='submit' name='myButton' value='Send US Dollars Payments in BTC' class='btn btn-hero'>
</form>

</td>
<!-- -------------end btc send------------------------------- -->	     
	     
        		</tr>
        		</table>
  	       <!-- ============buy lock coin section================================================================-->
  	         <div style='float: left; width:50%;  clear: right;  border-top: 1px solid gray; margin-top: 20px; padding-top: 20px;'>
  	         <center>
  	         <h3>Buy Lock Coin</h3>
  	         
      		 <form method='POST' action='BuyLock' onsubmit='myButton.disabled = true; return true;'>
      		 <input type='hidden' id='username' name='username' value='"+userName+"'>
      		 <input type='hidden' id='password' name='password' value='"+password+"'>
      		 <h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>
      		 <input type='submit' value='Buy Lock Coin' class='btn btn-hero'>   	            
      		 </form>
      		 </center>

      		 
      		 
    	       

  	        </div>
  	        

  	              		
     		 
  	        <div style='float: right; width:50%; border-top: 1px solid gray; margin-top: 20px;  padding-top: 20px;'>
  	        <!-- ==============sell lock coin section==============================================================-->
  	        <center>
  	                         <h3>Sell Lock Coin</h3>
  	    	        		 <form method='POST' action='SellLock' onsubmit='myButton.disabled = true; return true;'>
  	    	        		 <input type='hidden' id='username' name='username' value='<%=userName%>'>
  	    	        		 <input type='hidden' id='password' name='password' value='<%=password%>'>
  	    	        		 <h4>Amount of LockCoin to Sell </h4><input type='text' id='sellLockAmount' name='sellLockAmount' value=''>
  	    	        		 <input type='submit' name='myButton' value='Sell Lock Coin' class='btn btn-hero'>   	            
  	    	        		 </form>
  	    	        		 </center>     		 
  	    	        		  <br/>
  	    	        		 
  	    	    	       
  	    	
  	    	     	        <!--  ==============referal section==============================================================-->
  	    	     	        <center>
  	    	     	                         <h3></h3>
  	    	     	    	        		 <form method='POST' action='Refer' onsubmit='myButton.disabled = true; return true;' >
  	    	     	    	        		 <input type='hidden' id='username' name='username' value='<%=userName%>'>
  	    	     	    	        		 <input type='hidden' id='password' name='password' value='<%=password%>'>
  	    	     	    	        		  Referral:<INPUT class='form-textbox' NAME='referer_email' SIZE=50 VALUE=' Enter email of person who introduced you. '> 
  	    	     	    	        		 <input type='submit' name='myButton' value='Send Referral' class='btn btn-hero'>   	            
  	    	     	    	        		 </form>
  	    	     	    	        		 </center>     		 
  	    	     	    	        		  <br/>
  	    	     	    	        		 
  	    	     	    	    	       
  	    	     	    	        		
 
     		 
  	    	    </div>       		 


  	        <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
  	         <div style='text-align: center;'>
  	          	         
      		 <form method='POST' action='/second/index.jsp' onsubmit='myButton.disabled = true; return true;' >
      		 <input type='submit' value='LOG OUT' class='btn btn-hero'>   	            
      		 </form>
      		 </div>
<jsp:include page="includes/footer.jsp" />
<%
//request.getRequestDispatcher("includes/footer.jsp").include(request, response);
	
%>	
<%!
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
class IntReturner{
	int value=-1;
}
class DoubleReturner{
	double value=-1;
}
%>