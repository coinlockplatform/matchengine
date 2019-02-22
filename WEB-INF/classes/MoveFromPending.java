

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MoveFromPending
 */
public class MoveFromPending extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoveFromPending() {
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
		String email = request.getParameter("email");
		  double btc_holdings =Double.parseDouble(request.getParameter("btc_holdings"));
	      double BTC = Double.parseDouble(request.getParameter("BTC"));
	      
	      PrintWriter out = response.getWriter();
	   //   out.println("<h3>  email="+email+"  ethereum="+ethereum+" BTC="+BTC+" is registered</h3>");
			Connection conn=null;
			Statement stmt=null;
			
			try {
				final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
				final String DB_URL="jdbc:mysql://3.16.62.130/cl";

				//  Database credentials
				final String USER = "cl";
				final String PASS = "\r\n" + 
						"Ra.cQH&&ZrFG(44e)Uf";
				// Register JDBC driver
				Class.forName("com.mysql.jdbc.Driver");

				// Open a connection
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// Execute SQL query
				stmt = conn.createStatement();
				String sql;
				sql = "UPDATE users set  btc_holdings = "+(BTC+btc_holdings)+", PendingBTCDep = 0.0 where email = '"+email+"'  ;"; 
				stmt.executeUpdate(sql);
				out.println("<h3>Done BTC transfered from pending!!</h3>");
				
				
				
				stmt.close();
				conn.close();
				//response.sendRedirect(request.getContextPath() + "/AdminEscrowDo2");
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
