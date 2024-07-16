// DOMContentLoaded 이벤트 리스너 등록
document.addEventListener('DOMContentLoaded', function() {
  // 검색 아이콘 클릭 이벤트 처리
  document.getElementById('search-icon').addEventListener('click', function(event) {
    event.preventDefault(); // 기본 동작 방지
    openModal(); // 모달 열기
  });

  // 검색 버튼 클릭 이벤트 처리
  document.getElementById('search-btn').addEventListener('click', function(event) {
    event.preventDefault(); // 기본 동작 방지
    search(); // 검색 실행
  });
});

// 모달 열기 함수
function openModal() {
  document.getElementById('search-modal').style.display = 'block';
}

// 모달 닫기 함수
function closeModal() {
  document.getElementById('search-modal').style.display = 'none';
}

// 검색 실행 함수
function search() {
  var search = document.getElementById('search-input').value;
  // 검색어를 저장해
  var url = '/shoes/list?word=' + encodeURIComponent(search);
  window.location.href = url;
}
