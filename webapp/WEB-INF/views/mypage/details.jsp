<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>My Page</title>
    <%@ include file="/WEB-INF/views/fragment/header2.jsp" %>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
  
    <style>
  /* 사이드바 래퍼 스타일 */

  #page-wrapper {
    padding-left: 250px;
  }

  #sidebar-wrapper {
    position: absolute;
    width: 250px;
    height: 100%;
    margin-left: -250px;
    overflow-x: hidden;
    overflow-y: auto;
  }

  #page-content-wrapper {
    width: 100%;
    padding: 20px;
     border-left: 1px solid #aaa;
  }
  /* 사이드바 스타일 */

  .sidebar-nav {
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
  }

  .sidebar-nav li {
    text-indent: 1.5em;
    line-height: 2.8em;
  }

  .sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: black;
    padding: 0.4em 1em;
  }

  .sidebar-nav li a:hover {
    background: #ccc;
  }

    </style>
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js">
    // <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
            $(function (){
                $('#delete').on('click', function(){
                    if (!window.confirm('정말 탈퇴하시겠습니까?'))
                        return false;
                });
            })
    </script>
</head>

<body>
 
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="col-sm-11 page">
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <div class="card">
                              <div class="card-header">마이페이지</div>
                                <div class="card-body">
                                        <div class="form-group row">
                                            <input type="hidden" id="id" value="${member.id}">
                                            <label for="email" class="col-md-4 col-form-label text-md-right">아이디:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="email" value="${member.email}">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="name" class="col-md-4 col-form-label text-md-right">이름:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="name" value="${member.name}">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="nickname" class="col-md-4 col-form-label text-md-right">닉네임:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="nickname" value="${member.nickname}">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="age" class="col-md-4 col-form-label text-md-right">나이:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="age" value="${member.age}">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="regdate" class="col-md-4 col-form-label text-md-right">가입일:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="regdate" value="${member.regdate}">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="updatedate" class="col-md-4 col-form-label text-md-right">최근 수정일:</label>
                                            <div class="col-md-6">
                                                <input type="text" readonly class="form-control-plaintext" id="updatedate" value="${member.updatedate}">
                                            </div>
                                        </div>
                                        <c:if test="${not empty kakaoLogin}">
                                            <div class="col-md-10 offset-md-3 row">
                                                <div class="col-md-3">
                                                    <a href="/member/update"><button class="btn btn-primary">회원정보 변경</button></a>
                                                </div>
                                                <div class="col-md-3">
                                                    <button class="btn btn-primary" onclick="unlinkApp()">연동 탈퇴</button>
                                                    <input type="hidden" value="${Access_Token}" id="Access_Token">
                                                </div>
                                            </div>
                                        </c:if>

                                        <c:if test="${empty kakaoLogin}">
                                            
                                            <div class="col-md-10 offset-md-2 row">
                                                <div class="col-md-3">
                                                    <a href="/member/update"><button class="btn btn-primary">회원정보 변경</button></a>
                                                </div>

                                                <div class="col-md-3">
                                                    <a href="/mypage/setpass"><button class="btn btn-primary">비밀번호 변경</button></a>
                                                </div>
                                                <div class="col-md-3">
                                                    <a href="/member/delete?id=${member.id}"><button class="btn btn-primary" id="delete">회원 탈퇴</button></a>
                                                </div>
                                            </div>
                                        </c:if>
                                                <!-- 카카오 연동 아이디 탈퇴  -->
                                                <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.2/kakao.min.js"
                                                    integrity="sha384-TiCUE00h649CAMonG018J2ujOgDKW/kVWlChEuu4jK2vxfAAD0eZxzCKakxg55G4" crossorigin="anonymous"></script>
                                                    <script>
                                                        // kakao sdk 초기화
                                                    Kakao.init('fb6caa2c48ed468cb0bd26fbe289bb73'); // 사용하려는 앱의 JavaScript 키 입력
                                                    </script>

                                                    <script>
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

                                                    // function unlinkApp() {
                                                    //     const Access_Token = document.getElementById('Access_Token').value;
                                                    //     // api 요청
                                                    //     Kakao.API.request({
                                                    //     url: 'https://kapi.kakao.com/v1/user/unlink',
                                                    //     headers: {
                                                    //         'Content-Type': 'application/x-www-form-urlencoded',
                                                    //         'Authorization': 'Bearer ' + Access_Token,
                                                    //     },
                                                    //     })
                                                    //     .then(function(res) {
                                                    //         alert('카카오로 연동된 계정(id:' + res +')이 탈퇴 되었습니다.');
                                                    //         location.href = contextPath + '/logout';
                                                    //         document.cookie ='authorize-access-token=;';
                                                    //     })
                                                    //     .catch(function(err) {
                                                    //         alert('탈퇴에 실패했습니다.');
                                                    //         console.error(err); // 에러 내용 콘솔에 출력 (디버깅에 유용)
                                                    //     });
                                                    // }
                                                    // // 아래는 데모를 위한 UI 코드입니다.
                                                    // function deleteCookie() {
                                                    //     document.cookie = 'authorize-access-token=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
                                                    // }
                                                    </script>
                                      

                                </div>
                              </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 헤더 마무리 -->
    </div>
</div>

<%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

    </body>
</html>
