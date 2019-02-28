<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.io.*, java.sql.*,java.net.URL,javax.json.*,javax.servlet.*,javax.servlet.http.* "%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
String userName = request.getParameter("username");
String password = request.getParameter("password");
String submit = request.getParameter("submit");

  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  final String DB_URL="jdbc:mysql://3.16.62.130/cl";
      //int age = rs.getInt("age");
      String email=null ;
      String last=null ;
      double bitCoin =0;
	    double lotCoin =0 ;
	    double PendingBTCDep =0 ;
	    double PendingBTCWithdrawal =0 ;
	    double PendingLOCKWith =0 ;
	    boolean status = false;
	    int user_id  = 0;
 //  Database credentials
  final String USER = "cl";
  final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
    Connection conn=null;
    Statement stmt=null;
           if( userName != null){
	    	 // HttpSession session = request.getSession();
	    	  session.invalidate();//destroy any session that they may have
	    	  
	    	  out.println("<h1>(userName != null)="+userName+"</h1>");

	  	      try {
	  	         // Register JDBC driver
	  	          Class.forName("com.mysql.jdbc.Driver");

	  	         // Open a connection
	  	         conn = DriverManager.getConnection(DB_URL, USER, PASS);

	  	         // Execute SQL query
	  	         stmt = conn.createStatement();
	  	         String sql;

	  	         sql = "SELECT * FROM users where email = ? and password = ? ;" ;
	  	         PreparedStatement ps = conn.prepareStatement(sql);
	  	         ps.setString (1, userName);
	  	         ps.setString (2, password);
	  	         
	  	         ResultSet rs = ps.executeQuery();	
	  	       out.println("<h1>"+userName+"</h1>");
	  	         if(rs.first()){
	  	        	out.println("<h1>Valid user name</h1>");
	  	        	session = request.getSession();
	  	        	session.invalidate();
	   	    	     session = request.getSession();
	  	    	     session.setAttribute("username", userName);
	  	    	     session.setAttribute("password", password);
	  	    	     session.setAttribute("auth", "1");
	   	    	     status = rs.getBoolean("approved");
	    	            //int age = rs.getInt("age");
	    	         email = rs.getString("email");
	    	         //out.println("<h1>"+email+"</h1>");
	    	         last = rs.getString("lname");
	    	         bitCoin = rs.getDouble("btc_holdings");
	    	    	 lotCoin = rs.getDouble("loc_holdings");
	    	    	 PendingBTCDep = rs.getDouble("PendingBTCDep");
	    	    	 PendingBTCWithdrawal = rs.getDouble("PendingBTCWithdrawal");
	    	    	 PendingLOCKWith = rs.getDouble("PendingLOCKWith");
	    	    	 user_id = rs.getInt("user_id"); 
	    	    	 session.setAttribute("user_id",""+ user_id);
		    	     if(status==false){
	  	    	    	rs.close();
	  	    	    	stmt.close();
	  	    	    	conn.close();
	  	    	    	response.sendRedirect(request.getContextPath() + "/WaitForRegistration");
	  	    	    }
		    	     response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
	  	         }else {
	  	        	out.println("<h1>Invalid user name</h1>");
	  	        	response.sendRedirect(request.getContextPath() + "/index.jsp");
	  	        	//return;
	  	         }
	  	         
	  	      }catch(Exception e){
	  	    	  out.println(e);
	  	      }
	    	  	    	  
	      }
	      %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>