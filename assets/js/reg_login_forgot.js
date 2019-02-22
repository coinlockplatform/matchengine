/* --------------------------------------------------------------------------------------
        * FORM VALIDATION
        * --------------------------------------------------------------------------------
        * 
------- */
function switchRegLogin(screen){
   switch(screen){
         case 'register': 
                        $('#login-form-cont').hide();
                        $('#register-form-cont').show();
                        $('#forgotpass-form-cont').hide();  
                        $('.logincontainer').css("height","742px");
                        break;
         case 'login': 
                        $('#login-form-cont').show();
                        $('#register-form-cont').hide(); 
                        $('#forgotpass-form-cont').hide(); 
                        $('.logincontainer').css("height","370px");
                        break;
         case 'forgotpass': 
                        $('#login-form-cont').hide();
                        $('#register-form-cont').hide(); 
                        $('#forgotpass-form-cont').show(); 
                        $('.logincontainer').css("height","304px");
                        break;
         default: 
                        break;
   }
}


$(document).ready(function(){
   $("#login-form").submit(function(e){
      if (!$("#login-form").valid()) {
         e.preventDefault(e);
      }
   });
   $('.navbar').hide();
   $('#login-form').validate({
      rules: {
            login_email: {
               required: true,
               minlength: 7
            },
            login_password: {
               required: true,
               minlength: 8,
               pwcheck: true,
               rangelength:[8,20]
            }
         },
         messages: {
            login_email: {
               required: "Email is required to login",
               minlength: "Email must contain at least {0} characters"
            },
            login_password: {
               required: "Please enter a password",
               pwcheck: "Password must contain a letter, a digit, and a special character",
               minlength: "Password must contain at least {0} characters"
            }
         },
         errorPlacement: function(error, element) {
            if(element.is(login_email)){
               error.appendTo($('#login_email_e'));
            }else if(element.is(login_password)){
               error.appendTo($('#login_password_e'));
            }
         }
   });

   $("#forgotpass-form").submit(function(e){
      if (!$("#forgotpass-form").valid()) {
         e.preventDefault(e);
      }
   });
   $('#forgotpass-form').validate({
      rules: {
         forgotpass_email:{
            required: true,
            minlength: 7,
            rangelength:[7,36],
            email:true
         }
      },
      messages: {
         forgotpass_email: {
            required: "Please enter the email you registered with",
            minlength: "Email must contain at least {0} characters"
         }
      },
      errorPlacement: function(error, element) {
         if(element.is(forgotpass_email)){
            error.appendTo($('#forgotpass_email_e'));
         }
      }
   });

   $("#register-form").submit(function(e){
      e.preventDefault(e);
      if ($("#register-form").valid()) {
         var post_url = $("#register-form").attr("action"); //get form action url
         var post_url = "test.json"; //get form action url
         var form_data = $("#register-form").serialize();


         $.ajax({
            url: post_url,
            timeout: 10000,
            data: form_data,
            method: "POST",
            dataType: 'json',
            success: function(json) {
               console.log("here is status:"+json.status);
               if(json.data){
                  console.log("here is email:"+json.data.email);
                  console.log("here is fname:"+json.data.fname);
               }
               if(json.status == "success"){
                     alert('registration was a success');
               
               }else{
                     alert('registration failed');
                     alert(json.message);
               }

            }
         });
      }
   });
   $('#register-form').validate({
         rules: {
            fname: {
               required:true,
               minlength:2,
               rangelength:[2,20],
               lettersonly:true
            },
            lname: {
               required:true,
               minlength:2,
               rangelength:[2,20],
               lettersonly:true
            },
            email:{
               required: true,
               minlength: 7,
               rangelength:[7,36],
               email:true
            },
            password:{
               required: true,
               minlength: 8,
               pwcheck: true,
               rangelength:[8,20]
            },
            password_confirm:{
               required: true,
               minlength: 8,
               rangelength:[8,20],
               equalTo:"#reg_password"
            },
            agree:{
               required:true
            }
         },
         messages: {
            fname: {
               required: "Please enter your first name",
               minlength: "First name must contain at least {0} characters",
               lettersonly: "Only letters are allowed"
            },
            lname: {
               required: "Please enter your last name",
               minlength: "Last name must contain at least {0} characters",
               lettersonly: "Only letters are allowed"
            },
            email: {
               required: "Please enter your email address",
               minlength: "Email must contain at least {0} characters"
            },
            password: {
               required: "Please enter a password",
               pwcheck: "Password must contain a letter, a digit, and a special character",
               minlength: "Password must contain at least {0} characters"
            },
            password_confirm: {
               required: "Please confirm your password",
               minlength: "Password must contain at least {0} characters",
               equalTo: "Passwords do not match"
            },
            agree: {
               required: "Please accept terms and conditions"
            }
         },
         errorPlacement: function(error, element) {
            if (element.is(reg_first_name)) {
               error.appendTo($('#reg_first_name_e'));
            }else if(element.is(reg_last_name)){
               error.appendTo($('#reg_last_name_e'));
            }else if(element.is(reg_email)){
               error.appendTo($('#reg_email_e'));
            }else if(element.is(reg_password)){
               error.appendTo($('#reg_password_e'));
            }else if(element.is(reg_password_confirm)){
               error.appendTo($('#reg_password_confirm_e'));
            }else if(element.is(reg_agree)){
               error.appendTo($('#reg_agree_e'));
            }
         }
   });

   $.validator.addMethod("pwcheck", function(value) {
         return /^[A-Za-z0-9\d\=\!\-\@\.\_\*\%\@\#\$\&\^\(\)\+\|\{\}\:\;\?\<\>\,\/]*$/.test(value) // consists of only these
                     && /[a-z]/.test(value) // has a lowercase letter
                     && /\d/.test(value) // has a digit
                     && /[\!@#\$%\^\&\*\(\)_\+=\-\.\?\<\>\{\}\[\]\:\;]/.test(value) // has a special character letter
   });
});