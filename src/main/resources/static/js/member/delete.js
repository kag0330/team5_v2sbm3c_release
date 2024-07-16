function checkId() {
  let id = document.querySelector('#id');
  let text = document.querySelector('#checkIdText');
  let boolean = false;

  fetch('./checkId?id=' + id.value, {
    method: 'GET'
  })
    .then(response => response.json())
    .then(rdata => {
      console.log(rdata)

      if (rdata > 0) {
        text.innerHTML = '이미 사용중인 아이디입니다.';
        text.style.color = 'red';
        boolean = false;
      } else {
        text.innerHTML = '사용 가능한 아이디입니다.';
        text.style.color = 'blue';
        boolean = true;

      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
  return boolean;
}

function checkPw() {
  let pw1 = document.querySelector('#pw1');
  let pw2 = document.querySelector('#pw2');
  let text = document.querySelector('#checkPwText');

  if (pw1.value.trim().length == 0) {
    return false;
  }
  if (pw1.value == pw2.value) {
    text.innerHTML = '서로 일치하는 비밀번호입니다.'
    text.style.color = 'blue';
  } else {
    text.innerHTML = '서로 일치하지 않는 비밀번호입니다.'
    text.style.color = 'red';
  }

}

function checkPhone() {
  let regex = /^\d{2,3}-\d{4}-\d{4}$/;
  let phone = document.querySelector('#phone').value;
  let text = document.querySelector('#checkPhoneText');
  if (!regex.test(phone)) {
    console.log('1')
    text.style.display = '';
    text.innerHTML = '올바른 형식이 아닙니다. 다시 입력해주세요.';
    text.style.color = 'red';
  } else {
    text.style.display = 'none'
    text.innerHtML = '';
  }
}

function checkEmail() {
  let regex = /^.+@.+\..+$/;
  let email = document.querySelector('#email').value;
  let text = document.querySelector('#checkEmailText');

  if (!regex.test(email)) {
    console.log('1')
    text.style.display = '';
    text.innerHTML = '올바른 형식이 아닙니다. 다시 입력해주세요.';
    text.style.color = 'red';
  } else {
    text.style.display = 'none'
    text.innerHtML = '';
  }
}

function dateMix() {
  let datefield = document.querySelector('#dateField');
  let year = document.querySelector('#year')
  let yearOption = year.options[year.selectedIndex].value;

  let month = document.querySelector('#month');
  let monthOption = month.options[month.selectedIndex].value;

  let day = document.querySelector('#day');
  let dayOption = day.options[day.selectedIndex].value;

  let mix = yearOption + '-' + monthOption + '-' + dayOption;
  datefield.value = mix;
}

function send() { // 회원 가입 처리
  let id = document.querySelector('#id');
  let idText = document.querySelector('#checkIdText');
  if (id.value.trim().length == 0) {
    idText.innerHTML = '아이디가 누락됬습니다.';
    idText.style.color = 'red';
    id.focus();
    return false;  // 회원 가입 진행 중지
  }


  let pw1 = document.querySelector('#pw1');
  let pw2 = document.querySelector('#pw2');
  let pwText = document.querySelector('#checkPwText');
  if (pw1.value.trim().length == 0) {
    pwText.innerHTML = '비밀번호가 누락됬습니다.';
    pwText.style.color = 'red';
    pw1.focus();
    return false;  // 회원 가입 진행 중지
  }

  if (pw1.value != pw2.value) {
    pwText.innerHTML = '서로 일치하지 않는 비밀번호입니다.';
    pwText.style.color = 'red';
    pw1.focus();  // 첫번째 패스워드로 focus 설정

    return false;  // 회원 가입 진행 중지
  }


  let name = document.querySelector('#name');
  let nameText = document.querySelector('#checkNameText');
  if (name.value.length == 0) {
    nameText.innerHTML = '이름 입력은 필수입니다.';
    nameText.style.color = 'red';
    name.focus();

    return false;  // 회원 가입 진행 중지
  }


  let nick = document.querySelector('#nick');
  let nickText = document.querySelector('#checkNickText');
  if (nick.value.length == 0) {
    nickText.innerHTML = '닉네임 입력은 필수입니다.';
    nickText.style.color = 'red';
    nick.focus();

    return false;  // 회원 가입 진행 중지
  }


  let phone = document.querySelector('#phone');
  let phoneText = document.querySelector('#checkPhoneText');

  if (phone.value.length == 0) {
    phoneText.innerHTML = '전화번호 입력은 필수입니다.';
    phoneText.style.color = 'red';
    phone.focus();

    return false;  // 회원 가입 진행 중지
  }

  let email = document.querySelector('#email');
  let emailText = document.querySelector('#checkEmailText');

  if (email.value.length == 0) {
    emailText.innerHTML = '이메일 입력은 필수입니다.';
    emailText.style.color = 'red';
    email.focus();

    return false;  // 회원 가입 진행 중지
  }

  let year = document.querySelector('#year');
  let yearOption = year.options[year.selectedIndex].value;

  let month = document.querySelector('#month');
  let monthOption = month.options[month.selectedIndex].value;

  let day = document.querySelector('#day');
  let dayOption = day.options[day.selectedIndex].value;

  let dateText = document.querySelector('#checkDateText');
  if (yearOption == '년' || monthOption == '월' || dayOption == '일') {
    dateText.innerHTML = '생년월일 입력은 필수입니다.';
    dateText.style.color = 'red';
    return false;  // 회원 가입 진행 중지
  }

  let gender = document.querySelector('#gender');
  let genderOption = gender.options[gender.selectedIndex].value;
  let genderText = document.querySelector('#checkGenderText');
  if(genderOption == '성별 선택'){
    genderText.innerHTML = '성별선택은 필수입니다.';
    genderText.style.color = 'red';
    return false;
  }

  dateMix()

  document.querySelector('#zipcode').disabled = false;
  document.querySelector('#addr1').disabled = false;
  document.querySelector('#frm').submit();
}


window.onload = function() {
  document.querySelector('#id').addEventListener('keypress', (event) => {
    // document.getElementById('passwd').addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
      document.getElementById('btn_checkID').focus();
    }
  });

  document.querySelector('#passwd').addEventListener('keypress', (event) => {
    // document.getElementById('passwd').addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
      document.getElementById('passwd2').focus();
    }
  });

  document.querySelector('#passwd2').addEventListener('keypress', (event) => {
    // document.getElementById('passwd').addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
      document.getElementById('mname').focus();
    }
  });

  document.querySelector('#mname').addEventListener('keypress', (event) => {
    // document.getElementById('passwd').addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
      document.getElementById('tel').focus();
    }
  });

  document.querySelector('#tel').addEventListener('keypress', (event) => {
    // document.getElementById('passwd').addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
      document.getElementById('btn_DaumPostcode').focus();
    }
  });

  document.querySelector('#address2').addEventListener('keypress', (event) => {
    // document.getElementById('passwd').addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
      document.getElementById('btn_send').focus();
    }
  });


}