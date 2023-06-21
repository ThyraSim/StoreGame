<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 2023-06-05
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            rel="stylesheet"
            crossorigin="anonymous"/>
    <meta charset="UTF-8">
    <title>Création de compte</title>
    <script>
        function showRegistrationForm() {
            var loginForm = document.getElementById("loginForm");
            var registrationForm = document.getElementById("registrationForm");
            var loginTitle = document.getElementById("loginTitle");
            var registrationTitle = document.getElementById("registrationTitle");

            loginForm.style.display = "none";
            registrationForm.style.display = "block";
            loginTitle.style.display = "none";
            registrationTitle.style.display = "block";
        }
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/magasin.jsp">Magasin</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/profile.jsp">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login.jsp">login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Pricing</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Dropdown link
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<h1 id="loginTitle">Connexion</h1>
<form id="loginForm" action="/LoginServlet" method="post">
    <label for="username">Nom d'utilisateur:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Mot de passe:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Se connecter">
    <br><br>
    <button type="button" onclick="showRegistrationForm()">S'inscrire</button>
    <c:choose>
        <c:when test="${not empty param.index}">
            <input type="hidden" name="index" value="${param.index}">
        </c:when>
    </c:choose>
</form>

<h1 id="registrationTitle" style="display: none;">Création de compte</h1>
<form id="registrationForm" action="/CreationCompteServlet" method="post" style="display: none;">
    <label for="username">Nom d'utilisateur:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="profileName">Nom de Profile:</label>
    <input type="text" id="profileName" name="profileName" required><br><br>

    <label for="password">Mot de passe:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="S'inscrire">
</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
<script src="scripts/ScriptFilter.js"></script>
</body>
</html>
