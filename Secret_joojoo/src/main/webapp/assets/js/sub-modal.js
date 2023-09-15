// 모달창

   // 모달창 열기 함수
  const openModal = () => {
    document.querySelector(".modal").classList.remove("hidden");
  };

  // 모달창 닫기 함수
  const closeModal = () => {
    document.querySelector(".modal").classList.add("hidden");
  };

  // 지금 신청하기 버튼 클릭 이벤트 리스너
  document.querySelector(".sub-openBtn").addEventListener("click", openModal);

  // 닫기 버튼 클릭 이벤트 리스너
  document.querySelector(".closeBtn").addEventListener("click", closeModal);
  
  // submit 버튼 클릭 이벤트 리스너
  document.querySelector("#sub-buy").addEventListener("click", closeModal);

