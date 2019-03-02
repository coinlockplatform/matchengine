<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<p id="demo"></p>

<script>

$(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
    $.get("https://api.coincap.io/v2/assets?search=BYC&limit=5", function(data1) { 
    	
    	var myJSON = JSON.stringify(data1);
    	document.getElementById("demo").innerHTML = myJSON; 
    })
    }
    );

</script>
</head>
<body>

  <button id="somebutton">press here</button></p>
<p id="demo"></p>
</body>
</html>