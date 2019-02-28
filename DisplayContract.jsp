<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
String auth=(String)session.getAttribute("auth");  
if(!auth.equals("1")){
	  
	  session.invalidate();//destroy any session that they may have
	 response.sendRedirect(request.getContextPath() + "/index.jsp");
	//  out.println("redirect "+request.getContextPath() + "/login2.jsp");
	 return;
}
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
// double btcTradeAmount = Double.parseDouble(request.getParameter("btcLockAmount"));
 //   String name=request.getParameter("name");
//	String password=request.getParameter("password");
//	double fourTimesTradeAmount = btcTradeAmount*4;
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

     // Execute SQL query
     stmt = conn.createStatement();
     //     String sql="INSERT INTO Protect (protect_id, name, password, unmatched) values (0, '"+name+"', '"+ password+"', "+btcTradeAmount+")";
    // stmt.executeUpdate(sql);
    // stmt.close();
    // conn.close();
   //  second.MatchingEngine.match(out2); 
  }catch(Exception e){
	  out.println(e);
  }
%>
<!--  <title>Calculators</title>
</head>
<body> -->
  <!--  img id="logo" src="https://www.ssaurel.com/cryptocoins/screenshots/web_hi_res_512.png" height="20" width="20" / -->
  <div id="data" ></div>
   <div style="text-align: center;"> 	
 <div style="display: inline-block;">  
  
  <form>
<br/><center>
 <h3> Matched Trade # <%=request.getParameter("match_id") %></h3> </center>
 <table style="background-color: #aaddaa; border: 1px solid black;  ">
   <tr>
         <th colspan="2" >Match Engine</th>	
   </tr>
   <tr>
         <td>Freeze BTC Opening</td><td ><input type="checkbox" name="freezeOpen" id="freezeOpen" value="" style="background-color: #ffffff;"/></td>
   </tr>  
   <tr>
         <td>Opening BTC price</td><td ><input type="text" name="c57" id="c57" value="" style="background-color: #ffffff;"/></td>
   </tr>  
 </table> 
  <br/>
 <table style="background-color: #aaddaa; border: 1px solid black;  " >
   <tr>
         <td  >Amount of BTC to LOCK</td><td style="background-color: #aaddaa; "><input type="text" name="e100" id=e100 value="" style="background-color: #ffffff;"/></td>	
   </tr>
   <tr>
         <td>Current  BTC Price to Open</td><td style="background-color: #aaddaa; ">	<input type="text" name="e101" id="e101" value="" style="background-color: #ffffff;" /></td>
   </tr>
   <tr>      
         <td>Current LOCK Price to Open</td><td style="background-color: #aaddaa; ">	<input type="text" name="e102" id="e102" value="" style="background-color: #ffffff;"/></td>
   </tr>  
 </table> 
 
  <br/>
 <table style="background-color: #aaddaa; border: 1px solid black;  " >
   <tr>
         <td  >BTC CLOSING PRICE</td><td style="background-color: #aaddaa; "><input type="text" name="e118" id="e118" value="" style="background-color: #ffffff;" /></td>	
   </tr>
   <tr>
         <td>LOCK CLOSING PRICE</td><td style="background-color: #aaddaa; ">	<input type="text" name="e119" id="e119" value=""  style="background-color: #ffffff;"/></td>
   </tr>
  
 </table> 
 <br/> 
 <table style="background-color: #aaddaa;"   >
   <tr>
         <th colspan="2" >Coin Lock Contract </th >	
   </tr>
   <tr>
         <td>Amount of Multiplier</td><td ><input type="text" name="e107" id="e107" value="" style="background-color: #ffffff;" /></td>
        
   </tr>  
 </table>
   <br/>
  <table style="background-color: #aaddaa;"   >
   <tr><th>Opening Protect BTC Balance</th><th>Opening 4x Multiplier BTC Balance</th></tr>
   <tr><td><input type="text" name=d110 id="d110" value="" style="background-color: #ffffff;"/></td><td><input type="text" name="e110" id="e110" value="" style="background-color: #ffffff;"/></td></tr>	
 </table>
    <br/>
  <table style="background-color: #aaddaa;"   >
   <tr><th>Percent +/- BTC</th><th>Percent +/- Leverage</th></tr>
   <tr><td><input type="text" name="pd110" id="pd110" value="" style="background-color: #ffffff;"/></td><td><input type="text" name="pe110" id="pe110" value="" style="background-color: #ffffff;"/></td></tr>	
 </table>
    <br/>
  <table style="background-color: #aaddaa;"   >
   <tr><th>Closing Protect BTC Balance</th><th>Closing 4x Multiplier BTC Balance</th></tr>
   <tr><td><input type="text" name="cd110" id="cd110" value="" style="background-color: #ffffff;"/></td><td><input type="text" name="ce110" id="ce110" value="" style="background-color: #ffffff;"/></td></tr>	
 </table>
    <br/>
  <table style="background-color: #aaddaa;"    >
   <tr><th>OPENING USD VALUE</th><th>CLOSING USD VALUE</th></tr>
   <tr><td><input type="text" name="d113" id="d113" value="" style="background-color: #ffffff;" /></td><td><input type="text" name="e113" id="e113" value="" style="background-color: #ffffff;"/></td></tr>	
 </table>
    	
 
 <br/>
    <table style="background-color: #aaddaa; border: 1px solid black; display: none;  ">
   <tr>
         <td colspan="2" > ESCROW ACCOUNT</td>	
   </tr>
   <tr>
         <td>BTC ESCROW BALANCE</td><td >	<input type="text" name="e125" id="e125" value="" /></td>
   </tr>  
   <tr>
         <td>LOCK ESCROW BALANCE</td><td >	<input type="text" name="e126" id="e126" value="" /></td>
   </tr>  
 </table> 
  <br/>
 <table style="background-color: #aaaadd; border: 1px solid black; display: none;  ">
   <tr>
         <td colspan="2" >CLOSE OUT DETAILS</td>	
   </tr>
   <tr>
         <td>LOCK COIN PURCHASED</td><td >	<input type="text" name="e130" id="e130" value="" /></td>
   </tr>  
   <tr>
         <td>LOCK COIN SOLD</td><td >	<input type="text" name="e131" id="e131" value="" /></td>
   </tr>  
   <tr>
         <td>NET GAIN in LOCK</td><td >	<input type="text" name="e132" id="e132" value="" /></td>
   </tr>  
   <tr>
         <td>5% of Gain LOCK BONUS to Protected Holder</td><td >	<input type="text" name="e133" id="e133" value="" style="background-color: #ffffff;"/></td>
   </tr>  
   <tr>
         <td>USD LOCK Bonus Value</td><td >	<input type="text" name="e135" id="e135" value="" style="background-color: #ffffff;"/></td>
   </tr>  
   <tr>
         <td>USD LOCK Bonus to Referer</td><td >	<input type="text" name="e136" id="e136" value="" style="background-color: #ffffff;"/></td>
   </tr>  
   <tr>
         <td>Return Price Protected Coinholder</td><td >	<input type="text" name="e137" id="e137" value="" style="background-color: #ffffff;"/></td>
   </tr>  
   <tr>
         <td>Contract Gain/Loss</td><td >	<input type="text" name="e138" id="e138" value="" style="background-color: #ffffff;"/></td>
   </tr>  
   <tr>
         <td>Coin Lock Fee</td><td>	<input type="text" name="e139" id="e139" value="" style="background-color: #ffffff;"/></td>
   </tr>  
   <tr>
         <td>BTC to MATCHENGINE</td><td >	<input type="text" name="e140" id="e140" value="" style="background-color: #ffffff;"/></td>
   </tr>  
 </table> 
