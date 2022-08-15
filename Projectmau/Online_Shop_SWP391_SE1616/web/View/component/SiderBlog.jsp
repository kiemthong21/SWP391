<%-- 
    Document   : SiderBlog
    Created on : Jul 14, 2022, 2:46:03 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />

        <link href="View/Public/css/style.css" rel="stylesheet" type="text/css"/>
        <style type = "text/css">
            .imagepost{
                height: 80px;
                overflow:hidden;
            }
        </style>
    </head>
    <body>
        <div class="position-sticky" style="top: 5rem; border-radius: 10px; background-color: white" >   
            <div class="mb-4">
                <div class="row">
                    <div class="col-md-12 mb-3">
                        <form class="mb-3" action="BlogSearch">
                            <input class="form-control me-2" type="search" placeholder="Search Blog" aria-label="Search" name="keyword" value="${requestScope.keyword}">
                            <button class="btn btn-primary mt-2 mb-2 w-100" type="submit">Search</button>
                        </form>
                    </div>

                    <div class="accordion mb-3" id="accordionExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <strong>Category Blog</strong>
                                </button>
                            </h2>
                            <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    <div class="form-check">
                                        <c:forEach items="${st}" var="c">
                                            <div class = "col">
                                                <a class="text-decoration-none" style="color: black" href="BlogListCategory?categoryID=${c.settingID}">${c.name} </a>               
                                            </div>
                                        </c:forEach>  
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <h3 class="mb-3">What news to day?</h3>
            <c:forEach  items="${requestScope.lposts}" var="p">
                <div class="card mb-2" style="max-width: 540px;">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <div class="badge bg-transparent text-white position-absolute">                                              
                                <img src="View/Img/c338c3_a71d72719cdb46f1adfbdd414f524d8f_mv2.gif" height="30" alt=""/>
                            </div>  
                            <img src="View/imgpost/${p.thumbnail}" class="img-fluid rounded-start imagepost" alt="...">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <c:if test = "${p.postTitle.length() < 20}">
                                    <h5 class="card-title h5">${p.postTitle}</h2>
                                </c:if>
                                <c:if test = "${p.postTitle.length() > 20}">
                                    <c:set var="title" value="${fn: substring(p.postTitle, 0, 20)}" />
                                    <h5 class="card-title h5">${title}...</h2>
                                </c:if>
                                <a href="BlogDetail?blogID=${p.postId}" class="stretched-link"></a>
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

