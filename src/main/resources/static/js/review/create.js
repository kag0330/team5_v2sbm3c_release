document.addEventListener("DOMContentLoaded", function() {
  var reviewContents = document.getElementById("reviewContents");
  var createReview = document.getElementById("createReview");
  var nickname = document.getElementById("nickname").getAttribute("data-nickname");
  var stars = document.querySelectorAll('.star');
  var rating = document.getElementById('rating');
  var myid = document.getElementById("mymemberno");
  var myno = myid ? myid.getAttribute("data-mymemberno") : null;
  var shoesno = document.getElementById("shoesno").getAttribute("data-shoesno");
  rating.textContent = "5.0";
  reviewStars(parseFloat("5.0"));

  if (!myno) {
    reviewContents.value = "로그인 후 이용해주세요";
    reviewContents.disabled = true;
  }

  stars.forEach(star => {
    star.addEventListener('click', function() {
      var value = this.getAttribute('data-value');
      rating.textContent = value;
      reviewStars(value);
    });
  });

  function reviewStars(value) {
    stars.forEach(star => {
      star.classList.remove('selected');

      if (star.getAttribute('data-value') <= value) {
        star.classList.add('selected');
      }
    });
  }

  createReview.addEventListener("click", function(event) {
    event.preventDefault();
    var review = reviewContents.value;

    if (!nickname) {
      alert('로그인 후 이용해주세요.');
      return;
    }

    if (review.trim() === '') {
      alert('후기 내용을 입력해주세요.');
      return;
    }

    var write = false;
    var reviewList = document.querySelectorAll('.review');
    reviewList.forEach(element => {
      var memberno = element.querySelector('.memberno');
      if(!memberno) {
        return;
      }else if (parseInt(memberno.value.trim()) === parseInt(myno)) {
        write = true;
      }
      
    });

    if (write) {
      alert("이미 후기를 작성하셨습니다.");
      return;
    }

    fetch('/review/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        contents: review,
        rating: rating.textContent,
        shoesno: shoesno
      })
    })
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          alert('답글이 작성되었습니다.');
          window.location.href = "/shoes/" + shoesno;
        } else {
          alert('답글 작성에 실패하였습니다.');
        }
      })
      .catch(error => console.error('Error:', error));

  });

});

