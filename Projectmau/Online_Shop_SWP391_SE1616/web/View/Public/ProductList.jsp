<%-- Document : ProductList.jsp Created on : May 27, 2022, 2:03:50 AM Author : Admin --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Product List - F_Shop</title>
        <!-- Favicon-->
        <link href="View/Public/css/styles.css" rel="stylesheet" type="text/css" />
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
              rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->

    </head>

    <body >
        <%@include file="../component/Navbar.jsp" %>
        <!-- Section-->

        <c:if test="${sessionScope.alert != null}">
            <div class="alert alert-primary mt-5 text-center" role="alert">
                ${sessionScope.alert}
            </div>
        </c:if>

        <div class="container mt-5">
            <div class="row g-5 mt-1">
                <!-- Sider -->
                <div class="col-md-3">
                    <%@include file="../component/Sider.jsp" %>
                </div>

                <div class="col-md-9">
                    <form action="ProductList" method="get">
                        <input type="search" name="service" value="search" hidden>
                        <div class="row d-flex mb-2">
                            <div class="col-md-3">
                                <select name="sortBy" class="form-select me-2">
                                    <option <c:if test="${requestScope.sortBy == 'c' }">selected</c:if>
                                                                                        value="c">Sort by view</option>
                                        <option <c:if test="${requestScope.sortBy == 'a' }">selected</c:if>
                                                                                            value="a">Sort by update time</option>
                                        <option <c:if test="${requestScope.sortBy == 'b' }">selected</c:if>
                                                                                            value="b">Sort by price</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <select name="orderBy" class="form-select me-2">
                                        <option <c:if test="${requestScope.orderBy == '1'}">selected</c:if>
                                                                                            value="1">Descending</option>
                                        <option <c:if test="${requestScope.orderBy == '2'}">selected</c:if>
                                                                                            value="2">Ascending</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <input type="search" value="${requestScope.cateID}" name="cateID"
                                       hidden>
                                <input type="search" value="${requestScope.keyword}" name="keyword"
                                       hidden>
                                <input class="btn btn-primary btn-sm" type="submit" value="Submit">
                            </div>
                        </div>
                    </form>

                    <div
                        class="row gx-4 gx-lg-5 row-cols-1 row-cols-md-2 row-cols-xl-3 justify-content-center">
                        <c:forEach items="${requestScope.products}" var="p">
                            <div class="col mb-5">
                                <div class="card h-100">
                                    <!-- Product image-->
                                    <div class="card-header bg-transparent border-bottom-0 h-50">
                                        <c:if test="${p.thumbnail != null}">
                                            <c:if test="${p.discount!= 0}">
                                                <div class="badge bg-transparent text-white position-absolute" style="top: 0.5rem; right: 0.5rem">                                              
                                                    <img src="View/Img/XOgsXntflHYS.gif" height="40" alt=""/>
                                                </div>  
                                            </c:if>

                                            <img class="card-img-top h-100"
                                                 src="View/Img/${p.thumbnail}" alt="..." />
                                        </c:if>
                                        <c:if test="${p.thumbnail == null}">
                                            <img class="card-img-top"
                                                 src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg"
                                                 alt="..." />
                                        </c:if>
                                    </div>

                                    <!-- Product details-->
                                    <div class="card-body mt-5">
                                        <div class="text-center">
                                            <!-- Product name-->
                                            <h5 class="fw-bolder">${p.title}</h5>
                                            <div class="d-flex justify-content-center">
                                                <c:if test="${p.discount == 0}">
                                                    <p class="text-center ms-1"> ${p.price} $</p>
                                                </c:if>
                                                <c:if test="${p.discount!= 0}">
                                                    <p class="text-decoration-line-through"> ${p.price}$</p>
                                                    <p class="ms-3">
                                                        <fmt:formatNumber
                                                            value="${p.price*((100 - (p.discount))/100)}"
                                                            type="number" />$
                                                    </p>
                                                </c:if>  
                                            </div>
                                            <div>

                                                <p class="text-center">${p.view}<i class="bi bi-eye ms-1"></i></p>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Product actions-->
                                    <div class="card-footer border-top-0 bg-transparent">
                                        <a class="btn btn-outline-dark mt-auto me-2"
                                           href="ProductDetail?pid=${p.productID}">View Details</a>
                                        <a class="btn btn-outline-dark mt-auto"
                                           href="AddToCart?pid=${p.productID}"><i
                                                class="bi-cart-fill me-1"></i>Add To Cart</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:if test="${requestScope.totalPage > 3}">
                                <li class="page-item"><a class="page-link"
                                                         href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=<c:if test="
                                                                                                                                                                                        ${requestScope.page!=1}">${requestScope.page-1}
                                                         </c:if>
                                                         <c:if test="${requestScope.page==1}">1</c:if>">Previous</a></li>
                                    <c:if test="${requestScope.page == 1 }">
                                        <c:forEach var="i" begin="${requestScope.page}"
                                                   end="${requestScope.page+2}">
                                            <c:if test="${requestScope.page == i}">
                                            <li class="page-item active"><a class="page-link"
                                                                            href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=${i}">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${requestScope.page != i}">
                                            <li class="page-item"><a class="page-link"
                                                                     href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=${i}">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if
                                    test="${requestScope.page != 1 && requestScope.page <= (requestScope.totalPage - 1)}">
                                    <c:forEach var="i" begin="${requestScope.page}"
                                               end="${requestScope.page+2}">
                                        <c:if test="${requestScope.page == i}">
                                            <li class="page-item active"><a class="page-link"
                                                                            href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=${i}">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${requestScope.page != i}">
                                            <li class="page-item"><a class="page-link"
                                                                     href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=${i}">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${requestScope.totalPage == requestScope.page}">
                                    <li class="page-item"><a class="page-link"
                                                             href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=$${requestScope.totalPagei}">${requestScope.totalPage}</a>
                                    </li>
                                </c:if>
                                <c:if test="${requestScope.totalPage != requestScope.page}">
                                    <li class="page-item"><a class="page-link">...</a></li>
                                    </c:if>
                                <li class="page-item"><a class="page-link"
                                                         href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=<c:if test="
                                                                                                                                                                                        ${requestScope.page <
                                                                                                                                                                                          requestScope.totalPage}">${requestScope.page+1}</c:if>
                                                         <c:if test="${requestScope.page >= requestScope.totalPage}">
                                                             ${requestScope.totalPage}</c:if>">Next
                                                         </a></li>
                                </c:if>
                                <c:if test="${requestScope.totalPage < 4}">
                                    <c:if test="${requestScope.page == 1 }">
                                        <c:forEach var="i" begin="${requestScope.page}"
                                                   end="${requestScope.totalPage}">
                                            <c:if test="${requestScope.page == i}">
                                            <li class="page-item active"><a class="page-link"
                                                                            href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=${i}">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${requestScope.page != i}">
                                            <li class="page-item"><a class="page-link"
                                                                     href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=${i}">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if
                                    test="${requestScope.page != 1 && requestScope.page <= (requestScope.totalPage)}">
                                    <li class="page-item"><a class="page-link"
                                                             href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=1">1</a>
                                    </li>
                                    <c:forEach var="i" begin="${requestScope.page}"
                                               end="${requestScope.totalPage}">
                                        <c:if test="${requestScope.page == i}">
                                            <li class="page-item active"><a class="page-link"
                                                                            href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=${i}">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${requestScope.page != i}">
                                            <li class="page-item"><a class="page-link"
                                                                     href="ProductList?service=search&keyword=${requestScope.keyword}&orderBy=${requestScope.orderBy}&sortBy=${requestScope.sortBy}&cateID=${requestScope.cateID}&page=${i}">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:if>
                        </ul>
                    </nav>
                </div>

            </div>
        </div>
        <%@include file="../component/Footer.jsp" %>
    </body>

</html>