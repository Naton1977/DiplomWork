<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <c:url value="/resources/css/registerPage.css" var="registerPageCSS"/>
    <link rel="stylesheet" href="${registerPageCSS}">
    <title>Title</title>
    <style>
        .error {
            color: red;
        }
    </style>
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
        <form:form modelAttribute="userDto" action="${save}" method="post">
            <p><u>Регистрация пользователя</u></p><br>
            <p>Введите логин</p>
            <div>
                <form:input path="userLogin"/>
            </div>
            <form:errors path="userLogin" cssClass="error"/>
            <p>Введите пароль</p>
            <div>
                <form:input path="userPassword" type="password" id="password"/>
            </div>
            <form:errors path="userPassword" cssClass="error"/>
            <p>Повторите пароль</p>
            <div>
                <form:input path="confirmPassword" type="password" id="confirmPassword"/>
            </div>
            <form:errors path="confirmPassword" cssClass="error"/>
            <p>Введите email</p>
            <div>
                <form:input path="userEmail" id="mail"/>
            </div>
            <form:errors path="userEmail" cssClass="error"/>
            <p></p>
            <input type="submit">
        </form:form>
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