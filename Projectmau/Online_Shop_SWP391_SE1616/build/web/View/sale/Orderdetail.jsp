<%-- 
    Document   : Orderdetail
    Created on : Jul 15, 2022, 8:01:36 AM
    Author     : Vu Dai Luong
--%>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.7/dist/flowbite.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="View/marketing/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="View/marketing/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="View/marketing/css/sb-admin-2.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/View/marketing/richtexteditor/rte_theme_default.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/View/marketing/richtexteditor/rte.js"></script>
        <script>RTE_DefaultConfig.url_base = '${pageContext.request.contextPath}/View/marketing/richtexteditor/richtexteditor'</script>
        <script type="text/javascript" src='${pageContext.request.contextPath}/View/marketing/richtexteditor/plugins/all_plugins.js'></script>
    </head>
    <body id="page-top">
        <style>
            body {
                background: #DCDCDC
            }

            .form-control:focus {
                box-shadow: none;
                border-color: #BA68C8
            }

            .profile-button {
                background: rgb(99, 39, 120);
                box-shadow: none;
                border: none
            }

            .profile-button:hover {
                background: #682773
            }

            .profile-button:focus {
                background: #682773;
                box-shadow: none
            }

            .profile-button:active {
                background: #682773;
                box-shadow: none
            }

            .back:hover {
                color: #682773;
                cursor: pointer
            }

            .labels {
                font-size: 15px
            }

            .add-experience:hover {
                background: #BA68C8;
                color: #fff;
                cursor: pointer;
                border: solid 1px #BA68C8
            }

        </style>
        
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


                <!-- Nav Item - Pages Collapse Menu -->

                

                <!-- Divider -->
                <li class="nav-item active">
                    <a class="nav-link" href="SaleDashboard">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Sale dashboard</span></a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link" href="orderlist">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Order List</span></a>
                </li>

                
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <div id="content-wrapper" class="d-flex flex-column">
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
                        <span class="mr-2 d-none d-lg-inline text-gray-600 small">Sale user</span>

                    </a>
                    <!-- Dropdown - User Information -->
                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                         aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="#">
                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                            Profile
                        </a>
                        <a class="dropdown-item" href="#">
                            <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                            Settings
                        </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                            <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                            Logout
                        </a>
                    </div>
                </li>

            </ul>

        </nav>
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-5 border-right">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Basic order information</h4>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6"><label class="labels">order id</label>
                                <input type="text" class="form-control" placeholder="ID" value="${order.getOrderID()}" readonly></div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12"><label class="labels">Customer full name</label>
                                <input type="text" class="form-control" placeholder="full name" value="${order.getCusID().getName()}" readonly></div>
                            <div class="col-md-12"><label class="labels">Email</label>
                                <input type="text" class="form-control" placeholder="email" value="${order.getCusID().getEmail()}" readonly></div>
                            <div class="col-md-12"><label class="labels">Mobile</label>
                                <input type="text" class="form-control" placeholder="mobile" value="${order.getCusID().getPhone()}" readonly></div>
                            <div class="col-md-12"><label class="labels">Order date</label>
                                <input type= "date" class="form-control" placeholder="order date" value="${order.getOrderDate()}" readonly></div>
                            <div class="col-md-12"><label class="labels">Total cost</label>
                                <input type="text" class="form-control" placeholder="total cost" value="${order.getTotalBill()}" readonly></div>
                            <div class="col-md-12"><label class="labels">Status</label>
                                <input type="text" class="form-control" placeholder="status" value="${order.getStatus().getName()}" readonly></div>

                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center experience"><span>Receiver information</span></div><br>
                        <div class="col-md-12"><label class="labels">Full name</label><input type="text" class="form-control" placeholder="Name" value="${order.getReceive().getName()}" readonly></div> <br>
                        <div class="col-md-12"><label class="labels">Mobile</label><input type="text" class="form-control" placeholder="Mobile" value="${order.getReceive().getPhone()}" readonly></div><br>
                        <div class="col-md-12"><label class="labels">Address</label><input type="text" class="form-control" placeholder="Address" value="${order.getReceive().getAddress()}" readonly></div>
                        <div class="col-md-12"><label class="labels">Note</label><input type="text" class="form-control" placeholder="Note" value="${order.getReceive().getNote()}" readonly></div>
                    </div>
                </div>
            </div>
            <div class="col-md"><div class="p-3 py-5">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col" class="text-center">Thumbnail</th>
                                <th scope="col" class="text-center">Name</th>
                                <th scope="col" class="text-center">Category</th>
                                <th scope="col" class="text-center">Unit price</th>
                                <th scope="col" class="text-center">Quantity</th>
                                <th scope="col" class="text-center">Total cost</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listProduct}" var="od">
                                <tr class="">
                                    <td class="text-center "> <img class="card-img img-fluid mx-auto" style="width: 18rem;" src="View/Img/${od.product.thumbnail}" alt="..." /></td>
                                    <td class="text-center fs-5  ">${od.product.title}</td>
                                    <td class="text-center fs-5 ">${od.product.category.name}</td>
                                    <td class="text-center fs-5">${od.product.price}</td>
                                    <td class="text-center fs-5">${od.quantity}</td>
                                    <td class="text-center fs-5">${od.product.price*od.quantity}$</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                </div>
                <div class="d-flex justify-content-between">
                    <form action="OrderDetail" method="post">
                        <input type="text" value="17" name="status" hidden/>
                        <input type="text" value="${order.getOrderID()}" name="oid" hidden/>
                       <button type="submit" class="btn btn-outline-danger" >Cancel</button>
                    </form>
                    
                    <form action="OrderDetail" method="post">
                        <input type="text" value="16" name="status" hidden/>
                        <input type="text" value="${order.getOrderID()}" name="oid" hidden/>
                        <button type="submit" class="btn btn-outline-success" value="Confirm" >Confirm</button>
                    </form>
                </div>
            </div>
        </div>

        <%@include file="../component/Footer.jsp" %>  
        </div>
        </div>
        <script src="https://unpkg.com/flowbite@1.4.7/dist/flowbite.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/View/marketing/js/postDetail.js"></script>
    </body>
</html>
