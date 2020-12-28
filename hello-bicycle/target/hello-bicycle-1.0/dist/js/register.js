const nicknameIn = $('#nicknameIn');
const usernameIn = $('#usernameIn');
const passwordIn = $('#passwordIn');

nicknameIn.keydown(function(e) {
    if (e.keyCode === 13) {
        registerSubmit();
    }
})

usernameIn.keydown(function(e) {
    if (e.keyCode === 13) {
        registerSubmit();
    }
})

passwordIn.keydown(function(e) {
    if (e.keyCode === 13) {
        registerSubmit();
    }
})

$('#registerBtn').on('click', function () {
    registerSubmit();
});

function registerSubmit() {
    let nickname = nicknameIn.val();
    let username = usernameIn.val();
    let password = passwordIn.val();
    if (nickname === '' || username === '' || password === '') {
        alert("请填写用户信息");
        return;
    }
    if (!$('#agree').prop('checked')) {
        alert("请同意使用条款");
        return;
    }
    $.ajax({
        type: "post",
        url: "register",
        dataType: "json",
        data: {
            nickname: nickname,
            username: username,
            password: password
        },
        success: function(res){
            if (res.code === 20000) {
                alert('注册成功，请登录');
                window.location.href = 'login.jsp';
            } else {
                alert(res.message);
            }
        }
    });
}
