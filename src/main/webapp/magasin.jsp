<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: simbe--%>
<%--  Date: 2023-06-05--%>
<%--  Time: 13:55--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>

<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html>--%>

<%--&lt;%&ndash;<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>&ndash;%&gt;--%>

<%--<head>--%>

<%--    <title>Title</title>--%>
<%--    <link--%>
<%--            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"--%>
<%--            rel="stylesheet"--%>
<%--            crossorigin="anonymous"/>--%>
<%--</head>--%>
<%--<body>--%>
<%--<fmt:bundle basename="MessagesBundle">--%>

<%--<h1><fmt:message key="listeJeuTitle" /></h1>--%>

<%--<c:forEach var="jeu" items="${catalog}">--%>
<%--    <div>--%>
<%--        <p>${jeu.nomJeu} | ${jeu.prix} | ${jeu.genre} | ${jeu.description}</p>--%>
<%--        <form action="MagasinServlet" method="POST">--%>
<%--            <input type="hidden" name="index" value="${jeu.idJeu}">--%>
<%--            <input type="hidden" name="action" value="ACHETE">--%>
<%--            <input type="submit" value="<fmt:message key='buyCommand' />">--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</c:forEach>--%>

<%--<div>--%>
<%--    <h1><fmt:message key="panierTitle" /></h1>--%>
<%--    <ul>--%>
<%--        <c:forEach var="jeu" items="${listeJeux}">--%>
<%--            <li>${jeu.nomJeu} | ${jeu.prix} | ${jeu.genre} | ${jeu.description}</li>--%>
<%--            <form action="MagasinServlet" method="POST">--%>
<%--                <input type="hidden" name="idJeu" value="${jeu.idJeu}">--%>
<%--                <input type="hidden" name="action" value="DELETE">--%>
<%--                <input type="submit" value="<fmt:message key='removeCommand' />">--%>
<%--            </form>--%>
<%--        </c:forEach>--%>
<%--    </ul>--%>
<%--    <c:if test="${noOwned}">--%>
<%--        <form action="CheckOutServlet" method="POST">--%>
<%--            <input type="hidden" name="liste" value="${compteId}">--%>
<%--            <input type="hidden" name="action" value="SELF">--%>
<%--            <input type="submit" value="<fmt:message key='selfCommand' />">--%>
<%--        </form>--%>
<%--    </c:if>--%>
<%--    <form action="CheckOutServlet" method="POST">--%>
<%--        <input type="hidden" name="liste" value="${compteId}">--%>
<%--        <input type="hidden" name="action" value="GIFT">--%>
<%--        <input type="submit" value="<fmt:message key='giftCommand' />">--%>
<%--    </form>--%>
<%--</div>--%>

<%--</fmt:bundle>--%>

<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>--%>
<%--<script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>--%>

<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>
    <title>Title</title>
    <link
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            rel="stylesheet"
            crossorigin="anonymous"/>
</head>
<%--<body>--%>
<%--<fmt:bundle basename="MessagesBundle">--%>
<%--    <div class="container">--%>
<%--        <h1 class="text-center my-3"><fmt:message key="listeJeuTitle" /></h1>--%>

<%--        <div class="row">--%>
<%--            <c:forEach var="jeu" items="${catalog}">--%>
<%--                <div class="col-md-4 mb-4">--%>
<%--                    <div class="card">--%>
<%--                        <div class="card-body">--%>
<%--                            <h5 class="card-title">${jeu.nomJeu}</h5>--%>
<%--                            <p class="card-text">${jeu.description}</p>--%>
<%--                            <p><strong>Genre:</strong> ${jeu.genre}</p>--%>
<%--                            <p><strong>Price:</strong> ${jeu.prix}</p>--%>
<%--                            <form action="MagasinServlet" method="POST">--%>
<%--                                <input type="hidden" name="index" value="${jeu.idJeu}">--%>
<%--                                <input type="hidden" name="action" value="ACHETE">--%>
<%--                                <button type="submit" class="btn btn-primary"><fmt:message key='buyCommand' /></button>--%>
<%--                            </form>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>
<body>
<fmt:bundle basename="MessagesBundle">

    <div class="container">
        <div class="row">
            <h1><fmt:message key="FilterHeader"/></h1>

            <select id="genre" class="form-control form-control-lg">
                <option value=""><fmt:message key="FilterAllGenre"/></option>
                <c:forEach var="genre" items="${genreList}" varStatus="loop">
                    <option>${genre.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="row">
            <label> <fmt:message key="FilterByName"/></label>
            <input type="text" id="gameNameInput">
            <button id="btnSearchByName"><fmt:message key="FilterSearch"/></button>


        </div>

        <div class="row">
            <select id="priceFilter">
                <c:forEach begin="0" end="${maxPrice/10}" var="i">
                    <c:set var="minPriceRange" value="${i*10+1}" />
                    <c:set var="maxPriceRange" value="${(i+1)*10}" />
                    <option value="${minPriceRange}-${maxPriceRange}" minPriceRange="${minPriceRange}" maxPriceRange="${maxPriceRange}">
                            ${minPriceRange}-${maxPriceRange} $
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="container">
        <h1 class="text-center my-3"><fmt:message key="listeJeuTitle"/></h1>

        <div class="row">
            <c:forEach var="jeu" items="${catalog}">
                <div name="Card-Game" genre="${jeu.genre}" gameName="${jeu.nomJeu}" gamePrice="${jeu.prix}"
                     class="col-md-12 mb-4">
                    <div class="card text-center">
                        <div class="card-header">
                            <h5 class="card-title">${jeu.nomJeu}</h5>
                        </div>
                        <div class="card-body">
                            <p class="card-text">${jeu.description}</p>
                            <p><strong>Genre:</strong><fmt:message key="${jeu.genre}"/></p>
                            <p><strong><fmt:message key="Price"/></strong> ${jeu.prix}</p>
                            <form action="AcheteServlet" method="POST">
                                <input type="hidden" name="index" value="${jeu.idJeu}">
                                <input type="hidden" name="action" value="ACHETE">
                                <div class="card-footer text-muted">
                                    <button type="submit" class="btn btn-primary"><fmt:message
                                            key='buyCommand'/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="mt-5">
            <c:if test="${not empty panier.jeux}">
                <h2><fmt:message key="panierTitle"/></h2>
                <ul class="list-group">
                    <c:forEach var="jeu" items="${listePanier}">
                        <li class="list-group-item">${jeu.nomJeu} | ${jeu.prix} | <fmt:message key="${jeu.genre}"/>
                            | ${jeu.description}
                            <form action="DeleteServlet" method="POST" class="float-right">
                                <input type="hidden" name="idJeu" value="${jeu.idJeu}">
                                <input type="hidden" name="action" value="DELETE">
                                <button type="submit" class="btn btn-danger"><fmt:message key='removeCommand'/></button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${noOwned}">
                    <form action="CheckOutServlet" method="POST" class="mt-4">
                        <input type="hidden" name="liste" value="${compteId}">
                        <input type="hidden" name="action" value="SELF">
                        <button type="submit" class="btn btn-success"><fmt:message key='selfCommand'/></button>
                    </form>
                </c:if>
                <form action="CheckOutServlet" method="POST" class="mt-4">
                    <input type="hidden" name="liste" value="${compteId}">
                    <input type="hidden" name="action" value="GIFT">
                    <button type="submit" class="btn btn-warning"><fmt:message key='giftCommand'/></button>
                </form>
            </c:if>
        </div>
    </div>
</fmt:bundle>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
<script src="scripts/ScriptFilter.js"></script>


</body>
</html>
