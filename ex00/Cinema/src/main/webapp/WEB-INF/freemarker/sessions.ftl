<!DOCTYPE html>
<html>
<head>
    <title>Movie sessions</title>
</head>
<style>
    body {
        height: 100%;
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
    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
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

    .sessions-list {
        width: calc(100% - 300px);
        display: flex;
        flex-direction: column;
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
    table {
        font-size: 10pt;
        border-collapse: collapse;
    }
    hr {
        height: 2px;
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
    }
    th {
        background-color: #dddddd;
    }
    a {
        text-decoration: none;
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
        <div class="add-session-form">
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
        <div class="sessions-list">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Hall</th>
                    <th>Movie</th>
                    <th>Session date, from</th>
                    <th>Session date, to</th>
                    <th>Ticket cost, RUB</th>
                </tr>
                <#list model["sessions"] as session>
                    <tr>
                        <td>${session.id}</td>
                        <td>${session.hall.id}</td>
                        <td>
                            <div style="display: flex; justify-content: left; align-items: center">
                                <img style="width: 50px; height: 50px;" src="${session.film.posterUrl}">
                                <p style="margin: 0 0 0 5px;">${session.film.title} (${session.film.ageRestrictions}+)</p>
                            </div>
                        </td>
                        <td>${session.sessionDateTimeFrom.format('HH:mm dd.MM.yyyy')}</td>
                        <td>${session.sessionDateTimeTo.format('HH:mm dd.MM.yyyy')}</td>
                        <td>${session.ticketCost}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
</div>
</body>
</html>