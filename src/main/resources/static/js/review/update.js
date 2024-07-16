document.addEventListener("DOMContentLoaded", function() {
  var review_update = document.querySelectorAll(".review_update");
  var myno = document.getElementById("mymemberno").getAttribute("data-mymemberno");
  var shoesno = document.getElementById("shoesno").getAttribute("data-shoesno");
  var stars = document.querySelectorAll('.stars');
  var update_star = document.querySelectorAll('.update_star');
  var update_rating = document.getElementById('update_rating');
  update_rating.textContent = "5.0";
  reviewStars(parseFloat("5.0"));

  update_star.forEach(update_star => {
    update_star.addEventListener('click', function() {
      var value = this.getAttribute('update-data-value');
      update_rating.textContent = value;
      reviewStars(update_rating.textContent);
    });
  });

  function reviewStars(value) {
    update_star.forEach(update_stars => {
      update_stars.classList.remove('selected');
      if (parseFloat(update_stars.getAttribute('update-data-value')) <= value) {
        update_stars.classList.add('selected');
      }
    });
  }

  review_update.forEach(function(link) {
    link.addEventListener("click", function(event) {
      event.preventDefault();

      var review = link.closest('.review');
      var updateForm = review.querySelector("#updateForm");
      var updateReviewContents = review.querySelector("#updateReviewContents");
      var updateReview = review.querySelector("#updateReview");
      var cancelReview = review.querySelector("#cancelReview");

      updateForm.style.display = "block";
      updateReviewContents.value = "";

      // 기존에 등록된 이벤트 리스너를 제거
      if (updateReview.clickHandler) {
        updateReview.removeEventListener("click", updateReview.clickHandler);
      }
      if (cancelReview.clickHandler) {
        cancelReview.removeEventListener("click", cancelReview.clickHandler);
      }

      // 새로운 이벤트 리스너를 등록
      updateReview.clickHandler = function() {
        if (confirm("후기를 수정하시겠습니까?")) {
          var reviewno = review.querySelector(".reviewno").value;
          var contents = updateReviewContents.value;

          fetch('/review/update', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              reviewno: reviewno,
              contents: contents,
              rating: update_rating.textContent
            })
          })
          .then(response => response.json())
          .then(data => {
            if (data.success) {
              alert("후기가 수정되었습니다.");
              window.location.href = "/shoes/" + shoesno;
            } else {
              alert("후기 수정에 실패하였습니다.");
            }
          })
          .catch(error => console.error('Error:', error));
        }
      };

      cancelReview.clickHandler = function() {
        updateForm.style.display = "none";
      };

      updateReview.addEventListener("click", updateReview.clickHandler);
      cancelReview.addEventListener("click", cancelReview.clickHandler);
    });
  });
});
