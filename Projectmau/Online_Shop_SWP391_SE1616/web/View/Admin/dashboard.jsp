<%-- 
    Document   : dashboard
    Created on : Jun 26, 2022, 8:35:44 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

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
    <body style="background-color: gainsboro">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">F_Shop Admin Site</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">

                    </ul>                 
                    <div class="dropdown ms-2">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-person-circle"></i> Admin
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item" href="UserList">User List</a></li>
                            <li><a class="dropdown-item" href="LogOut">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row pt-4 ms-1">
                <div class="col-4 ">
                    <div class="row ">
                        <div class="card shadow">
                            <form action="Dashboard" method="GET" id="dateform">
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
                                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                        Total Sales</div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.totalSale}$</div>
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
                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                        Total Order</div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.totalOrder}</div>
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
                            <h6 class="m-0 font-weight-bold text-secondary">Total Sale by Category</h6>
                        </div>
                        <div class="card-body">
                            <h4 class="small font-weight-bold">Shoe: <span class="float-right">${requestScope.totalByCat[0]}$</span></h4>
                            <div class="progress mb-4">
                                <div class="progress-bar bg-danger" role="progressbar" style="width: ${requestScope.totalByCat[0]/requestScope.maxCat*100}%" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <h4 class="small font-weight-bold">Smart Phone: <span class="float-right">${requestScope.totalByCat[1]}$</span></h4>
                            <div class="progress mb-4">
                                <div class="progress-bar bg-warning" role="progressbar" style="width: ${requestScope.totalByCat[1]/requestScope.maxCat*100}%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <h4 class="small font-weight-bold">Chair: <span class="float-right">${requestScope.totalByCat[2]}$</span></h4>
                            <div class="progress mb-4">
                                <div class="progress-bar" role="progressbar" style="width: ${requestScope.totalByCat[2]/requestScope.maxCat*100}%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <h4 class="small font-weight-bold">Alcohol: <span class="float-right">${requestScope.totalByCat[3]}$</span></h4>
                            <div class="progress mb-4">
                                <div class="progress-bar bg-info" role="progressbar" style="width: ${requestScope.totalByCat[3]/requestScope.maxCat*100}%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <h4 class="small font-weight-bold">TV: <span class="float-right">${requestScope.totalByCat[4]}$</span></h4>
                            <div class="progress mb-4">
                                <div class="progress-bar bg-dark" role="progressbar" style="width: ${requestScope.totalByCat[4]/requestScope.maxCat*100}%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <h4 class="small font-weight-bold">Laptop: <span class="float-right">${requestScope.totalByCat[5]}$</span></h4>
                            <div class="progress">
                                <div class="progress-bar bg-success" role="progressbar" style="width: ${requestScope.totalByCat[5]/requestScope.maxCat*100}%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 mb-4">
                    <div class="card shadow mb-4 h-100">      
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-secondary">Order Count</h6>
                        </div>
                        <div class="card-body">
                            <canvas id="pieChart" style="width:100%; "></canvas> 
                        </div>
                        <script>
                            var xValues = ["Processing", "Confirmed", "Cancel"];
                            var yValues = [${requestScope.processing}, ${requestScope.confirmed}, ${requestScope.cancel}];
                            var barColors = [
                                "#00aba9",
                                "#1e7145",
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
            <div class="row">
                <div class="card shadow mb-4">      
                    <div class="card-header py-3 mb-1 mt-2">
                        <h6 class="m-0 font-weight-bold text-secondary">Trend of order counts</h6>
                    </div>
                    <div class="card-body">
                        <canvas id="lineChart" style="background-color: white;width:100%"></canvas>
                    </div>
                </div>
                <script>
                    var xValues = ['YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD', 'YYYY/MM/DD'];

                    new Chart("lineChart", {
                        type: "line",
                        data: {
                            labels: xValues,
                            datasets: [{
                                    data: [8, 11, 10, 10, 10, 11, 13, 21, 73, 27],
                                    borderColor: "red",
                                    fill: false
                                }, {
                                    data: [16, 17, 17, 10, 20, 27, 40, 50, 60, 70],
                                    borderColor: "green",
                                    fill: false
                                }]
                        },
                        options: {
                            legend: {display: false}
                        }
                    });
                </script>
            </div>
        </div>
        <%@include file="../component/Footer.jsp" %>
    </body>
</html>
