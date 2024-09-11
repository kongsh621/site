document.addEventListener("DOMContentLoaded", () => {
    // 이메일 중복 버튼
    $(document).ready(function() {
        $('#idCheck').click(function() {
               const email = $('#email').val();

               if (!email) {
                   alert("이메일을 입력해 주세요.");
                   return;
               }
               $.ajax({
                   type: "POST",
                   url: "/member/idcheck",
                   contentType: "application/json",
                   data: JSON.stringify({ email: email }),
                   success: function(response) {
                       var message = response.message;
                       console.log('message는 ' + message);
                       alert(message);
                   },
                     error: function(xhr, status, error) {
                        console.error("AJAX 오류: ", xhr, status, error); // 상세 오류 로그 출력
                       alert("값을 가져오지 못했습니다.~ 상태: " + status + " 오류: " + error);
                   }
                });
            });
        });

    const signUp = document.getElementById('sign-up');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const passwordCheck = document.getElementById('passwordCheck');
    const name = document.getElementById('name');
    const nickname = document.getElementById('nickname');
    const age = document.getElementById('age');
    const button = document.getElementById('submitButton');

    let isEmailValid = false;
    let isPasswordValid = false;
    let isPasswordCheckValid = false;
    let isNameValid = false;
    let isNicknameValid = false;
    let isAgeValid = false;
    let isButtonValid = false;


    // 에러 메시지 표시
    function showError(input, errorId){
        const errorElement = document.getElementById(errorId);
        errorElement.style.display = 'block';

        // 입력창 테두리 색
        input.style.border = '1px solid red';
    }

    // 에러 메시지 숨기기
    function hideError(input, errorId){
        const errorElement = document.getElementById(errorId);
        errorElement.style.display = 'none';
        input.style.border = '1px solid gray';
    }

    function checkEmail(){
        const emailValue = email.value.trim();

        isEmailValid = false;
        hideError(email, 'emailEmptyError');

        if (!emailValue){
            showError(email, 'emailEmptyError');
        } else 
            isEmailValid = true;

        buttonActive();
    }

    function checkPassword(){
        const passwordValue = password.value.trim();

        isPasswordValid = false;
        hideError(password, 'passwordEmptyError');

        if (!passwordValue){
            hideError(password, 'passwordLengthError');
            showError(password, 'passwordEmptyError');
        } else if (passwordValue.length < 8){
            hideError(password, 'passwordEmptyError');
            showError(password, 'passwordLengthError');
        } 
        else 
            isPasswordValid = true;

        buttonActive();
    }
    function checkPasswordCheck(){
        const passwordCheckValue = passwordCheck.value.trim();
        const passwordValue = password.value.trim();

        isPasswordCheckValid = false;
        hideError(passwordCheck, 'passwordCheckEmptyError');
        hideError(passwordCheck, 'passwordCheckIncorrectError');

        if (!passwordCheckValue){
            showError(passwordCheck, 'passwordCheckEmptyError');
        } else if (passwordValue != passwordCheckValue){
            showError(passwordCheck, 'passwordCheckIncorrectError')
        } 
        else 
            isPasswordCheckValid = true;

        buttonActive();
    }
    function checkName(){
        const nameValue = name.value.trim();

        isNameValid = false;
        hideError(name, 'nameEmptyError');

        if (!nameValue){
            showError(name, 'nameEmptyError');
        } else 
            isNameValid = true;

        buttonActive();
    }
    function checkNickname(){
        const nicknameValue = nickname.value.trim();

        isNicknameValid = false;
        hideError(nickname, 'nicknameEmptyError');

        if (!nicknameValue){
            showError(nickname, 'nicknameEmptyError');
        } else 
            isNicknameValid = true;

        buttonActive();
    }

    function checkAge(){
        const ageValue = age.value.trim();

        isAgeValid = false;
        hideError(age, 'ageEmptyError');

        if (!ageValue){
            showError(age, 'ageEmptyError');
        } else 
            isAgeValid = true;

        buttonActive();
    }
    function buttonActive(){
        isButtonValid = false;
        if (isEmailValid && isPasswordValid && isPasswordCheckValid && isNameValid && isNicknameValid && isAgeValid){
            isButtonValid = true;
            button.disabled = false;
        } else button.disabled = true;
    }

    buttonActive();

    // focusout 했을 때 위의 것들을 체크
    if (email){
        email.addEventListener("focusout", checkEmail);
    }
    if (password){
        password.addEventListener("focusout", checkPassword);
    }
    if (passwordCheck){
        passwordCheck.addEventListener("focusout", checkPasswordCheck);
    }
    if (name){
        name.addEventListener("focusout", checkName);
    }
    if (nickname){
        nickname.addEventListener("focusout", checkNickname);
    }
    if (age){
        age.addEventListener("focusout", checkAge);
    }
});