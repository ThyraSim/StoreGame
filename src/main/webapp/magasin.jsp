<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 2023-06-05
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>

<head>
    <title>Title</title>
</head>
<body>
<fmt:bundle basename="MessagesBundle">

<h1><fmt:message key="listeJeuTitle" /></h1>

<c:forEach var="jeu" items="${catalog}">
    <div>
        <p>${jeu.nomJeu} | ${jeu.prix} | ${jeu.genre} | ${jeu.description}</p>
        <form action="MagasinServlet" method="POST">
            <input type="hidden" name="index" value="${jeu.idJeu}">
            <input type="hidden" name="action" value="ACHETE">
            <input type="submit" value="<fmt:message key='buyCommand' />">
        </form>
    </div>
</c:forEach>

<div>
    <h1><fmt:message key="panierTitle" /></h1>
    <ul>
        <c:forEach var="bibliotheque" items="${panier}">
            <li>${bibliotheque.jeu.nomJeu} | ${bibliotheque.jeu.prix} | ${bibliotheque.jeu.genre} | ${bibliotheque.jeu.description}</li>
            <form action="MagasinServlet" method="POST">
                <input type="hidden" name="idBiblio" value="${bibliotheque.idBiblio}">
                <input type="hidden" name="action" value="DELETE">
                <input type="submit" value="<fmt:message key='removeCommand' />">
            </form>
        </c:forEach>
    </ul>
    <c:if test="${noOwned}">
        <form action="MagasinServlet" method="POST">
            <input type="hidden" name="liste" value="${compteId}">
            <input type="hidden" name="action" value="SELF">
            <input type="submit" value="<fmt:message key='selfCommand' />">
        </form>
    </c:if>
    <form action="MagasinServlet" method="POST">
        <input type="hidden" name="liste" value="${compteId}">
        <input type="hidden" name="action" value="GIFT">
        <input type="submit" value="<fmt:message key='giftCommand' />">
    </form>
</div>

</fmt:bundle>

</body>
</html>