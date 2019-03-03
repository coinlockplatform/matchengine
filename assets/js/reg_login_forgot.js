
$(document).ready(function(){
   $('.navbar').hide(); // Hide the navigation on the login screen
   /*----------------------------------------------------
            ###############################
                     LOGIN FORM
            ###############################
   ----------------------------------------------------*/
   $("#login-form").submit(function(e){
      e.preventDefault(e);
      if ($("#login-form").valid()) {
         //var post_url = $("#login-form").attr("action"); //get form action url
         var post_url = "/api/v1/auth";
         var form_data = $("#login-form").serializeArray();
         form_data.push({name: "type", value: 'login'});
         $.ajax({
            url: post_url,
            timeout: 5000,
            data: form_data,
            method: "POST",
            dataType: 'json',
            success: function(json) {
               if(json.status == "success" && json.data){
                     swal({
                        title: 'Login Success',
                        text: 'You will be forwarded to your dashboard now',
                        type: 'success'
                     });
                     
                     setTimeout(function(){
                        $(location).attr('href', '/dashboard.jsp');
                     }, 1000);

               }else{
                     swal({
                        title: 'Oops!',
                        text: json.message,
                        type: 'error'
                     });
               }
            }
         });
      }
   });
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


   /*----------------------------------------------------
            ###############################
                     RESET PASSWORD FORM
            ###############################
   ----------------------------------------------------*/
   $("#forgotpass-form").submit(function(e){
      e.preventDefault(e);
      if ($("#forgotpass-form").valid()) {
         //var post_url = $("#forgotpass-form").attr("action"); //get form action url
         var post_url = "/api/v1/auth";
         var form_data = $("#forgotpass-form").serializeArray();
         form_data.push({name: "action", value: 'reset'});
         $.ajax({
            url: post_url,
            timeout: 10000,
            data: form_data,
            method: "POST",
            dataType: 'json',
            success: function(json) {
               if(json.status == "success" && json.data){
                     swal({
                        title: 'Ok!',
                        text: 'If ' + json.data.email + ' exists in our records, you will receive password reset instructions',
                        type: 'success'
                     });
               }else{
                     swal({
                        title: 'Oops!',
                        text: json.message,
                        type: 'error'
                     });
               }
            }
         });
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






   /*----------------------------------------------------
            ###############################
                     REGISTER FORM
            ###############################
   ----------------------------------------------------*/
   $("#register-form").submit(function(e){
      e.preventDefault(e);
      if ($("#register-form").valid()) {
         //var post_url = $("#register-form").attr("action"); //get form action url
         var post_url = "/api/v1/registration";
         var form_data = $("#register-form").serialize();
         $.ajax({
            url: post_url,
            timeout: 10000,
            data: form_data,
            method: "POST",
            dataType: 'json',
            success: function(json) {
               if(json.status == "success" && json.data){
                     swal({
                        title: 'Please Verify your email',
                        text: 'A confirmation link has been sent to your email address',
                        type: 'success'
                     });
               }else{
                     swal({
                        title: 'Oops!',
                        text: json.message,
                        type: 'error'
                     });
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





/*----------------------------------------------------
         ###############################
                  SWITCH PANELS
         ###############################
----------------------------------------------------*/
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
   var getUrlParameter = function getUrlParameter(sParam) {
      var sPageURL = window.location.search.substring(1),
         sURLVariables = sPageURL.split('&'),
         sParameterName,
         i;
   
      for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');
   
            if (sParameterName[0] === sParam) {
               return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
      }
      return 0;
   };

   var previous = parseInt(getUrlParameter('previous')); // previous is the number of the previous image. Used when user wants to see a new image and we dont want to show them the same one again
   var bi = parseInt(getUrlParameter('bg'));
   if(!bi){
      while(bi == previous || bi==0){ // If we are viewing another bg image, dont show the same one they already were looking at which will be sent as previous get variable
          var bi = Math.floor(Math.random() * 23) + 1;
      }
   }
   console.log('bg image '+bi);
   var infobg=''; 
   switch(bi){
      case 1: var logo='logo-white-strokeblack.png'; var opacity='opacityBlack-darkest'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infohtml='Monaco'; break;
      case 2: var logo='logo-white.png'; var opacity='opacitySlight'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infohtml='Cappadocia, Turkey'; break;
      case 3: var logo='logo-black.png'; var opacity='opacitySlight'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infohtml='California, USA'; break;
      case 4: var logo='logo-white.png'; var opacity='opacityWhite'; var cardtxt='#fff'; var linktxt='#234DD1'; var infotxt='#fff'; var infobg='rgba(0, 0, 0, 0.4)'; var infohtml='Masaya, Nicaragua'; break;
      case 5: var logo='logo-white.png'; var opacity='opacityWhite'; var cardtxt='#fff'; var linktxt='#234DD1'; var infotxt='#fff'; var infohtml='Hong Kong'; break;
      case 6: var logo='logo-black.png'; var opacity='opacityGrey'; var cardtxt='#000'; var linktxt='#234DD1'; var infotxt='#fff'; var infohtml='California, USA'; break;
      case 7: var logo='logo-white.png'; var opacity='opacityBlack-dark'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infobg='rgba(0, 0, 0, 0.4)'; var infohtml='Egypt'; break;
      case 8: var logo='logo-white.png'; var opacity='opacitySlight'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infohtml='London, England'; break;
      case 9: var logo='logo-black.png'; var opacity='opacitySlight'; var cardtxt='#000'; var linktxt='#fff'; var infotxt='#fff'; var infobg='rgba(0, 0, 0, 0.5)'; var infohtml='Oregon, USA'; break;
      case 10: var logo='logo-white.png'; var opacity='opacityWhite'; var cardtxt='#000'; var linktxt='#000'; var infotxt='#fff'; var infobg='rgba(0, 0, 0, 0.5)'; var infohtml='Sorrento, Italy'; break;
      case 11: var logo='logo-white.png'; var opacity='opacityWhite'; var cardtxt='#000'; var linktxt='#234DD1'; var infotxt='#fff'; var infohtml='Chicago, USA'; break;
      case 12: var logo='logo-slateblue.png'; var opacity='opacityWhite'; var cardtxt='#33558C'; var linktxt='#234DD1'; var infotxt='#fff'; var infobg='rgba(0, 0, 0, 0.3)'; var infohtml='HCMC, Vietnam'; break;
      case 13: var logo='logo-white.png'; var opacity='opacityWhite'; var cardtxt='#fff'; var linktxt='#000'; var infotxt='#fff'; var infohtml='Dubrovnik, Croatia'; break;
      case 14: var logo='logo-white-strokeblack.png'; var opacity='opacitySlight'; var cardtxt='#000'; var linktxt='#fff'; var infotxt='#fff'; var infohtml='Palawan, Philippines'; break;
      case 15: var logo='logo-white-strokeblack.png'; var opacity='opacityBlack-dark'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infobg='rgba(0, 0, 0, 0.3)'; var infohtml='Palawan, Philippines'; break;
      case 16: var logo='logo-black-strokewhite.png'; var opacity='opacityBlack'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infobg='rgba(0, 0, 0, 0.5)'; var infohtml='Ushuaia, Argentina'; break;
      case 17: var logo='logo-black-strokewhite.png'; var opacity='opacityWhite'; var cardtxt='#000'; var linktxt='#000'; var infotxt='#fff'; var infohtml='Nepal'; break;
      case 18: var logo='logo-black.png'; var opacity='opacitySlight'; var cardtxt='#fff'; var linktxt='#234DD1'; var infotxt='#fff'; var infohtml='Nepal'; break;
      case 19: var logo='logo-white.png'; var opacity='opacityBlack-dark'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infohtml='New York, USA'; break;
      case 21: var logo='logo-white.png'; var opacity='opacitySlight'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infohtml='Easter Island, Chile'; break;
      case 20: var logo='logo-white-strokeblack.png'; var opacity='opacityWhite'; var cardtxt='#000'; var linktxt='#234DD1'; var infotxt='#fff'; var infohtml='Paris, France'; break;
      case 22: var logo='logo-black.png'; var opacity='opacityBlack-dark'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infobg='rgba(0, 0, 0, 0.3)'; var infohtml='Kentucky, USA'; break;
      case 23: var logo='logo-white.png'; var opacity='opacityBlack-dark'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infohtml='Easter Island, Chile'; break;
      case 24: var logo='logo-black-strokewhite.png'; var opacity='opacityBlack-dark'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infohtml='Wyoming, USA'; break;
      default: var logo='logo-white.png'; var opacity='opacityWhite'; var cardtxt='#fff'; var linktxt='#fff'; var infotxt='#fff'; var infobg='rgba(0, 0, 0, 0.5)'; var infohtml='many countries'; break;
   }

   $("#page_content").css("background-image", "url('https://www.brentrussell.com/images/matchengine/"+bi+".jpg')");
   $("#front_logo").attr("src","/global_assets/images/logos/"+logo);
   $("#login-form-cont, #register-form-cont, #forgotpass-form-cont").addClass(opacity);
   $(".card-title, .card-body").css("color",cardtxt);
   //$(".card-title").css("textShaddow","9px 9px #f00");
   $(".card-title").css('textShadow','#555 2px 2px 1px');
   $(".card-title").css('fontSize','1.4em');

   $("a").css("color",linktxt);
   $("a").css("font-weight",'normal');
   $(".bottom-align-text, .bottom-align-text a").css("color",infotxt);
   $(".bottom-align-text").css("background-color",infobg);
   $(".bottom-align-text").html('We have users as far away as '+infohtml+'!<br /><a href=\'/?previous='+bi+'\'>See another</a>');
});