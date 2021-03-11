<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div style="display: flex; flex-wrap: nowrap;">
    <c:url value="/resources/image/body-params-1.png" var="bodyParameters"/>
    <img src="${bodyParameters}" alt="">
    <form style="margin-left: 10px; display: flex; flex-wrap: nowrap;" method="post" id="formParameters">
        <div>
            <label>1 Вес<br><input name="weight"></label>
            <p></p>
            <label>2 Обхват шеи<br><input name="neckGirth"></label>
            <p></p>
            <label>3 Обхват груди<br><input name="chestGirth"></label>
            <p></p>
            <label>4 Обхват под грудью<br><input name="underBus"></label>
            <p></p>
            <label>5 Обхват талии<br><input name="waist"></label>
            <p></p>
        </div>
        <div style="margin-left: 15px;">
            <label>6 Обхват живота<br><input name="abdominalGirth"></label>
            <p></p>
            <label>7 Обхват бедер<br><input name="hipGirth"></label>
            <p></p>
            <label>8 Обхват бедра<br><input name="thighGirth"></label>
            <p></p>
            <label>9 Обхват под коленом<br><input name="girthUnderTheKnee"></label>
            <p></p>
            <label>10 Обхват икры<br><input name="calfGirth"></label>
            <p></p>
            <input type="submit" value="Сохранить">
        </div>
        <div style="margin-left: 15px;">
            <label>11 Обхват лодыжки<br><input name="ankleGirth"></label>
            <p></p>
            <label>12 Обхват плеча<br><input name="shoulderGirth"></label>
            <p></p>
            <label>13 Обхват предплечья<br><input name="forearmGirth"></label>
            <p></p>
            <label>13 Обхват запястья<br><input name="wristGirth"></label>
            <p></p>
        </div>
    </form>
</div>
