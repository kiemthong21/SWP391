<%-- 
    Document   : CartDetail
    Created on : Jun 9, 2022, 8:39:39 PM
    Author     : Admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Details</title>
        <link href="View/Public/css/CartDetaill.css" rel="stylesheet" type="text/css"/>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body > <!-- style="background-color: white"-->"
        <%@include file="../component/Navbar.jsp" %>
        <div class="container mt-5" >
            <div class="row g-5 mt-1">
                <!-- Sider -->
                <div class="col-md-3"> <!-- style="background-color: white"-->
                    <%@include file="../component/Sider.jsp" %>
                </div>

                <div class="col-md-9">
                    <section class="py-1">  <!-- style="background-color: white"-->
                        <div class="container" style="min-height: 800px;">
                            <c:if test="${sessionScope.numberCart == 0}">
                                <div class="text-center mt-3 border" style="background-color: white; height: 400px">
                                    <h3 class="mt-5 text-warning">You have no products in your cart!</h3>
                                    <a class="link-warning" href="Homepage">Let buy now!</a>
                                </div>
                            </c:if>
                            <c:if test = "${sessionScope.numberCart != 0}">
                                <h3>Products in cart</h3>
                                <table class="table" style="height: 100px">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Image</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Total Price</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listCartDetail}" var="c">
                                        <form action="ActionCart">
                                            <input type="search" name="action" value="updateQuantity" hidden>
                                            <input type="search" name="pid" value="${c.product.productID}" hidden>
                                            <tr style="height: 60px">
                                                <th scope="row">${c.product.productID}</th>
                                                <th scope="row">${c.product.title}</th>
                                                <th scope="row"><img src="View/Img/${c.product.thumbnail}" width="50"/></th>
                                                <th scope="row">
                                                    <fmt:formatNumber
                                                        value="${c.product.price*(1-(c.product.discount/100))}"
                                                        type="number" /></th>
                                                <th scope="row"><input onchange="this.form.submit()" type="number" name="quantity" value="${c.quantity}"></th>
                                                <th scope="row">
                                                    <fmt:formatNumber
                                                        value="${c.quantity * (c.product.price*(1-(c.product.discount/100))) }"
                                                        type="number" /></th></th>
                                                <th scope="row">
                                                    <button class="btn btn-outline-danger" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${c.product.productID}"><i class="bi bi-trash-fill"></i>Delete
                                                    </button>

                                                </th>                                      
                                            </tr> 
                                        </form>
                                        <div class="modal fade" id="exampleModal${c.product.productID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel"><i class="bi bi-trash-fill"></i> Delete</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Are you sure to delete this product?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <form action="ActionCart" method="GET">
                                                            <input type="search" value="delete" name="action"
                                                                   hidden>
                                                            <input type="search" value="${c.product.productID}" name="pid"
                                                                   hidden>
                                                            <button type="submit" class="btn btn-primary">Yes</button>
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="d-flex justify-content-between ">
                                    <a href="Cart?action=contact" class="btn btn-outline-success ">Check Out</a> 
                                    <a href="ProductList?service=productList" class="btn btn-outline-success">Choose More Product</a>
                                </div>
                            </c:if>
                        </div>
                    </section>

                </div>
            </div>
        </div>
        <%@include file="../component/Footer.jsp" %>


    </body>
</html>
