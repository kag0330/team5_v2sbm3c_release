document.addEventListener("DOMContentLoaded", function() {
  var likes = document.querySelectorAll(".likes");
  likes.forEach(function(like) {
    like.addEventListener('click', function(event) {
      event.preventDefault();

      var review = like.closest('.review');
      var reviewno = review.querySelector(".reviewno").value;
      var likes_count = review.querySelector('.likes_count');

      fetch('/likes/likes', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          reviewno: reviewno,
          liked: likes_count.classList.contains('liked')
        })
      })
        .then(response => response.json())
        .then(data => {
          likes_count.innerText = data.likes_count;
          if (data.fail === 'login') {
            alert('로그인 후 이용해주세요.');
          } else if (data.success === 'increase') {
            likes_count.classList.add('liked');
          } else if (data.success === 'decrease') {
            likes_count.classList.remove('liked');
          }

        })
        .catch(error => console.error('Error:', error));

    });
  });

});