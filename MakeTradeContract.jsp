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
<form action="TradeInfo.jsp" method="post">

  <fieldset>
    <legend>Trade:</legend>
    Name:<br>
    <input type="text" name="name" value=""></br></br>
    Password:<br>
    <input type="text" name="password" value=""></br></br>
    Amount of BTC you want to trade:<br>
    <input type="text" name="btcTradeAmount" value=""></br></br>
    <input type="submit" value="Submit"></br>
  </fieldset>
</form>
</body>
</html>