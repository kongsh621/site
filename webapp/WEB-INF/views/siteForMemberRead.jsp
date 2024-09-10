<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ include file="/WEB-INF/views/fragment/header2.jsp" %>


        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />

<script>
    $(function (){
        $('#delete').on('click', function(){
            if (!window.confirm('정말로 삭제하시겠습니까?'))
                return false;
        });
    })

    $(function (){
                $('#deleteCom').on('click', function(){
                    if (!window.confirm('정말로 삭제하시겠습니까?'))
                        return false;
                });
            })
</script>
    </head>
    <body>
    <!-- Page content-->
    <div class="container mt-5">
        <div class="row">
            <div class="col-lg-12 center">
                <!-- Post content-->
                <article>
                    <!-- Post header-->
                    <header class="mb-4">
                        <!-- 작성자용 버튼 -->
                        <div style="display: inline-block;">
                            <div class="mb-3 justify-content-right">
                            <c:choose>
                                <c:when test="${not empty writer}">
                                        <a href="../list?page=${pageMaker.criteria.page}"><button class="btn btn-primary" style="text-decoration-line: none;">목록</button></a>
                                        <a href="../update?id=${board.id}&page=${pageMaker.criteria.page}"><button class="btn btn-primary" style="text-decoration-line: none;">수정</button></a>
                                        <a href="../delete?id=${board.id}&page=${pageMaker.criteria.page}"><button class="btn btn-primary" style="text-decoration-line: none;" id="delete">삭제</button></a>
                                </c:when>
                                <c:otherwise>
                                        <a href="../list?page=${pageMaker.criteria.page}"><button class="btn btn-primary" style="text-decoration-line: none;">목록</button></a>
                                </c:otherwise>
                            </c:choose>
                            </div>
                        </div>
                        <!-- Post title-->
                        <h2 class="fw-bolder mb-1">${board.title}</h2>
                        <!-- Post regdate-->
                        <div class="text-muted fst-italic mb-2">작성자: ${board.writer}</div>
                        <div class="text-muted fst-italic mb-2">작성일: ${board.regdate}</div>
                        <input type="hidden" value="${board.id}" name="postId" id="id">
                    </header>
                    <!-- Post content-->
                    <section class="mb-5">
                    <c:if test="${not empty attachedFile}"> <!--이미지 첨부되어 있으면 같이 출력-->
                        <!-- Preview image figure-->
                        <figure class="mb-4"><img class="img-fluid rounded" src="${board.filepath}" alt="${board.title}" /></figure>
                        <p class="fs-5 mb-4">${board.content}</p>
                    </section>

                    <a class="mb-4" href="${board.filepath}">[다운 받기]</a>
                    </c:if>
                </article>


                <!-- Comments section-->
                <section class="mt-3 mb-5">
                    <div class="card bg-light">
                        <div class="card-body">
                            <!-- 댓글수 -->
                                <p>댓글(${commentPageMaker.total})</p>
                                <!-- Comment form-->
                                <div class="container-fluid">
                                <form class="row mb-4" action="../writeComment">
                                    <input type="hidden" id="writer" name="writer" value="${loginSession.nickname}">
                                    <input type="hidden" name="postid" value="${board.id}">
                                    <div class="col-10"><textarea class="form-control justify-content-left" rows="3" name="content" placeholder="댓글을 남겨주세요."></textarea></div>
                                    <div class="col-2"><button type="submit" class="btn btn-primary justify-content-right" id="comButton">등록</button></div>   
                                </form>
                                <!-- Single comment-->
                                <c:forEach var="comment" items="${list}">
                                <div class="card col-10">
                                    <!-- <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div> -->
                                    <div class="card-body"> 
                                        <div class=" ms-3 mb-4" id="comment_container">
                                            <div class="fw-bold">${comment.writer}</div>
                                            <p><small>${comment.regdate}</small></p>
                                            ${comment.content}
                                        </div>
                                        <div class="card-footer justify-content-right">
                                            <c:if test="${comment.writer.equals(loginSession.nickname)}">
                                            <input type="hidden" name="id" value="${comment.id}">
                                                <a href="../deleteComment?id=${comment.id}&postid=${comment.postid}&page=${pageMaker.criteria.page}"><button type="submit" class="btn btn-primary col-md-1 offset-md-8 ml-3" id="delete">삭제</button></a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            </div>
                            </div>


