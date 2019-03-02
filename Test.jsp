<!DOCTYPE html>
<html lang="en">
    <head>
        <title>SO question 4112686</title>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
            $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                $.post("api/v1/balances",{ action: "login",password: "Im@TheM00n", fname: "myfirst", lname : "mylast", email: "alan@coinlock.app", phone:"3213213212", address: "my way here"}, function(data1) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                   $("#somediv").text(data1);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                  
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
         // background.js
/*           browser.runtime.onMessage.addListener(message => {
              console.log("background: onMessage", message);

              // Add this line:
              return Promise.resolve("Dummy response to keep the console quiet");
            }); 
*/     
</script>
    </head>
    <body>
        <button id="somebutton">press here</button>
        <div id="somediv"></div>
    </body>
</html>