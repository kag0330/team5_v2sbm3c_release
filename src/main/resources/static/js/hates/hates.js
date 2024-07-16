document.addEventListener("DOMContentLoaded", function() {
  var hates = document.querySelectorAll(".hates");
  hates.forEach(function(hate) {
    hate.addEventListener('click', function(event) {
      event.preventDefault();

      var review = hate.closest('.review');
      var reviewno = review.querySelector(".reviewno").value;
      var hates_count = review.querySelector('.hates_count');

      fetch('/hates/hates', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          reviewno: reviewno,
          hated: hates_count.classList.contains('hated')
        })
      })
        .then(response => response.json())
        .then(data => {
          hates_count.innerText = data.hates_count;
          if (data.fail === 'login') {
            alert('로그인 후 이용해주세요.');
          } else if (data.success === 'increase') {
            hates_count.classList.add('hated');
          } else if (data.success === 'decrease') {
            hates_count.classList.remove('hated');
          }
        })
        .catch(error => console.error('Error:', error));

    });
  });

});