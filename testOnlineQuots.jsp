<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>SO question 4112686</title>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
            $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                $.get("https://api.coincap.io/v2/assets?search=BYC&limit=5", function(data1) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                	//document.getElementById("somediv").innerHTML = "hello";//JSON.stringify(data1, undefined, 2);
                	                 //  $("#somediv").text("hello");           // Locate
  /*              	                 
                	var ciResponseText = document.getElementById('ciResponseText');
var obj = JSON.parse(http.response);
ciResponseText.innerHTML = JSON.stringify(obj, undefined, 2);                  
                	var items = [];
                	  $.each( data1, function( key, val ) {
                	    items.push( "<li id='" + key + "'>"+key + " : " + val + "</li>" );
                	    if(key=="data"&&data1.data!=null){
                	    	document.write(  data1.data["btc_holdings"]);
                	    	// document.getElementById("'"+ key+"'").innerHtml+="hello";//(  dataIn.data.user_id);
                	    }
                	  });
                	 
                	  $( "<ul/>", {
                	    "class": "my-new-list",
                	    html: items.join( "" )
                	  }).appendTo( "body" );
                	//  document.write(  key.user_id);
                })
            });
     
</script>
    </head>
    <body>
        <button id="somebutton">press here</button>
         <pre id="ciResponseText">Output will de displayed here.</pre>
    </body>
</html>