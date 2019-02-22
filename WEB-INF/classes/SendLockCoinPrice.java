

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

/**
 * Servlet implementation class SendLockCoinPrice
 */
public class SendLockCoinPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendLockCoinPrice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	      // JDBC driver name and database URL
	       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	       final String DB_URL="jdbc:mysql://3.16.62.130/cl";
	      //  Database credentials
	       final String USER = "cl";
	       final String PASS = "\r\n" + 
	       		"Ra.cQH&&ZrFG(44e)Uf";
 	     // request.getRequestDispatcher("includes/header.jsp").include(request, response);
	      // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	     // String title = "Lock Coin User";
	      
	      //String docType =
	      //   "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	     // String style="<style>  .fixed_header { display:block; height: 300px; overflow-y: scroll;} .fixed_header tbody{  display:block;  overflow:auto;  height:20px;  width:100%;} .fixed_header thead tr{  display:block;} </style>";
	      Connection conn=null;
	      Statement stmt=null;
	      try {
	         // Register JDBC driver
	          Class.forName("com.mysql.jdbc.Driver");

	         // Open a connection
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);

	         // Execute SQL query
	         stmt = conn.createStatement();
	         IntReturner ir=new IntReturner();
	         DoubleReturner drCoinsAvailableInTier=new DoubleReturner();
	         out.print(""+getLockCoinPrice(ir, drCoinsAvailableInTier, stmt));
	      }catch(Exception e){
	    	  out.print("ERROR");
	      }finally{
	    	  try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	      }
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
		// TODO Auto-generated method stub
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
