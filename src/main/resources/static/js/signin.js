function send() { // 회원 가입 처리
  let id = document.getElementById('id');
  let id_msg = document.getElementById('id_msg');

  if (id.value.trim().length == 0) {
    id_msg.innerHTML = 'ID가 누락됬습니다. ID 입력은 필수 입니다.';
    id_msg.classList.add('span_warning');    // class 적용
    id.focus();

    return false;  // 회원 가입 진행 중지

  }
  
  let passwd = document.getElementById('pw1');
  let passwd_msg = document.getElementById('pw_msg');

  if (passwd.value.trim().length == 0) {
    passwd_msg.innerHTML = '패스워드가 누락됬습니다. 패스워드 입력은 필수 입니다. ';
    passwd_msg.classList.add('span_warning');    // class 적용
    passwd.focus();

    return false;  // 회원 가입 진행 중지

  }
  document.querySelector('#frm').submit();
}


window.onload = function() {
  document.querySelector('#id').addEventListener('keypress', (event) => {
    // document.getElementById('passwd').addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
      document.getElementById('pw1').focus();
    }
  });

  document.querySelector('#pw1').addEventListener('keypress', (event) => {
    // document.getElementById('passwd').addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
      send();
    }
  });


}