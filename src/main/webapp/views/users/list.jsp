
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Danh Sách Tài Khoản Phông Bạt</title>
    <!--Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <%@ include file="../layouts/navbar.jsp" %>
    <div class="card">
        <div class="card-header">
            User List
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-md-6">
                    <a class="btn btn-success" href="/admin/users/create">Add new</a>

                </div>
                <div class="col-12 col-md-6">
                    <form class="d-flex" action="/admin/users" method="get">
                        <input class="form-control" type="text" name="keyword">
                        <button class="btn btn-primary" type="submit">Search</button>
                    </form>
                </div>
            </div>

            Total user: <c:out value="${data.size()}"/>
            <table class="table">
                <tr>
                    <td>#</td>
                    <td>Name</td>
                    <td>Role</td>
                    <td></td>
                </tr>
                <c:forEach items="${data}" var="user" >
                    <tr>
                        <td><c:out value="${user.id}"/></td>
                        <td><c:out value="${user.username}"/></td>
                        <c:if test="${user.role == 1}">
                            <td>Admin</td>
                        </c:if>
                        <c:if test="${user.role == 2}">
                            <td>Employee</td>
                        </c:if>
                        <td>
                            <a class="btn btn-danger" onclick="return confirm('Are you sure?')" href="${pageContext.request.contextPath}/admin/users/delete?id=<c:out value="${user.id}"/> ">Delete</a>
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/users/update?id=<c:out value="${user.id}"/> ">Update</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="/admin/users?page=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach var = "i" begin = "1" end = "${totalPage}">

                        <c:if test="${currentPage == i}">
                            <li class="page-item active" aria-current="page" ><a class="page-link" href="/admin/users?page=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                        </c:if>
                        <c:if test="${currentPage != i}">
                            <li class="page-item" aria-current="page" ><a class="page-link" href="/admin/users?page=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                        </c:if>

                    </c:forEach>


                    <li class="page-item ${currentPage == totalPage ? 'disabled' : ''}">
                        <a class="page-link" href="/admin/users?page=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>


