<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Set account</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Страница добавления аккаунта</h2>
    <label id="errLabel"></label>
    <form >
        <div class="form-group">
            <label for="username">Login:</label>
            <input type="username" class="form-control" id="username" placeholder="Enter username" name="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
        </div>
        <div class="form-group">
            <label for="password2">Reenter password:</label>
            <input type="password" class="form-control" id="password2" placeholder="Reenter password" name="password2">
        </div>
        <div class="form-group">
            <label for="prefix">Префикс базы данных:</label>
            <input type="prefix" class="form-control" id="prefix" placeholder="Префикс базы данных" name="prefix">
        </div>

        <%--<div class="checkbox">
            <label><input type="checkbox" name="remember"> Remember me</label>
        </div>--%>
        <button type="button" onclick="RegNewUser()" class="btn btn-default">Зарегистрировать</button>
        <button type="button" onclick="ValidLgn()" class="btn btn-default">Валидация логина</button>
    </form>
</div>
<script>
    var service = 'http://localhost:8080/';
    var lgn = '';
    var pwd1 = '';
    var pwd2 = '';
    var criptPwd = '';
    var prfx = '';
    var arrUsers = [];

    var RegNewUser = function () {
        lgn = $("#username").val();
        pwd1 = $("#password").val();
        pwd2 = $("#password2").val();
        prfx = $("#prefix").val();

        console.log('AddLgnPsw, ' + 'lgn=' + lgn + ', pwd1=' + pwd1 + ', pwd2=' + pwd2);
        if (pwd1 == pwd2){
            console.log('validation cuccesful, add new user into base');
            GetArrUsers();
        } else {
            console.log('validation not cuccesful');
            $('#errLabel').text('Пароль повторен не точно!');
            $('#errLabel').css("color", "red");
        }
    };

    var GetArrUsers = function () {
        $.ajax({
            type: 'GET',
            url: service + "users/getusersbylgn/" + lgn,
            dataType: 'json',
            async: false,
            success: function (result) {
                var stringData = JSON.stringify(result);
                console.log(stringData);
                arrUsers = JSON.parse(stringData);
                if (arrUsers.length == 0) {
                    AddNewUser();
                } else {
                    console.log('alredy there is that login')
                    $('#errLabel').text('Такой логин уже есть');
                    $('#errLabel').css("color", "red");
                }
            },
            error: function (jqXHR, testStatus, errorThrown) {
                console.log('error getting users by login')
            }
        });
    };

    var AddNewUser = function () {

        var objNewUser = {
            'login' : lgn,
            'password' : pwd1,
            'role_name' : 'ROLE_USER',
            'prefix' : prfx
        };
        $.ajax({
            type: 'POST',
            url: service + "users/add",
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(objNewUser),
            dataType: 'json',
            async: false,
            success: function (result) {
                console.log('Success add new user');
                $('#errLabel').text('Пользователь успешно зарегистрирован!');
                $('#errLabel').css("color", "green");
                AuthUser();
            },
            error: function (jqXHR, testStatus, errorThrown) {
                console.log('Failed add new user');
            }
        });
    };

    var AuthUser = function () {
        $.ajax({
            type: 'POST',
            url: service + "login",
            data: jQuery.param({ username: $("#username").val(), password : $("#password").val()}) ,
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function (result) {
                console.log('Login user success');
                $('#errLabel').text('Аутентификация прошла успешно');
                $('#errLabel').css("color", "green");
                //   setTimeout('window.open(\'http://localhost:8080/pet\', \'_blank\')', 3000);
                 setTimeout( 'location="http://localhost:8080/cdr";', 3000 );

            },
            error: function (jqXHR, testStatus, errorThrown) {
                console.log('Failed login user');
            }
        });
    };

    var ValidLgn = function () {
        console.log('ValidLgn');
        lgn = $("#username").val();
        $.ajax({
            type: 'GET',
            url: service + "users/getusersbylgn/" + lgn,
            dataType: 'json',
            async: false,
            success: function (result) {
                var stringData = JSON.stringify(result);
                console.log(stringData);
                arrUsers = JSON.parse(stringData);
                if (arrUsers.length == 0) {
                    $('#errLabel').text('Такой логин не занят');
                    $('#errLabel').css("color", "green");
                } else {
                    console.log('alredy there is that login')
                    $('#errLabel').text('Такой логин уже есть');
                    $('#errLabel').css("color", "red");
                }
            },
            error: function (jqXHR, testStatus, errorThrown) {
                console.log('error getting users by login')
            }
        });
    };

</script>
</body>
</html>

