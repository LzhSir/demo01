<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>靓仔单车 | 注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="plugins/font-awesome/css/all.min.css">
    <!-- AdminLTE -->
    <link rel="stylesheet" href="dist/css/adminlte.min.css">
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="login.jsp">靓仔单车</a>
    </div>
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
            <p class="login-box-msg">注册新用户</p>

            <div class="input-group mb-3">
                <label for="nicknameIn"></label><input id="nicknameIn" type="text" class="form-control" placeholder="昵称">
                <div class="input-group-append">
                    <div class="input-group-text">
                        <span class="fas fa-address-book"></span>
                    </div>
                </div>
            </div>
            <div class="input-group mb-3">
                <label for="usernameIn"></label><input id="usernameIn" type="text" class="form-control" placeholder="账号">
                <div class="input-group-append">
                    <div class="input-group-text">
                        <span class="fas fa-user"></span>
                    </div>
                </div>
            </div>
            <div class="input-group mb-3">
                <label for="passwordIn"></label><input id="passwordIn" type="password" class="form-control" placeholder="密码">
                <div class="input-group-append">
                    <div class="input-group-text">
                        <span class="fas fa-lock"></span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-8">
                    <div class="icheck-primary">
                        <input type="checkbox" id="agree">
                        <label for="agree">
                            我已同意<a href="#">《使用条款》</a>
                        </label>
                    </div>
                </div>
            </div>

            <div class="social-auth-links text-center mb-3">
                <button id="registerBtn" class="btn btn-success btn-block">注册</button>
            </div>

            <p class="mb-0">
                <a href="login.jsp">已有帐号，前往登录</a>
            </p>
        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- AdminLTE -->
<script src="dist/js/adminlte.min.js"></script>
<!-- Login -->
<script src="dist/js/register.js"></script>
</body>
</html>
