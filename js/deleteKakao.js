document.addEventListener("DOMContentLoaded", ()=> {
    // kakao sdk 초기화
    Kakao.init('fb6caa2c48ed468cb0bd26fbe289bb73'); // 사용하려는 앱의 JavaScript 키 

    const KAKAO_UNLINK_URI = 'https://kapi.kakao.com/v1/user/unlink';
    const accessToken = document.getElementById('Access_Token').value; 

    async function unlinkApp() {
    try {
        if (!window.confirm('정말로 탈퇴하시겠습니까?'))
            return false;
        const response = await fetch(KAKAO_UNLINK_URI, {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Bearer ' + accessToken, 
        },
        });

        if (!response.ok) {
        throw new Error('Network response was not ok');
        }

        const data = await response.json(); // JSON 응답 처리
        alert('카카오로 연동된 계정이 탈퇴 되었습니다.');
        const contextPath = 'http://localhost:8080';
        location.href = contextPath + '/logout';
        console.log('연결 해제 성공:', data);

            // 서버에 탈퇴 완료 알림
        const notifyResponse = await fetch('/member/notifyDeleted', {
            method: 'GET'
        });
        if (!notifyResponse.ok) {
            throw new Error('Server notification request failed');
        }

        const notifyData = await notifyResponse.json();
        console.log('서버 알림 성공:', notifyData);

    } catch (error) {
        console.error('오류 발생:', error);
    }
}

document.getElementById('unlinkButton').addEventListener('click', unlinkApp);

// 기본 계정 탈퇴
$(function (){
    $('#delete').on('click', function(){
        if (!window.confirm('정말 탈퇴하시겠습니까?'))
            return false;
    });
})


});
