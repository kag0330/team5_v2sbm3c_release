document.addEventListener("DOMContentLoaded", function() {
  var report = document.querySelectorAll(".report");
  var popup = document.getElementById("popup");
  var closebtn = document.querySelector(".close-btn");
  var popupreviewno = document.getElementById("reviewno");
  var popupnickname = document.getElementById("reportnickname"); // reportnickname 요소를 가져옵니다.
  var createReport = document.getElementById("createReport");
  var reportTitle = document.getElementById("reportTitle");
  var reportContents = document.getElementById("reportContents");
  var selectType = document.getElementById("reportType");
  report.forEach(function(link) {
    link.addEventListener("click", function(event) {
      event.preventDefault();
      var memberno = link.closest(".review").querySelector(".memberno").value;
      var myno = document.getElementById("mymemberno") ? document.getElementById("mymemberno").getAttribute("data-mymemberno") : 0;
      if (myno === 0) {
        alert('로그인 후 이용해주세요.');
      } else if (memberno === myno) {
        alert('본인은 신고할 수 없습니다.');
      } else {
        var reviewno = link.closest(".review").querySelector(".reviewno").value;
        var nickname = link.closest(".review").querySelector(".nickname").value;

        fetch('/report/report_count', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            reviewno: reviewno
          })
        })
          .then(response => response.json())
          .then(data => {
            if (data === 0) {
              popupreviewno.value = reviewno;
              popupnickname.textContent = "신고 대상: " + nickname;
              popup.style.display = "block";
            } else {
              alert("해당 댓글을 이미 신고하셨습니다.");
            }
          })
          .catch(error => console.error('Error:', error));
      }

    });
  });

  closebtn.addEventListener("click", function() {

    popup.style.display = "none";
  });

  createReport.addEventListener("click", function(event) {
    event.preventDefault();
    var typeno = selectType.options[selectType.selectedIndex].value;

    if (typeno.trim() === '' || reportTitle.value.trim() === '' || reportContents.value.trim() === '') {
      alert("신고 유형, 제목, 내용을 입력해주세요.");
      return;
    }

    fetch('/report/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        title: reportTitle.value,
        contents: reportContents.value,
        typeno: typeno,
        reviewno: popupreviewno.value
      })
    })
      .then(response => response.json())
      .then(data => {
        if (data.fail) {
          alert('로그인 후 이용해주세요.');
        } else if (data.success) {
          alert('신고가 성공적으로 접수되었습니다.');
          popup.style.display = "none";
          window.location.href = "/";
        } else {
          alert('신고 접수에 실패했습니다.');
        }
      })
      .catch(error => console.error('Error:', error));
  });

});