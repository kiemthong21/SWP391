<%-- 
    Document   : CartComplete
    Created on : Jun 22, 2022, 9:51:39 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Completion</title>
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
                    <div class="px-3 py-3 container-fluid">
                        <c:if test= "${requestScope.listProductNotEnought.size() == 0}">                      
                            <div class="text-center border border-secondary px-3 py-3">
                                <h1 class="fw-bold">THANK YOU!</h1>
                                <i class="fs-5 mt-3">
                                    Your order has been successfully initiated. Please go to my order to review the orders that have been created. The system will resend an email confirming the order has been initialized.
                                </i>
                                 <img src="https://img.freepik.com/free-vector/thank-you-your-order-simple-hand-drawn-lettering-vector-text-illustration-logo-label-design_499817-501.jpg?w=2000" alt="Sorry" width="300" height="400">
                                <div>                         
                                    <a class="border-bottom-10" href="Homepage"> <button type="button" class="btn btn-primary btn-lg">Go to Homepage</button></a> 
                                </div>                                
                            </div>
                        </c:if>
                        <c:if test= "${requestScope.listProductNotEnought.size() != 0}">
                            <div class="text-center border border-danger px-3 py-3">
                                <h1 class="fw-bold">SORRY!</h1>
                                <div>
                                    <i class="fs-5 mt-3">Sorry, product 
                                    <c:forEach var="c" items="${requestScope.listProductNotEnought}">
                                        <b>
                                            ${c.getTitle()}  
                                        </b>,                             
                                    </c:forEach>
                                    does not have enough products left to fulfill your order. Please select another product or change the number of products in the cart detail
                                    </i>
                                </div>     
                                <img src="https://recmiennam.com/wp-content/uploads/2018/02/hinh-nen-sorry-2.jpg" alt="Sorry" width="500" height="400">                                                
                                <div>
                                    <a class="border-bottom-10" href="Cart?action=detail"> <button type="button" class="btn btn-primary btn-lg">Go to Cart Detail</button></a>
                                </div>                           
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../component/Footer.jsp" %>



    </body>
</html>
