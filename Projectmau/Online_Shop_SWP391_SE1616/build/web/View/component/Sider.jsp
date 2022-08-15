<%-- 
    Document   : Sider
    Created on : Jun 10, 2022, 1:49:43 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />

        <link href="View/Public/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>


        <div class="position-sticky px-2 py-2" style="top: 5rem; border-radius: 10px; background-color: white" >   
            <div class="mb-4">
                <div class="row">
                    <div class="col-md-12 mb-3">
                        <form class="mb-3" action="ProductList" method="GET">
                            <input type="search" name="service" value="search" hidden>
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="keyword" value="${requestScope.keyword}">
                            <input type="search" name="cateID" value="${requestScope.cateId}" hidden>
                            <input type="search" name="orderBy" value="1" hidden>
                            <input type="search" name="sortBy" value="c" hidden>
                            
                            <button class="btn btn-primary mt-2 mb-2 w-100" type="submit">Search</button>
                            
                        </form>
                    </div>

                    <div class="accordion mb-3" id="accordionExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <strong>Category</strong>
                                </button>
                            </h2>
                            <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    <div class="form-check">
                                        <c:forEach items="${sessionScope.cate}" var="category">
                                            <div class = "col">
                                                <a class="text-decoration-none" style="color: black" href="ProductList?service=search&cateID=${category.getSettingID()}&orderBy=1&sortBy=c&keyword=${requestScope.keyword}">${category.getName()} </a>               
                                            </div>
                                        </c:forEach>  
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <h3 class="mb-3">Latest Product</h3>
            <c:forEach items="${sessionScope.latestProduct}" var="p">
                <div class="card mb-3" style="max-width: 540px;">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <div class="badge bg-transparent text-white position-absolute">                                              
                                <img src="View/Img/c338c3_a71d72719cdb46f1adfbdd414f524d8f_mv2.gif" height="30" alt=""/>
                            </div>  
                            <img src="View/Img/${p.thumbnail}" class="img-fluid rounded-start h-100" alt="...">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">${p.title}</h5>
                                <a href="ProductDetail?pid=${p.productID}" class="stretched-link"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <style>
            .card:hover{
                transform: scale(1.0);
                box-shadow: 0 0 8px;
            }
            .card{
                transition: transform .5s;
            }
        </style>
    </body>
</html>
