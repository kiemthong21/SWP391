<%-- 
    Document   : changePassword
    Created on : May 30, 2022, 9:00:03 PM
    Author     : Hieuhihi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <form class="mx-auto mt-5" action="ChangePass" style="max-width: 500px"  method="post">
                <c:if test="${error!=null}">
                    <div class="alert alert-danger mb-3" role="alert">
                        ${error}
                    </div>
                </c:if>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="new-password" class="form-label">New password</label>
                    <input type="password" name="new-password" class="form-control" id="new-password" required>
                </div>
                <div class="mb-3">
                    <label for="confirm-password" class="form-label">Confirm password</label>
                    <input type="password" name="confirm-password" class="form-control" id="confirm-password" required>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</html>
