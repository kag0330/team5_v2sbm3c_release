// dropdown.js



document.addEventListener("DOMContentLoaded", function() {
  var dropdownTrigger1 = document.getElementById("navbarDropdown1");
  var dropdownTrigger2 = document.getElementById("navbarDropdown2");
  var dropdownTrigger3 = document.getElementById("navbarDropdown3");
  var dropdownTrigger4 = document.getElementById("navbarDropdown4");
  var dropdownTrigger5 = document.getElementById("navbarDropdown5");
  var dropdownTrigger6 = document.getElementById("navbarDropdown6");
  var dropdownMenu1 = dropdownTrigger1.nextElementSibling;
  var dropdownMenu2 = dropdownTrigger2.nextElementSibling;
  var dropdownMenu3 = dropdownTrigger3.nextElementSibling;
  var dropdownMenu4 = dropdownTrigger4.nextElementSibling;
  var dropdownMenu5 = dropdownTrigger5.nextElementSibling;
  var dropdownMenu6 = dropdownTrigger6.nextElementSibling;


  //---------------------------------------------------------------------

  dropdownTrigger1.addEventListener("mouseenter", function() {
    dropdownMenu1.classList.add("show");
  });

  dropdownTrigger1.addEventListener("mouseleave", function() {
    dropdownMenu1.classList.remove("show");
  });

  dropdownMenu1.addEventListener("mouseenter", function() {
    dropdownMenu1.classList.add("show");
  });

  dropdownMenu1.addEventListener("mouseleave", function() {
    dropdownMenu1.classList.remove("show");
  });
  //---------------------------------------------------------------------
  dropdownTrigger2.addEventListener("mouseenter", function() {
    dropdownMenu2.classList.add("show");
  });

  dropdownTrigger2.addEventListener("mouseleave", function() {
    dropdownMenu2.classList.remove("show");
  });

  dropdownMenu2.addEventListener("mouseenter", function() {
    dropdownMenu2.classList.add("show");
  });

  dropdownMenu2.addEventListener("mouseleave", function() {
    dropdownMenu2.classList.remove("show");
  });
  //---------------------------------------------------------------------
  dropdownTrigger3.addEventListener("mouseenter", function() {
    dropdownMenu3.classList.add("show");
  });

  dropdownTrigger3.addEventListener("mouseleave", function() {
    dropdownMenu3.classList.remove("show");
  });

  dropdownMenu3.addEventListener("mouseenter", function() {
    dropdownMenu3.classList.add("show");
  });

  dropdownMenu3.addEventListener("mouseleave", function() {
    dropdownMenu3.classList.remove("show");
  });

  //---------------------------------------------------------------------
  dropdownTrigger4.addEventListener("mouseenter", function() {
    dropdownMenu4.classList.add("show");
  });

  dropdownTrigger4.addEventListener("mouseleave", function() {
    dropdownMenu4.classList.remove("show");
  });

  dropdownMenu4.addEventListener("mouseenter", function() {
    dropdownMenu4.classList.add("show");
  });

  dropdownMenu4.addEventListener("mouseleave", function() {
    dropdownMenu4.classList.remove("show");
  });
  //---------------------------------------------------------------------
  dropdownTrigger5.addEventListener("mouseenter", function() {
    dropdownMenu5.classList.add("show");
  });

  dropdownTrigger5.addEventListener("mouseleave", function() {
    dropdownMenu5.classList.remove("show");
  });

  dropdownMenu5.addEventListener("mouseenter", function() {
    dropdownMenu5.classList.add("show");
  });

  dropdownMenu5.addEventListener("mouseleave", function() {
    dropdownMenu5.classList.remove("show");
  });
  //---------------------------------------------------------------------
  dropdownTrigger6.addEventListener("mouseenter", function() {
    dropdownMenu6.classList.add("show");
  });

  dropdownTrigger6.addEventListener("mouseleave", function() {
    dropdownMenu6.classList.remove("show");
  });

  dropdownMenu6.addEventListener("mouseenter", function() {
    dropdownMenu6.classList.add("show");
  });

  dropdownMenu6.addEventListener("mouseleave", function() {
    dropdownMenu6.classList.remove("show");
  });
  //---------------------------------------------------------------------

  //---------------------------------------------------------------------
  
});
