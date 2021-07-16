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
            `<div class="col-sm-4 mb-2">
                <div class="card h-100">
                    <div class="card-body bg-light">
                        <a class="card-title nounderline crop-text-1" href="${news.url}" target="_blank">${news.title}</a>
                    </div>
                    <div class="card-footer bg-transparent">
                        <div class="row pt-auto justify-content-evenly">
                            <div class="col pt-auto text-start"><small class="text-muted">${news.time_ago}</small></div>
                            ${news.user != null ? `<div class="col text-end"><small>${news.user}</small></div>` : ''}
                        </div>
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
            previous.setAttribute("disabled", "true");
            next.removeAttribute("disabled");
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
            previous.setAttribute("disabled", "true");
            next.removeAttribute("disabled");
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
            previous.setAttribute("disabled", "true")
            next.setAttribute("disabled","true");
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
        setActiveButtons();
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
        setActiveButtons();
    });
}

function removeActive() {
    let navLinks = document.querySelectorAll('.nav-link');
    for (let link of navLinks) {
        link.classList.remove('active');
    }
}

function setActiveButtons() {
    let next = document.getElementById('next');
    let previous = document.getElementById('previous');
    if (next.dataset.next === "11" || previous.dataset.category === "newest" && next.dataset.next === "8"){
        next.setAttribute("disabled", "true");
    } else {
        next.removeAttribute("disabled");
    }
    if (previous.dataset.prev === "0"){
        previous.setAttribute("disabled", "true");
    } else {
        previous.removeAttribute("disabled");
    }
}

initTopNewsButton();
initNewestButton();
initJobsButton();
getNews("top", "1");
initPreviousButton();
initNextButton();
setActiveButtons();
