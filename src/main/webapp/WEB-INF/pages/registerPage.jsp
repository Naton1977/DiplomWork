<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <c:url value="/resources/css/registerPageCSS.css" var="registerPageCSS"/>
    <link rel="stylesheet" href="${registerPageCSS}">
    <title>Title</title>
</head>
<body>
<header>
    <c:url value="/resources/image/label.jpg" var="label"/>
    <img src="${label}" alt="">
    <div>SLIM BODY</div>
    <div>диетический дневник</div>
</header>
<div id="item1"></div>

<main>
    <div id="mainInput">
        <c:url value="/registerPage/save" var="save"/>
        <form method="post" action="${save}">
            <p><u>Регистрация пользователя</u></p><br>
            <p>Введите логин</p>
            <input name="userLogin" type="text"><br>
            <p>Введите пароль</p>
            <input name="userPassword" type="password" id="password"><br>
            <p>Повторите пароль</p>
            <input name="confirmPassword" type="password" id="confirmPassword"><br>
            <p>Введите email</p>
            <input name="userEmail" type="email" id="mail"><br>
            <p></p>
            <input type="submit">
            <c:if test="${passwordNoConfirm == 1}">
                <p id="error">Пароли не совпадают !!!</p>
            </c:if>
        </form>
    </div>
</main>
<div id="item1a"></div>
<div id="footer">
    <div id="footer1">SLIM BODY</div>
    <div><i class="far fa-copyright" id="copyFooter"></i></div>
</div>

<div id="yearFooter">2020</div>

</body>
</html>