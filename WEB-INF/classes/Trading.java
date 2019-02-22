

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
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Trading
 */
public class Trading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Trading() {
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


		String userName = request.getParameter("username");
	      String password = request.getParameter("password");
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
  	      String title = "Match Engine Wallet";
  	      
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
  	         
  	         
  	         int user_id = 0;
  	         if(rs.next()){
  	            //Retrieve by column name
  	            user_id  = rs.getInt("user_id");
  	            //int age = rs.getInt("age");
  	            String email = rs.getString("email");
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
	    			     out.println("Current LOCK Balance");
	    		      out.println("</td>");
	    		      out.println("<td style='padding-left: 20px;'>");
	    			     out.println(lotCoin);
	    		      out.println("</td>");
	    	       out.println("</tr>");
 	    	    out.println("</table>");
	    	    out.println("</center>");
	    	    

	    	    //out.println("</h5>");
	    	    out.println("<br/>");
  	          //  out.println("bitCoin: " + bitCoin + "<br>");
  	          //  out.println("lotCoin: " + lotCoin + "<br>");
  	          //  out.println("First: " + first + "<br>");
  	          //  out.println("Last: " + last + "<br>");
  	            
  	         
  				//--------------get BitCoin price---------------------------------------------------------------------
  				double bitCoinValue=getBitCoinPrice();
  				
  				//out.println("the bitcoin value is");
/*  				try{
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
 */ 				//--------------get lockCoin price---------------------------------------------------------------------
  				double coinsAvailableInTier=-1 ;
  				DoubleReturner drCoinsAvailableInTier = new DoubleReturner();
  				IntReturner ir=new IntReturner() ;
  				double lockCoinValue=getLockCoinPrice(ir,drCoinsAvailableInTier,  stmt);
  				coinsAvailableInTier = drCoinsAvailableInTier.value;
  				int tier = ir.value;
  				out.println("<div style='text-align: center;'>");	
  	         out.println("<h5>BITCOIN $ PRICE: "+bitCoinValue+"</h5>");
  	         out.println("<h5>LOCKCOIN $ PRICE: "+lockCoinValue+"</h5>");
  	         out.printf("<h5>LOCKCOIN LEFT AT THIS PRICE:%.2f </h5>",coinsAvailableInTier);
  	         out.println(" </div>");
  	         out.println(" </div>");
  	         
  	         
  	       givenUsingTimer_whenSchedulingRepeatedTask_thenCorrect( out); 
  	         
  	         
  	       out.println( "<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
  	       request.getRequestDispatcher("includes/footer.jsp").include(request, response);
  	       
  	       
  	       
           try {
	            if(rs!=null)
	               rs.close();
	         } catch(SQLException se3) {
	        	 se3.printStackTrace();	 	        	 
	         } // nothing we can do
           try {
	            if(stmt!=null)
	               stmt.close();
	         } catch(SQLException se2) {
	        	 se2.printStackTrace();	 	        	 
	         } // nothing we can do
	 	         try {
	 	            if(conn!=null)
	 	                conn.close();
	 	         } catch(SQLException se) {
	 	             se.printStackTrace();
	 	         } //end finally try
  	         }
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
 	 } //
	
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
	
	double getBitCoinPrice(){
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

				   e.printStackTrace();
				  
		       }
          return bitCoinValue;
		
	}
	
	
	
	public void givenUsingTimer_whenSchedulingRepeatedTask_thenCorrect(final PrintWriter out){
	    TimerTask repeatedTask = new TimerTask() {
	        public void run() {
	            out.println("<h1>Task performed on " + new Date()+" </h1>");
	        }
	    };
	    Timer timer = new Timer("Timer");
	     
	    long delay  = 10L;
	    long period = 10L;
	    timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}
}
