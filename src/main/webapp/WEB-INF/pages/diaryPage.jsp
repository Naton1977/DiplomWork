<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <c:url value="/resources/css/diaryPage.css" var="diaryPageCSS"/>
    <link rel="stylesheet" href="${diaryPageCSS}">
    <c:url value="/resources/fonts/fontawesome-free-5.14.0-web/css/all.css" var="fonts"/>
    <link rel="stylesheet" href="${fonts}">
    <c:url value="/resources/css/calendar.css" var="calendar"/>
    <link rel="stylesheet" type="text/css" href="${calendar}">
    <c:url value="/resources/js/calendar.js" var="calendarjs"/>
    <script type="text/javascript" src="${calendarjs}"></script>
    <c:url value="/resources/js/bodyParameterPage.js" var="bodyParam"/>
    <script type="text/javascript" src="${bodyParam}"></script>
    <c:url value="/resources/js/addFigureParameters.js" var="addFigire"/>
    <script type="text/javascript" src="${addFigire}"></script>
    <c:url value="/resources/js/productsTab.js" var="prodactsTab"/>
    <script type="text/javascript" src="${prodactsTab}"></script>
    <c:url value="/resources/css/productsTabContent.css" var="productsTabContent"/>
    <link rel="stylesheet" type="text/css" href="${productsTabContent}">
    <c:url value="/resources/js/addDietTab.js" var="dietTab"/>
    <script type="text/javascript" src="${dietTab}"></script>
    <c:url value="/resources/js/recipe.js" var="recipe"/>
    <script type="text/javascript" src="${recipe}"></script>
    <c:url value="/resources/css/dietTab.css" var="dietTab"/>
    <link rel="stylesheet" href="${dietTab}">
    <c:url value="/resources/css/recipe.css" var="recipeccs"/>
    <link rel="stylesheet" href="${recipeccs}"/>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
<header>
    <c:url value="/resources/image/label.jpg" var="label1"/>
    <img src="${label1}" alt="">
    <div>SLIM BODY</div>
    <div>диетический дневник</div>
</header>
<div id="item1">
    <div id="item1a">Рацион</div>
    <div id="item1b">Продукты</div>
    <div id="item1c">Рецепты</div>
    <div id="item1d">Ваши параметры</div>
    <div id="item1e">Ваша калорийность</div>
</div>
<div id="main">
    <div id="mainContainer">
        <div id="column1"></div>
        <div id="column2">
            <sec:authentication property="name" var="username"/>
            <div id="userLogin">Вы вошли как :&nbsp;<span id="login">${username}</span></div>
            <div id="containerLogout">Для выхода нажмете : <a id="logout" href="<spring:url value="/logout"/>">выход</a>
            </div>
            <div id="userCalorieContent">Ваша дневная калорийность :<span id="DCResult">&nbsp;${calorieContent}</span>&nbsp;
                Ккал
            </div>
            <div style="margin-left: 5px"><em>(Для отображения заполните форму)</em></div>
            <div id="calendar_table"></div>
            <form id="form-submit-product" method="post" action="<spring:url value="/api/v1/product/create"/>">
                <sec:csrfInput/>
            </form>
        </div>
    </div>
</div>
<form id="form-logout" method="post" action="<spring:url value="/logout"/>">
</form>
<div id="item9">
    <div id="item9a">SLIM BODY</div>
    <div><i class="far fa-copyright" id="copy"></i></div>
</div>
<div id="year">2020</div>
<script>
    $("#logout").click((e) => {
        e.preventDefault();
        $("#form-logout").submit();
    })
</script>
</body>
</html>









