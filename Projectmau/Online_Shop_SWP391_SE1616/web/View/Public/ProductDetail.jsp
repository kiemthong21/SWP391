<%-- 
    Document   : ProductDetail
    Created on : May 25, 2022, 12:39:54 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Product Detail</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body style="background-color: gainsboro">
        <!-- Navigation-->
        <%@include file="../component/Navbar.jsp" %>
        <!-- Product section-->
        <div class="container" style="padding-top: 50px">
            <div class="row">
                <!-- Side widgets-->
                <div class="col-lg-3 mt-5 mb-5">
                    <%@include file="../component/Sider.jsp" %>

                </div>
                <div class="col-lg-9">
                    <!--Product Detail-->              
                    <div class="container px-4 px-lg-5 my-5 border border-secondary" style="background-color: white">
                        <div class="row gx-4 gx-lg-5 align-items-center mt-4">
                            <div class="col-md-6 ">
                                <c:if test="${requestScope.productDetail.thumbnail != null}">
                                    <div class="hover-zoom">
                                        <img class="card-img-top mb-5 mb-md-0" src="View/Img/${requestScope.productDetail.thumbnail}" alt="..." />
                                    </div>
                                </c:if>
                                <c:if test="${requestScope.productDetail.thumbnail == null}">
                                    <img class="card-img-top mb-5 mb-md-0" src="https://dummyimage.com/600x700/dee2e6/6c757d.jpg" alt="..." />
                                </c:if>
                                <!-- Product list img>    
                                <!--div class="card-group">
                                    <div class="card">
                                        <img class="card-img-top" src="https://dummyimage.com/200x200/dee2e6/6c757d.jpg" alt="Card image cap">
                                    </div>
                                    <div class="card">
                                        <img class="card-img-top" src="https://dummyimage.com/200x200/dee2e6/6c757d.jpg" alt="Card image cap">
                                    </div>
                                    <div class="card">
                                        <img class="card-img-top" src="https://dummyimage.com/200x200/dee2e6/6c757d.jpg" alt="Card image cap">
                                    </div>
                                </div-->
                            </div>
                            <div class="col-md-6 mb-4">
                                <h1 class="display-5 fw-bolder">${requestScope.productDetail.title}</h1>
                                <div class="fs-5 mb-5">
                                    <span class="text-decoration-line-through"> ${requestScope.productDetail.price}$</span>
                                    <span  style="color: red"> <fmt:formatNumber value="${requestScope.productDetail.price-requestScope.productDetail.price*requestScope.productDetail.discount/100}" type="currency" />$</span>
                                    <span style="color: white; background-color: red">Sale ${requestScope.productDetail.discount}%</span> </br>
                                    <i class="bi bi-eye">${requestScope.productDetail.view}</i>
                                </div>

                                <div class="d-flex">
                                    <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                                    <a href="AddToCart?pid=${requestScope.productDetail.productID}">
                                        <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                            <i class="bi-cart-fill me-1"></i>
                                            Add to cart
                                        </button>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="row gx-4 gx-lg-5 align-items-center">
                            <div class="alert alert-secondary mt-3 mx-auto">            
                                Seller Contact
                            </div>
                            <div class="">
                                <i class="bi bi-person-circle"></i> ${requestScope.productDetail.author.name} </br>
                                <i class="bi bi-envelope"></i>   ${requestScope.productDetail.author.email} </br>
                               <i class="bi bi-telephone"></i>  ${requestScope.productDetail.author.phone}
                            </div>
                        </div>
                        <div class="row gx-4 gx-lg-5 align-items-center">
                            <div class="alert alert-secondary mt-3 mx-auto">            
                                Product Detail
                            </div>
                            <div class="">
                                <p class="lead">${requestScope.productDetail.summary}</p>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2022</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