<input type="button" onclick="getLocPrice()" class='btn btn-hero' value="Calculate"/> 
 </form>
 
 </div>
 <div style="float: right;"><form action="ExitMatchTrade" method="post"> 
 <h4>Closing Trade Multiplier BTC Balance:</h4>
 <input type='hidden' id="trade_id" name="trade_id" value="<%=request.getParameter("trade_id") %>">
 <input type='hidden' id="trade_email" name="trade_email" value="<%=request.getParameter("trade_email") %>">
 <input type="hidden" name="match_id" id="match_id" value="<%=request.getParameter("match_id") %>" />
 <input type="hidden" name="protect_email" id="protect_email" value="<%=request.getParameter("protect_email") %>" />
 <input type="hidden" name="exit_cd110" id="exit_cd110" value="" />
 
 <input type="text" name="exit_ce110" id="exit_ce110" value="" style="background-color: #ffffff;"/>
 <input type="submit" class='btn btn-hero' value="Exit"/> 
  </form></div>
 </div> 
 <% 
/* out.println("<br/>");
					  out.println("<div style='background-color: #dddddd; margin-left:auto; margin-right: auto; width: 200px; padding: 15px;'>");
		    	         
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
 */%>		        		 
		        		 
		        		 
  <script type="text/javascript">
  
  
//  document.getElementById("myText").value = "Johnny Bravo";

