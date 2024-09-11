// 버튼으로 종류별 분류
document.addEventListener('DOMContentLoaded', () => {
  const buttons = document.querySelectorAll('.btn.btn-default');

  buttons.forEach(button => {
    button.addEventListener('click', function() {
      const buttonId = this.id;
      console.log('Type =', buttonId);

      fetch('/infoboard/sort', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ type: buttonId })
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('시스템에 문제가 발생하였습니다.');
        }
        return response.json();
      })
      .then(data => {
        const map1 = data;
        const map = new Map(Object.entries(map1)); // Map 객체로 변환

        console.log('Received Map:', map);

        // 서버에서 받은 데이터 
        const list = data.list; 
        const criteria = data.pageMaker.criteria;
        const total = data.pageMaker.total;

        console.log('Criteria:', criteria);
        console.log('Total:', total);

        // 기존 값 비우기
        const container = document.getElementById('board-container');
        container.innerHTML = ''; // 비우기

        // list에서 board를 하나씩 받아서
        // 받아온 값 추가
        list.forEach(board => { // 데이터타입 int 말고는 다 변환해주어야 한다.
          const id = Number(board.id);
          const cardHTML = ` 
                  <div class="col">
                      <div class="card h-100">
                          <img class="card-img-top" src="/img/info/${board.title}.jpg" alt="${board.title}" />
                          <div class="card-body text-center">
                              <h5 class="fw-bolder">${board.title}</h5>
                              <div class="d-flex justify-content-center small text-warning mb-2">
                                  <div class="bi-star-fill"></div>
                                  <div class="bi-star-fill"></div>
                                  <div class="bi-star-fill"></div>
                                  <div class="bi-star-fill"></div>
                                  <div class="bi-star-fill"></div>
                              </div>
                              <p class="text-muted">2016.12.02. ~ 2017.01.21.</p>
                          </div>
                          <div class="card-footer p-4 pt-0 border-top-0 bg-transparent text-center">
                              <a class="btn btn-outline-dark" href="../infoboard/readinfo?id=${id}&page=${criteria.page}">더보기</a>
                              <a class="btn mt-1" href="#" id="like">♡</a>
                          </div>
                      </div>
                  </div>
          `;
            console.log(board.id, board.title, board.type);
          // HTML 문자열을 DOM에 추가
          container.innerHTML += cardHTML;
          console.log('card', cardHTML);
        });
      })
      .catch(error => {
        console.log('Error:', error);
      });
    });
  });
});

