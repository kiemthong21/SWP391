<%-- 
    Document   : saledashboard
    Created on : Jul 8, 2022, 8:53:23 PM
    Author     : Admin
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
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

        <title>Shopping online</title>

        <!-- Custom fonts for this template -->
        <link href="View/Admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
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
                <form action="SaleDashboard" method="GET" id="dateform">
                    <div class="row">
                        <div class="ms-4 w-100">                           
                            From: </br><input type="date" id="date1" name="startD" value="${requestScope.startD}" onchange="submitDate()"/></br>
                            To:</br> <input type="date" name="endD" value="${requestScope.endD}"/>                   
                        </div>
                    </div>
                    <div class="row">
                        <div class="col mx-1">
                            <button type="submit" class="btn btn-outline-secondary mt-2 mb-3 w-100">Filter</button>
                        </div>
                    </div>
                </form>
                <hr class="sidebar-divider d-none d-md-block">
          
                
               
                <li class="nav-item active">
                    <a class="nav-link" href="orderlist">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Order list</span></a>
                </li>
            </ul>
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
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
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
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
                            <div class="col-lg-8 mb-4">
                                <!-- Project Card Example -->
                                <div class="card shadow mb-4">      
                                    <div class="card-header py-3 mb-1">
                                        <h6 class="m-0 font-weight-bold text-secondary">Trend of order counts</h6>
                                    </div>
                                    <div class="card-body">
                                        <canvas id="lineChart" style="background-color: white;width:100%"></canvas>
                                    </div>
                                </div>
                                <script>
                                    var xValues = ['YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD'];
                                    var times = [];
                                    var totalOd = [];
                                    var successOd = [];
                                    var revenues = [];
                                    
                                    <% ArrayList<Date> time = (ArrayList<Date>) request.getAttribute("date");
                                    ArrayList<Integer> tod = (ArrayList<Integer>) request.getAttribute("tod");
                                    ArrayList<Integer> sod = (ArrayList<Integer>) request.getAttribute("sod");
                                    ArrayList<Float> r = (ArrayList<Float>) request.getAttribute("revenus");
                                    
                                    for (int idx =  0; idx < time.size(); idx++) {
                                        String elem = "'" + time.get(idx) + "'";%>

                                        times.push(String(<%=elem%>));
                                        totalOd.push(<%=tod.get(idx)%>);
                                        successOd.push(<%=sod.get(idx)%>);
                                        revenues.push(<%=r.get(idx)%>);
                                    <%   }
                                    %>
                                    new Chart("lineChart", {
                                        type: "line",
                                        data: {
                                            labels: times,
                                            datasets: [{
                                                    labels: "Total Order",
                                                    data: totalOd,
                                                    borderColor: "red",
                                                    fill: false
                                                }, {
                                                    labels: "Success Order",
                                                    data: successOd,
                                                    borderColor: "green",
                                                    fill: false
                                                }]
                                        },
                                        options: {
                                            legend: {display: true},
                                            
                                        }
                                    });
                                </script>
                            </div>
                            <div class="col-lg-4 mb-4">
                                <div class="row">
                                    <div class="card shadow mb-4 ">      
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-secondary">Total order</h6>
                                        </div>
                                        <div class="card-body">
                                            ${requestScope.totalOrder} 
                                        </div>
                                    </div> 
                                </div>
                                        <div class="row">
                                    <div class="card shadow mb-3 h-100">      
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-info">Processing Order</h6>
                                        </div>
                                        <div class="card-body">
                                            ${requestScope.processing} 
                                        </div>
                                    </div> 
                                </div>
                                        <div class="row">
                                    <div class="card shadow mb-4 h-100">      
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-success">Confirmed Order</h6>
                                        </div>
                                        <div class="card-body">
                                            ${requestScope.confirmed} 
                                        </div>
                                    </div> 
                                </div>
                                        <div class="row">
                                    <div class="card shadow mb-4 h-100">      
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-danger">Cancel Order</h6>
                                        </div>
                                        <div class="card-body">
                                            ${requestScope.cancel} 
                                        </div>
                                    </div> 
                                </div>
                                <div class="row">
                                    <div class="card shadow mb-4  h-100">      
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-warning">Total sale</h6>
                                        </div>
                                        <div class="card-body">
                                            ${requestScope.totalSale} 
                                        </div>
                                    </div> 
                                </div>

                            </div>
                        </div>
                        <div class="row">

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
        <!--        model-Create-->
        <div class="modal fade" id="CreateFormLabel" tabindex="-1" aria-labelledby="CreateFormLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="card">
                        <div class="modal-header" style="background-color:#36b9cc">
                            <h5 class="modal-title" id="registerFormLabel" style="color: white;">Create</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="card-body p-4">                 
                            <form action="create_customer" method="POST">
                                <c:if test="${error!=null}">
                                    <div class="p-4 mb-4 text-sm text-red-700 bg-red-100 rounded-lg" role="alert">
                                        <span class="font-medium">Error!</span> ${error}
                                    </div>
                                </c:if>
                                <div class="form-floating">
                                    <input type="Text" class="form-control mb-2" id="email"  placeholder="name@example.com" name="Name">
                                    <label for="Name">Name</label>
                                </div>
                                <div class="form-floating">
                                    <input type="email" class="form-control mb-2" id="Price" placeholder="Email" name="Email">
                                    <label for="Email"> Email</label>
                                </div>
                                <div class="form-floating">
                                    <input type="number" class="form-control mb-2" id="phone" placeholder="Phone" name="Phone">
                                    <label for="Phone">Phone</label>
                                </div>
                                <label for="Title">Gender</label>
                                <div class="form-floating">
                                    <input class="form-check-input" type="radio" name="gender" value="true"  checked="checked" Male  </br>
                                    <input class="form-check-input" type="radio" name="gender" value="false" checked="checked" Female
                                </div>
                                <label for="Title">Status</label>
                                <div class="form-floating"> 
                                    <select class="form-select form-select-sm form-control mb-2" aria-label=".form-select-sm example" name="status">
                                        <c:forEach items="${requestScope.status}" var="stt">
                                            <option    value="${stt.getSettingID()}" >${stt.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>                                
                                <div id="saveBtn" class="d-flex justify-content-center mt-2">

                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!--        model-Edit-->


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
