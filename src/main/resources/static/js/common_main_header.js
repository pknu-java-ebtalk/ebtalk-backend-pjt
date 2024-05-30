// main.js
// study_register.html 내용을 로드해서 삽입하는 함수
function loadHtml() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', '../common/common_main_header.html', true); // 경로는 실제 study_register.html의 경로로 변경해야 함
  xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
          document.querySelector('header').innerHTML = xhr.responseText;
      }
  };
  xhr.send();
}

window.addEventListener('load', loadHtml);