<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 2023-06-05
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:bundle basename="MessagesBundle">
<head>
    <title><fmt:message key="userProfileTitle" /></title>
    <link
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            rel="stylesheet"
            crossorigin="anonymous"/>

</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/MagasinServlet"><fmt:message key="magasin"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/ProfileServlet"><fmt:message key="profile"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/LoginServlet"><fmt:message key="login"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="Price"/></a>
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
<div class="container">
    <div class="row my-4">
        <div class="col-md-4">
            <img src="${pageContext.request.contextPath}/image/aoruschibi.PNG" class="rounded-circle" alt="Profile picture">
        </div>
        <div class="col-md-8">
            <h2>${compte.profileName}</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h2><fmt:message key="listeJeuTitle"/></h2>
            <h3><fmt:message key="favoritTitle"/>:</h3>
            <c:if test="${not empty commandes}">
                <ul class="list-group">
                    <c:forEach var="commande" items="${commandes}">
                        <c:if test="${not empty commande.jeux}">
                            <c:forEach var="jeu" items="${commande.jeux}">
                                <li class="list-group-item">${jeu.nomJeu} | <fmt:message key="${jeu.genre}" /> | ${jeu.description}</li>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
</body>
</fmt:bundle>
</html>