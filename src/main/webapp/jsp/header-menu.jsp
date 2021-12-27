<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">BookLibrary</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page"
                       href="${pageContext.request.contextPath}/jsp/main.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/jsp/about.jsp">О нас</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-expanded="false">Действия</a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/addBook.jsp">Добавить
                            новую книгу</a></li>
                        <li><a class="dropdown-item" href="#">Удалить книгу</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/addBookMark.jsp">Добавить
                            закладку в книгу</a></li>
                        <li><a class="dropdown-item" href="#">Удалить закладку</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/addBooksFromFile.jsp">Добавить
                            книги из файла</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/addAuthor.jsp">Добавить
                            автора</a></li>
                        <li><a class="dropdown-item" href="#">Удалить автора и его книги</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">Искать книги по части названия</a></li>
                        <li><a class="dropdown-item" href="#">Искать книги по номеру ISBN</a></li>
                        <li><a class="dropdown-item" href="#">Искать книги по части имени автора</a></li>
                        <li><a class="dropdown-item" href="#">Искать книги в интервале дат выпуска</a></li>
                        <li><a class="dropdown-item" href="#">Искать книгу по дате выпуска, количеству страниц и части
                            названия </a></li>
                        <li><a class="dropdown-item" href="#">Искать книги с моими закладками</a></li>
                        <li class="admin-menu">
                            <hr class="dropdown-divider">
                        </li>
                        <li class="admin-menu"><a class="dropdown-item"
                                                  href="${pageContext.request.contextPath}/jsp/addUser.jsp">Создать
                            пользователя</a></li>
                        <li class="admin-menu"><a class="dropdown-item" href="#">Заблокировать пользователя</a></li>
                        <li class="admin-menu"><a class="dropdown-item" href="#">Разблокировать пользователя</a></li>
                        <li class="admin-menu"><a class="dropdown-item" href="#">Посмотреть историю всех
                            пользователей</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-close-white" data-toggle="modal" data-target="#exitModalMenu">
                        Выход
                    </button>
                </li>
            </ul>
            <form class="d-flex">
                <p>Пользователь: ${sessionScope.userLogin}</p>
            </form>
        </div>
    </div>
</nav>