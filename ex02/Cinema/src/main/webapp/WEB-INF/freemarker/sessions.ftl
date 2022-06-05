<!DOCTYPE html>
<html lang="en">
<head>
    <title>Movie sessions</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script>
        var prevValue = ''

        var tableHeader = "<th>ID</th>" +
            "<th>Hall</th>" +
            "<th>Movie</th>" +
            "<th>Session date, from</th>" +
            "<th>Session date, to</th>" +
            "<th>Ticket cost, RUB</th>"

        function search(title) {
            $.get("/admin/panel/sessions/search?filmName=" + title, function (data) {
                reDrawTable(data);
            });
        }

        function onClick() {
            search(document.getElementById('search').value);
        }

        function openSession(sessionId) {
            window.open("/sessions/" + sessionId, "_self");
        }

        function reDrawTable(sessions) {
            table = document.getElementById('sessions-table');
            $("#sessions-table tr").remove();

            if (sessions.length > 0) {
                var headerTr = table.insertRow(-1);
                headerTr.innerHTML = tableHeader;

                for (let i = 1; i < sessions.length + 1; ++i) {
                    var session = sessions[i - 1];
                    var rowTr = table.insertRow(i);
                    rowTr.setAttribute('class', 'select-session');
                    rowTr.setAttribute('id', 'select-session');
                    rowTr.setAttribute('onclick', 'openSession(' + session.id + ')');

                    var idTd = rowTr.insertCell(0);
                    idTd.innerHTML = session.id;

                    var hallTd = rowTr.insertCell(1);
                    hallTd.innerHTML = session.hall.id;

                    var movieTd = rowTr.insertCell(2);
                    movieTd.innerHTML = '<div class="film-container">' +
                        '<img class="film-poster" src="' + session.film.posterUrl + '">' +
                        '<p class="film-title">' + session.film.title + ' (' + session.film.ageRestrictions + '+)</p>' +
                        '</div>';

                    var fromTd = rowTr.insertCell(3);
                    fromTd.innerHTML = session.sessionDateTimeFrom;

                    var toTd = rowTr.insertCell(4);
                    toTd.innerHTML = session.sessionDateTimeTo;

                    var costTd = rowTr.insertCell(5);
                    costTd.innerHTML = session.ticketCost;
                }
            }
        }

        $(document).ready(function () {
            $('#search').on('keyup', function () {
                var title = $(this).val();

                if (prevValue !== title) {
                    search(title);
                    prevValue = title;
                }
            });
        });
    </script>
</head>
<style>
    body {
        height: 100%;
        width: 100%;
        font-family: Verdana, sans-serif;
        margin: 0;
    }
    .container {
        width: 1200px;
        height: calc(100% - 100px);
        display: flex;
        flex-direction: column;
        margin: 0 auto;
        padding: 50px 0;
    }

    .container-content {
        width: 100%;
        display: flex;
        flex-direction: row;
        justify-content: center;
    }

    .add-session-form {
        width: 250px;
        margin-right: 50px;
        display: flex;
        justify-content: left;
    }
    input, select {
        height: 25px;
        width: calc(100% - 10px);
        padding: 5px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }
    input:focus {
        background-color: #ddd;
        outline: none;
    }
    select {
        height: 35px;
        max-height: 100px;
        width: 100%;
    }
    textarea {
        width: calc(100% - 10px);
        resize: none;
        padding: 5px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }
    textarea:focus {
        background-color: #ddd;
        outline: none;
    }
    .createbtn {
        background-color: #5237d5;
        color: white;
        border: none;
        cursor: pointer;
        width: 100%;
        height: 35px;
        opacity: 0.9;
        font-size: 10pt;
    }
    .createbtn:hover {
        opacity:1;
        cursor: pointer;
    }

    .session-search {
        width: calc(100% - 300px);
        height: 100%;
        display: flex;
        flex-direction: column;
    }
    .search-form {
        width: 100%;
        margin-bottom: 20px;
        position: relative;
        height: 34px;
    }
    .sessions-list {
        width: 100%;
        height: 100%;
        overflow-y: auto;
    }
    .container-label {
        color: #5237d5;
        padding: 5px 10px;
        margin: 0;
        font-size: 13pt;
        text-align: left;
        cursor: pointer;
    }
    .current-container-label {
        color: #ffffff;
        background-color: #5237d5;
    }
    .film-poster {
        width: 50px;
        height: 50px;
    }
    .film-title {
        margin: 0 0 0 5px;
    }
    .film-container {
        display: flex;
        justify-content: left;
        align-items: center;
    }
    table {
        width: 100%;
        overflow-y: auto;
        font-size: 10pt;
        border-collapse: collapse;
    }
    hr {
        height: 3px;
        background-color: #5237d5;
        border: none;
        width: 100%;
        padding: 0;
        margin: 0 0 15px;
    }
    td, th {
        border: 1px solid #dddddd;
        text-align: center;
        padding: 7px;
        height: 50px;
    }
    th {
        background-color: #dddddd;
        height: 20px;
    }
    a {
        text-decoration: none;
    }
    .search-input, .search-input:focus {
        display: block;
        width: calc(100% - 4px);
        height: 34px;
        line-height: 34px;
        padding: 0;
        margin: 0;
        border: 2px solid #5237d5;
        outline: none;
        overflow: hidden;
        border-radius: 18px;
        background-color: rgb(255, 255, 255);
        text-indent: 15px;
        font-size: 14px;
        color: #222;
    }
    .search-input:hover, .search-input:focus {
        border: 3px solid #5237d5;
        width: calc(100% - 6px);
        height: 32px;
        line-height: 32px;
    }
    .searchbtn {
        border: 0;
        outline: 0;
        position: absolute;
        top: 5px;
        right: 15px;
        background-color: white;
        cursor: pointer;
        color: #5237d5;
    }
    .select-session:hover {
        cursor: pointer;
        background-color: #eeeeee;
    }