// <input type="text" name="firstname" id="firstname"/>
  var json="";
  <% IntReturner ir = new IntReturner();
  DoubleReturner drCoinsAvailableInTier =new DoubleReturner();
  %>
  var locPrice=<%= getLockCoinPrice(ir, drCoinsAvailableInTier) %>;
  function getBtcPrice()
  {
    var xmlhttp = new XMLHttpRequest();
    var url = "https://api.coindesk.com/v1/bpi/currentprice.json";
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4  &&  this.status == 200) {
       json = JSON.parse(this.responseText);
        parseJson(json);
      }		
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }     
  function getLocPrice()
  {
    var xmlhttp = new XMLHttpRequest();
    var url = "https://localhost/second/SendLockCoinPrice";
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4  &&  this.status == 200) {
       locPrice = this.responseText;
      
      }		
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }    
    function parseJson(json) {
      var time = "<b>Last Updated : " + json["time"]["updated"] + "</b>";
      var usdValue = "1 BTC equals to $" + json["bpi"]["USD"]["rate"];
      var gbpValue = "1 BTC equals to &pound;" + json["bpi"]["GBP"]["rate"];
      var euroValue = "1 BTC equals to &euro;" + json["bpi"]["EUR"]["rate"];

      var lockCoinValue = "1 LOCKCOIN equals to $" +locPrice ;
      document.getElementById("data").innerHTML = time + 
	       "<br /><br />" + usdValue + 
		   "<br />" + lockCoinValue ; 
		     var c57 = document.getElementById("c57");  		    
		     var e100 = document.getElementById("e100"); 
		     var e101=document.getElementById("e101");
		     var e102 = document.getElementById("e102"); 
		     var e107 = document.getElementById("e107"); 
		     var d110 = document.getElementById("d110"); 
		     var cd110 = document.getElementById("cd110"); 
		     var pd110 = document.getElementById("pd110"); 
		     var e110 = document.getElementById("e110"); 
		     var pe110 = document.getElementById("pe110"); 
		     var ce110 = document.getElementById("ce110"); 
		     var d113 = document.getElementById("d113"); 
		     var e113 = document.getElementById("e113"); 
		     var e118 = document.getElementById("e118"); 
		     var e119 = document.getElementById("e119"); 
		     var e125 = document.getElementById("e125"); 
		     var e126 = document.getElementById("e126"); 
		     var e130 = document.getElementById("e130"); 
		     var e131 = document.getElementById("e131"); 
		     var e132 = document.getElementById("e132"); 
		     var e133 = document.getElementById("e133"); 
		     var e135 = document.getElementById("e135"); 
		     var e136 = document.getElementById("e136"); 
		     var e137 = document.getElementById("e137"); 
		     var e138 = document.getElementById("e138"); 
		     var e139 = document.getElementById("e139"); 
		     var e140 = document.getElementById("e140");
		     var exit_cd110 = document.getElementById("exit_cd110");
		     var exit_ce110 = document.getElementById("exit_ce110");
		     var freezeOpen = document.getElementById("freezeOpen");
		     <% double btcLockAmount = Double.parseDouble(request.getParameter("protect_amount_matched")); 
		     double bitcion_price_at_match = Double.parseDouble(request.getParameter("bitcion_price_at_match"));
		     double fourTimesTradeAmount = btcLockAmount*3;
		     %>
		     e107.value=3;
		     e100.value=<%=btcLockAmount%>;
		     if(freezeOpen.checked != true){
		    	 c57.value = <%=bitcion_price_at_match%>;
		     }
		        
		     if(freezeOpen.checked != true){	     
		           e101.value=<%=bitcion_price_at_match%>;
		     }
		     //freezeOpen.checked = true;
		     document.getElementById("freezeOpen").checked = true;
		    e102.value =locPrice;
			d110.value=e100.value;
    		e110.value=d110.value/e107.value;
           // d113.value=(d110.value+e110.value)*e101.value;
            d113.value=(parseFloat(d110.value)+parseFloat(e110.value)) *(e101.value.replace(/,/g, ''));
           // d113.value=Math.round((e101.value.replace(/,/g, ''))*10000;
               // d113.value=3;//////////////////////////test
           // e113.value=(e110.value+d110.value)*e118.value;
//            e118.value=6000;     //set by user
            e113.value=(parseFloat(e110.value)+parseFloat(d110.value))*e118.value;
//            e119.value=2.95;     //set by user
            
//ask allan            e125.value=(H183.value)*e100.value;
        //    e125.value=(c57.value/e118.value)*e100.value;
         if(freezeOpen.checked == true){
        	 e118.value = (json["bpi"]["USD"]["rate"]).replace(/,/g, '');
         }
            e125.value=parseFloat(c57.value/e118.value).toFixed(8);
 /////////////new///////////////////////////
//         cd110.value=parseFloat(e125.value)-parseFloat(d110.value);
          var diff2=1-parseFloat(e125.value);    
          cd110.value=parseFloat(d110.value)-(diff2*d110.value);
        // cd110.value=parseFloat(e125.value).toFixed(8);
         var diff=parseFloat(d110.value)-parseFloat(cd110.value);
         ce110.value=(parseFloat(e110.value)+diff).toFixed(8);
         exit_cd110.value=cd110.value;
         exit_ce110.value=ce110.value;
         pd110.value=parseFloat(-diff*100/d110.value).toFixed(4);
         pe110.value=parseFloat(diff*100/e110.value).toFixed(4);
 ///////////////end new/////////////////////////
  //worng  		e130.value=(e130.value*e102.value)/e102.value;
    		e130.value=(e125.value*e118.value)/e102.value;
    		e126.value=e130.value;//moved down 1
    		e131.value=(e130.value*e102.value)/e119.value;
    		e132.value=parseFloat(e130.value)-parseFloat(e131.value);
    		e133.value=e132.value*0.05;
    		e135.value=e133.value*e119.value;
    		e136.value=e135.value*0.25;
    		e137.value=e135.value/(e125.value*e118.value);
    		if(parseFloat(e113.value)-parseFloat(d113.value)>0.0){
    			e138.value=parseFloat(e113.value)-parseFloat(d113.value);
    		}else{
    			e138.value=0.0;
    		}
    		if(e138.value*0.1>0.0){
    			e139.value=e138.value*0.1;
    		}else{
    			e139.value=0.0;
    		}
    		if(e139.value/e118.value>0.0){
    			e140.value=e139.value/e118.value;
    		}else{
    			e140.value=0.0;
    		}
  //  		if( !isNaN(e119.value)&&parseFloat(e119.value)<parseFloat(e102.value)){
  //  			e119.value=e102.value;
  //  		}
    		//h101=e125*e118
    	//???????c183 =	(e130.value*e102.value)done
    	//c183=h101	=e125*e118	
    	//b182 =e100	
    	//h183=(c57.value/e118.value)	changed from d182/e118
    }
  var myVar = setInterval(getBtcPrice, 100);
   
