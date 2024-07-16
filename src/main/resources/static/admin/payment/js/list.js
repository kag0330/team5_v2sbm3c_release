/*<![CDATA[*/
window.onload = function() {
  newPaymentListLoding();
}

function newPaymentListLoding() {
  counting();
  paymentList();
}

/* 배열에 체크박스 체크된 값 저장 */
function statussAdd() {
  const paymentStatus = [];
  const status = [];
  const csStatus = [];
  
  document.querySelectorAll('input[id^="btn-check-PS-"]:checked').forEach((checkbox) => {
    paymentStatus.push(checkbox.nextElementSibling.textContent);
  });

  document.querySelectorAll('input[id^="btn-check-S-"]:checked').forEach((checkbox) => {
    status.push(checkbox.nextElementSibling.textContent);
  });

  document.querySelectorAll('input[id^="btn-check-CSS-"]:checked').forEach((checkbox) => {
    csStatus.push(checkbox.nextElementSibling.textContent);
  });
  
  const order = document.querySelector('input[name^="btn-check-order"]:checked').value;
  return { paymentStatus, status, csStatus, order };
}

/* 체크박스 change이벤트 호출되면 동작 */
document.querySelectorAll('.btn-check').forEach((checkbox) => {
  checkbox.addEventListener('change', () => {
    counting();
    paymentList();
  });
});

function counting() {
  const { paymentStatus, status, csStatus } = statussAdd();
  
  let text = document.getElementById("countSpan");
  fetch('./count', {
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      payment_statuss: paymentStatus,
      statuss: status,
      cs_statuss: csStatus,
      word: word
    }),
  })
  .then((response) => {
    if (!response.ok) {
      throw new Error('Network response was not ok' + response.statusText);
    }
    return response.text();
  })
  .then((data) => {
    text.innerText = data;
  })
  .catch(() => {
    text.innerText = '?(?)';
  });
}

function paymentList() {
  const { paymentStatus, status, csStatus, order } = statussAdd();
  
  
  
  fetch(`./payment`, {
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      word: word,
      now_page: now_page,
      payment_statuss: paymentStatus,
      statuss: status,
      cs_statuss: csStatus,
      order: order,
      path: window.location.pathname
    }),
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok' + response.statusText);
    }
    return response.text();
  })
  .then(data => {
    document.querySelector('.paymentDiv').innerHTML = data;
  })
  .catch(error => {
    alert('리스트를 가져오는 데 실패했습니다.');
    console.error("ERROR:", error);
  });
}

function fetchList(button, memberno) {
  const contentsDiv = button.nextElementSibling;
  const { paymentStatus, status, csStatus } = statussAdd();

  if (button.classList.contains("active")) {
    // 버튼이 활성화 상태일 경우 내용을 닫습니다.
    contentsDiv.style.maxHeight = null;
    button.classList.remove("active");
  } else {
    // 버튼이 활성화 상태가 아닐 경우 AJAX 요청을 보냅니다.
    // AJAX 요청을 보내기 전에 기존 내용을 초기화합니다.
    contentsDiv.innerHTML = '';

    fetch(`./${memberno}/paymentDetails`, {
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        payment_statuss: paymentStatus,
        statuss: status,
        cs_statuss: csStatus
      }),
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok' + response.statusText);
      }
      return response.text();
    })
    .then(data => {
      contentsDiv.innerHTML = data;

      // 모든 활성화된 버튼과 내용을 축소합니다.
      document.querySelectorAll('.collapsible.active').forEach(activeButton => {
        activeButton.classList.remove('active');
        activeButton.nextElementSibling.style.maxHeight = null;
      });

      // 버튼을 활성화하고 내용을 확장합니다.
      button.classList.add("active");
      contentsDiv.style.maxHeight = contentsDiv.scrollHeight + "px";
    })
    .catch(error => {
      alert('리스트를 가져오는 데 실패했습니다.');
      console.error("ERROR:", error);
    });
  }
}

function collapseChild(element) {
  let before = document.querySelector(".activeChild");
  if (before && document.querySelector(".activeChild") != element) {
    before.nextElementSibling.style.maxHeight = null;
    before.classList.remove("activeChild");
  }
  element.classList.toggle("activeChild");

  let contents = element.nextElementSibling;
  if (contents.style.maxHeight != 0) {
    contents.style.maxHeight = null;
  } else {
    let parent = element.parentNode;
    let height = contents.scrollHeight + element.scrollHeight + parent.scrollHeight;
    parent.style.maxHeight = height + "px";
    contents.style.maxHeight = contents.scrollHeight + "px";
  }
}

function submit(btn) {
  let grandparentDiv = btn.closest('.contents').previousElementSibling;
  let parentDiv = btn.closest('.clearfix');

  let span = grandparentDiv.querySelector('.collapsible > div > div > span');
  let paymentno = span.textContent.trim().match(/\d+/)[0];

  let payment_status = parentDiv.querySelector('div:nth-child(2) > div:nth-child(1) > select[name=payment_status] option:checked').value;
  let status = parentDiv.querySelector('div:nth-child(2) > div:nth-child(2) > select[name=status] option:checked').value;
  let cs_status = parentDiv.querySelector('div:nth-child(2) > div:nth-child(3) > select[name=cs_status] option:checked').value;

  let payment_status_span = grandparentDiv.querySelector('.collapsible > div > div:nth-child(2) > span:nth-child(1)');
  let status_span = grandparentDiv.querySelector('.collapsible > div > div:nth-child(2) > span:nth-child(2)');
  let cs_status_span = grandparentDiv.querySelector('.collapsible > div > div:nth-child(2) > span:nth-child(3)');

  fetch('./update', {
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      paymentno: paymentno,
      payment_status: payment_status,
      status: status,
      cs_status: cs_status
    }),
  })
  .then((response) => {
    if (!response.ok) {
      throw new Error('Network response was not ok' + response.statusText);
    }
    return response.text();
  })
  .then((response) => {
    console.log(response);
    payment_status_span.innerHTML = '결제상태: ' + payment_status;
    status_span.innerHTML = '주문상태: ' + status;
    cs_status_span.innerHTML = 'C S 상태: ' + cs_status;
    
    newPaymentListLoding();
    
  })
  .catch(() => {
    alert('주문정보 업데이트에 실패하였습니다.');
  });
}
/*]]>*/
