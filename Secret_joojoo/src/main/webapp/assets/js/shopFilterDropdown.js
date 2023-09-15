const sectionsData = [
    {
        id: "frequency",
        text: "도수",
        options: ["0%~10%", "10%~20%", "20%~30%", "30%~40%"],
    },
    {
        id: "sweetness",
        text: "단맛",
        options: ["약", "중", "강"],
    },
    {
        id: "sour-taste",
        text: "신맛",
        options: ["약", "중", "강"],
    },
    {
        id: "carbonic-acid",
        text: "탄산",
        options: ["약", "중", "강"],
    },
];

function generateSectionElements() {
    const sectionContainer = document.querySelector(".section-container");

    sectionsData.forEach((sectionData) => {
        const section = document.createElement("section");
        section.setAttribute("id", sectionData.id);
        section.classList.add("section");

        const selectDiv = document.createElement("div");
        selectDiv.setAttribute("class", "select");

        const textDiv = document.createElement("div");
        textDiv.setAttribute("class", "text");
        textDiv.textContent = sectionData.text;

        const ul = document.createElement("ul");
        ul.setAttribute("class", "option-list");

        sectionData.options.forEach((optionText) => {
            const li = document.createElement("li");
            li.setAttribute("class", "option");
            li.textContent = optionText;
            ul.appendChild(li);
        });

        selectDiv.appendChild(textDiv);
        selectDiv.appendChild(ul);
        section.appendChild(selectDiv);
        sectionContainer.appendChild(section);
    });
}

function onClickSelect(e) {
    const isActive = e.currentTarget.classList.contains("active");
    if (isActive) {
        e.currentTarget.classList.remove("active");
    } else {
        e.currentTarget.classList.add("active");
    }

    const themeSection = e.currentTarget.closest("section");
    const isActiveClass = themeSection.querySelector(".option.active");
    if (isActiveClass) {
        themeSection.classList.add("active");
    } else {
        themeSection.classList.remove("active");
    }


}

function onClickOption(e) {
    const selectedValue = e.currentTarget.innerHTML;
    const textElement = e.currentTarget.closest(".select").querySelector(".text");
    textElement.textContent = selectedValue;

    // 선택한 값의 텍스트 색상과 굵기 변경
    const prevSelectedOption = e.currentTarget.closest(".select").querySelector(".option.active");
    if (prevSelectedOption) {
        prevSelectedOption.classList.remove("active");
    }
    e.currentTarget.classList.add("active");
    textElement.classList.add("active");

    // 선 색상 변경
    const themeSection = e.currentTarget.closest("section");
    const isActiveClass = themeSection.querySelector(".option.active");
    if (isActiveClass) {
        themeSection.style.border = "1px solid #FF4D63";
        themeSection.style.borderRadius = "5px";
    } else {
        themeSection.style.border = "";
    }

    // 선택된 옵션의 스타일 변경을 제거
    const optionList = e.currentTarget.closest(".option-list");
    const options = optionList.querySelectorAll(".option");
    options.forEach((option) => {
        option.style.cssText = "";
    });


    var tmppalcoholValue = getSelectedOptionValue("frequency") // "0%~10%", "10%~20%", "20%~30%", "30%~40%"
    var extractedNumbers = null;
    var palcoholValue = null;
    console.log('확인: ' + tmppalcoholValue);
    if (tmppalcoholValue != '') {
        extractedNumbers = tmppalcoholValue.match(/\d+/g);   // 위 문자열에서 숫자만 추출 [0, 10] , [10,20],[20,30],[30,40]
        palcoholValue = parseInt(extractedNumbers[1]);   // 그 중 2번째 자리에 있는 숫자 추출
    }

    const psweetValue = getSelectedOptionValue("sweetness");
    const psourValue = getSelectedOptionValue("sour-taste");
    const psparkleValue = getSelectedOptionValue("carbonic-acid");
    // 선택한 값을 URL에 추가하여 새로운 페이지로 이동

    const urlParams = new URLSearchParams();

    urlParams.set("pSweet", psweetValue);
    urlParams.set("pSour", psourValue);
    urlParams.set("pSparkle", psparkleValue);
    urlParams.set("pAlcohol", palcoholValue * 1.0);


    const newUrl = `searchFilter.do?${urlParams.toString()}`;
    window.location.href = newUrl;
}


function getSelectedOptionValue(id) {
    const section = document.getElementById(id);
    const activeOption = section.querySelector(".option.active");
    return activeOption ? activeOption.textContent : "";
}


function addEventListeners() {
    const selectList = document.querySelectorAll(".select");
    selectList.forEach((select) => {
        select.addEventListener("click", onClickSelect);
        const optionList = select.querySelectorAll(".option");
        optionList.forEach((option) => {
            option.addEventListener("click", onClickOption);
        });
    });
}

generateSectionElements();
addEventListeners();