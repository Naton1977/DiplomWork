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
    <link  href="${home}" rel="stylesheet"/>

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
        <p><span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquam architecto consequatur
                dolorem necessitatibus praesentium rerum sapiente voluptas? Ad aliquam doloribus, ducimus excepturi
                impedit ipsam nemo nisi obcaecati quidem rem totam.</span><span>Alias asperiores cumque distinctio
                eveniet explicabo facilis inventore laudantium maiores maxime nam, ratione sunt velit veritatis?
                Amet beatae dignissimos dolor enim eos explicabo in libero pariatur quas quos, soluta ut!</span>
            <span>Commodi corporis excepturi iure libero magnam, magni maxime nam, nemo neque nisi obcaecati
                    quia ratione recusandae sint sunt temporibus ullam voluptate.</span></p>
        <a href="#">читать дальше</a>
    </div>
    <div id="item4">
        <c:url value="/resources/image/proteins.jpg" var="proteins"/>
        <img src="${proteins}" alt="">
        <p>Что такое белки и зачем они нужны</p>
        <p><span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis dolores eveniet fugiat quia
                quis! At consectetur corporis earum facere illo illum molestiae optio pariatur quisquam, reiciendis
                rem suscipit veritatis vitae!</span><span>Animi aperiam corporis cumque cupiditate ea enim error
                exercitationem fugiat, ipsam laudantium minima minus natus nihil odit officia provident quisquam
                ratione repellat repudiandae saepe tenetur vel voluptate voluptatibus! Esse, quam?</span>
            <span>Alias assumenda commodi ea impedit labore placeat possimus quas, recusandae rem repudiandae.
                    Accusantium aliquam, cumque dignissimos dolorem, ea id iste iure maiores necessitatibus obcaecati
                    officia quae quasi quia, sint voluptatem?</span><span>Accusantium culpa eveniet, expedita illo
                    impedit, iure molestiae nisi placeat quaerat repellendus tempora tempore unde? In ipsa laborum
                    magni maiores sint, temporibus?Animi aperiam corporis cumque</span></p>

        <p><a href="#">читать дальше</a></p>
    </div>
    <div id="item5">
        <c:url value="/resources/image/fats.jpg" var="fats"/>
        <img src="${fats}" alt="">
        <p>Нужны ли жиры организму человека ?</p>
        <p><span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A cumque cupiditate deserunt dolore error
            excepturi exercitationem facere illo, impedit laboriosam modi odio omnis reprehenderit sint soluta
            temporibus voluptas! Ab, iure.</span><span>Accusamus adipisci aliquam doloremque eveniet excepturi
            fuga incidunt inventore itaque laborum maiores, numquam odio repellat rerum sint sit tempora unde
            voluptas voluptatem. Inventore iure nemo, reiciendis temporibus vel veniam voluptates?</span><span>
            Accusantium aliquid atque consectetur consequatur dicta dolor dolore dolorem eaque eius ex expedita
            harum hic impedit in libero, minima, minus nam necessitatibus odio quae quibusdam quod reiciendis saepe
            veniam vitae.Ad architecto atque deserunt itaque quos, voluptatum.</span></p>
        <p><a href="#">читать дальше</a></p>
    </div>
    <div id="item6">
        <c:url value="/resources/image/carbohydrates.jpg" var="carbogydrates"/>
        <img src="${carbogydrates}" alt="">
        <p>Углеводы источник энергии или ..... ?</p>
        <p><span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, ad atque dolores eius eligendi hic
            itaque iure labore magnam nemo non quaerat quos recusandae, repellendus reprehenderit repudiandae tempora
            voluptates? Ab!</span><span>Culpa dicta exercitationem, expedita illum, ipsa ipsum maiores molestiae natus
            numquam officiis repellendus vel voluptas? Aspernatur dolore doloribus exercitationem fuga ipsa nisi quaerat.
            Aut dicta fuga nisi nostrum officiis voluptates.</span><span>Inventore molestias quaerat temporibus.
            Assumenda cumque earum, facilis ipsum laboriosam mollitia non ullam? Animi beatae dolor eius ex facilis
            modi odio tenetur vero! Ad architecto atque deserunt itaque quos, voluptatum.</span><span>Delectus dolores
            eligendi enim obcaecati quia! Ab ad asperiores ducimus exercitationem fugiat fugit laborum odit quibusdam
            vel veritatis? Accusantium consequatur corporis culpa eaque error </span></p>
        <p><a href="#">читать дальше</a></p>
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