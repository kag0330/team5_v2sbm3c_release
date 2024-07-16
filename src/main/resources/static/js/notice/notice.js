document.addEventListener("DOMContentLoaded", function() {
  $.noConflict();
  var zoom = document.querySelectorAll('.zoom');

  zoom.forEach(function(button) {
    button.addEventListener('click', function() {
      var tr = this.closest('tr');
      var noticeno = tr.querySelector('input[id="noticeno"]').value;
      var content = tr.nextElementSibling; 
      fetch('/notice/increased_views', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ noticeno: noticeno, zoom: button.innerText })
      })
        .then(response => response.json())
        .then(response => {
          tr.querySelector('#views').textContent = response.views;
          if (response.status == 'increased') {
            button.innerText = "-";
          } else if (response.status == 'close') {
            button.innerText = "+";
          }
          if (content) {
            content.classList.toggle('contents');
          }
        })
        .catch(error => console.error('Error:', error));
        
        button.innerHTML = "<img src='/images/progress.gif' style='width: 30%;'>";
    });
  });

  var frm_search = document.querySelector('form[name="frm_search"]');
  frm_search.addEventListener('submit', function(event) {
    event.preventDefault(); // 기본 동작 중단
    var word = document.getElementById('word').value;
    if (word.trim() === '') {
      alert('검색어를 입력해주세요.');
      return;
    }
    fetch('/notice/search', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ word: word })
    })
      .then(response => response.json())
      .then(size => {
        if (parseInt(size) == null || parseInt(size) === 0) {
          alert('검색 결과가 없습니다.');
        } else {
          frm_search.submit();
        }
      })
      .catch(error => console.error('Error:', error));
  });

});