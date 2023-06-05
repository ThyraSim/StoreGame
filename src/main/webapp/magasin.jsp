<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 2023-06-05
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>

<head>
    <title>Title</title>
</head>
<body>


<h1>Liste de jeux</h1>

<c:forEach var="jeu" items="${catalog}">
    <div>
        <p>${jeu.nomJeu} | ${jeu.prix} | ${jeu.genre} | ${jeu.description}</p>
        <form action="MagasinServlet" method="get">
            <input type="hidden" name="index" value="${jeu.idJeu}">
            <input type="hidden" name="action" value="ACHETE">
            <input type="submit" value="Acheté">
        </form>
    </div>
</c:forEach>

</body>

</div>


</body>
</html>