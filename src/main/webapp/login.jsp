<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 2023-06-05
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<fmt:bundle basename="MessagesBundle">
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
    <link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<jsp:include page="background.jsp"/>
<h1 id="loginTitle"><fmt:message key="login"/></h1>
<form id="loginForm" action="/LoginServlet" method="post">
    <label for="username"><fmt:message key="userTableHeader"/>:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password"><fmt:message key="passwordTableHeader"/>:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="<fmt:message key="login"/>">
    <br><br>
    <a type="button" href="creationCompte.jsp"><fmt:message key="register"/></a>
    <c:choose>
        <c:when test="${not empty param.index}">
            <input type="hidden" name="index" value="${param.index}">
        </c:when>
    </c:choose>
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
</fmt:bundle>
</html>
