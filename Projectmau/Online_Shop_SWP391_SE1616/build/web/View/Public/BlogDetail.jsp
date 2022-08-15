<%-- 
    Document   : BlogDetail
    Created on : May 29, 2022, 9:50:51 PM
    Author     : Vu Dai Luong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Blog Post - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <%@include file="../component/Navbar.jsp" %>
        <!-- Page content-->
        <div class="container mt-5">
            <div class="row">
                <div class="col-lg-3">      
                    <%@include  file="../component/SiderBlog.jsp"%>
                </div>
                <div class="col-lg-9">
                    <!-- Post content-->
                    <article>
                        <!-- Post header-->
                        <header class="mb-4">
                            <!-- Post title-->
                            <h1 class="fw-bolder mb-1">${blog.postTitle}</h1>
                            <!-- Post meta content-->
                            <div class="text-muted fst-italic mb-2">Post Date:${blog.postDate}</div>
                            <div class="d-flex mt-4">
                                <div class="">
                                    <div class="fw-bold">Author: ${blog.author.name}</div>
                                </div>
                            </div>

                        </header>
                        <!-- Preview image figure-->
                        <figure class="mb-4"><img class="img-fluid rounded" src="View/imgpost/${blog.thumbnail}" alt="..." /></figure>
                        <!-- Post content-->
                        <section class="mb-5">
                            <p> ${blog.postContent} </p>
                        </section>
                    </article>
                    <!-- Comments section-->                   
                </div>

            </div>
        </div>
        <!-- Footer-->

        <%@include file="../component/Footer.jsp" %>
    </body>
</html>
