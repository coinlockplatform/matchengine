<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
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
String name = (String)session.getAttribute("username");
int user_id = Integer.parseInt((String)session.getAttribute("user_id"));
String password = (String)session.getAttribute("password");
%>
<jsp:include page="includes/header.jsp" />
<!--  html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
-->
<style type="text/css">
  #data {
    width: 400px;
    border: 1px dashed  black;
    font-size: 20px;
    text-align: center;
    margin: 0 auto;
    margin-top: 50px;
    padding: 10px;
  }
  #logo {
    width: 320px;
    height: 320px;
    margin: 0 auto;
    margin-top: 50px;
    display: block;
  }
  table, th, td {
  border: 1px solid black;
}
td { text-align: center;
}
input[type="text"] {
    background-color: #bbbbbb;
}
</style>
<%@page import="java.io.PrintWriter, java.sql.*"%>
 <%
 PrintWriter out2 = response.getWriter();
 double btcTradeAmount = Double.parseDouble(request.getParameter("btcTradeAmount"));
 
		//double fourTimesTradeAmount = btcTradeAmount*4;
    //JDBC driver name and database URL
   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   final String DB_URL="jdbc:mysql://3.16.62.130/cl";

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
     int trade_id=0;
     // Execute SQL query
     stmt = conn.createStatement();
     if(btcTradeAmount!=0){
//	     String sql="INSERT INTO Trading (trade_id, name, password, unmatched) values (0, '"+name+"', '"+ password+"', "+btcTradeAmount+");";
	     String sql="INSERT INTO Trading (trade_id, name, password, unmatched) values (0, ?, ?, ?);";
	    // stmt.executeUpdate(sql);
	     //--------------------------------------
			PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,name);
			ps.setString(2,"");
			ps.setDouble(3, btcTradeAmount);
			
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			
			if(rs.next()){
				trade_id=rs.getInt(1);
			}
	
	     //-------------------------------------
	     sql="select btc_holdings from users where email='"+name+"';";
	      rs = stmt.executeQuery(sql);
	     
	     rs.next();
	      double bitCoin = rs.getDouble("btc_holdings");
	      rs.close();
	      double newBtcHoldings = bitCoin-btcTradeAmount;
	      if(newBtcHoldings>=0){
	           sql="UPDATE users set btc_holdings="+newBtcHoldings+" where email='"+name+"';";
	           stmt.executeUpdate(sql);
	      }
	      else{
	    	 out2.println("<h2>Not enough BTC in account for transaction</h2>");
	      }
	      
	     second.MatchingEngine.match(out2); 
	    sql="select unmatched, matched from Trading where trade_id="+trade_id+" ;";			 
	    rs = stmt.executeQuery(sql);			 
	    if(rs.next()){
	    	double unmatched = rs.getDouble("unmatched");
	    	out2.println("<h2>Trade "+unmatched+" is UNMATCHED</h2>");
	    	double matched = rs.getDouble("matched");
	    	out2.println("<h2>Trade "+matched+" is MATCHED</h2>");
	    }
	   //  stmt.close();
	   //  conn.close();
	     
	     }
  }catch(Exception e){
	  out2.println("error"+e);
  }
