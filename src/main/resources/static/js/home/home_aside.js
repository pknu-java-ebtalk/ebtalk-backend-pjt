// main.js
// study_register.html 내용을 로드해서 삽입하는 함수
function loadHtml() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', '../../html/home/home_aside.html', true); // 본인이 side에 '넣을' 코드 적힌 파일 상대경로 작성
  xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
          document.querySelector('#aside-contents-box').innerHTML = xhr.responseText;
      }
  };
  xhr.send();
}

window.addEventListener('load', loadHtml);