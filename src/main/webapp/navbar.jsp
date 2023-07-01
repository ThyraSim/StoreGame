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

      .dropdown-menu{
        background-color: #192D68;
        color: #ffd700;
        border: 1px solid black;
      }

      .dropdown-item {
        display: flex;
        align-items: center;
      }

      .total{
        display: flex;
        align-items: center;
        color: #ffd700;
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



      .scrollable-container {
        max-height: 200px; /* Set the desired max height for the container */
        overflow-y: auto; /* Enable vertical scrolling */
        overflow-x: hidden; /* Hide the horizontal scrollbar */
      }

      ::-webkit-scrollbar {
        width: 12px; /* Set the width of the scrollbar */
      }

      ::-webkit-scrollbar-thumb {
        background-color: #ffd700; /* Set the color of the scrollbar thumb */
      }

      ::-webkit-scrollbar-track {
        background-color: #192D68; /* Set the color of the scrollbar track */
      }

      .dropdown-item:hover {
        color: #192D68; /* Change the text color to blue */
        background-color: #ffd700; /* Change the background color to yellow */
      }

      #NavProfileLink  {
        color: #ffd700;
      }

    </style>

  </head>
  <body>

  <c:set var="loc" value="${not empty param.lang ? param.lang : pageContext.request.locale}" />
  <c:set scope="session" var="lang" value="${loc}"/>
  <fmt:setLocale value="${sessionScope.lang}" />

  <fmt:bundle basename="MessagesBundle">

    <nav class="navbar navbar-expand-lg" style="background-color: #192D68;">
      <div class="navbar-bottom-line"></div>
      <div class="container-fluid">
        <h5><a class="nav-link" href="/MagasinServlet"><fmt:message key="magasin"/></a></h5>
        <div class="navbar-brand">
          <img src="image/Logo.png" alt="logo" width="200px" height="auto">
        </div>
        <ul class="navbar-nav ml-auto">
          <c:if test="${not empty panier.jeux}">
            <li class="nav-item dropdown">
              <a class="nav-link" href="#" id="cartDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                 aria-expanded="false">
                <h5><i class="bi bi-cart"></i></h5>
              </a>
              <div class="dropdown-menu dropdown-menu-right wider-dropdown-menu" aria-labelledby="cartDropdown">
                <div id="cartItemsContainer">
                  <div class="scrollable-container">
                    <c:set var="totalPrice" value="0"/>
                    <c:forEach var="jeu" items="${panier.jeux}">
                      <c:set var="totalPrice" value="${totalPrice + jeu.prix}"/>
                      <div class="d-flex align-items-center my-1">
                        <span class="mx-3">${jeu.nomJeu}</span>
                        <span class="ml-auto">${jeu.prix}</span>
                        <form action="DeleteServlet" method="POST" class="float-right">
                          <input type="hidden" name="idJeu" value="${jeu.idJeu}">
                          <input type="hidden" name="source" value="nav">
                          <button type="submit" class="btn btn-danger ml-2"
                                  style="padding: 0.25rem 0.5rem; font-size: 0.875rem; margin-right: 10px"><i
                                  class="bi bi-trash"></i></button>
                        </form>
                      </div>
                    </c:forEach>
                  </div>
                  <div class="dropdown-divider" style="border-color:black;"></div>
                  <div class="total">
                    <span style="margin-left: 20px;"><fmt:message key="totalPrice"/>:</span>
                    <span class="ml-auto"
                          style="margin-right: 10px;"><fmt:formatNumber value="${totalPrice}" minFractionDigits="2"
                                                                        maxFractionDigits="2"/></span>
                  </div>
                </div>
                <div class="dropdown-divider" style="border-color:black;"></div>
                <h5><a class="dropdown-item" href="panier.jsp"><fmt:message key="seeCart"/></a></h5>
              </div>
            </li>
          </c:if>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="currencyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <fmt:message key="currency"/>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="currencyDropdown">
              <a class="dropdown-item" href="?currency=USD">USD</a>
              <a class="dropdown-item" href="?currency=CAD">CAD</a>
              <a class="dropdown-item" href="?currency=EUR">EUR</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <c:choose>
            <c:when test="${not empty loggedInAccount}">
            <!-- Si l'utilisateur est connecté, affichez le menu déroulant -->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="loginDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                ${loggedInAccount.profileName}
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="loginDropdown">
              <a class="dropdown-item" href="/ProfileServlet"><fmt:message key="profile"/></a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/LoginServlet?logout=true">Logout</a>
            </div>
          </li>
          </c:when>
          <c:otherwise>
            <!-- Si l'utilisateur n'est pas connecté, affichez simplement un lien -->
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/LoginServlet">
                <fmt:message key="login"/>
              </a>
            </li>
          </c:otherwise>
          </c:choose>

          <!-- Language Dropdown -->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="languageDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
              <fmt:message key="language"/>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="languageDropdown">
              <a class="dropdown-item" href="?lang=en">English</a>
              <a class="dropdown-item" href="?lang=fr">French</a>
            </div>
        </ul>
      </div>
    </nav>

  </fmt:bundle>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.2.3/js/bootstrap.min.js"
          integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
          crossorigin="anonymous"></script>
  <script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
  </body>
</html>


