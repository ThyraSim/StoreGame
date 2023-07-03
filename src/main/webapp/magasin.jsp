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
    <script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="scripts/scriptLangue.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>
<c:set var="lang" value="${not empty param.lang ? param.lang : pageContext.request.locale.language}"/>
<c:set scope="session" var="lang" value="${lang}"/>
<fmt:setLocale value="${lang}"/>
<fmt:bundle basename="MessagesBundle">
<jsp:include page="navbar.jsp?lang=${lang}"/>
<jsp:include page="background.jsp"/>
    <%--    SECTION DES FILTRES --%>
<div class="container">
    <h1><fmt:message key="FilterHeader"/></h1>
        <%--FILTRE DU GENRE--%>
    <div class="row">
        <div class="col">
            <label for="cbGenre"><fmt:message key="LabelFilterGenre"/></label>
            <select id="cbGenre" class="form-control form-control-lg text-center p-1 mb-1">
                <option value=""><fmt:message key="FilterAllGenre"/></option>
                <c:forEach var="genre" items="${genreList}" varStatus="loop">
                    <option value="${genre.name}"><fmt:message key="${genre.name}"/></option>
                </c:forEach>
            </select>
        </div>
            <%-- FILTRE DU PRIX --%>
        <div class="col">
            <label for="priceFilter"><fmt:message key="LabelFilterPrice"/></label>
            <select id="priceFilter" class="form-control form-control-lg text-center p-1 mb-1">
                <option minPriceRange="0" maxPriceRange="${maxPrice}" name="ElFiltre"><fmt:message
                        key="AllPrice"/></option>
                <c:set var="DesiredRange" value="25"/>
                <c:forEach begin="0" end="${maxPrice/DesiredRange}" var="i">
                    <c:set var="minPriceRange" value="${i*DesiredRange+1}"/>
                    <c:set var="maxPriceRange" value="${(i+1)*DesiredRange}"/>
                    <option value="${minPriceRange}-${maxPriceRange}" minPriceRange="${minPriceRange}"
                            maxPriceRange="${maxPriceRange}">
                            ${minPriceRange}-${maxPriceRange} $
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
        <%-- FILTRE DU NOM --%>
    <div class="row">
        <div class="col">
            <div class="input-group">
                <input type="text" class="form-control" id="gameNameInput">
                <button class="btn btn-outline-warning" id="btnSearchByName"><fmt:message key="FilterSearch"/></button>
            </div>
        </div>
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

    <div class="row">
        <div class="col" id="filterAllGameCol" style="display: none;">
            <input type="checkbox" id="chkShowOwnedGame">
            <label for="chkShowOwnedGame"><fmt:message key="LabelFilterShowOwned"/></label>
        </div>
            <%--    Bouton pour remettre les filtres de base--%>
        <div class="col">
            <button class="btn btn-outline-warning" id="btnResetFilter"><fmt:message key="ResetFilter"/></button>
        </div>
    </div>
</div>


        <%--    AFFICHER LE CATALOG DE JEU ( par défaut catalog exclu les jeux du panier et ceux  déjà posséder--%>
        <%-- AFFICHER LE CATALOG DE JEU (par défaut le catalogue exclut les jeux du panier et ceux déjà possédés) --%>
    <div class="container">
        <h1 class="text-center my-3"><fmt:message key="listeJeuTitle"/></h1>

        <div class="row">
            <c:forEach var="jeu" items="${catalog}">
                <c:set var="flagOwned" value="false"/>

                <%-- Parcourir la liste des jeux possédés pour définir un drapeau si déjà possédé --%>
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
                        <div class="card-body less-intense-background"
                             style="background-image: url('${jeu.imagePath}');">
                            <p class="card-text">${jeu.description}</p>
                            <p><strong>Genre:</strong><fmt:message key="${jeu.genre}"/></p>
                            <p><strong><fmt:message key="Price"/></strong> ${jeu.prix}$</p>
                            <form action="AcheteServlet" method="POST">
                                <input type="hidden" name="index" value="${jeu.idJeu}">
                                <input type="hidden" name="action" value="ACHETE">

                                    <%-- Valeurs de filtre enregistrées --%>
                                <input type="hidden" name="genreFilter" value="${genreFilterValue}">
                                <input type="hidden" name="gameNameFilter" value="${gameNameFilterValue}">
                                <input type="hidden" name="priceFilter" value="${priceFilterValue}">
                                <input type="hidden" name="showOwnedGamesFilter" value="${showOwnedGamesFilterValue}">

                                <div class="card-footer text-muted">
                                        <%-- Lorsque la checkbox "chkShowOwnedGame" est à true, le catalogue inclut maintenant les jeux déjà possédés --%>
                                    <c:if test="${flagOwned eq true}">
                                        <p><fmt:message key="AlreadyPossessed"/></p>
                                    </c:if>

                                    <button type="submit" class="btn btn-primary"><fmt:message
                                            key='buyCommand'/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <c:if test="${empty jeu.imagePath}">
                        <div class="image-placeholder">
                            <p>Image non disponible</p>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>

    </fmt:bundle>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="scripts/ScriptFilter.js"></script>


</body>
</html>
