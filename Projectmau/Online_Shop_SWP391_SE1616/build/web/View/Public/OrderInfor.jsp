<%-- 
    Document   : OrderInfor
    Created on : Jun 9, 2022, 11:14:09 AM
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
        <title>Order Information</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    </head>
    <body style="background-color: gainsboro">
        <%@include file="../component/Navbar.jsp" %> 
        <div class="container mt-5 mb-4">
            <div class="row ">
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
                <div class="col-9 mt-5">
                    <div class="container px-4 px-lg-1">
                        <div class="card mb-2 w-100">
                            <c:set value="${requestScope.order}" var="o"></c:set> 
                                <div class="card-header bg-transparent ms-4">
                                <%--c:if test="${o.status.settingID == 15}">
                                    <p style="float: right" class="text-primary">Processing</p>
                                </c:if>
                                <c:if test="${o.status.settingID == 16}">
                                    <p style="float: right" class="text-success">Confirmed</p>
                                </c:if>
                                <c:if test="${o.status.settingID == 17}">
                                    <p style="float: right" class="text-danger">Cancel</p>
                                </c:if--%>
                                <a class="link-warning" href="">OrderID: ${o.orderID} </a></br>
                                Order Date: ${o.orderDate} </br>
                                Total product: ${o.totalProduct} </br>
                                Order total cost: ${o.totalBill}$ </br>                                
                            </div>
                            <div class="card-body text-secondary">
                                <div class="row ms-4">
                                    <div class="col-5">
                                        <h4>Receiver</h4>
                                        <ul class="mx-3">
                                            <li>Name: ${o.cusID.name}</li>
                                            <li>Address: ${o.cusID.address}</li>
                                            <li>Email: ${o.cusID.email}</li>
                                            <li>Phone: ${o.cusID.phone}</li>
                                        </ul>
                                    </div>
                                    <div class="col-7">
                                        <div class="row me-1">
                                            <div class="alert alert-secondary">
                                                <i class="bi bi-activity"></i>
                                                Status
                                            </div>

                                            <c:if test="${o.getStatus().getSettingID() == 15}">
                                                <div class="alert alert-primary">
                                                    <i class="bi bi-arrow-clockwise"></i>
                                                    Processing
                                                </div>
                                            </c:if>
                                            <c:if test="${o.status.settingID == 16}">
                                                <div class="alert alert-success">
                                                    <i class="bi bi-check-circle"></i>
                                                    Confirmed
                                                </div>
                                            </c:if>
                                            <c:if test="${o.status.settingID == 17}">
                                                <div class="alert alert-danger">
                                                    <i class="bi bi-x-circle"></i>
                                                    Cancel
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <c:forEach items="${o.listProduct}" var="od">
                                    <div class="row border-top mb-3 ">
                                        <div class="col-3 mb-2 mt-3 text-center">                                                    
                                            <img class="card-im img-fluid  mx-auto" src="View/Img/${od.product.thumbnail}" alt="..." />
                                        </div>
                                        <div class="col-5 mt-3">
                                            <h5 class="card-title">${od.product.title}</h5>
                                            Category: ${od.product.category.name} </br>
                                            Price: ${od.product.price} </br>
                                            Quantity: ${od.quantity} </br>  
                                            Total cost: ${od.product.price*od.quantity}$
                                        </div>
                                        <!--div class="col-4 mt-2">
                                            <div class="container">
                                                <a class="btn btn-outline-secondary  mt-3 w-100"> <i class="bi bi-person-circle"></i> Contact seller</a> </br>
                                                <a class="btn btn-outline-secondary  mt-3 w-100"> <i class="bi bi-chat-dots"></i> Feedback</a> </br>
                                                <a class="btn btn-outline-secondary  mt-3 w-100" href="ProductDetail?pid=${od.product.productID}"> <i class="bi bi-cart-plus me-1"></i> Buy again</a>
                                            </div>
                                        </div-->
                                    </div> 
                                </c:forEach>                                                                                               
                            </div>
                            <div class="card-footer bg-transparent"> 
                                <div class="row ">
                                    <div class="col-8">
                                    </div>
                                    <div class="col-4">
                                        <c:if test="${o.getStatus().getSettingID() == 15}">
                                            <div class="container">
                                                <a class="btn btn-outline-secondary  mt-3 w-100" href="ProductDetail?pid=${od.product.productID}"> <i class="bi bi-cart-plus me-1"></i> Buy again</a>
<!--                                                <form action="UpdateOrderController?orderID=${requestScope.order.orderID}" method="POST">
                                                    <button type="submit" class="btn-outline-success  mt-2 w-100"> <i class="bi bi-arrow-up-square"></i> Update Order</button> </br>
                                                </form>                           -->
                                                <button class="btn-outline-danger  mt-2 mb-2 w-100" data-bs-toggle="modal" data-bs-target="#updateAlert"> <i class="bi bi-x-square"></i> Cancel Order</button> </br>
                                            </div>
                                        </c:if>
                                    </div>
                                </div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="updateAlert" tabindex="-1" aria-labelledby="updateAlertLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateAlertLabel">Are you sure to cancel this order?</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-footer">
                        <form action="OrderInformation" method="POST">
                            <button type="submit" class="btn btn-danger">Yes</button>
                            <input type="hidden" value="${requestScope.order.orderID}" name="orderID"/>
                        </form>
                        <button type="button" class="btn btn-infor" data-bs-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../component/Footer.jsp" %>
    </body>
</html>
