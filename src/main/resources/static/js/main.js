let countWord = 0;

document.addEventListener('DOMContentLoaded', function () {
    var addNewModalBtn = document.querySelector("#addNewModalBtn");
    addNewWordCloseBtn = document.querySelector("#addNewWordCloseBtn");
    checkModalBtn = document.querySelector("#checkModalBtn");
    rusEngBtn = document.querySelector("#rus-engBtn");
    engRusBtn = document.querySelector("#eng-rusBtn");
    acceptCheck = document.querySelector("#acceptCheck");


    let engRus = new Boolean();

    addNewModalBtn.addEventListener("click", function () {
        $('#startModal').modal('hide');
        $('#addNewModal').modal('show');

    })
    addNewWordCloseBtn.addEventListener("click", function () {
        addNewWordByAddNewModal();
    })

    checkModalBtn.addEventListener("click", function () {
        $('#startModal').modal('hide');
        $('#checkWordModal').modal('show');


    })

    rusEngBtn.addEventListener("click", function () {
        getCheckWord(false);
    })

    engRusBtn.addEventListener("click", function () {
        getCheckWord(true);
    })

    acceptCheck.addEventListener("click", function () {
        for (let i = 1; i < countWord; i++) {
            let element = document.getElementById(`input${i}`);
            let word = element.value;
            let translate = element.getAttribute("translateWord");
        }
    })
})


function addNewWordByAddNewModal() {
    let rus = document.getElementById("rusInput").value;
    let eng = document.getElementById("engInput").value;
    let word = {
        rus: rus,
        eng: eng
    }
    let serializedWord = JSON.stringify(word);
    $.ajax({
        url: 'http://localhost:8080/api/addWordByAddNewModal',
        type: 'POST',
        contentType: "application/json;charset=UTF-8",
        data: serializedWord,
        success: function (data) {
            $('#addNewModal').modal('hide');
            $(".modal-backdrop.show").hide();
            alert("Success!");
        },
        error: function () {
            alert("Ошибка выполнения");
        }
    });
}

async function checkMain(engRus, count) {
    let data;
    if (count != null) {
        data = await checkMainRestWithCount(count);
    } else {
        data = await checkMainRest(count);
    }

    countWord = 0;
    data.forEach(function (word) {


        let id = word.id;
        let rus = word.rus;
        let eng = word.eng;
        let addTd;
        if (engRus) {
            addTd = `<tr>
                    <td id="russianWord${id}">${rus}</td>
                    <td id="englishWord${id}"><input id="input${id}" translateWord="${eng}" ></td>
                </tr>'`;
        } else {
            addTd = `<tr>
                    <td id="englishWord${id}">${eng}</td>
                    <td id="russianWord${id}"><input id="input${id}" translateWord="${rus}" ></td>
                </tr>'`;
        }
        $('#checkWordTbody').append(addTd);
        countWord++;
    })
}

async function checkMainRestWithCount(count) {
    let dataFromJSON;
    let url = `http://localhost:8080/api/getAll/${count}`;
    $.ajax({
        url: url,
        cache: false,
        type: 'GET',
        contentType: "application/json;charset=UTF-8",
        dataType: 'json',
        async: false,
        success: function (data) {
            dataFromJSON = data;
        },
        error: function () {
            alert("Ошибка выполнения");
        }
    });
    setTimeout(4000);
    return dataFromJSON;
}

async function checkMainRest() {
    let dataFromJSON;
    $.ajax({
        url: 'http://localhost:8080/api/getAll',
        cache: false,
        type: 'GET',
        contentType: "application/json;charset=UTF-8",
        dataType: 'json',
        async: false,
        success: function (data) {
            dataFromJSON = data;
        },
        error: function () {
            alert("Ошибка выполнения");
        }
    });
    setTimeout(4000);
    return dataFromJSON;
}

function getCheckWord(isEngRus) {
    $('#checkWordModal').modal('hide');
    $('#checkWordMainModal').modal('show');
    let element = document.getElementById('countOfWord');
    let count = element.value;
    if (count == 0) {
        count = null;
    }
    checkMain(isEngRus, count);
}



