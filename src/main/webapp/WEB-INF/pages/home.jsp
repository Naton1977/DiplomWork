<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <c:url value="/resources/fonts/fontawesome-free-5.14.0-web/css/all.css" var="css1"/>
    <link href="${css1}" rel="stylesheet">

    <c:url value="/resources/js/homePage.js" var="homejs"/>
    <script src="${homejs}"></script>

    <c:url value="/resources/css/home.css" var="home"/>
    <link href="${home}" rel="stylesheet"/>

    <c:url value="/resources/css/weathewidget.css" var="weatherWidget"/>
    <link rel="stylesheet" href="${weatherWidget}"/>

    <c:url value="/resources/fonts/fontawesome-free-5.14.0-web/css/all.css" var="font1"/>
    <link rel="stylesheet" href="${font1}"/>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<header>
    <c:url value="/resources/image/label.jpg" var="label"/>
    <img src="${label}" alt="">
    <div>SLIM BODY</div>
    <div>диетический дневник</div>
    <div>
        <a href="<spring:url value="/login"/> ">Вход</a>
        <p>&nbspили</p>
        <c:url value="/registerPage" var="register"/>
        <a href="${register}">Регистрация</a>
    </div>
</header>
<div id="item1"></div>
<div id="main">
    <div id="item3">
        <c:url value="/resources/image/water.jpg" var="water"/>
        <img src="${water}" alt="">
        <p>Значение воды для оганизма человека</p>
        <p><span>Вода – основной элемент организма человека, жизненно важный для работы органов и терморегуляции.
            В воде растворяется больше веществ, чем в любом другом растворителе. Для большинства происходящих
            в наших клетках химических реакций требуется вода. Вода нужна для транспортировки питательных веществ
            и кислорода ко всем клеткам тела. Она помогает преобразовывать пищу в энергию и усваивать питательные
            вещества. Вода поддерживает стабильность температуры тела и защищает жизненно важные органы, участвует
            в поддержании формы клеток и органов и важна для здоровья кожи.</span></p>
        <c:url value="/article/1" var="one"/>
        <a href="${one}">читать дальше</a>
    </div>
    <div id="item4">
        <c:url value="/resources/image/proteins.jpg" var="proteins"/>
        <img src="${proteins}" alt="">
        <p>Что такое белки и зачем они нужны</p>
        <p><span>Белок – это самый важный материал для процессов восстановления и замены устаревших клеток на новые.
            Эти процессы происходят в человеческом организме постоянно. Выработка этого вещества в нашем организме
            невозможна без аминокислот, которыми богат растительный и животный белок. Выполняя строительные функции
            клеток и органов, белок, на основе которого создаются различные соединения и гормоны, наряду с глюкозой
            является превосходной пищей для мозга. Волосы, ногти, мышцы, внутренние органы – весь наш организм работает
            на этом материале.</span><br><span>Основные источники белка – это животные и растительные продукты,
            например, такие как творог, рыба, мясо и яйца. В процессе пищеварения и переработки организмом изначальный
            его состав будет отличаться от</span>
        </p>
        <c:url value="/article/2" var="two"/>
        <p><a href="${two}">читать дальше</a></p>
    </div>
    <div id="item5">
        <c:url value="/resources/image/fats.jpg" var="fats"/>
        <img src="${fats}" alt="">
        <p>Нужны ли жиры организму человека ?</p>
        <p>Кто занимается спортом, знает, как много нужно приложить усилий для избавления от лишних
            килограммов и жира. Сжигание жира это длительный и довольно сложный процесс. Однако, жир
            необходим для множества процессов, происходящих в нашем организме. Без жиров существование
            человека невозможно. Они также необходимы для нормального функционирования головного мозга.
            Но не все жиры полезны.<br>
            Жир дает огромный приток энергии, достичь которого практически невозможно, потребляя только белки
            и углеводы. Каждый грамм жира снабжает 9 калориями. Для сравнения, белки и углеводы дадут только
            4 калории на каждый грамм веса.</p>
        <c:url value="/article/3" var="three"/>
        <p><a href="${three}">читать дальше</a></p>
    </div>
    <div id="item6">
        <c:url value="/resources/image/carbohydrates.jpg" var="carbogydrates"/>
        <img src="${carbogydrates}" alt="">
        <p>Углеводы источник энергии или ..... ?</p>
        <p>Понятия «углевод» и “сахар” – не одно и то же. Сахар – это условное обиходное понятие, используемое в основном
            в отношении сахарозы (т.н. столовый сахар), а также других водорастворимых простых углеводов со сладким
            вкусом (моно- и дисахариды, такие как глюкоза, фруктоза, лактоза, мальтоза). Наш организм, а в особенности
            мозг, нуждается в постоянном снабжении глюкозой, обеспечивающей эффективность и результативность его работы.
            При длительном недостатке углеводов организм начинает синтезировать глюкозу из собственных белков, из-за
            чего заметно снижается его защитная способность в отношении факторов внешней среды. Глюкоза – основное
            «топливо» для большинства клеток тела. Она откладывается в печени и мышцах в виде гликогена. Гликоген
            печени используется</p>
        <c:url value="/article/4" var="four"/>
        <p><a href="${four}">читать дальше</a></p>
    </div>
    <div id="item7"></div>
    <div id="item8"></div>
    <div id="item9">
        <div id="item9a">SLIM BODY</div>
        <div><i class="far fa-copyright" id="copy"></i></div>
    </div>
    <div id="year">2020</div>
</div>
</body>
</html>