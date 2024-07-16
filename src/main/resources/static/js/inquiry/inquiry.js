document.addEventListener("DOMContentLoaded", function() {
  var inquiry = document.querySelector(".inquiry");
  var popup = document.getElementById("popup");
  var closebtn = document.querySelector(".close-btn");
  var createInquiry = document.getElementById("createInquiry");
  var inquiryTitle = document.getElementById("inquiryTitle");
  var inquiryContents = document.getElementById("inquiryContents");
  var selectType = document.getElementById("inquiryType");
  var shoesSelect = document.getElementById('shoes_select');
  var paymentSelect = document.getElementById('payment_select');
  var shoesText = document.getElementById('shoes_text');
  var paymentText = document.getElementById('payment_text');
  var selectShoes = document.getElementById('selectShoes');
  inquiry.addEventListener("click", function(event) {
    event.preventDefault();
    popup.style.display = "block";
  });

  closebtn.addEventListener("click", function() {
    popup.style.display = "none";
    selectType.value = '';
    shoesText.value = '';
    paymentText.value = '';
    shoesSelect.style.display = 'none';
    paymentSelect.style.display = 'none';
  });

  selectType.addEventListener('change', function() {
    if (this.value === 'shoes') {
      shoesSelect.style.display = 'block';
      paymentText.value = '';
      paymentSelect.style.display = 'none';
    } else if (this.value === 'payment') {
      paymentSelect.style.display = 'block';
      shoesText.value = '';
      shoesSelect.style.display = 'none';
    } else {
      shoesText.value = '';
      paymentText.value = '';
      shoesSelect.style.display = 'none';
      paymentSelect.style.display = 'none';
    }
  });

  // 검색 결과 창 변수
  var search_popup = document.getElementById("search-popup");
  var search_closebtn = document.querySelector(".search-close-btn");
  var shoes_result = document.getElementById("shoes-result");
  var payment_result = document.getElementById("payment-result");
  var shoesTable = document.querySelector("#shoes-table tbody");
  var paymentTable = document.querySelector("#payment-table tbody");
  var shoesno = document.getElementById("shoesno");
  var paymentdetailsno = document.getElementById("payment_details_no");
  selectShoes.addEventListener("click", function(event) {
    event.preventDefault();
    if (selectShoes.textContent === '다시 검색') {
      selectShoes.textContent = '검색';
      shoesText.value = '';
      shoesText.readOnly = false;
      return;
    } else if (shoesText.value.trim() === '') {
      alert('문의하고 싶은 신발을 입력해주세요.');
    } else {
      fetch('/inquiry/select', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          select: selectType.value,
          word: shoesText.value
        })
      })
        .then(response => response.json())
        .then(data => {
          if (data.nologin) {
            alert('로그인 후 이용해주세요.');
          } else if (data.success) {
            shoesTable.innerHTML = '';

            data.list.forEach(function(shoe) {
              var title = shoe.title;
              var brand = shoe.brand;

              var tr = document.createElement('tr');
              var tdTitle = document.createElement('td');
              tdTitle.textContent = title;
              tdTitle.style.cursor = 'pointer';
              var tdBrand = document.createElement('td');
              tdBrand.textContent = brand;

              tdTitle.addEventListener('click', function() {
                if (confirm(title + ' 신발을 문의하시겠습니까?')) {
                  selectShoes.textContent = "다시 검색";
                  shoesText.value = title;
                  shoesno.value = shoe.shoesno;
                  shoesText.readOnly = true;
                  search_popup.style.display = "none";

                }
              });
              tr.appendChild(tdTitle);
              tr.appendChild(tdBrand);
              shoesTable.appendChild(tr);
            });
            search_popup.style.display = "block";
            shoes_result.style.display = "block";
            payment_result.style.display = "none";
          } else {
            alert('검색 결과가 없습니다.');
          }
        })
        .catch(error => console.error('Error:', error));
    }

  });

  paymentText.addEventListener("click", function(event) {
    event.preventDefault();
    fetch('/inquiry/select', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        select: selectType.value
      })
    })
      .then(response => response.json())
      .then(data => {
        if (data.nologin) {
          alert('로그인 후 이용해주세요.');
        } else if (data.success) {
          paymentTable.innerHTML = '';

          data.list.forEach(function(payment) {
            var payment_details_no = payment.paymentDetailsVO.payment_details_no;
            var payment_amount = payment.paymentDetailsVO.payment_amount;
            var sizes = payment.optionVO.sizes;
            var color = payment.optionVO.color;
            var title = payment.shoesVO.title;

            var tr = document.createElement('tr');

            var tdno = document.createElement('td');
            tdno.textContent = payment_details_no;

            var tdShoes = document.createElement('td');
            tdShoes.textContent = title + ' | ' + sizes + ' | ' + color + ' | ' + payment_amount + '개';
            tdShoes.style.cursor = 'pointer';

            tdShoes.addEventListener('click', function() {
              if (confirm('선택한 주문을 문의하시겠습니까?\n주문번호 : ' + payment_details_no)) {
                paymentText.value = title + ' | 주문번호 : ' + payment_details_no;
                paymentdetailsno.value = payment_details_no;
                search_popup.style.display = "none";
              }
            });

            tr.appendChild(tdno);
            tr.appendChild(tdShoes);
            paymentTable.appendChild(tr);
          });
          search_popup.style.display = "block";
          shoes_result.style.display = "none";
          payment_result.style.display = "block";
        } else {
          alert('검색 결과가 없습니다.');
        }
      })
      .catch(error => console.error('Error:', error));

  });

  search_closebtn.addEventListener("click", function() {
    search_popup.style.display = "none";
  });

  createInquiry.addEventListener("click", function(event) {
    event.preventDefault();
    var typeno = selectType.options[selectType.selectedIndex].value;
    if (typeno.trim() === '' || inquiryTitle.value.trim() === '' || inquiryContents.value.trim() === '') {
      alert("문의 유형, 제목, 내용을 입력해주세요.");
      return;
    } else {
      var select = selectType.value;
      var url = '/inquiry/' + select;
      var number = '';
      if (select === 'shoes') {
        if (shoesText.readOnly) {
          number = shoesno.value;
        } else {
          alert('문의하고자 하는 신발을 등록해주세요.');
          return;
        }
      } else if (select === 'payment') {
        if (paymentText.value.trim() !== '') {
          number = paymentdetailsno.value;
        } else {
          alert('문의하고자 하는 주문을 등록해주세요.');
          return;
        }
      }

      fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          number: number,
          title: inquiryTitle.value,
          contents: inquiryContents.value
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

});