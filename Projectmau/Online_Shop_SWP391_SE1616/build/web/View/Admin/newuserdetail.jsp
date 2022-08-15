<%-- 
    Document   : newuserdetail
    Created on : Jul 8, 2022, 8:37:49 PM
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

        <title>User Detail</title>

        <!-- Custom fonts for this template -->
        <link href="View/Admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->

        <link href="View/Admin/css/sb-admin-2.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this page -->
        <link href="View/Admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
        <script>
            $(document).ready(function () {
                var x_timer;
                $("#email").keyup(function (e) {
                    clearTimeout(x_timer);
                    var email = $(this).val();
                    x_timer = setTimeout(function () {
                        check_email_ajax(email);
                    }, 1000);
                });

                $('#phone').keyup(function (e) {
                    clearTimeout(x_timer);
                    var phone = $("#phone").val();
                    x_timer = setTimeout(function () {
                        check_phone(phone);
                    }, 1000);
                });

                $('#dob').change(function (e) {
                    clearTimeout(x_timer);
                    x_timer = setTimeout(function () {
                        regBtn();
                    }, 1000);
                });

                function check_email_ajax(email) {
                    var email = $('#email').val();
                    $.ajax({
                        url: '../EmailValidate',
                        method: 'POST',
                        data: {
                            email: email
                        },
                        success: function (resultText) {
                            $('#alert').html(resultText);
                        },
                        error: function (jqXHR, exception) {
                            $('#alert').addClass('alert alert-danger');
                            $('#alert').html("Please Fill All Field!");
                        }
                    });
                }

                function check_phone(phone) {
                    var isphone = /^(1\s|1|)?((\(\d{3}\))|\d{3})(\-|\s)?(\d{3})(\-|\s)?(\d{4})$/.test(phone);
                    if (!isphone) {
                        $('#phonealert').html('<div class="alert alert-danger d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg><div>Please enter valid phone number!</div></div>');
                    } else {
                        $('#phonealert').html('<div class="alert alert-success d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg><div>Valid Phonenumber</div></div>');
                    }
                }

                function regBtn() {
                    $('#regBtn').html('<button type="submit" class="btn btn-success btn-block btn-lg  text-body mb-2 style="color: white;"">Add</button>');
                }

                //                $('#sortIt').change(function (e) {
                //                    $('#sortform').submit();
                //                });
            });
        </script>
        <script>
            if (${requestScope.mess == 'success'}) {
                $(window).on('load', function () {
                    $('#myModal').modal('show');
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
                    <div class="sidebar-brand-text mx-3">Shopping Online</div>
                </a>
                <hr class="sidebar-divider d-none d-md-block">
                <c:if test="${requestScope.mode == 'view'}">
                    <div class="row mt-1 mx-4 mb-2">
                        <a class="nav-link btn btn-light" href="UserDetails?uid=${requestScope.user.userID}&mode=edit">
                            <span>Edit Mode</span></a>        
                    </div>
                </c:if>
                <c:if test="${requestScope.mode == 'edit'}">
                    <div class="row mt-1 mx-4 mb-2">                  
                        <a class="nav-link btn btn-light" href="UserDetails?uid=${requestScope.user.userID}&mode=view">
                            <span>View Mode</span></a>   
                    </div>
                </c:if>
                <div class="row mx-4 mb-3">
                    <button class="btn btn-light"  data-bs-toggle="modal" data-bs-target="#registerForm">Add New User</button>
                </div>
                <hr class="sidebar-divider d-none d-md-block">
                <li class="nav-item active">
                    <a class="nav-link" href="Dashboard">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Admin Dashboard</span></a>
                </li>   
                <li class="nav-item active">
                    <a class="nav-link" href="UserList?service=accessPage">
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
                    <nav class="navbar navbar-expand navbar-light bg-light topbar mb-4 static-top shadow">

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
                        <div class="row">
                            <div class="col-1">

                            </div>
                            <div class="col-3 pt-5">
                                <div class="card shadow h-100">
                                    <div class="card-body">                           
                                        <img style="width: 275px; height: 250px" class="card-img-center " src="<c:if test="${requestScope.user.avatar==null}">https://www.bastiaanmulder.nl/wp-content/uploads/2013/11/dummy-image-square.jpg</c:if><c:if test="${!requestScope.user.avatar!=null}">View/Img/${requestScope.user.avatar}</c:if>" alt="..." />
                                            <div class="row text-center mt-5">
                                                    <h4 class="text-success">Full Name: ${requestScope.user.name}</h4>
                                        </div>
                                    </div>
                                    <!--button class="btn btn-outline-light mt-2 mx-2 mb-2" data-bs-toggle="modal" data-bs-target="#registerForm">Add New</button-->

                                </div>
                            </div>
                            <div class="col-7 pt-5">
                                <div class="card mb-2 w-100 shadow h-100">
                                    <div class="card-header">
                                        User Detail
                                    </div>
                                    <div class="card-body mt-3">

                                        <p>Gender: <c:if test="${requestScope.user.gender}">Male</c:if> <c:if test="${!requestScope.user.gender}">Female</c:if></p>
                                        <p>Email: ${requestScope.user.email}</p>
                                        <p>Phone: ${requestScope.user.phone}</p>
                                        <p>Address: ${requestScope.user.address}</p>
                                        <c:if test="${requestScope.mode == 'view'}">
                                            <p >Role: ${requestScope.user.role.name}</p>
                                            <p >Status: ${requestScope.user.status.settingID}</p>
                                        </c:if>
                                        <c:if test="${requestScope.mode == 'edit'}">
                                            <form action="UserDetails" method="POST">
                                                <input type="hidden" name ="uid" value="${requestScope.user.userID}"/>
                                                <div id="editmode" class="">
                                                    Role: <select id="editmode" class="form-select mb-2 " name="role">
                                                        <option value="1" <c:if test="${requestScope.user.role.settingID == 1}">selected</c:if>>Customer</option>
                                                        <option value="2" <c:if test="${requestScope.user.role.settingID == 2}">selected</c:if>>Marketing</option>
                                                        <option value="3" <c:if test="${requestScope.user.role.settingID == 3}">selected</c:if>>Sale</option>
                                                        <option value="4" <c:if test="${requestScope.user.role.settingID == 4}">selected</c:if>>Sale Manager</option>
                                                        </select>

                                                        Status: <select id="editmode" class="form-select" name="status">
                                                            <option value="7" <c:if test="${requestScope.user.status.settingID == 7}">selected</c:if>>Active</option>
                                                        <option value="6" <c:if test="${requestScope.user.status.settingID == 6}">selected</c:if>>Non Active</option>
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
                            <div class="col-1">

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
        <!-- Modal Register-->
        <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
    </symbol>
    <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
    </symbol>
    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
    </symbol>
    </svg>
    <!-- Modal Register-->
    <div class="modal fade" id="registerForm" tabindex="-1" aria-labelledby="registerFormLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="card">
                    <div class="modal-header" style="background-color: gray">
                        <h5 class="modal-title" id="registerFormLabel" style="color: white;"><i class="bi bi-person-plus"></i> Add New User</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="card-body p-4">                 
                        <form action="UserList" method="POST">                        
                            <div class="form-floating">
                                <input type="text" class="form-control mb-2" id="name"  placeholder="Your name" name="newname">
                                <label for="name">Name</label>
                            </div>
                            <div class="form-floating">
                                <input type="email" class="form-control mb-2" id="email"  placeholder="name@example.com" name="email">
                                <label for="email">Email</label>
                            </div>
                            <div id="alert" class="">

                            </div>                        
                            <div class="form-check">
                                <input class="form-check-input" id="gender" type="radio" name="newgender" value="male"/> Male  </br>
                                <input class="form-check-input" id="gender" type="radio" name="newgender" value="female"/> Female
                            </div>
                            <div class="form-floating">
                                <input type="text" class="form-control mb-2" id="phone" placeholder="Phone Number" name="phone">
                                <label for="phone">Phone Number</label>
                            </div>
                            <div id="phonealert" class="">

                            </div>
                            <div class="form-floating">
                                <input type="text" class="form-control mb-2" id="address" placeholder="Address" name="address">
                                <label for="address">Address</label>
                            </div>
                            <div class="form-floating">
                                Role: <select class="form-select mb-2" name="newrole">
                                    <option value="1" selected>Customer</option>
                                    <option value="2" >Marketing</option>
                                    <option value="3" >Sale</option>
                                    <option value="4" >Sale Manager</option>
                                </select>
                            </div>
                            <div class="form-floating">
                                <input type="date" class="form-control mb-2" onchange="regBtn()" id="dob"  name="dob">
                                <label for="dob">Date Of Birth</label>
                            </div>                       
                            <div id="regBtn" class="d-flex justify-content-center mt-2">
                            </div>
                            <!--div class="d-flex justify-content-center mt-2">
                            <%//@include file="../component/googlebtn.jsp" %> 
                        </div-->                                         
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div id="myModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="card-body">
                    <div class="alert alert-success text-center" >
                         <h4 class="alert-heading"><i class="bi bi-check-circle"></i> SUCCESS!</h4>
                        <p>Your changes was save!! </p>                                              
                    </div> 
                </div>
            </div>
        </div>
    </div>


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
