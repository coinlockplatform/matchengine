

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class gauge
 */
public class gauge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gauge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// Get the mysql driver

		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		// Set the host and database for mysql
		final String DB_URL="jdbc:mysql://3.16.62.130/cl"; // Mysql host and database
		final String USER = "cl"; // DB User
		final String PASS = "Ra.cQH&&ZrFG(44e)Uf"; // DB Password
		
		Connection conn=null;
		Statement stmt=null;
        IntReturner ir= new IntReturner();
        IntReturner irCap= new IntReturner();
        DoubleReturner drCoinsAvailableInTier=new DoubleReturner();
        double lockCoinValue=0;
	     try {
	         // Register JDBC driver
	          Class.forName("com.mysql.jdbc.Driver");

	         // Open a connection
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);

	         // Execute SQL query
	         stmt = conn.createStatement();
	         lockCoinValue = getLockCoinPrice(ir,drCoinsAvailableInTier,  conn, irCap);
	   	    
	   	    	stmt.close();
	   	    	conn.close();
		         JsonGenerator gen = Json.createGenerator( response.getWriter());
	 	          gen.writeStartObject()	  	           
	 	          .write("remainingPercent", second.MatchingEngine.round(drCoinsAvailableInTier.value/irCap.value*100,0))	  	           
	 	          .write("remainingCoin", second.MatchingEngine.round( drCoinsAvailableInTier.value,0))
	 	          .write("pricePoint", lockCoinValue)
	 	          .writeEnd();
	 	          gen.close();

	     }catch(Exception e){
	    	 JsonGenerator gen = Json.createGenerator( response.getWriter());
	          gen.writeStartObject()
	          .write("error", e.toString())	  	           
	          .writeEnd();
	          gen.close();
	        }
	       

		
	}
	   private double getLockCoinPrice(IntReturner ir, DoubleReturner drCoinsAvailableInTier, Connection conn, IntReturner irCap) throws SQLException{
	    	double coinsAvailableInTier=0;
	    	double lockCoinValue=-1;
	    	int tierCount=2;
	    	int cap=0;
	    	ResultSet rs=null;
	    	
	    	
		//	String sql = "SELECT * FROM coin_stats where coin_id =  "+tierCount+" ;" ;
			
			
			String sql = "SELECT * FROM coin_stats where coin_id = ? ;" ;
	          PreparedStatement ps = conn.prepareStatement(sql);
	          ps.setInt(1, tierCount);
	       //  ResultSet rs = stmt.executeQuery(sql);
	         rs = ps.executeQuery();

			//rs = stmt.executeQuery(sql);
	        
			// Extract data from result set
			if(rs.next()){
				coinsAvailableInTier=rs.getDouble("available_coins");
				lockCoinValue = rs.getDouble("coin_usd_value");
				cap=rs.getInt("cap");
			}
			
			while(coinsAvailableInTier*lockCoinValue<100.0&&tierCount<12){
				tierCount++;
				sql = "SELECT * FROM coin_stats where coin_id = ? ;" ;
		           ps = conn.prepareStatement(sql);
		          ps.setInt(1, tierCount);
		       //  ResultSet rs = stmt.executeQuery(sql);
		         rs = ps.executeQuery();
	            
				// Extract data from result set
				if(rs.next()){
					coinsAvailableInTier=rs.getDouble("available_coins");
					lockCoinValue = rs.getDouble("coin_usd_value");
					cap=rs.getInt("cap");
				}
			}
			drCoinsAvailableInTier.value=coinsAvailableInTier;
			ir.value=tierCount;
			irCap.value=cap;
	    	return lockCoinValue;
	    }
	}




