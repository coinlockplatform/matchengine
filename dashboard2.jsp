<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.io.*, java.sql.*,java.net.URL,javax.json.*,javax.servlet.*,javax.servlet.http.* "%>
<jsp:include page="includes/header.jsp" />
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
String userName = (String)session.getAttribute("username");
int user_id = Integer.parseInt((String)session.getAttribute("user_id"));
String password = (String)session.getAttribute("password");
//String submit = (String)session.getAttribute("auth");

// Get the mysql driver
final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
// Set the host and database for mysql
final String DB_URL="jdbc:mysql://3.16.62.130/cl"; // Mysql host and database
final String USER = "cl"; // DB User
final String PASS = "Ra.cQH&&ZrFG(44e)Uf"; // DB Password

// Init variables
String email=userName;
String last=null;
double bitCoin =0;
double lotCoin =0 ;
double PendingBTCDep =0 ;
double PendingBTCWithdrawal =0 ;
double PendingLOCKWith =0 ;
boolean status = false;
//int user_id  = 0;

Connection conn=null;
Statement stmt=null;


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
    	//     session = request.getSession();
   	    // session.setAttribute("username", userName);
   	    // session.setAttribute("password", password);
   	    // session.setAttribute("auth", "1");
    	     status = rs.getBoolean("approved");
	            
	         //email = rs.getString("email");
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
response.setContentType("text/html");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
String title = "Match Engine Wallet";
%>  	      
<br/><br/><br/><br/>   
<!--    <style>  .fixed_header { display:block; height: 300px; overflow-y: scroll;} .fixed_header tbody{  display:block;  overflow:auto;  height:20px;  width:100%;} .fixed_header thead tr{  display:block;} </style>-->	

<table>
	<tr>
		<td style='margin: 25px;'>
			<div style='float: right;'>
				<br/><br/><br/><br/>
				<h5 align = "center"><%=title%> </h5>
				<h5 align ="center" ><%=email %> </h5>
				<!--Display values-->
				<center>
				<table class='fixed_header' border='1' style='font-size:12px; margin-top=70px;'>
					<tr>
						<td>
							Current BTC Balance
						</td>
						<td style='padding-left: 20px;'>
							<b><%=bitCoin %></b>
						</td>
					</tr>
					<tr>
						<td>
							Pending BTC Deposit
						</td>
						<td style='padding-left: 20px;'>
							<b><%=PendingBTCDep%></b>
						</td>
					</tr>
					<tr>
						<td>
							Pending BTC Withdrawal
						</td>
						<td style='padding-left: 20px;'>
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
					</tr>
				</table>
				</center>
	    	    <br/>
				<%  
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
				%> 				
				<div style='text-align: center; margin-bottom: 50;'>	
					<h5>BITCOIN $ PRICE: <%= bitCoinValue%></h5>
					<h5>LOCKCOIN $ PRICE: <%= lockCoinValue%></h5>
					<h5>LOCKCOIN LEFT AT THIS PRICE:<%=coinsAvailableInTier %></h5>
				</div>
			</div>
		</td>
		<td style='padding: 25px;'>
			<div style='float: left; margin: auto;  width:20%;'>
				<center>	
				<!-- ==============trading===============================================================================-->
				<div style='text-align: center; margin-top: 100px;'>
					<form method='POST' action='Trading2.jsp' onsubmit='myButton.disabled = true; return true;'>
						<h6>Amount you want to Autotrade </h6><input type='text' id='autoTrade' name='autoTrade' value=''>
						<input type='submit' name='myButton' value='   Trade   ' class='btn btn-hero'>   	            
					</form>
				</div>
				<!-- =======================Lock Bitcoin======================================================--> 
				<div style='text-align: center; margin-top: 10px;'>
					<form method='POST' action='MatchBTC'onsubmit='myButton.disabled = true; return true;' >
        				<input type='submit' name='myButton' value='   Buy BTC Position  ' class='btn btn-hero'>   	            
        			</form>
        		</div>
				<!-- =======================Transaction Information======================================================--> 
				<div style='text-align: center; margin-top: 10px;'>
					<!--//<h4>Buy Lock Coin</h4>	-->    	         
					<form method='POST' action='DispalyTransactions' onsubmit='myButton.disabled = true; return true;' >
						<!-- //<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>-->
						<input type='submit' name='myButton' value='Transaction Information' class='btn btn-hero'>   	            
					</form>
				</div>
				</center>
        	</div>
        </td>
        <td style='padding: 25px;'>
			<!-- ---------------------------price protect---------------------------------- -->         		
        	<form action='BtcPriceProtection3.jsp' method='post' onsubmit='myButton.disabled = true; return true;'>
        		<fieldset>
        			<legend>Get Price Protection:</legend>
        	    	Amount of BTC you want to lock:
					<br>
        			<input type='text' name='btcLockAmount' value=''>
					</br></br>
        			<input type='submit' name='myButton' value='Submit' class='btn btn-hero'></br>
        		</fieldset>
        	</form>         		
			<!----------------------------end price protect------------------------------>         		
        </td>
		<td style='margin: 25px;'>
			<!----------------------------trade----------------------------------    -->     		
			<form action='TradeInfo2.jsp' method='post' onsubmit='myButton.disabled = true; return true;'>
				<fieldset>
					<legend>Give Price Protection:</legend>          								
					Amount of BTC you want to trade:<br>
					<input type='text' name='btcTradeAmount' value=''></br></br>
					<input type='submit' name='myButton' value='Submit' class='btn btn-hero'></br>
				</fieldset>
			</form>         		
			<!----------------------------end trade---------------------------- -->           		
		</td>
		<td style='margin: 25px;'>
			<!-------------deposit btc---------------------------------->
			<form action='BTCDEP' method='POST' onsubmit='myButton.disabled = true; return true;'>
				<input type='text' id='BTC' name='BTC' value=''>
			
				<input type='submit' name='myButton' value='Deposit/Withdraw BTC Amount' class='btn btn-hero'>
			</form>
	    </td>
			<!---------------end deposit-------------------------------  -->
			<!-------------- btc send---------------------------------->
	    <td style='margin: 25px;'>
			<form action='BTCSendUsd' method='POST' onsubmit='myButton.disabled = true; return true;'>
				<input type='text' id='BTC' name='BTC' value=''>
				
				<input type='submit' name='myButton' value='Send US Dollars Payments in BTC' class='btn btn-hero'>
			</form>
		</td>
			<!---------------end btc send------------------------------- -->	     	     
	</tr>
