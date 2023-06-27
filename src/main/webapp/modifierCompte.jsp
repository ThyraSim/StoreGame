<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 6/27/2023
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
Created by IntelliJ IDEA.
User: simbe
Date: 2023-06-05
Time: 22:34
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:bundle basename="MessagesBundle">
  <head>
    <title>Title</title>
    <link
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            rel="stylesheet"
            crossorigin="anonymous"/>
    <meta charset="UTF-8">
  </head>
  <body>
  <jsp:include page="navbar.jsp"/>
  <jsp:include page="background.jsp"/>
    <h1 id="registrationTitle"><fmt:message key="modify" /></h1>
    <form id="registrationForm" action="/ModifierCompteServlet" method="post">
      <label for="username"><fmt:message key="userTableHeader"/>:</label>
      <input type="text" id="username" name="username" value="${loggedInAccount.user}" required><br><br>

      <label for="profileName"><fmt:message key="profileNameTableHeader" />:</label>
      <input type="text" id="profileName" name="profileName" value="${loggedInAccount.profileName}" required><br><br>

      <label for="password"><fmt:message key="passwordTableHeader" />:</label>
      <input type="password" id="password" name="password" value="${loggedInAccount.password}" required><br><br>

      <label for="nom"><fmt:message key="nomClientTableHeader" />:</label>
      <input type="text" id="nom" name="nom" value="${client.nom}" required><br><br>

      <label for="prenom"><fmt:message key="prenomClientTableHeader" />:</label>
      <input type="text" id="prenom" name="prenom" value="${client.prenom}" required><br><br>

      <label for="physique"><fmt:message key="adressPhyTableHeader" />:</label>
      <input type="text" id="physique" name="physique" value="${client.adressePhysique}" required><br><br>

      <label for="email"><fmt:message key="adressEmTableHeader" />:</label>
      <input type="text" id="email" name="email" value="${client.adresseCourriel}" required><br><br>

      <input type="submit" value="<fmt:message key='save'/>">
      <a href="ProfileServlet"><fmt:message key="cancel"/></a>
    </form>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
  </body>
</fmt:bundle>
</html>
