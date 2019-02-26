



$(window).on('load', function(){ 
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



   $('#tradebtn').on('click', function() {
      if($('#trade-form').valid()) {
         var notyConfirm = new Noty({
               text: '<h6 class="mb-3">Please confirm Trade</h6><label>Are you sure you want to submit this trade?</label> ',
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
                           $('#trade-form').trigger("reset");
                           $("#trade-form").ajaxSubmit({url: 'trade.jsp', type: 'post'})
                           notyConfirm.close(); 
                     },
                     {id: 'button1', 'data-status': 'ok'}
                  )
               ]
         }).show();
      }
   });


   $("#trade_amount").keyup(function(){
      var VAL = this.value; // Value of the key that was pressed. this keyword referes to itself, or the local scope
      amounttext = $("#trade_amount")[0];//returns a HTML DOM Object. To get the same result as document.getElementById, you can access the jQuery Object and get the first element in the object
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

   $("#trade-form").submit(function(e){
      e.preventDefault(e);
   }); 

   $('#trade-form').validate({
      rules: {
            amount: {
               required: true,
               number: true,
               rangelength:[1,16]
            }
         },
         messages: {
            amount: {
               required: "Please enter a trade amount",
               number: "Please enter a valid trade amount. ",
               rangelength: "Please enter a trade amount between 1 and 16 digits"
            }
         },
         errorPlacement: function(error, element) {
            error.appendTo($('#trade_e'));
         }
   });

   // Update button and text fields when trade type is selected
   $('#trade-selector').on('switchChange.bootstrapSwitch', function (event, state) {
      if(!state){
         //$("#amountDesc").html("Lock in the price of your BTC by getting price protection. If BTC declines, you will gain Bitcoin so your USD value stays the same!"); 
         $("#tradebtn").html("Place Protect Order"); 
         $("#giveDesc").removeClass("active"); 
         $("#getDesc").removeClass("active"); 
         $("#autoDesc").removeClass("active"); 
         $("#giveDesc").addClass("active"); 

      }else{
         //$("#amountDesc").html("Grow your BTC faster by providing peer to peer 3X price protection of Bitcoin by using our automated match engine.");   
         $("#tradebtn").html("Place 3X Protection Order");
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
// Initialize trade selector switch
$(function() {
   $("[name='trade-selector']").bootstrapSwitch({});
});