<!-- 페이징 부분 -->
<%@ include file="/WEB-INF/views/fragment/paginationBoard.jsp" %>
<!--             -->
  
                        </div>
                    </div>
                </section>
            </div>
        <!-- 헤더 마무리 -->
    </div>
</div>

<!-- 댓글 작성 회원 전용 -->
    <script>
        document.getElementById('comButton').addEventListener('click', function(event){
            const writerValue = document.getElementById('writer').value;
        
            if (writerValue.trim() === ""){
                alert('댓글은 회원만 작성할 수 있습니다.');
                event.preventDefault();
                return;
            }
        });


// 토글 버튼
        window.addEventListener('DOMContentLoaded', event => {

// Toggle the side navigation
const sidebarToggle = document.body.querySelector('#sidebarToggle');
if (sidebarToggle) {
    // Uncomment Below to persist sidebar toggle between refreshes
    // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
    //     document.body.classList.toggle('sb-sidenav-toggled');
    // }
    sidebarToggle.addEventListener('click', event => {
        event.preventDefault();
        document.body.classList.toggle('sb-sidenav-toggled');
        localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
    });
}

});
// 오른쪽 상단 드롭다운
document.getElementById('navbarDropdown').addEventListener('click', function() {
    const content = document.getElementById('nav-content');
    content.classList.toggle('dropdown-item'); // 'show' 클래스를 토글
});
    </script>
    
<!-- <script>
    // 댓글 버튼 누르면 서버로 보내서 등록 후 양식 변경
    document.addEventListener('DOMContentLoaded', () => {
    const comInput = document.getElementById('comContent');
    const writer = document.getElementById('writer');
    const postid = document.getElementById('postId');
    const container = document.getElementById('comment_container');
    const comButton = document.getElementById('comButton');

    if (comButton) {
        comButton.addEventListener('click', function(event) {
            event.preventDefault(); // 폼 제출 기본 동작 방지

            // 내용 가져옴
            const content = comInput? comInput.value.trim() : '';
            const writerValue = writer ? writer.value.trim() : ''; // writer 값 가져오기
            const postIdValue = postid ? postid.value.trim() : ''; // postid 값 가져오기

            if (content && writerValue && postIdValue) {
                fetch('/receiveComment', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        content: content,
                        writer: writerValue, // writer 값을 JSON에 포함
                        postid: postIdValue // postid 값을 JSON에 포함
                    })
                })
                .then(response => {
                    if (!response.ok) {
                        return response.text().then(text => {
                            throw new Error(`서버 응답 오류: ${text}`);
                        });
                    }
                    return response.json();
                })
                .then(data => {
                    console.info('Received List: ', data);
                    comInput.value = ''; // 댓글 입력창 비우기
                    updateCommentList(data.comments); // 서버 응답의 댓글 목록으로 업데이트
                })
                .catch(error => {
                    console.error('Error:', error); // 오류 처리
                });
            } else {
                alert('댓글을 입력해 주세요. 모든 필드를 확인하세요.');
            }
        });
    } else {
        console.error('버튼 요소를 찾을 수 없습니다.');
    }

    function updateCommentList(comments) {
        // 기존 댓글 리스트 비우기
        container.innerHTML = '';

        // 댓글 목록 업데이트
        comments.forEach(comment => {
            const listItem = document.createElement('li');
            listItem.innerHTML = `
                <div class="fw-bold">${comment.writer}</div>
                ${comment.content}
            `;
            container.appendChild(listItem);
        });
    }
});

</script> -->


<%@ include file="/WEB-INF/views/fragment/footer2.jsp" %>

    </body>
</html>
