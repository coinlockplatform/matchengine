

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
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.json.*;
/**
 * Servlet implementation class BuyLock
 */
public class BuyLock extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyLock() {
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
		
		double buyLockAmount = Double.parseDouble(request.getParameter("buyLockAmount"));
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL="jdbc:mysql://3.16.62.130/cl";

		//  Database credentials
		final String USER = "cl";
		final String PASS = "Ra.cQH&&ZrFG(44e)Uf";

	// Set response content type
//
	      response.setContentType("application/json");
	      response.setCharacterEncoding("UTF-8");
		
//		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		String title = "YOUR TRANSACTION IS CONFIRMED";

		String docType =
				"<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

		Connection conn=null;
		Statement stmt=null;
		double bitCoin=-1;
		double lotCoin=-1;
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
			int uid=0;
			while(rs.next()){
				//Retrieve by column name
				// int id  = rs.getInt("id");
				uid = rs.getInt("user_id");
				String first = rs.getString("fname");
				String last = rs.getString("lname");
				bitCoin = rs.getDouble("btc_holdings");
				lotCoin = rs.getDouble("loc_holdings");





/*				out.println(docType +
						"<html >\n" +
						"<head><title>" + title + "</title></head>\n" +
						"<body bgcolor = \"#ffffff\">\n" +
						"<h1 align = \"center\">" + first+ " "+ last + "</h1>\n");
*/
				
				request.getRequestDispatcher("includes/header.jsp").include(request, response);
				
	    	    out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><h1 align = \"center\">" + title + "</h1>\n");
   	 	             

				out.println("<h3 align = \"center\">" + userName + "</h3>\n");
				
				//Display values
				out.println("<h2>");
				out.println("<center>");

				out.println("<table border='2' style='font-size:30px'>");
				out.println("<tr>");
				out.println("<td>");
				out.println("Opening BTC Balance");
				out.println("</td>");
				out.println("<td style='padding-left: 20px;'>");
				out.println(bitCoin);
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>");
				out.println("Opening LOCK Balance");
				out.println("</td>");
				out.println("<td style='padding-left: 20px;'>");
				out.println(lotCoin);
				out.println("</td>");
				out.println("</tr>");

				out.println("</table>");
				out.println("</center>");


				out.println("</h2>");
				out.println("<br/>");
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
			//===========================to be changed later================================================== 
			//URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
			//String x = url.getQuery();
			double bitCoinValue = 3800.0;;
			double lockCoinValue = 0.29;
			
