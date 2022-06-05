<!DOCTYPE html>
<html lang="en">
<#global session=model["session"]>
<head>
    <title>Session</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script>
        function openChat() {
            window.open("/films/${session.film.id}/chat", "_self");
        }
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
    a {
        text-decoration: none;
    }

    hr {
        height: 3px;
        background-color: #5237d5;
        border: none;
        width: 100%;
        padding: 0;
        margin: 0 0 15px;
    }

    .film-container {
        display: flex;
        justify-content: left;
        align-items: flex-start;
    }
    .film-poster {
        background-color: #222222;
        display: flex;
        width: 150px;
        height: 300px;
    }
    .poster-img {
        margin: auto;
        max-width: 150px;
        max-height: 300px;
    }
    .film-info {
        margin: 0 0 0 20px;
        display: flex;
        flex-direction: column;
    }
    .film-title {
        font-size: 25pt;
        margin: 0 0 15px;
    }

    .additional-film-info {
        font-size: 13pt;
        margin: 0 0 0 5px;
        display: flex;
        flex-direction: row;
        overflow-x: auto;
    }
    .info-key {
        margin-right: 15px;
        min-width: 100px;
    }
    .label {
        color: #a4a4a4;
        margin: 6px 0;
    }
    .value {
        margin: 6px 0;
    }

</style>
<body>
<div class="container">
    <div style="display: flex; flex-direction: row" class="container-head">
        <a href="/admin/panel/halls" class="container-label">Halls</a>
        <a href="/admin/panel/films" class="container-label">Movies</a>
        <a href="/admin/panel/sessions" class="container-label">Sessions</a>
    </div>
    <hr>
    <div class="container-content">
        <div class="film-container">
            <div class="film-poster">
                <img class="poster-img" src="${session.film.posterUrl}">
            </div>
            <div class="film-info">
                <p class="film-title">${session.film.title}</p>
                <div class="additional-film-info">
                    <div class="info-key">
                        <p class="label">Description</p>
                        <p class="label">Year of release</p>
                        <p class="label">Duration</p>
                        <p class="label">Age restriction</p>
                        <p class="label">Hall ID</p>
                        <p class="label">Seats count</p>
                        <p class="label">Session start</p>
                        <p class="label">Chat</p>
                    </div>
                    <div class="info-value">
                        <p class="value">
                            <#if session.film.description?has_content>
                                ${session.film.description}
                            <#else>
                                -
                            </#if>
                        </p>
                        <p class="value">${session.film.yearOfRelease}</p>
                        <p class="value">${session.film.duration}</p>
                        <p class="value">${session.film.ageRestrictions}+</p>
                        <p class="value">${session.hall.id}</p>
                        <p class="value">${session.hall.seatsCount}</p>
                        <p class="value">${(session.sessionDateTimeFrom).format('dd.MM.yyyy HH:mm')}</p>
                        <p class="value" onclick="openChat()">
                            <i style="color: #5237d5; cursor: pointer" class="material-icons" id="chat">chat</i>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>