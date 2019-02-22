<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
/*String auth=(String)session.getAttribute("auth");  
if(!auth.equals("1")){
	  
	  session.invalidate();//destroy any session that they may have
	 response.sendRedirect(request.getContextPath() + "/index.jsp");
	//  out.println("redirect "+request.getContextPath() + "/login2.jsp");
	 return;

}*/
%>
<jsp:include page="includes/header.jsp" />               
<section id="contact-form" >
    <div class="container pt100 pb100">
        <div class="col-md-8 col-sm-8 m-auto">
			<h3>Registration</h3>

			
			
			
                <form action="doRegistration" method="POST" name="contact" id="contact" novalidate>
                    <div class="text-center">
                        <div class="form-group">
                            <input class="form-control" type="text" name="fname" id="fname" size="30" placeholder="Your First Name *" required>
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="lname" id="lname" size="30" placeholder="Your Last Name *" required>
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="email" id="email" size="30" placeholder="Your email  (user name)*" required>
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="phone" id="phone" size="30" placeholder="Your phone *" required>
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="text" name="address" id="address" size="30" placeholder="Your Address *" required>
                        </div>
                        <div class="form-group">
                            <!--  input class="form-control" type="text" name="username" id="unsername" size="30" placeholder="Choose User Name *" required-->
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="password" name="password" id="password" size="30"  placeholder="Password*" required>
                        </div>
                      
                        <div class="form-group">
                            <button type="submit" value='Log In' class="btn btn-hero">Submit</button>
              
                        </div>
                    </div>
                    
                </form> 
			
			
			
			
		</div>
	</div>
</section>
<center>
<FORM NAME="marquee1">
 Referral code:
	<INPUT class="form-textbox" NAME="text" SIZE=50 VALUE=" Remember to Add Refferal Code! "> 
</FORM> 
</center>

<jsp:include page="includes/footer.jsp" />

