window.addEventListener("load", () => {
        let widthTd1;
        let widthTd2;
        let widthTd3;
        let widthTd4;
        let widthTd5;
        let widthTd6;
        let widthTd7;
        let productValue = '';
        let productSearchArray = [];
        let sendTime;
        let products;
        let ration;
        let dailyCalorieContent = 0;

        let createDietPageHead = function () {
            document.getElementById("column1").innerHTML = "";
            let containerWidth = document.getElementById("column1").offsetWidth;
            let tableWidth = (containerWidth / 100) * 98;
            widthTd1 = (containerWidth / 100) * 8;
            widthTd2 = (containerWidth / 100) * 46;
            widthTd3 = (containerWidth / 100) * 8;
            widthTd4 = (containerWidth / 100) * 8;
            widthTd5 = (containerWidth / 100) * 8;
            widthTd6 = (containerWidth / 100) * 12;
            widthTd7 = (containerWidth / 100) * 8;
            let tmp = '';
            tmp += '<div id="pageDietHead">Дневник питания</div>';
            tmp += '<p id="titleToday">За сегодня</p>';
            tmp += '<table id="pageDietTable" style="width: ' + tableWidth + 'px">';
            tmp += '<thead id="pageDietThead">';
            tmp += '<tr>';
            tmp += '<td style="width: ' + widthTd1 + 'px">Время</td>';
            tmp += '<td style="width: ' + widthTd2 + 'px">Наименование</td>';
            tmp += '<td style="width: ' + widthTd3 + 'px">Вес</td>';
            tmp += '<td style="width: ' + widthTd4 + 'px">Белки</td>';
            tmp += '<td style="width: ' + widthTd5 + 'px">Жиры</td>';
            tmp += '<td style="width: ' + widthTd6 + 'px">Углеводы</td>';
            tmp += '<td style="width: ' + widthTd7 + 'px">Ккалл</td>';
            tmp += '</tr>';
            tmp += '</thead>';
            tmp += '<tbody id="pageDietTBody"></tbody>';
            tmp += '<tfoot id="pageDietTFoot"></tfoot>'
            tmp += '</table>';
            tmp += '<div id="containerProductSearch" style="width: ' + tableWidth + 'px">';
            tmp += '<input type="text" id="time" style="width: ' + (widthTd1 - 10) + 'px; height: 22px"/>';
            tmp += '<div id="productSearch" style="width: ' + widthTd2 + 'px">';
            tmp += '<input type="text" id="inputSearch" style="width: ' + (widthTd2 - 10) + 'px; height: 22px" />';
            tmp += '<div id="containerSelect"></div>';
            tmp += '<p id="description">Введите первые 3-4 буквы названия продукта и выберите его из списка</p>';
            tmp += '</div>';
            tmp += '<input type="text" id="inputWeight" style="width: ' + (widthTd3 - 10) + 'px; height: 22px"/>';
            tmp += '<button id="SubmitSearch">Отправить</button>';
            tmp += '</div>';
            document.getElementById("column1").innerHTML = tmp;
            document.getElementById("inputSearch").focus();
        }


        let createLocalTime = function () {
            let hours = new Date().getHours();
            let minutes = new Date().getMinutes();

            if (hours < 10) {
                hours = "0" + hours;
            }

            if (minutes < 10) {
                minutes = "0" + minutes;
            }
            let time = hours + " " + ":" + " " + minutes;
            sendTime = hours + ":" + minutes;
            let timeContainer = document.getElementById("time");
            timeContainer.value = time;
        }


        let addSearchTableEvent = function (e) {
            if (e.target.id === "item1a") {
                createDietPageHead();
                createLocalTime();
                addFullProductList();
                clickButtonOnInputField();
            }
        }


        let addFullProductList = function () {
            const xhr1 = new XMLHttpRequest();
            xhr1.open('GET', 'http://localhost:8080/api/v1/product', false);
            xhr1.send();
            products = JSON.parse(xhr1.responseText);
        }

        let searchProduct = function () {
            if (productValue.length < 3) {
                let div = document.getElementById("containerSelect");
                div.innerHTML = '';
            }

            if (productValue.length >= 3) {
                for (let i = 0; i < products.length; i++) {
                    let charAp = productValue.charAt(0).toLocaleUpperCase();
                    let newProductValue = charAp + productValue.substring(1, productValue.length);
                    let index = products[i].productName.includes(productValue);
                    let index2 = products[i].productName.includes(newProductValue);
                    if (index || index2) {
                        let productPresent = false;
                        for (let j = 0; j < productSearchArray.length; j++) {
                            if (productSearchArray[j].productName === products[i].productName) {
                                productPresent = true;
                            }
                        }
                        if (!productPresent) {
                            productSearchArray.push(products[i]);
                        }
                    }
                    if (i === (products.length - 1)) {
                        if (productSearchArray.length > 0) {
                            let tmp = '';
                            if (productSearchArray.length < 5) {
                                tmp += '<select id="selectProductName" style="width: ' + (widthTd2 - 10) + 'px" size="' + (productSearchArray.length + 1) + '">';
                            } else {
                                tmp += '<select id="selectProductName" style="width: ' + (widthTd2 - 10) + 'px" size="5">';
                            }

                            for (let j = 0; j < productSearchArray.length; j++) {
                                tmp += '<option value="' + productSearchArray[j].productId + '">' + productSearchArray[j].productName + '</option>';
                            }
                            tmp += '</select>'
                            let div = document.getElementById("containerSelect");
                            div.innerHTML = tmp;

                            let select = document.getElementById("selectProductName");
                            select.onchange = function () {
                                let value = select.value;
                                let valueInt = parseInt(value);
                                for (let j = 0; j < products.length; j++) {
                                    if (valueInt === products[j].productId) {
                                        let input = document.getElementById("inputSearch");
                                        input.value = products[j].productName;
                                        document.getElementById("containerSelect").innerHTML = '';
                                        document.getElementById("inputWeight").focus();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        let clickButtonOnInputField = function () {
            let input = document.getElementById("inputSearch");
            input.onkeypress = function () {
                let searchValue = input.onkeypress.arguments[0].key;
                productValue += searchValue;
                searchProduct();
            }
        }

        let clickButton = function (e) {
            if (e.keyCode === 8 && e.target.id === "inputSearch") {
                let input = document.getElementById("inputSearch");
                productValue = input.value;
                productSearchArray.length = 0;
                searchProduct();
            }
        }


        let pushButtonSubmitSearchProduct = function (e) {
            if (e.target.id === "SubmitSearch") {
                let weight = document.getElementById("inputWeight").value;
                let weightInt = parseInt(weight);
                if (weightInt > 0) {
                    let productName = document.getElementById("inputSearch").value;
                    if (productName !== '') {
                        for (let i = 0; i < products.length; i++) {
                            if (productName === products[i].productName) {
                                let now = new Date();
                                let year = now.getFullYear();
                                let month = now.getMonth() + 1;
                                let day = now.getDate();
                                let time = document.getElementById("time").value;
                                let timeIndex = time.indexOf(":");
                                let hours = time.substring(0, timeIndex);
                                let minutes = time.substring((timeIndex + 1), time.length);
                                const product = {
                                    productId: products[i].productId,
                                    weight: weightInt,
                                    time: year + "-" + month + "-" + day + "@" + hours.trim() + ":" + minutes.trim()
                                };

                                let sendJson = JSON.stringify(product);
                                const xhr1 = new XMLHttpRequest();
                                xhr1.open('POST', 'http://localhost:8080/api/v1/product/create/newProductToTheDailyDiet', false);
                                xhr1.setRequestHeader('Content-Type', 'application/json');
                                xhr1.send(sendJson);
                                productValue = '';
                                productSearchArray.length = 0;
                                createDietPageHead();
                                createLocalTime();
                                loadUserDiet();
                                clickButtonOnInputField();
                            }
                        }
                    }
                }
            }
        }


        let loadUserDiet = function () {
            let tmp = "";
            const xhr1 = new XMLHttpRequest();
            xhr1.open('GET', 'http://localhost:8080/api/v1/product/allRation', false);
            xhr1.send();
            ration = JSON.parse(xhr1.responseText);
            for (let i = 0; i < ration.length; i++) {
                let now = new Date();
                let dateAdded = new Date(ration[i].dateAdded);
                let year = now.getFullYear();
                let month = now.getMonth() + 1;
                let day = now.getDate();
                let hoursDateAdded = dateAdded.getHours();
                let minutesDateAdded = dateAdded.getMinutes();
                if (hoursDateAdded < 10) {
                    hoursDateAdded = "0" + hoursDateAdded;
                }


                if (minutesDateAdded < 10) {
                    minutesDateAdded = "0" + dateAdded.getMinutes();
                }
                let timeDateAdded = hoursDateAdded + " : " + minutesDateAdded;

                if (year === dateAdded.getFullYear() && month === (dateAdded.getMonth() + 1) && day === dateAdded.getDate()) {
                    tmp += createDietTableTBody(timeDateAdded, ration[i].productTitle, ration[i].productWeight, ration[i].productProteins, ration[i].productFats, ration[i].productCarbohydrates, ration[i].calorieContent)
                    dailyCalorieContent += Number(ration[i].calorieContent);
                }

            }
            insertProduct(tmp);
            createDietTableTFoot();
        }


        let createDietTableTBody = function (time, title, weight, proteins, fats, carbohidrates, calorieContent) {
            let tmp = "";
            tmp += "<tr>";
            tmp += "<td>" + time + "</td>";
            tmp += "<td>" + title + "</td>";
            tmp += "<td>" + weight + "</td>";
            tmp += "<td>" + proteins + "</td>";
            tmp += "<td>" + fats + "</td>";
            tmp += "<td>" + carbohidrates + "</td>";
            tmp += "<td>" + calorieContent + "</td>";
            tmp += "</tr>";
            return tmp;
        }

        let createDietTableTFoot = function () {
            let tmp = "";
            tmp += "<tr>";
            tmp += "<td></td>";
            tmp += "<td></td>";
            tmp += "<td></td>";
            tmp += "<td></td>";
            tmp += "<td></td>";
            tmp += "<td></td>";
            tmp += "<td style='font-weight: bold'>Итого :</td>";
            tmp += "</tr>";
            if (dailyCalorieContent !== 0) {
                let newCalorieContent = Math.floor(dailyCalorieContent * 100) / 100;
                tmp += "<tr>";
                tmp += "<td></td>";
                tmp += "<td></td>";
                tmp += "<td></td>";
                tmp += "<td></td>";
                tmp += "<td></td>";
                tmp += "<td></td>";
                tmp += "<td>" + newCalorieContent + "</td>";
                tmp += "</tr>";
            }

            document.getElementById("pageDietTFoot").innerHTML = tmp;
        }


        let insertProduct = function (tmp) {
            let tBody = document.getElementById("pageDietTBody").innerHTML = tmp;
        }


        let clickButtonRation = function (e) {
            if (e.target.id === "item1a") {
                createDietPageHead();
                createLocalTime();
                addFullProductList();
                clickButtonOnInputField();
                loadUserDiet();
            }
        }


        createDietPageHead();
        createLocalTime();
        addFullProductList();
        clickButtonOnInputField();
        loadUserDiet();
        document.body.addEventListener("click", addSearchTableEvent);
        document.body.addEventListener("keyup", clickButton);
        document.body.addEventListener("click", pushButtonSubmitSearchProduct);
        document.body.addEventListener("click", clickButtonRation);

    }
)