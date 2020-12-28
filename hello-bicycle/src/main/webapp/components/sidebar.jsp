<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="${pageContext.request.contextPath}/index.jsp" class="brand-link">
        <img src="${pageContext.request.contextPath}/dist/images/logo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
             style="opacity: .8">
        <span class="brand-text font-weight-light">靓仔单车</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="${pageContext.request.contextPath}/dist/images/avatar.jpg" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <a href="#" class="d-block">${online.nickname}</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <li class="nav-item">
                    <a id="index" href="${pageContext.request.contextPath}/index.jsp" class="nav-link">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            仪表盘
                            <i class="right fas"></i>
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a id="info" href="${pageContext.request.contextPath}/info.jsp" class="nav-link">
                        <i class="nav-icon fas fa-car"></i>
                        <p>
                            车辆信息
                            <span class="right badge badge-danger">上新</span>
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a id="rented" href="${pageContext.request.contextPath}/rented.jsp" class="nav-link">
                        <i class="nav-icon fas fa-home"></i>
                        <p>
                            我的骑行
                        </p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>

<script>
    let active = "<%= request.getParameter("active") %>";
    let item = document.getElementById(active);
    item.classList.add('active');
</script>
