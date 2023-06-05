<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "CHAOS!!!!!!!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br>
<a href="ProfileServlet">Profile</a>
<br>
<form action="MagasinServlet" method="get">
  <input type="hidden" name="action" value="ouverture">
  <input type="submit" value="Magasin">
</form>
</body>
</html>