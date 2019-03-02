<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="includes/header.jsp" />

               
<section id="contact-form" >
    <div class="container pt100 pb100">
        <div class="col-md-8 col-sm-8 m-auto">
			<h3>Please login</h3>

			
			
			
                <form action="dashboard.jsp" method="POST" name="contact" id="contact" novalidate>
                    <div class="text-center">
                        <div class="form-group">
                            <input class="form-control" type="text" name="username" id="unsername" size="30" placeholder="Your Email *" required>
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="password" name="password" id="password" size="30"  placeholder="Your Password *" required>
                        </div>
                      
                        <div class="form-group">
                            <button type="submit" value='Log In' class="btn btn-hero">Submit</button>
                            <br />
                            <a href='reset_pass.jsp'>Forgot Password</a> | <a href='register.jsp'>Register</a>
                        </div>
                    </div>
                    
                </form>												
		</div>
	</div>
</section>
<center>
<FORM NAME="marquee1">
 Referral:
	<INPUT class="form-textbox" NAME="text" SIZE=50 VALUE=" Enter email of person who introduced you. "> 
</FORM> 
</center>




<div id="demo">
</div>
<jsp:include page="includes/footer.jsp" />