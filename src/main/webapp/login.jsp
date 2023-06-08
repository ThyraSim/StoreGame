<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 2023-06-05
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
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
<h1 id="loginTitle">Connexion</h1>
<form id="loginForm" action="/LoginServlet" method="post">
    <label for="username">Nom d'utilisateur:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Mot de passe:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Se connecter">
    <br><br>
    <button type="button" onclick="showRegistrationForm()">S'inscrire</button>
</form>

<h1 id="registrationTitle" style="display: none;">Création de compte</h1>
<form id="registrationForm" action="registerProcess.jsp" method="post" style="display: none;">
    <label for="username">Nom d'utilisateur:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="profileUser">Nom de Profile:</label>
    <input type="text" id="profileUser" name="profileUser" required><br><br>

    <label for="password">Mot de passe:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="S'inscrire">
</form>
</body>
</html>
