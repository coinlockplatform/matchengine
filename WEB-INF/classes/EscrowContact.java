

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EscrowContact
 */
public class EscrowContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EscrowContact() {
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
		String protect_email = request.getParameter("protect_email");
		int match_id = Integer.parseInt(request.getParameter("match_id"));
		double protect_amount_matched = Double.parseDouble(request.getParameter("protect_amount_matched"));
	//	int user_id = Integer.parseInt(request.getParameter("user_id"));
		double dollarValue = Double.parseDouble(request.getParameter("dollarValue"));
		String username=request.getParameter("username");
		request.getRequestDispatcher("includes/header.jsp").include(request, response);
		
		out.println("<h2 style='margin-top: 100px; margin-left: auto; margin-right: auto;'>Please Send $ "+dollarValue+" to "+protect_email+" using Simple Banking App   <a href='http://simple.com'> Simple.com</a></h2>");
	         String form2 ="<form action='ConfirmFundsTransfered' method='POST'>"+
	   	   	"<input type='hidden' id='protect_email' name='protect_email' value='"+protect_email+"'>"+
		   	"<input type='hidden' id='match_id' name='match_id' value='"+match_id+"'>"+
			  "<input type='hidden' id='protect_amount_matched' name='protect_amount_matched' value='"+protect_amount_matched+"'>"+    				  
			  "<input type='hidden' id='dollarValue' name='dollarValue' value='"+dollarValue+"'>"+  
			//  "<input type='hidden' id='user_id' name='user_id' value='"+user_id+"'>"+
			  "<input type='hidden' id='username' name='username' value='"+username+"'>"+
			  "<input type='submit' value='Confirm Funds Transferred' class='btn btn-hero'>"+
			  "</form>";
	         out.println(form2);
		request.getRequestDispatcher("includes/footer.jsp").include(request, response);
	}

}