</style>
<body>
<div class="container">
    <div style="display: flex; flex-direction: row" class="container-head">
        <a href="/admin/panel/halls" class="container-label">Halls</a>
        <a href="/admin/panel/films" class="container-label">Movies</a>
        <a href="/admin/panel/sessions" class="container-label current-container-label">Sessions</a>
    </div>
    <hr>
    <div class="container-content">
        <div class="add-session-form", id="session-list">
            <form action="/admin/panel/sessions" method="post">
                <label for="film"><b style="font-size: 10pt">Movie</b></label>
                <select autocomplete="off" name="filmId" id="film" required>
                    <option style="display:none" selected disabled value>Select movie</option>
                    <#list model["films"] as film>
                        <option value="${film.id}">${film.title}</option>
                    </#list>
                </select>

                <label for="hall"><b style="font-size: 10pt">Hall</b></label>
                <select autocomplete="off" name="hallId" id="hall" required>
                    <option style="display:none" selected disabled value>Select hall</option>
                    <#list model["halls"] as hall>
                        <option value="${hall.id}">${hall.id} - ${hall.seatsCount} seats</option>
                    </#list>
                </select>

                <label for="ticket-cost"><b style="font-size: 10pt">Ticket cost, RUB</b></label>
                <input autocomplete="off" type="text" placeholder="Enter cost" name="ticketCost" id="ticket-cost" required>

                <label for="session-date"><b style="font-size: 10pt">Session date</b></label>
                <input autocomplete="off" type="datetime-local" placeholder="Select date" name="sessionDateTime" id="session-date" required
                       pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}">

                <button type="submit" class="createbtn" value="/admin/panel/sessions">Create session</button>
            </form>
        </div>
        <div class="session-search">
            <div class="search-form">
                <input autocomplete="off" class="search-input" type="text" name="filmName" id="search" placeholder="Enter movie title">
                <button class="searchbtn" onclick="onClick()"><i class="material-icons">search</i></button>
            </div>
            <div class="sessions-list">
                <table class="session-table" id="sessions-table">
                    <tr>
                        <th>ID</th>
                        <th>Hall</th>
                        <th>Movie</th>
                        <th>Session date, from</th>
                        <th>Session date, to</th>
                        <th>Ticket cost, RUB</th>
                    </tr>
                    <#list model["sessions"] as session>
                        <tr class="select-session" id="select-session" onclick="openSession(${session.id})">
                            <td>${session.id}</td>
                            <td>${session.hall.id}</td>
                            <td>
                                <div class="film-container">
                                    <img class="film-poster" src="${session.film.posterUrl}">
                                    <p class="film-title">${session.film.title} (${session.film.ageRestrictions}+)</p>
                                </div>
                            </td>
                            <td>${(session.sessionDateTimeFrom).format('HH:mm dd.MM.yyyy')}</td>
                            <td>${(session.sessionDateTimeTo).format('HH:mm dd.MM.yyyy')}</td>
                            <td>${session.ticketCost}</td>
                        </tr>
                    </#list>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>