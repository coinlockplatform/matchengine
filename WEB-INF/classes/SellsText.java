

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SellsText
 */
public class SellsText extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellsText() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//	PrintWriter out = new PrintWriter(new File("forTransactions.txt"));
			PrintWriter out = response.getWriter();
		// open db
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
	         String sql;
	         sql = "SELECT ROUND(t.amount_from,0) AS LOC, ROUND(t.amount_to,4) AS BTC FROM transactions t WHERE to_coin_id = 1";
 
	         		
	         ResultSet rs = stmt.executeQuery(sql);

	         // Extract data from result set
	         // Extract data from result set
	         while(rs.next()){
	        	 //int uid = rs.getInt("uid");
	        	 //int from_coin_id = rs.getInt("from_coin_id");
	        	 //int to_coin_id = rs.getInt("to_coin_id");
	        	 double LOC = rs.getDouble("LOC");
	        	 double BTC = rs.getDouble("BTC");
	      
	        	 out.printf("SOLD %.0f LOC FOR %.4f BTC",LOC,BTC);
	        	 out.println();
	         }
	         out.close();
	         rs.close();
	         //===================end table of transactions========================
	         stmt.close();
	         conn.close();
	        
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
	         } 
	         
	         //end finally try
	      } //end tr
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// get record set -- select * from transactions limit 10;
		// show record set on screen
		// done
		

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

		try {
		//	PrintWriter out = new PrintWriter(new File("forTransactions.txt"));
			PrintWriter out = response.getWriter();
		// open db
	       final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	       final String DB_URL="jdbc:mysql://54.214.194.247/cl";

	      //  Database credentials
	       final String USER = "cl";
	       final String PASS = "95ODVqCd8T7mfivU";
		
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
	         sql = "SELECT ROUND(t.amount_from,0) AS LOC, ROUND(t.amount_to,4) AS BTC FROM transactions t WHERE to_coin_id = 1";
 
	         		
	         ResultSet rs = stmt.executeQuery(sql);

	         // Extract data from result set
	         // Extract data from result set
	         while(rs.next()){
	        	 //int uid = rs.getInt("uid");
	        	 //int from_coin_id = rs.getInt("from_coin_id");
	        	 //int to_coin_id = rs.getInt("to_coin_id");
	        	 double LOC = rs.getDouble("LOC");
	        	 double BTC = rs.getDouble("BTC");
	      
	        	 out.printf("SOLD %.0f LOC FOR %.4f BTC",LOC,BTC);
	        	 out.println();
	         }
	         out.close();
	         rs.close();
	         //===================end table of transactions========================
	         stmt.close();
	         conn.close();
	        
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
	         } 
	         
	         //end finally try
	      } //end tr
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// get record set -- select * from transactions limit 10;
		// show record set on screen
		// done
		

	}

}
