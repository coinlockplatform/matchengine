<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
session = request.getSession();
String auth=(String)session.getAttribute("auth");
// out.println("<h1>String auth="+auth+"</h1>"); 
if(auth==null||!auth.equals("1")){
	  out.println("<h1>String auth="+auth+"</h1>"); 
	  session.invalidate();//destroy any session that they may have
	 response.sendRedirect(request.getContextPath() + "/index.jsp");
	//  out.println("redirect "+request.getContextPath() + "/login2.jsp");
	 return;
}
String userName = (String)session.getAttribute("username");
int user_id = Integer.parseInt((String)session.getAttribute("user_id"));
String password = (String)session.getAttribute("password");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@page import="java.sql.*"%>
<%
    //JDBC driver name and database URL
   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   final String DB_URL="jdbc:mysql://3.16.62.130/cl";

   //  Database credentials
   final String USER = "cl";
  final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
  double amountToTrade = Double.parseDouble(request.getParameter("autoTrade"));
//  String userName = request.getParameter("username"); 
//  String password = request.getParameter("password");
//  int user_id = Integer.parseInt(request.getParameter("user_id"));

  Connection conn=null;
  Statement stmt=null;
  try {
     // Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

     // Open a connection
     conn = DriverManager.getConnection(DB_URL, USER, PASS);

     // Execute SQL query
     stmt = conn.createStatement();
     String sql;
     sql = "SELECT * FROM users where email = '"+ userName + "' and password = '" + password +"' ;" ;
     ResultSet rs = stmt.executeQuery(sql);
     String javaCalcLogic="";
     // Extract data from result set
     
             //user_id  = rs.getInt("user_id");
        //int age = rs.getInt("age");
        String email = null;
        String last = null;
        double bitCoin = 0;
	    double lotCoin = 0;

    // int user_id = 0;
     if(rs.next()){
        //Retrieve by column name
        //user_id  = rs.getInt("user_id");
        //int age = rs.getInt("age");
        email = rs.getString("email");
        last = rs.getString("lname");
        bitCoin = rs.getDouble("btc_holdings");
	    lotCoin = rs.getDouble("loc_holdings");

     } 
	    if(bitCoin>amountToTrade){
	    	sql = "UPDATE users " +
					"SET btc_holdings = "+(bitCoin-amountToTrade)+"  where user_id =  "+user_id+" ;" ;
			stmt.executeUpdate(sql);
			
	    }else{
	    	
	    	%> //todo output to little bitcoin<% 
	    }  
   } catch(SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
   } catch(Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
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
         } //end finally try
    } //end try
     
     
     
%>
<!--  input type="text" name="amountOfBit" id="amountOfBit" />-->

<!-- <button  onclick="timeToStart(3, 35, 0)" >activate</button>-->
<div id="data"></div>
		  <form action="Trading3.jsp" method="POST" id="trading3">
	        <input type='hidden' id='data2' name='data2' />		  
	        <input type='hidden' id='username2' name='username2' />		  
	        <input type='hidden' id='password2' name='password2'/>		  
	        <input type='hidden' id='user_id2' name='user_id2'/>		  
	        <input type='hidden' id='amountOfBit' name='amountOfBit'/>		  
	        <input type='hidden' id='profitLoss' name='profitLoss'/>		  
		  </form>
  <script type="text/javascript">
  
  
//  document.getElementById("myText").value = "Johnny Bravo";

