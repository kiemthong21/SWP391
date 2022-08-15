<%-- 
    Document   : settingDetails
    Created on : Jul 8, 2022, 8:47:31 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Shopping online</title>

        <!-- Custom fonts for this template -->
        <link href="View/Admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Custom styles for this template -->

        <link href="View/Admin/css/sb-admin-2.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this page -->
        <link href="View/Admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
        <script>
            if (${requestScope.mess == 'success'}) {
                $(window).on('load', function () {
                    $('#myModal').modal('show');
                });
            } else if (${requestScope.mess == 'null'}) {
                $(window).on('load', function () {
                    $('#myModal1').modal('show');
                });
            }
        </script>
    </head>

    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">
            <ul class="navbar-nav bg-gradient-info sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="Homepage">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Setting Details</div>
                </a>
                <hr class="sidebar-divider d-none d-md-block">
                <div class="row mx-4 mb-3">
                    <button class="btn btn-light"  data-bs-toggle="modal" data-bs-target="#registerForm">Add New Setting</button>
                </div>

                <c:if test="${requestScope.mode == 'view'}">
                    <div class="row mt-1 mx-4 mb-2">
                        <a class="nav-link btn btn-light" href="SettingDetails?sid=${requestScope.setting.settingID}&mode=edit">
                            <span>Edit Mode</span></a>        
                    </div>
                </c:if>
                <c:if test="${requestScope.mode == 'edit'}">
                    <div class="row mt-1 mx-4 mb-2">                  
                        <a class="nav-link btn btn-light" href="SettingDetails?sid=${requestScope.setting.settingID}&mode=view">
                            <span>View Mode</span></a>   
                    </div>
                </c:if>
                <hr class="sidebar-divider d-none d-md-block">
                <li class="nav-item active">
                    <a class="nav-link" href="Dashboard">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Admin Dashboard</span></a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link" href="UserList?service=filt&keyword=&searchBy=Name">
                        <i class="fas fa-fw fa-table"></i>
                        <span>User List</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="SettingList">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Setting List</span></a>
                </li>
            </ul>
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
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
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span>

                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="LogOut" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <div class="row container-fluid mt-5">
                            <div class="card mb-2 w-100 shadow">
                                <div class="card-header">
                                    Setting Detail
                                </div>
                                <div class="card-body">
                                    <c:if test="${requestScope.mode == 'view'}">
                                        <p>Type: ${requestScope.setting.group}</p>
                                        <p>Name: ${requestScope.setting.name}</p>
                                        <p>Order: ${requestScope.setting.order}</p>
                                        <p>Status: <c:if test="${requestScope.setting.status}">Active</c:if><c:if test="${!requestScope.setting.status}">Non-Active</c:if></p>
                                    </c:if>
                                    <c:if test="${requestScope.mode == 'edit'}">
                                        <form action="SettingDetails" method="POST">
                                            <input type="hidden" name ="sid" value="${requestScope.setting.settingID}"/>
                                            <div class="form-check">
                                                <label for="">Type:</label>
                                                <select class="form-control" name="group">
                                                    <option value="Customer" <c:if test="${requestScope.setting.group == 'Customer'}">selected</c:if>>Customer</option>
                                                    <option value="Sale" <c:if test="${requestScope.setting.group == 'Sale'}">selected</c:if>>Sale</option>
                                                    <option value="Sale Manager" <c:if test="${requestScope.setting.group == 'Sale Manager'}">selected</c:if>>Sale Manager</option>
                                                    <option value="Marketing" <c:if test="${requestScope.setting.group == 'Marketing'}">selected</c:if>>Marketing</option>
                                                    <option value="Admin" <c:if test="${requestScope.setting.group == 'Admin'}">selected</c:if>>Admin</option>
                                                    </select>                                               
                                                </div>
                                                <div class="form-check">
                                                    <label for="">Name:</label>
                                                    <input type="text" class="form-control mb-2" name ="name" value="${requestScope.setting.name}"/>                                              
                                            </div>
                                            <div class="form-check">
                                                <label for="">Order:</label>
                                                <input type="text" class="form-control mb-2" name ="order" value="${requestScope.setting.order}"/>                                              
                                            </div>    
                                            <div id="editmode" class="form-check">
                                                Status: <select id="editmode" class="form-control mb-2" name="status">
                                                    <option value="7" <c:if test="${requestScope.setting.status}">selected</c:if>>Active</option>
                                                    <option value="6" <c:if test="${!requestScope.setting.status}">selected</c:if>>Non Active</option>
                                                    </select>
                                                </div>
                                                <div id="saveBtn" class="mt-2 mb-5">
                                                    <button type="submit" class="btn btn-outline-secondary text-body mb-2 me-3" style="float:right">Save</button>
                                                </div>
                                            </form> 
                                    </c:if>

                                </div>                                                                   
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->
                </div>


                <!-- Footer -->
                <%@include file="../component/Footer.jsp" %>
                <!-- End of Footer -->

            </div>

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <a class="btn btn-primary" href="LogOut">Logout</a>
                    </div>
                </div>
            </div>
        </div>
        <!--        model-Create-->
        <div class="modal fade" id="registerForm" tabindex="-1" aria-labelledby="registerFormLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="card">
                        <div class="modal-header" style="background-color: gray">
                            <h5 class="modal-title" id="registerFormLabel" style="color: white;"><i class="bi bi-person-plus"></i> Add New Setting</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="card-body p-4">                 
                            <form action="SettingList" method="POST">
                                <input type="hidden" name ="sid" />
                                <div class="form-check">
                                    <label for="">Type:</label>
                                    <select class="form-control" name="group">
                                        <option value="Customer" selected>Customer</option>
                                        <option value="Sale">Sale</option>
                                        <option value="Sale Manager" >Sale Manager</option>
                                        <option value="Marketing" >Marketing</option>
                                        <option value="Admin">Admin</option>
                                    </select>                                                
                                </div>
                                <div class="form-check">
                                    <label for="">Name:</label>
                                    <input type="text" class="form-control mb-2" name ="name" />                                              
                                </div>
                                <div class="form-check">
                                    <label for="">Order:</label>
                                    <input type="text" class="form-control mb-2" name ="order"/>                                              
                                </div>   
                                <div id="editmode" class="">
                                    Status: <select id="editmode" class="form-control mb-2" name="status">
                                        <option value="7" selected>Active</option>
                                        <option value="6" >Non Active</option>
                                    </select>
                                </div>
                                <div id="saveBtn" class="mt-2">
                                    <button type="submit" class="btn btn-outline-secondary text-body mb-2 me-3" style="float:right">Create</button>
                                </div>
                            </form> 
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div id="myModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="card-body p-4">
                        <div class="alert alert-success mt-3 text-center" >
                            <h4 class="alert-heading"><i class="bi bi-check-circle"></i> SUCCESS!</h4>
                            <p>Your changes was save!! </p>                                              
                        </div> 
                    </div>
                </div>
            </div>
        </div>
        <div id="myModal1" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content text-center">
                    <div class="card-body p-4">
                        <div class="alert alert-danger mt-3 text-center" >
                            <h4 class="alert-heading"><i class="bi bi-check-circle"></i> Failed!</h4>
                            <p> You must fill all field before save data! </p>                                              
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--        model-Edit-->


        <!-- Bootstrap core JavaScript-->
        <script src="View/Admin/vendor/jquery/jquery.min.js"></script>
        <script src="View/Admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="View/Admin/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="View/Admin/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="View/Admin/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="View/Admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="View/Admin/js/demo/datatables-demo.js"></script>

    </body>

</html>
