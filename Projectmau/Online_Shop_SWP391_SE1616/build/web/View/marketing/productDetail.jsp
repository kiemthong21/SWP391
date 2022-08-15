<%-- 
    Document   : create
    Created on : Jun 14, 2022, 8:50:23 PM
    Author     : Hieuhihi
--%>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.7/dist/flowbite.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="View/marketing/css/sb-admin-2.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this page -->
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <link href="View/marketing/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="View/marketing/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        <link rel="stylesheet" href="${pageContext.request.contextPath}/View/marketing/richtexteditor/rte_theme_default.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/View/marketing/richtexteditor/rte.js"></script>
        <script>RTE_DefaultConfig.url_base = '${pageContext.request.contextPath}/View/marketing/richtexteditor/richtexteditor'</script>
        <script type="text/javascript" src='${pageContext.request.contextPath}/View/marketing/richtexteditor/plugins/all_plugins.js'></script>
    </head>
    <body id="page-top">
        <div id="wrapper">
            <ul class="navbar-nav bg-gradient-info sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="Homepage">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Shopping Online</div>
                </a>
                <hr class="sidebar-divider d-none d-md-block">


                <!-- Nav Item - Pages Collapse Menu -->


                <!-- Divider -->
                

                <li class="nav-item active">
                    <a class="nav-link" href="ProductMarketing?service=list">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Product List</span></a>
                </li>
                
                <li class="nav-item active">
                    <a class="nav-link" href="postmarketing">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Post</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="customers">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Customer</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="sliders">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Slider list</span></a>
                </li>
                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>
                    <ul class="navbar-nav ml-auto">
                        <!-- Nav Item - Messages -->

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.user.name}</span>

                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                 aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <div class="container">
                    <div class="card mx-auto p-5">
                        <div class="mb-3 flex justify-between items-center">
                            <h1 class="text-xl font-medium">Detail product</h1>
                        </div>
                        <div class="mt-5" >
                            <input type="hidden" name="id" value="${requestScope.product.productID}"/>
                            <c:if test="${error!=null}">
                                <div class="p-4 mb-4 text-sm text-red-700 bg-red-100 rounded-lg" role="alert">
                                    <span class="font-medium">Error!</span> ${error}
                                </div>
                            </c:if>
                            <div>
                                <div class="mb-6">
                                    <label for="title" class="block mb-2 text-sm font-medium text-gray-900">Title</label>
                                    <input type="text" disabled id="title" name="title" value="${product.title}" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"  >
                                </div>
                                <div class="mb-6">
                                    <div>
                                        <label for="category" class="block mb-2 text-sm font-medium text-gray-900">Category</label>
                                    </div>
                                    <div disabled id="category" name="category"
                                         class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"  >

                                        <div>${product.category.name}</div>

                                    </div>
                                </div>

                                <div class="mb-6">
                                    <div>
                                        <label for="category" class="block mb-2 text-sm font-medium text-gray-900">Status</label>
                                    </div>
                                    <div disabled id="category" name="category"
                                         class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"  >

                                        <div>${product.status.name}</div>

                                    </div>
                                </div>
                                <img src="${pageContext.request.contextPath}/View/Img/${product.thumbnail}" class="img-fluid" alt="...">
                                <div>
                                    <label for="price" class="block mb-2 text-sm font-medium text-gray-900">price</label>
                                    <div class="preview">
                                        ${product.price}$
                                    </div>
                                </div>
                                <div>
                                    <label for="discount" class="block mb-2 text-sm font-medium text-gray-900">discount</label>
                                    <div class="preview">
                                        ${product.discount}$
                                    </div>
                                </div>
                                <div>
                                    <label for="quantity" class="block mb-2 text-sm font-medium text-gray-900">quantity</label>
                                    <div class="preview">
                                        ${product.quantity}
                                    </div>
                                </div>                
                                <div>
                                    <label for="summary" class="block mb-2 text-sm font-medium text-gray-900">summary</label>
                                    <div class="preview">
                                        ${product.summary}
                                    </div>
                                </div>
                            </div>                       
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../component/Footer.jsp" %>
        </div>
        </div>
        <script src="https://unpkg.com/flowbite@1.4.7/dist/flowbite.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/View/marketing/js/postDetail.js"></script>
    </body>
</html>