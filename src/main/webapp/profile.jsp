<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 2023-06-05
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Profil utilisateur</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"
    ></script>
    <style>
        .profile {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .profile-image {
            width: 50px;
            height: 50px;
            background-color: red;
            border-radius: 50%;
            margin-right: 10px;
        }
    </style>
</head>
<body>
<fmt:bundle basename="MessagesBundle">
<div class="profile">
    <div class="profile-image"></div>
    <h2>Nom de l'utilisateur</h2>
</div>
<h3><fmt:message key="favoritTitle"/>:</h3>
    <ul>
        <c:forEach var="jeu" items="${listeJeuOwned}">
            <li>${jeu.nomJeu} | ${jeu.prix} | ${jeu.genre} | ${jeu.description}</li>

        </c:forEach>
    </ul>
</fmt:bundle>
</body>
</html>