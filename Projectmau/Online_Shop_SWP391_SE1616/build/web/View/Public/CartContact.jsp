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
    <body>
        <%@include file="../component/Navbar.jsp" %>
        <div class="container mt-5" >
            <div class="row g-5 mt-1">
                <!-- Sider -->
                <div class="col-md-3">
                    <%@include file="../component/Sider.jsp" %>>
                </div>

                <div class="col-md-9">
                    <section class="py-1">
                        <div class="container" style="min-height: 800px">
                            <div>
                                <h3>Receiver Information </h3>
                                <c:if test="${sessionScope.receiver == null}">
                                    <form action="Cart" method="post">
                                        <div class="form-floating">
                                            <input type="text" class="form-control mb-1" id="name" value="${sessionScope.customer.name}" placeholder="Your name" name="name">
                                            <label for="name">Name</label>
                                        </div>
                                        <div class="form-floating">
                                            <input type="text" class="form-control mb-1" id="phone" value="${sessionScope.customer.phone}" placeholder="Phone Number" name="phone">
                                            <label for="phone">Phone Number</label>
                                        </div>
                                        <div class="form-floating">
                                            <input type="text" class="form-control mb-1" id="address" value="${sessionScope.customer.address}" placeholder="Address" name="address">
                                            <label for="address">Address</label>
                                        </div>
                                        <div class="form-floating">
                                            <input type="text" class="form-control mb-1" id="note" placeholder="Note" name="note">
                                            <label for="address">Note</label>
                                        </div>
                                        <div class="form-floating">
                                            <input type="submit" class="form-control mb-1 btn btn-outline-success" id="note" placeholder="submit" value="Change Receiver Information.">
                                        </div> 
                                    </form>
                                </c:if>
                                <c:if test="${sessionScope.receiver != null}">
                                    <form action="Cart" method="post">
                                        <div class="form-floating">
                                            <input type="text" class="form-control mb-1" id="name" value="${sessionScope.receiver.name}" placeholder="Your name" name="name">
                                            <label for="name">Name</label>
                                        </div>
                                        <div class="form-floating">
                                            <input type="text" class="form-control mb-1" id="phone" value="${sessionScope.receiver.phone}" placeholder="Phone Number" name="phone">
                                            <label for="phone">Phone Number</label>
                                        </div>
                                        <div class="form-floating">
                                            <input type="text" class="form-control mb-1" id="address" value="${sessionScope.receiver.address}" placeholder="Address" name="address">
                                            <label for="address">Address</label>
                                        </div>
                                        <div class="form-floating">
                                            <input type="text" class="form-control mb-1" id="note" value="${sessionScope.receiver.getNote()}" placeholder="Note" name="note">
                                            <label for="address">Note</label>
                                        </div>    
                                        <div class="form-floating">
                                            <input type="submit" class="form-control mb-1 btn btn-outline-success" id="note" placeholder="submit" value="Change Receiver Information.">
                                        </div>
                                    </form>
                                </c:if>              
                            </div>
                            <c:if test="${sessionScope.numberCart == 0}">
                                <h1>List Cart is Empty</h1>
                            </c:if>
                            <c:if test = "${sessionScope.numberCart != 0}">
                                <h3>Cart Content</h3>
                                <table class="table">
                                    <thead>
                                        <tr >
                                            <th scope="col">ID</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Image</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Total Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listCartDetail}" var="c">
                                        <input type="search" name="action" value="updateQuantity" hidden>
                                        <input type="search" name="pid" value="${c.product.productID}" hidden>
                                        <tr style="height: 60px">
                                            <th scope="row">${c.product.productID}</th>
                                            <th scope="row">${c.product.title}</th>
                                            <th scope="row"><img src="View/Img/${c.product.thumbnail}" width="50"/></th>
                                            <th scope="row">
                                                <fmt:formatNumber
                                                    value="${c.product.price*(1-(c.product.discount/100))}"
                                                    type="number" />$</th>
                                            <th scope="row">${c.quantity}</th>
                                            <th scope="row">
                                                <fmt:formatNumber
                                                    value="${c.quantity * (c.product.price*(1-(c.product.discount/100))) }"
                                                    type="number" />$</th></th>
                                        </tr> 
                                        </form
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <h3>Total Amount: <fmt:formatNumber
                                        value="${requestScope.amount}"
                                        type="number" />$</h3>
                                <div class="d-flex justify-content-between ">
                                    <a href="Cart?action=detail" class="btn btn-outline-success ">Go Back</a>
                                    <a href="#" class="btn btn-outline-success"  data-bs-toggle="modal" data-bs-target="#confirmAlert">Submit</a> 
                                </div> 
                            </c:if>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <%@include file="../component/Footer.jsp" %>

        <!--        confirm submit-->
        <div class="modal fade" id="confirmAlert" tabindex="-1" aria-labelledby="confirmAlertLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmAlertLabel">Are you sure to submit?</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-footer">
                        <a href="CartCompletion?total=${requestScope.amount}">
                            <button type="submit" id="submitbtn" class="btn btn-danger" onmousedown="disable5s()">Yes</button>
                        </a>
                        <button type="button" class="btn btn-infor" data-bs-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function disable5s(){
                setInterval(function(){ document.getElementById("submitbtn").disabled = true }, 50000);
            }
        </script> 
    </body>
</html>
