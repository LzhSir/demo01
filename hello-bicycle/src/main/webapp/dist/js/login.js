const usernameIn = $('#usernameIn');
const passwordIn = $('#passwordIn');

usernameIn.keydown(function(e) {
    if (e.keyCode === 13) {
        loginSubmit();
    }
})

passwordIn.keydown(function(e) {
    if (e.keyCode === 13) {
        loginSubmit();
    }
})

$('#loginBtn').on('click', function () {
    loginSubmit();
});

function loginSubmit() {
    let username = usernameIn.val();
    let password = passwordIn.val();
    if (username === '' || password === '') {
        alert("请填写用户名密码");
        return;
    }
    $.ajax({
        type: "post",
        url: "login",
        dataType: "json",
        data: {
            username: username,
            password: password
        },
        success: function(res){
            if (res.code === 20000) {
                window.location.href = 'index.jsp';
            } else {
                alert(res.message);
            }
        }
    });
}
