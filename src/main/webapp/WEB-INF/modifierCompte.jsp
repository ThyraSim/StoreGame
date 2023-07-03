<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<c:set scope="session" var="lang" value="${lang}"/>
<fmt:setLocale value="${lang}" />
<fmt:bundle basename="MessagesBundle">
  <head>
    <title>Chaos Games</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../style/modifierCompte.css">
    <link rel="stylesheet" type="text/css" href="../style/style.css">
    <script src="../scripts/scriptLangue.js" type="text/javascript"></script>
    <meta charset="UTF-8">
  </head>
  <body>
  <jsp:include page="navbar.jsp"/>
  <jsp:include page="background.jsp"/>
  <h3 id="registrationTitle"><fmt:message key="modify" /></h3>

  <form id="registrationForm" action="/ModifierCompteServlet" method="post">
    <div class="form-group row">
      <div class="col-lg-4">
        <div class="form-label">
          <label for="username"><fmt:message key="userTableHeader"/></label>
        </div>
      </div>
      <div class="col-lg-8">
        <input type="text" id="username" name="username" value="${loggedInAccount.user}" class="form-control" required>
      </div>
    </div>

    <div class="form-group row">
      <div class="col-lg-4">
        <div class="form-label">
          <label for="profileName"><fmt:message key="profileNameTableHeader" /></label>
        </div>
      </div>
      <div class="col-lg-8">
        <input type="text" id="profileName" name="profileName" value="${loggedInAccount.profileName}" class="form-control" required>
      </div>
    </div>

    <div class="form-group row">
      <div class="col-lg-4">
        <div class="form-label">
          <label for="password"><fmt:message key="passwordTableHeader" /></label>
        </div>
      </div>
      <div class="col-lg-8">
        <input type="password" id="password" name="password" value="${loggedInAccount.password}" class="form-control" required>
      </div>
    </div>

    <div class="form-group row">
      <div class="col-lg-4">
        <div class="form-label">
          <label for="nom"><fmt:message key="nomClientTableHeader" /></label>
        </div>
      </div>
      <div class="col-lg-8">
        <input type="text" id="nom" name="nom" value="${client.nom}" class="form-control" required>
      </div>
    </div>

    <div class="form-group row">
      <div class="col-lg-4">
        <div class="form-label">
          <label for="prenom"><fmt:message key="prenomClientTableHeader" /></label>
        </div>
      </div>
      <div class="col-lg-8">
        <input type="text" id="prenom" name="prenom" value="${client.prenom}" class="form-control" required>
      </div>
    </div>

    <div class="form-group row">
      <div class="col-lg-4">
        <div class="form-label">
          <label for="physique"><fmt:message key="adressPhyTableHeader" /></label>
        </div>
      </div>
      <div class="col-lg-8">
        <input type="text" id="physique" name="physique" value="${client.adressePhysique}" class="form-control" required>
      </div>
    </div>

    <div class="form-group row">
      <div class="col-lg-4">
        <div class="form-label">
          <label for="email"><fmt:message key="adressEmTableHeader" /></label>
        </div>
      </div>
      <div class="col-lg-8">
        <input type="text" id="email" name="email" value="${client.adresseCourriel}" class="form-control" required>
      </div>
    </div>

    <div class="form-group row">
      <div class="col-lg-12">
        <input class="btn btn-success" type="submit" value="<fmt:message key='save'/>">
        <a href="ProfileServlet" class="link-info"><fmt:message key="cancel"/></a>
      </div>
    </div>
  </form>

  <script src="../scripts/jquery-3.7.0.js" type="text/javascript"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</fmt:bundle>
</html>
