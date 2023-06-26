
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
                    <c:set var="minPriceRange" value="${i*10+1}" />
                    <c:set var="maxPriceRange" value="${(i+1)*10}" />
                    <option value="${minPriceRange}-${maxPriceRange}" minPriceRange="${minPriceRange}" maxPriceRange="${maxPriceRange}">
                            ${minPriceRange}-${maxPriceRange} $
                    </option>
                </c:forEach>
            </select>
        </div>

                <%--FILTRE POUR AFFICHER LES JEUX POSSEDÉS--%>
        <div class="row">
            <div>
                <label ><fmt:message key="FilerAllGame"/></label>
                <input  type="checkbox" id="chkShowOwnedGame" >
                </form>
            </div>
        </div>
    </div>


    <%--FILTRE POUR AFFICHER TOUS LES JEUX--%>


<%--    AFFICHER LE CATALOG DE JEU ( par défaut catalog exclu les jeux du panier et ceux du déjà posséder--%>
    <div class="container">
        <h1 class="text-center my-3"><fmt:message key="listeJeuTitle"/></h1>

        <div class="row">
            <c:forEach var="jeu" items="${catalog}">
                <c:set var="flagOwned" value="false"/>


<%--            Parcour la liste des jeux posséder pour set un flag deja posséer--%>
                <c:forEach var="ownedGame" items="${owned}">
                    <c:if test="${ownedGame.idJeu eq jeu.idJeu}">
                        <c:set var="flagOwned" value="true"/>

                    </c:if>
                </c:forEach>

                <div name="Card-Game" genre="${jeu.genre}" gameName="${jeu.nomJeu}" gamePrice="${jeu.prix}" gameOwned="${flagOwned}"
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
                                <div class="card-footer text-muted">
                            <%-- Lorsque la checkbox Afficher tous les jeux, le catalog inclus maintenant les jeux deja posseder--%>

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
    <a href="panier.jsp">Panier</a>
</fmt:bundle>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="scripts/ScriptFilter.js"></script>

</body>
</html>