%>
<!--  <title>Calculators</title>
</head>
<body> -->
  <!--  img id="logo" src="https://www.ssaurel.com/cryptocoins/screenshots/web_hi_res_512.png" height="20" width="20" / -->
  <div id="data" ></div>
   <div style="text-align: center;"> 	
 <div style="display: inline-block;"> 
 <%
 out.println("<div style='text-align: center;'>");
 out.println("<form method='POST' action='dashboard.jsp' >");
  out.println("<input type='submit' value='RETURN TO HOME' class='btn btn-hero'>");   	            
  out.println("</form></div>");
 %> 
 
 
 
 </div>
 </div> 
 <% 
 out2.println("<br/>");
 /*	  out.println("<div style='background-color: #dddddd; margin-left:auto; margin-right: auto; width: 200px; padding: 15px;'>");
		    	         
		   	        // out.println(" <br/>");
		        		 out.println("<form name='calc' method='POST'  >");
		        		 out.println("Cash Out Bitcoin");
		        		 out.println("<h6><input type='text' id='btcToSell' name='btcToSell' value='' size='10' >Sell BTC </h6>");
		        		 out.println("<h6><input type='text' id='btcPrice' name='btcPrice' value=''  size='10' >BTC Price </h6>");
		        		 
		        		 out.println("<h6><input type='checkbox' id='autoLeverage75' name='autoLeverage75' value='' size='10' >75% Auto Leverage </h6>");
		        		 out.println("<h6><input type='text' id='Leverage Auto' name='LevAuto' value=''>Auto Leverage in BTC</h6>");
		        		 out.println("<h6><input type='text' id='netSale' name='netSale' value=''>Net Cash Out USD</h6>");
		        		 out.println("<input type=button onClick='showpay()' value='Confirm'>");   	            
		        		 out.println("</form>");
		        		 out.println(" </div>"); 
		        		 */
		        		
		        		 out2.println("<form action='DeleteUnMatched' method='POST'  >");
		        		 out2.println("Delete Pending Orders");
		        		// out2.println("<input type='hidden' id='name' name='name' value='"+name+"'>");
		         		// out2.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
		         		out2.println("<input type='hidden' id='btcLockAmount' name='btcLockAmount' value='"+btcTradeAmount+"'>");
		        		 out2.println("<input type=Submit value='Delete' class='btn btn-hero'>");   	            
		        		 out2.println("</form>");
		        		 
		        		 
	
 %>		        		 
		        		 
		        		 
 
     <%@ page import="java.sql.*" %>
     <%
      try{  
     String sql="Select * from Protect where name='"+name+"'  ORDER BY protect_id DESC;";
     ResultSet rs = stmt.executeQuery(sql);
     out2.println("<h4>Protect</h4>");
     out2.println("<table>");
     out2.println("<tr><td>protect_id</td><td>name</td><td>unmatched</td><td>matched</td>");
     // Extract data from result set
     while(rs.next()){
        //Retrieve by column name
        int protect_id  = rs.getInt("protect_id");
        //int age = rs.getInt("age");
         name = rs.getString("name");
        
       
        double unmatched = rs.getDouble("unmatched");
        double matched = rs.getDouble("matched");
        
        out2.println("<tr><td>"+protect_id+"</td><td>"+name+"</td><td>"+unmatched+"</td><td>"+matched+"</td>");
     }
     out2.println("</table>");
     rs.close();
     sql="Select * from Trading where name='"+name+"'  ORDER BY trade_id DESC;";
      rs = stmt.executeQuery(sql);
      out2.println("<center><h4>Trade</h4>");
      out2.println("<table>");
      out2.println("<tr><td>trade_id</td><td>name</td><td>unmatched</td><td>matched</td>");
     // Extract data from result set
     while(rs.next()){
        //Retrieve by column name
        int trade_id  = rs.getInt("trade_id");
        //int age = rs.getInt("age");
         name = rs.getString("name");
         
       
        double unmatched = rs.getDouble("unmatched");
        double matched = rs.getDouble("matched");
        out2.println("<tr><td>"+trade_id+"</td><td>"+name+"</td><td>"+unmatched+"</td><td>"+matched+"</td>");
     }
     out2.println("</table></center>");
     rs.close();
     
      }catch(Exception e){
    	  out.println(e);
      }finally{
    	  stmt.close();
    	  conn.close();
      }
     %>
     <%!  
     
     
     private double getLockCoinPrice(IntReturner ir, DoubleReturner drCoinsAvailableInTier) throws SQLException{
     	double coinsAvailableInTier=0;
     	double lockCoinValue=-1;
     	int tierCount=2;
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL="jdbc:mysql://3.16.62.130/cl"; // Mysql host and database
		final String USER = "cl"; // DB User
		final String PASS = "Ra.cQH&&ZrFG(44e)Uf"; // DB Password

	      Connection conn=null;
	      Statement stmt=null;
	      ResultSet rs=null;
	      try {
	         // Register JDBC driver
	          Class.forName("com.mysql.jdbc.Driver");

	         // Open a connection
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);

	         // Execute SQL query
	         stmt = conn.createStatement();
     	
     	
     	
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
 		
	      }catch(SQLException se){
	    	  
	      }catch(Exception se){
	    	  
	      }finally{
	    	  rs.close();
	    	  stmt.close();
	    	  conn.close();
	      }
 		
 		
     	return lockCoinValue;
     }
     class IntReturner{
    		int value=-1;
    	}
    	class DoubleReturner{
    		double value=-1;
    	}
     %>
<!--  /body>
</html-->


<jsp:include page="includes/footer.jsp" />