// <input type="text" name="firstname" id="firstname"/>
  var json="";
 
  var locPrice=<%= 0 %>;
  var username=<%= 0 %>;
  var password=<%= 0 %>;
  var user_id=<%= 0 %>;
  
  
  function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
  
  function getBtcPrice()
  {
    var xmlhttp = new XMLHttpRequest();
    var url = "https://api.coindesk.com/v1/bpi/currentprice.json";
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4  &&  this.status == 200) {
    	  
    	  
        stopCounter++;  
       json = JSON.parse(this.responseText);
        parseJson(json);
        
        
        
        if(stopCounter==7){
        	stop();
        	
        	stopCounter=0;
        	
        }
      }		
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }     
  function getLocPrice()
  {
    var xmlhttp = new XMLHttpRequest();
    var url = "https://localhost/second/SendLockCoinPrice";
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4  &&  this.status == 200) {
       locPrice = this.responseText;
      
      }		
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }    
    function parseJson(json) {
      var time = "<b>Last Updated : " + json["time"]["updated"] + "</b>";
      var usdValue =  (json["bpi"]["USD"]["rate"]).replace(/,/g, '');

      
      
//      var amountOfBit = document.getElementById("amountOfBit");
      var amountOfBit = <%=amountToTrade%>;
      var dollarInvested=0;
      
      var counter = stopCounter;
      if(stopCounter<=6)
            dollarInvested = amountOfBit/6*stopCounter * (json["bpi"]["USD"]["rate"]).replace('/','/g', ''); //was .replace(/,/g, '');
     
      var gbpValue = "1 BTC equals to &pound;" + json["bpi"]["GBP"]["rate"];
      var euroValue = "1 BTC equals to &euro;" + json["bpi"]["EUR"]["rate"];

      var lockCoinValue = "1 LOCKCOIN equals to $" +locPrice ;
     document.getElementById("data").innerHTML +="<br />"+ time + 
	      "<br />1 BTC equals to $" + usdValue + 
		   "<br />" + dollarInvested +"<br />";
		  //  var row = "<tr><td>"+time+"</td><td>"+usdValue+"</td><td>"+dollarInvested+"</td></tr>";
		  //  alert(row);
		   // document.getElementById("data").innerHTML +=row;
	  if(stopCounter<=6){	   
           usdValueTotal+= parseFloat(usdValue);  
	  } 
	  if(stopCounter==7){
		  
		  stopPrice=usdValue*1;
		  averagePrice=usdValueTotal/6;
		  profitAmount=stopPrice-averagePrice;
		  
		  
		  totalProfit=amountOfBit*profitAmount;
		  
		  //alert("a3");
		  document.getElementById("data").innerHTML +="<br /><br /> <h3>total profit: "+totalProfit+"</h3>";
		  var data2 = document.getElementById("data").innerHTML;
		  document.getElementById("data2").value=data2;
		  document.getElementById("username2").value=username;
		  document.getElementById("password2").value=password;
		  document.getElementById("user_id2").value=user_id;
		  document.getElementById("amountOfBit").value=amountOfBit;
		  document.getElementById("profitLoss").value=totalProfit;
		  document.getElementById("trading3").submit();
		//  stopCounter=0;
	  }
 
    }
    var totalProfit=0.0;
    var averagePrice=0.0;
    var stopPrice=0.0;
    var usdValueTotal=0.0;
    var stopCounter=0;
    var myIntervalVar;
    
    function go(){
    	var d = new Date();
    	
    	//document.getElementById("data").innerHTML +="<br /><br /> <h3>date: "+d+"</h3>";
    	//document.getElementById("data").innerHTML="<table>";
    	myIntervalVar = setInterval(getBtcPrice, 2000);
    	//alert("a2");
         return myIntervalVar;
         
         
    }
    
    function stop(){
    	clearInterval(myIntervalVar);
    }
    function timeToStart(hour, minuet, second){
    	
    	var now = new Date();
    	var millisTill10 = new Date(now.getFullYear(), now.getMonth(), now.getDate(), hour, minuet, second, 0) - now;
    	if (millisTill10 < 0) {
    	     millisTill10 += 86400000; // it's after 10am, try 10am tomorrow.
    	}
    	setTimeout(function(){go()}, millisTill10);    	
 //   	setTimeout(go(), millisTill10);    	
    	//alert("a1");
    	//document.getElementById("data").innerHTML="<table>";
    }
    timeToStart(14, 14, 0);
</script>
</body>
</html>