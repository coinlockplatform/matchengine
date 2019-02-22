

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StillWaiting
 */
public class StillWaiting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StillWaiting() {
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

		int escrowId = Integer.parseInt(request.getParameter("escrowId"));
		  String userName = request.getParameter("username");
		//  String password = request.getParameter("password");
	      Connection conn=null;
	      Statement stmt=null;
	      PrintWriter out = response.getWriter();
	      ResultSet rs = null;
	      final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL="jdbc:mysql://3.16.62.130/cl";

	      //  Database credentials
	       final String USER = "cl";
	       final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
	       request.getRequestDispatcher("includes/header.jsp").include(request, response);
	      try {
	         // Register JDBC driver
	          Class.forName("com.mysql.jdbc.Driver");

	         // Open a connection
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         stmt = conn.createStatement();
	        String sql = "SELECT * FROM escrow where escrow_id = "+escrowId+" ;" ;
	        rs = stmt.executeQuery(sql);
	         if(rs.next()){
	        	 int done = rs.getInt("escrow_done");
	        	 if(done==1){
	        		      RequestDispatcher dispatcher = getServletContext()
	        			                 .getRequestDispatcher("/FinishedWaiting.jsp");
	        			    dispatcher.forward(request, response);
	        	 }
	        	 else{  
	        	//	 out.println("<html><head></head><body>");
	        		 out.println ("<p><p><p><p><br/><br/><br/><br/><br/><br/><center>");
	        		 out.println("<h2 style='text-align: center;'>Waiting for finalization of  BTC escrow</h2>");
		     		 out.println("<form method='POST' action='StillWaiting' >");
		     		 out.println("<input type='hidden' id='escrowId' name='escrowId' value='"+escrowId+"'>");
		     		 out.println("<input type='hidden' id='userName' name='userName' value='"+userName+"'>");
		     		 //out.println("<h4>Amount of LockCoin to Buy </h4><input type='text' id='buyLockAmount' name='buyLockAmount' value=''>");
		     		 out.println("<input type='submit' value='Check to see if BTC is locked' class='btn btn-hero'>");   	            
		     		 out.println("</form></center>");
		     		out.println("</p>");
	        	 }
	         }

	      }catch(Exception e){
	    	  out.println(e);
	      }
	      request.getRequestDispatcher("includes/footer.jsp").include(request, response);
	}

}
