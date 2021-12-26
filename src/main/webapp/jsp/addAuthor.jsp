<%@ page contentType="text/html;charset=UTF-8" buffer="none" isThreadSafe="true" errorPage="/jsp/error.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

    <title>Добавить нового автора</title>
</head>
<body>

<%@ include file="/jsp/header-menu.jsp" %>


<div class="container mt-lg-5">
    <form class="row" name="addAuthorForm" method="POST"
          action="${pageContext.request.contextPath}/controller?command=add_author">
        <input type="hidden" name="command" value="add_author"/>
        <div class="col-md-4">
            <label for="inputAuthorName" class="form-label">Имя автора книг</label>
            <input type="text" class="form-control" id="inputAuthorName" name="inputAuthorName">
        </div>
        <div class="col-12 mt-lg-5">
            <button type="submit" class="btn btn-primary">Добавить автора</button>
        </div>
    </form>
</div>


<!-- Modal menu exit  -->
<%@ include file="/jsp/modalMenuExit.jsp" %>

<%@ include file="/jsp/modalMenuOperationResult.jsp" %>


<input class="hidden-role-value" type="hidden" name="role" value="${role}"/>
<input class="operation-status" type="hidden" name="operation-status" value="${status}"/>
</body>
<script src=${pageContext.request.contextPath}/js/main.js></script>
<script src=${pageContext.request.contextPath}/js/modalMenuAction.js></script>
</html>


