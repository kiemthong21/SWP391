<%-- 
    Document   : userDetails
    Created on : Jun 26, 2022, 8:36:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Details</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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

            });

            function c() {
                $("#showmode").removeClass("d-none");
            }
            $(document).ready(function () {
                if (${!requestScope.mess == null}) {
                $("#myModal").modal('show');
                }
                );
        </script>
    </head>
    <body style="background-color: gainsboro">
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">F_Shop Admin Site</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">

                    </ul>                 
                    <div class="dropdown ms-2">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-person-circle"></i> Admin
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item" href="Dashboard">Dashboard</a></li>
                            <li><a class="dropdown-item" href="UserList?service=accessPage">UserList</a></li>
                            <li><a class="dropdown-item" href="LogOut">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <div class="container mt-5">
            <div class="row">
                <div class="col-1">

                </div>
                <div class="col-3 pt-5">
                    <div class="card shadow h-100">
                        <div class="card-body">                           
                            <img style="width: 275px; height: 250px" class="card-img-center " src="<c:if test="${requestScope.user.avatar==null}">https://www.bastiaanmulder.nl/wp-content/uploads/2013/11/dummy-image-square.jpg</c:if><c:if test="${!requestScope.user.avatar==null}">View/Img/${requestScope.user.avatar}</c:if>" alt="..." />
                            </div>
                            <button class="btn btn-outline-secondary mt-2 mx-2 mb-2" data-bs-toggle="modal" data-bs-target="#registerForm">Add New</button>

                        </div>
                    </div>
                    <div class="col-7 pt-5">
                        <div class="card mb-2 w-100 shadow">
                            <div class="card-header">
                                User Detail
                            </div>
                            <div class="card-body">
                                    <p>Full Name: ${requestScope.user.name}</p>
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
                                        <div id="saveBtn" class="">
                                            <button type="submit" class="btn btn-outline-secondary text-body mb-2 me-3" style="float:right">Save</button>
                                        </div>
                                    </form> 
                            </c:if>
                            <div id="showmode " class="d-none">
                            </div>
                        </div>

                    </div>
                </div>
                <div class="col-1">

                </div>
            </div>
        </div>
        <div class="row" style="height: 100px">

        </div>
        <%@include file="../component/Footer.jsp" %>       
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
                <div class="card ">
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
                <div class="modal-header">
                    ${requestScope.mess}                      
                </div>
            </div>
        </div>
    </div>                  
</body>

</html>
