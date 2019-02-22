(function ($) {

  "use strict";
    $(document).ready(function () {
    



    // Parallax Effect
    parallaxen();




    // jQuery to collapse the navbar on scroll
    function collapseNavbar() {
    if ($(".navbar").offset().top > 50) {
        $(".navbar-fixed-top").addClass("top-nav-collapse");
    } else {
        $(".navbar-fixed-top").removeClass("top-nav-collapse");
    }
            if ($(this).scrollTop() > 800) {
            $("#back-to-top").stop().animate({ opacity: '1' }, 150);
        } else {
            $("#back-to-top").stop().animate({ opacity: '0' }, 150);
        }
    }
    $(window).scroll(collapseNavbar);
    



    // Closes the Responsive Menu on Menu Item Click
    $(document).on('click','.navbar-collapse.in',function(e) {
    if( $(e.target).is('a') && $(e.target).attr('class') != 'dropdown-toggle' ) {
        $(this).collapse('hide');
    }
    });




    


    // Smooth Scroll to Anchor c) 2016 Chris Ferdinandi | MIT License | http://github.com/cferdinandi/smooth-scroll */
    smoothScroll.init({
    selector: '[data-scroll]', // Selector for links (must be a class, ID, data attribute, or element tag)
    selectorHeader: null, // Selector for fixed headers (must be a valid CSS selector) [optional]
    speed: 800, // Integer. How fast to complete the scroll in milliseconds
    easing: 'easeInOutCubic', // Easing pattern to use
    offset: 0, // Integer. How far to offset the scrolling anchor location in pixels
    callback: function ( anchor, toggle ) {} // Function to run after scrolling
    });




    // ScrollReveal
    window.sr = ScrollReveal();
    sr.reveal('.fadeHero', { duration: 1500, delay: 200 } )
    sr.reveal('.fadeIn', { duration: 1500, viewFactor: 0.6} )
    sr.reveal('.fadeLeft', { duration: 500, origin: 'left', viewFactor: 0.7,}, 200)
    sr.reveal('.fadeLeft2', { duration: 1500, origin: 'left', viewFactor: 0.7,}, 200)



        
   /*
 * ----------------------------------------------------------------------------------------
         *  COUNTER UP JS
         * ----------------------------------------------------------------------------------------
         */

        $('.counter-num').counterUp();

    });
})(jQuery);
