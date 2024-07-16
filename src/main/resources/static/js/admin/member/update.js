window.onpageshow = function(event) {
  if (event.persisted) {
    document.querySelector('#no').disabled = true;
  }
};

function send(){
  document.querySelector('#no').disabled = false;
  document.querySelector('#frm').submit();
}