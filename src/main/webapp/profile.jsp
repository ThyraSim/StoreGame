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
    <li>Jeu 1</li>
    <li>Jeu 2</li>
    <li>Jeu 3</li>
    <li>Jeu 4</li>
</ul>
</fmt:bundle>
</body>
</html>