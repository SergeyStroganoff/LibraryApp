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

    <title>About Library</title>
</head>
<body>

<%@ include file="/jsp/header-menu.jsp" %>

<input class="hidden-role-value" type="hidden" name="role" value="${role}"/>


<div id="about-text" class="visible">
    <div class="container" aria-atomic="true">
        <div class="starter-template text-center py-3 px-3">
            <h3>О нас</h3>
            <p>Наш проект создавался как учебный в 2021 году и вырос до полноценной и многофункциональной библиотеки</p>
            <p>Проект развивается и поддерживается усилиями одного разработчика в свободное время.</p>
            <p>Свои предложения, пожелания и благодарности Вы можете выразить на email <a
                    href="mailto:stroganov@sergey.ru">stroganov@sergey.ru</a></p>
        </div>
    </div>
</div>

<!-- Modal menu exit  -->
<%@ include file="/jsp/modalMenuExit.jsp" %>


</body>
<script src=${pageContext.request.contextPath}/js/main.js></script>
</html>