

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import second.RandomString;

/**
 * Servlet implementation class doRegistration
 */
@WebServlet("/registration2/*")
public class registration2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     */
    public registration2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text = "some text";

	    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(text);       // Write response body.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		 HttpSession session = request.getSession();
	      String auth=(String)session.getAttribute("auth");  
	      if(!auth.equals("1")){
	    	  
	    	  session.invalidate();//destroy any session that they may have
	    	 response.sendRedirect(request.getContextPath() + "/index.jsp");
	    	//  out.println("redirect "+request.getContextPath() + "/login2.jsp");
	    	 return;
	      }
	*/	// String username = request.getParameter("username");
	      String password = request.getParameter("password");
	      String first = request.getParameter("fname");
	      String last = request.getParameter("lname");
	    //  String phone = request.getParameter("phone");
	   //   String address = request.getParameter("address");
	      String email = request.getParameter("email");
	      double btc_holdings=0.0;
	      double loc_holdings=0.0;
	      PrintWriter out = response.getWriter();
	      response.setContentType("application/json");
	      response.setCharacterEncoding("UTF-8");
	   	  String token = new RandomString(21).nextString();         

//	      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//	      response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//	      response.setDateHeader("Expires", 0); // Proxies.
//	      out.println(" in doRegistration   ");
			Connection conn=null;
			Statement stmt=null;
			double bitCoin=-1;
			double lotCoin=-1;
			int userId=0;
			try {
				final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
				final String DB_URL="jdbc:mysql://3.16.62.130/cl";

				//  Database credentials
				final String USER = "cl";
				final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
				// Register JDBC driver
				Class.forName("com.mysql.jdbc.Driver");

				// Open a connection
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// Execute SQL query
				stmt = conn.createStatement();
		//-------------------------------------------------------		
				
				 String sql;
///	  	         sql = "SELECT email FROM users where email = '"+ email + "'  ;" ;
                  sql = "SELECT email FROM users where email = ?  ;" ;
	  	          PreparedStatement ps = conn.prepareStatement(sql);
	  	          ps.setString(1, email);
	  	       //  ResultSet rs = stmt.executeQuery(sql);
	  	         ResultSet rs = ps.executeQuery();
	  	         boolean userNameAllreadyPresent=false;
	  	         // Extract data from result set
	  	         if(rs.first()){	
	  	        	userNameAllreadyPresent=true;    
	  	         }
	  	         
		//---------------------------------------------------------	
	  	         if(userNameAllreadyPresent==false) {
				     	//sql = "INSERT INTO users ( user_id, email, fname, lname, password, phone, address, btc_holdings, loc_holdings ) VALUES ( 0, '" +
							//	email+"', '"+first+"', '"+ last +"', '"+password+"', '"+phone+"', '"+address+ "', "+btc_holdings+ ", "+loc_holdings+" );"; 
						   // stmt.executeUpdate(sql);
					     	sql = "INSERT INTO users ( user_id, email, fname, lname, password, btc_holdings, loc_holdings, join_ip, last_ip ) VALUES ( 0, ?, ?, ?, ?, ?, ? , ? , ?  );"; 
							   // stmt.executeUpdate(sql);
					     	
					     	 String ip = request.getHeader("X-Forwarded-For");  
					         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
					             ip = request.getHeader("Proxy-Client-IP");  
					         }  
					         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
					             ip = request.getHeader("WL-Proxy-Client-IP");  
					         }  
					         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
					             ip = request.getHeader("HTTP_CLIENT_IP");  
					         }  
					         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
					             ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
					         }  
					         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
					             ip = request.getRemoteAddr();  
					         }     	
					     	
				    PreparedStatement ps2 = conn.prepareStatement(sql);
				    ps2.setString(1, email);
				    ps2.setString(2, first);
				    ps2.setString(3, last);
				    ps2.setString(4, password);
				   // ps2.setString(5, phone);
				   // ps2.setString(6, address);
				    ps2.setDouble(5, btc_holdings);
				    ps2.setDouble(6, loc_holdings);
				    ps2.setString(7, ip);
				    ps2.setString(8, ip);
				    ps2.executeUpdate();
				    
//			     	sql = "INSERT INTO verify_tokens " + 
//			     			" (  token_id, user_id, token_type, token ) VALUES ( " +
//						0 +" , LAST_INSERT_ID() , 1 , '"+token+"' );"; 
			     	sql = "INSERT INTO verify_tokens " + 
			     			" (  token_id, user_id, token_type, token ) VALUES ( 0  , LAST_INSERT_ID() , ? , ? );"; 
				    PreparedStatement ps3 = conn.prepareStatement(sql);
				    ps3.setInt(1, 1);
				    ps3.setString(2, token);
				    ps3.executeUpdate();
			     	
				  //  stmt.executeUpdate(sql);
				   /* {  
				    	   "result":1,
				    	   "email":"mailbrent@gmail.com",
				    	   "firstname":"bReNt"
				    	}				    
				    */
		  	         JsonGenerator gen = Json.createGenerator( response.getWriter());
		  	          gen.writeStartObject()
		  	          .write("result", 1)	  	           
		  	           .write("email", email)
		  	          .write("fname", first)
		  	           .write("lname", last)
		  	        //  .write("phone", phone)
		  	         //  .write("address", address)
		  	           .write("token", token)
		  	           .writeEnd();
		  	          gen.close();
	  	         }
	  	         else {
	  	        	 /*
	  	        	  * {
	                      "result":0,
	                       "error": "Could not insert"
}
	  	        	  */
	  	        //  Map<String, String> options = new LinkedHashMap<>();
	  	       //  options.put("result", "0");
	  	       //  options.put("result", "Could not insert");
	  	         JsonGenerator gen = Json.createGenerator( response.getWriter());
	  	          gen.writeStartObject()
	  	           .write("result", 0)	  	           
	  	           .write("error", "Could not insert")
	  	           .writeEnd();
	  	          gen.close();
	  	         }
				stmt.close();
				conn.close();
				//response.sendRedirect(request.getContextPath() + "/WaitForRegistration");
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
			
	}

}
