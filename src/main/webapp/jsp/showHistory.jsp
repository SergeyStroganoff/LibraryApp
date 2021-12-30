<%@ page contentType="text/html;charset=UTF-8" buffer="none" isThreadSafe="true" errorPage="/jsp/error.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <!-- Обязательные мета -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS-->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

    <link rel="icon" href="${pageContext.request.contextPath}/img/books-library-logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/books-library-logo.png" type="image/x-icon">

    <title>История всех пользователей</title>
</head>
<body>

<%@ include file="/jsp/header-menu.jsp" %>

<p class="mt-lg-5"></p>

<div class=" container table-responsive">
    <table class="table">
        <thead class="bg-light">
        <tr>
            <th scope="col">Имя пользователя</th>
            <th scope="col">Логин пользователя</th>
            <th scope="col">Дата и время</th>
            <th scope="col">Событие</th>
        </tr>
        </thead>
        <tbody class="table-striped">
        <c:forEach var="historyEvent" items="${history}">
            <tr>
                <td>${historyEvent.user.fullName}</td>
                <td>${historyEvent.user.login }</td>
                <td><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                    value="${historyEvent.localDateTime}"/></td>
                <td>${historyEvent.event}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<!-- Modal menu exit  -->
<%@ include file="/jsp/modalMenuExit.jsp" %>

<%@ include file="/jsp/modalMenuOperationResult.jsp" %>

<input class="hidden-role-value" type="hidden" name="role" value="${sessionScope.role}"/>
</body>
<script src=${pageContext.request.contextPath}/js/main.js></script>
<script src=${pageContext.request.contextPath}/js/modalMenuAction.js></script>
</html>



