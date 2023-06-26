
<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 6/26/2023
  Time: 2:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <link
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            rel="stylesheet"
            crossorigin="anonymous"/>
    <script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<fmt:bundle basename="MessagesBundle">
    <jsp:include page="navbar.jsp"/>
    <div class="mt-5">
        <c:if test="${not empty panier.jeux}">
            <h2><fmt:message key="panierTitle"/></h2>
            <ul class="list-group">
                <c:forEach var="jeu" items="${listePanier}">
                    <li class="list-group-item">${jeu.nomJeu} | ${jeu.prix}$     | <fmt:message key="${jeu.genre}"/>
                        | ${jeu.description}
                        <form action="DeleteServlet" method="POST" class="float-right">
                            <input type="hidden" name="idJeu" value="${jeu.idJeu}">
                            <input type="hidden" name="source" value="panier">
                            <button type="submit" class="btn btn-danger"><fmt:message key='removeCommand'/></button>
                        </form>
                    </li>
                </c:forEach>
            </ul>

            <%--Dans la servlet on associe une valeur bolean "noOwned" qui est a false  si un produit du panier est possédé par l'utilisateur--%>
            <%--Ainsi le bouton pour acheter à soi-même est désactiver car seulement 1 copie de jeu par compte--%>
            <c:if test="${noOwned}">
                <form action="CheckOutServlet" method="POST" class="mt-4">
                    <input type="hidden" name="liste" value="${compteId}">
                    <input type="hidden" name="action" value="SELF">
                    <button type="submit" class="btn btn-success"><fmt:message key='selfCommand'/></button>
                </form>
            </c:if>
            <%--Le bouton acheté pour un cadeau, c'est-à-dire donnée à un autre compte --%>
            <form action="CheckOutServlet" method="POST" class="mt-4">
                <input type="hidden" name="liste" value="${compteId}">
                <input type="hidden" name="action" value="GIFT">
                <button type="submit" class="btn btn-warning"><fmt:message key='giftCommand'/></button>
            </form>
        </c:if>
    </div>
</fmt:bundle>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="scripts/ScriptFilter.js"></script>
</body>
</html>
