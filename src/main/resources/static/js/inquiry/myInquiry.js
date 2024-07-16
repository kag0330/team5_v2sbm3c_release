document.addEventListener("DOMContentLoaded", function() {
  $.noConflict();
  var zoom = document.querySelectorAll('.zoom');
  zoom.forEach(function(button) {
    button.addEventListener('click', function() {
      var tr = this.closest('tr');
      var content = tr.nextElementSibling;

      content.classList.toggle('contents');
      if (button.innerText === "+") {
        button.innerText = "-";
      } else {
        button.innerText = "+";
      }

    });
  });

});