</table>
<!--============buy lock coin section================================================================-->
<div style='float: left; width:50%;  clear: right;  border-top: 1px solid gray; margin-top: 20px; padding-top: 20px;'>
	<center>
	<h3>Buy Lock Coin</h3>
	<form method='POST' action='JsonBuyLock' onsubmit='myButton.disabled = true; return true;'>
		<input type='hidden' id='username' name='username' value='"+userName+"'>
		<input type='hidden' id='password' name='password' value='"+password+"'>
		<h4>Amount of LockCoin to Buy </h4>
		<input type='text' id='buyLockAmount' name='buyLockAmount' value=''>
		<input type='submit' value='Buy Lock Coin' class='btn btn-hero'>   	            
	</form>
	</center>
</div>
<div style='float: right; width:50%; border-top: 1px solid gray; margin-top: 20px; padding-top: 20px;'>
	<!--==============sell lock coin section==============================================================-->
	<center>
	<h3>Sell Lock Coin</h3>
	<form method='POST' action='SellLock' onsubmit='myButton.disabled = true; return true;'>

		<h4>Amount of LockCoin to Sell </h4><input type='text' id='sellLockAmount' name='sellLockAmount' value=''>
		<input type='submit' name='myButton' value='Sell Lock Coin' class='btn btn-hero'>   	            
	</form>
	</center>     		 
	<br/>
	<!--==============referal section==============================================================-->
	<center>
	<h3></h3>
	<form method='POST' action='Refer' onsubmit='myButton.disabled = true; return true;' >
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

<%!
    private double getLockCoinPrice(IntReturner ir, DoubleReturner drCoinsAvailableInTier, Statement stmt) throws SQLException{
    	double coinsAvailableInTier=0;
    	double lockCoinValue=-1;
    	int tierCount=2;
    	ResultSet rs=null;
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