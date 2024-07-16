document.addEventListener("DOMContentLoaded", function() {
  var moreButton = document.getElementById("moreButton");
  var moreMenu = document.querySelector('.moreMenu');
  var moreMenuContainer = document.querySelector('.content_main');

  moreButton.addEventListener("click", function(event) {
    // 기본 동작 중지

    // 추가 내용이 숨겨져 있는 경우 보여줍니다.
    if (moreMenu.style.display === "none") {
      moreMenu.style.display = "block";
      moreButton.textContent = "접기";
      moreMenuContainer.appendChild(moreMenu); // 새로운 내용을 moreMenuContainer의 자식으로 추가
    }
    // 추가 내용이 보여져 있는 경우 숨깁니다.
    else {
      moreMenu.style.display = "none";
      moreButton.textContent = "더보기+";
      moreMenuContainer.removeChild(moreMenu);
    }

  });
document.querySelector('.btn_close').addEventListener('click', function() {
    this.style.display = 'none'; // 클릭했을 때 요소를 숨김
});

});
