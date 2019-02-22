

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
 * Servlet implementation class SellLock
 */
public class SellLock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellLock() {
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
	      String userName=(String)session.getAttribute("username");
	      String password=(String)session.getAttribute("password");
	      String user_idS=(String)session.getAttribute("user_id");	  	    
	      int user_id=Integer.parseInt(user_idS);	
		double sellLockAmount = Double.parseDouble(request.getParameter("sellLockAmount"));
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL="jdbc:mysql://3.16.62.130/cl";

		//  Database credentials
		final String USER = "cl";
		final String PASS = "Ra.cQH&&ZrFG(44e)Uf";

		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		String title = "Sell Order Confirmed";
		
		String docType =
				"<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

		Connection conn=null;
		Statement stmt=null;
		double bitCoin=-1;
		double lotCoin=-1;
		int userId=0;
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

			// Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				// int id  = rs.getInt("id");
				//int age = rs.getInt("age");
				String first = rs.getString("fname");
				String last = rs.getString("lname");
				bitCoin = rs.getDouble("btc_holdings");
				lotCoin = rs.getDouble("loc_holdings");
				userId = rs.getInt("user_id"); 




/*				out.println(docType +
						"<html>\n" +
						"<head><title>" + title + "</title></head>\n" +
						"<body bgcolor = \"#a0f0af0\">\n" +
						"<h1 align = \"center\">" + "Before" + "</h1>\n"+
						"<h1 align = \"center\">" + first+ " "+ last + "</h1>\n");
*/
				
				request.getRequestDispatcher("includes/header.jsp").include(request, response);
				 out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><h1 align = \"center\">" + title + "</h1>\n"+
	   	 	             "<h3 align = \"center\">"+ userName + "</h3>\n");
				//Display values
				out.println("<h2>");
				out.println("<center>");

/*				out.println("<table border='2' style='font-size:30px'>");
				out.println("<tr>");
				out.println("<td>");
				out.println("Opening BITCOIN");
				out.println("</td>");
				out.println("<td style='padding-left: 20px;'>");
				out.println(bitCoin);
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>");
				out.println("Opening LOCKCOIN");
				out.println("</td>");
				out.println("<td style='padding-left: 20px;'>");
				out.println(lotCoin);
				out.println("</td>");
				out.println("</tr>");

				out.println("</table>");
*/				out.println("</center>");


				out.println("</h2>");

				//  out.println("bitCoin: " + bitCoin + "<br>");
				//  out.println("lotCoin: " + lotCoin + "<br>");
				//  out.println("First: " + first + "<br>");
				//  out.println("Last: " + last + "<br>");

			}

			// ============================================================================
			/*    	         out.println("<h3>Buy Lock Coin</h3>");
    	        		 out.println("<form method='POST' action='BuyLock' >");    	           
    	        				 out.println("<input type='submit' value='Buy Lock Coin'>");   	            
    	        		out.println("</form>");
			 */    	       
			 
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
			out.println("<br/>");
			out.println("<div style='text-align: center;'>");	
         out.println("<h4>BITCOIN $ PRICE: "+bitCoinValue+"</h4>");
         out.println("<h4>LOCKCOIN $ PRICE: "+lockCoinValue+"</h4>");
         out.printf("<h4>LOCKCOIN LEFT AT THIS PRICE:%.2f </h4>",coinsAvailableInTier);
         out.println(" </div>");
			
			
			
			
			
			
			//===========================calculate if user has enough bitcoin for sale================================================== 
           // double bitNeededForSale=(lockCoinValue*sellLockAmount)/bitCoinValue;
			//=============logic or transaction=====================================================================
			if(lotCoin>=sellLockAmount){
				double dollarValueOfTransaction=sellLockAmount*lockCoinValue;
				//double amountOfBitCoinToSubtract =dollarValueOfTransaction/bitCoinValue;
				//double newUserBitCoinAmount = bitCoin-amountOfBitCoinToSubtract;

			   // double newUserLockCoinAmount=sellLockAmount+lotCoin;
				try{
				sql = "INSERT INTO sellersList ( users_id, email, amountToSell, priority ) VALUES ( " +
						userId+", '"+userName+"', "+ sellLockAmount +", "+0+ " );"; 
				stmt.executeUpdate(sql);
				out.println("<br/><center><h3>You are now part of the auction!!</h3></center><br/>");
				}catch(SQLException se){
					out.println("<br/><center><h3 style='color: red;'>You can not join the Auction Twice </h3>"+
							"<h3 style='color: red;'>Please wait till previous sell order is completed </h3></center><br/>" );
					//out.println(se);
				}
			 //=========================auction=====================================================================
			    
		    //========================update coins in data base=====================================================
			
				
				//stmt = conn.createStatement();
/*				sql = "UPDATE users " +
						"SET btc_holdings = "+newUserBitCoinAmount+", loc_holdings = "+newUserLockCoinAmount+"  where email = '"+ userName + "' and password = '" + password +"' ;" ;
				stmt.executeUpdate(sql);
*/				//==========================Get result of transcation================================================================
				sql = "SELECT * FROM users where email = '"+ userName + "' and password = '" + password +"' ;" ;
				rs = stmt.executeQuery(sql);

				// Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					// int id  = rs.getInt("id");
					//int age = rs.getInt("age");
					String first = rs.getString("fname");
					String last = rs.getString("lname");
					bitCoin = rs.getDouble("btc_holdings");
					lotCoin = rs.getDouble("loc_holdings");






                    out.println("<center>");
					//Display values
					out.println("<h2>Investment results after transaction");
					

					out.println("<table border='2' style='font-size:30'>");
					out.println("<tr>");
					out.println("<td>");
					out.println("Current BITCOIN");
					out.println("</td>");
					out.println("<td style='padding-left: 20px;'>");
					out.println(bitCoin);
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>");
					out.println("Current LOCKCOIN");
					out.println("</td>");
					out.println("<td style='padding-left: 20px;'>");
					out.println(lotCoin);
					out.println("</td>");
					out.println("</tr>");

					out.println("</table>");
					out.println("</center>");


					out.println("</h2>");
					//=========================end results of transaction=================================================
					out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/>");
					//out.println("</body></html>");

					// Clean-up environment
				}
			}else{
				out.println("<h2>The amount of LOCKCOIN required for sale exceeds LOCKCOIN in your account</h2>");
			}
			 out.println("<div style='text-align: center; margin-bottom: 30px'>");
	         //out.println("<h4>Buy Lock Coin</h4>");	    	         
    		 out.println("<form method='POST' action='CancelSale' >");
     		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
    		 out.println("<input type='submit' value='Cancel Sale' class='btn btn-hero'>");   	            
    		 out.println("</form></div>");

			
			
    		 out.println("<div style='text-align: center;'>");
    		 out.println("<form method='POST' action='dashboard.jsp' >");
    		  out.println("<input type='submit' value='RETURN TO HOME' class='btn btn-hero'>");   	            
    	      out.println("</form></div>");
			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException se) {
			//Handle errors for JDBC
			out.println(" sql error "+se);
		} catch(Exception e) {
			//Handle errors for Class.forName
			out.println(" general error  "+e);
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

				}
		}
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
}
