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
    <script src="../scripts/jquery-3.7.0.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
      .wider-dropdown-menu {
        white-space: nowrap;
      }

      .dropdown-item {
        display: flex;
        align-items: center;
      }
    </style>

  </head>
  <body>
    <fmt:bundle basename="MessagesBundle">

      <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
          <a class="navbar-brand" href="/MagasinServlet"><fmt:message key="magasin"/></a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
          </button>
            <ul class="navbar-nav ml-auto">
              <c:if test="${not empty panier.jeux}">
                <li class="nav-item dropdown">
                  <a class="nav-link" href="#" id="cartDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="bi bi-cart"></i>
                  </a>
                  <div class="dropdown-menu dropdown-menu-right wider-dropdown-menu" aria-labelledby="cartDropdown">
                    <div class="dropdown-header"><fmt:message key="inCart"/></div>
                    <div id="cartItemsContainer">
                      <c:forEach var="jeu" items="${panier.jeux}">
                        <div class="dropdown-item d-flex align-items-center">
                          <span>${jeu.nomJeu}</span>
                          <span class="ml-auto">${jeu.prix}</span>
                          <form action="DeleteServlet" method="POST" class="float-right">
                            <input type="hidden" name="idJeu" value="${jeu.idJeu}">
                            <input type="hidden" name="source" value="nav">
                            <button type="submit" class="btn btn-danger ml-2"><i class="bi bi-trash"></i></button>
                          </form>
                        </div>
                      </c:forEach>
                      <div class="dropdown-item total-price">
                        <span>Total Price:</span>
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
    <script src="../scripts/jquery-3.7.0.js" type="text/javascript"></script>
  </body>
</html>
