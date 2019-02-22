

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidateMoneyRecieved
 */
public class ValidateMoneyRecieved extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateMoneyRecieved() {
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

 /*  	  "<input type='hidden' id='bitcion_price_at_match' name='bitcion_price_at_match' value='"+bitcion_price_at_match+"'>"+
  		  "<input type='hidden' id='time_stamp' name='time_stamp' value='"+time_stamp+"'>"+
  		  "<input type='hidden' id='match_id' name='match_id' value='"+match_id+"'>"+
  		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
  		  "<input type='hidden' id='trade_id' name='trade_id' value='"+trade_id+"'>"+
  		  "<input type='hidden' id='trade_email' name='trade_email' value='"+trade_email+"'>"+
  		  "<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
  		  "<input type='hidden' id='user_id' name='user_id' value='"+user_id+"'>"+
  		  "<input type='hidden' id='username' name='username' value='"+userName+"'>"+
		  "<input type='hidden' id='protect_amount_matched' name='protect_amount_matched' value='"+protect_amount_matched+"'>"+    				  
		  "<input type='submit' value='Money Validate' class='btn btn-hero'>"+
*/
		PrintWriter out = response.getWriter();
		//String protect_email = request.getParameter("protect_email");
		int match_id = Integer.parseInt(request.getParameter("match_id"));
		//double protect_amount_matched = Double.parseDouble(request.getParameter("protect_amount_matched"));
	//	int user_id = Integer.parseInt(request.getParameter("user_id"));
		//double dollarValue = Double.parseDouble(request.getParameter("dollarValue"));
		String username=request.getParameter("username");
		
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
		     String   sql = "select money_transfer from matched where match_id="+match_id+" ;";
		     ResultSet  rs = stmt.executeQuery(sql);
             rs.next();
             String money_transfer = rs.getString("money_transfer");
             rs.close();
		      sql ="UPDATE matched set protect_email = '"+money_transfer+"' ,  money_transfer='no', is_escrow=0 WHERE match_id="+match_id+" ;";
		     stmt.executeUpdate(sql);
		     out.println("<h2 style='margin-top: 100px; margin-left: auto; margin-right: auto;'> Acknowledged receipt of Funds "+match_id+" protect side now "+money_transfer+"</h2>");
		     conn.close();
		     stmt.close();
		  } catch(Exception e){
			  out.println(e);
		  }
		  
		  request.getRequestDispatcher("includes/footer.jsp").include(request, response);
	}

}
