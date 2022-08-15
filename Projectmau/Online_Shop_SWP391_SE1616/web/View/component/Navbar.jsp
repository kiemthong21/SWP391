<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css
      ">
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>

        <script src="View/Public/js/loginAndReg.js"></script>  
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


        <script>
            function chooseFile(fileInput) {
                if (fileInput.files && fileInput.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#image').attr('src', e.target.result);
                    }
                    reader.readAsDataURL(fileInput.files[0])
                }
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">F_Shop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="Homepage">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="BlogList">Blog</a></li>
                        <li class="nav-item"><a class="nav-link" href="ProductList?service=productList">Product List</a></li>
                    </ul>
                    <!--form class="d-flex mx-auto" action="SearchProduct" method="get">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form-->
                    <form class="d-flex" action="Cart" method="get">
                        <input type="search" value="detail" name="action" hidden>
                        <button class="btn btn-outline-dark" type="submit">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill">${(sessionScope.numberCart == null)?0:sessionScope.numberCart}</span>
                        </button>
                    </form>
                    <c:choose >
                        <c:when test="${sessionScope.customer != null }" >
                            <div class="dropdown ms-2">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="bi bi-person-circle"></i> ${customer.name}
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li> <a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#profileForm" href="ProfileCustomer?sid=${user.userID}">
                                            Profile 
                                        </a> </li>
                                    <li> <a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#changepasswordForm">
                                            Change Password 
                                        </a> </li>
                                    <li><a class="dropdown-item" href="MyOrder">My Order</a></li>
                                    <div class="dropdown-divider"></div>
                                    <li><a class="dropdown-item" href="LogOut">Logout</a></li>
                                </ul>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-outline-primary ms-lg-2" data-bs-toggle="modal" data-bs-target="#loginForm">
                                Login 
                            </button>                      
                            <button class="btn btn-outline-primary ms-lg-2 " data-bs-toggle="modal" data-bs-target="#registerForm">
                                Register
                            </button>   
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>
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
                        <h5 class="modal-title" id="registerFormLabel" style="color: white;">Register</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="card-body p-4">                 
                        <form action="RegisterSendmailController" method="POST">                        
                            <div class="form-floating">
                                <input type="text" class="form-control mb-2" id="name"  placeholder="Your name" name="name">
                                <label for="name">Name</label>
                            </div>
                            <div class="form-floating">
                                <input type="email" class="form-control mb-2" id="email"  placeholder="name@example.com" name="email">
                                <label for="email">Email</label>
                            </div>
                            <div id="alert" class="">

                            </div>
                            <div class="form-floating">
                                <input type="password" class="form-control mb-2" id="password" placeholder="Password" name="password">
                                <label for="password">Password</label>
                            </div>
                            <div id="vpassalert" class="">

                            </div>
                            <div class="form-floating">
                                <input type="password" class="form-control mb-2" id="repassword" placeholder="Password" name="repassword">
                                <label for="repassword">Confirm Password</label>
                            </div>
                            <div id="passalert" class="">

                            </div>
                            <div class="form-check">
                                <input class="form-check-input" id="gender" type="radio" name="gender" value="male"/> Male  </br>
                                <input class="form-check-input" id="gender" type="radio" name="gender" value="female"/> Female
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
                                <input type="date" class="form-control mb-2" onchange="regBtn()" id="dob"  name="dob">
                                <label for="dob">Date Of Birth</label>
                            </div>
                            <div class="form-check d-flex justify-content-center">  
                                <label class="form-check-label" for="formCheck">
                                    By creating an account you agree to our <a href="./View/component/termsofservice.jsp" class="text-body"><u>Terms of service</u></a>
                                </label>
                            </div>
                            <div id="regBtn" class="d-flex justify-content-center mt-2">

                            </div>
                            <!--div class="d-flex justify-content-center mt-2">
                            <%//@include file="../component/googlebtn.jsp" %> 
                        </div-->                       
                            <p class="text-center text-muted mt-2 mb-0">Have already an account? <a href="#!" class="fw-bold text-body" data-bs-toggle="modal" data-bs-target="#loginForm"><u>Login here</u></a></p>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!--Model Login-->
    <div class="modal fade" id="loginForm" tabindex="-1" aria-labelledby="loginFormLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="card">
                    <div class="modal-header" style="background-color: gray;">
                        <h5 class="modal-title" id="registerFormLabel" style="color: white;">Login</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="card-body p-4">   
                        <form action="LoginCustomer" method="POST">                        
                            <div class="form-floating">
                                <input type="email" class="form-control mb-2" id="mail" placeholder="Email" name="email">
                                <label for="mail">Email</label>
                            </div>
                            <div class="form-floating ">
                                <input type="password" class="form-control " id="pass" placeholder="Password" name="pass">
                                <label for="pass">Password</label>
                            </div>
                            <div id="alertt" class="">

                            </div>
                            <div class="d-flex justify-content-center" style="padding-top: 10px">

                                <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body mb-2" >Login</button>
                            </div>
                            <p class="text-center text-muted mt-5 mb-0">You don't have an account yet? <a href="#!" class="fw-bold text-body" data-bs-toggle="modal" data-bs-target="#registerForm"><u>Register</u></a></p>
                            <p class="text-center text-muted mt-5 mb-0"> <a href="./Resetpass" class="fw-bold text-body">Forgot password</a></p>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!--Model Profile-->
    <div class="modal fade" id="profileForm" tabindex="-1" aria-labelledby="registerFormLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="card">
                    <div class="modal-header" style="background-color: gray">
                        <h5 class="modal-title" id="registerFormLabel" style="color: white;">Profile</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="card-body p-4">   
                        <form action="ProfileCustomer" method="POST">
                            <div class="form-floating"> 
                                <img src="View/Img/${sessionScope.customer.avatar}" alt="" id="image" width="200" height="200" >
                                <input type="file" name="imagine" value="${sessionScope.customer.avatar}" id="imageFile" onchange="chooseFile(this)"
                                       accept="image/gif, image/jpeg, image/png">
                            </div>
                            <div class="form-floating">
                                <input type="text" class="form-control mb-2" id="floatingInput" placeholder="Your name" name="name" value="${sessionScope.customer.name}"/>
                                <label for="floatingInput">Name</label>
                            </div>
                            <div class="form-floating">
                                <input type="text" class="form-control mb-2" hidden id="floatingInput" placeholder="Your name" name="sid" value="${sessionScope.customer.userID}"/>
                                <label for="floatingInput">${sessionScope.customer.userID}</label>
                            </div>
                            <div class="form-floating">
                                <input type="email" class="form-control mb-2" id="floatingInput" placeholder="name@example.com" readonly name="email" value="${sessionScope.customer.email}"/>
                                <label for="floatingInput">Email</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="gender" value="true" <c:if test="${sessionScope.customer.gender}"> checked="checked"</c:if>/> Male  </br>
                                <input class="form-check-input" type="radio" name="gender" value="false" <c:if test="${!sessionScope.customer.gender}"> checked="checked"</c:if>/> Female
                                </div>
                                <div class="form-floating">
                                    <input type="text" class="form-control mb-2" id="phone" placeholder="Phone Number" name="phone" value="${sessionScope.customer.phone}"/>
                                <label for="floatingInput">Phone</label>
                            </div>

                            <div class="form-floating">
                                <input type="text" class="form-control mb-2" id="floatingInput" placeholder="Address" name="address" value="${sessionScope.customer.address}" />
                                <label for="floatingInput">Address</label>
                            </div>
                            <div class="form-floating">
                                <input type="date" class="form-control mb-2" id="floatingInput"  name="dob" value="${sessionScope.customer.dob}"/>
                                <label for="floatingInput">Date</label>
                            </div>
                            <div class="d-flex justify-content-center mt-2">
                                <button type="submit" class="btn btn-success">Save</button>
                            </div> 
                        </form> 
                        <p class="text-center text-muted mt-2 mb-0">Have you want to change password? <a href="#!" class="fw-bold text-body" data-bs-toggle="modal" data-bs-target="#loginForm"><u>Change password here</u></a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="changepasswordForm" tabindex="-1" aria-labelledby="changepasswordForm" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="card">
                    <div class="modal-header" style="background-color: gray">
                        <h5 class="modal-title" id="registerFormLabel" style="color: white;">Change password</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="card-body p-4">   
                        <form action="ChangePass" method="POST">
                            <c:if test="${error!=null}">
                                <div class="alert alert-danger mb-3" role="alert">
                                    ${error}
                                </div>
                            </c:if>
                            
                            <div class="form-floating">
                                <input type="text" class="form-control mb-2" id="floatingInput" placeholder="Your name" name="password"/>
                                <label for="floatingInput">Password</label>
                            </div>
                            <div class="form-floating">
                                <input type="text" class="form-control mb-2" id="floatingInput" placeholder="Your name" name="new-password"/>
                                <label for="floatingInput">New password</label>
                            </div>
                            <div class="form-floating">
                                <input type="text" class="form-control mb-2" id="floatingInput"  name="confirm-password" />
                                <label for="floatingInput">Confirm password</label>
                            </div>  
                            <div class="d-flex justify-content-center mt-2">
                                <button type="submit" class="btn btn-success">Submit</button>
                            </div> 
                        </form> 
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>