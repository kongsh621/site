/* const getCookie = (key) => {
    const cookies = document.cookie;
    const cookieList = cookies.split("; ").map(el => el.split("="));
    // "K=V" 형태 -> "K", "V"

    const obj = {};
        console.log("saveId =" + getCookie);

    // 다루기 쉽게 obj 객체에 추가
    for (let i = 0; i < cookieList.length; i++){
        const k = cookieList[i][0];
        const v = cookieList[i][1];
        obj[k] = v;
    }
    return obj[key];
}

const loginEmail = document.querySelector("#loginForm input[name='email']");

if (loginEmail != null) {
    const saveId = getCookie("saveId");

    // 체크된 경우
    if (saveId != undefined){
        loginEmail.value = saveId; // 쿠키에서 얻어온 값을 input의 value 값으로 세팅

        // 아이디 저장 체크박스에 체크
        document.querySelector("input[name='saveId']").checked = true;
    }
}*/
// 로그인 쿠키
document.addEventListener('DOMContentLoaded', () => {
    const getCookie = (key) => {
        const cookies = document.cookie.split('; ');
        for (let cookie of cookies) {
            const [cookieKey, cookieValue] = cookie.split('=');
            if (cookieKey === key) {
                return decodeURIComponent(cookieValue); // 디코딩
            }
        }
        return undefined;
    }

    const loginEmail = document.querySelector("#loginForm input[name='email']");
    const saveIdCheckbox = document.querySelector("input[name='saveId']");

    if (loginEmail != null && saveIdCheckbox != null) {
        const saveId = getCookie("saveId");

        if (saveId != undefined) {
            loginEmail.value = saveId;
            saveIdCheckbox.checked = true;
        }
    }
});

// 로그인 버튼 누르면 당시 경로 저장하고 로그인 후에 거기로 돌아감 
document.addEventListener('DOMContentLoaded', () => {
    var element = document.getElementById('loginBt');
    if (element) {
        element.addEventListener('click', function(event){
            const baseUrl = 'http://localhost:8080'; // 기본값
            const pageUrl = window.location.href;
            console.log('보내기 전 pageUrl = ' + pageUrl);
            element.disabled = true; // 버튼 비활성화

            fetch(`${baseUrl}/member/pageurl`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    pageUrl: pageUrl
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('에러');
                }
                return response.json();
            })
            .then(data => {
                console.log('PreLoginUrl: ', data);
                element.disabled = false; // 요청 성공 후 버튼 활성화
            })
            .catch((error) => {
                console.error('Error:', error);
                element.disabled = false; // 요청 실패 후 버튼 활성화
            });
        });
    }
});

