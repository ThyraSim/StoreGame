<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 2023-06-05
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <c:set var="lang" value="${not empty param.lang ? param.lang : pageContext.request.locale.language}" />
    <c:set scope="session" var="lang" value="${lang}"/>
    <fmt:setLocale value="${lang}" />

<fmt:bundle basename="MessagesBundle">
    <title><fmt:message key="userProfileTitle"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">


    <link rel="stylesheet" type="text/css" href="style/style.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
          integrity="sha512-...your-integrity-hash...==" crossorigin="anonymous"/>
    <script src="scripts/scriptLangue.js" type="text/javascript"></script>

</head>
<body>

<jsp:include page="navbar.jsp?lang=${lang}" />

<jsp:include page="background.jsp"/>
<div class="container">
    <div class="row my-4">
        <div class="col-md-4">
            <img src="${pageContext.request.contextPath}/image/aoruschibi.PNG" class="rounded-circle"
                 alt="Profile picture">
        </div>
        <div class="col-md-8">
            <h2>${compte.profileName}</h2>
            <h5>${client.adresseCourriel}</h5>
        </div>
        <a class="link-info" href="/modifierCompte.jsp"><fmt:message key="modify"/></a>
        <div>
            <h4><fmt:message key="hi"/> ${client.prenom} ${client.nom}</h4>
        </div>
    </div>


    <h1><fmt:message key="listeJeuTitle"/></h1>
    <c:forEach var="jeu" items="${owned}" varStatus="loop">
        <div class="ProfileGameRow row  mb-1 h2 ">
            <div>
                    <span class="">${jeu.nomJeu}</span> <span class="badge badge-pill badge-info mr-1" style="font-size: 0.9rem"><fmt:message
                    key="${jeu.genre}"/></span>
                <button
                        class="btn btn-outline-light btn-sm float-right"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#description${loop.index}"
                        aria-expanded="false"
                        aria-controls="description${loop.index}"
                >
                    <i class="fas fa-search"></i>
                </button>
            </div>
            <div class="collapse" id="description${loop.index}">
                <div class="card card-body PanierCollapseCardDescription ">
                    <span style="font-size: 0.9rem">${jeu.description} </span>
                </div>
            </div>


        </div>
    </c:forEach>

</div>

<script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</fmt:bundle>
</html>