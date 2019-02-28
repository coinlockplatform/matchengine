<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    //session = request.getSession();

    String auth=(String)session.getAttribute("auth");
    if(auth!=null && auth.equals("1")){
        response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        return;
    }
%>
<jsp:include page="/includes/header.jsp" />
<link href="/css/reg_login_forgot.css" rel="stylesheet" type="text/css">
<script src="/assets/js/reg_login_forgot.js"></script>
<script src="/global_assets/js/demo_pages/form_checkboxes_radios.js"></script>
<script src="/global_assets/js/plugins/notifications/sweet_alert.min.js"></script>



	<!-- Page content -->
	<div class="page-content pt-0">
		<!-- Main content -->
		<div class="content-wrapper">
			<!-- Content area -->
			<div class="content">
				<!-- Dashboard content -->
				<div class="row">
					<div class="col-xl-12 mt-5">

                        <div class="row justify-content-md-center">
                            <div class="col-12 text-center mb-4">
                                <img src='/global_assets/images/matchengine-logo-big.png' width='480' height='165' alt='matchengine.io'>
                            </div>
                            <div class="col col-lg-2"></div>
                                    





                            <!-- Login Form -->
                            <div id='login-form-cont' class="card logincontainer col-md-auto">
                                <div class="card-header header-elements-inline">
                                    <h1 class="card-title">Sign In</h1>
                                </div>
                                <div class="card-body">
                                    <form id='login-form'>
                                        <input type="email" class="email" id='login_email' name='email' placeholder="EMAIL" required title='you need to do this'><br /><div id='login_email_e' class='errormsg'></div>
                                        <input type="password" class="pwd" id='login_password' name='password' placeholder="PASSWORD" required><br /><div id='login_password_e' class='errormsg'></div>
                                        <br/>
                                        <button class="signin" id="login-form-submit" type='submit' value='login' name="submit"><span> login </span></button>
                                    </form>
                                </div>
								<div class="text-center">
                                    <a href="#" onclick="switchRegLogin('register');" class="breadcrumb-item"> Don't have an account?</a>
                                    <a href="#" onclick="switchRegLogin('forgotpass');"  class="breadcrumb-item">Forgot password</a>
								</div>
                            </div>












                            <!-- Register Form -->
                            <div id='register-form-cont' class="card logincontainer col-md-auto">
                                <div class="card-header header-elements-inline">
                                    <h1 class="card-title">Register for an Account</h1>
                                </div>
                                <div class="card-body">
                                    <form id='register-form'>
                                        <input value='test@test.com' type="email" class="email" id='reg_email' name='email' placeholder="EMAIL"><br/><div id='reg_email_e' class='errormsg'></div>
                                        <input value='bob' type="text" class="pwd" id='reg_first_name' name='fname' placeholder="FIRST NAME"><br/><div id='reg_first_name_e' class='errormsg'></div>
                                        <input value='test' type="text" class="pwd" id='reg_last_name' name='lname' placeholder="LAST NAME"><br/><div id='reg_last_name_e' class='errormsg'></div>
                                        <input value='qqQQ11!!' type="password" class="pwd" id='reg_password' name='password' placeholder="PASSWORD"><br/><div id='reg_password_e' class='errormsg'></div>
                                        <input value='qqQQ11!!' type="password" class="pwd" id='reg_password_confirm' name='rpassword_confirm' placeholder="CONFIRM"><br /><div id='reg_password_confirm_e' class='errormsg'></div>
                                        
                                        
                                            <div class="form-check ml-4">
                                                <label class="form-check-label" id='reg_agree_label'>
                                                    <input type="checkbox" id='reg_agree' name='agree' class="form-check-input-styled-primary" data-fouc>
                                                    Check if you agree to terms
                                                </label>
                                                <br /><div id='reg_agree_e' class='errormsg'></div>
                                            </div>                                        

                                        
                                        <div class='termsx'>
                                                <h4>Terms of Service</h4>
                                                <br />
                                                <strong>Using our services</strong>
                                                <p>
                                                    Thanks for using our products and services ("Services"). The Services are provided by Google LLC ("Google"), located at 1600 Amphitheatre Parkway, Mountain View, CA 94043, United States.<br />
                                                    By using our Services, you are agreeing to these terms. Please read them carefully.<br />
                                                    Our Services are very diverse, so sometimes additional terms or product requirements (including age requirements) may apply. Additional terms will be available with the relevant Services, and those additional terms become part of your agreement with us if you use those Services.
                                                </p>
                                                <strong>Policies</strong>
                                                <p>
                                                    You must follow any policies made available to you within the Services.
                                                    <br />
                                                    Don’t misuse our Services. For example, don’t interfere with our Services or try to access them using a method other than the interface and the instructions that we provide. You may use our Services only as permitted by law, including applicable export and re-export control laws and regulations. We may suspend or stop providing our Services to you if you do not comply with our terms or policies or if we are investigating suspected misconduct.
                                                    <br />
                                                    Using our Services does not give you ownership of any intellectual property rights in our Services or the content you access. You may not use content from our Services unless you obtain permission from its owner or are otherwise permitted by law. These terms do not grant you the right to use any branding or logos used in our Services. Don’t remove, obscure, or alter any legal notices displayed in or along with our Services.
                                                    <br />
                                                    Our Services display some content that is not Google’s. This content is the sole responsibility of the entity that makes it available. We may review content to determine whether it is illegal or violates our policies, and we may remove or refuse to display content that we reasonably believe violates our policies or the law. But that does not necessarily mean that we review content, so please don’t assume that we do.
                                                </p>
                                                <strong>Additional Terms</strong>
                                                <p>
                                                    Thanks for using our products and services ("Services"). The Services are provided by Google LLC ("Google"), located at 1600 Amphitheatre Parkway, Mountain View, CA 94043, United States.<br />
                                                    By using our Services, you are agreeing to these terms. Please read them carefully.<br />
                                                    Our Services are very diverse, so sometimes additional terms or product requirements (including age requirements) may apply. Additional terms will be available with the relevant Services, and those additional terms become part of your agreement with us if you use those Services.
                                                </p>
                                                <strong>Using our services</strong>
                                                <p>
                                                    Thanks for using our products and services ("Services"). The Services are provided by Google LLC ("Google"), located at 1600 Amphitheatre Parkway, Mountain View, CA 94043, United States.<br />
                                                    By using our Services, you are agreeing to these terms. Please read them carefully.<br />
                                                    Our Services are very diverse, so sometimes additional terms or product requirements (including age requirements) may apply. Additional terms will be available with the relevant Services, and those additional terms become part of your agreement with us if you use those Services.
                                                </p>
                                                <strong>Policies</strong>
                                                <p>
                                                    You must follow any policies made available to you within the Services.
                                                    <br />
                                                    Don’t misuse our Services. For example, don’t interfere with our Services or try to access them using a method other than the interface and the instructions that we provide. You may use our Services only as permitted by law, including applicable export and re-export control laws and regulations. We may suspend or stop providing our Services to you if you do not comply with our terms or policies or if we are investigating suspected misconduct.
                                                    <br />
                                                    Using our Services does not give you ownership of any intellectual property rights in our Services or the content you access. You may not use content from our Services unless you obtain permission from its owner or are otherwise permitted by law. These terms do not grant you the right to use any branding or logos used in our Services. Don’t remove, obscure, or alter any legal notices displayed in or along with our Services.
                                                    <br />
                                                    Our Services display some content that is not Google’s. This content is the sole responsibility of the entity that makes it available. We may review content to determine whether it is illegal or violates our policies, and we may remove or refuse to display content that we reasonably believe violates our policies or the law. But that does not necessarily mean that we review content, so please don’t assume that we do.
                                                </p>
                                                <strong>Additional Terms</strong>
                                                <p>
                                                    Thanks for using our products and services ("Services"). The Services are provided by Google LLC ("Google”), located at 1600 Amphitheatre Parkway, Mountain View, CA 94043, United States.<br />
                                                    By using our Services, you are agreeing to these terms. Please read them carefully.<br />
                                                    Our Services are very diverse, so sometimes additional terms or product requirements (including age requirements) may apply. Additional terms will be available with the relevant Services, and those additional terms become part of your agreement with us if you use those Services.
                                                </p>
                                        </div>
                                        <br/>
                                        <button class="register pr-4" id="register-form-submit" type='submit' value='register'>
                                            <span> Register </span>
                                        </button>
                                    </form>
                                </div>
                                <br />
								<div class="text-center">
                                    <a href="#" onclick="switchRegLogin('login');" class="breadcrumb-item"> Have an account? Sign In</a>
                                    <a href="#" onclick="switchRegLogin('forgotpass');"  class="breadcrumb-item">Forgot password</a>
								</div>
                            </div>










                            <!-- Forgot Pass Form -->
                            <div id='forgotpass-form-cont' class="card logincontainer col-md-auto">
                                <div class="card-header header-elements-inline">
                                    <h1 class="card-title">Forgot Password</h1>
                                </div>
                                <div class="card-body">
                                    <form id='forgotpass-form'>
                                        <input type="email" class="email" id='forgotpass_email' name='email' placeholder="EMAIL YOU REGISTERED WITH" required><br/><div id='forgotpass_email_e' class='errormsg'></div>
                                        <br/>
                                        <button class="register" id="forgotpass-form-submit" type='submit' value='register' name="submit">
                                            <span> Reset Password </span> 
                                        </button>   
                                    </form>
                                </div>
                                <br />
								<div class="text-center">
                                    <a href="#" onclick="switchRegLogin('login');" class="breadcrumb-item"> Have an account? Sign In</a>
                                    <a href="#" onclick="switchRegLogin('register');"  class="breadcrumb-item">Don't have an Account</a>
								</div>
                            </div>



                            <div class="col col-lg-2"></div>
                        </div>









					</div>
				</div>
				<!-- /dashboard content -->
			</div>
			<!-- /content area -->
			<!-- / End every page content-->
		</div>
		<!-- /main content -->
	</div>
	<!-- /page content -->
<jsp:include page="/includes/footer.jsp" />