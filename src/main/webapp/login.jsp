<%--
  Created by IntelliJ IDEA.
  User: Server
  Date: 16.12.2021
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" buffer="none" isThreadSafe="true" errorPage="/error.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="ru">
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="icon" href="img/books-library-logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="img/books-library-logo.png" type="image/x-icon">

    <title>Login form</title>
    <!-- Custom styles for this template -->
    <link href="https://bootstrap5.ru/css/examples/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin" name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login"/>
    <img class="mb-4" src="img/books-library-logo.png" alt="logo library" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Пожалуйста войдите</h1>
    <label for="inputLogin" class="sr-only">Логин</label>
    <input type="text" id="inputLogin" class="form-control" placeholder="Логин" required="" autofocus="" value="">
    <label for="inputPassword" class="sr-only">Пароль</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" required="" value="">
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Запомнить меня
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit" value="Log in">Войти</button>

    <p class="mt-5 mb-3 text-muted">&copy; 2021 by S.Stroganov </p>
    <p></p>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
</form>

<!-- Необязательный JavaScript; выберите один из двух! -->
<!-- Вариант 1: пакет Bootstrap с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
<!-- Вариант 2: отдельные JS для Popper и Bootstrap -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
-->
</body>
</html>
