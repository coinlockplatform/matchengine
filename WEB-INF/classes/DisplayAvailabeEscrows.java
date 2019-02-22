

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayAvailabeEscrows
 */
public class DisplayAvailabeEscrows extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAvailabeEscrows() {
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
		PrintWriter out = response.getWriter();
		// double buyEscrowAmt = Double.parseDouble(request.getParameter("protect_amount_matched"));
		    String username=request.getParameter("username");
//			String password=request.getParameter("password");
//			int match_id=Integer.parseInt(request.getParameter("match_id"));
			request.getRequestDispatcher("includes/header.jsp").include(request, response);
		
		
		
		
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

/*		     if(buyEscrowAmt!=0){
			      sql="INSERT INTO Protect (protect_id, name, password, unmatched) values (0, '"+name+"', '"+ password+"', "+UsdInBitCoin+")";
			     stmt.executeUpdate(sql);
			     
			     sql="select btc_holdings from users where email='"+name+"';";
			     ResultSet rs = stmt.executeQuery(sql);
			     
			     rs.next();
			      double bitCoin = rs.getDouble("btc_holdings");
			      rs.close();
			      double newBtcHoldings = bitCoin-buyEscrowAmt;
			      if(newBtcHoldings>=0){
			           sql="UPDATE users set btc_holdings="+newBtcHoldings+" where email='"+name+"';";
			           stmt.executeUpdate(sql);
			      }
			      else{
			    	 out.println("<h2>Not enough BTC in account for transaction</h2>");
			      }
			      
			    // stmt.close();
			    // conn.close();
			     second.MatchingEngine.match(out); 
		     }
*/		     out.println ("</br></br>");
			  out.println("<h4>Protect Contracts</h4>");
			  String sql = "select * from matched where is_escrow = 1 and finished = 0 ORDER BY match_id DESC;";
			     ResultSet  rs = stmt.executeQuery(sql);
			     out.println("<table class='fixed_header'>");

		         
		  	    	out.println("<tr>");

			    	out.println("<th>Match ID</th><th>Protect ID</th><th>Trade ID</th><th>protect_email</th><th>trade_email</th><th>trade_amount_matched</th><th>protect_amount_matched</th><th>bitcion_price_at_match</th><th>time_stamp</th><th> Protected $ Value</th><th>view</th>");
		    	    out.println("</tr>");
			     while(rs.next()){
			    	 int match_id2 = rs.getInt("match_id");
			    	 int protect_id = rs.getInt("protect_id");
			    	 int trade_id = rs.getInt("trade_id");
			    	 String protect_email = rs.getString("protect_email");
			    	 String trade_email = rs.getString("trade_email");

			    	 double trade_amount_matched = rs.getDouble("trade_amount_matched");
			    	 double protect_amount_matched =rs.getDouble("protect_amount_matched");
			    	 double bitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
			    	 String time_stamp = rs.getString("time_stamp");
			    	 double dollarValue=protect_amount_matched*bitcion_price_at_match;
			    	 
  		         String form ="<form action='DisplayProtectContract.jsp' method='POST'>"+
   		    	  "<input type='hidden' id='bitcion_price_at_match' name='bitcion_price_at_match' value='"+bitcion_price_at_match+"'>"+
   		   		  "<input type='hidden' id='time_stamp' name='time_stamp' value='"+time_stamp+"'>"+
   		   		  "<input type='hidden' id='match_id' name='match_id' value='"+match_id2+"'>"+
   		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
   		   		  "<input type='hidden' id='trade_id' name='trade_id' value='"+trade_id+"'>"+
   		   		  "<input type='hidden' id='trade_email' name='trade_email' value='"+trade_email+"'>"+
   		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
   		   		 "<input type='hidden' id='username' name='username' value='"+protect_email+"'>"+
   				  "<input type='hidden' id='protect_amount_matched' name='protect_amount_matched' value='"+protect_amount_matched+"'>"+    				  
   				  "<input type='submit' value='Display Contract' class='btn btn-hero'>"+
   				  "</form>";
  		         String form2 ="<form action='EscrowContact' method='POST'>"+
   		    	  "<input type='hidden' id='bitcion_price_at_match' name='bitcion_price_at_match' value='"+bitcion_price_at_match+"'>"+
   		   		  "<input type='hidden' id='time_stamp' name='time_stamp' value='"+time_stamp+"'>"+
   		   		  "<input type='hidden' id='match_id' name='match_id' value='"+match_id2+"'>"+
   		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
   		   		  "<input type='hidden' id='trade_id' name='trade_id' value='"+trade_id+"'>"+
   		   		  "<input type='hidden' id='trade_email' name='trade_email' value='"+trade_email+"'>"+
   		   		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
   				  "<input type='hidden' id='protect_amount_matched' name='protect_amount_matched' value='"+protect_amount_matched+"'>"+    				  
   				  "<input type='hidden' id='dollarValue' name='dollarValue' value='"+dollarValue+"'>"+ 
   				  "<input type='hidden' id='username' name='username' value='"+username+"'>"+
   				  "<input type='submit' value='Send Payment' class='btn btn-hero'>"+
   				  "</form>";

			  	    	out.println("<tr>");
			  	    	  out.println("<td>" + match_id2 + "</td>");
			  		      out.println("<td>" + protect_id + "</td>");
			  		      out.println("<td>" + trade_id + "</td>");
			  		      out.println("<td>" + protect_email + "</td>");
			  		      out.println("<td>" + trade_email + "</td>");
			  		      out.println("<td>" + trade_amount_matched + "</td>");
			  		      out.println("<td>" + protect_amount_matched + "</td>");
			  		      out.println("<td>" + bitcion_price_at_match + "</td>");
			  		     // out.printf("<td>%.4f</td>",coinsAvailableInTier);
			  		     // out.printf("<td>%.6f</td>",bitcoin_gained);
				  		    out.println("<td>" + time_stamp + "</td>");
				  		    out.println("<td>" + dollarValue + "</td>");
				  		    out.println("<td>" + form + "</td>");
				  		    out.println("<td>" + form2 + "</td>");
			    	    out.println("</tr>");
			     }
			     out.println("</table>");
			     request.getRequestDispatcher("includes/footer.jsp").include(request, response); 
			     rs.close();
		  }catch(Exception e){
			  out.println(e);
		  }

	}

}
