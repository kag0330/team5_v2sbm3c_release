document.addEventListener("DOMContentLoaded", function() {
  var paymentinquiry = document.querySelectorAll(".paymentinquiry");
  var popup = document.getElementById("paymentpopup");
  var closebtn = document.querySelector(".payment-close-btn");
  var shoesname = document.getElementById("shoesname");
  var paymentcreateInquiry = document.getElementById("paymentcreateInquiry");
  var payment_details_no = '';
  paymentinquiry.forEach(function(inquiry) {
    inquiry.addEventListener("click", function(event) {
      event.preventDefault();

      var shoestitle = inquiry.closest("tbody").querySelector(".shoestitle").innerHTML;
      payment_details_no = inquiry.closest("tbody").querySelector(".payment_details_no").innerHTML;
      shoesname.textContent = '문의 신발 : ' + shoestitle;

      popup.style.display = "block"; // 팝업 표시
    });
  });

  paymentcreateInquiry.addEventListener("click", function(event) {
    event.preventDefault();
    var paymentinquiryTitle = document.getElementById("paymentinquiryTitle").value;
    var paymentinquiryContents = document.getElementById("paymentinquiryContents").value;

    if (paymentinquiryTitle.trim() === '' || paymentinquiryContents === '') {
      alert("문의 제목, 내용을 입력해주세요.");
    } else {
      fetch('/inquiry/payment', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          number: payment_details_no,
          title: paymentinquiryTitle,
          contents: paymentinquiryContents
        })
      })
        .then(response => response.json())
        .then(data => {
          if (data.nologin) {
            alert('로그인 후 이용해주세요.');
          } else if (data.success) {
            alert('문의를 작성했습니다.');
            popup.style.display = "none";
          } else {
            alert('문의에 실패했습니다.');
          }

        })
        .catch(error => console.error('Error:', error));
    }

  });

  closebtn.addEventListener("click", function() {
    popup.style.display = "none";
  });
});