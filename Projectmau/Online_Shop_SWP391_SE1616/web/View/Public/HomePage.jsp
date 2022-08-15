<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - F_Shop</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
        <!-- UIkit CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/css/uikit.min.css" />

        <!-- UIkit JS -->
        <script src="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/js/uikit.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/uikit@3.14.3/dist/js/uikit-icons.min.js"></script>
        <script src="View/Public/js/loginAndReg.js"></script>
        <style type = "text/css">
            .imagepost{
                height: 100px;
                overflow:hidden;
            }
        </style>
    </head>
    <body>
        <!-- Navigation-->
        <%@include file="../component/Navbar.jsp" %>   
        <!-- Header-->
        <c:if test="${sessionScope.mess != null}">
            <div class="alert alert-danger mt-5 text-center" role="alert">
                ${sessionScope.mess}
            </div>
        </c:if>
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner">

                        <div class="carousel-item active">
                            <a href="${requestScope.sliders[0].content}">
                                <img src="View/imgslider/${requestScope.sliders[0].img}" alt="First slide" class="d-block w-100" >
                                <div class="carousel-caption d-none d-md-block">
                                </div>
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="${requestScope.sliders[1].content}">
                                <img src="View/imgslider/${requestScope.sliders[1].img}" alt="Second slide" class="d-block w-100" >
                                <div class="carousel-caption d-none d-md-block">
                                </div>
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="${requestScope.sliders[2].content}">
                                <img src="View/imgslider/${requestScope.sliders[2].img}" alt="Third slide" class="d-block w-100">
                                <div class="carousel-caption d-none d-md-block">
                                </div>
                            </a>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div> 
            </div>
        </section>
        <div class="container">
            <div class="row">
                <div class="col-3 pt-5">
                    <!--div class="container px-4 px-lg-5 my-5"-->                   
                    <!--Lastest Post-->
                    <div class="card mb-4">
                        <div class="card-header text-center">What news to day?</div>
                        <div class="card-body">
                            <div class="row">                       
                                <ul class="list-unstyled mb-0">
                                    <c:forEach items="${requestScope.lposts}" var="p">
                                        <a href="BlogDetail?blogID=${p.postId}">
                                            <li class="list-group-item" >
                                                <p>${p.postTitle}</p>
                                                <div class="small text-muted">${p.postDate}</div>
                                            </li>
                                        </a>
                                    </c:forEach>                          
                                </ul>                                     
                            </div>
                        </div>
                    </div>
                    <!--Contact Link-->
                    <div class="card mb-4">
                        <div class="card-header">Contact with us</div>
                        <div class="card-body">                        
                            <div class="row">                       
                                <ul class="list-unstyled mb-0">
                                    <li>+ <i class="bi bi-telephone"></i> Phone: 0358097385</li>
                                    <li>+ <i class="bi bi-envelope"></i> Email: luongvdhe150026@fpt.edu.vn</li>
                                    <li>+ <i class="bi bi-house-door"></i> Address: FPT University</li>
                                    <li>+ <i class="bi bi-question-diamond"></i> <a href="#">Need help?</a></li>
                                </ul>                                     
                            </div>
                        </div>
                    </div>
                    <!--/div-->                  
                </div>
                <div class="col-9">
                    <!--Post-->
                    <div class="container px-4 px-lg-1 mt-5">
                        <div class="alert alert-secondary" role="alert">
                            <i class="bi bi-star"></i>
                            Hot News!
                        </div>
                        <div class="container">
                            <div class="uk-grid-divider uk-child-width-expand" uk-grid>   
                                <c:forEach items="${requestScope.posts}" var="p">
                                    <!--div class="col-3 card"-->
                                    <div class="">
                                        <img class="card-img-top imagepost" src="View/imgpost/${p.thumbnail}" alt="..." />
                                        <div class="small text-muted">${p.postDate}</div>
                                        <i class="bi bi-eye">${p.view}</i>
                                        <c:if test = "${p.postTitle.length() < 20}">
                                            <h2 class="card-title h5">${p.postTitle}</h2>
                                        </c:if>
                                        <c:if test = "${p.postTitle.length() > 20}">
                                            <c:set var="title" value="${fn:substring(p.postTitle, 0, 20)}" />
                                            <h2 class="card-title h5">${title}...</h2>
                                        </c:if>
                                        <c:set var="sumary" value="${fn:substring(p.postContent, 0, 20)}" />
                                        <p class="card-text">${sumary}...<a href="BlogDetail?blogID=${p.postId}">Read more →</a></p>
                                    </div>
                                    <!--/div-->     
                                </c:forEach>                  
                            </div>   
                        </div>
                    </div>

                    <!--Category-->
                    <div class="container px-4 px-lg-1 mt-5">
                        <div class="alert alert-secondary" role="alert">
                            <i class="bi bi-bag-plus"></i>
                            Category
                        </div>
                        <div class="uk-child-width-expand uk-text-center" uk-grid>
                            <c:forEach var="i" begin="0" end="5">                              
                                <div>
                                    <a class="btn btn-outline-secondary" 
                                       href="ProductList?service=search&keyword=&orderBy=1&sortBy=c&cateID=${st[i].settingID}&page=1">${st[i].name}
                                        <c:forEach var="j" begin="0" end="5">
                                            <c:if test="${st[i].settingID == requestScope.catThumbnail[j].category.settingID}">
                                                <img class="uk-transition-scale-up uk-transition-opaque" style="width: 100px; height: 100px" src="View/Img/${requestScope.catThumbnail[j].thumbnail}"  alt="">
                                            </c:if>
                                        </c:forEach>  
                                    </a>
                                </div> 
                            </c:forEach>                   
                        </div>
                    </div>
                    <!--Product-->
                    <div class="container px-4 px-lg-1 mt-5">
                        <div class="alert alert-secondary" role="alert">
                            <i class="bi bi-star"></i>
                            Feature Products
                        </div>
                        <div class="row gx-4 gx-lg-5 row-cols-1 row-cols-md-2 row-cols-xl-4 justify-content-center">
                            <c:forEach items="${requestScope.products}" var="p">
                                <div class="col mb-5 ">
                                    <div class="card h-100 shadow p-3 mb-5 bg-body rounded">
                                        <!-- Product image-->
                                        <div class="card-header bg-transparent border-bottom-0 h-50">
                                            <c:if test="${p.thumbnail != null}">
                                                <div class="uk-inline-clip uk-transition-toggle" tabindex="0">
                                                    <img class="uk-transition-scale-up uk-transition-opaque" src="View/Img/${p.thumbnail}" width="1800" height="1200" alt="">
                                                </div>
                                            </c:if>
                                            <c:if test="${p.thumbnail == null}">
                                                <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                                            </c:if>
                                        </div>

                                        <!-- Product details-->
                                        <div class="card-body">
                                            <div class="text-center">
                                                <!-- Product name-->
                                                <h5 class="fw-bolder">${p.title}</h5>
                                                <div class=" uk-child-width-1-2 mb-1" uk-grid>
                                                    <div class="uk-right text-decoration-line-through"> ${p.price}$</div> 
                                                    <div class="uk-left uk-text-danger">
                                                        <fmt:formatNumber value = "${p.price*((100 - (p.discount))/100)}" type = "number"/>$
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <!-- Product actions-->
                                        <a class="btn btn-outline-dark mt-auto me-2 uk-position-bottom-left" href="ProductDetail?pid=${p.productID}">View Details</a>
                                        <a class="btn btn-outline-dark mt-auto uk-position-bottom-right" href="AddToCart?pid=${p.productID}"><i class="bi-cart-fill me-1"></i>Add</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <nav
                        <div class="alert alert-secondary text-center" role="alert">
                            <a href="ProductList?service=productList" class="alert-link">View more</a>
                        </div>
                    </nav>
                </div>             
            </div>                              
        </div>
        <%@include file="../component/Footer.jsp" %>
    </body>
</html>
