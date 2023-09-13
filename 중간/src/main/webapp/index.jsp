<!DOCTYPE html>
<html>
<head>
  <style>
    /* 아코디언 항목 스타일 */
    .accordion {
      cursor: pointer;
      padding: 18px;
      background-color: #f1f1f1;
      border: none;
      text-align: left;
      outline: none;
      transition: 0.4s;
    }

    /* 펼쳐진 상태 스타일 */
    .active {
      background-color: #ccc;
    }

    /* 아코디언 콘텐츠 스타일 */
    .panel {
      padding: 0 18px;
      display: none;
      background-color: white;
      overflow: hidden;
    }
  </style>
</head>
<body>

<h2>아코디언 예시</h2>

<button class="accordion">섹션 1</button>
<div class="panel">
  <p>섹션 1의 내용을 여기에 표시합니다.</p>
</div>

<button class="accordion">섹션 2</button>
<div class="panel">
  <p>섹션 2의 내용을 여기에 표시합니다.</p>
</div>

<script>
  // JavaScript로 아코디언 동작 구현
  var acc = document.getElementsByClassName("accordion");
  var i;

  for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
      this.classList.toggle("active");
      var panel = this.nextElementSibling;
      if (panel.style.display === "block") {
        panel.style.display = "none";
      } else {
        panel.style.display = "block";
      }
    });
  }
</script>

</body>
</html>
