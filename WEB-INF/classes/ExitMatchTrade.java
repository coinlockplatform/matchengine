

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import second.MatchingEngine;

/**
 * Servlet implementation class ExitMatchTrade
 */
public class ExitMatchTrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExitMatchTrade() {
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
	   //   String password=(String)session.getAttribute("password");
	      String user_idS=(String)session.getAttribute("user_id");	  	    
	      int user_id=Integer.parseInt(user_idS);	
		// <input type="hidden" name="match_id" id="match_id" value="<%=request.getParameter("match_id") %>" />
		// <input type="hidden" name="protect_email" id="protect_email" value="<%=request.getParameter("protect_email") %>" />
		// <input type="text" name="exit_ce110" id="exit_ce110" value="" />
		 //<input type='hidden' id='trade_id' name='trade_id' value="<%=request.getParameter("trade_id") %>">
		 //<input type='hidden' id='trade_email' name='trade_email' value="<%=request.getParameter("trade_email") %>">
		request.getRequestDispatcher("includes/header.jsp").include(request, response);
		PrintWriter out = response.getWriter();
        int match_id = Integer.parseInt(request.getParameter("match_id"));
        String protect_email = request.getParameter("protect_email");
        double protectAmount = Double.parseDouble(request.getParameter("exit_cd110"));
        double leverageAmount = Double.parseDouble(request.getParameter("exit_ce110"));
        String trade_email = request.getParameter("trade_email");
        int trade_id = Integer.parseInt(request.getParameter("trade_id"));
        double protectAmountDiv3=protectAmount/3.0;
        ArrayList <Trade>tradeList = new ArrayList<Trade>();
		// ArrayList <Trade>tradeList = new ArrayList<Trade>();
		 ArrayList <Trade>tradeList2 = new ArrayList<Trade>();
		// ArrayList <Trade>tradeList2 = new ArrayList<Trade>();
		// PrintWriter out = response.getWriter();
		// double btcTradeAmount ;
		    
		//	double fourTimesTradeAmount = btcTradeAmount*4;
		    //JDBC driver name and database URL
		   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   final String DB_URL="jdbc:mysql://3.16.62.130/cl";

		   //  Database credentials
		   final String USER = "cl";
		  final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
		  Connection conn=null;
		  Statement stmt=null;
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
			     //  rs.close();
			     //  stmt.close();
			     //  conn.close();
				   e.printStackTrace();
				   return;
		       }
		//--------------------------------------------------------------------------------------------------------------	
		  try {
		     // Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		     // Open a connection
		     conn = DriverManager.getConnection(DB_URL, USER, PASS);

		     // Execute SQL query
		     stmt = conn.createStatement();
		     
		    String sql="SELECT * FROM matched WHERE match_id="+match_id+" and finished=1 ;";
		    ResultSet rs = stmt.executeQuery(sql);
		     
		    if(rs.next()){
		    	response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
		    }

		     
		     sql="Select * from Trading ;";
	         rs = stmt.executeQuery(sql);
	         String javaCalcLogic="";
	         // Extract data from result set
	         while(rs.next()){
	            //Retrieve by column name
	            int tradeing_id  = rs.getInt("trade_id");
	            //int age = rs.getInt("age");
	            String name = rs.getString("name");
	            String password = rs.getString("password");
	           
	            double unmatched = rs.getDouble("unmatched");
	            double matched = rs.getDouble("matched");
	            Trade p = new Trade(tradeing_id, name, password,
	    				 unmatched, matched);
	            tradeList.add(p);
	         }
	         rs.close();
	         boolean matched=false;
		     for(Trade t: tradeList){
		    	 if(t.unmatched>=protectAmountDiv3){
		    		  sql="UPDATE Trading set unmatched="+(t.unmatched-protectAmountDiv3)+", matched="+ (t.matched+protectAmountDiv3)+" where trade_id="+t.trade_id+";";
		                stmt.executeUpdate(sql);
	 	                sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+t.name+"', '"+trade_email+"', "+t.trade_id+", "+trade_id+", "+leverageAmount+", "+(leverageAmount*3)+", NULL, "+bitCoinValue+"  );";
		               stmt.executeUpdate(sql);
		               sql = "SELECT * FROM users where email = '"+ trade_email + "' ;" ;
						rs = stmt.executeQuery(sql);
						// Extract data from result set
						double btc_holdings=0;
						if(rs.next()){
							btc_holdings=rs.getDouble("btc_holdings");
							
						}
		               sql = "UPDATE users " +
								"SET btc_holdings = "+(btc_holdings+leverageAmount)+" "+//////////////////////////////////////////////
								"  where email = '"+ trade_email + "' ;" ;
						stmt.executeUpdate(sql);
						 sql = "UPDATE matched " +
									"SET finished = 1 "+
									"  where match_id = "+ match_id + " ;" ;
					    stmt.executeUpdate(sql);
					    matched=true;
					    break;
		    	 }
		     }
		     out.println("</br></br></br></br></br></br></br></br>");
		     if(matched){
		    	 out.println("<h1 style='text-align: center'>Matched</h1>");
		     }else{
		    	 out.println("<h1 style='text-align: center'>Not Matched</h1>");
		    	 sql="select * from unmatchedexittrade where match_id="+match_id+" ;";
		    	 rs = stmt.executeQuery(sql);
		    	 if(!rs.next()) {
			    	 sql="INSERT INTO unmatchedexittrade ( id, email, amount, match_id, protect_side ) values ( 0, '"+trade_email+"', "+leverageAmount+", "+match_id+", "+protectAmount+" );";
			    	 stmt.executeUpdate(sql);
		    		 
		    	 }else {
		    		 out.println("<h1 style='text-align: center'>Already waiting to be Matched</h1>");
		    	 }
		    	 
		    	 
		    	 
//		    	 MatchingEngine.matchUpExits(out);
		     }
		     stmt.close();
		     conn.close();

		  }catch(Exception e){
			 out.println(e);
		  }
		  request.getRequestDispatcher("includes/footer.jsp").include(request, response);

	}

}
class Trade{
	 int trade_id;;
  String name;
  String password ;
 
  double unmatched;
  double matched ;
	public Trade(int trade_id, String name, String password,
			double unmatched, double matched) {
		super();
		this.trade_id = trade_id;
		this.name = name;
		this.password = password;
		this.unmatched = unmatched;
		this.matched = matched;
	}
  
}
