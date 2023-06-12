<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--  <title>JSP - Hello World</title>--%>
<%--  <link--%>
<%--          href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"--%>
<%--          rel="stylesheet"--%>
<%--          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"--%>
<%--          crossorigin="anonymous"/>--%>

<%--</head>--%>
<%--<body>--%>
<%--<h1><%= "CHAOShop!" %></h1><br>--%>
<%--<div class="container text-center">--%>
<%--  <div class="row row-cols-4">--%>
<%--    <div class="col"><a href="login.jsp">Login</a></div>--%>
<%--    <div class="col"><a href="ProfileServlet"> vers profile servlet</a></div>--%>
<%--    <div class="col"><form action="MagasinServlet" method="POST">--%>
<%--      <input type="hidden" name="action" value="ouverture">--%>
<%--      <input type="submit" value="Magasin">--%>
<%--    </form></div>--%>
<%--    <div class="col">Column</div>--%>
<%--  </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--<script--%>
<%--        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"--%>
<%--        crossorigin="anonymous"--%>
<%--></script>--%>
<%--<script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>--%>
<%--</html>--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <link
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet"
          crossorigin="anonymous"/>
</head>
<body>
<div class="container">
  <h1 class="text-center my-3">CHAOShop!</h1><br>
  <div class="row text-center">
    <div class="col"><a class="btn btn-primary" href="login.jsp">Login</a></div>
    <div class="col"><a class="btn btn-secondary" href="ProfileServlet">Vers Profile Servlet</a></div>
    <div class="col">
      <form action="MagasinServlet" method="POST">
        <input type="hidden" name="action" value="ouverture">
        <input type="submit" class="btn btn-success" value="Magasin">
      </form>
    </div>
    <div class="col">Column</div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>

</body>
</html>


