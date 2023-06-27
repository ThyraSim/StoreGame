<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 6/22/2023
  Time: 1:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <link
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            rel="stylesheet"
            crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <style>
      .wider-dropdown-menu {
        white-space: nowrap;
      }

      .dropdown-item {
        display: flex;
        align-items: center;
      }

      .navbar-brand {
        transform: translateX(-50%);
        left: 50%;
        position: absolute;
      }

      .navbar-bottom-line {
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
        height: 2px;
        background-color: black;
      }

      .navbar{
        min-height: 90px;
      }
    </style>

  </head>
  <body>
    <fmt:bundle basename="MessagesBundle">

      <nav class="navbar navbar-expand-lg" style="background-color: #192D68;">
        <div class="navbar-bottom-line"></div>
        <div class="container-fluid">
          <h5><a href="/MagasinServlet"><fmt:message key="magasin"/></a></h5>
          <div class="navbar-brand">
            <img src="image/Logo.png" alt="logo" width="200px" height="auto">
          </div>
          <ul class="navbar-nav ml-auto">
              <c:if test="${not empty panier.jeux}">
                <li class="nav-item dropdown">
                  <a class="nav-link" href="#" id="cartDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="bi bi-cart"></i>
                  </a>
                  <div class="dropdown-menu dropdown-menu-right wider-dropdown-menu" aria-labelledby="cartDropdown">
                    <div class="dropdown-header"><fmt:message key="inCart"/></div>
                    <div id="cartItemsContainer">
                      <c:set var="totalPrice" value="0" />
                      <c:forEach var="jeu" items="${panier.jeux}">
                        <c:set var="totalPrice" value="${totalPrice + jeu.prix}" />
                        <div class="d-flex align-items-center">
                          <span class="mx-3">${jeu.nomJeu}</span>
                          <span class="ml-auto">${jeu.prix}</span>
                          <form action="DeleteServlet" method="POST" class="float-right">
                            <input type="hidden" name="idJeu" value="${jeu.idJeu}">
                            <input type="hidden" name="source" value="nav">
                            <button type="submit" class="btn btn-danger ml-2"><i class="bi bi-trash"></i></button>
                          </form>
                        </div>
                      </c:forEach>
                      <div class="dropdown-divider"></div>
                      <div class="dropdown-item total-price">
                        <span><fmt:message key="totalPrice"/>:</span>
                        <span class="ml-auto">${totalPrice}</span>
                      </div>
                    </div>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="panier.jsp"><fmt:message key="seeCart"/></a>
                  </div>
                </li>
              </c:if>
              <c:choose>
                <c:when test="${not empty loggedInAccount}">
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/ProfileServlet">
                      ${loggedInAccount.profileName}
                    </a>
                  </li>
                </c:when>
                <c:otherwise>
                  <li class="nav-item">
                    <a class="nav-link" href="/LoginServlet">
                      <fmt:message key="login"/>
                    </a>
                  </li>
                </c:otherwise>
              </c:choose>
            </ul>
        </div>
      </nav>
    </fmt:bundle>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
  </body>
</html>
