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
<c:set scope="session" var="lang" value="${lang}"/>
<fmt:setLocale value="${lang}" />
<fmt:bundle basename="MessagesBundle">
    <head>
        <title>Chaos Games</title>

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
        <script src="scripts/scriptLangue.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="style/style.css">
    </head>
    <jsp:include page="navbar.jsp?lang=${lang}" />
    <jsp:include page="background.jsp"/>
    <body>
    <div id="loginContainer">
        <h1 id="loginTitle"><fmt:message key="login"/></h1>
        <form id="loginForm" action="/LoginServlet" method="post">
            <div class="form-group">
                <label for="username"><fmt:message key="userTableHeader"/>:</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password"><fmt:message key="passwordTableHeader"/>:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <input class="btn btn-outline-warning mt-3" type="submit" value="<fmt:message key="login"/>">
            <br>
            <a class="btn btn-success" type="button"  href="creationCompte.jsp"><fmt:message key="register"/></a>
            <c:choose>
                <c:when test="${not empty param.index}">
                    <input type="hidden" name="index" value="${param.index}">
                </c:when>
            </c:choose>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.2.3/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

    </body>
</fmt:bundle>
</html>
