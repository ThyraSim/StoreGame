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
<body>
<fmt:bundle basename="MessagesBundle">
    <jsp:include page="navbar.jsp"/>

                                    <%--    SECTION DES FILTRES --%>
    <div class="container">

            <%--FILTRE DU GENRE--%>
        <div class="row">
            <h1><fmt:message key="FilterHeader"/></h1>

            <select id="cbGenre" class="form-control form-control-lg">
                <option value=""><fmt:message key="FilterAllGenre"/></option>
                <c:forEach var="genre" items="${genreList}" varStatus="loop">
                    <option value="${genre.name}"><fmt:message key="${genre.name}"/></option>
                </c:forEach>
            </select>
        </div>

            <%--FILTRE DU NOM--%>
        <div class="row">
            <label> <fmt:message key="FilterByName"/></label>
            <input type="text" id="gameNameInput">
            <button id="btnSearchByName"><fmt:message key="FilterSearch"/></button>
        </div>

            <%--FILTRE DU PRIX--%>

        <div class="row">
            <select id="priceFilter">
                <option minPriceRange="0" maxPriceRange="${maxPrice}"><fmt:message key="AllPrice"/></option>
                <c:forEach begin="0" end="${maxPrice/10}" var="i">
                    <c:set var="minPriceRange" value="${i*10+1}"/>
                    <c:set var="maxPriceRange" value="${(i+1)*10}"/>
                    <option value="${minPriceRange}-${maxPriceRange}" minPriceRange="${minPriceRange}"
                            maxPriceRange="${maxPriceRange}">
                            ${minPriceRange}-${maxPriceRange} $
                    </option>
                </c:forEach>
            </select>
        </div>

            <%--FILTRE POUR AFFICHER LES JEUX POSSEDÉS--%>

            <%--on va déterminer un boolean qui sera utile pour afficher ou cacher le filtre selon si nous sommes connecté à un compte--%>
                <c:choose>
                    <c:when test="${empty sessionScope.loggedInAccount}">
                        <input name="loggedInAccountFlag" type="hidden" value="false">
                    </c:when>
                    <c:otherwise>
                        <input name="loggedInAccountFlag" type="hidden" value="true">
                    </c:otherwise>
                </c:choose>

<%--                Par défaut les jeu posséder ne sont pas afficher, selon la boolean loggedInAccountFlag on va afficher ou non.--%>
        <div class="row" id="filterAllGameRow" style="display: none;">
                     <label><fmt:message key="FilerAllGame"/></label>
            <input type="checkbox" id="chkShowOwnedGame">
        </div>

                    <%--    Bouton pour remettre les filtres de base--%>
        <div>
            <button id="btnResetFilter" ><fmt:message key="ResetFilter"/></button>
        </div>


    </div>




    <%--    AFFICHER LE CATALOG DE JEU ( par défaut catalog exclu les jeux du panier et ceux  déjà posséder--%>
    <div class="container">
        <h1 class="text-center my-3"><fmt:message key="listeJeuTitle"/></h1>

        <div class="row">
            <c:forEach var="jeu" items="${catalog}">
                <c:set var="flagOwned" value="false"/>


                <%--            Parcour la liste des jeux posséder pour set un flag si deja posséer--%>
                <c:forEach var="ownedGame" items="${owned}">
                    <c:if test="${ownedGame.idJeu eq jeu.idJeu}">
                        <c:set var="flagOwned" value="true"/>

                    </c:if>
                </c:forEach>

                <div name="Card-Game" genre="${jeu.genre}" gameName="${jeu.nomJeu}" gamePrice="${jeu.prix}"
                     gameOwned="${flagOwned}"
                     class="col-md-12 mb-4">
                    <div class="card text-center">
                        <div class="card-header">
                            <h5 class="card-title">${jeu.nomJeu}</h5>
                        </div>
                        <div class="card-body">
                            <p class="card-text">${jeu.description}</p>
                            <p><strong>Genre:</strong><fmt:message key="${jeu.genre}"/></p>
                            <p><strong><fmt:message key="Price"/></strong> ${jeu.prix}$</p>
                            <form action="AcheteServlet" method="POST">
                                <input type="hidden" name="index" value="${jeu.idJeu}">
                                <input type="hidden" name="action" value="ACHETE">


                                    <%--                                Valeur de filtre enregistrer--%>
                                <input type="hidden" name="genreFilter" value="${genreFilterValue}">
                                <input type="hidden" name="gameNameFilter" value="${gameNameFilterValue}">
                                <input type="hidden" name="priceFilter" value="${priceFilterValue}">
                                <input type="hidden" name="showOwnedGamesFilter" value="${showOwnedGamesFilterValue}">


                                <div class="card-footer text-muted">
                       <%-- Lorsque la checkbox "chkShowOwnedGame" est a true, le catalog inclus maintenant les jeux deja posseder--%>
                                    <c:if test="${flagOwned eq true}">
                                        <p><fmt:message key="AlreadyPossessed"/></p>
                                    </c:if>

                                    <button type="submit" class="btn btn-primary"><fmt:message
                                            key='buyCommand'/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>


            <%--        PANIER--%>
        <div class="mt-5">
            <c:if test="${not empty panier.jeux}">
                <h2><fmt:message key="panierTitle"/></h2>
                <ul class="list-group">
                    <c:forEach var="jeu" items="${listePanier}">
                        <li class="list-group-item">${jeu.nomJeu} | ${jeu.prix}$ | <fmt:message key="${jeu.genre}"/>
                            | ${jeu.description}
                            <form action="DeleteServlet" method="POST" class="float-right">
                                <input type="hidden" name="idJeu" value="${jeu.idJeu}">
                                <input type="hidden" name="action" value="DELETE">
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
    </div>
</fmt:bundle>

<script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="scripts/ScriptFilter.js"></script>


</body>
</html>
