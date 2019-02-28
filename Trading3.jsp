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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	double amountToTrade = Double.parseDouble(request.getParameter("amountOfBit"));
	double profitLoss = Double.parseDouble(request.getParameter("profitLoss"));
	String userName = request.getParameter("username2"); 
	String password = request.getParameter("password2");
	String data2 = request.getParameter("data2");
	int user_id = Integer.parseInt(request.getParameter("user_id2"));

%>
<div id="data"></div>
<script type="text/javascript">
document.getElementById("data").innerHTML = "<%=data2%>";


</script>


</body>
</html>