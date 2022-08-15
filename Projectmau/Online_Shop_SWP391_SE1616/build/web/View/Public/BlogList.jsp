<%-- 
    Document   : BlogList
    Created on : May 26, 2022, 2:54:07 PM
    Author     : Vu Dai Luong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Blog Home - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <!-- Responsive navbar-->
        <%@include file="../component/Navbar.jsp" %>

        <div class="container mt-5">
            <div class="row g-5 mt-1">
                <!-- Sider -->
                <div class="col-md-3">
                    <%@include file="../component/SiderBlog.jsp" %>  
                </div>
                <div class="col-md-9">

                    <c:forEach items="${requestScope.ListPost}" var="l">
                        <div class="card mb-4">
                            <a href="BlogDetail?blogID=${l.postId}"><img class="card-img img-fluid mx-auto" src="View/imgpost/${l.thumbnail}" alt="..." /></a>
                            <div class="card-body">
                                <div class="col-sm-6">

                                    <div class="">Post Date:${l.postDate}</div> 
                                    <div class="">Author:${l.author.name}</div>
                                    <div class="">View:${l.view}</div>   
                                    <h2 class="card-title">${l.postTitle}</h2>
                                    <p class="card-text"> ${l.summary}</p>
                                    <a class="btn btn-primary" href="BlogDetail?blogID=${l.postId}">Read more →</a>
                                </div>
                            </div>  
                        </div>
                    </c:forEach> 

                </div>
            </div>
        </div>

        <!--pagging-->
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <a class="page-link" href="BlogList?page=${page-1}" tabindex="-1" aria-disabled="true">Previous</a>
                </li>
                <c:forEach begin="1" end="${totalPage}" var="p">
                    <li class="page-item ${p==page?"active":""}"><a class="page-link " href="BlogList?page=${p}">${p}</a></li>
                    </c:forEach> 
                <a class="page-link" href="BlogList?page=${page+1}">Next</a>
                </li>
            </ul>
        </nav>

        <!-- Footer-->
        <%@include file="../component/Footer.jsp" %>
    </body>
</html>
