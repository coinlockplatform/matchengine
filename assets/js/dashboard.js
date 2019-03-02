


function updateBalances(){
   var post_url = "/api/v1/balances";
   var balanceUpdateTime = 600000; // Set update timer to 10 minutes
   $.ajax({
      url: post_url,
      timeout: 5000,
      cache: false,
      method: "GET",
      dataType: 'json',
      success: function(json) {
         if(json.status == "success"){
            var balances = json.data.assets
            var total_balance = 0;
            $('#balances_tbl').html('');
            for (var asset in balances) {
               balance_data = '<tr><td><div class="d-flex align-items-center"><div class="mr-2"><a href="#"><img src="/global_assets/images/crypto-logos/' + balances[asset].name + 
                              '.png" class="rounded-circle" width="32" height="32" alt=""></a></div><div><a href="#" class="text-default font-weight-semibold">'+ balances[asset].name + 
                              '</a><div class="text-muted font-size-sm">' + balances[asset].asset +
                              '</div></div></div></td><td><span class="text-muted font-size-sm">' + balances[asset].holdings.toLocaleString('en', {maximumSignificantDigits : 12}) + 
                              '</span><div class="text-muted font-size-sm"><i class="icon-coin-dollar font-size-sm mr-1"></i>'+ balances[asset].usd_value.toLocaleString('en') +
                              '</div></td><td><h6 class="text-secondary font-size-sm mb-0">' + balances[asset].pending.toLocaleString('en', {maximumSignificantDigits : 12}) +
                              '</h6></td></tr>';
               $('#balances_tbl').append(balance_data);
               total_balance = total_balance + balances[asset].usd_value;
            }
            $('#total_balance').html(numberWithCommas(total_balance.toFixed(2)));
         }else{
               console.log('could not obtain balences: ' + json.message);
               balanceUpdateTime = 10000; // set the update timmer to ry again in 10 seconds since we failed
         }
      }
   });
   setTimeout(updateBalances, balanceUpdateTime); // Start timer
}


$( document ).ready(function() {
   updateBalances(); // Get the balances

   /* Lock Left guage */
   var lockLeft_el = document.getElementById('lockLeft');
   var lockLeft_chart = echarts.init(lockLeft_el);
   var option = {
      tooltip : {
            formatter: "{a} <br/>{b} : {c}%"
      },
      toolbox: {
            feature: {
               restore: {},
               saveAsImage: {}
            }
      },
      series: [
            {
               name: 'LOCLEFT',
               type: 'gauge',
               detail: {formatter:'{value}%'},
               data: [{value: 10, name: 'LOCK left at 0.58'}]
            }
      ]
   };
   lockLeft_chart.setOption(option);
   /* End Lock Left guage */






   $("#position_form").submit(function(e){
      e.preventDefault(e);
      if ($("#position_form").valid()) {

         var notyConfirm = new Noty({
            text: '<h6 class="mb-3">Please confirm</h6><label>Are you sure you want to enter this position?</label> ',
            timeout: false,
            modal: true,
            layout: 'center',
            theme: 'alert alert-primary alert-styled-left p-0 bg-white',
            closeWith: 'button',
            type: 'confirm',
            buttons: [
               Noty.button('No', 'btn btn-link', function () {
                  notyConfirm.close();
               }),

               Noty.button('Yes <i class="icon-paperplane ml-2"></i>', 'btn bg-success ml-1', function () {

                  var post_url = "/api/v1/position/create";
                  if($('[id="position_selector"]').is(':checked')){
                     var position_type = 'give';
                  }else{
                     var position_type = 'get';
                  }
                  var form_data = $("#position_form").serializeArray();
                  form_data.push({name: "type", value: position_type});
                  form_data.push({name: "autotrade", value: 0});

                  $.ajax({
                     url: post_url,
                     timeout: 5000,
                     data: form_data,
                     method: "POST",
                     dataType: 'json',
                     success: function(json) {
                        if(json.status == "success" && json.data){
                           $('#position_form').trigger("reset");
                              swal({
                                 title: 'Position Entered Successfully',
                                 text: json.message,
                                 type: 'success'
                              });
                        }else{
                              console.log('error');
                              swal({
                                 title: 'Oops!',
                                 text: json.message,
                                 type: 'error'
                              });
                        }
                     }
                  });
                  notyConfirm.close(); 
               },
               {id: 'button1', 'data-status': 'ok'}
               )
            ]
         }).show();
                        
      }
   });

   $('#position_form').validate({
      rules: {
            amount: {
               required: true,
               number: true,
               rangelength:[1,16]
            }
         },
         messages: {
            amount: {
               required: "Please enter an amount",
               number: "Please enter a valid amount. ",
               rangelength: "Please enter an amount between 1 and 16 digits"
            }
         },
         errorPlacement: function(error, element) {
            error.appendTo($('#position_e'));
         }
   });

   // Prevent non digit keys from being entered into the amount field
   $("#position_amount").keyup(function(){
      var VAL = this.value; // Value of the key that was pressed. this keyword referes to itself, or the local scope
      amounttext = $("#position_amount")[0];//returns a HTML DOM Object. To get the same result as document.getElementById, you can access the jQuery Object and get the first element in the object
      var unwantedChars = new RegExp('[^0-9.]', 'g');

      if (unwantedChars.test(VAL)) {			// If the key pressed (VAL) matches the regular expression (unwantedChars)
         amounttext.value = amounttext.value.replace(unwantedChars,"");// To run the regular expression we use .test on the regex 
      }
      // Make sure the first character is not a 0. If it is, make sure there is a deical after it like in 0.02. It can not be 02.44, that is not a valid number
      if(amounttext.value[0]==0 && amounttext.value[1]!='.' && amounttext.value[1]!=null){
         var firstZero = new RegExp('^0');
         amounttext.value = amounttext.value.replace(firstZero,"");
      }
   });

   // Update button and text fields when trade type is selected
   $('#position_selector').on('switchChange.bootstrapSwitch', function (event, state) {
      if(!state){
         //$("#amountDesc").html("Lock in the price of your BTC by getting price protection. If BTC declines, you will gain Bitcoin so your USD value stays the same!"); 
         $("#positionbtn").html("Place Protect Order"); 
         $("#giveDesc").removeClass("active"); 
         $("#getDesc").removeClass("active"); 
         $("#autoDesc").removeClass("active"); 
         $("#giveDesc").addClass("active"); 

      }else{
         //$("#amountDesc").html("Grow your BTC faster by providing peer to peer 3X price protection of Bitcoin by using our automated match engine.");   
         $("#positionbtn").html("Place 3X Protection Order");
         $("#giveDesc").removeClass("active"); 
         $("#getDesc").removeClass("active"); 
         $("#autoDesc").removeClass("active"); 
         $("#getDesc").addClass("active"); 
      }
   }); 


});

function copyTocplb() {
   var copyText = document.getElementById("depositAddress");/* Get the text field */
   copyText.select();/* Select the text field */
   document.execCommand("copy");/* Copy the text inside the text field */
   alert("Address copied to clipboard");/* Alert the copied text */
}
// Initialize position selector switch
$(function() {
   $("[id='position_selector']").bootstrapSwitch({});
});