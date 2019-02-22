

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
 * Servlet implementation class AlertOnEscrow
 */
public class AlertOnEscrow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlertOnEscrow() {
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
	      if(!auth.equals("1")){
	    	  
	    	  session.invalidate();//destroy any session that they may have
	    	 response.sendRedirect(request.getContextPath() + "/index.jsp");
	    	//  out.println("redirect "+request.getContextPath() + "/login2.jsp");
	    	 return;
	      }
		int escrow_id = Integer.parseInt(request.getParameter("escrow_id"));
	      Connection conn=null;
	      Statement stmt=null;
	      PrintWriter out = response.getWriter();
	      ResultSet rs = null;
	      final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL="jdbc:mysql://3.16.62.130/cl";

	      //  Database credentials
	       final String USER = "cl";
	       final String PASS = "Ra.cQH&&ZrFG(44e)Uf";

	      try {
	         // Register JDBC driver
	          Class.forName("com.mysql.jdbc.Driver");

	         // Open a connection
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);

	         // Execute SQL query
	         stmt = conn.createStatement();
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
					// =========
		
		     String sql = "UPDATE escrow " +
				"SET escrow_done = 1,  btc_price_at_lock = "+bitCoinValue+", lockc_price_at_lock = "+lockCoinValue+" where escrow_id = "+ escrow_id +" ;" ;
		     stmt.executeUpdate(sql);

 	         sql = "SELECT * FROM escrow ;" ;
 	         rs = stmt.executeQuery(sql);
 
            //int age = rs.getInt("age");
         
           // String last = rs.getString("lname");
           // double bitCoin = rs.getDouble("btc_holdings");
    	   // double lotCoin = rs.getDouble("loc_holdings");
 	        out.println("</br></br></br></br></br></br></br></br>");
            out.println("<table class='fixed_header' border='1' style='font-size:12px; margin-top=70px;'>");
    	    	out.println("<tr>");
	    		  out.println("<th>");
	    			out.println("Escrow Id");
	    		  out.println("</th>");
	    		  out.println("<th>");
	    			out.println("User Id");
	    		  out.println("</th>");
	    		  out.println("<th>");
	    			out.println("btc escrow");
	    		  out.println("</th>");
	    		  out.println("<th>");
	    			out.println("Lock Escrow");
	    		  out.println("</th>");
	    		  out.println("<th>");
	    			out.println("Time Stamp");
	    		  out.println("</th>");
	    		  out.println("<th>");
	    			out.println("Select");
	    		  out.println("</th>");
	    		  out.println("<th>");
	    			out.println("Done");
	    		  out.println("</th>");
	    	    out.println("</tr>");

	         // Extract data from result set
	         while(rs.next()){
	            escrow_id  = rs.getInt("escrow_id");
	            int user_id  = rs.getInt("user_id");
	            float btc_escrow = rs.getFloat("btc_escrow");
	            float lock_escrow = rs.getFloat("lock_escrow");
	            String time_stamp = rs.getString("time_stamp");
	            int escrow_done  = rs.getInt("escrow_done");
	    	   
	    	   out.println("<tr>");
    		      out.println("<td>");
    			     out.println(escrow_id);
    		      out.println("</td>");
    		      out.println("<td>");
    			     out.println(user_id);
    		      out.println("</td>");
    		      out.println("<td>");
    			     out.println(btc_escrow);
    		      out.println("</td>");
    		      out.println("<td>");
    			     out.println(lock_escrow);
    		      out.println("</td>");
    		      out.println("<td>");
    			     out.println(time_stamp);
    		      out.println("</td>");
    		      out.println("<td>");
    		         String form ="<form action='AlertOnEscrow' method='POST'>"+
     		    	  "<input type='hidden' id='escrow_id' name='escrow_id' value='"+escrow_id+"'>"+
     		   		  "<input type='hidden' id='user_id' name='user_id' value='"+user_id+"'>"+
     				  "<input type='hidden' id='btc_escrow' name='btc_escrow' value='"+btc_escrow+"'>"+
     				  "<input type='hidden' id='lock_escrow' name='lock_escrow' value='"+lock_escrow+"'>"+
     				  "<input type='hidden' id='time_stamp' name='time_stamp' value='"+time_stamp+"'>"+
     				  " <input type='hidden' name='fromLogin' value='notFromLogIn'>"+
     				  "<input type='submit' value='Place Bitcoin Escrow'>"+
     				  "</form>";
  	     		     out.println(form);    		     
  	     		     out.println("</td>");
  	     		     out.println("<td>");
 			         out.println(escrow_done);
 		             out.println("</td>");
    	          out.println("</tr>");
	    	    
	         }
	         out.println("</table>");
	         rs.close();
		     
		     
	         stmt.close();
	         conn.close();

	      }catch(Exception e){
	    	  out.println(e);
 	         try {
 	        	 if(stmt!=null)
				     stmt.close();
 	        	 if(conn!=null)
				     conn.close();
			} catch (SQLException es) {
				out.println(es);
			}
 	         

	      }
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
