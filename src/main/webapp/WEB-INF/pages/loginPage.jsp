<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <c:url value="/resources/css/loginPageCSS.css" var="loginPageCSS"/>
    <link rel="stylesheet" href="${loginPageCSS}">
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
        <form method="post" action="<spring:url value="/login"/>">
            <sec:csrfInput/>
            <label><u>Вход пользователя</u></label><br>
            <p></p>
            <label for="login">Введите логин <br>
                <p></p><input name="login" type="text" id="login"></label><br>
            <p></p>
            <label for="password">Введите пароль<br>
                <p></p><input name="password" type="password" id="password"></label><br>
            <p></p>
            <input type="submit">
            <div>
                <c:if test="${param.enter == 'failure'}">
                    <p style="color: #FF0000">Неверный логин или пароль</p>
                </c:if>
                <c:if test="${param.logout == 'success'}">
                    <p style="color: green">Вы успешно вышли !!!</p>
                </c:if>
            </div>
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