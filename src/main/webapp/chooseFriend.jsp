<%--
  Created by IntelliJ IDEA.
  User: simbe
  Date: 2023-06-08
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:bundle basename="MessagesBundle">
    <head>
        <title><fmt:message key="messageChoisir" /></title>
        <link
                href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
                rel="stylesheet"
                integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
                crossorigin="anonymous"
        />
        <script
                src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"
        ></script>
        <script src="scripts/jquery-3.7.0.js" type="text/javascript"></script>
    </head>
    <body>
        <form action="SearchServlet" method="POST">
            <input type="text" id="searchInput" name="searchInput" placeholder="<fmt:message key="search"/>">
            <input type="submit" value="<fmt:message key='btnRecherche' />">
        </form>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>
                        <fmt:message key="idcompteTableHeader" />
                    </th>
                    <th>
                        <fmt:message key="userTableHeader" />
                    </th>
                    <th>
                        <fmt:message key="profileNameTableHeader" />
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty compteResult}">
                    <c:forEach var="compte" items="${compteResult}">
                        <tr>
                            <td>${compte.idCompte}</td>
                            <td>${compte.user}</td>
                            <td>${compte.profileName}</td>
                            <td>
                                <form action="MagasinServlet" method="POST">
                                    <input type="hidden" name="giftId" value="${compte.idCompte}">
                                    <input type="hidden" name="action" value="GIFT">
                                    <input type="submit" value="<fmt:message key="choose"/>">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </body>
</fmt:bundle>
</html>