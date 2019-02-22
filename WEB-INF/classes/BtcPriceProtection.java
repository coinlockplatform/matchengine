

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class BtcPriceProtection
 */
public class BtcPriceProtection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BtcPriceProtection() {
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

  	      // JDBC driver name and database URL
  	       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  	       final String DB_URL="jdbc:mysql://3.16.62.130/cl";

  	      //  Database credentials
  	       final String USER = "cl";
  	       final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
	    	    request.getRequestDispatcher("includes/header.jsp").include(request, response);
  	      // Set response content type
  	      response.setContentType("text/html");
  	      PrintWriter out = response.getWriter();
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
  	            double bitCoin = rs.getDouble("btc_holdings");
  	    	    double lotCoin = rs.getDouble("loc_holdings");
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

/*    	    	      out.println(docType +
  	    	    	         "<html>\n" +
  	    	    	         "<head>"+style+"<title>" + title + "</title></head>\n" +
  	    	    	         "<body bgcolor = \"#ffffff\">\n" +
  	    	    	         "<h1 align = \"center\">" + title + "</h1>\n"+
  	    	 	             "<h1 align = \"center\">" + first+ " "+ last + "</h1>\n");
*/
//  	    	    request.getRequestDispatcher("includes/header.jsp").include(request, response);
  	    	    out.println( "<br/><br/><br/><br/><h1 align = \"center\">" + title + "</h1>\n"+
	    	 	             "<h1 align = \"center\">" + first+ " "+ last + "</h1>\n");

  	            //Display values
    	    	    out.println("<h2>");
  	    	    out.println("<center>");
      
  	    	    out.println("<table border='2' style='font-size:30px'>");
	    	    	out.println("<tr>");
  	    		  out.println("<td>");
  	    			out.println("BTC Balance");
  	    		  out.println("</td><td>"
  	    		  		+ "<b>" + bitCoin + "</b>");
  	    		  out.println("</td>");
  	    	    out.println("</tr>");
  	    	   out.println("<tr>");
	    		      out.println("<td>");
	    			     out.println("LOCK  Balance");
	    		      out.println("</td>");
	    		      out.println("<td>");
	    			     out.println(lotCoin);
	    		      out.println("</td>");
	    	       out.println("</tr>");
 	    	    out.println("</table>");
	    	    out.println("</center>");
	    	    

	    	    out.println("</h2>");

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
  				       rs.close();
  				       stmt.close();
  				       conn.close();
  					   e.printStackTrace();
  					   return;
  			       }
  				//--------------get lockCoin price---------------------------------------------------------------------
  				double coinsAvailableInTier=-1 ;
  				DoubleReturner drCoinsAvailableInTier = new DoubleReturner();
  				IntReturner ir=new IntReturner() ;
  				double lockCoinValue=getLockCoinPrice(ir,drCoinsAvailableInTier,  stmt);
  				coinsAvailableInTier = drCoinsAvailableInTier.value;
  				int tier = ir.value;
  				out.println("<div style='text-align: center;'>");	
  	         out.println("<h4>BITCOIN $ PRICE: "+bitCoinValue+"</h4>");
  	         out.println("<h4>LOCKCOIN $ PRICE: "+lockCoinValue+"</h4>");
  	         out.printf("<h4>LOCKCOIN LEFT AT THIS PRICE:%.2f </h4>",coinsAvailableInTier);
  	         out.println(" </div>");
  	        // ============buy lock coin section================================================================
  	         out.println("<div style='float: left;'>");
  	         out.println("<h2>Buy Lock Coin</h2>");
  	         
      		 out.println("<form method='POST' action='BuyLock' >");
      		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
      		 out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
      		 out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
      		 out.println("<input type='submit' value='Buy Lock Coin'>");   	            
      		 out.println("</form>");
  	         
  	        //=============================================================================
  	        out.println("</div>");
  	        //=======================BTC Price protection======================================================
  	        out.println("<div style='float: right;'>");
  	        // ==============sell lock coin section==============================================================
  	    	         out.println("<h2>Sell Lock Coin</h2>");
  	    	        		 out.println("<form method='POST' action='SellLock' >");
  	    	        		 out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
  	    	        		 out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
  	    	        		 out.println("<h4>Amount of LockCoin to Sell </h4><input type='text' id='sellLockAmount' name='sellLockAmount' value=''>");
  	    	        		 out.println("<input type='submit' value='Sell Lock Coin'>");   	            
  	    	        		 out.println("</form>");
  	    	        		 
  	    	        		 out.println(" <br/>");
  	    	        		 
  	    	    	         out.println("<div style='background-color: #dddddd;'>");
  	    	    	         
  	    	    	         out.println(" <br/>");
  	    	        		 out.println("<form name='calc' method='POST'  >");
  	    	        		 out.println("Cash Out Bitcoin");
  	    	        		 out.println("<h6><input type='text' id='btcToSell' name='btcToSell' value='' size='10' >Sell BTC </h5>");
  	    	        		 out.println("<h5><input type='text' id='btcPrice' name='btcPrice' value='"+bitCoinValue+"'  size='10' >BTC Price </h5>");
  	    	        		 
  	    	        		 out.println("<h5><input type='checkbox' id='autoLeverage75' name='autoLeverage75' value='' size='10' >75% Auto Leverage </h5>");
  	    	        		 out.println("<h5><input type='text' id='netSale' name='netSale' value=''>Net Sale</h5>");
  	    	        		 out.println("<input type=button onClick='showpay()' value='Confirm'>");   	            
  	    	        		 out.println("</form>");
  	    	        		 out.println(" </div>");
  	    	        		 rs.close();
  	    	 //=============================================================================
  	    	   out.println(" </div>");       		 
  	         }else{
  	        	 out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
  	        	 out.println(" <h2 style='text-align: center;'>LOG IN FAILED</h2>");
					 out.println("<div style='text-align: center;'>");
	    	         //out.println("<h4>Buy Lock Coin</h4>");	    	         
	        		 out.println("<form method='POST' action='index.jsp' >");
	        		// out.println("<input type='hidden' id='username' name='username' value='"+userName+"'>");
	        		// out.println("<input type='hidden' id='password' name='password' value='"+password+"'>");
	        		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
	        		 out.println("<input type='submit' value='RETURN TO LOGIN'>");   	            
	        		 out.println("</form>");

  	         
               }
  	         // Clean-up environment
  	         rs.close();
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
  	         stmt.close();
  	         conn.close();
  	         out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
  	        // out.println("</body></html>");
  	         
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
  	      request.getRequestDispatcher("includes/footer.jsp").include(request, response);

	
	
	
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
    class IntReturner{
    	int value=-1;
    }
    class DoubleReturner{
    	double value=-1;
    }
}

