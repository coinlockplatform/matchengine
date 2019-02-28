<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
String auth=(String)session.getAttribute("auth");  
if(!auth.equals("1")){
	  
	  session.invalidate();//destroy any session that they may have
	 response.sendRedirect(request.getContextPath() + "/index.jsp");
	//  out.println("redirect "+request.getContextPath() + "/login2.jsp");
	 return;
}
%>
<jsp:include page="includes/header.jsp" />
<% 
 out.println("<br/>");
					  out.println("<div style='background-color: #dddddd; margin-left:auto; margin-right: auto; width: 200px; padding: 15px;'>");
		    	         
		    	        // out.println(" <br/>");
		        		 out.println("<form name='calc' method='POST'  >");
		        		 out.println("Cash Out Bitcoin");
		        		 out.println("<h6><input type='text' id='btcToSell' name='btcToSell' value='' size='10' >Sell BTC </h6>");
		        		 out.println("<h6><input type='text' id='btcPrice' name='btcPrice' value=''  size='10' >BTC Price </h6>");
		        		 
		        		 out.println("<h6><input type='checkbox' id='autoLeverage75' name='autoLeverage75' value='' size='10' >75% Auto Leverage </h6>");
		        		 out.println("<h6><input type='text' id='Leverage Auto' name='LevAuto' value=''>Auto Leverage in BTC</h6>");
		        		 out.println("<h6><input type='text' id='netSale' name='netSale' value=''>Net Cash Out USD</h6>");
		        		 out.println("<input type=button onClick='showpay()' value='Confirm'>");   	            
		        		 out.println("</form>");
		        		 out.println(" </div>"); 
 %>		        

<jsp:include page="includes/footer.jsp" />