function getNews(topic, number) {
    let url = "/api/" + topic + "?page=" + number
    fetch(url)
        .then(response => response.json())
        .then(data => fillPage(data));
}

function fillPage(data) {
    let row = document.getElementById('cards');
    row.innerHTML = "";
    for (let news of data) {
        let outerHtml =
            `<div class="col-sm-4">
            <div class="card dark bg-dark h-100">
            <div class="card-body">
            <a class="card-title text-primary nounderline" href="${news.url}">${news.title} </a>
            <div class="text-white">${news.time_ago}</div>
            ${news.user != null ? `<div class="text-white">${news.user}</div>` : ''}
            </div>
            </div>
            </div>`;
        row.insertAdjacentHTML("beforeend", outerHtml);
    }
}

function initTopNewsButton() {
    let topNews = document.getElementById('top-news');
    topNews.addEventListener("click", function () {
            let previous = document.getElementById('previous');
            let next = document.getElementById('next');
            previous.dataset.category = "top";
            previous.dataset.prev = "0";
            next.dataset.category = "top";
            next.dataset.next = "2";
            removeActive();
            topNews.classList.add('active');
            getNews("top", "1");
        }
    );

}

function initNewestButton() {
    let newest = document.getElementById('newest');
    newest.addEventListener("click", function () {
            let previous = document.getElementById('previous');
            let next = document.getElementById('next');
            previous.dataset.category = "newest";
            previous.dataset.prev = "0";
            next.dataset.category = "newest";
            next.dataset.next = "2";
            removeActive();
            newest.classList.add('active');
            getNews("newest", "1");
        }
    );
}

function initJobsButton() {
    let jobs = document.getElementById('jobs');
    jobs.addEventListener("click", function () {
            let previous = document.getElementById('previous');
            let next = document.getElementById('next');
            previous.dataset.category = "jobs";
            previous.dataset.prev = "0";
            next.dataset.category = "jobs";
            next.dataset.next = "2";
            removeActive();
            jobs.classList.add('active');
            getNews("jobs", "1");
        }
    );
}

function initPreviousButton() {
    let previous = document.getElementById('previous');
    let next = document.getElementById('next');
    previous.addEventListener("click", function () {
        let prevNum = previous.dataset.prev;
        if (prevNum !== "0" || previous.dataset.category !== "jobs") {
            let nextNum = next.dataset.next;
            previous.dataset.prev = (parseInt(prevNum) - 1).toString();
            next.dataset.next = (parseInt(nextNum) - 1).toString();
            let category = previous.dataset.category;
            getNews(category, prevNum);
        }
    });
}

function initNextButton() {
    let next = document.getElementById('next');
    let previous = document.getElementById('previous');
    next.addEventListener("click", function () {
        let nextNum = next.dataset.next;
        if (nextNum === "11" || previous.dataset.category === "jobs" || (previous.dataset.category === "newest" && nextNum === "8")) {
            //
        } else {
            next.dataset.next = (parseInt(nextNum) + 1).toString();
            let prevNum = previous.dataset.prev;
            previous.dataset.prev = (parseInt(prevNum) + 1).toString();
            let category = next.dataset.category;
            getNews(category, nextNum);
        }
    });
}

function removeActive() {
    let navLinks = document.querySelectorAll('.nav-link');
    for (let link of navLinks) {
        link.classList.remove('active');
    }
}

// window.onload = function () {
initTopNewsButton();
initNewestButton();
initJobsButton();
getNews("top", "1");
initPreviousButton();
initNextButton();
// }