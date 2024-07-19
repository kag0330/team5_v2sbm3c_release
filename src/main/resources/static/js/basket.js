// 수량 감소 함수
function decreaseQuantity(button) {
  var basketElement = button.closest('.basket');
  var quantityElement = basketElement.querySelector('.quantity');
  var basketno = basketElement.querySelector('.basketno').value;
  var price = parseInt(basketElement.querySelector('.price').value);
  var quantity = parseInt(quantityElement.innerText);
  if (quantity > 1) {
    update(basketno, quantity - 1, price, quantityElement, basketElement.querySelector('.total'));
  }
}

// 수량 증가 함수
function increaseQuantity(button) {
  var basketElement = button.closest('.basket');
  var quantityElement = basketElement.querySelector('.quantity');
  var basketno = basketElement.querySelector('.basketno').value;
  var price = parseInt(basketElement.querySelector('.price').value);
  var quantity = parseInt(quantityElement.innerText);
  update(basketno, quantity + 1, price, quantityElement, basketElement.querySelector('.total'));
}

// 서버에 POST 요청을 보내어 amount 값을 업데이트하는 함수
function update(basketno, amount, price, quantityElement, totalElement) {
  fetch('/basket/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      basketno: parseInt(basketno),
      amount: parseInt(amount)
    })
  })
    .then(response => response.json())
    .then(response => {
      if (response.success) {
        quantityElement.innerText = amount;
        totalElement.innerText = price * amount;
      } else {
        alert('수량 변경에 실패했습니다.');
        console.error('수량 변경에 실패했습니다.');
      }
    })
    .catch(error => console.error('Error:', error));
}

// DOMContentLoaded 이벤트 핸들러
document.addEventListener('DOMContentLoaded', function() {
  // 수량 증가 및 감소 버튼들을 모두 선택합니다.
  var increaseButtons = document.querySelectorAll('.quantity-control .quantity-btn.increase');
  var decreaseButtons = document.querySelectorAll('.quantity-control .quantity-btn.decrease');

  increaseButtons.forEach(function(button) {
    button.addEventListener('click', function() {
      increaseQuantity(button);
    });
  });

  decreaseButtons.forEach(function(button) {
    button.addEventListener('click', function() {
      decreaseQuantity(button);
    });
  });
});


document.addEventListener('DOMContentLoaded', function() {
  var cartButton = document.querySelector('.detail_btnl');

  // 장바구니 추가 버튼 이벤트 리스너
  if (cartButton) {
    cartButton.addEventListener('click', function() {

      if (sizes === '' || color === '' || (amountInput.value == null || amountInput.value == 0)) {
        alert('옵션을 선택해주세요.');
        return false;
      }
      
      if(memberno == null){
        alert('로그인 해주세요.');
        return false;
      }
      // 장바구니 추가 요청 보내기
      fetch('/basket/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          shoesno: shoesno,
          sizes: parseInt(sizes),
          color: color,
          amount: parseInt(amountInput.value)
        })
      })
        .then(response => response.json())
        .then(response => {
          if (response.success) {
            alert('장바구니에 담았습니다.');
          } else {
            alert(response.message);
            location.reload(); // 페이지 새로고침
          }
        })
        .catch(error => console.error('Error:', error));
    });
  }
});


document.addEventListener('DOMContentLoaded', function() {
  var cancelBasketButtons = document.querySelectorAll('.basket_btn');

  // 장바구니 취소 버튼 클릭 시
  cancelBasketButtons.forEach(function(button) {
    button.addEventListener('click', function() {
      var basketElement = button.closest('.basket'); // '.basket-list'가 아닌 '.basket'을 사용
      var basketno = basketElement.querySelector('.basketno').value;

      if (!basketno) {
        alert('장바구니 항목을 찾을 수 없습니다.');
        return;
      }

      cancelBasket(basketno, basketElement);
    });
  });

  // 장바구니 상품 삭제 요청 함수
  function cancelBasket(basketno, basketElement) {
    fetch('/basket/delete', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        basketno: parseInt(basketno) // 삭제할 바스켓 번호 전달
      })
    })
      .then(response => response.json())
      .then(response => {
        if (response.success) {
          alert('장바구니에서 제품을 삭제했습니다.');
          console.log('장바구니에서 제품을 성공적으로 삭제했습니다.');
          basketElement.remove(); // 화면에서 해당 장바구니 아이템 제거
        } else {
          console.error('장바구니 제품 삭제에 실패했습니다.');
        }
      })
      .catch(error => console.error('Error:', error));
  }
});
