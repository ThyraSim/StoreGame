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
        <title>Chaos Games</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="style/style.css">
        <script src="scripts/scriptLangue.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-...your-integrity-hash...==" crossorigin="anonymous" />
    </head>
    <body>
        <c:set scope="session" var="lang" value="${lang}"/>
        <fmt:setLocale value="${lang}" />
        <fmt:bundle basename="MessagesBundle">
        <jsp:include page="navbar.jsp?lang=${lang}" />
        <jsp:include page="background.jsp"/>

        <div class="container h3">
            <div class="row">

                <div class="col">
                    <c:if test="${not empty panier.jeux}">
                        <h1><fmt:message key="panierTitle"/></h1>
                    </c:if>
                </div>
            </div>

            <c:set var="totalPrice" value="0"/>
            <c:forEach var="jeu" items="${listePanier}" varStatus="loop">
                <c:set var="totalPrice" value="${totalPrice + jeu.prix}"/>
                <div class="PanierGameRow row  mb-1 ">
                    <div>
                            ${jeu.nomJeu}  <span class="badge badge-pill badge-info" style="font-size: 0.9rem"><fmt:message key="${jeu.genre}"/></span>
                        <button
                                class="btn btn-outline-light btn-sm"
                                type="button"
                                data-bs-toggle="collapse"
                                data-bs-target="#description${loop.index}"
                                aria-expanded="false"
                                aria-controls="description${loop.index}"
                        >
                            <i class="fas fa-search"></i>
                        </button>



                        <form action="DeleteServlet" method="POST" class="float-right">
                            <input type="hidden" name="idJeu" value="${jeu.idJeu}"/>
                            <input type="hidden" name="source" value="panier"/>
                            <button type="submit" class="btn btn-danger ml-2" style="padding: 0.25rem 0.5rem; font-size: 0.875rem; margin-right: 10px"><i class="bi bi-trash"></i></button>

                        </form>

                        <span style="float: right; margin-right: 10px;">${jeu.prix}$</span>

                    </div>


                    <div class="collapse" id="description${loop.index}">
                        <div class="card card-body PanierCollapseCardDescription ">
                            <span style="font-size: 0.9rem">${jeu.description} </span>
                        </div>
                    </div>


                </div>
            </c:forEach>

            <div class="total row float-right">
                <h1><fmt:message key="totalPrice"/>:
                    <fmt:formatNumber value="${totalPrice}"
                                      minFractionDigits="2"
                                      maxFractionDigits="2"/>$</h1>
            </div>

            <%--Dans la servlet on associe une valeur bolean "noOwned" qui est a false  si un produit du panier est possédé par l'utilisateur--%>
            <%--Ainsi le bouton pour acheter à soi-même est désactiver car seulement 1 copie de jeu par compte--%>
            <c:if test="${noOwned}">
                <form action="CheckOutServlet" method="POST" class="mt-3">
                    <input type="hidden" name="liste" value="${compteId}">
                    <input type="hidden" name="action" value="SELF">
                    <button type="submit" class="btn-lg btn-success "><fmt:message key='selfCommand'/></button>
                </form>
            </c:if>

                <%--Le bouton acheté pour un cadeau, c'est-à-dire donnée à un autre compte --%>
            <form action="CheckOutServlet" method="POST" class="mt-2">
                <input type="hidden" name="liste" value="${compteId}">
                <input type="hidden" name="action" value="GIFT">
                <button type="submit" class="btn-lg btn-warning text-white"><fmt:message key='giftCommand'/></button>
            </form>

        </div>

        <script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
        </fmt:bundle>
    </body>
</html>
