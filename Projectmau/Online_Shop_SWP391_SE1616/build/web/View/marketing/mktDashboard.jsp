<%-- 
    Document   : mktDashboard
    Created on : Jul 14, 2022, 4:01:11 PM
    Author     : Hieuhihi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marketing Dashboard</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="View/marketing/css/sb-admin-2.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this page -->
        <link href="View/marketing/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="View/marketing/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
        
        <script>
            function submitDate{
                $('#dateform').submit();
            }

            $('#date1').change(function () {
                $('#dateform').submit();
            });
        </script>
    </head>
    <body style="background-color: gainsboro" id="page-top">
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
               

                <li class="nav-item active">
                    <a class="nav-link" href="ProductMarketing?service=list">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Product List</span></a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link" href="postmarketing">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Post</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="customers">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Customer</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="sliders">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Slider list</span></a>
                </li>
                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Navigation-->
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
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.user.name}</span>

                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                <div class="container px-4 px-lg-5 mt-5">
                    <div class="row pt-4 ms-1">
                        <div class="col-4 ">
                            <div class="row ">
                                <div class="card shadow">
                                    <form method="GET" id="dateform">
                                        <div class="row">
                                            <div class="col-3 me-5 mb-2">                           
                                                From: <input type="date" id="date1" name="startD" value="${requestScope.startD}" onchange="submitDate()"/>
                                            </div>
                                            <div class="col-3 ms-4 me-2">
                                                To: <input type="date" name="endD" value="${requestScope.endD}"/>                   
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col mx-1">
                                                <button type="submit" class="btn btn-outline-secondary mt-2 mb-3 w-100">Filter</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row pt-4">

                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card shadow">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                                                New User</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.totalUser}</div>
                                        </div>
                                        <div class="col-auto">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card shadow">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                Total Star</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.totalStar}</div>
                                        </div>
                                        <div class="col-auto">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 mb-4">
                            <!-- Project Card Example -->
                            <div class="card shadow mb-4 h-100">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-secondary">New Products</h6>
                                </div>
                                <div class="card-body">
                                    <h4 class="small font-weight-bold">Shoe: <span class="float-right">${requestScope.totalByCat[0]}</span></h4>
                                    <div class="progress mb-4">
                                        <div class="progress-bar bg-danger" role="progressbar" style="width: ${requestScope.totalByCat[0]/requestScope.maxCat*100}%" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <h4 class="small font-weight-bold">Smart Phone: <span class="float-right">${requestScope.totalByCat[1]}</span></h4>
                                    <div class="progress mb-4">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: ${requestScope.totalByCat[1]/requestScope.maxCat*100}%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <h4 class="small font-weight-bold">Chair: <span class="float-right">${requestScope.totalByCat[2]}</span></h4>
                                    <div class="progress mb-4">
                                        <div class="progress-bar" role="progressbar" style="width: ${requestScope.totalByCat[2]/requestScope.maxCat*100}%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <h4 class="small font-weight-bold">Alcohol: <span class="float-right">${requestScope.totalByCat[3]}</span></h4>
                                    <div class="progress mb-4">
                                        <div class="progress-bar bg-info" role="progressbar" style="width: ${requestScope.totalByCat[3]/requestScope.maxCat*100}%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <h4 class="small font-weight-bold">TV: <span class="float-right">${requestScope.totalByCat[4]}</span></h4>
                                    <div class="progress mb-4">
                                        <div class="progress-bar bg-dark" role="progressbar" style="width: ${requestScope.totalByCat[4]/requestScope.maxCat*100}%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <h4 class="small font-weight-bold">Laptop: <span class="float-right">${requestScope.totalByCat[5]}</span></h4>
                                    <div class="progress">
                                        <div class="progress-bar bg-success" role="progressbar" style="width: ${requestScope.totalByCat[5]/requestScope.maxCat*100}%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 mb-4">
                            <div class="card shadow mb-4 h-100">      
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-secondary">Post Count</h6>
                                </div>
                                <div class="card-body">
                                    <canvas id="pieChart" style="width:100%; "></canvas> 
                                </div>
                                <script>
                                    var xValues = ["Sale", "Minigame", "Review", "Discover", "System"];
                                    var yValues = [${requestScope.salePost}, ${requestScope.minigamePost}, ${requestScope.reviewPost}, ${requestScope.discoverPost}, ${requestScope.systemPost}];
                                    var barColors = [
                                        "#00aba9",
                                        "#1e7145",
                                        "#edbc70",
                                        "#28323b",
                                        "#b91d47"
                                    ];

                                    new Chart("pieChart", {
                                        type: "pie",
                                        data: {
                                            labels: xValues,
                                            datasets: [{
                                                    backgroundColor: barColors,
                                                    data: yValues
                                                }]
                                        },
                                        options: {
                                            title: {
                                                display: false,
                                                text: "Order Counts"
                                            }
                                        }
                                    });
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
                <%@include file="../component/Footer.jsp" %>
            </div>
        </div>
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
    </body>
</html>
