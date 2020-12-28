<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>靓仔单车 | 车辆信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="plugins/font-awesome/css/all.min.css">
    <!-- Bootstrap Table -->
    <link rel="stylesheet" href="plugins/bootstrap-table/css/bootstrap-table.min.css">
    <!-- AdminLTE -->
    <link rel="stylesheet" href="dist/css/adminlte.min.css">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

    <!-- Navbar -->
    <jsp:include page="components/navbar.jsp"/>

    <!-- Main Sidebar Container -->
    <jsp:include page="components/sidebar.jsp">
        <jsp:param name="active" value="info"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>车辆信息</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">首页</a></li>
                            <li class="breadcrumb-item active">车辆信息</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <c:if test="${online.roles == 'admin'}">
                                <div class="card-header">
                                    <button id="createBtn" type="button" class="btn btn-success" data-toggle="modal" data-target="#formModal">新增</button>
                                </div>
                            </c:if>
                            <div class="card-body">
                                <table id="infoTable"></table>
                            </div>
                        </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->

            <!-- rent modal -->
            <div class="modal fade" id="rentModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">租借</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <div class="form-group row">
                                    <label>单车类型</label>
                                    <label for="hireBrand"></label><input id="hireBrand" class="form-control" disabled/>
                                </div>
                                <div class="form-group row">
                                    <label>描述</label>
                                    <label for="hireModel"></label><input id="hireModel" class="form-control" disabled/>
                                </div>
                                <div class="form-group row">
                                    <label>租金</label>
                                    <label for="hireRent"></label><input id="hireRent" class="form-control" disabled/>
                                </div>
                                <div class="form-group row">
                                    <label>车辆编号</label>
                                    <label for="hireLicence"></label><input id="hireLicence" class="form-control" disabled/>
                                </div>
                                <div class="form-group row">
                                    <label>骑行时间</label>
                                    <label for="hireRentTime"></label><input id="hireRentTime" class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button id="hireSubmit" type="button" class="btn btn-primary">确定</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.rent modal -->

            <!-- form modal -->
            <div class="modal fade" id="formModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">车辆信息</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <div class="form-group row">
                                    <label>单车类型</label>
                                    <label for="brandIn"></label><input id="brandIn" class="form-control"/>
                                </div>
                                <div class="form-group row">
                                    <label>描述</label>
                                    <label for="modalIn"></label><input id="modalIn" class="form-control"/>
                                </div>
                                <div class="form-group row">
                                    <label>租金</label>
                                    <label for="rentIn"></label><input id="rentIn" class="form-control"/>
                                </div>
                                <div class="form-group row">
                                    <label>车辆编号</label>
                                    <label for="licenceIn"></label><input id="licenceIn" class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button id="formSubmit" type="button" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.update modal -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Footer -->
    <jsp:include page="components/footer.jsp"/>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Bootstrap Table -->
<script src="plugins/bootstrap-table/js/bootstrap-table.min.js"></script>
<!-- AdminLTE -->
<script src="dist/js/adminlte.min.js"></script>
<!-- Info -->
<script>const roles = '${online.roles}'</script>
<script src="dist/js/info.js"></script>
</body>
</html>