			//--------------get BitCoin price---------------------------------------------------------------------
			
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
			lockCoinValue=getLockCoinPrice(ir,drCoinsAvailableInTier,  stmt);
			coinsAvailableInTier = drCoinsAvailableInTier.value;
			int tier = ir.value;
			//out.println("coinsAvailableInTier="+coinsAvailableInTier);
			/*
			sql = "SELECT * FROM coin_stats where coin_id =  2 ;" ;
			rs = stmt.executeQuery(sql);
            
			// Extract data from result set
			if(rs.next()){
				coinsAvailableInTier=rs.getDouble("available_coins");
				lockCoinValue = rs.getDouble("coin_usd_value");
			}
			if(coinsAvailableInTier<0){
				sql = "SELECT * FROM coin_stats where coin_id =  3 ;" ;
				rs = stmt.executeQuery(sql);
	            
				// Extract data from result set
				if(rs.next()){
					coinsAvailableInTier=rs.getDouble("available_coins");
					lockCoinValue = rs.getDouble("coin_usd_value");
				}
			}
			*/
//do more stuff		
			//===========================calculate if user has enough bitcoin for sale================================================== 
            double bitNeededForSale=(lockCoinValue*buyLockAmount)/bitCoinValue;
			//=============logic of transaction=====================================================================
			if(bitCoin>=bitNeededForSale){
				double dollarValueOfTransaction=buyLockAmount*lockCoinValue;
				double amountOfBitCoinToSubtract =dollarValueOfTransaction/bitCoinValue;
				double newUserBitCoinAmount = bitCoin-amountOfBitCoinToSubtract;

			    double newUserLockCoinAmount=buyLockAmount+lotCoin;
			  //========================update coins in user data base=====================================================
				sql = "UPDATE users " +
						"SET btc_holdings = "+newUserBitCoinAmount+", loc_holdings = "+newUserLockCoinAmount+"  where email = '"+ userName + "' and password = '" + password +"' ;" ;
				stmt.executeUpdate(sql);
				//==========================================================================================
//do something here				
				
			//======send transaction to database===================================================	
			sql =	"INSERT INTO transactions ( tid, uid, from_coin_id, to_coin_id, amount_from, amount_to, current_btc_usd, date) VALUES (NULL, "+uid+", "+1+", "+tier+", "+amountOfBitCoinToSubtract+", "+buyLockAmount+", "+bitCoinValue+", CURRENT_TIMESTAMP);";
			stmt.executeUpdate(sql);
			//out.println("<h4>"+sql+"</h4>");
			
				//========================Get Amount of Coin in Master DataBase====================================================
				sql = "SELECT * FROM coin_stats where coin_name =  'BitCoin' ;" ;
				rs = stmt.executeQuery(sql);
                double coinInMaster=0;
				// Extract data from result set
				if(rs.next()){
					coinInMaster=rs.getDouble("available_coins");
				}
			    //========================update coins in master btc data base=====================================================
				sql = "UPDATE coin_stats " +
						"SET available_coins = "+(amountOfBitCoinToSubtract+coinInMaster)+"  where coin_name =  'BitCoin' ;" ;
				stmt.executeUpdate(sql);
				//==========================The Auction================================================================
				//-----------------find the dollarValueOfBit in coin_stats
				sql = "SELECT * FROM coin_stats where coin_name =  'BitCoin' ;" ;
				rs = stmt.executeQuery(sql);
                coinInMaster=0;
				// Extract data from result set
				if(rs.next()){
					coinInMaster=rs.getDouble("available_coins");
				}
				rs.close();
				double dollarValueOfBit = coinInMaster*bitCoinValue;
				//out.println("<h2>dollarValueOfBit "+dollarValueOfBit+"</h2>");
				//==========================delete sellers with less than $100 in lockcoin to sell=======================================================================================
				double hundredUsdInLockCoin=(1.0/lockCoinValue)*100.0;
				sql="DELETE from sellersList where amountToSell < "+hundredUsdInLockCoin+" ;";
				//==================================================================================================================
				int countOfSellers=0;
				//====================find number of sellers===============================================
				sql = "SELECT count(*) as count FROM sellersList ;" ;
				rs = stmt.executeQuery(sql);
				if(rs.next()){
					countOfSellers=rs.getInt("count");
					
				}
				//=====================end find number of sellers=====================================================================
				while(dollarValueOfBit>=100.0 && countOfSellers>0){
					sql = "SELECT * FROM sellersList ;" ;
					rs = stmt.executeQuery(sql);
					// Extract data from result set
					int rangeTop=1;
					ArrayList <Seller>sellersList = new ArrayList<Seller>();
					while(rs.next()){
						Seller seller = new Seller();
						//get seller information from database
						seller.email=rs.getString("email");
						seller.amountToSell=rs.getDouble("amountToSell");
						seller.priority=rs.getInt("priority");
						seller.users_id=rs.getInt("users_id");
						//set the range information for seller
						seller.rangeBottom=rangeTop;
						rangeTop+=seller.priority;
						seller.rangeTop=rangeTop;
						rangeTop=rangeTop+1;
						//add seller to sellerList
						sellersList.add(seller);						
					}
					Random random = new Random();
					int selection = random.nextInt(rangeTop-1)+1;
					for(int i = 0; i <sellersList.size();i++){
						Seller seller = sellersList.get(i);
						if(selection>=seller.rangeBottom && selection<=seller.rangeTop){
//							out.println("<h5>A winner "+seller.email+" LockCoin Sell Price="+lockCoinValue+" coinsAvailableInTier="+coinsAvailableInTier+"</h5>");
						  //========================update coins in user data base for winner=====================================================
							
							double winnerOriginalBitCoinAmt=-1.0;
							double winnerOriginalLockCoinAmt=-1.0;
							sql = "SELECT * FROM users where email = '"+ userName + "' ;" ;
							rs = stmt.executeQuery(sql);
							// Extract data from result set
							if(rs.next()){
								winnerOriginalBitCoinAmt=rs.getDouble("btc_holdings");
								winnerOriginalLockCoinAmt=rs.getDouble("loc_holdings");
							}//====fix this
							//100 usd in bitcoin
							double hundredUsdInBitCoin=(1.0/bitCoinValue)*100.0;
							newUserBitCoinAmount=winnerOriginalBitCoinAmt+hundredUsdInBitCoin;
						    hundredUsdInLockCoin=(1.0/lockCoinValue)*100.0;
							newUserLockCoinAmount=winnerOriginalLockCoinAmt-hundredUsdInLockCoin;
							//out.println("<center><h5>A winner "+seller.email+" LockCoin Sell Price="+lockCoinValue+" coinsAvailableInTier="+coinsAvailableInTier+" BitCoin gained="+hundredUsdInBitCoin+"</h5></center>");
							sql="INSERT INTO winners (winner_id, user_id, email, lockcoin_value, coinsAvailableInTier, bitcoin_gained ) VALUES( 0, "+seller.users_id+", '"+seller.email+"' , "+lockCoinValue+", "+coinsAvailableInTier+", "+hundredUsdInBitCoin+" ) ;";
							stmt.executeUpdate(sql);
							sql = "UPDATE users " +
									"SET btc_holdings = "+(newUserBitCoinAmount)+", loc_holdings = "+(newUserLockCoinAmount)+
									"  where email = '"+ seller.email + "' ;" ;
							stmt.executeUpdate(sql);
							//----------------------------
							//======send transaction to database===================================================	
							sql =	"INSERT INTO transactions ( tid, uid, from_coin_id, to_coin_id, amount_from, amount_to, current_btc_usd, date) VALUES (NULL, "+uid+", "+tier+", "+1+", "+hundredUsdInLockCoin+", "+hundredUsdInBitCoin+", "+bitCoinValue+", CURRENT_TIMESTAMP);";
							stmt.executeUpdate(sql);
							//out.println("<h4>"+sql+"</h4>");
							//=================================
							double amountToSell=0;
							sql = "SELECT * FROM sellersList where email = '"+ seller.email + "' ;" ;
							rs = stmt.executeQuery(sql);
							if(rs.next()){
								amountToSell=rs.getDouble("amountToSell");
								
							}
							//-----------update sellers list for winner-----------------------
							if(amountToSell>=hundredUsdInLockCoin){
							   sql = "UPDATE sellersList " +
									"SET amountToSell = "+(amountToSell-hundredUsdInLockCoin)+
									"  where email = '"+ seller.email + "' ;" ;
							   stmt.executeUpdate(sql);
							}
							//==========================================================================================
							break;
						}
					}
				    //========================update coins in master btc data base=====================================================
					double hundredUsdInBitCoin=(1.0/bitCoinValue)*100.0;
					sql = "UPDATE coin_stats " +
							"SET available_coins = "+(coinInMaster-hundredUsdInBitCoin)+"  where coin_name =  'BitCoin' ;" ;
					stmt.executeUpdate(sql);
					
					
					lockCoinValue=getLockCoinPrice(ir, drCoinsAvailableInTier,  stmt);
					System.out.println("lockCoinValue="+lockCoinValue);
					int tierNum = ir.value;
					 coinsAvailableInTier=drCoinsAvailableInTier.value;
					
					
					sql = "UPDATE coin_stats " +
							"SET available_coins = "+(coinsAvailableInTier-hundredUsdInLockCoin)+"  where coin_id =  "+tierNum+" ;" ;
					stmt.executeUpdate(sql);
					
					lockCoinValue=getLockCoinPrice(ir, drCoinsAvailableInTier,  stmt);
					
                    //=================================================================================================================
					sql = "SELECT * FROM coin_stats where coin_name =  'BitCoin' ;" ;
					rs = stmt.executeQuery(sql);
	                coinInMaster=0;
					// Extract data from result set
					if(rs.next()){
						coinInMaster=rs.getDouble("available_coins");
					}
					dollarValueOfBit = coinInMaster*bitCoinValue;
					
					
					//==========================delete sellers with less than $100 in lockcoin to sell=======================================================================================
					hundredUsdInLockCoin=(1.0/lockCoinValue)*100.0;
					sql="DELETE from sellersList where amountToSell < "+hundredUsdInLockCoin+" ;";
					stmt.executeUpdate(sql);
					//==========================find number of sellers========================================================================================
					sql = "SELECT count(*) as count FROM sellersList ;" ;
					rs = stmt.executeQuery(sql);
					if(rs.next()){
						countOfSellers=rs.getInt("count");
						
					}
                    //=========================================================================================

				}//end lottery while
				//===========================Get result of transcation==============================================================
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







					//Display values
					out.println("<center><h2>Updated Transactions");
					//out.println("</center>");

					out.println("<table border='2' style='font-size:30px'>");
					out.println("<tr>");
					out.println("<td>");
					out.println("Closing BTC Balance");
					out.println("</td>");
					out.println("<td style='padding-left: 20px;'>");
					out.println(bitCoin);
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td >");
					out.println("Closing LOCK Balance");
					out.println("</td>");
					out.println("<td style='padding-left: 20px;'>");
					out.println(lotCoin);
					out.println("</td>");
					out.println("</tr>");

					out.println("</table>");
					out.println("</center>");
                    
//==========================calculator============================
					out.println("</h2>");
					
					out.println("<br/>");
	/*				  out.println("<div style='background-color: #dddddd; margin-left:20%; margin-right: 20%'>");
		    	         
		    	        // out.println(" <br/>");
		        		 out.println("<form name='calc' method='POST'  >");
		        		 out.println("Cash Out Bitcoin");
		        		 out.println("<h6><input type='text' id='btcToSell' name='btcToSell' value='' size='10' >Sell BTC </h6>");
		        		 out.println("<h6><input type='text' id='btcPrice' name='btcPrice' value='"+bitCoinValue+"'  size='10' >BTC Price </h6>");
		        		 
		        		 out.println("<h6><input type='checkbox' id='autoLeverage75' name='autoLeverage75' value='' size='10' >75% Auto Leverage </h6>");
		        		 out.println("<h6><input type='text' id='Leverage Auto' name='LevAuto' value=''>Auto Leverage in BTC</h6>");
		        		 out.println("<h6><input type='text' id='netSale' name='netSale' value=''>Net Cash Out USD</h6>");
		        		 out.println("<input type=button onClick='showpay()' value='Confirm'>");   	            
		        		 out.println("</form>");
		        		 out.println(" </div>");
*/					 out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/>");
					//=========================end results of transaction=================================================
					//out.println("</body></html>");

					// Clean-up environment
				}
			}else{
				out.println("<h2 style='text-align: center;'>The amount of bitcoin required for sale exceeds bitcoin in your account</h2>");
			}
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
class IntReturner{
	int value=-1;
}
class DoubleReturner{
	double value=-1;
}
class Seller{
	String email;
	double amountToSell;
	int priority;
	int rangeBottom;
	int rangeTop;
	int users_id;
}
