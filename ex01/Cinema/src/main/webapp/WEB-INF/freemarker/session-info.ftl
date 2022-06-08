<!DOCTYPE html>
<html lang="en">
<head>
    <title>Session</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
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
        margin-top: 5px;
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
        flex-direction: column;
        overflow-x: auto;
    }
    .additional-film-info-item {
        display: flex;
        flex-direction: row;
        margin-bottom: 10px;
    }
    .info-key {
        margin-right: 15px;
        min-width: 150px;
    }
    .label {
        color: #a4a4a4;
        margin: 0;
    }
    .value {
        margin: 0;
    }

</style>
<body>
<#global session=model["session"]>
<div class="container">
    <div style="display: flex; flex-direction: row" class="container-head">
        <a href="/admin/panel/halls" class="container-label">Halls</a>
        <a href="/admin/panel/films" class="container-label">Movies</a>
        <a href="/admin/panel/sessions" class="container-label">Sessions</a>
    </div>
    <hr>
    <div class="container-content">
        <p class="film-title">${session.film.title}</p>
        <div class="film-container">
            <div class="film-poster">
                <img class="poster-img" src="${session.film.posterUrl}">
            </div>
            <div class="film-info">
                <div class="additional-film-info">
                    <div class="additional-film-info-item">
                        <p class="info-key label">Description</p>
                        <p class="value">
                            <#if session.film.description?has_content>
                                ${session.film.description}
                            <#else>
                                -
                            </#if>
                        </p>
                    </div>

                    <div class="additional-film-info-item">
                        <p class="info-key label">Year of release</p>
                        <p class="value">${session.film.yearOfRelease}</p>
                    </div>
                    <div class="additional-film-info-item">
                        <p class="info-key label">Duration</p>
                        <p class="value">${session.film.duration}</p>
                    </div>
                    <div class="additional-film-info-item">
                        <p class="info-key label">Age restriction</p>
                        <p class="value">${session.film.ageRestrictions}+</p>
                    </div>
                    <div class="additional-film-info-item">
                        <p class="info-key label">Hall ID</p>
                        <p class="value">${session.hall.id}</p>
                    </div>
                    <div class="additional-film-info-item">
                        <p class="info-key label">Seats count</p>
                        <p class="value">${session.hall.seatsCount}</p>
                    </div>
                    <div class="additional-film-info-item">
                        <p class="info-key label">Session start</p>
                        <p class="value">${(session.sessionDateTimeFrom).format('HH:mm dd.MM.yyyy')}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>