let countWord = 0;

document.addEventListener('DOMContentLoaded', function () {
    var addNewModalBtn = document.querySelector("#addNewModalBtn");
    addNewWordCloseBtn = document.querySelector("#addNewWordCloseBtn");
    checkModalBtn = document.querySelector("#checkModalBtn");
    rusEngBtn = document.querySelector("#rus-engBtn");
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
        engRus = false;
        $('#checkWordModal').modal('hide');
        $('#checkWordMainModal').modal('show');
        checkMain(false);
    })

    acceptCheck.addEventListener("click", function () {
        for (let i = 1; i < countWord; i++) {
            let element = document.getElementById(`input${i}`);
            let word = element.value;
            let translate = element.getAttribute("translateWord");
            alert(word);
            alert(translate);
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

async function checkMain(engRus) {
    let data = await checkMainRest();

    countWord = 0;
    data.forEach(function (word) {


        let id = word.id;
        let rus = word.rus;
        let eng = word.eng;
        let addTd = `<tr>
                    <td>${id}</td>
                    <td id="russianWord${id}">${rus}</td>
                    <td id="englishWord${id}"><input id="input${id}" translateWord="${eng}" ></td>
                </tr>'`;
        $('#checkWordTbody').append(addTd);
        countWord++;
    })
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
            // alert(data);
            dataFromJSON = data;
        },
        error: function () {
            alert("Ошибка выполнения");
        }
    });
    setTimeout(4000);
    return dataFromJSON;
}