</script>
     <%@ page import="java.sql.*" %>
     <%
      try{  
    	  out.println("<table width='100%' style='border-style: none;'><tr><td style='border-style: none;'>");
     String sql="Select * from Protect where name='"+request.getParameter("trade_email")+"' ORDER BY protect_id DESC;";
     ResultSet rs = stmt.executeQuery(sql);
     out.println("<h4>Protect</h4>");
     out.println("<center><table>");
     out.println("<tr><td>protect_id</td><td >name</td><td>unmatched</td><td>matched</td>");
     // Extract data from result set
     while(rs.next()){
        //Retrieve by column name
        int protect_id  = rs.getInt("protect_id");
        //int age = rs.getInt("age");
        String name = rs.getString("name");
        String password = rs.getString("password");
       
        double unmatched = rs.getDouble("unmatched");
        double matched = rs.getDouble("matched");
        
        out.println("<tr><td>"+protect_id+"</td><td>"+name+"</td><td>"+unmatched+"</td><td>"+matched+"</td></tr>");
     }
     out.println("</table></center>");
     out.println("</td><td style='border-style: none;'>");
     rs.close();
     sql="Select * from Trading where name='"+request.getParameter("trade_email")+"' ORDER BY trade_id DESC;";
      rs = stmt.executeQuery(sql);
      out.println("<center><h4>Trade</h4>");
      out.println("<table>");
      out.println("<tr><td>trade_id</td><td>name</td><td>unmatched</td><td>matched</td></tr>");
     // Extract data from result set
     while(rs.next()){
        //Retrieve by column name
        int trade_id  = rs.getInt("trade_id");
        //int age = rs.getInt("age");
        String name = rs.getString("name");
        String password = rs.getString("password");
       
        double unmatched = rs.getDouble("unmatched");
        double matched = rs.getDouble("matched");
        out.println("<tr><td>"+trade_id+"</td><td>"+name+"</td><td>"+unmatched+"</td><td>"+matched+"</td></tr>");
     }
     out.println("</table></center>");
     out.println("</td></tr></table>");
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
/*		final String DB_URL="jdbc:mysql://54.214.194.247/cl";

		//  Database credentials
		final String USER = "cl";
		final String PASS = "95ODVqCd8T7mfivU";
*/
final String DB_URL="jdbc:mysql://3.16.62.130/cl"; // Mysql host and database
final String USER = "cl"; // DB User
final String PASS = "Ra.cQH&&ZrFG(44e)Uf"; // DB PasswordConnection conn=null;		
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