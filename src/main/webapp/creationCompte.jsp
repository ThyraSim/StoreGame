<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 2023-06-05
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<c:set scope="session" var="lang" value="${lang}"/>
<fmt:setLocale value="${lang}" />
<fmt:bundle basename="MessagesBundle">

    <head>
        <title>Title</title>
        <script src="scripts/scriptLangue.js" type="text/javascript"></script>
    </head>
    <body>
    <jsp:include page="navbar.jsp?lang=${lang}" />
    <jsp:include page="background.jsp"/>
    <div id="registrationContainer">
        <h1 id="registrationTitle"><fmt:message key="creation" /></h1>
        <form id="registrationForm" action="/CreationCompteServlet" method="post">
            <div class="form-group">
                <label for="username"><fmt:message key="userTableHeader"/>:</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="profileName"><fmt:message key="profileNameTableHeader" />:</label>
                <input type="text" id="profileName" name="profileName" required>
            </div>

            <div class="form-group">
                <label for="password"><fmt:message key="passwordTableHeader" />:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-group">
                <label for="nom"><fmt:message key="nomClientTableHeader" />:</label>
                <input type="text" id="nom" name="nom" required>
            </div>

            <div class="form-group">
                <label for="prenom"><fmt:message key="prenomClientTableHeader" />:</label>
                <input type="text" id="prenom" name="prenom" required>
            </div>

            <div class="form-group">
                <label for="physique"><fmt:message key="adressPhyTableHeader" />:</label>
                <input type="text" id="physique" name="physique" required>
            </div>

            <div class="form-group">
                <label for="email"><fmt:message key="adressEmTableHeader" />:</label>
                <input type="text" id="email" name="email" required>
            </div>

            <input class="btn btn-success" type="submit" value="<fmt:message key='register'/>">
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.2.3/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
    </body>
</fmt:bundle>
</html>
