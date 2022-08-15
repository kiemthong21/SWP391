<%-- 
    Document   : MyOrder
    Created on : Jun 9, 2022, 7:40:56 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>My Order</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    </head>
    <body style="background-color: gainsboro">     
        <%--jsp:include page="../component/Navbar.jsp"></jsp:include--%> 
        <%@include file="../component/Navbar.jsp" %>
        <div class="container mt-5">
            <div class="row">
                <div class="col-3 pt-5">
                    <%@include file="../component/Sider.jsp" %>
                    <!--Contact Link-->
                    <!--div class="card mb-4">
                        <div class="card-header text-center">Need help?</div>
                        <div class="card-body">                        
                            <div class="row">                       
                                <ul class="list-unstyled mb-0">
                                    <li><a href="#">+ How can I drop my order?</a></li>
                                    <li><a href="#">+ Some question</a></li>
                                    <li><a href="#">+ Some question</a></li>
                                    <li><a href="#">+ Some question</a></li>
                                </ul>                                     
                            </div>
                        </div>
                    </div-->
                </div> 
                <div class="col-9 pt-5">
                    ${requestScope.alert}

                    <div class="container px-4 px-lg-1">
                        <div class="btn-group w-100" role="group" aria-label="Basic checkbox toggle button group" style="background-color: white">
                            <a class="btn btn-outline-secondary" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" href="">All Order</a>
                            <a class="btn btn-outline-secondary" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" href="">Processing</a>
                            <a class="btn btn-outline-secondary" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" href="">Confirmed</a>
                            <a class="btn btn-outline-secondary" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" href="">Cancel</a>
                        </div>
                        <c:if test="${requestScope.orders.isEmpty() }">
                            <div class="text-center mt-3 border" style="background-color: white; height: 400px">
                                <h3 class="mt-5 text-warning">You don't have any order!</h3>
                                <a class="link-warning" href="Homepage">Let buy now!</a>
                            </div>

                        </c:if>
                        <div id="carouselExampleIndicators" class="carousel slide mt-3 mb-5" data-bs-ride="true">
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <c:forEach items="${requestScope.orders}" var="o">
                                        <div class="card mb-2 w-100">
                                            <div class="card-header bg-transparent">
                                                <a class="link-warning" href="OrderInformation?orderID=${o.orderID}&status=${o.status.settingID}">OrderID: ${o.orderID}</a></br>
                                                ${o.orderDate}
                                                <c:if test="${o.status.settingID == 15}">
                                                    <p style="float: right" class="text-primary">Processing</p>
                                                </c:if>
                                                <c:if test="${o.status.settingID == 16}">
                                                    <p style="float: right" class="text-success">Confirmed</p>
                                                </c:if>
                                                <c:if test="${o.status.settingID == 17}">
                                                    <p style="float: right" class="text-danger">Cancel</p>
                                                </c:if>
                                            </div>
                                            <div class="card-body text-secondary">
                                                <div class="row">
                                                    <div class="col-2 ">                                                    
                                                        <img style="width: 100px; height: 100px" class="card-img-center ms-4" src="View/Img/${o.listProduct[0].product.thumbnail}" alt="..." />
                                                    </div>
                                                    <div class="col-8">
                                                        <h4 class="card-title">${o.listProduct[0].product.title}</h4>
                                                        <p class="card-text"></p>                             
                                                    </div>
                                                    <div class="col-2"> 
                                                        <p class="card-text">Price: ${o.listProduct[0].product.price}</p>
                                                        <p class="card-text">Quantity: ${o.listProduct[0].quantity}</p>
                                                        <p class="card-text">Total cost: ${o.listProduct[0].product.price*o.listProduct[0].quantity}$</p>
                                                    </div>
                                                </div> 
                                            </div>
                                            <div class="card-footer bg-transparent"> 
                                                <div class="row">
                                                    <div class="col-9">
                                                         <button class="btn-outline-info mx-1 mt-2"> <i class="bi bi-person-circle"></i> Contact seller</button>
                                                        <!--button class="btn-outline-success mt-2"> <i class="bi bi-cart-plus me-1"></i> Buy again</button-->
                                                    </div>
                                                    <div class="col-3">
                                                        Total product: ${o.totalProduct} </br>
                                                        Order total cost: ${o.totalBill}$ </br>                                
                                                    </div>
                                                </div> 
                                            </div>
                                        </div>   
                                    </c:forEach>
                                    <c:if test="${!requestScope.orders.isEmpty() }">
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination justify-content-center">
                                                <li class="page-item"><a class="page-link" href="MyOrder?page=1">1</a></li>
                                                <li class="page-item"><a class="page-link" href="MyOrder?page=2">2</a></li>
                                                <li class="page-item"><a class="page-link" href="MyOrder?page=3">3</a></li>
                                            </ul>
                                        </nav>
                                    </c:if>
                                </div>
                                <div class="carousel-item">
                                    <c:forEach items="${requestScope.orders}" var="o">
                                        <c:if test="${o.status.settingID == 15}">
                                            <div class="card mb-2 w-100">
                                                <div class="card-header bg-transparent">
                                                    <a class="link-warning" href="OrderInformation?orderID=${o.orderID}&status=${o.status.settingID}">OrderID: ${o.orderID}</a></br>
                                                    ${o.orderDate}
                                                    <c:if test="${o.status.settingID == 15}">
                                                        <p style="float: right" class="text-primary">Processing</p>
                                                    </c:if>
                                                    <c:if test="${o.status.settingID == 16}">
                                                        <p style="float: right" class="text-success">Confirmed</p>
                                                    </c:if>
                                                    <c:if test="${o.status.settingID == 17}">
                                                        <p style="float: right" class="text-danger">Cancel</p>
                                                    </c:if>
                                                </div>
                                                <div class="card-body text-secondary">
                                                    <div class="row">
                                                        <div class="col-2 ">                                                    
                                                            <img style="width: 100px; height: 100px" class="card-img-center ms-4" src="View/Img/${o.listProduct[0].product.thumbnail}" alt="..." />
                                                        </div>
                                                        <div class="col-8">
                                                            <h5 class="card-title">${o.listProduct[0].product.title}</h5>
                                                            <p class="card-text">Summary</p>                             
                                                        </div>
                                                        <div class="col-2"> 
                                                            <p class="card-text">Price: ${o.listProduct[0].product.price}</p>
                                                            <p class="card-text">Quantity: ${o.listProduct[0].quantity}</p>
                                                            <p class="card-text">Total cost: ${o.listProduct[0].product.price*o.listProduct[0].quantity}$</p>
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="card-footer bg-transparent"> 
                                                    <div class="row">
                                                        <div class="col-9">
                                                             <button class="btn-outline-info mx-1 mt-2"> <i class="bi bi-person-circle"></i> Contact seller</button>
                                                            <!--button class="btn-outline-success mt-2"> <i class="bi bi-cart-plus me-1"></i> Buy again</button-->
                                                        </div>
                                                        <div class="col-3">
                                                            Total product: ${o.totalProduct} </br>
                                                            Order total cost: ${o.totalBill}$ </br>                                
                                                        </div>
                                                    </div> 
                                                </div>
                                            </div>   
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="carousel-item">
                                    <c:forEach items="${requestScope.orders}" var="o">
                                        <c:if test="${o.status.settingID == 16}">
                                            <div class="card mb-2 w-100">
                                                <div class="card-header bg-transparent">
                                                    <a class="link-warning" href="OrderInformation?orderID=${o.orderID}&status=${o.status.settingID}">OrderID: ${o.orderID}</a></br>
                                                    ${o.orderDate}
                                                    <c:if test="${o.status.settingID == 15}">
                                                        <p style="float: right" class="text-primary">Processing</p>
                                                    </c:if>
                                                    <c:if test="${o.status.settingID == 16}">
                                                        <p style="float: right" class="text-success">Confirmed</p>
                                                    </c:if>
                                                    <c:if test="${o.status.settingID == 17}">
                                                        <p style="float: right" class="text-danger">Cancel</p>
                                                    </c:if>
                                                </div>
                                                <div class="card-body text-secondary">
                                                    <div class="row">
                                                        <div class="col-2 ">                                                    
                                                            <img style="width: 100px; height: 100px" class="card-img-center ms-4" src="View/Img/${o.listProduct[0].product.thumbnail}" alt="..." />
                                                        </div>
                                                        <div class="col-8">
                                                            <h5 class="card-title">${o.listProduct[0].product.title}</h5>
                                                            <p class="card-text">Summary</p>                             
                                                        </div>
                                                        <div class="col-2"> 
                                                            <p class="card-text">Price: ${o.listProduct[0].product.price}</p>
                                                            <p class="card-text">Quantity: ${o.listProduct[0].quantity}</p>
                                                            <p class="card-text">Total cost: ${o.listProduct[0].product.price*o.listProduct[0].quantity}$</p>
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="card-footer bg-transparent"> 
                                                    <div class="row">
                                                        <div class="col-9">
                                                             <button class="btn-outline-info mx-1 mt-2"> <i class="bi bi-person-circle"></i> Contact seller</button>
                                                            <!--button class="btn-outline-success mt-2"> <i class="bi bi-cart-plus me-1"></i> Buy again</button-->
                                                        </div>
                                                        <div class="col-3">
                                                            Total product: ${o.totalProduct} </br>
                                                            Order total cost: ${o.totalBill}$ </br>                                
                                                        </div>
                                                    </div> 
                                                </div>
                                            </div>   
                                        </c:if>
                                    </c:forEach> 
                                </div>
                                <div class="carousel-item">
                                    <c:forEach items="${requestScope.orders}" var="o">
                                        <c:if test="${o.status.settingID == 17}">
                                            <div class="card mb-2 w-100">
                                                <div class="card-header bg-transparent">
                                                    <a class="link-warning" href="OrderInformation?orderID=${o.orderID}&status=${o.status.settingID}">OrderID: ${o.orderID}</a></br>
                                                    ${o.orderDate}
                                                    <c:if test="${o.status.settingID == 15}">
                                                        <p style="float: right" class="text-primary">Processing</p>
                                                    </c:if>
                                                    <c:if test="${o.status.settingID == 16}">
                                                        <p style="float: right" class="text-success">Confirmed</p>
                                                    </c:if>
                                                    <c:if test="${o.status.settingID == 17}">
                                                        <p style="float: right" class="text-danger">Cancel</p>
                                                    </c:if>
                                                </div>
                                                <div class="card-body text-secondary">
                                                    <div class="row">
                                                        <div class="col-2 ">                                                    
                                                            <img style="width: 100px; height: 100px" class="card-img-center ms-4" src="View/Img/${o.listProduct[0].product.thumbnail}" alt="..." />
                                                        </div>
                                                        <div class="col-8">
                                                            <h5 class="card-title">${o.listProduct[0].product.title}</h5>
                                                            <p class="card-text">Summary</p>                             
                                                        </div>
                                                        <div class="col-2"> 
                                                            <p class="card-text">Price: ${o.listProduct[0].product.price}</p>
                                                            <p class="card-text">Quantity: ${o.listProduct[0].quantity}</p>
                                                            <p class="card-text">Total cost: ${o.listProduct[0].product.price*o.listProduct[0].quantity}$</p>
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="card-footer bg-transparent"> 
                                                    <div class="row">
                                                        <div class="col-9">
                                                             <button class="btn-outline-info mx-1 mt-2"> <i class="bi bi-person-circle"></i> Contact seller</button>
                                                            <!--button class="btn-outline-success mt-2"> <i class="bi bi-cart-plus me-1"></i> Buy again</button-->
                                                        </div>
                                                        <div class="col-3">
                                                            Total product: ${o.totalProduct} </br>
                                                            Order total cost: ${o.totalBill}$ </br>                                
                                                        </div>
                                                    </div> 
                                                </div>
                                            </div>   
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--jsp:include page="../component/Footer.jsp"></jsp:include--%> 
        <%@include file="../component/Footer.jsp" %>
    </body>
</html>
